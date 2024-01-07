package top.tangyh.lamp.model.constant;

/**
 * Echo注解中dictType的常量
 * <p>
 * 存放系统中常用的类型
 * <p>
 * 本类中的 @lamp.generator auto insert 请勿删除
 *
 * @author zuihou
 * @date 2019/07/26
 */
public interface EchoDictType {
    // @lamp.generator auto insert EchoDictType

    /**
     * 全局字典类型
     */
    interface Global {
        // @lamp.generator auto insert Global

        /**
         * 行政级别
         * [10-国家 20-省份/直辖市 30-地市 40-区县 50-乡镇]
         */
        String AREA_LEVEL = "GLOBAL_AREA_LEVEL";
        /**
         * 民族
         * [01-汉族 02-...]
         */
        String NATION = "GLOBAL_NATION";
        /**
         * 学历
         * [01-小学 02-中学 03-高中 04-专科 05-本科 06-硕士 07-博士 08-博士后 99-其他]
         */
        String EDUCATION = "GLOBAL_EDUCATION";
        /**
         * 性别
         */
        String SEX = "GLOBAL_SEX";
        /**
         * 激活状态
         * [10-未激活 20-已激活]
         */
        String ACTIVE_STATUS = "GLOBAL_ACTIVE_STATUS";
        /**
         * 数据类型
         * [10-系统值 20-业务值]
         */
        String DATA_TYPE = "GLOBAL_DATA_TYPE";
    }

    /**
     * 基础服务
     */
    interface Base {
        // @lamp.generator auto insert Base
        String MSG_INTERFACE_LOGGING_STATUS = "MSG_INTERFACE_LOGGING_STATUS";
        String INTERFACE_EXEC_MODE = "INTERFACE_EXEC_MODE";
        String MSG_TEMPLATE_TYPE = "MSG_TEMPLATE_TYPE";
        String NOTICE_TARGET = "NOTICE_TARGET";
        String NOTICE_REMIND_MODE = "NOTICE_REMIND_MODE";

        /**
         * 职位状态
         * [10-在职 20-离职]
         */
        String POSITION_STATUS = "BASE_POSITION_STATUS";

        /**
         * 机构类型
         * [10-单位 20-部门]
         */
        String ORG_TYPE = "BASE_ORG_TYPE";

        /**
         * 角色类别
         * [10-功能角色 20-桌面角色 30-数据角色]
         */
        String ROLE_CATEGORY = "BASE_ROLE_CATEGORY";
    }

    /**
     * 租户服务
     */
    interface System {
        // @lamp.generator auto insert System

        /**
         * 数据范围 [01-全部 02-本单位及子级 03-本单位 04-本部门 05-本部门及子级 06-个人 07-自定义]
         */
        String RESOURCE_DATA_SCOPE = "TENANT_RESOURCE_DATA_SCOPE";
        /**
         * 资源类型 [10-应用 20-菜单 30-视图 40-按钮 50-字段 06-数据]
         */
        String RESOURCE_TYPE = "TENANT_RESOURCE_TYPE";
        /**
         * 打开方式 [01-组件 02-内链 03-外链]
         */
        String RESOURCE_OPEN_WITH = "TENANT_RESOURCE_OPEN_WITH";

        /**
         * 字典分类 [10-系统字典 20-业务字典]
         */
        String DICT_CLASSIFY = "TENANT_DICT_CLASSIFY";

        /**
         * 应用类型 [10-自建应用 20-第三方应用]
         */
        String APPLICATION_TYPE = "TENANT_APPLICATION_TYPE";
        /**
         * 授权类型 [10-应用授权 20-应用续期 30-取消授权]
         */
        String APPLICATION_GRANT_TYPE = "TENANT_APPLICATION_GRANT_TYPE";
        /**
         * 参数类型 [10-系统参数 20-业务参数]
         */
        String PARAMETER_TYPE = "TENANT_PARAMETER_TYPE";
        /**
         * 地区来源
         * [10-爬取 20-新增]
         */
        String AREA_SOURCE = "TENANT_AREA_SOURCE";
        /**
         * 客户端类型
         * [10-WEB网站;15-移动端应用;20-手机H5网页;25-内部服务; 30-第三方应用]
         */
        String CLIENT_TYPE = "TENANT_CLIENT_TYPE";
        /**
         * 租户审批状态
         * [05-正常 10-待初始化 15-已撤回 20-待审核 25-已拒绝 30-已同意]
         */
        String TENANT_STATUS = "TENANT_TENANT_STATUS";
        /**
         * 登录状态
         * [01-登录成功 02-验证码错误 03-密码错误 04-账号锁定 05-切换租户 06-短信验证码错误]
         */
        String LOGIN_STATUS = "SYSTEM_LOGIN_STATUS";


    }

    /**
     * 认证服务
     */
    interface Oauth {
        // @lamp.generator auto insert Oauth

    }

    /**
     * 文件服务
     */
    interface File {
        // @lamp.generator auto insert File

    }

    /**
     * 消息服务
     */
    interface Msg {
        // @lamp.generator auto insert Msg

    }

    /**
     * 网关服务
     */
    interface Gateway {
        // @lamp.generator auto insert Gateway

    }

    // 新增内部 Xxx 接口后，请在PackageUtils的static代码块中新增 putDictType(EchoDictType.Xxx.class)， 否则代码生成器会重复生成
}
