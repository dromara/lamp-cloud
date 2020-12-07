package com.tangyh.lamp.file.utils;


import com.tangyh.basic.exception.BizException;
import com.tangyh.basic.utils.StrPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import static com.tangyh.basic.utils.StrPool.SLASH;


/**
 * ZipUtils on spring-boot-filemanager
 *
 * @author <a href="mailto:akhuting@hotmail.com">Alex Yang</a>
 * @date 2016年08月25日 10:08
 */
@Slf4j
public class ZipUtils {
    private ZipUtils() {
    }

    private static final String AGENT_FIREFOX = "firefox";

    private static void zipFiles(ZipOutputStream out, String path, File... srcFiles) {
        path = path.replaceAll("\\*", SLASH);
        if (!path.endsWith(SLASH)) {
            path += SLASH;
        }
        byte[] buf = new byte[1024];
        try {
            for (File srcFile : srcFiles) {
                if (srcFile.isDirectory()) {
                    File[] files = srcFile.listFiles();
                    String srcPath = srcFile.getName();
                    srcPath = srcPath.replaceAll("\\*", SLASH);
                    if (!srcPath.endsWith(SLASH)) {
                        srcPath += SLASH;
                    }
                    out.putNextEntry(new ZipEntry(path + srcPath));
                    zipFiles(out, path + srcPath, files);
                } else {
                    try (FileInputStream in = new FileInputStream(srcFile)) {
                        out.putNextEntry(new ZipEntry(path + srcFile.getName()));
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        out.closeEntry();
                    }
                }
            }
        } catch (Exception e) {
            log.info("ZipUtils error ", e);
        }
    }

    /**
     * 通过流打包下载文件
     *
     * @param out      输出刘
     * @param fileName 文件名
     * @param is       输入流
     */
    public static void zipFilesByInputStream(ZipOutputStream out, String fileName, InputStream is) throws Exception {
        byte[] buf = new byte[1024];
        try {
            out.putNextEntry(new ZipEntry(fileName));
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            is.close();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * 下载指定输入流的图片
     *
     * @param is  输入流
     * @param out 输出流
     * @throws Exception 异常
     */
    private static void downloadFile(InputStream is, OutputStream out) throws Exception {
        try {
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) > 0) {
                out.write(b, 0, length);
            }
        } finally {
            if (out != null) {
                out.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }

    public static void unZipFiles(File zipFile, String descDir) throws IOException {
        if (!descDir.endsWith(SLASH)) {
            descDir += SLASH;
        }
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        try (ZipFile zip = new ZipFile(zipFile)) {
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();

                String outPath = (descDir + zipEntryName).replaceAll("\\*", SLASH);
                //判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }


                try (InputStream in = zip.getInputStream(entry); OutputStream out = new FileOutputStream(outPath)) {
                    byte[] buf1 = new byte[1024];
                    int len;
                    while ((len = in.read(buf1)) > 0) {
                        out.write(buf1, 0, len);
                    }
                }
            }
        }
    }

    public static void zipFilesByInputStream(Map<String, String> fileMap, Long fileSize, String extName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpURLConnection connection = null;

        response.setContentType("application/octet-stream; charset=utf-8");
        String downloadFileName;
        String agent = request.getHeader("USER-AGENT");
        if (agent != null && agent.toLowerCase().indexOf(AGENT_FIREFOX) > 0) {
            downloadFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64((extName).getBytes(StandardCharsets.UTF_8)))) + "?=";
        } else {
            //~ \ / |:"<>?   这些字符不能被替换，因为系统允许文件名有这些字符！！
            downloadFileName = URLEncoder.encode(extName, "UTF-8")
                    .replaceAll("\\+", "%20").replaceAll("%28", "\\(")
                    .replaceAll("%29", "\\)")
                    .replaceAll("%3B", StrPool.SEMICOLON)
                    .replaceAll("%40", StrPool.AT).replaceAll("%23", "\\#")
                    .replaceAll("%26", "\\&").replaceAll("%2C", "\\,")
                    .replaceAll("%2B", StrPool.PLUS).replaceAll("%25", StrPool.PERCENT)
                    .replaceAll("%21", StrPool.EXCLAMATION_MARK).replaceAll("%5E", StrPool.HAT)
                    .replaceAll("%24", "\\$").replaceAll("%7E", StrPool.TILDA)

                    .replaceAll("%60", StrPool.BACKTICK).replaceAll("%5B", StrPool.LEFT_SQ_BRACKET)
                    .replaceAll("%3D", StrPool.EQUALS)
                    .replaceAll("%5D", StrPool.RIGHT_SQ_BRACKET).replaceAll("%5C", "\\\\")
                    .replaceAll("%27", StrPool.SINGLE_QUOTE).replaceAll("%2F", SLASH)
                    .replaceAll("%7B", StrPool.LEFT_BRACE).replaceAll("%7D", StrPool.RIGHT_BRACE)

                    .replaceAll("%7C", "\\|").replaceAll("%3A", "\\:")
                    .replaceAll("%22", "\\\"").replaceAll("%3C", "\\<")
                    .replaceAll("%3E", "\\>").replaceAll("%3F", "\\?")

            ;
            log.info("downloadFileName={}", downloadFileName);
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + downloadFileName);
        // 加了这个下载会报错？
//        if (fileSize != null && fileSize > 0) {
//            response.setHeader("Content-Length", String.valueOf(fileSize));
//        }

        ServletOutputStream out = response.getOutputStream();
        if (fileMap.size() == 1) {
            String url = null;
            for (Map.Entry<String, String> entry : fileMap.entrySet()) {
                url = entry.getValue();
            }
            try {
                connection = getConnection(url);
                ZipUtils.downloadFile(connection.getInputStream(), out);
            } catch (Exception e) {
                throw new BizException("文件地址连接超时", e);
            }
            return;
        }

        try (ZipOutputStream zos = new ZipOutputStream(out); BufferedOutputStream bos = new BufferedOutputStream(zos)) {
            for (Map.Entry<String, String> entry : fileMap.entrySet()) {
                String fileName = entry.getKey();
                String url = entry.getValue();
                BufferedInputStream bis = null;
                try {
                    connection = getConnection(url);
                    bis = new BufferedInputStream(connection.getInputStream());
                    zos.putNextEntry(new ZipEntry(fileName));

                    int len;
                    byte[] buf = new byte[10 * 1024];
                    while ((len = bis.read(buf, 0, buf.length)) != -1) {
                        bos.write(buf, 0, len);
                    }
                    bos.flush();
                } catch (Exception e) {
                    log.warn("打包下载多个文件异常, fileName=" + fileName + ",url=" + url, e);
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                    zos.closeEntry();
                }
            }
        }
    }

    private static HttpURLConnection getConnection(String url) throws Exception {
        log.info("url={}", url);
        URL conUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) conUrl.openConnection();
        connection.connect();
        return connection;
    }
}
