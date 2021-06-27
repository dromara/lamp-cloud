package com.tangyh.lamp.msg.dto;

import com.google.common.collect.Sets;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.Valid;
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
@ApiModel(value = "MsgSaveDTO", description = "消息中心")
public class MsgSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "消息内容")
    @NotNull(message = "消息内容不能为空")
    @Valid
    private MsgDTO msgDTO;

    /**
     * 接收人集合
     *
     */
    @ApiModelProperty(value = "接收人id集合")
    private Set<Long> userIdList;

    /**
     * 角色编码
     *
     */
    @ApiModelProperty(value = "角色编码")
    private Set<String> roleCodeList;


    public static MsgSaveDTO buildPersonal(MsgDTO msgDTO, Long userId) {
        return MsgSaveDTO.builder()
                .msgDTO(msgDTO)
                .userIdList(Sets.newHashSet(userId)).build();
    }

    public static MsgSaveDTO buildPersonal(MsgDTO msgDTO, Set<Long> userIdList) {
        return MsgSaveDTO.builder()
                .msgDTO(msgDTO)
                .userIdList(userIdList).build();
    }

    public static MsgSaveDTO buildRole(MsgDTO msgDTO, String roleCode) {
        return MsgSaveDTO.builder()
                .msgDTO(msgDTO)
                .roleCodeList(Sets.newHashSet(roleCode)).build();
    }

    public static MsgSaveDTO buildRole(MsgDTO msgDTO, Set<String> roleCodeList) {
        return MsgSaveDTO.builder()
                .msgDTO(msgDTO)
                .roleCodeList(roleCodeList).build();
    }
}
