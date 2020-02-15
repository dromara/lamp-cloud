package com.github.zuihou;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试DTO
 *
 * @author zuihou
 * @date 2019/07/25
 */

@Data
@ToString
public class D2 {
    private String date;
    private String d2;

    @Test
    public void testId1() {
        DefaultIdentifierGenerator defaultIdentifierGenerator = new DefaultIdentifierGenerator(0, 0);
        Set<Long> ids = new HashSet<>();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            ids.add(defaultIdentifierGenerator.nextId(null));
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);
        System.out.println(ids.size());
    }

    @Test
    public void testId2() {
        Snowflake snowflake = IdUtil.getSnowflake(0, 0);
        Set<Long> ids = new HashSet<>();
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            ids.add(snowflake.nextId());
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
        System.out.println(ids.size());
    }

}
