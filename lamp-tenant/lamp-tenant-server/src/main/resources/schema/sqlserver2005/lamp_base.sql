use lamp_base_1234;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_appendix]') AND type in (N'U')) DROP TABLE [dbo].[c_appendix];
CREATE TABLE [dbo].[c_appendix](
    id BIGINT NOT NULL,
    biz_id BIGINT NOT NULL DEFAULT  0,
    biz_type VARCHAR(255) NOT NULL,
    file_type VARCHAR(255),
    bucket VARCHAR(255),
    path VARCHAR(255),
    original_file_name VARCHAR(255),
    content_type VARCHAR(255),
    size_ BIGINT DEFAULT  0,
    create_time DATETIME NOT NULL,
    created_by BIGINT,
    update_time DATETIME NOT NULL,
    updated_by BIGINT,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '业务附件', 'SCHEMA', dbo, 'table', c_appendix, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', c_appendix, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '业务id', 'SCHEMA', dbo, 'table', c_appendix, 'column', biz_id;
EXEC sp_addextendedproperty 'MS_Description', '业务类型', 'SCHEMA', dbo, 'table', c_appendix, 'column', biz_type;
EXEC sp_addextendedproperty 'MS_Description', '文件类型', 'SCHEMA', dbo, 'table', c_appendix, 'column', file_type;
EXEC sp_addextendedproperty 'MS_Description', '桶', 'SCHEMA', dbo, 'table', c_appendix, 'column', bucket;
EXEC sp_addextendedproperty 'MS_Description', '文件相对地址', 'SCHEMA', dbo, 'table', c_appendix, 'column', path;
EXEC sp_addextendedproperty 'MS_Description', '原始文件名', 'SCHEMA', dbo, 'table', c_appendix, 'column', original_file_name;
EXEC sp_addextendedproperty 'MS_Description', '文件类型', 'SCHEMA', dbo, 'table', c_appendix, 'column', content_type;
EXEC sp_addextendedproperty 'MS_Description', '大小', 'SCHEMA', dbo, 'table', c_appendix, 'column', size_;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_appendix, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', c_appendix, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '最后修改时间', 'SCHEMA', dbo, 'table', c_appendix, 'column', update_time;
EXEC sp_addextendedproperty 'MS_Description', '最后修改人', 'SCHEMA', dbo, 'table', c_appendix, 'column', updated_by;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_application]') AND type in (N'U')) DROP TABLE [dbo].[c_application];
CREATE TABLE [dbo].[c_application](
    id BIGINT NOT NULL,
    client_id VARCHAR(24),
    client_secret VARCHAR(32),
    website VARCHAR(100),
    name VARCHAR(255) NOT NULL,
    icon VARCHAR(255),
    app_type VARCHAR(10),
    describe_ VARCHAR(200),
    state BIT DEFAULT  1,
    created_by BIGINT,
    create_time DATETIME,
    updated_by BIGINT,
    update_time DATETIME,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '应用', 'SCHEMA', dbo, 'table', c_application, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', c_application, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '客户端ID', 'SCHEMA', dbo, 'table', c_application, 'column', client_id;
EXEC sp_addextendedproperty 'MS_Description', '客户端密码', 'SCHEMA', dbo, 'table', c_application, 'column', client_secret;
EXEC sp_addextendedproperty 'MS_Description', '官网', 'SCHEMA', dbo, 'table', c_application, 'column', website;
EXEC sp_addextendedproperty 'MS_Description', '应用名称', 'SCHEMA', dbo, 'table', c_application, 'column', name;
EXEC sp_addextendedproperty 'MS_Description', '应用图标', 'SCHEMA', dbo, 'table', c_application, 'column', icon;
EXEC sp_addextendedproperty 'MS_Description', '类型;#{SERVER:服务应用;APP:手机应用;PC:PC网页应用;WAP:手机网页应用}', 'SCHEMA', dbo, 'table', c_application, 'column', app_type;
EXEC sp_addextendedproperty 'MS_Description', '备注', 'SCHEMA', dbo, 'table', c_application, 'column', describe_;
EXEC sp_addextendedproperty 'MS_Description', '状态', 'SCHEMA', dbo, 'table', c_application, 'column', state;
EXEC sp_addextendedproperty 'MS_Description', '创建人id', 'SCHEMA', dbo, 'table', c_application, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_application, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '更新人id', 'SCHEMA', dbo, 'table', c_application, 'column', updated_by;
EXEC sp_addextendedproperty 'MS_Description', '更新时间', 'SCHEMA', dbo, 'table', c_application, 'column', update_time;


CREATE UNIQUE INDEX uk_client_id ON c_application(client_id);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_area]') AND type in (N'U')) DROP TABLE [dbo].[c_area];
CREATE TABLE [dbo].[c_area](
    id BIGINT NOT NULL,
    code VARCHAR(64) NOT NULL,
    label VARCHAR(255) NOT NULL,
    full_name VARCHAR(255),
    sort_value INT DEFAULT  1,
    longitude VARCHAR(255),
    latitude VARCHAR(255),
    level_ VARCHAR(10),
    source_ VARCHAR(255),
    state BIT DEFAULT  0,
    parent_id BIGINT DEFAULT  0,
    create_time DATETIME,
    created_by BIGINT,
    update_time DATETIME,
    updated_by BIGINT,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '地区表', 'SCHEMA', dbo, 'table', c_area, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'id', 'SCHEMA', dbo, 'table', c_area, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '编码', 'SCHEMA', dbo, 'table', c_area, 'column', code;
EXEC sp_addextendedproperty 'MS_Description', '名称', 'SCHEMA', dbo, 'table', c_area, 'column', label;
EXEC sp_addextendedproperty 'MS_Description', '全名', 'SCHEMA', dbo, 'table', c_area, 'column', full_name;
EXEC sp_addextendedproperty 'MS_Description', '排序', 'SCHEMA', dbo, 'table', c_area, 'column', sort_value;
EXEC sp_addextendedproperty 'MS_Description', '经度', 'SCHEMA', dbo, 'table', c_area, 'column', longitude;
EXEC sp_addextendedproperty 'MS_Description', '维度', 'SCHEMA', dbo, 'table', c_area, 'column', latitude;
EXEC sp_addextendedproperty 'MS_Description', '行政区级;
@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.AREA_LEVEL)', 'SCHEMA', dbo, 'table', c_area, 'column', level_;
EXEC sp_addextendedproperty 'MS_Description', '数据来源', 'SCHEMA', dbo, 'table', c_area, 'column', source_;
EXEC sp_addextendedproperty 'MS_Description', '状态', 'SCHEMA', dbo, 'table', c_area, 'column', state;
EXEC sp_addextendedproperty 'MS_Description', '父ID', 'SCHEMA', dbo, 'table', c_area, 'column', parent_id;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_area, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', c_area, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '更新时间', 'SCHEMA', dbo, 'table', c_area, 'column', update_time;
EXEC sp_addextendedproperty 'MS_Description', '更新人', 'SCHEMA', dbo, 'table', c_area, 'column', updated_by;


