package top.tangyh.lamp.file.strategy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.tangyh.basic.base.R;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.file.dao.FileMapper;
import top.tangyh.lamp.file.domain.FileDeleteBO;
import top.tangyh.lamp.file.domain.FileGetUrlBO;
import top.tangyh.lamp.file.dto.chunk.FileChunksMergeDTO;
import top.tangyh.lamp.file.entity.File;
import top.tangyh.lamp.file.enumeration.FileStorageType;
import top.tangyh.lamp.file.properties.FileServerProperties;
import top.tangyh.lamp.file.utils.ZipUtils;
import top.tangyh.lamp.file.vo.param.FileUploadVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author zuihou
 * @date 2021/7/8 10:26
 */
@Slf4j
@Component
public class FileContext {
    private final Map<String, FileStrategy> contextStrategyMap = new ConcurrentHashMap<>();
    private final Map<String, FileChunkStrategy> contextChunkStrategyMap = new ConcurrentHashMap<>();
    private final FileServerProperties fileServerProperties;
    private final FileMapper fileMapper;

    public FileContext(Map<String, FileStrategy> map,
                       Map<String, FileChunkStrategy> chunkMap,
                       FileServerProperties fileServerProperties,
                       FileMapper fileMapper) {
        map.forEach(this.contextStrategyMap::put);
        chunkMap.forEach(this.contextChunkStrategyMap::put);
        this.fileServerProperties = fileServerProperties;
        this.fileMapper = fileMapper;
    }

    private static Predicate<File> getFilePredicate() {
        return file -> file != null && StrUtil.isNotEmpty(file.getUrl());
    }

    private static String buildNewFileName(String filename, Integer order) {
        return StrUtil.strBuilder(filename).insert(filename.lastIndexOf(StrPool.DOT), "(" + order + ")").toString();
    }

    /**
     * 文件上传
     *
     * @param file         文件
     * @param fileUploadVO 文件上传参数
     * @return 文件对象
     */
    public File upload(MultipartFile file, FileUploadVO fileUploadVO) {
        FileStrategy fileStrategy = getFileStrategy(fileUploadVO.getStorageType());
        return fileStrategy.upload(file, fileUploadVO.getBucket(), fileUploadVO.getBizType());
    }

    private FileStrategy getFileStrategy(FileStorageType storageType) {
        storageType = storageType == null ? fileServerProperties.getStorageType() : storageType;
        FileStrategy fileStrategy = contextStrategyMap.get(storageType.name());
        ArgumentAssert.notNull(fileStrategy, "请配置正确的文件存储类型");
        return fileStrategy;
    }

    /**
     * 删除源文件
     *
     * @param list 列表
     * @return 是否成功
     */
    public boolean delete(List<File> list) {
        if (!fileServerProperties.getDelFile()) {
            return false;
        }

        list.forEach(item -> {
            FileDeleteBO fileDeleteBO = FileDeleteBO.builder()
                    .bucket(item.getBucket())
                    .path(item.getPath())
                    .storageType(item.getStorageType())
                    .build();
            FileStrategy fileStrategy = getFileStrategy(item.getStorageType());
            fileStrategy.delete(fileDeleteBO);
        });
        return true;
    }

    /**
     * 根据路径获取访问地址
     *
     * @param paths 文件查询对象
     * @return
     */
    public Map<String, String> findUrlByPath(List<String> paths) {
        List<File> pathFiles = fileMapper.selectList(Wraps.<File>lbQ().in(File::getPath, paths));

        return findUrl(pathFiles);
    }

    private Map<String, String> findUrl(List<File> pathFiles) {
        Map<String, List<File>> pathMap = pathFiles.stream().collect(Collectors.groupingBy(File::getPath, LinkedHashMap::new, toList()));

        Map<String, String> map = new LinkedHashMap<>(CollHelper.initialCapacity(pathMap.size()));
        pathMap.forEach((path, files) -> {
            if (CollUtil.isEmpty(files)) {
                return;
            }
            File fileFile = files.get(0);

            if (FileStorageType.LOCAL.eq(fileFile.getStorageType())) {
                map.put(path, fileFile.getUrl());
            } else {
                FileStrategy fileStrategy = getFileStrategy(fileFile.getStorageType());
                map.put(path, fileStrategy.getUrl(FileGetUrlBO.builder()
                        .bucket(fileFile.getBucket())
                        .path(fileFile.getPath())
                        .originalFileName(fileFile.getOriginalFileName())
                        .build()));
            }
        });
        return map;
    }

