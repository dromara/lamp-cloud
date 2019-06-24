package com.github.zuihou.file.constant;


import java.io.Serializable;

/**
 * <p>
 * 数据库常量
 * 文件表
 * </p>
 *
 * @author zuihou
 * @date 2019-05-20
 */
public class FileConstants implements Serializable {

    /**
     * 字段常量
     */
    public static final String ID = "id";
    public static final String DATA_TYPE = "data_type";
    public static final String SUBMITTED_FILE_NAME = "submitted_file_name";
    public static final String TREE_PATH = "tree_path";
    public static final String GRADE = "grade";
    public static final String IS_DELETE = "is_delete";
    public static final String FOLDER_ID = "folder_id";
    public static final String URL = "url";
    public static final String SIZE = "size";
    public static final String FOLDER_NAME = "folder_name";
    public static final String GROUP = "group_";
    public static final String PATH = "path";
    public static final String RELATIVE_PATH = "relative_path";
    public static final String FILE_MD5 = "file_md5";
    public static final String CONTEXT_TYPE = "context_type";
    public static final String FILENAME = "filename";
    public static final String EXT = "ext";
    public static final String ICON = "icon";
    public static final String CREATE_MONTH = "create_month";
    public static final String CREATE_WEEK = "create_week";
    public static final String CREATE_DAY = "create_day";
    public static final String CREATE_TIME = "create_time";
    public static final String CREATE_USER = "create_user";
    public static final String UPDATE_TIME = "update_time";
    public static final String UPDATE_USER = "update_user";
    private static final long serialVersionUID = 1L;

    private FileConstants() {
        super();
    }
}
