package top.tangyh.lamp.datascope.model;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.tangyh.basic.utils.ArgumentAssert;

import java.util.Collections;
import java.util.List;

/**
 * @author zuihou
 * @date 2022/1/9 21:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataFieldProperty {
    public static final List<DataFieldProperty> EMPTY_INSTANCE = Collections.emptyList();
    String alias;

    String field;

    private List<Long> values;

    public DataFieldProperty(String alias) {
        this.alias = alias;
    }

    public String getAliasDotField() {
        ArgumentAssert.notEmpty(this.field, "请为数据权限配置待过滤数据的字段名");
        return StrUtil.isEmpty(alias) ? this.field : this.alias + "." + this.field;
    }


}
