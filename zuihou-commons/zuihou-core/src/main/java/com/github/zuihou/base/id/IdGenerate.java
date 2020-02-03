package com.github.zuihou.base.id;


import java.io.Serializable;

/**
 * 主键（id）生成器，用于生成唯一id号
 * 1、注意：在写主键生成器时，请考虑主键不会重复。
 * 2、尤其需要考虑在短时间内大量调用生成器，也保证不要出现重复
 * 3、实现类必须是线程安全的，因为会应用在多线程环境中去
 *
 * @author zuihou
 * @since
 */
@FunctionalInterface
@Deprecated
public interface IdGenerate<T extends Serializable> {
    /**
     * id 生成器
     *
     * @return
     */
    T generate();
}
