package top.tangyh.lamp.generator.rules.echo;

import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.StrUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.StrPool;

import java.util.List;

/**
 * @author zuihou
 * @date 2022/3/22 16:49
 */
@Getter
@EqualsAndHashCode(of = "echoStr")
public class EchoType {
    private static final String KEY_VALUE = "String {} = \"{}\";";
    /** 数据库注释中解析的原始 @Echo 字符串 */
    String echoStr;

    /** 数据库注释中解析的@Echo()内部的 api 的值 */
    String api;
    String apiConstants;
    String apiField;
    /** 数据库注释中解析的@Echo()内部的 api 的值是否为常量 */
    Boolean apiIsConstants;

    /** 数据库注释中解析的@Echo()内部的 ref 的值 */
    String ref;
    String refConstants;
    String refField;
    /** 数据库注释中解析的@Echo()内部的 ref 的值是否为常量 */
    Boolean refIsConstants;

    /** 数据库注释中解析的@Echo()内部的 dictType 的值 */
    String dictType;
    String dictTypeConstants;
    String dictTypeField;
    String dictTypePosition;
    /** 数据库注释中解析的@Echo()内部的 dictType 的值是否为常量 */
    Boolean dictTypeIsConstants;

    /** 数据库注释中解析的@Echo()内部的 beanClass 的值 */
    String beanClass;

    /** 数据库注释中解析的原始 字典字符串 */
    String dictStr;

    private static String getKeyValue(String content) {
        List<String> split = StrUtil.split(content, StrPool.DOT);
        if (split.size() >= 2) {
            return StrUtil.format(KEY_VALUE, split.get(split.size() - 1), split.get(split.size() - 1));
        }
        return StrPool.EMPTY;
    }

    public EchoType setDictStr(String dictStr) {
        this.dictStr = dictStr;
        return this;
    }

    public EchoType setEchoStr(String echoStr) {
        this.echoStr = echoStr;
        return this;
    }

    public EchoType setBeanClass(String beanClass) {
        this.beanClass = beanClass;
        return this;
    }

    public EchoType setApi(String api) {
        this.api = api;
        this.apiIsConstants = !StrUtil.startWith(api, CharPool.DOUBLE_QUOTES);
        if (this.apiIsConstants) {
            this.apiConstants = getKeyValue(this.api);
            this.apiField = getField(this.api);
        }
        return this;
    }

    public EchoType setRef(String ref) {
        this.ref = ref;
        this.refIsConstants = !StrUtil.startWith(ref, CharPool.DOUBLE_QUOTES);
        if (this.refIsConstants) {
            this.refConstants = getKeyValue(this.ref);
            this.refField = getField(this.ref);
        }
        return this;
    }

    public EchoType setDictType(String dictType) {
        this.dictType = dictType;
        this.dictTypeIsConstants = !StrUtil.startWith(dictType, CharPool.DOUBLE_QUOTES);
        if (this.dictTypeIsConstants) {
            this.dictTypeConstants = getKeyValue(this.dictType);
            this.dictTypeField = getField(this.dictType);
            List<String> split = StrUtil.split(dictType, StrPool.DOT);
            if (split.size() >= 2) {
                this.dictTypePosition = split.get(split.size() - 2);
            }
        }
        return this;
    }

    private String getField(String value) {
        List<String> split = StrUtil.split(value, StrPool.DOT);
        if (split.size() >= 1) {
            return split.get(split.size() - 1);
        }
        return StrPool.EMPTY;
    }

    public void valid() {
        if (refIsConstants && StrUtil.isNotEmpty(ref)) {
            ArgumentAssert.isTrue(StrUtil.split(this.ref, StrPool.DOT).size() > 1, "@Echo注解中ref属性: [{}] 必须是字符串或常量", ref);
        }
        if (apiIsConstants && StrUtil.isNotEmpty(api)) {
            ArgumentAssert.isTrue(StrUtil.split(this.api, StrPool.DOT).size() > 1, "@Echo注解中api属性: [{}] 必须是字符串或常量", api);
        }
        if (dictTypeIsConstants && StrUtil.isNotEmpty(dictType)) {
            ArgumentAssert.isTrue(StrUtil.split(this.dictType, StrPool.DOT).size() > 1, "@Echo注解中dictType属性: [{}] 必须是字符串或常量", dictType);
        }
        if (StrUtil.isNotEmpty(beanClass)) {
            ArgumentAssert.isTrue(StrUtil.contains(beanClass, StrPool.DOT), "@Echo注解中beanClass属性: [{}] 必须是有效的Class", beanClass);
        }
    }
}