    public Map<Long, String> findUrlById(List<Long> ids) {
        List<File> pathFiles = fileMapper.selectList(Wraps.<File>lbQ().in(File::getId, ids));

        Map<Long, List<File>> pathMap = pathFiles.stream().collect(Collectors.groupingBy(File::getId, LinkedHashMap::new, toList()));

        Map<Long, String> map = new LinkedHashMap<>(CollHelper.initialCapacity(pathMap.size()));
        pathMap.forEach((id, files) -> {
            if (CollUtil.isEmpty(files)) {
                return;
            }
            File fileFile = files.get(0);

            FileStrategy fileStrategy = getFileStrategy(fileFile.getStorageType());
            map.put(id, fileStrategy.getUrl(FileGetUrlBO.builder()
                    .bucket(fileFile.getBucket())
                    .path(fileFile.getPath())
                    .originalFileName(fileFile.getOriginalFileName())
                    .build()));
        });
        return map;
    }

    public void download(HttpServletRequest request, HttpServletResponse response, List<File> list) throws Exception {
        for (File fileFile : list) {
            FileStrategy fileStrategy = getFileStrategy(fileFile.getStorageType());
            String url = fileStrategy.getUrl(FileGetUrlBO.builder()
                    .bucket(fileFile.getBucket())
                    .path(fileFile.getPath())
                    .build());
            fileFile.setUrl(url);
        }
        down(request, response, list);
    }

    public void down(HttpServletRequest request, HttpServletResponse response, List<File> list) throws Exception {
        long fileSize = list.stream().filter(getFilePredicate())
                .mapToLong(file -> Convert.toLong(file.getSize(), 0L)).sum();
        String packageName = list.get(0).getOriginalFileName();
        if (list.size() > 1) {
            packageName = StrUtil.subBefore(packageName, ".", true) + "等.zip";
        }

        Map<String, String> map = new LinkedHashMap<>(CollHelper.initialCapacity(list.size()));
        Map<String, Integer> duplicateFile = new HashMap<>(map.size());
        list.stream()
                //过滤不符合要求的文件
                .filter(getFilePredicate())
                //循环处理相同的文件名
                .forEach(file -> {
                    String originalFileName = file.getOriginalFileName();
                    if (map.containsKey(originalFileName)) {
                        if (duplicateFile.containsKey(originalFileName)) {
                            duplicateFile.put(originalFileName, duplicateFile.get(originalFileName) + 1);
                        } else {
                            duplicateFile.put(originalFileName, 1);
                        }
                        originalFileName = buildNewFileName(originalFileName, duplicateFile.get(originalFileName));
                    }
                    map.put(originalFileName, file.getUrl());
                });


        ZipUtils.zipFilesByInputStream(map, fileSize, packageName, request, response);
    }

    private FileChunkStrategy getFileChunkStrategy(FileStorageType storageType) {
        storageType = storageType == null ? fileServerProperties.getStorageType() : storageType;
        FileChunkStrategy fileStrategy = contextChunkStrategyMap.get(storageType.name());
        ArgumentAssert.notNull(fileStrategy, "请配置正确的文件存储类型");
        return fileStrategy;
    }

    /**
     * 根据md5检测文件
     *
     * @param md5    md5
     * @param userId 用户id
     * @return 附件
     */
    public File md5Check(String md5, Long userId) {
        FileChunkStrategy fileChunkStrategy = getFileChunkStrategy(fileServerProperties.getStorageType());
        return fileChunkStrategy.md5Check(md5, userId);
    }

    /**
     * 合并文件
     *
     * @param merge 合并参数
     * @return 附件
     */
    public R<File> chunksMerge(FileChunksMergeDTO merge) {
        FileChunkStrategy fileChunkStrategy = getFileChunkStrategy(fileServerProperties.getStorageType());
        return fileChunkStrategy.chunksMerge(merge);
    }
}
