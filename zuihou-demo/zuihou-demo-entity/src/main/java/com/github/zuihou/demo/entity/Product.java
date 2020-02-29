package com.github.zuihou.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 商品
 * </p>
 *
 * @author zuihou
 * @since 2019-08-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("m_product")
@ApiModel(value = "Product", description = "商品")
public class Product extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @Length(max = 255, message = "名称长度不能超过255")
    @TableField("name")
    private String name;

    /**
     * 库存
     */
    @ApiModelProperty(value = "库存")
    @TableField("stock")
    private Integer stock;


    @Builder
    public Product(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                   String name, Integer stock) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.name = name;
        this.stock = stock;
    }

}
