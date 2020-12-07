package com.tangyh.lamp.common.constant;

import com.tangyh.basic.exception.code.ExceptionCode;
import com.tangyh.basic.utils.BizAssert;

import java.util.Arrays;

/**
 * 附件表中事先约定的业务类型。
 * <p>
 * 预定方式为：  定义一个系统级别的接口   此接口内包含这个系统内的所有表中需要的附件类型
 * key定时方式为entity_field
 * value定义方式为： 系统简称-entity-field
 *
 * @author zuihou
 * @date 2018/12/11
 */
public final class AttachmentType {

    /**
     * 后端代码不需要使用该 业务类型时， 无需使用常量，直接在ALL_TYPES数组中写字符串即可。
     * 命名规则:
     * 业务表名_字段名
     */
    public static final String[] ALL_TYPES = {
            Authority.APPLICATION_LOGO_URL,
            Authority.APPLICATION_TITLE_ICON,
    };

    private AttachmentType() {
    }

    public static boolean assertType(String type) {
        boolean flag = Arrays.asList(ALL_TYPES).contains(type);
        BizAssert.isTrue(flag, ExceptionCode.BAD_GATEWAY);
        return flag;
    }

    /**
     * 权限管理系统中有关的的附件类型定义
     *
     * @author zuihou
     */
    interface Authority {
        /**
         * 权限管理系统中的应用表中的logo
         */
        String APPLICATION_LOGO_URL = "auth_application_logo";
        /**
         * 权限管理系统中的应用表中的应用标题图标
         */
        String APPLICATION_TITLE_ICON = "auth_application_titleIcon";
    }

    /**
     * 文件 业务类型定义
     */
    interface File {

    }


    /**
     * 消息系统 业务类型定义
     */
    interface Msg {

    }


}