CREATE UNIQUE INDEX uk_code ON c_area(code);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_dictionary]') AND type in (N'U')) DROP TABLE [dbo].[c_dictionary];
CREATE TABLE [dbo].[c_dictionary](
    id BIGINT NOT NULL,
    type VARCHAR(255) NOT NULL,
    label VARCHAR(255) NOT NULL,
    code VARCHAR(64) NOT NULL,
    name VARCHAR(64) NOT NULL,
    state BIT DEFAULT  1,
    describe_ VARCHAR(255),
    sort_value INT DEFAULT  1,
    icon VARCHAR(255),
    css_style VARCHAR(255),
    css_class VARCHAR(255),
    readonly_ BIT DEFAULT  0,
    created_by BIGINT,
    create_time DATETIME,
    updated_by BIGINT,
    update_time DATETIME,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '字典项', 'SCHEMA', dbo, 'table', c_dictionary, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', c_dictionary, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '类型', 'SCHEMA', dbo, 'table', c_dictionary, 'column', type;
EXEC sp_addextendedproperty 'MS_Description', '类型标签', 'SCHEMA', dbo, 'table', c_dictionary, 'column', label;
EXEC sp_addextendedproperty 'MS_Description', '编码', 'SCHEMA', dbo, 'table', c_dictionary, 'column', code;
EXEC sp_addextendedproperty 'MS_Description', '名称', 'SCHEMA', dbo, 'table', c_dictionary, 'column', name;
EXEC sp_addextendedproperty 'MS_Description', '状态', 'SCHEMA', dbo, 'table', c_dictionary, 'column', state;
EXEC sp_addextendedproperty 'MS_Description', '描述', 'SCHEMA', dbo, 'table', c_dictionary, 'column', describe_;
EXEC sp_addextendedproperty 'MS_Description', '排序', 'SCHEMA', dbo, 'table', c_dictionary, 'column', sort_value;
EXEC sp_addextendedproperty 'MS_Description', '图标', 'SCHEMA', dbo, 'table', c_dictionary, 'column', icon;
EXEC sp_addextendedproperty 'MS_Description', 'css样式', 'SCHEMA', dbo, 'table', c_dictionary, 'column', css_style;
EXEC sp_addextendedproperty 'MS_Description', 'css;class', 'SCHEMA', dbo, 'table', c_dictionary, 'column', css_class;
EXEC sp_addextendedproperty 'MS_Description', '内置', 'SCHEMA', dbo, 'table', c_dictionary, 'column', readonly_;
EXEC sp_addextendedproperty 'MS_Description', '创建人id', 'SCHEMA', dbo, 'table', c_dictionary, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_dictionary, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '更新人id', 'SCHEMA', dbo, 'table', c_dictionary, 'column', updated_by;
EXEC sp_addextendedproperty 'MS_Description', '更新时间', 'SCHEMA', dbo, 'table', c_dictionary, 'column', update_time;


CREATE UNIQUE INDEX uk_type_code ON c_dictionary(type,code);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_file]') AND type in (N'U')) DROP TABLE [dbo].[c_file];
CREATE TABLE [dbo].[c_file](
    id BIGINT NOT NULL,
    biz_type VARCHAR(255) NOT NULL,
    file_type VARCHAR(255),
    storage_type VARCHAR(255),
    bucket VARCHAR(255),
    path VARCHAR(255),
    url VARCHAR(255),
    unique_file_name VARCHAR(255),
    file_md5 VARCHAR(255),
    original_file_name VARCHAR(255),
    content_type VARCHAR(255),
    suffix VARCHAR(255),
    size_ BIGINT DEFAULT  0,
    create_time DATETIME NOT NULL,
    created_by BIGINT NOT NULL,
    update_time DATETIME NOT NULL,
    updated_by BIGINT NOT NULL,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '增量文件上传日志', 'SCHEMA', dbo, 'table', c_file, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', c_file, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '业务类型', 'SCHEMA', dbo, 'table', c_file, 'column', biz_type;
EXEC sp_addextendedproperty 'MS_Description', '文件类型', 'SCHEMA', dbo, 'table', c_file, 'column', file_type;
EXEC sp_addextendedproperty 'MS_Description', '存储类型
LOCAL;FAST_DFS MIN_IO ALI', 'SCHEMA', dbo, 'table', c_file, 'column', storage_type;
EXEC sp_addextendedproperty 'MS_Description', '桶', 'SCHEMA', dbo, 'table', c_file, 'column', bucket;
EXEC sp_addextendedproperty 'MS_Description', '文件相对地址', 'SCHEMA', dbo, 'table', c_file, 'column', path;
EXEC sp_addextendedproperty 'MS_Description', '文件访问地址', 'SCHEMA', dbo, 'table', c_file, 'column', url;
EXEC sp_addextendedproperty 'MS_Description', '唯一文件名', 'SCHEMA', dbo, 'table', c_file, 'column', unique_file_name;
EXEC sp_addextendedproperty 'MS_Description', '文件md5', 'SCHEMA', dbo, 'table', c_file, 'column', file_md5;
EXEC sp_addextendedproperty 'MS_Description', '原始文件名', 'SCHEMA', dbo, 'table', c_file, 'column', original_file_name;
EXEC sp_addextendedproperty 'MS_Description', '文件类型', 'SCHEMA', dbo, 'table', c_file, 'column', content_type;
EXEC sp_addextendedproperty 'MS_Description', '后缀', 'SCHEMA', dbo, 'table', c_file, 'column', suffix;
EXEC sp_addextendedproperty 'MS_Description', '大小', 'SCHEMA', dbo, 'table', c_file, 'column', size_;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_file, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', c_file, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '最后修改时间', 'SCHEMA', dbo, 'table', c_file, 'column', update_time;
EXEC sp_addextendedproperty 'MS_Description', '最后修改人', 'SCHEMA', dbo, 'table', c_file, 'column', updated_by;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_login_log]') AND type in (N'U')) DROP TABLE [dbo].[c_login_log];
CREATE TABLE [dbo].[c_login_log](
    id BIGINT NOT NULL,
    request_ip VARCHAR(50),
    user_id BIGINT,
    user_name VARCHAR(50),
    account VARCHAR(30),
    description VARCHAR(255),
    login_date CHAR(10),
    ua TEXT,
    browser VARCHAR(255),
    browser_version VARCHAR(255),
    operating_system VARCHAR(255),
    location VARCHAR(255),
    create_time DATETIME,
    created_by BIGINT,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '登录日志', 'SCHEMA', dbo, 'table', c_login_log, null, null;
EXEC sp_addextendedproperty 'MS_Description', '主键', 'SCHEMA', dbo, 'table', c_login_log, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '登录IP', 'SCHEMA', dbo, 'table', c_login_log, 'column', request_ip;
EXEC sp_addextendedproperty 'MS_Description', '登录人ID', 'SCHEMA', dbo, 'table', c_login_log, 'column', user_id;
EXEC sp_addextendedproperty 'MS_Description', '登录人姓名', 'SCHEMA', dbo, 'table', c_login_log, 'column', user_name;
EXEC sp_addextendedproperty 'MS_Description', '登录人账号', 'SCHEMA', dbo, 'table', c_login_log, 'column', account;
EXEC sp_addextendedproperty 'MS_Description', '登录描述', 'SCHEMA', dbo, 'table', c_login_log, 'column', description;
EXEC sp_addextendedproperty 'MS_Description', '登录时间', 'SCHEMA', dbo, 'table', c_login_log, 'column', login_date;
EXEC sp_addextendedproperty 'MS_Description', '浏览器请求头', 'SCHEMA', dbo, 'table', c_login_log, 'column', ua;
EXEC sp_addextendedproperty 'MS_Description', '浏览器名称', 'SCHEMA', dbo, 'table', c_login_log, 'column', browser;
EXEC sp_addextendedproperty 'MS_Description', '浏览器版本', 'SCHEMA', dbo, 'table', c_login_log, 'column', browser_version;
EXEC sp_addextendedproperty 'MS_Description', '操作系统', 'SCHEMA', dbo, 'table', c_login_log, 'column', operating_system;
EXEC sp_addextendedproperty 'MS_Description', '登录地点', 'SCHEMA', dbo, 'table', c_login_log, 'column', location;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_login_log, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', c_login_log, 'column', created_by;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_menu]') AND type in (N'U')) DROP TABLE [dbo].[c_menu];
CREATE TABLE [dbo].[c_menu](
    id BIGINT NOT NULL,
    label VARCHAR(20) NOT NULL,
    resource_type CHAR(2),
    tree_grade INT,
    tree_path VARCHAR(512),
    describe_ VARCHAR(200),
    is_general BIT DEFAULT  0,
    path VARCHAR(255),
    component VARCHAR(255),
    state BIT DEFAULT  1,
    sort_value INT DEFAULT  1,
    icon VARCHAR(255),
    group_ VARCHAR(20),
    data_scope CHAR(2),
    custom_class VARCHAR(255),
    is_def BIT DEFAULT  0,
    parent_id BIGINT DEFAULT  0,
    readonly_ BIT DEFAULT  0,
    created_by BIGINT,
    create_time DATETIME,
    updated_by BIGINT,
    update_time DATETIME,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '菜单', 'SCHEMA', dbo, 'table', c_menu, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', c_menu, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '名称', 'SCHEMA', dbo, 'table', c_menu, 'column', label;
EXEC sp_addextendedproperty 'MS_Description', '资源类型;[20-菜单 60-数据];@Echo(api = DICTIONARY_ITEM_FEIGN_CLASS,dictType = EchoDictType.RESOURCE_TYPE)', 'SCHEMA', dbo, 'table', c_menu, 'column', resource_type;
EXEC sp_addextendedproperty 'MS_Description', '树层级', 'SCHEMA', dbo, 'table', c_menu, 'column', tree_grade;
EXEC sp_addextendedproperty 'MS_Description', '树路径', 'SCHEMA', dbo, 'table', c_menu, 'column', tree_path;
EXEC sp_addextendedproperty 'MS_Description', '描述', 'SCHEMA', dbo, 'table', c_menu, 'column', describe_;
EXEC sp_addextendedproperty 'MS_Description', '通用菜单;True表示无需分配所有人就可以访问的', 'SCHEMA', dbo, 'table', c_menu, 'column', is_general;
EXEC sp_addextendedproperty 'MS_Description', '路径', 'SCHEMA', dbo, 'table', c_menu, 'column', path;
EXEC sp_addextendedproperty 'MS_Description', '组件', 'SCHEMA', dbo, 'table', c_menu, 'column', component;
EXEC sp_addextendedproperty 'MS_Description', '状态', 'SCHEMA', dbo, 'table', c_menu, 'column', state;
EXEC sp_addextendedproperty 'MS_Description', '排序', 'SCHEMA', dbo, 'table', c_menu, 'column', sort_value;
EXEC sp_addextendedproperty 'MS_Description', '菜单图标', 'SCHEMA', dbo, 'table', c_menu, 'column', icon;
EXEC sp_addextendedproperty 'MS_Description', '分组', 'SCHEMA', dbo, 'table', c_menu, 'column', group_;
EXEC sp_addextendedproperty 'MS_Description', '数据范围;[01-全部 02-本单位及子级 03-本单位 04-本部门 05-本部门及子级 06-个人 07-自定义]', 'SCHEMA', dbo, 'table', c_menu, 'column', data_scope;
EXEC sp_addextendedproperty 'MS_Description', '实现类', 'SCHEMA', dbo, 'table', c_menu, 'column', custom_class;
EXEC sp_addextendedproperty 'MS_Description', '是否默认', 'SCHEMA', dbo, 'table', c_menu, 'column', is_def;
EXEC sp_addextendedproperty 'MS_Description', '父级菜单ID', 'SCHEMA', dbo, 'table', c_menu, 'column', parent_id;
EXEC sp_addextendedproperty 'MS_Description', '内置', 'SCHEMA', dbo, 'table', c_menu, 'column', readonly_;
EXEC sp_addextendedproperty 'MS_Description', '创建人id', 'SCHEMA', dbo, 'table', c_menu, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_menu, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '更新人id', 'SCHEMA', dbo, 'table', c_menu, 'column', updated_by;
EXEC sp_addextendedproperty 'MS_Description', '更新时间', 'SCHEMA', dbo, 'table', c_menu, 'column', update_time;


IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_opt_log]') AND type in (N'U')) DROP TABLE [dbo].[c_opt_log];
CREATE TABLE [dbo].[c_opt_log](
    id BIGINT NOT NULL,
    request_ip VARCHAR(50),
    type VARCHAR(5),
    user_name VARCHAR(50),
    description VARCHAR(255),
    class_path VARCHAR(255),
    action_method VARCHAR(50),
    request_uri VARCHAR(50),
    http_method VARCHAR(10),
    start_time DATETIME,
    finish_time DATETIME,
    consuming_time BIGINT,
    ua VARCHAR(500),
    create_time DATETIME,
    created_by BIGINT,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '系统日志', 'SCHEMA', dbo, 'table', c_opt_log, null, null;
EXEC sp_addextendedproperty 'MS_Description', '主键', 'SCHEMA', dbo, 'table', c_opt_log, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '操作IP', 'SCHEMA', dbo, 'table', c_opt_log, 'column', request_ip;
EXEC sp_addextendedproperty 'MS_Description', '日志类型;
#LogType{OPT:操作类型;EX:异常类型}', 'SCHEMA', dbo, 'table', c_opt_log, 'column', type;
EXEC sp_addextendedproperty 'MS_Description', '操作人', 'SCHEMA', dbo, 'table', c_opt_log, 'column', user_name;
EXEC sp_addextendedproperty 'MS_Description', '操作描述', 'SCHEMA', dbo, 'table', c_opt_log, 'column', description;
EXEC sp_addextendedproperty 'MS_Description', '类路径', 'SCHEMA', dbo, 'table', c_opt_log, 'column', class_path;
EXEC sp_addextendedproperty 'MS_Description', '请求方法', 'SCHEMA', dbo, 'table', c_opt_log, 'column', action_method;
EXEC sp_addextendedproperty 'MS_Description', '请求地址', 'SCHEMA', dbo, 'table', c_opt_log, 'column', request_uri;
EXEC sp_addextendedproperty 'MS_Description', '请求类型;
#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}', 'SCHEMA', dbo, 'table', c_opt_log, 'column', http_method;
EXEC sp_addextendedproperty 'MS_Description', '开始时间', 'SCHEMA', dbo, 'table', c_opt_log, 'column', start_time;
EXEC sp_addextendedproperty 'MS_Description', '完成时间', 'SCHEMA', dbo, 'table', c_opt_log, 'column', finish_time;
EXEC sp_addextendedproperty 'MS_Description', '消耗时间', 'SCHEMA', dbo, 'table', c_opt_log, 'column', consuming_time;
EXEC sp_addextendedproperty 'MS_Description', '浏览器', 'SCHEMA', dbo, 'table', c_opt_log, 'column', ua;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_opt_log, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', c_opt_log, 'column', created_by;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_opt_log_ext]') AND type in (N'U')) DROP TABLE [dbo].[c_opt_log_ext];
CREATE TABLE [dbo].[c_opt_log_ext](
    id BIGINT NOT NULL,
    params TEXT,
    result TEXT,
    ex_detail TEXT,
    create_time DATETIME,
    created_by BIGINT,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '系统日志扩展', 'SCHEMA', dbo, 'table', c_opt_log_ext, null, null;
EXEC sp_addextendedproperty 'MS_Description', '主键', 'SCHEMA', dbo, 'table', c_opt_log_ext, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '请求参数', 'SCHEMA', dbo, 'table', c_opt_log_ext, 'column', params;
EXEC sp_addextendedproperty 'MS_Description', '返回值', 'SCHEMA', dbo, 'table', c_opt_log_ext, 'column', result;
EXEC sp_addextendedproperty 'MS_Description', '异常描述', 'SCHEMA', dbo, 'table', c_opt_log_ext, 'column', ex_detail;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_opt_log_ext, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', c_opt_log_ext, 'column', created_by;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_org]') AND type in (N'U')) DROP TABLE [dbo].[c_org];
CREATE TABLE [dbo].[c_org](
    id BIGINT NOT NULL,
    label VARCHAR(255) NOT NULL,
    type_ CHAR(2),
    abbreviation VARCHAR(255),
    parent_id BIGINT DEFAULT  0,
    tree_path VARCHAR(255),
    sort_value INT DEFAULT  1,
    state BIT DEFAULT  1,
    describe_ VARCHAR(255),
    create_time DATETIME,
    created_by BIGINT,
    update_time DATETIME,
    updated_by BIGINT,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '组织', 'SCHEMA', dbo, 'table', c_org, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', c_org, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '名称', 'SCHEMA', dbo, 'table', c_org, 'column', label;
EXEC sp_addextendedproperty 'MS_Description', '类型;@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.ORG_TYPE)', 'SCHEMA', dbo, 'table', c_org, 'column', type_;
EXEC sp_addextendedproperty 'MS_Description', '简称', 'SCHEMA', dbo, 'table', c_org, 'column', abbreviation;
EXEC sp_addextendedproperty 'MS_Description', '父ID', 'SCHEMA', dbo, 'table', c_org, 'column', parent_id;
EXEC sp_addextendedproperty 'MS_Description', '树结构', 'SCHEMA', dbo, 'table', c_org, 'column', tree_path;
EXEC sp_addextendedproperty 'MS_Description', '排序', 'SCHEMA', dbo, 'table', c_org, 'column', sort_value;
EXEC sp_addextendedproperty 'MS_Description', '状态', 'SCHEMA', dbo, 'table', c_org, 'column', state;
EXEC sp_addextendedproperty 'MS_Description', '描述', 'SCHEMA', dbo, 'table', c_org, 'column', describe_;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_org, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', c_org, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', dbo, 'table', c_org, 'column', update_time;
EXEC sp_addextendedproperty 'MS_Description', '修改人', 'SCHEMA', dbo, 'table', c_org, 'column', updated_by;


CREATE UNIQUE INDEX uk_name ON c_org(label);
CREATE INDEX fu_path ON c_org(tree_path);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_parameter]') AND type in (N'U')) DROP TABLE [dbo].[c_parameter];
CREATE TABLE [dbo].[c_parameter](
    id BIGINT NOT NULL,
    key_ VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    describe_ VARCHAR(255),
    state BIT DEFAULT  1,
    readonly_ BIT DEFAULT  0,
    created_by BIGINT,
    create_time DATETIME,
    updated_by BIGINT,
    update_time DATETIME,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '参数配置', 'SCHEMA', dbo, 'table', c_parameter, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', c_parameter, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '参数键', 'SCHEMA', dbo, 'table', c_parameter, 'column', key_;
EXEC sp_addextendedproperty 'MS_Description', '参数值', 'SCHEMA', dbo, 'table', c_parameter, 'column', value;
EXEC sp_addextendedproperty 'MS_Description', '参数名称', 'SCHEMA', dbo, 'table', c_parameter, 'column', name;
EXEC sp_addextendedproperty 'MS_Description', '描述', 'SCHEMA', dbo, 'table', c_parameter, 'column', describe_;
EXEC sp_addextendedproperty 'MS_Description', '状态', 'SCHEMA', dbo, 'table', c_parameter, 'column', state;
EXEC sp_addextendedproperty 'MS_Description', '内置', 'SCHEMA', dbo, 'table', c_parameter, 'column', readonly_;
EXEC sp_addextendedproperty 'MS_Description', '创建人id', 'SCHEMA', dbo, 'table', c_parameter, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_parameter, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '更新人id', 'SCHEMA', dbo, 'table', c_parameter, 'column', updated_by;
EXEC sp_addextendedproperty 'MS_Description', '更新时间', 'SCHEMA', dbo, 'table', c_parameter, 'column', update_time;


CREATE UNIQUE INDEX uk_key ON c_parameter(key_);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_resource]') AND type in (N'U')) DROP TABLE [dbo].[c_resource];
CREATE TABLE [dbo].[c_resource](
    id BIGINT NOT NULL,
    code VARCHAR(500),
    name VARCHAR(255) NOT NULL,
    menu_id BIGINT,
    describe_ VARCHAR(255),
    readonly_ BIT DEFAULT  1,
    created_by BIGINT,
    create_time DATETIME,
    updated_by BIGINT,
    update_time DATETIME,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '资源', 'SCHEMA', dbo, 'table', c_resource, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', c_resource, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '编码', 'SCHEMA', dbo, 'table', c_resource, 'column', code;
EXEC sp_addextendedproperty 'MS_Description', '名称', 'SCHEMA', dbo, 'table', c_resource, 'column', name;
EXEC sp_addextendedproperty 'MS_Description', '菜单;#c_menu', 'SCHEMA', dbo, 'table', c_resource, 'column', menu_id;
EXEC sp_addextendedproperty 'MS_Description', '描述', 'SCHEMA', dbo, 'table', c_resource, 'column', describe_;
EXEC sp_addextendedproperty 'MS_Description', '内置', 'SCHEMA', dbo, 'table', c_resource, 'column', readonly_;
EXEC sp_addextendedproperty 'MS_Description', '创建人id', 'SCHEMA', dbo, 'table', c_resource, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_resource, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '更新人id', 'SCHEMA', dbo, 'table', c_resource, 'column', updated_by;
EXEC sp_addextendedproperty 'MS_Description', '更新时间', 'SCHEMA', dbo, 'table', c_resource, 'column', update_time;


CREATE UNIQUE INDEX uk_code ON c_resource(code);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_role]') AND type in (N'U')) DROP TABLE [dbo].[c_role];
CREATE TABLE [dbo].[c_role](
    id BIGINT NOT NULL,
    category CHAR(2),
    name VARCHAR(30) NOT NULL,
    code VARCHAR(20),
    describe_ VARCHAR(100),
    state BIT DEFAULT  1,
    readonly_ BIT DEFAULT  0,
    created_by BIGINT,
    create_time DATETIME,
    updated_by BIGINT,
    update_time DATETIME,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '角色', 'SCHEMA', dbo, 'table', c_role, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', c_role, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '角色类别;[10-功能角色 20-桌面角色 30-数据角色]', 'SCHEMA', dbo, 'table', c_role, 'column', category;
EXEC sp_addextendedproperty 'MS_Description', '名称', 'SCHEMA', dbo, 'table', c_role, 'column', name;
EXEC sp_addextendedproperty 'MS_Description', '编码', 'SCHEMA', dbo, 'table', c_role, 'column', code;
EXEC sp_addextendedproperty 'MS_Description', '描述', 'SCHEMA', dbo, 'table', c_role, 'column', describe_;
EXEC sp_addextendedproperty 'MS_Description', '状态', 'SCHEMA', dbo, 'table', c_role, 'column', state;
EXEC sp_addextendedproperty 'MS_Description', '内置角色', 'SCHEMA', dbo, 'table', c_role, 'column', readonly_;
EXEC sp_addextendedproperty 'MS_Description', '创建人id', 'SCHEMA', dbo, 'table', c_role, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_role, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '更新人id', 'SCHEMA', dbo, 'table', c_role, 'column', updated_by;
EXEC sp_addextendedproperty 'MS_Description', '更新时间', 'SCHEMA', dbo, 'table', c_role, 'column', update_time;


CREATE UNIQUE INDEX uk_code ON c_role(code);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_role_authority]') AND type in (N'U')) DROP TABLE [dbo].[c_role_authority];
CREATE TABLE [dbo].[c_role_authority](
    id BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,
    authority_type VARCHAR(10) NOT NULL,
    role_id BIGINT NOT NULL,
    create_time DATETIME,
    created_by BIGINT,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '角色的资源', 'SCHEMA', dbo, 'table', c_role_authority, null, null;
EXEC sp_addextendedproperty 'MS_Description', '主键', 'SCHEMA', dbo, 'table', c_role_authority, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '资源id;
#c_resource #c_menu', 'SCHEMA', dbo, 'table', c_role_authority, 'column', authority_id;
EXEC sp_addextendedproperty 'MS_Description', '权限类型;
#AuthorizeType{MENU:菜单;RESOURCE:资源;}', 'SCHEMA', dbo, 'table', c_role_authority, 'column', authority_type;
EXEC sp_addextendedproperty 'MS_Description', '角色id;
#c_role', 'SCHEMA', dbo, 'table', c_role_authority, 'column', role_id;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_role_authority, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', c_role_authority, 'column', created_by;


CREATE UNIQUE INDEX uk_role_authority ON c_role_authority(authority_id,authority_type,role_id);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_role_org]') AND type in (N'U')) DROP TABLE [dbo].[c_role_org];
CREATE TABLE [dbo].[c_role_org](
    id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    org_id BIGINT NOT NULL,
    create_time DATETIME,
    created_by BIGINT,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '角色组织关系', 'SCHEMA', dbo, 'table', c_role_org, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', c_role_org, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '角色;#c_role', 'SCHEMA', dbo, 'table', c_role_org, 'column', role_id;
EXEC sp_addextendedproperty 'MS_Description', '部门;#c_org', 'SCHEMA', dbo, 'table', c_role_org, 'column', org_id;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_role_org, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', c_role_org, 'column', created_by;


CREATE UNIQUE INDEX uk_role_org ON c_role_org(org_id,role_id);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_station]') AND type in (N'U')) DROP TABLE [dbo].[c_station];
CREATE TABLE [dbo].[c_station](
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    org_id BIGINT,
    state BIT DEFAULT  1,
    describe_ VARCHAR(255),
    create_time DATETIME,
    created_by BIGINT,
    update_time DATETIME,
    updated_by BIGINT,
    created_org_id BIGINT,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '岗位', 'SCHEMA', dbo, 'table', c_station, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', c_station, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '名称', 'SCHEMA', dbo, 'table', c_station, 'column', name;
EXEC sp_addextendedproperty 'MS_Description', '组织;#c_org;@Echo(api = ORG_ID_CLASS,  beanClass = Org.class)', 'SCHEMA', dbo, 'table', c_station, 'column', org_id;
EXEC sp_addextendedproperty 'MS_Description', '状态', 'SCHEMA', dbo, 'table', c_station, 'column', state;
EXEC sp_addextendedproperty 'MS_Description', '描述', 'SCHEMA', dbo, 'table', c_station, 'column', describe_;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_station, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', c_station, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', dbo, 'table', c_station, 'column', update_time;
EXEC sp_addextendedproperty 'MS_Description', '修改人', 'SCHEMA', dbo, 'table', c_station, 'column', updated_by;
EXEC sp_addextendedproperty 'MS_Description', '创建者所属机构', 'SCHEMA', dbo, 'table', c_station, 'column', created_org_id;


CREATE UNIQUE INDEX uk_name ON c_station(name);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_user]') AND type in (N'U')) DROP TABLE [dbo].[c_user];
CREATE TABLE [dbo].[c_user](
    id BIGINT NOT NULL,
    account VARCHAR(30) NOT NULL,
    name VARCHAR(50) NOT NULL,
    org_id BIGINT,
    station_id BIGINT,
    readonly BIT NOT NULL DEFAULT  0,
    email VARCHAR(255),
    mobile VARCHAR(20),
    sex VARCHAR(1) DEFAULT  'M',
    state BIT DEFAULT  1,
    avatar VARCHAR(255),
    nation CHAR(2),
    education CHAR(2),
    position_status CHAR(2),
    work_describe VARCHAR(255),
    password_error_last_time DATETIME,
    password_error_num INT DEFAULT  0,
    password_expire_time DATETIME,
    password VARCHAR(64) NOT NULL,
    salt VARCHAR(20) NOT NULL,
    last_login_time DATETIME,
    created_by BIGINT,
    create_time DATETIME,
    updated_by BIGINT,
    update_time DATETIME,
    created_org_id BIGINT,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '用户', 'SCHEMA', dbo, 'table', c_user, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', c_user, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '账号', 'SCHEMA', dbo, 'table', c_user, 'column', account;
EXEC sp_addextendedproperty 'MS_Description', '姓名', 'SCHEMA', dbo, 'table', c_user, 'column', name;
EXEC sp_addextendedproperty 'MS_Description', '组织;#c_org;@Echo(api = ORG_ID_CLASS,  beanClass = Org.class)', 'SCHEMA', dbo, 'table', c_user, 'column', org_id;
EXEC sp_addextendedproperty 'MS_Description', '岗位;#c_station;@Echo(api = STATION_ID_CLASS)', 'SCHEMA', dbo, 'table', c_user, 'column', station_id;
EXEC sp_addextendedproperty 'MS_Description', '内置', 'SCHEMA', dbo, 'table', c_user, 'column', readonly;
EXEC sp_addextendedproperty 'MS_Description', '邮箱', 'SCHEMA', dbo, 'table', c_user, 'column', email;
EXEC sp_addextendedproperty 'MS_Description', '手机', 'SCHEMA', dbo, 'table', c_user, 'column', mobile;
EXEC sp_addextendedproperty 'MS_Description', '性别;#Sex{W:女;M:男;N:未知}', 'SCHEMA', dbo, 'table', c_user, 'column', sex;
EXEC sp_addextendedproperty 'MS_Description', '状态', 'SCHEMA', dbo, 'table', c_user, 'column', state;
EXEC sp_addextendedproperty 'MS_Description', '头像', 'SCHEMA', dbo, 'table', c_user, 'column', avatar;
EXEC sp_addextendedproperty 'MS_Description', '民族;@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.NATION)', 'SCHEMA', dbo, 'table', c_user, 'column', nation;
EXEC sp_addextendedproperty 'MS_Description', '学历;@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.EDUCATION)', 'SCHEMA', dbo, 'table', c_user, 'column', education;
EXEC sp_addextendedproperty 'MS_Description', '职位状态;@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.POSITION_STATUS)', 'SCHEMA', dbo, 'table', c_user, 'column', position_status;
EXEC sp_addextendedproperty 'MS_Description', '工作描述', 'SCHEMA', dbo, 'table', c_user, 'column', work_describe;
EXEC sp_addextendedproperty 'MS_Description', '最后一次输错密码时间', 'SCHEMA', dbo, 'table', c_user, 'column', password_error_last_time;
EXEC sp_addextendedproperty 'MS_Description', '密码错误次数', 'SCHEMA', dbo, 'table', c_user, 'column', password_error_num;
EXEC sp_addextendedproperty 'MS_Description', '密码过期时间', 'SCHEMA', dbo, 'table', c_user, 'column', password_expire_time;
EXEC sp_addextendedproperty 'MS_Description', '密码', 'SCHEMA', dbo, 'table', c_user, 'column', password;
EXEC sp_addextendedproperty 'MS_Description', '密码盐', 'SCHEMA', dbo, 'table', c_user, 'column', salt;
EXEC sp_addextendedproperty 'MS_Description', '最后登录时间', 'SCHEMA', dbo, 'table', c_user, 'column', last_login_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人id', 'SCHEMA', dbo, 'table', c_user, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_user, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '更新人id', 'SCHEMA', dbo, 'table', c_user, 'column', updated_by;
EXEC sp_addextendedproperty 'MS_Description', '更新时间', 'SCHEMA', dbo, 'table', c_user, 'column', update_time;
EXEC sp_addextendedproperty 'MS_Description', '创建者所属机构', 'SCHEMA', dbo, 'table', c_user, 'column', created_org_id;


CREATE UNIQUE INDEX uk_account ON c_user(account);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[c_user_role]') AND type in (N'U')) DROP TABLE [dbo].[c_user_role];
CREATE TABLE [dbo].[c_user_role](
    id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_by BIGINT,
    create_time DATETIME,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '角色分配;账号角色绑定', 'SCHEMA', dbo, 'table', c_user_role, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', c_user_role, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '角色;#c_role', 'SCHEMA', dbo, 'table', c_user_role, 'column', role_id;
EXEC sp_addextendedproperty 'MS_Description', '用户;#c_user', 'SCHEMA', dbo, 'table', c_user_role, 'column', user_id;
EXEC sp_addextendedproperty 'MS_Description', '创建人ID', 'SCHEMA', dbo, 'table', c_user_role, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', c_user_role, 'column', create_time;


CREATE UNIQUE INDEX uk_user_role ON c_user_role(role_id,user_id);

