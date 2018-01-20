package com.github.zuihou.commons.exception.core;


import com.github.zuihou.exception.code.BaseExceptionCode;

/**
 * 业务系统错误码
 * @author zuihou
 * @createTime 2017-12-13 16:22
 */
public enum ExceptionCode implements BaseExceptionCode {

    //系统相关 start
    SYSTEM_BUSY(-1, "系统繁忙，请稍候再试"),
    SYSTEM_TIMEOUT(-2, "系统超时，请稍候再试"),
    //系统相关 end

    //DB相关 start
    DB_REMOVE_ERROR(10000, "无法软删除"),
    //DB相关 end

    //jwt token 相关 start
    //过期
    JWT_TOKEN_EXPIRED(40001, "token超时，请检查 token 的有效期"),
    //签名错误
    JWT_SIGNATURE(40002, "不合法的token，请认真比对 token 的签名"),
    //token 为空
    JWT_ILLEGAL_ARGUMENT(40003, "缺少token参数"),
    JWT_GEN_TOKEN_FAIL(40004, "生成token失败"),
    JWT_PARSER_TOKEN_FAIL(40005, "解析token失败"),
    JWT_APPID_SECRET_INVALID(40006, "获取 access_token 时 AppSecret 错误，或者 AppId 无效！"),
    JWT_APPID_ENABLED(40007, "AppId 已经被禁用！请联系管理员"),
    //jwt token 相关 end

    //权限相关 start
    CLIENT_FORBIDDEN(50001, "客户端被禁止!"),
    USER_NAME_PWD_ERROR(50002, "帐号或者密码错误"),
    USER_NAME_EXIST(50003, "帐号已存在"),
    USER_NOT_EXIST(50004, "帐号不存在"),
    //权限相关 end

    //资源管理相关 start
    MENU_GROUP_NULL(51000, "菜单组不能为空"),
    MENU_GROUP_CODE_EMPTY(51001, "菜单组编码不能为空"),
    MENU_GROUP_EXIST(51002, "菜单组CODE已存在"),
    MENU_GROUP_TOO_MUCH(51003, "菜单组最多只能创建20个"),
    MENU_GROUP_ID_NULL(51004, "菜单组id不能为空"),
    MENU_GROUP_EXIST_CHILD(51005, "该菜单组存在子菜，无法删除"),

    MENU_NULL(51100, "菜单不能为空"),
    MENU_CODE_EMPTY(51101, "菜单编码[code]不能为空"),
    MENU_TYPE_EMPTY(51102, "菜单类型[type]不能为空"),
    MENU_GROUP_NOT_EXIST(51103, "菜单组编码[code]不存在"),
    MENU_TOO_MUCH(51104, "每组菜单最多只能创建500个"),
    MENU_ID_NULL(51105, "菜单[id]不能为空"),
    MENU_EXIST_CHILD(51106, "该菜单存在子菜单或子资源，无法删除"),

    RESOURCES_NULL(51200, "资源信息不能为空"),
    RESOURCES_CODE_EMPTY(51201, "资源编码[code]不能为空"),
    RESOURCES_MENU_ID_NULL(51202, "资源菜单id[menuId]不能为空"),
    RESOURCES_TYPE_NULL(51203, "资源类型[type]不能为空"),
    RESOURCES_EXIST(51204, "菜单/资源编码[code]已存在"),
    RESOURCES_ID_NULL(51205, "资源id[id]不能为空"),
    MENU_NOT_EXIST(51206, "资源所属菜单不存在"),
    //资源管理相关 end

    //角色管理相关 start
    ROLE_NULL(52000, "角色信息不能为空"),
    ROLE_CODE_EMPTY(52001, "角色编码[code]不能为空"),
    ROLE_CODE_EXIST(52002, "角色编码[code]已存在"),
    ROLE_ID_NULL(52003, "角色[id]不能为空"),
    //角色管理相关 end

    //帐号管理相关 start
    USER_EXIST(53000, "登录名username已存在"),
    USER_ID_NULL(53001, "用户id不能为空"),
    USER_NAME_EMPTY(53002, "登录名username不能为空"),
    USER_PWD_EMPTY(53003, "密码不能为空"),
    USER_PWD_NOT_EQUALS(53004, "密码与确认密码不一致"),
    USER_NULL(53005, "用户信息不能为空"),

    DEPART_EXIST(53501, "部门code已存在"),
    DEPART_NULL(53502, "部门信息不能为空"),
    DEPART_ID_NULL(53503, "部门id不能为空"),
    DEPART_CODE_EMPTY(53504, "部门code不能为空"),
    DEPART_CODE_EXIST(53505, "部门code已经存在"),
    DEPART_PARENT_NOT_EXIST(53506, "父部门不存在"),
    //帐号管理相关 end

    //数据字典相关 start
    DICTIONARY_TYPE_NULL(54000, "数据字典类型不能为空"),
    DICTIONARY_TYPE_ID_NULL(54001, "数据字典类型id不能为空"),
    DICTIONARY_TYPE_CODE_EMPTY(54002, "数据字典类型code不能为空"),
    DICTIONARY_TYPE_CODE_EXIST(54003, "数据字典类型code不能重复"),
    DICTIONARY_TYPE_EXIST(54004, "数据字典类型不存在"),

    DICTIONARY_NULL(54500, "数据字典不能为空"),
    DICTIONARY_ID_NULL(54501, "数据字典id不能为空"),
    DICTIONARY_CODE_EMPTY(54502, "数据字典code不能为空"),
    DICTIONARY_CODE_EXIST(54503, "数据字典code不能重复"),
    //数据字典相关 end
    ;

    private  int code;
    private  String msg;

    ExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
