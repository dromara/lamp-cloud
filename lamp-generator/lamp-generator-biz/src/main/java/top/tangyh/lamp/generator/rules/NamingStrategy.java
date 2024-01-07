package top.tangyh.lamp.generator.rules;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import top.tangyh.basic.utils.StrPool;

import java.util.Arrays;
import java.util.List;

/**
 * 从数据库表到文件的命名策略
 *
 * @author zuihou
 * @date 2022/3/13 22:13
 */
public enum NamingStrategy {

    /**
     * 不做任何改变，原样输出
     */
    no_change,

    /**
     * 下划线转驼峰命名
     */
    underline_to_camel;

    /**
     * 下划线转驼峰
     *
     * @param name 待转内容
     */
    public static String underlineToCamel(String name) {
        // 快速检查
        if (StringUtils.isBlank(name)) {
            // 没必要转换
            return StringPool.EMPTY;
        }
        String tempName = name;
        // 大写数字下划线组成转为小写 , 允许混合模式转为小写
        if (StringUtils.isCapitalMode(name) || StringUtils.isMixedMode(name)) {
            tempName = name.toLowerCase();
        }
        StringBuilder result = new StringBuilder();
        // 用下划线将原始字符串分割
        String[] camels = tempName.split(StrPool.UNDERSCORE);
        // 跳过原始字符串中开头、结尾的下换线或双重下划线
        // 处理真正的驼峰片段
        Arrays.stream(camels).filter(camel -> !StringUtils.isBlank(camel)).forEach(camel -> {
            if (result.length() == 0) {
                // 第一个驼峰片段，首字母都小写
                result.append(StringUtils.firstToLowerCase(camel));
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(capitalFirst(camel));
            }
        });
        return result.toString();
    }

    /**
     * 去掉指定的前缀
     *
     * @param name   表名
     * @param prefix 前缀
     * @return 转换后的字符串
     */
    public static String removePrefix(String name, List<String> prefix) {
        if (StringUtils.isBlank(name)) {
            return StringPool.EMPTY;
        }
        // 判断是否有匹配的前缀，然后截取前缀
        return prefix.stream().distinct().filter(pf -> name.toLowerCase().startsWith(pf.toLowerCase()))
                .findFirst().map(pf -> name.substring(pf.length())).orElse(name);
    }

    /**
     * 去掉下划线前缀并转成驼峰格式
     *
     * @param name   表名
     * @param prefix 前缀
     * @return 转换后的字符串
     */
    public static String removePrefixAndCamel(String name, List<String> prefix) {
        return underlineToCamel(removePrefix(name, prefix));
    }

    /**
     * 去掉指定的后缀
     *
     * @param name   表名
     * @param suffix 后缀
     * @return 转换后的字符串
     */
    public static String removeSuffix(String name, List<String> suffix) {
        if (StringUtils.isBlank(name)) {
            return StringPool.EMPTY;
        }
        // 判断是否有匹配的后缀，然后截取后缀
        return suffix.stream().distinct().filter(sf -> name.toLowerCase().endsWith(sf.toLowerCase()))
                .findFirst().map(sf -> name.substring(0, name.length() - sf.length())).orElse(name);
    }

    /**
     * 去掉下划线后缀并转成驼峰格式
     *
     * @param name   表名
     * @param suffix 后缀
     * @return 转换后的字符串
     */
    public static String removeSuffixAndCamel(String name, List<String> suffix) {
        return underlineToCamel(removeSuffix(name, suffix));
    }

    /**
     * 实体首字母大写
     *
     * @param name 待转换的字符串
     * @return 转换后的字符串
     */
    public static String capitalFirst(String name) {
        if (StringUtils.isNotBlank(name)) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return StringPool.EMPTY;
    }
}
