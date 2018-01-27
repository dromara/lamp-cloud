package com.github.zuihou.file.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * FileUtils on spring-boot-filemanager
 *
 * @author <a href="mailto:akhuting@hotmail.com">Alex Yang</a>
 * @date 2016年08月25日 10:02
 */
public class FileUtils {

    private static List<String> ExtsDocument = Arrays.asList
            (
                    ".doc", ".docx", ".docm",
                    ".dot", ".dotx", ".dotm",
                    ".odt", ".fodt", ".rtf", ".txt",
                    ".html", ".htm", ".mht",
                    ".pdf", ".djvu", ".fb2", ".epub", ".xps"
            );

    private static List<String> ExtsSpreadsheet = Arrays.asList
            (
                    ".xls", ".xlsx", ".xlsm",
                    ".xlt", ".xltx", ".xltm",
                    ".ods", ".fods", ".csv"
            );

    private static List<String> ExtsPresentation = Arrays.asList
            (
                    ".pps", ".ppsx", ".ppsm",
                    ".ppt", ".pptx", ".pptm",
                    ".pot", ".potx", ".potm",
                    ".odp", ".fodp"
            );


    public static String getFileName(String header) {
        String[] tempArr1 = header.split(";");
        String[] tempArr2 = tempArr1[2].split("=");
        //获取文件名，兼容各种浏览器的写法
        return tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");

    }

    public static String getPermissions(Path path) throws IOException {
        if (path.getFileSystem().getSeparator().equals("/")) {
            PosixFileAttributeView fileAttributeView = Files.getFileAttributeView(path, PosixFileAttributeView.class);
            PosixFileAttributes readAttributes = fileAttributeView.readAttributes();
            Set<PosixFilePermission> permissions = readAttributes.permissions();
            return PosixFilePermissions.toString(permissions);
        } else {

            return "rwxrwxr-x";
        }
    }

    public static String setPermissions(File file, String permsCode, boolean recursive) throws IOException {
        PosixFileAttributeView fileAttributeView = Files.getFileAttributeView(file.toPath(), PosixFileAttributeView.class);
        fileAttributeView.setPermissions(PosixFilePermissions.fromString(permsCode));
        if (file.isDirectory() && recursive && file.listFiles() != null) {
            for (File f : file.listFiles()) {
                setPermissions(f, permsCode, true);
            }
        }
        return permsCode;
    }

    public static boolean write(InputStream inputStream, String fileUploadPath, String fileName) {
        boolean ret = false;
        File dir = new File(fileUploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(fileUploadPath + File.separator + fileName);
        try (OutputStream outputStream = new FileOutputStream(file)) {

            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            ret = true;

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    public static void mkFolder(String fileName) {
        File f = new File(fileName);
        if (!f.exists()) {
            f.mkdir();
        }
    }

    public static File mkFile(String fileName) {
        File f = new File(fileName);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    public static void fileProber(File dirFile) {

        File parentFile = dirFile.getParentFile();
        if (!parentFile.exists()) {

            // 递归寻找上级目录
            fileProber(parentFile);

            parentFile.mkdir();
        }

    }

    public static FileType getFileType(String fileName) {
        String ext = getExtension(fileName).toLowerCase();

        if (ExtsDocument.contains(ext)) {
            return FileType.Text;
        }

        if (ExtsSpreadsheet.contains(ext)) {
            return FileType.Spreadsheet;
        }

        if (ExtsPresentation.contains(ext)) {
            return FileType.Presentation;
        }

        return FileType.Text;
    }

    public static String getExtension(String fileName) {
        if (StringUtils.INDEX_NOT_FOUND == StringUtils.indexOf(fileName, ".")) {
            return StringUtils.EMPTY;
        }
        String ext = StringUtils.substring(fileName,
                StringUtils.lastIndexOf(fileName, "."));
        return StringUtils.trimToEmpty(ext);
    }

    public static void main(String[] args) {
        String filePath = "/home/xx.";
        //System.out.println(filePath.substring(filePath.lastIndexOf(".") + 1));
        System.out.println(getExtension(filePath));
    }
}
