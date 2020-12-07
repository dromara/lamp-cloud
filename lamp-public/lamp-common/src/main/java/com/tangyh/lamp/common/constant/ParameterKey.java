package com.tangyh.lamp.common.constant;

/**
 * 全局参数表（c_parameter）的key
 *
 * @author zuihou
 * @date 2020年04月02日22:37:05
 */
public interface ParameterKey {

    String LOGIN_POLICY = LoginPolicy.class.getSimpleName();

    enum LoginPolicy {
        /**
         * 一个用户在一个应用只能登录一次（如一个用户只能在一个APP上登录，也只能在一个PC端登录，但能同时登录PC和APP）
         * 后面的用户T掉前面的用户
         */
        ONLY_ONE_CLIENT,

        /**
         * 用户可以任意登录： token -> userid
         * <p>
         * 不用T人，所有人登录都行
         */
        MANY,
        /**
         * 一个用户只能登录一次， 后面的用户T掉前面的用户LOGIN_POLICY
         */
        ONLY_ONE,
        ;

        public boolean eq(String val) {
            return this.name().equalsIgnoreCase(val);
        }
    }
}
