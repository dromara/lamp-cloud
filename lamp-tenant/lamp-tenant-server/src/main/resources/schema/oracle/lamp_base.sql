CREATE TABLE c_appendix(
    id NUMBER(20) NOT NULL,
    biz_id NUMBER(20) DEFAULT  0 NOT NULL,
    biz_type VARCHAR2(255) NOT NULL,
    file_type VARCHAR2(255),
    bucket VARCHAR2(255),
    path VARCHAR2(255),
    original_file_name VARCHAR2(255),
    content_type VARCHAR2(255),
    size_ NUMBER(20) DEFAULT  0,
    create_time DATE NOT NULL,
    created_by NUMBER(20),
    update_time DATE NOT NULL,
    updated_by NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_appendix IS '业务附件';
COMMENT ON COLUMN c_appendix.id IS 'ID';
COMMENT ON COLUMN c_appendix.biz_id IS '业务id';
COMMENT ON COLUMN c_appendix.biz_type IS '业务类型';
COMMENT ON COLUMN c_appendix.file_type IS '文件类型';
COMMENT ON COLUMN c_appendix.bucket IS '桶';
COMMENT ON COLUMN c_appendix.path IS '文件相对地址';
COMMENT ON COLUMN c_appendix.original_file_name IS '原始文件名';
COMMENT ON COLUMN c_appendix.content_type IS '文件类型';
COMMENT ON COLUMN c_appendix.size_ IS '大小';
COMMENT ON COLUMN c_appendix.create_time IS '创建时间';
COMMENT ON COLUMN c_appendix.created_by IS '创建人';
COMMENT ON COLUMN c_appendix.update_time IS '最后修改时间';
COMMENT ON COLUMN c_appendix.updated_by IS '最后修改人';

CREATE TABLE c_application(
    id NUMBER(20) NOT NULL,
    client_id VARCHAR2(24),
    client_secret VARCHAR2(32),
    website VARCHAR2(100),
    name VARCHAR2(255) NOT NULL,
    icon VARCHAR2(255),
    app_type VARCHAR2(10),
    describe_ VARCHAR2(200),
    state NUMBER(1) DEFAULT  1,
    created_by NUMBER(20),
    create_time DATE,
    updated_by NUMBER(20),
    update_time DATE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_application IS '应用';
COMMENT ON COLUMN c_application.id IS 'ID';
COMMENT ON COLUMN c_application.client_id IS '客户端ID';
COMMENT ON COLUMN c_application.client_secret IS '客户端密码';
COMMENT ON COLUMN c_application.website IS '官网';
COMMENT ON COLUMN c_application.name IS '应用名称';
COMMENT ON COLUMN c_application.icon IS '应用图标';
COMMENT ON COLUMN c_application.app_type IS '类型;
#{SERVER:服务应用;APP:手机应用;PC:PC网页应用;WAP:手机网页应用}';
COMMENT ON COLUMN c_application.describe_ IS '备注';
COMMENT ON COLUMN c_application.state IS '状态';
COMMENT ON COLUMN c_application.created_by IS '创建人id';
COMMENT ON COLUMN c_application.create_time IS '创建时间';
COMMENT ON COLUMN c_application.updated_by IS '更新人id';
COMMENT ON COLUMN c_application.update_time IS '更新时间';


CREATE UNIQUE INDEX uk_client_id ON c_application(client_id);

CREATE TABLE c_area(
    id NUMBER(20) NOT NULL,
    code VARCHAR2(64) NOT NULL,
    label VARCHAR2(255) NOT NULL,
    full_name VARCHAR2(255),
    sort_value NUMBER(10) DEFAULT  1,
    longitude VARCHAR2(255),
    latitude VARCHAR2(255),
    level_ VARCHAR2(10),
    source_ VARCHAR2(255),
    state NUMBER(1) DEFAULT  0,
    parent_id NUMBER(20) DEFAULT  0,
    create_time DATE,
    created_by NUMBER(20),
    update_time DATE,
    updated_by NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_area IS '地区表';
COMMENT ON COLUMN c_area.id IS 'id';
COMMENT ON COLUMN c_area.code IS '编码';
COMMENT ON COLUMN c_area.label IS '名称';
COMMENT ON COLUMN c_area.full_name IS '全名';
COMMENT ON COLUMN c_area.sort_value IS '排序';
COMMENT ON COLUMN c_area.longitude IS '经度';
COMMENT ON COLUMN c_area.latitude IS '维度';
COMMENT ON COLUMN c_area.level_ IS '行政区级;
@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.AREA_LEVEL)';
COMMENT ON COLUMN c_area.source_ IS '数据来源';
COMMENT ON COLUMN c_area.state IS '状态';
COMMENT ON COLUMN c_area.parent_id IS '父ID';
COMMENT ON COLUMN c_area.create_time IS '创建时间';
COMMENT ON COLUMN c_area.created_by IS '创建人';
COMMENT ON COLUMN c_area.update_time IS '更新时间';
COMMENT ON COLUMN c_area.updated_by IS '更新人';


CREATE UNIQUE INDEX uk_area_code ON c_area(code);

CREATE TABLE c_dictionary(
    id NUMBER(20) NOT NULL,
    type VARCHAR2(255) NOT NULL,
    label VARCHAR2(255) NOT NULL,
    code VARCHAR2(64) NOT NULL,
    name VARCHAR2(64) NOT NULL,
    state NUMBER(1) DEFAULT  1,
    describe_ VARCHAR2(255),
    sort_value NUMBER(10) DEFAULT  1,
    icon VARCHAR2(255),
    css_style VARCHAR2(255),
    css_class VARCHAR2(255),
    readonly_ NUMBER(1) DEFAULT  0,
    created_by NUMBER(20),
    create_time DATE,
    updated_by NUMBER(20),
    update_time DATE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_dictionary IS '字典项';
COMMENT ON COLUMN c_dictionary.id IS 'ID';
COMMENT ON COLUMN c_dictionary.type IS '类型';
COMMENT ON COLUMN c_dictionary.label IS '类型标签';
COMMENT ON COLUMN c_dictionary.code IS '编码';
COMMENT ON COLUMN c_dictionary.name IS '名称';
COMMENT ON COLUMN c_dictionary.state IS '状态';
COMMENT ON COLUMN c_dictionary.describe_ IS '描述';
COMMENT ON COLUMN c_dictionary.sort_value IS '排序';
COMMENT ON COLUMN c_dictionary.icon IS '图标';
COMMENT ON COLUMN c_dictionary.css_style IS 'css样式';
COMMENT ON COLUMN c_dictionary.css_class IS 'css;class';
COMMENT ON COLUMN c_dictionary.readonly_ IS '内置';
COMMENT ON COLUMN c_dictionary.created_by IS '创建人id';
COMMENT ON COLUMN c_dictionary.create_time IS '创建时间';
COMMENT ON COLUMN c_dictionary.updated_by IS '更新人id';
COMMENT ON COLUMN c_dictionary.update_time IS '更新时间';


CREATE UNIQUE INDEX uk_type_code ON c_dictionary(type,code);

CREATE TABLE c_file(
    id NUMBER(20) NOT NULL,
    biz_type VARCHAR2(255) NOT NULL,
    file_type VARCHAR2(255),
    storage_type VARCHAR2(255),
    bucket VARCHAR2(255),
    path VARCHAR2(255),
    url VARCHAR2(255),
    unique_file_name VARCHAR2(255),
    file_md5 VARCHAR2(255),
    original_file_name VARCHAR2(255),
    content_type VARCHAR2(255),
    suffix VARCHAR2(255),
    size_ NUMBER(20) DEFAULT  0,
    create_time DATE NOT NULL,
    created_by NUMBER(20) NOT NULL,
    update_time DATE NOT NULL,
    updated_by NUMBER(20) NOT NULL,
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_file IS '增量文件上传日志';
COMMENT ON COLUMN c_file.id IS 'ID';
COMMENT ON COLUMN c_file.biz_type IS '业务类型';
COMMENT ON COLUMN c_file.file_type IS '文件类型';
COMMENT ON COLUMN c_file.storage_type IS '存储类型
LOCAL;FAST_DFS MIN_IO ALI';
COMMENT ON COLUMN c_file.bucket IS '桶';
COMMENT ON COLUMN c_file.path IS '文件相对地址';
COMMENT ON COLUMN c_file.url IS '文件访问地址';
COMMENT ON COLUMN c_file.unique_file_name IS '唯一文件名';
COMMENT ON COLUMN c_file.file_md5 IS '文件md5';
COMMENT ON COLUMN c_file.original_file_name IS '原始文件名';
COMMENT ON COLUMN c_file.content_type IS '文件类型';
COMMENT ON COLUMN c_file.suffix IS '后缀';
COMMENT ON COLUMN c_file.size_ IS '大小';
COMMENT ON COLUMN c_file.create_time IS '创建时间';
COMMENT ON COLUMN c_file.created_by IS '创建人';
COMMENT ON COLUMN c_file.update_time IS '最后修改时间';
COMMENT ON COLUMN c_file.updated_by IS '最后修改人';

CREATE TABLE c_login_log(
    id NUMBER(20) NOT NULL,
    request_ip VARCHAR2(50),
    user_id NUMBER(20),
    user_name VARCHAR2(50),
    account VARCHAR2(30),
    description VARCHAR2(255),
    login_date CHAR(10),
    ua CLOB,
    browser VARCHAR2(255),
    browser_version VARCHAR2(255),
    operating_system VARCHAR2(255),
    location VARCHAR2(255),
    create_time DATE,
    created_by NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_login_log IS '登录日志';
COMMENT ON COLUMN c_login_log.id IS '主键';
COMMENT ON COLUMN c_login_log.request_ip IS '登录IP';
COMMENT ON COLUMN c_login_log.user_id IS '登录人ID';
COMMENT ON COLUMN c_login_log.user_name IS '登录人姓名';
COMMENT ON COLUMN c_login_log.account IS '登录人账号';
COMMENT ON COLUMN c_login_log.description IS '登录描述';
COMMENT ON COLUMN c_login_log.login_date IS '登录时间';
COMMENT ON COLUMN c_login_log.ua IS '浏览器请求头';
COMMENT ON COLUMN c_login_log.browser IS '浏览器名称';
COMMENT ON COLUMN c_login_log.browser_version IS '浏览器版本';
COMMENT ON COLUMN c_login_log.operating_system IS '操作系统';
COMMENT ON COLUMN c_login_log.location IS '登录地点';
COMMENT ON COLUMN c_login_log.create_time IS '创建时间';
COMMENT ON COLUMN c_login_log.created_by IS '创建人';

CREATE TABLE c_menu(
    id NUMBER(20) NOT NULL,
    label VARCHAR2(255) NOT NULL,
    resource_type CHAR(2),
    tree_grade NUMBER(10),
    tree_path VARCHAR2(512),
    describe_ VARCHAR2(200),
    is_general NUMBER(1) DEFAULT  0,
    path VARCHAR2(255),
    component VARCHAR2(255),
    state NUMBER(1) DEFAULT  1,
    sort_value NUMBER(10) DEFAULT  1,
    icon VARCHAR2(255),
    group_ VARCHAR2(20),
    data_scope CHAR(2),
    custom_class VARCHAR2(255),
    is_def NUMBER(1) DEFAULT  0,
    parent_id NUMBER(20) DEFAULT  0,
    readonly_ NUMBER(1) DEFAULT  0,
    created_by NUMBER(20),
    create_time DATE,
    updated_by NUMBER(20),
    update_time DATE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_menu IS '菜单';
COMMENT ON COLUMN c_menu.id IS 'ID';
COMMENT ON COLUMN c_menu.label IS '名称';
COMMENT ON COLUMN c_menu.resource_type IS '资源类型;[20-菜单 60-数据];@Echo(api = DICTIONARY_ITEM_FEIGN_CLASS,dictType = EchoDictType.RESOURCE_TYPE)';
COMMENT ON COLUMN c_menu.tree_grade IS '树层级';
COMMENT ON COLUMN c_menu.tree_path IS '树路径';
COMMENT ON COLUMN c_menu.describe_ IS '描述';
COMMENT ON COLUMN c_menu.is_general IS '通用菜单;True表示无需分配所有人就可以访问的';
COMMENT ON COLUMN c_menu.path IS '路径';
COMMENT ON COLUMN c_menu.component IS '组件';
COMMENT ON COLUMN c_menu.state IS '状态';
COMMENT ON COLUMN c_menu.sort_value IS '排序';
COMMENT ON COLUMN c_menu.icon IS '菜单图标';
COMMENT ON COLUMN c_menu.group_ IS '分组';
COMMENT ON COLUMN c_menu.data_scope IS '数据范围;[01-全部 02-本单位及子级 03-本单位 04-本部门 05-本部门及子级 06-个人 07-自定义]';
COMMENT ON COLUMN c_menu.custom_class IS '实现类';
COMMENT ON COLUMN c_menu.is_def IS '是否默认';
COMMENT ON COLUMN c_menu.parent_id IS '父级菜单ID';
COMMENT ON COLUMN c_menu.readonly_ IS '内置';
COMMENT ON COLUMN c_menu.created_by IS '创建人id';
COMMENT ON COLUMN c_menu.create_time IS '创建时间';
COMMENT ON COLUMN c_menu.updated_by IS '更新人id';
COMMENT ON COLUMN c_menu.update_time IS '更新时间';

CREATE TABLE c_opt_log(
    id NUMBER(20) NOT NULL,
    request_ip VARCHAR2(50),
    type VARCHAR2(5),
    user_name VARCHAR2(50),
    description VARCHAR2(255),
    class_path VARCHAR2(255),
    action_method VARCHAR2(50),
    request_uri VARCHAR2(50),
    http_method VARCHAR2(10),
    start_time DATE,
    finish_time DATE,
    consuming_time NUMBER(20),
    ua VARCHAR2(500),
    create_time DATE,
    created_by NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_opt_log IS '系统日志';
COMMENT ON COLUMN c_opt_log.id IS '主键';
COMMENT ON COLUMN c_opt_log.request_ip IS '操作IP';
COMMENT ON COLUMN c_opt_log.type IS '日志类型;
#LogType{OPT:操作类型;EX:异常类型}';
COMMENT ON COLUMN c_opt_log.user_name IS '操作人';
COMMENT ON COLUMN c_opt_log.description IS '操作描述';
COMMENT ON COLUMN c_opt_log.class_path IS '类路径';
COMMENT ON COLUMN c_opt_log.action_method IS '请求方法';
COMMENT ON COLUMN c_opt_log.request_uri IS '请求地址';
COMMENT ON COLUMN c_opt_log.http_method IS '请求类型;
#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}';
COMMENT ON COLUMN c_opt_log.start_time IS '开始时间';
COMMENT ON COLUMN c_opt_log.finish_time IS '完成时间';
COMMENT ON COLUMN c_opt_log.consuming_time IS '消耗时间';
COMMENT ON COLUMN c_opt_log.ua IS '浏览器';
COMMENT ON COLUMN c_opt_log.create_time IS '创建时间';
COMMENT ON COLUMN c_opt_log.created_by IS '创建人';

CREATE TABLE c_opt_log_ext(
    id NUMBER(20) NOT NULL,
    params CLOB,
    result CLOB,
    ex_detail CLOB,
    create_time DATE,
    created_by NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_opt_log_ext IS '系统日志扩展';
COMMENT ON COLUMN c_opt_log_ext.id IS '主键';
COMMENT ON COLUMN c_opt_log_ext.params IS '请求参数';
COMMENT ON COLUMN c_opt_log_ext.result IS '返回值';
COMMENT ON COLUMN c_opt_log_ext.ex_detail IS '异常描述';
COMMENT ON COLUMN c_opt_log_ext.create_time IS '创建时间';
COMMENT ON COLUMN c_opt_log_ext.created_by IS '创建人';

CREATE TABLE c_org(
    id NUMBER(20) NOT NULL,
    label VARCHAR2(255) NOT NULL,
    type_ CHAR(2),
    abbreviation VARCHAR2(255),
    parent_id NUMBER(20) DEFAULT  0,
    tree_path VARCHAR2(255),
    sort_value NUMBER(10) DEFAULT  1,
    state NUMBER(1) DEFAULT  1,
    describe_ VARCHAR2(255),
    create_time DATE,
    created_by NUMBER(20),
    update_time DATE,
    updated_by NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_org IS '组织';
COMMENT ON COLUMN c_org.id IS 'ID';
COMMENT ON COLUMN c_org.label IS '名称';
COMMENT ON COLUMN c_org.type_ IS '类型;
@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.ORG_TYPE)';
COMMENT ON COLUMN c_org.abbreviation IS '简称';
COMMENT ON COLUMN c_org.parent_id IS '父ID';
COMMENT ON COLUMN c_org.tree_path IS '树结构';
COMMENT ON COLUMN c_org.sort_value IS '排序';
COMMENT ON COLUMN c_org.state IS '状态';
COMMENT ON COLUMN c_org.describe_ IS '描述';
COMMENT ON COLUMN c_org.create_time IS '创建时间';
COMMENT ON COLUMN c_org.created_by IS '创建人';
COMMENT ON COLUMN c_org.update_time IS '修改时间';
COMMENT ON COLUMN c_org.updated_by IS '修改人';


CREATE UNIQUE INDEX uk_org_name ON c_org(label);
CREATE INDEX fu_org_path ON c_org(tree_path);

CREATE TABLE c_parameter(
    id NUMBER(20) NOT NULL,
    key_ VARCHAR2(255) NOT NULL,
    value VARCHAR2(255) NOT NULL,
    name VARCHAR2(255) NOT NULL,
    describe_ VARCHAR2(255),
    state NUMBER(1) DEFAULT  1,
    readonly_ NUMBER(1) DEFAULT  0,
    created_by NUMBER(20),
    create_time DATE,
    updated_by NUMBER(20),
    update_time DATE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_parameter IS '参数配置';
COMMENT ON COLUMN c_parameter.id IS 'ID';
COMMENT ON COLUMN c_parameter.key_ IS '参数键';
COMMENT ON COLUMN c_parameter.value IS '参数值';
COMMENT ON COLUMN c_parameter.name IS '参数名称';
COMMENT ON COLUMN c_parameter.describe_ IS '描述';
COMMENT ON COLUMN c_parameter.state IS '状态';
COMMENT ON COLUMN c_parameter.readonly_ IS '内置';
COMMENT ON COLUMN c_parameter.created_by IS '创建人id';
COMMENT ON COLUMN c_parameter.create_time IS '创建时间';
COMMENT ON COLUMN c_parameter.updated_by IS '更新人id';
COMMENT ON COLUMN c_parameter.update_time IS '更新时间';


CREATE UNIQUE INDEX uk_param_key ON c_parameter(key_);

CREATE TABLE c_resource(
    id NUMBER(20) NOT NULL,
    code VARCHAR2(500),
    name VARCHAR2(255) NOT NULL,
    menu_id NUMBER(20),
    describe_ VARCHAR2(255),
    readonly_ NUMBER(1) DEFAULT  1,
    created_by NUMBER(20),
    create_time DATE,
    updated_by NUMBER(20),
    update_time DATE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_resource IS '资源';
COMMENT ON COLUMN c_resource.id IS 'ID';
COMMENT ON COLUMN c_resource.code IS '编码';
COMMENT ON COLUMN c_resource.name IS '名称';
COMMENT ON COLUMN c_resource.menu_id IS '菜单;#c_menu';
COMMENT ON COLUMN c_resource.describe_ IS '描述';
COMMENT ON COLUMN c_resource.readonly_ IS '内置';
COMMENT ON COLUMN c_resource.created_by IS '创建人id';
COMMENT ON COLUMN c_resource.create_time IS '创建时间';
COMMENT ON COLUMN c_resource.updated_by IS '更新人id';
COMMENT ON COLUMN c_resource.update_time IS '更新时间';


CREATE UNIQUE INDEX uk_res_code ON c_resource(code);

CREATE TABLE c_role(
    id NUMBER(20) NOT NULL,
    category CHAR(2),
    name VARCHAR2(30) NOT NULL,
    code VARCHAR2(20),
    describe_ VARCHAR2(100),
    state NUMBER(1) DEFAULT  1,
    readonly_ NUMBER(1) DEFAULT  0,
    created_by NUMBER(20),
    create_time DATE,
    updated_by NUMBER(20),
    update_time DATE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_role IS '角色';
COMMENT ON COLUMN c_role.id IS 'ID';
COMMENT ON COLUMN c_role.category IS '角色类别;[10-功能角色 20-桌面角色 30-数据角色]';
COMMENT ON COLUMN c_role.name IS '名称';
COMMENT ON COLUMN c_role.code IS '编码';
COMMENT ON COLUMN c_role.describe_ IS '描述';
COMMENT ON COLUMN c_role.state IS '状态';
COMMENT ON COLUMN c_role.readonly_ IS '内置角色';
COMMENT ON COLUMN c_role.created_by IS '创建人id';
COMMENT ON COLUMN c_role.create_time IS '创建时间';
COMMENT ON COLUMN c_role.updated_by IS '更新人id';
COMMENT ON COLUMN c_role.update_time IS '更新时间';


CREATE UNIQUE INDEX uk_role_code ON c_role(code);

CREATE TABLE c_role_authority(
    id NUMBER(20) NOT NULL,
    authority_id NUMBER(20) NOT NULL,
    authority_type VARCHAR2(10) NOT NULL,
    role_id NUMBER(20) NOT NULL,
    create_time DATE,
    created_by NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_role_authority IS '角色的资源';
COMMENT ON COLUMN c_role_authority.id IS '主键';
COMMENT ON COLUMN c_role_authority.authority_id IS '资源id;
#c_resource #c_menu';
COMMENT ON COLUMN c_role_authority.authority_type IS '权限类型;
#AuthorizeType{MENU:菜单;RESOURCE:资源;}';
COMMENT ON COLUMN c_role_authority.role_id IS '角色id;
#c_role';
COMMENT ON COLUMN c_role_authority.create_time IS '创建时间';
COMMENT ON COLUMN c_role_authority.created_by IS '创建人';


CREATE UNIQUE INDEX uk_role_authority ON c_role_authority(authority_id,authority_type,role_id);

CREATE TABLE c_role_org(
    id NUMBER(20) NOT NULL,
    role_id NUMBER(20) NOT NULL,
    org_id NUMBER(20) NOT NULL,
    create_time DATE,
    created_by NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_role_org IS '角色组织关系';
COMMENT ON COLUMN c_role_org.id IS 'ID';
COMMENT ON COLUMN c_role_org.role_id IS '角色;#c_role';
COMMENT ON COLUMN c_role_org.org_id IS '部门;#c_org';
COMMENT ON COLUMN c_role_org.create_time IS '创建时间';
COMMENT ON COLUMN c_role_org.created_by IS '创建人';


CREATE UNIQUE INDEX uk_role_org ON c_role_org(org_id,role_id);

CREATE TABLE c_station(
    id NUMBER(20) NOT NULL,
    name VARCHAR2(255) NOT NULL,
    org_id NUMBER(20),
    state NUMBER(1) DEFAULT  1,
    describe_ VARCHAR2(255),
    create_time DATE,
    created_by NUMBER(20),
    update_time DATE,
    updated_by NUMBER(20),
    created_org_id NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_station IS '岗位';
COMMENT ON COLUMN c_station.id IS 'ID';
COMMENT ON COLUMN c_station.name IS '名称';
COMMENT ON COLUMN c_station.org_id IS '组织;#c_org;@Echo(api = ORG_ID_CLASS,  beanClass = Org.class)';
COMMENT ON COLUMN c_station.state IS '状态';
COMMENT ON COLUMN c_station.describe_ IS '描述';
COMMENT ON COLUMN c_station.create_time IS '创建时间';
COMMENT ON COLUMN c_station.created_by IS '创建人';
COMMENT ON COLUMN c_station.update_time IS '修改时间';
COMMENT ON COLUMN c_station.updated_by IS '修改人';
COMMENT ON COLUMN c_station.created_org_id IS '创建者所属机构';


CREATE UNIQUE INDEX uk_station_name ON c_station(name);

CREATE TABLE c_user(
    id NUMBER(20) NOT NULL,
    account VARCHAR2(30) NOT NULL,
    name VARCHAR2(50) NOT NULL,
    org_id NUMBER(20),
    station_id NUMBER(20),
    readonly NUMBER(1) DEFAULT  0 NOT NULL,
    email VARCHAR2(255),
    mobile VARCHAR2(20),
    sex VARCHAR2(1) DEFAULT  'M',
    state NUMBER(1) DEFAULT  1,
    avatar VARCHAR2(255),
    nation CHAR(2),
    education CHAR(2),
    position_status CHAR(2),
    work_describe VARCHAR2(255),
    password_error_last_time DATE,
    password_error_num NUMBER(10) DEFAULT  0,
    password_expire_time DATE,
    password VARCHAR2(64) NOT NULL,
    salt VARCHAR2(20) NOT NULL,
    last_login_time DATE,
    created_by NUMBER(20),
    create_time DATE,
    updated_by NUMBER(20),
    update_time DATE,
    created_org_id NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_user IS '用户';
COMMENT ON COLUMN c_user.id IS 'ID';
COMMENT ON COLUMN c_user.account IS '账号';
COMMENT ON COLUMN c_user.name IS '姓名';
COMMENT ON COLUMN c_user.org_id IS '组织;#c_org;@Echo(api = ORG_ID_CLASS,  beanClass = Org.class)';
COMMENT ON COLUMN c_user.station_id IS '岗位;#c_station;@Echo(api = STATION_ID_CLASS)';
COMMENT ON COLUMN c_user.readonly IS '内置';
COMMENT ON COLUMN c_user.email IS '邮箱';
COMMENT ON COLUMN c_user.mobile IS '手机';
COMMENT ON COLUMN c_user.sex IS '性别;
#Sex{W:女;M:男;N:未知}';
COMMENT ON COLUMN c_user.state IS '状态';
COMMENT ON COLUMN c_user.avatar IS '头像';
COMMENT ON COLUMN c_user.nation IS '民族;
@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.NATION)';
COMMENT ON COLUMN c_user.education IS '学历;
@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.EDUCATION)';
COMMENT ON COLUMN c_user.position_status IS '职位状态;
@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.POSITION_STATUS)';
COMMENT ON COLUMN c_user.work_describe IS '工作描述';
COMMENT ON COLUMN c_user.password_error_last_time IS '最后一次输错密码时间';
COMMENT ON COLUMN c_user.password_error_num IS '密码错误次数';
COMMENT ON COLUMN c_user.password_expire_time IS '密码过期时间';
COMMENT ON COLUMN c_user.password IS '密码';
COMMENT ON COLUMN c_user.salt IS '密码盐';
COMMENT ON COLUMN c_user.last_login_time IS '最后登录时间';
COMMENT ON COLUMN c_user.created_by IS '创建人id';
COMMENT ON COLUMN c_user.create_time IS '创建时间';
COMMENT ON COLUMN c_user.updated_by IS '更新人id';
COMMENT ON COLUMN c_user.update_time IS '更新时间';
COMMENT ON COLUMN c_user.created_org_id IS '创建者所属机构';


CREATE UNIQUE INDEX uk_user_account ON c_user(account);

CREATE TABLE c_user_role(
    id NUMBER(20) NOT NULL,
    role_id NUMBER(20) NOT NULL,
    user_id NUMBER(20) NOT NULL,
    created_by NUMBER(20),
    create_time DATE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE c_user_role IS '角色分配;账号角色绑定';
COMMENT ON COLUMN c_user_role.id IS 'ID';
COMMENT ON COLUMN c_user_role.role_id IS '角色;#c_role';
COMMENT ON COLUMN c_user_role.user_id IS '用户;#c_user';
COMMENT ON COLUMN c_user_role.created_by IS '创建人ID';
COMMENT ON COLUMN c_user_role.create_time IS '创建时间';


CREATE UNIQUE INDEX uk_user_role ON c_user_role(role_id,user_id);

