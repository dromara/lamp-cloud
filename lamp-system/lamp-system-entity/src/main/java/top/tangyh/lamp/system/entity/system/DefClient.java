package top.tangyh.lamp.system.entity.system;

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
 * 客户端
 * </p>
 *
 * @author zuihou
 * @since 2021-10-13
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("def_client")
@AllArgsConstructor
public class DefClient extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    @TableField(value = "client_id", condition = LIKE)
    private String clientId;

    /**
     * 客户端密码
     */
    @TableField(value = "client_secret", condition = LIKE)
    private String clientSecret;

    /**
     * 客户端名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 类型;[10-WEB网站;15-移动端应用;20-手机H5网页;25-内部服务; 30-第三方应用]	@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.CLIENT_TYPE)
     */
    @TableField(value = "type", condition = LIKE)
    private String type;

    /**
     * 备注
     */
    @TableField(value = "remarks", condition = LIKE)
    private String remarks;

    /**
     * 状态
     */
    @TableField(value = "state")
    private Boolean state;


    @Builder
    public DefClient(Long id, Long createdBy, LocalDateTime createdTime, Long updatedBy, LocalDateTime updatedTime,
                     String clientId, String clientSecret, String name, String type, String remarks, Boolean state) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.name = name;
        this.type = type;
        this.remarks = remarks;
        this.state = state;
    }

}
