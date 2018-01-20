package com.github.zuihou.admin.utils;

import java.util.UUID;

/**
 * @author zuihou
 * @createTime 2017-12-15 16:23
 */
public class UUIDUtils {
    public final static String[] CHARS = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    private UUIDUtils() {
    }

    /**
     * 生成32位UUID
     * @return
     */
    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成8位UUID
     * @return
     */
    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer("zkhy");
        String uuid = generateUuid();
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(CHARS[x % 0x3E]);
        }
        return shortBuffer.toString();
    }
}
