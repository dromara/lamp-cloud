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
 * 资源接口
 * </p>
 *
 * @author zuihou
 * @since 2021-09-17
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, of = {"springApplicationName", "uri", "requestMethod"})
@Accessors(chain = true)
@TableName("def_resource_api")
@AllArgsConstructor
public class DefResourceApi extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源ID
     */
    @TableField(value = "resource_id")
    private Long resourceId;

    /**
     * 控制器类名
     */
    @TableField(value = "controller", condition = LIKE)
    private String controller;

    /**
     * 所属服务;取配置文件中 spring.application.name
     */
    @TableField(value = "spring_application_name", condition = LIKE)
    private String springApplicationName;

    /**
     * 请求类型
     */
    @TableField(value = "request_method", condition = LIKE)
    private String requestMethod;

    /**
     * 接口名;接口上的注释
     */
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 接口路径;lamp-cloud版：uri需要拼接上gateway中路径前缀
     * lamp-boot版: uri需要不需要拼接前缀
     */
    @TableField(value = "uri", condition = LIKE)
    private String uri;

    @TableField(value = "is_input")
    private Boolean isInput;


    @Builder
    public DefResourceApi(Long id, Long createdBy, LocalDateTime createdTime, Long updatedBy, LocalDateTime updatedTime,
                          Long resourceId, String controller, String springApplicationName,
                          String requestMethod, String name, String uri, Boolean isInput) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.resourceId = resourceId;
        this.controller = controller;
        this.springApplicationName = springApplicationName;
        this.requestMethod = requestMethod;
        this.name = name;
        this.uri = uri;
        this.isInput = isInput;
    }

}
