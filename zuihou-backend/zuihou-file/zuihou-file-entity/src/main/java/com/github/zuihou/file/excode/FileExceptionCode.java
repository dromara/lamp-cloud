package com.github.zuihou.file.excode;


import com.github.zuihou.exception.code.BaseExceptionCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * * 文件服务（gxqpt-file 项目）相关的异常代码
 * * 60000~64999, 每个子模块加500， 每个异常代码+1
 *
 * @author tangyh
 * @date 2019/05/08
 */
@Getter
@AllArgsConstructor
public enum FileExceptionCode implements BaseExceptionCode {

    /**
     * 分享文件已过期
     */
    SHARE_EXPIRE(60000, "分享文件已过期"),
    SHARE_PWD_ERROR(60001, "提取码错误"),
    SHARE_PWD_NULL(60002, "请输入密码"),

    ;

    private int code;
    private String msg;
}
