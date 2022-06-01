package top.tangyh.lamp.security.constant;

/**
 * 系统默认角色
 *
 * @author zuihou
 * @date 2020年03月29日21:16:22
 */
public final class RoleConstant {
    private RoleConstant() {
    }

    public static final String SUPER_ADMIN = "SUPER_ADMIN";

    public static final String HAS_ROLE_ADMIN = "hasAnyRole('" + SUPER_ADMIN + "')";

    public static final String USER = "user";

    public static final String HAS_ROLE_USER = "hasAnyRole('" + USER + "')";

    public static final String TEST = "test";

    public static final String HAS_ROLE_TEST = "hasAnyRole('" + TEST + "')";

}
