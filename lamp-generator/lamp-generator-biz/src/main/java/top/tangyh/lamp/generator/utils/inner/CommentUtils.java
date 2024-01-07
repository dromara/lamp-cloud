package top.tangyh.lamp.generator.utils.inner;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.generator.rules.echo.EchoDict;
import top.tangyh.lamp.generator.rules.echo.EchoType;
import top.tangyh.lamp.generator.rules.enumeration.EnumType;
import top.tangyh.lamp.generator.rules.enumeration.EnumTypeKeyValue;
import top.tangyh.lamp.generator.utils.GenCodeConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static top.tangyh.lamp.generator.utils.inner.PackageUtils.getName;

/**
 * 解析注释中的内容
 *
 * @author zuihou
 * @date 2022/3/22 13:15
 */
public class CommentUtils {

    /**
     * Echo 注解解析 正则
     *
     * <p>
     *
     * @Echo() 内部的字段编写顺序一定是： api、ref、beanClass、dictType， 除了api必填，其他都可以不填
     * api、ref、dictType 可以直接写字符串，也能写常量，但
     * api 的常量只能存放在 EchoApi
     * ref 的常量只能存放在 EchoRef
     * dictType 的常量只能存放在 EchoDictType
     * <p>
     * api、ref、beanClass、dictType 中使用的类需要提前在 lamp.generator.constantsPackage 中配置
     * <p>
     * 如：
     * 匹配： @Echo(api="")
     * 匹配： @Echo(api="", dictType = "")
     * 匹配： @Echo(api="", beanClass=Xxx.class)
     * 匹配： @Echo(api="", ref="", beanClass=Xxx.class)
     * 匹配： @Echo(api="orgApi", ref="" beanClass=Org.class, dictType="")
     */
    public final static Pattern ECHO_FIELD_PATTERN = Pattern.compile("(@Echo[(](api|feign)? *= *([a-zA-Z\\d\"._]+)(, *ref *= *([a-zA-Z\\d\"._]+))?(, *beanClass *= *([a-zA-Z\\d\"._]+))?(, *dictType *= *([a-zA-Z\\d\"._]+))?[)])");
    /**
     * 字典列表解析
     * <p>
     * 注释模板1： 注释内容 [key-value ...]
     */
    public final static Pattern ECHO_DICT_ITEM_PATTERN = Pattern.compile("\\[(.*?)?]");

    /**
     * 字典列表解析 正则
     * 匹配 key-value 形式的注释
     */
    public final static Pattern ECHO_DICT_ITEM_KEY_VALUE_PATTERN = Pattern.compile("(.*?)-(.*?)? ");

    /**
     * 枚举类型 正则
     * 匹配 xx:xx; 形式的注释
     */
    public final static Pattern ENUM_KEY_VALUE_PATTERN = Pattern.compile("([A-Za-z1-9_-]+):(.*?)?;");
    /**
     * 枚举类型解析
     * <p>
     * 注释模板1： 注释内容 #枚举类名{枚举值英文名:"枚举值英文注释";  ...}
     * 注释模板2： 注释内容 #枚举类名{枚举值英文名:val,"枚举值英文注释";  ...}
     * 注释模板3： 注释内容 #枚举类名{枚举值英文名:val,"枚举值英文注释",val2;  ...}
     * 注释模板4： 注释内容 #{枚举值英文名:"枚举值英文注释";  ...}
     */
    public final static Pattern ENUM_FIELD_PATTERN = Pattern.compile("(#([a-zA-Z\\d\"._]+)?[{](.*?)?[}])");

