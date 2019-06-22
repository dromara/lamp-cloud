package com.github.zuihou.common.constant;

import java.util.Arrays;

import com.hengyunsoft.commons.exception.core.ExceptionCode;
import com.hengyunsoft.utils.BizAssert;

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
public abstract class AttachmentType {

    /**
     * 后端代码不需要使用该 业务类型时， 无需使用常量，直接在ALL_TYPES数组中写字符串即可。
     * 命名规则:
     * 业务表名_字段名
     */
    public static final String[] ALL_TYPES = {
            Authority.APPLICATION_LOGO_URL,
            Authority.APPLICATION_TITLE_ICON,
            Mt.VERSION_ER_WEI_MA,
    };

    private AttachmentType() {
    }

    public static boolean assertType(String type) {
        boolean flag = Arrays.asList(ALL_TYPES).contains(type);
        BizAssert.assertTrue(ExceptionCode.BAD_GATEWAY, flag);
        return flag;
    }

    /**
     * 权限管理系统中有关的的附件类型定义
     *
     * @author 潘定遥
     */
    interface Authority {
        /**
         * 权限管理系统中的应用表中的logo
         */
        String APPLICATION_LOGO_URL = "auth-application-logo";
        /**
         * 权限管理系统中的应用表中的应用标题图标
         */
        String APPLICATION_TITLE_ICON = "auth-application-titleIcon";
    }

    /**
     * 云盘 业务类型定义
     */
    interface File {

    }

    /**
     * 移动终端  业务类型定义
     */
    interface Mt {
        /**
         * 移动终端发布后二维码
         */
        String VERSION_ER_WEI_MA = "version-er-wei-ma";
    }


    /**
     * 消息系统 业务类型定义
     */
    interface Msgs {

    }

    /**
     * 共享交换 业务类型定义
     */
    interface Exchange {

    }
}
