package top.tangyh.lamp.generator.rules.enumeration;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zuihou
 * @date 2022/3/22 15:49
 */
@Data
@EqualsAndHashCode(of = "enumName")
public class EnumType {
    private String enumStr;
    private String enumName;
    private String swaggerComment;
    private String enumPackage;
    private String keyValue;
    private List<EnumTypeKeyValue> kvList;


}
