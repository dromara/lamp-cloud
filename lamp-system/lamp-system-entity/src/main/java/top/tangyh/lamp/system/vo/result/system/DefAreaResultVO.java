package top.tangyh.lamp.system.vo.result.system;


import cn.hutool.core.map.MapUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.annotation.echo.Echo;
import top.tangyh.basic.base.entity.TreeEntity;
import top.tangyh.basic.interfaces.echo.EchoVO;
import top.tangyh.lamp.model.constant.EchoApi;
import top.tangyh.lamp.model.constant.EchoDictType;

import java.io.Serializable;
import java.util.Map;


/**
 * <p>
 * 实体类
 * 地区表
 * </p>
 *
 * @author zuihou
 * @since 2021-10-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "地区表")
public class DefAreaResultVO extends TreeEntity<DefAreaResultVO, Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();
    @Schema(description = "名称")
    protected String name;
    @Schema(description = "父ID")
    protected Long parentId;
    @Schema(description = "排序号")
    protected Integer sortValue;
    @Schema(description = "主键")
    private Long id;
    /**
     * 编码
     */
    @Schema(description = "编码")
    
    private String code;
    /**
     * 全名
     */
    @Schema(description = "全名")
    
    private String fullName;
    /**
     * 经度
     */
    @Schema(description = "经度")
    
    private String longitude;
    /**
     * 维度
     */
    @Schema(description = "维度")
    
    private String latitude;
    /**
     * 行政级别;[10-国家 20-省份/直辖市 30-地市 40-区县 50-乡镇]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.AREA_LEVEL)
     */
    @Schema(description = "行政级别")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.AREA_LEVEL)
    
    private String level;
    /**
     * 数据来源;[10-爬取 20-新增]	@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.AREA_SOURCE)
     */
    @Schema(description = "数据来源")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.AREA_SOURCE)
    
    private String source;
    /**
     * 状态
     */
    @Schema(description = "状态")
    
    private Boolean state;
    /**
     * 树层级
     */
    @Schema(description = "树层级")
    
    private Integer treeGrade;
    /**
     * 路径
     */
    @Schema(description = "路径")
    
    private String treePath;
}
