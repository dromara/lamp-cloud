package com.github.zuihou.mybatis.typehandler;


import org.apache.ibatis.type.Alias;

/**
 * 仅仅用于like查询
 *
 * @author Administrator
 */
@Alias("rightLike")
public class RightLikeTypeHandler extends BaseLikeTypeHandler {
    public RightLikeTypeHandler() {
        super(false, true);
    }
}

