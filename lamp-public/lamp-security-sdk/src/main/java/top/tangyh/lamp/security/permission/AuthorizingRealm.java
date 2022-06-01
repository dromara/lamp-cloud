package top.tangyh.lamp.security.permission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 参考了Shiro的源码
 *
 * @author zuihou
 * @date 2020/11/27 10:54 下午
 */
public class AuthorizingRealm {

    /**
     * 验证是否拥有指定权限
     *
     * @param perms         拥有的权限集
     * @param permission    待验证权限
     * @param caseSensitive 是否区分大小写
     * @return 是否拥有权限
     */
    public static boolean isPermitted(Collection<WildcardPermission> perms, String permission, boolean caseSensitive) {
        return isPermitted(perms, new WildcardPermission(permission, caseSensitive));
    }

    /**
     * 验证是否拥有指定权限
     *
     * @param perms      拥有的权限集
     * @param permission 待验证权限
     * @return 是否拥有权限
     */
    private static boolean isPermitted(Collection<WildcardPermission> perms, WildcardPermission permission) {
        if (perms == null || perms.isEmpty()) {
            return false;
        }
        return perms.parallelStream().anyMatch(perm -> perm.implies(permission));
    }


    /**
     * 验证是否拥有所有指定权限
     *
     * @param allPermissions 拥有的权限集
     * @param permissions    待验证权限
     * @param caseSensitive  是否区分大小写
     * @return 是否拥有权限
     */
    public static boolean hasAllPermission(Collection<String> allPermissions, String[] permissions, boolean caseSensitive) {
        if (permissions != null && permissions.length > 0) {
            Collection<WildcardPermission> allPerms = allPermissions.parallelStream().map(perm -> new WildcardPermission(perm, caseSensitive)).collect(Collectors.toList());
            return Arrays.stream(permissions).allMatch(perm -> isPermitted(allPerms, perm, caseSensitive));
        }
        return false;
    }

    /**
     * 验证是否拥有任意一个指定权限
     *
     * @param allPermissions 拥有的权限集
     * @param permissions    待验证权限
     * @param caseSensitive  是否区分大小写
     * @return 是否拥有权限
     */
    public static boolean hasAnyPermission(Collection<String> allPermissions, String[] permissions, boolean caseSensitive) {
        if (permissions != null && permissions.length > 0) {
            Collection<WildcardPermission> allPerms = allPermissions.parallelStream().map(perm -> new WildcardPermission(perm, caseSensitive)).collect(Collectors.toList());
            return Arrays.stream(permissions).anyMatch(perm -> isPermitted(allPerms, perm, caseSensitive));
        }
        return false;
    }


    /**
     * 验证是否不包含所有的指定权限
     *
     * @param allPermissions 拥有的权限集
     * @param permissions    待验证权限
     * @param caseSensitive  是否区分大小写
     * @return 是否拥有权限
     */
    public static boolean hasNoPermission(Collection<String> allPermissions, String[] permissions, boolean caseSensitive) {
        if (permissions != null && permissions.length > 0) {
            Collection<WildcardPermission> allPerms = allPermissions.parallelStream().map(perm -> new WildcardPermission(perm, caseSensitive)).collect(Collectors.toList());
            return Arrays.stream(permissions).noneMatch(perm -> isPermitted(allPerms, perm, caseSensitive));
        }
        return false;
    }

    public static void main(String[] args) {
        Collection<String> perms = new ArrayList<>();
        perms.add("authority:user:*");
        perms.add("authority:org:add");
        perms.add("authority:*:view");
        perms.add("authority:station:add,view");

        String[] noTrue = {"authority:org:delete", "authority:station:delete"};
        String[] noFalse = {"authority:user:add", "authority:aaa:vvv"};
        String[] andTrue = {"authority:org:add", "authority:station:add", "authority:station:view"};
        String[] andFalse = {"authority:org:add", "authority:org:edit"};
        String[] anyTrue = {"authority:user:add", "authority:org:edit"};
        String[] anyFalse = {"authority:org:delete", "authority:station:delete"};

        System.out.println(hasNoPermission(perms, noTrue, false));
        System.out.println(hasNoPermission(perms, noFalse, false));
        System.out.println(hasAllPermission(perms, noTrue, false));
        System.out.println(hasAllPermission(perms, noFalse, false));
        System.out.println(hasAllPermission(perms, andTrue, false));
        System.out.println(hasAllPermission(perms, andFalse, false));
        System.out.println(hasAnyPermission(perms, anyTrue, false));
        System.out.println(hasAnyPermission(perms, anyFalse, false));
    }
}
