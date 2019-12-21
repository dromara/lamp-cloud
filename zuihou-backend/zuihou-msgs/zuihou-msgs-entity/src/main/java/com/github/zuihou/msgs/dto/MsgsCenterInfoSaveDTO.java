package com.github.zuihou.msgs.dto;

import com.google.common.collect.Sets;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 实体类
 * 消息中心表
 * </p>
 *
 * @author zuihou
 * @since 2019-12-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "MsgsCenterInfoSaveDTO", description = "消息中心")
public class MsgsCenterInfoSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "消息内容")
    @NotNull(message = "消息内容不能为空")
    private MsgsCenterInfoDTO msgsCenterInfoDTO;

    /**
     * 接收人集合
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "接收人id集合")
    private Set<Long> userIdList;

    /**
     * 角色编码
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "角色编码")
    private Set<String> roleCodeList;


    public static MsgsCenterInfoSaveDTO buildPersonal(MsgsCenterInfoDTO msgsCenterInfo, Long userId) {
        return MsgsCenterInfoSaveDTO.builder()
                .msgsCenterInfoDTO(msgsCenterInfo)
                .userIdList(Sets.newHashSet(userId)).build();
    }

    public static MsgsCenterInfoSaveDTO buildPersonal(MsgsCenterInfoDTO msgsCenterInfo, Set<Long> userIdList) {
        return MsgsCenterInfoSaveDTO.builder()
                .msgsCenterInfoDTO(msgsCenterInfo)
                .userIdList(userIdList).build();
    }

    public static MsgsCenterInfoSaveDTO buildRole(MsgsCenterInfoDTO msgsCenterInfo, String roleCode) {
        return MsgsCenterInfoSaveDTO.builder()
                .msgsCenterInfoDTO(msgsCenterInfo)
                .roleCodeList(Sets.newHashSet(roleCode)).build();
    }

    public static MsgsCenterInfoSaveDTO buildRole(MsgsCenterInfoDTO msgsCenterInfo, Set<String> roleCodeList) {
        return MsgsCenterInfoSaveDTO.builder()
                .msgsCenterInfoDTO(msgsCenterInfo)
                .roleCodeList(roleCodeList).build();
    }
}
