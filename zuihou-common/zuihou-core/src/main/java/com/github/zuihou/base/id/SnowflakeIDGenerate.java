package com.github.zuihou.base.id;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1bit 		+ 41bit 		+ 17bit 		+ 5bit
 * |			    |				|				|
 * |				|				|				|
 * 符合位     	        时间戳（毫秒）		     序列号			      机器码
 * 第1bit固定是0  符号位不动 。
 * 第2bit到第42bit使用时间蹉，精确到毫秒  41bit。 使用年限是69年
 * 第43bit到第59bit使用自增的序列号       17bit  可用序列号最大131071个，说明一毫秒我们可以生成131071个不同的序列号。
 * 第60bit到第64bit使用机器码	5bit   可以使系统可以分布式，最大分布式数量是32台机子。
 *
 * @author zuihou
 */
public class SnowflakeIDGenerate implements IdGenerate<Long> {

    /**
     * 最大17bit的序列号是131071
     */
    private final static int MAX_ORDER_NO = 131071;
    /**
     * 时间戳的掩码
     */
    private final static long TIME_CODE = Long.MAX_VALUE >>> 22;
    /**
     * 2015-01-01 00:00:00
     * 1420041600210
     * 因为我们的生成器可以使用69年，而我们想在这些时间里面，生成出来的id是逐渐自增的。
     * 所以我这里指定了从什么时候开始使用id生成器。
     */
    private final static long START_TIME = 1420041600210L;

    /**
     * 用于生成序列号
     */
    private AtomicInteger orderNo;
    /**
     * 机器码 （0-31）
     */
    private final long MACHINE_CODE;


    public SnowflakeIDGenerate(final long machineCode) {
        if (machineCode < 0 || machineCode > 31) {
            throw new IllegalArgumentException("请注意，1、机器码在多台机器或应用间是不允许重复的！2、机器码取值仅仅在0~31之间");
        }
        this.MACHINE_CODE = machineCode;
        orderNo = new AtomicInteger(0);
    }

    @Override
    public Long generate() {
        /**
         * 时间戳
         *  1、先去掉高23bit，保留当前时间的低41bit
         *  2、将0到41bit移到高位去
         */
        long currentTimeMillis = TIME_CODE & (System.currentTimeMillis() - START_TIME) << 22;

        /**
         * 序列号自增1和获取
         */
        int orderNo = this.orderNo.incrementAndGet();
        if (orderNo > MAX_ORDER_NO) {
            //如果超过了最大序列号   则重置为0
            if (!this.orderNo.compareAndSet(orderNo, 0)) {
                //这里使用cas操作，所以不需要加锁    1、操作失败了   则表示别的线程已经更改了数据，则直接进行自增并获取则可以了
                orderNo = this.orderNo.incrementAndGet();
            } else {
                //操作成功后，则序列号为0了
                orderNo = 0;
            }
        }
        //符号位（1）bit、时间戳（2~42）bit | 序列号（43~59）bit | 机器码（60~64）bit
        return currentTimeMillis | (orderNo << 14 >>> 9) | MACHINE_CODE;
    }

}
