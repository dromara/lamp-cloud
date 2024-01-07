package top.tangyh.lamp.generator.utils;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import top.tangyh.lamp.generator.rules.echo.EchoDict;
import top.tangyh.lamp.generator.rules.echo.EchoType;
import top.tangyh.lamp.generator.utils.inner.CommentUtils;

import java.util.regex.Matcher;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/4/2 7:40 PM
 * @create [2022/4/2 7:40 PM ] [tangyh] [初始创建]
 */
public class CommentUtilsTest {

    @Test
    public void testEnum() {
//        String comment = "注释内容 #UserEnum{MAN:123; WOMAN:\"女\"}";
//        String comment = "注释内容 #UserEnum{MAN_M:\"枚举值英文注释\"}";
//        String comment = "注释内容 #UserEnum{MAN_M:1,\"枚举值英文注释\";}";
//        String comment = "注释内容 #UserEnum{MAN_M:2,\"枚举值英文注释\",\"123\";}";
        String comment = "注释内容 #{MAN_M:\"枚举值英文注释\";}";
        Matcher matcher = CommentUtils.ENUM_FIELD_PATTERN.matcher(comment);
        if (matcher.find()) {
            String enumComment = CommentUtils.trim(matcher.group(1)); // 枚举字符串
            String enumName = CommentUtils.trim(matcher.group(2)); // 枚举名称
            String keyValue = CommentUtils.trim(matcher.group(3)); // kv

            if (StrUtil.isNotEmpty(keyValue)) {
                keyValue = keyValue.endsWith(";") ? keyValue : keyValue + ";";
                Matcher kvMatcher = CommentUtils.ENUM_KEY_VALUE_PATTERN.matcher(keyValue);
                while (kvMatcher.find()) {
                    String key = CommentUtils.trim(kvMatcher.group(1));
                    String value = CommentUtils.trim(kvMatcher.group(2));
                }
            }

        }
    }

    @Test
    public void testEcho() {
//        String comment = "@Echo(api=\"xxxx\", ref=\"bbbbb\", beanClass=1) RemoteData<Long, Org>";
//        String comment = "@Echo(api = \"userApi\",   ref = USER_ID_NAME_METHOD) ";
//        String comment = "@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.EDUCATION)";
//        String comment = "@Echo(api=\"xxxx\")";
//        String comment = "@Echo(api=EchoApi.dddd, ref=EchoDictType.AAA, beanClass= com.aa.ded.EchoDictType.class, dictType = EchoDictType.Global.NATION) RemoteData<Long, Org>";
        String comment = "@Echo(api=EchoApi.dddd, beanClass= com.aa.ded.EchoDictType.class, dictType = EchoDictType.NATION) RemoteData<Long, Org>";
//        String comment = "@Echo(api=EchoDictType.dddd, ref=EchoDictType.AAA, beanClass= EchoDictType.class, dictType = EchoDictType.NATION) RemoteData<Long, Org>";
//        String comment = "@Echo(api=\"xxxx\", ref=\"bbbbb\", beanClass=1, dictType = EchoDictType.NATION) RemoteData<Long, Org>";
        Matcher matcher = CommentUtils.ECHO_FIELD_PATTERN.matcher(comment);
        if (matcher.find()) {
            String annotation = CommentUtils.trim(matcher.group(1)); // @Echo(api="xxxx", ref="bbbbb")
            String apiValue = CommentUtils.trim(matcher.group(3)); //xxxx
            String refValue = CommentUtils.trim(matcher.group(5));  //bbbbb
            String beanClassValue = CommentUtils.trim(matcher.group(7));
            String dictType = CommentUtils.trim(matcher.group(9));
            System.out.println(111);
        }
        EchoType echoType = CommentUtils.getEchoType(comment);
        System.out.println(echoType);
    }

    @Test
    public void testDictItem() {
        String comment = "士大夫; [1-女生 2-男生] @Echo(api=EchoApi.dddd, beanClass= com.aa.ded.EchoDictType.class, dictType = EchoDictType.NATION) RemoteData<Long, Org>";
//        String comment = "@Echo(api=EchoDictType.dddd, ref=EchoDictType.AAA, beanClass= EchoDictType.class, dictType = EchoDictType.NATION) RemoteData<Long, Org>";
//        String comment = "@Echo(api=\"xxxx\", ref=\"bbbbb\", beanClass=1, dictType = EchoDictType.NATION) RemoteData<Long, Org>";
        EchoDict echoDict = CommentUtils.getEchoDict("123", "", comment);
        System.out.println(echoDict);
    }
}
