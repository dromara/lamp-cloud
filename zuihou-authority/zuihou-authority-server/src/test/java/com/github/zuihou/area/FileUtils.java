package com.github.zuihou.area;

import java.io.File;
import java.io.IOException;

/**
 * @author zuihou
 */

public class FileUtils {

    public static String getProjectDir() {
        File directory = new File("");// 参数为空
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(courseFile);
        return courseFile;
    }


}
