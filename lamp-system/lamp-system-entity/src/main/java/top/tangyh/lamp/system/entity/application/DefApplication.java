package top.tangyh.lamp.system.entity.application;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.Entity;

import java.time.LocalDateTime;

import static top.tangyh.lamp.model.constant.Condition.LIKE;

/**
 * <p>
 * 实体类
 * 应用
 * </p>
 *
 * @author zuihou
 * @since 2021-09-15
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("def_application")
@AllArgsConstructor
public class DefApplication extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 应用标识
     */
    @TableField(value = "app_key", condition = LIKE)
    private String appKey;

    /**
     * 应用秘钥
     */
    @TableField(value = "app_secret", condition = LIKE)
    private String appSecret;

    /**
     * 应用名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 版本
     */
    @TableField(value = "version", condition = LIKE)
    private String version;

    /**
     * 应用类型;[10-自建应用 20-第三方应用]
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.APPLICATION_TYPE)
     */
    @TableField(value = "type", condition = LIKE)
    private String type;

    /**
     * 简介
     */
    @TableField(value = "introduce", condition = LIKE)
    private String introduce;

    /**
     * 备注
     */
    @TableField(value = "remark", condition = LIKE)
    private String remark;

    /**
     * 应用地址
     */
    @TableField(value = "url", condition = LIKE)
    private String url;

    /**
     * 是否可见;0-否 1-是
     */
    @TableField(value = "is_visible")
    private Boolean isVisible;

    /**
     * 是否公共应用;0-否 1-是
     */
    @TableField(value = "is_general")
    private Boolean isGeneral;

    /**
     * 排序
     */
    @TableField(value = "sort_value")
    private Integer sortValue;


    @Builder
    public DefApplication(Long id, Long createdBy, LocalDateTime createdTime, Long updatedBy, LocalDateTime updatedTime,
                          String appKey, String appSecret, String name, String version, String type, Boolean isGeneral,
                          String introduce, String remark, String url, Boolean isVisible, Integer sortValue) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.name = name;
        this.version = version;
        this.type = type;
        this.introduce = introduce;
        this.remark = remark;
        this.url = url;
        this.isVisible = isVisible;
        this.isGeneral = isGeneral;
        this.sortValue = sortValue;
    }

}
