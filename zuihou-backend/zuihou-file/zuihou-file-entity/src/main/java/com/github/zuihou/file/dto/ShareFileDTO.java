package com.github.zuihou.file.dto;

import java.io.Serializable;

import com.github.zuihou.file.entity.ShareFile;
import com.github.zuihou.file.enumeration.DataType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实体类
 * 分享文件表
 * </p>
 *
 * @author tangyh
 * @since 2019-05-20
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShareFileDTO", description = "分享文件表")
public class ShareFileDTO extends ShareFile implements Serializable {

    /**
     * 在DTO中新增并自定义字段，需要覆盖验证的字段，请新建DTO。Entity中的验证规则可以自行修改，但下次生成代码时，记得同步代码！！
     */
    private static final long serialVersionUID = 1L;
    /**
     * 数据类型
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "数据类型")
    private DataType dataType;
    /**
     * 文件名
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "文件名")
    private String submittedFileName;
    /**
     * 大小
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "大小")
    private String size;
    /**
     * 链接
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "链接")
    private String url;
    /**
     * 图标
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    public static ShareFileDTO build() {
        return new ShareFileDTO();
    }
}
