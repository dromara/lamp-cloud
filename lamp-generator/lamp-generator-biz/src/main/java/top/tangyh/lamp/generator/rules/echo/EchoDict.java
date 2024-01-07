package top.tangyh.lamp.generator.rules.echo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/4/21 9:40 PM
 * @create [2022/4/21 9:40 PM ] [tangyh] [初始创建]
 */
@Data
@EqualsAndHashCode(of = "key")
public class EchoDict {
    private String str;
    private String key;
    private String value;
    private List<EchoDict> itemList;

    public static EchoDict of(String key, String value) {
        EchoDict dict = new EchoDict();
        dict.setKey(key);
        dict.setValue(value);
        return dict;
    }

    public static EchoDict of(String str, String key, String value) {
        EchoDict dict = new EchoDict();
        dict.setStr(str);
        dict.setKey(key);
        dict.setValue(value);
        return dict;
    }
}
