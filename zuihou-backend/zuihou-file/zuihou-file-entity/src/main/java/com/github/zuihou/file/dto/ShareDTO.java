package com.github.zuihou.file.dto;

import java.io.Serializable;

import com.github.zuihou.file.entity.Share;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实体类
 * 分享目录表
 * </p>
 *
 * @author zuihou
 * @since 2019-06-24
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShareDTO", description = "分享目录表")
public class ShareDTO extends Share implements Serializable {

    /**
     * 在DTO中新增并自定义字段，需要覆盖验证的字段，请新建DTO。Entity中的验证规则可以自行修改，但下次生成代码时，记得同步代码！！
     */
    private static final long serialVersionUID = 1L;

    public static ShareDTO build() {
        return new ShareDTO();
    }

}