    public static EchoDict getEchoDict(String dictType, String fieldComment, String comment) {
        if (StrUtil.isEmpty(dictType)) {
            return null;
        }
        Matcher dictMatcher = ECHO_DICT_ITEM_PATTERN.matcher(comment);
        if (dictMatcher.find()) {
            String dictComment = dictMatcher.group(0);
            String dictItemStr = dictMatcher.group(1);
            EchoDict dict = EchoDict.of(dictComment, dictType, StrUtil.isEmpty(fieldComment) ? dictType : fieldComment);
            if (StrUtil.isEmpty(dictItemStr)) {
                return dict;
            }
            dictItemStr = StrUtil.endWith(dictItemStr, StrPool.SPACE) ? dictItemStr : dictItemStr + StrPool.SPACE;

            Matcher kvMatcher = ECHO_DICT_ITEM_KEY_VALUE_PATTERN.matcher(dictItemStr);
            List<EchoDict> itemList = new ArrayList<>();
            while (kvMatcher.find()) {
                String key = trim(kvMatcher.group(1));
                String value = trim(kvMatcher.group(2));
                EchoDict kv = EchoDict.of(key, value);
                itemList.add(kv);
            }
            dict.setItemList(itemList);
            return dict;
        }
        return null;
    }

    public static EchoType getEchoType(String comment) {
        if (StrUtil.isEmpty(comment)) {
            return null;
        }
        Matcher matcher = ECHO_FIELD_PATTERN.matcher(comment);
        if (matcher.find()) {
            String echoStr = trim(matcher.group(1));
            String apiValue = trim(matcher.group(3));
            String refValue = trim(matcher.group(5));
            String beanClassValue = trim(matcher.group(7));
            String dictType = trim(matcher.group(9));

            EchoType et = new EchoType();
            et.setEchoStr(echoStr);
            et.setApi(apiValue);
            et.setRef(refValue);
            et.setDictType(dictType);
            et.setBeanClass(beanClassValue);
            et.valid();
            return et;
        }
        return null;
    }

    public static String getEchoAnnotation(String comment) {
        if (StrUtil.isEmpty(comment)) {
            return StrPool.EMPTY;
        }
        Matcher matcher = ECHO_FIELD_PATTERN.matcher(comment);
        if (matcher.find()) {
            return trim(matcher.group(1));
        }
        return StrPool.EMPTY;
    }

    public static EnumType getEnumStr(String entityName, String javaField, String formatEnumFileName, String swaggerComment, String comment) {
        if (StrUtil.isEmpty(comment)) {
            return null;
        }
        EnumType et = new EnumType();
        et.setSwaggerComment(swaggerComment);
        Matcher matcher = ENUM_FIELD_PATTERN.matcher(comment);

        if (matcher.find()) {
            // 枚举字符串
            String enumStr = trim(matcher.group(1));
            // 枚举名称
            String enumName = trim(matcher.group(2));
            // kv
            String keyValue = trim(matcher.group(3));
            et.setEnumStr(enumStr);
            enumName = StrUtil.isEmpty(enumName) ? getName(entityName + StrUtil.upperFirst(javaField), formatEnumFileName, GenCodeConstant.ENUM) : enumName;
            et.setEnumName(enumName);
            et.setKeyValue(keyValue);
            List<EnumTypeKeyValue> kvList = new ArrayList<>();
            if (StrUtil.isNotEmpty(keyValue)) {
                keyValue = keyValue.endsWith(StrPool.SEMICOLON) ? keyValue : keyValue + StrPool.SEMICOLON;
                Matcher kvMatcher = ENUM_KEY_VALUE_PATTERN.matcher(keyValue);
                while (kvMatcher.find()) {
                    String key = trim(kvMatcher.group(1));
                    String valueStr = trim(kvMatcher.group(2));
                    List<String> valueList = StrUtil.split(valueStr, StrPool.COMMA);
                    List<String> values = valueList.stream().filter(StrUtil::isNotEmpty).toList();
                    EnumTypeKeyValue etKv = new EnumTypeKeyValue();
                    etKv.setKey(key);
                    etKv.setValues(values);
                    kvList.add(etKv);
                }
            }

            et.setKvList(kvList);
            return et;
        }
        return null;
    }

    public static String trim(String val) {
        return val == null ? StringPool.EMPTY : val.trim();
    }
}
