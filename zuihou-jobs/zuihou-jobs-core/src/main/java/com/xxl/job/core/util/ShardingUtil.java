package com.xxl.job.core.util;

import lombok.ToString;

/**
 * sharding vo
 *
 * @author xuxueli 2017-07-25 21:26:38
 */
public class ShardingUtil {

    private static InheritableThreadLocal<ShardingVO> contextHolder = new InheritableThreadLocal<ShardingVO>();

    public static ShardingVO getShardingVo() {
        return contextHolder.get();
    }

    public static void setShardingVo(ShardingVO shardingVo) {
        contextHolder.set(shardingVo);
    }

    /**
     * 分片
     *
     * @author zuihou
     * @date 2019-07-25 14:26
     */
    @ToString
    public static class ShardingVO {

        private int index;
        private int total;

        public ShardingVO(int index, int total) {
            this.index = index;
            this.total = total;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

}
