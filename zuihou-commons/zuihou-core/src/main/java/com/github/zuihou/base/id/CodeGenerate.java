package com.github.zuihou.base.id;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 1、 一秒钟可以产生10个编码，一分钟产生600个编码
 * 2、使用期限从2019-1-1 0:0:0 到达 Tue Jan 13 10:27:01 CST 2059 使用年限为40年
 * 3、支持最大16台机器的分布
 * 4、长度都是8位的字符串
 * 5、编码全部是大写字符，因为数据库一般期望不区分大小写
 *
 * @author
 */
public class CodeGenerate {

    //字符仅仅取值大写的，因为数据库一般期望不区分大小写
    public final static char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    //固定为26个字符
    public final static int charsNum = chars.length;
    /**
     * Fri Feb 01 01:01:01 CST 2019
     * 1548954061000L
     * 因为我们的生成器可以使用40年，而我们想在这些时间里面，生成出来的id是逐渐自增的。
     * 所以我这里指定了从什么时候开始使用id生成器。
     */
    private final static long START_TIME = 1548954061000L;

    /**
     * 机器码 （0-15）
     */
    private final int MACHINE_CODE;
    /**
     * 用于生成序列号
     */
    private AtomicLong orderNo;


    public CodeGenerate(final int machineCode) {

        if (machineCode < 0 || machineCode > 15) {
            throw new IllegalArgumentException("请注意，1、机器码在多台机器或应用间是不允许重复的！2、机器码取值仅仅在[0,15]闭区间");
        }
        this.MACHINE_CODE = machineCode;
        orderNo = new AtomicLong((System.currentTimeMillis() - START_TIME) / 100);
    }

    public String next() {
        long orderNo = this.orderNo.incrementAndGet();
        return encoded((orderNo << 4) | MACHINE_CODE);
    }

    private String encoded(long id) {

        char[] result = new char[8];
        //2021年前产生的编码长度不足8位，仅仅只有7位，故将首位固定为chars中
        //的最后一位. 而且只能是最后一位  因为首位下一次出现此字符的时间将会是最远的
        result[0] = chars[chars.length - 1];
        //转换成为26个字母
        int unit = charsNum;
        //最大长度为8
        int index = result.length - 1;
        do {
            result[index] = (chars[(int) (id % unit)]);
            index--;
            id = id / unit;
        } while (id != 0);
        return new String(result);
    }
}
