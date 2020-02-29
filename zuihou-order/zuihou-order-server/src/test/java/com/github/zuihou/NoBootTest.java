package com.github.zuihou;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/08/12
 */
public class NoBootTest {
    @Test
    public void testCopy() throws Exception {
        File src = new File("/projects/uploadfile/file/2019/08/4b4c5f2f3d945db915d4645e26189d6e.mp4");
        File target = new File("/projects/uploadfile/file/2019/08/appache/1.mp4");
        File target2 = new File("/projects/uploadfile/file/2019/08/hutool/1.mp4");

        FileUtils.copyFile(src, target);

        FileUtil.copy(src, target2, true);

    }


}
