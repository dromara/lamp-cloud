package com.tangyh.lamp.file.biz;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.tangyh.basic.utils.CollHelper;
import com.tangyh.basic.utils.StrPool;
import com.tangyh.lamp.file.domain.FileDO;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.utils.ZipUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 文件和附件的一些公共方法
 *
 * @author zuihou
 * @date 2019/05/06
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class FileBiz {

    private final FileServerProperties fileProperties;

    private static String buildNewFileName(String filename, Integer order) {
        return StrUtil.strBuilder(filename).insert(filename.lastIndexOf(StrPool.DOT), "(" + order + ")").toString();
    }


    private static Predicate<FileDO> getFilePredicate() {
        return file -> file != null && StrUtil.isNotEmpty(file.getUrl());
    }

    public void down(List<FileDO> list, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取内网前缀地址
        String innerUriPrefix = fileProperties.getInnerUriPrefix();
        String remoteUriPrefix = fileProperties.getUriPrefix();
        log.info("内网前缀地址 innerUriPrefix={}", innerUriPrefix);
        long fileSize = list.stream().filter(getFilePredicate())
                .mapToLong(file -> Convert.toLong(file.getSize(), 0L)).sum();
        String extName = list.get(0).getOriginalFileName();
        if (list.size() > 1) {
            extName = StrUtil.subBefore(extName, ".", true) + "等.zip";
        }

        Map<String, String> map = new LinkedHashMap<>(CollHelper.initialCapacity(list.size()));
        Map<String, Integer> duplicateFile = new HashMap<>(map.size());
        list.stream()
                //过滤不符合要求的文件
                .filter(getFilePredicate())
                //将外网地址转成内网地址
                .peek(file -> {
                    String url = file.getUrl();
                    if (StrUtil.isNotEmpty(innerUriPrefix)) {
                        //转为内网渠道下载
                        url = url.replaceAll(remoteUriPrefix, innerUriPrefix);
                        log.info("文件转内网 url地址 ={}", url);
                    }
                    file.setUrl(url);
                })
                //循环处理相同的文件名
                .forEach(file -> {
                    String submittedFileName = file.getOriginalFileName();
                    if (map.containsKey(submittedFileName)) {
                        if (duplicateFile.containsKey(submittedFileName)) {
                            duplicateFile.put(submittedFileName, duplicateFile.get(submittedFileName) + 1);
                        } else {
                            duplicateFile.put(submittedFileName, 1);
                        }
                        submittedFileName = buildNewFileName(submittedFileName, duplicateFile.get(submittedFileName));
                    }
                    map.put(submittedFileName, file.getUrl());
                });


        ZipUtils.zipFilesByInputStream(map, fileSize, extName, request, response);
    }


}
