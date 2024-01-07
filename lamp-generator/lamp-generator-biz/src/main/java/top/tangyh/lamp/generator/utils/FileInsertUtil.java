package top.tangyh.lamp.generator.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.model.constant.EchoApi;
import top.tangyh.lamp.model.constant.EchoDictType;
import top.tangyh.lamp.model.constant.EchoRef;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/4/20 3:55 PM
 * @create [2022/4/20 3:55 PM ] [tangyh] [初始创建]
 */
@Slf4j
@AllArgsConstructor
public class FileInsertUtil {
    public final static String SLOT_PAT = "@lamp.generator auto insert ([a-zA-Z0-9._]+)( -->)?";
    private static final Set<String> ONE_TAB = CollUtil.newHashSet(EchoDictType.class.getSimpleName(), EchoRef.class.getSimpleName(), EchoApi.class.getSimpleName());
    /** 要操作的文件的路径 */
    private String filePath;
    private String prefix;
    private Map<String, String> map;

    /**
     * 获取服务器的换行符
     */
    public static String getSeparator() {
        String separator = System.getProperty("line.separator");
        return separator == null ? "\r\n" : separator;
    }

    public static String repeatTab(int count) {
        return StrUtil.repeat(StrPool.TAB, count);
    }

    public static String repeatTab() {
        return repeatTab(1);
    }

    public static FileInsertUtil of(String path, Map<String, String> map) {
        return new FileInsertUtil(path, null, map);
    }

    public static FileInsertUtil of(String path, String prefix, Map<String, String> map) {
        return new FileInsertUtil(path, prefix, map);
    }

    @SneakyThrows
    public String replaceAll() {
        URL resource = getClass().getResource(filePath);
        if (resource == null) {
            throw BizException.wrap("文件不存在 [{}] ", filePath);
        }
        String dbPath = resource.getPath();
        File file = new File(dbPath);
        if (!file.exists()) {
            String tmpDir = System.getProperties().getProperty(StrPool.JAVA_TEMP_DIR);
            dbPath = tmpDir + filePath;
            file = new File(dbPath);
            String classPath = "classpath:" + filePath;
            InputStream resourceAsStream = ResourceUtil.getStreamSafe(classPath);
            if (resourceAsStream != null) {
                FileUtils.copyInputStreamToFile(resourceAsStream, file);
            }
        }

        String con = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        return StrUtil.replace(con, SLOT_PAT, matcher -> getText(con, matcher));
    }

    private String getText(String con, Matcher matcher) {
        String slot = StrUtil.trim(matcher.group(0));
        String key = StrUtil.trim(matcher.group(1));
        if (this.map.containsKey(key)) {
            String value = StrUtil.trim(this.map.get(key));

            if (StrUtil.contains(con, value)) {
                log.info("检测到【{}】中已经存在【{}】，已经忽略在该文件中追加内容", filePath, value);
                return slot;
            }

            String enter = getSeparator();
            String pre;
            if (StrUtil.isNotEmpty(prefix)) {
                pre = prefix;
            } else {
                pre = ONE_TAB.contains(key) ? repeatTab() : repeatTab(2);
            }
            return slot + enter + pre + value;
        } else {
            log.info("检测到【{}】中不存在插槽【{}】，已经忽略在该文件中追加内容", filePath, key);
            return slot;
        }
    }


    @SneakyThrows
    public void writeFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            // 只能在本地IDEA中运行时，可以在生成代码时，写入常量文件。jar启动时，无法写入常量文件！
            throw BizException.wrap("文件不存在：{}", filePath);
        }

        // 读取
        String con = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        // 替换
        String text = StrUtil.replace(con, SLOT_PAT, matcher -> getText(con, matcher));
        // 写入
        FileUtils.writeStringToFile(file, text, StandardCharsets.UTF_8);
    }
}

