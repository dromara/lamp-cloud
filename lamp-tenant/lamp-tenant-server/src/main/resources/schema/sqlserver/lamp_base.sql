/*
 Navicat Premium Data Transfer

 Source Server         : 172.26.3.67
 Source Server Type    : SQL Server
 Source Server Version : 15004236
 Source Host           : 117.187.194.249:1433
 Source Catalog        : lamp_base_1
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15004236
 File Encoding         : 65001

 Date: 22/08/2022 23:07:25
*/


-- ----------------------------
-- Table structure for base_dict
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_dict]') AND type IN ('U'))
	DROP TABLE [dbo].[base_dict]
GO

CREATE TABLE [dbo].[base_dict] (
  [id] bigint  NOT NULL,
  [parent_id] bigint  NOT NULL,
  [parent_key] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [key_] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [classify] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('20') NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [state] bit DEFAULT ((1)) NULL,
  [remark] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [sort_value] int DEFAULT ((1)) NULL,
  [icon] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [css_style] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [css_class] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[base_dict] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属字典',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属字典标识',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'parent_key'
GO

EXEC sp_addextendedproperty
'MS_Description', N'标识',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'key_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'分类;[10-系统字典 20-业务字典]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Tenant.DICT_CLASSIFY)',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'classify'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'remark'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'图标',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'icon'
GO

EXEC sp_addextendedproperty
'MS_Description', N'css样式',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'css_style'
GO

EXEC sp_addextendedproperty
'MS_Description', N'css类元素',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'css_class'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'base_dict',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'字典',
'SCHEMA', N'dbo',
'TABLE', N'base_dict'
GO


-- ----------------------------
-- Table structure for base_employee
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_employee]') AND type IN ('U'))
	DROP TABLE [dbo].[base_employee]
GO

CREATE TABLE [dbo].[base_employee] (
  [id] bigint  NOT NULL,
  [is_default] bit DEFAULT ((0)) NOT NULL,
  [position_id] bigint  NULL,
  [user_id] bigint  NOT NULL,
  [main_org_id] bigint  NULL,
  [real_name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [active_status] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('20') NULL,
  [position_status] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('10') NULL,
  [state] bit DEFAULT ((1)) NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[base_employee] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否默认员工;[0-否 1-是]',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'is_default'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属岗位',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'position_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'user_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属主机构',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'main_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'真实姓名',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'real_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'激活状态;[10-未激活 20-已激活]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.ACTIVE_STATUS)',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'active_status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'职位状态;[10-在职 20-离职]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.POSITION_STATUS)',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'position_status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态;[0-禁用 1-启用]',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新人',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新时间',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'员工',
'SCHEMA', N'dbo',
'TABLE', N'base_employee'
GO


-- ----------------------------
-- Table structure for base_employee_org_rel
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_employee_org_rel]') AND type IN ('U'))
	DROP TABLE [dbo].[base_employee_org_rel]
GO

CREATE TABLE [dbo].[base_employee_org_rel] (
  [id] bigint  NOT NULL,
  [org_id] bigint  NOT NULL,
  [employee_id] bigint  NOT NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[base_employee_org_rel] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_org_rel',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'关联机构',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_org_rel',
'COLUMN', N'org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'关联员工',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_org_rel',
'COLUMN', N'employee_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_org_rel',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_org_rel',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新人',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_org_rel',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新时间',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_org_rel',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_org_rel',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'员工所在部门',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_org_rel'
GO


-- ----------------------------
-- Table structure for base_employee_role_rel
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_employee_role_rel]') AND type IN ('U'))
	DROP TABLE [dbo].[base_employee_role_rel]
GO

CREATE TABLE [dbo].[base_employee_role_rel] (
  [id] bigint  NOT NULL,
  [role_id] bigint  NOT NULL,
  [employee_id] bigint  NOT NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[base_employee_role_rel] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_role_rel',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'拥有角色;#base_role',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_role_rel',
'COLUMN', N'role_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属员工;#base_employee',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_role_rel',
'COLUMN', N'employee_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_role_rel',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_role_rel',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新人',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_role_rel',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新时间',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_role_rel',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_role_rel',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'员工的角色',
'SCHEMA', N'dbo',
'TABLE', N'base_employee_role_rel'
GO


-- ----------------------------
-- Table structure for base_operation_log
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_operation_log]') AND type IN ('U'))
	DROP TABLE [dbo].[base_operation_log]
GO

CREATE TABLE [dbo].[base_operation_log] (
  [id] bigint  NOT NULL,
  [request_ip] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [type] varchar(5) COLLATE Chinese_PRC_CI_AS DEFAULT ('OPT') NULL,
  [user_name] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [description] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [class_path] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [action_method] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [request_uri] varchar(500) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [http_method] varchar(10) COLLATE Chinese_PRC_CI_AS DEFAULT ('GET') NULL,
  [start_time] datetime  NULL,
  [finish_time] datetime  NULL,
  [consuming_time] bigint DEFAULT ((0)) NULL,
  [ua] varchar(500) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [created_time] datetime  NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[base_operation_log] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'操作IP',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'request_ip'
GO

EXEC sp_addextendedproperty
'MS_Description', N'日志类型;#LogType{OPT:操作类型;EX:异常类型}',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'操作人',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'user_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'操作描述',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类路径',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'class_path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求方法',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'action_method'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求地址',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'request_uri'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求类型;#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'http_method'
GO

EXEC sp_addextendedproperty
'MS_Description', N'开始时间',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'start_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'完成时间',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'finish_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消耗时间',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'consuming_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'浏览器',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'ua'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新时间',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新人',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'操作日志',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log'
GO


-- ----------------------------
-- Table structure for base_operation_log_ext
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_operation_log_ext]') AND type IN ('U'))
	DROP TABLE [dbo].[base_operation_log_ext]
GO

CREATE TABLE [dbo].[base_operation_log_ext] (
  [id] bigint  NOT NULL,
  [params] text COLLATE Chinese_PRC_CI_AS  NULL,
  [result] text COLLATE Chinese_PRC_CI_AS  NULL,
  [ex_detail] text COLLATE Chinese_PRC_CI_AS  NULL,
  [created_time] datetime  NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[base_operation_log_ext] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log_ext',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求参数',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log_ext',
'COLUMN', N'params'
GO

EXEC sp_addextendedproperty
'MS_Description', N'返回值',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log_ext',
'COLUMN', N'result'
GO

EXEC sp_addextendedproperty
'MS_Description', N'异常描述',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log_ext',
'COLUMN', N'ex_detail'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log_ext',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log_ext',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新时间',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log_ext',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新人ID',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log_ext',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log_ext',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'操作日志扩展',
'SCHEMA', N'dbo',
'TABLE', N'base_operation_log_ext'
GO


-- ----------------------------
-- Table structure for base_org
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_org]') AND type IN ('U'))
	DROP TABLE [dbo].[base_org]
GO

CREATE TABLE [dbo].[base_org] (
  [id] bigint  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [type_] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('10') NULL,
  [short_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [parent_id] bigint  NULL,
  [tree_grade] int DEFAULT ((0)) NULL,
  [tree_path] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [sort_value] int DEFAULT ((1)) NULL,
  [state] bit DEFAULT ((1)) NULL,
  [remarks] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [created_time] datetime  NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[base_org] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类型;[10-单位 20-部门]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.ORG_TYPE)',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'type_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'简称',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'short_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'父组织',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'树层级',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'tree_grade'
GO

EXEC sp_addextendedproperty
'MS_Description', N'树路径;用id拼接树结构',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'tree_path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态;[0-禁用 1-启用]',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'remarks'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'base_org',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'组织',
'SCHEMA', N'dbo',
'TABLE', N'base_org'
GO


-- ----------------------------
-- Table structure for base_org_role_rel
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_org_role_rel]') AND type IN ('U'))
	DROP TABLE [dbo].[base_org_role_rel]
GO

CREATE TABLE [dbo].[base_org_role_rel] (
  [id] bigint  NOT NULL,
  [org_id] bigint  NOT NULL,
  [role_id] bigint  NOT NULL,
  [created_time] datetime  NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[base_org_role_rel] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'base_org_role_rel',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属部门;#base_org',
'SCHEMA', N'dbo',
'TABLE', N'base_org_role_rel',
'COLUMN', N'org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'拥有角色;#base_role',
'SCHEMA', N'dbo',
'TABLE', N'base_org_role_rel',
'COLUMN', N'role_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_org_role_rel',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'base_org_role_rel',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新时间',
'SCHEMA', N'dbo',
'TABLE', N'base_org_role_rel',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新人',
'SCHEMA', N'dbo',
'TABLE', N'base_org_role_rel',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'base_org_role_rel',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'组织的角色',
'SCHEMA', N'dbo',
'TABLE', N'base_org_role_rel'
GO


-- ----------------------------
-- Table structure for base_parameter
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_parameter]') AND type IN ('U'))
	DROP TABLE [dbo].[base_parameter]
GO

CREATE TABLE [dbo].[base_parameter] (
  [id] bigint  NOT NULL,
  [key_] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [value] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [remarks] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [state] bit DEFAULT ((1)) NULL,
  [param_type] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('20') NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[base_parameter] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'base_parameter',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数键',
'SCHEMA', N'dbo',
'TABLE', N'base_parameter',
'COLUMN', N'key_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数值',
'SCHEMA', N'dbo',
'TABLE', N'base_parameter',
'COLUMN', N'value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数名称',
'SCHEMA', N'dbo',
'TABLE', N'base_parameter',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'base_parameter',
'COLUMN', N'remarks'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'base_parameter',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类型;[10-系统参数 20-业务参数]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Tenant.PARAMETER_TYPE)',
'SCHEMA', N'dbo',
'TABLE', N'base_parameter',
'COLUMN', N'param_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'base_parameter',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_parameter',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人id',
'SCHEMA', N'dbo',
'TABLE', N'base_parameter',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'base_parameter',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'base_parameter',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'个性参数',
'SCHEMA', N'dbo',
'TABLE', N'base_parameter'
GO


-- ----------------------------
-- Table structure for base_position
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_position]') AND type IN ('U'))
	DROP TABLE [dbo].[base_position]
GO

CREATE TABLE [dbo].[base_position] (
  [id] bigint  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [org_id] bigint  NULL,
  [state] bit DEFAULT ((1)) NULL,
  [remarks] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [created_time] datetime  NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[base_position] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'base_position',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'base_position',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属组织;#base_org@Echo(api = EchoApi.ORG_ID_CLASS)',
'SCHEMA', N'dbo',
'TABLE', N'base_position',
'COLUMN', N'org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态;0-禁用 1-启用',
'SCHEMA', N'dbo',
'TABLE', N'base_position',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'base_position',
'COLUMN', N'remarks'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_position',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'base_position',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'base_position',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'base_position',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'base_position',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'岗位',
'SCHEMA', N'dbo',
'TABLE', N'base_position'
GO


-- ----------------------------
-- Table structure for base_role
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_role]') AND type IN ('U'))
	DROP TABLE [dbo].[base_role]
GO

CREATE TABLE [dbo].[base_role] (
  [id] bigint  NOT NULL,
  [category] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('10') NOT NULL,
  [type_] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('20') NOT NULL,
  [name] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [code] varchar(20) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [remarks] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [state] bit DEFAULT ((1)) NULL,
  [readonly_] bit DEFAULT ((0)) NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[base_role] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'base_role',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色类别;[10-功能角色 20-桌面角色 30-数据角色]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.ROLE_CATEGORY)',
'SCHEMA', N'dbo',
'TABLE', N'base_role',
'COLUMN', N'category'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色类型;[10-系统角色 20-自定义角色]; 
@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.DATA_TYPE)',
'SCHEMA', N'dbo',
'TABLE', N'base_role',
'COLUMN', N'type_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'base_role',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编码',
'SCHEMA', N'dbo',
'TABLE', N'base_role',
'COLUMN', N'code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'base_role',
'COLUMN', N'remarks'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'base_role',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'内置角色',
'SCHEMA', N'dbo',
'TABLE', N'base_role',
'COLUMN', N'readonly_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'base_role',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_role',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人',
'SCHEMA', N'dbo',
'TABLE', N'base_role',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'base_role',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'base_role',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色',
'SCHEMA', N'dbo',
'TABLE', N'base_role'
GO


-- ----------------------------
-- Table structure for base_role_resource_rel
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_role_resource_rel]') AND type IN ('U'))
	DROP TABLE [dbo].[base_role_resource_rel]
GO

CREATE TABLE [dbo].[base_role_resource_rel] (
  [id] bigint  NOT NULL,
  [resource_id] bigint  NOT NULL,
  [application_id] bigint  NOT NULL,
  [role_id] bigint  NOT NULL,
  [created_time] datetime  NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[base_role_resource_rel] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'base_role_resource_rel',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'拥有资源;#def_resource',
'SCHEMA', N'dbo',
'TABLE', N'base_role_resource_rel',
'COLUMN', N'resource_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属应用;#def_application',
'SCHEMA', N'dbo',
'TABLE', N'base_role_resource_rel',
'COLUMN', N'application_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属角色;#base_role',
'SCHEMA', N'dbo',
'TABLE', N'base_role_resource_rel',
'COLUMN', N'role_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_role_resource_rel',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'base_role_resource_rel',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新时间',
'SCHEMA', N'dbo',
'TABLE', N'base_role_resource_rel',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新人',
'SCHEMA', N'dbo',
'TABLE', N'base_role_resource_rel',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'base_role_resource_rel',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色的资源',
'SCHEMA', N'dbo',
'TABLE', N'base_role_resource_rel'
GO


-- ----------------------------
-- Table structure for com_appendix
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[com_appendix]') AND type IN ('U'))
	DROP TABLE [dbo].[com_appendix]
GO

CREATE TABLE [dbo].[com_appendix] (
  [id] bigint  NOT NULL,
  [biz_id] bigint  NOT NULL,
  [biz_type] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [file_type] varchar(10) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [bucket] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [path] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [original_file_name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [content_type] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [created_time] datetime  NOT NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NOT NULL,
  [updated_by] bigint  NULL,
  [created_org_id] bigint  NULL,
  [size_] bigint DEFAULT ((0)) NULL
)
GO

ALTER TABLE [dbo].[com_appendix] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务id',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'biz_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务类型;同一个业务，不同的字段，需要分别设置不同的业务类型',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'biz_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件类型;#FileType{IMAGE:图片;VIDEO:视频;AUDIO:音频;DOC:文档;OTHER:其他;}',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'file_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'桶',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'bucket'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件相对地址',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'原始文件名',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'original_file_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件类型',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'content_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'大小',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix',
'COLUMN', N'size_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务附件',
'SCHEMA', N'dbo',
'TABLE', N'com_appendix'
GO


-- ----------------------------
-- Table structure for com_file
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[com_file]') AND type IN ('U'))
	DROP TABLE [dbo].[com_file]
GO

CREATE TABLE [dbo].[com_file] (
  [id] bigint  NOT NULL,
  [biz_type] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [file_type] varchar(10) COLLATE Chinese_PRC_CI_AS DEFAULT ('OTHER') NULL,
  [storage_type] varchar(30) COLLATE Chinese_PRC_CI_AS DEFAULT ('LOCAL') NULL,
  [bucket] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [path] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [url] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [unique_file_name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [file_md5] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [original_file_name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [content_type] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [suffix] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [created_time] datetime  NOT NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NOT NULL,
  [updated_by] bigint  NULL,
  [created_org_id] bigint  NULL,
  [size_] bigint DEFAULT ((0)) NULL
)
GO

ALTER TABLE [dbo].[com_file] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务类型;同一个业务，不同的字段，需要分别设置不同的业务类型',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'biz_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件类型;#FileType{IMAGE:图片;VIDEO:视频;AUDIO:音频;DOC:文档;OTHER:其他;}',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'file_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'存储类型;#FileStorageType{LOCAL:本地;FAST_DFS:FastDFS;MIN_IO:MinIO;ALI_OSS:阿里云OSS;QINIU_OSS:七牛云OSS;HUAWEI_OSS:华为云OSS;}',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'storage_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'桶',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'bucket'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件相对地址',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件访问地址',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'唯一文件名',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'unique_file_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件md5',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'file_md5'
GO

EXEC sp_addextendedproperty
'MS_Description', N'原始文件名',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'original_file_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件类型',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'content_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'后缀',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'suffix'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人组织',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'大小',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'size_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'增量文件上传日志',
'SCHEMA', N'dbo',
'TABLE', N'com_file'
GO


-- ----------------------------
-- Table structure for extend_interface_log
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[extend_interface_log]') AND type IN ('U'))
	DROP TABLE [dbo].[extend_interface_log]
GO

CREATE TABLE [dbo].[extend_interface_log] (
  [id] bigint  NOT NULL,
  [interface_id] bigint  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [success_count] int DEFAULT ((0)) NULL,
  [fail_count] int DEFAULT ((0)) NULL,
  [last_exec_time] datetime  NULL,
  [created_time] datetime  NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[extend_interface_log] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口ID;
#extend_interface',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_log',
'COLUMN', N'interface_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口名称',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_log',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'成功次数',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_log',
'COLUMN', N'success_count'
GO

EXEC sp_addextendedproperty
'MS_Description', N'失败次数',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_log',
'COLUMN', N'fail_count'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后执行时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_log',
'COLUMN', N'last_exec_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_log',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_log',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_log',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_log',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口执行日志',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_log'
GO


-- ----------------------------
-- Table structure for extend_interface_logging
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[extend_interface_logging]') AND type IN ('U'))
	DROP TABLE [dbo].[extend_interface_logging]
GO

CREATE TABLE [dbo].[extend_interface_logging] (
  [id] bigint  NOT NULL,
  [log_id] bigint  NOT NULL,
  [exec_time] datetime  NOT NULL,
  [status] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('01') NULL,
  [params] text COLLATE Chinese_PRC_CI_AS  NULL,
  [result] text COLLATE Chinese_PRC_CI_AS  NULL,
  [error_msg] text COLLATE Chinese_PRC_CI_AS  NULL,
  [biz_id] bigint  NULL,
  [created_time] datetime  NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[extend_interface_logging] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口日志ID;
#extend_interface_log',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_logging',
'COLUMN', N'log_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'执行时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_logging',
'COLUMN', N'exec_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'执行状态;[01-初始化 02-成功 03-失败]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_INTERFACE_LOGGING_STATUS)',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_logging',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求参数',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_logging',
'COLUMN', N'params'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口返回',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_logging',
'COLUMN', N'result'
GO

EXEC sp_addextendedproperty
'MS_Description', N'异常信息',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_logging',
'COLUMN', N'error_msg'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务ID',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_logging',
'COLUMN', N'biz_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_logging',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_logging',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_logging',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_logging',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口执行日志记录',
'SCHEMA', N'dbo',
'TABLE', N'extend_interface_logging'
GO


-- ----------------------------
-- Table structure for extend_msg
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[extend_msg]') AND type IN ('U'))
	DROP TABLE [dbo].[extend_msg]
GO

CREATE TABLE [dbo].[extend_msg] (
  [id] bigint  NOT NULL,
  [template_code] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [type] char(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [status] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [channel] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [param] text COLLATE Chinese_PRC_CI_AS  NULL,
  [title] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [content] text COLLATE Chinese_PRC_CI_AS  NULL,
  [send_time] datetime  NULL,
  [biz_id] bigint  NULL,
  [biz_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [remind_mode] char(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [author] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[extend_msg] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'短信记录ID',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消息模板;
#extend_msg_template',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'template_code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消息类型;[01-短信 02-邮件 03-站内信];@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'执行状态;
#TaskStatus{DRAFT:草稿;WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发送渠道;
#SourceType{APP:应用;SERVICE:服务}',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'channel'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数;需要封装为[{‘key’:‘‘,;’value’:‘‘}, {’key2’:‘‘, ’value2’:‘‘}]格式',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'param'
GO

EXEC sp_addextendedproperty
'MS_Description', N'标题',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'title'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发送内容',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'content'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发送时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'send_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务ID',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'biz_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务类型',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'biz_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'提醒方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)[01-待办 02-预警 03-提醒]',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'remind_mode'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发布人姓名',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'author'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人ID',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人所属机构',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消息',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg'
GO


-- ----------------------------
-- Table structure for extend_msg_recipient
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[extend_msg_recipient]') AND type IN ('U'))
	DROP TABLE [dbo].[extend_msg_recipient]
GO

CREATE TABLE [dbo].[extend_msg_recipient] (
  [id] bigint  NOT NULL,
  [msg_id] bigint  NOT NULL,
  [recipient] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [ext] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[extend_msg_recipient] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_recipient',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'任务ID;
#extend_msg',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_recipient',
'COLUMN', N'msg_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接收人;
可能是手机号、邮箱、用户ID等',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_recipient',
'COLUMN', N'recipient'
GO

EXEC sp_addextendedproperty
'MS_Description', N'扩展信息',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_recipient',
'COLUMN', N'ext'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_recipient',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_recipient',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_recipient',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_recipient',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消息接收人',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_recipient'
GO


-- ----------------------------
-- Table structure for extend_msg_template
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[extend_msg_template]') AND type IN ('U'))
	DROP TABLE [dbo].[extend_msg_template]
GO

CREATE TABLE [dbo].[extend_msg_template] (
  [id] bigint  NOT NULL,
  [interface_id] bigint  NOT NULL,
  [type] char(2) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [code] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [state] bit  NULL,
  [template_code] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [sign] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [title] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [content] text COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [script] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [param] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [remarks] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [target_] char(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [auto_read] bit DEFAULT ((1)) NULL,
  [remind_mode] char(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [url] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[extend_msg_template] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板ID',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口ID',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'interface_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消息类型;[01-短信 02-邮件 03-站内信];@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板标识',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板名称',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板编码',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'template_code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'签名',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'sign'
GO

EXEC sp_addextendedproperty
'MS_Description', N'标题',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'title'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板内容',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'content'
GO

EXEC sp_addextendedproperty
'MS_Description', N'脚本',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'script'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板参数',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'param'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'remarks'
GO

EXEC sp_addextendedproperty
'MS_Description', N'打开方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)[01-页面 02-弹窗 03-新开窗口]',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'target_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'自动已读',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'auto_read'
GO

EXEC sp_addextendedproperty
'MS_Description', N'提醒方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)[01-待办 02-预警 03-提醒]',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'remind_mode'
GO

EXEC sp_addextendedproperty
'MS_Description', N'跳转地址',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人ID',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消息模板',
'SCHEMA', N'dbo',
'TABLE', N'extend_msg_template'
GO


-- ----------------------------
-- Table structure for extend_notice
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[extend_notice]') AND type IN ('U'))
	DROP TABLE [dbo].[extend_notice]
GO

CREATE TABLE [dbo].[extend_notice] (
  [id] bigint  NOT NULL,
  [msg_id] bigint  NULL,
  [biz_id] varchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [biz_type] varchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [recipient_id] bigint  NOT NULL,
  [remind_mode] char(2) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [title] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [content] text COLLATE Chinese_PRC_CI_AS  NULL,
  [author] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [url] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [target_] char(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [auto_read] bit  NULL,
  [handle_time] datetime  NULL,
  [read_time] datetime  NULL,
  [is_read] bit DEFAULT ((0)) NULL,
  [is_handle] bit DEFAULT ((0)) NULL,
  [created_time] datetime  NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[extend_notice] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消息ID',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'msg_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务ID',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'biz_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务类型',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'biz_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接收人',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'recipient_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'提醒方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)[01-待办 02-预警 03-提醒]',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'remind_mode'
GO

EXEC sp_addextendedproperty
'MS_Description', N'标题',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'title'
GO

EXEC sp_addextendedproperty
'MS_Description', N'内容',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'content'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发布人',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'author'
GO

EXEC sp_addextendedproperty
'MS_Description', N'处理地址',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'打开方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)[01-页面 02-弹窗 03-新开窗口]',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'target_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'自动已读',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'auto_read'
GO

EXEC sp_addextendedproperty
'MS_Description', N'处理时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'handle_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'读取时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'read_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否已读',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'is_read'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否处理',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'is_handle'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属组织',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'通知表',
'SCHEMA', N'dbo',
'TABLE', N'extend_notice'
GO


-- ----------------------------
-- Primary Key structure for table base_dict
-- ----------------------------
ALTER TABLE [dbo].[base_dict] ADD CONSTRAINT [PK__base_dic__3213E83FDB52DBAB] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table base_employee
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_emp_user_id]
ON [dbo].[base_employee] (
  [user_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table base_employee
-- ----------------------------
ALTER TABLE [dbo].[base_employee] ADD CONSTRAINT [PK__base_emp__3213E83FDC56619B] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table base_employee_org_rel
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_employee_org]
ON [dbo].[base_employee_org_rel] (
  [org_id] ASC,
  [employee_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table base_employee_org_rel
-- ----------------------------
ALTER TABLE [dbo].[base_employee_org_rel] ADD CONSTRAINT [PK__base_emp__3213E83FB4FDD9A8] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table base_employee_role_rel
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_err_role_employee]
ON [dbo].[base_employee_role_rel] (
  [role_id] ASC,
  [employee_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table base_employee_role_rel
-- ----------------------------
ALTER TABLE [dbo].[base_employee_role_rel] ADD CONSTRAINT [PK__base_emp__3213E83FC73D72DB] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table base_operation_log
-- ----------------------------
ALTER TABLE [dbo].[base_operation_log] ADD CONSTRAINT [PK__base_ope__3213E83FC9BBE7DC] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table base_operation_log_ext
-- ----------------------------
ALTER TABLE [dbo].[base_operation_log_ext] ADD CONSTRAINT [PK__base_ope__3213E83F55840A0A] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table base_org
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_org_name]
ON [dbo].[base_org] (
  [name] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table base_org
-- ----------------------------
ALTER TABLE [dbo].[base_org] ADD CONSTRAINT [PK__base_org__3213E83FCAC40BC9] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table base_org_role_rel
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_org_role]
ON [dbo].[base_org_role_rel] (
  [org_id] ASC,
  [role_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table base_org_role_rel
-- ----------------------------
ALTER TABLE [dbo].[base_org_role_rel] ADD CONSTRAINT [PK__base_org__3213E83FC7437233] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table base_parameter
-- ----------------------------
ALTER TABLE [dbo].[base_parameter] ADD CONSTRAINT [PK__base_par__3213E83F84241E46] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table base_position
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_name]
ON [dbo].[base_position] (
  [name] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table base_position
-- ----------------------------
ALTER TABLE [dbo].[base_position] ADD CONSTRAINT [PK__base_pos__3213E83FB002A158] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table base_role
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_code]
ON [dbo].[base_role] (
  [code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table base_role
-- ----------------------------
ALTER TABLE [dbo].[base_role] ADD CONSTRAINT [PK__base_rol__3213E83F12C57C95] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table base_role_resource_rel
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_role_resource]
ON [dbo].[base_role_resource_rel] (
  [resource_id] ASC,
  [role_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table base_role_resource_rel
-- ----------------------------
ALTER TABLE [dbo].[base_role_resource_rel] ADD CONSTRAINT [PK__base_rol__3213E83F15AF3C6C] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table com_appendix
-- ----------------------------
ALTER TABLE [dbo].[com_appendix] ADD CONSTRAINT [PK__com_appe__3213E83FE2F94C1D] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table com_file
-- ----------------------------
ALTER TABLE [dbo].[com_file] ADD CONSTRAINT [PK__com_file__3213E83F020C486A] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table extend_interface_log
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [UK_EIL_INTERFACE_ID]
ON [dbo].[extend_interface_log] (
  [interface_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table extend_interface_log
-- ----------------------------
ALTER TABLE [dbo].[extend_interface_log] ADD CONSTRAINT [PK__extend_i__3213E83F0B7E5045] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table extend_interface_logging
-- ----------------------------
ALTER TABLE [dbo].[extend_interface_logging] ADD CONSTRAINT [PK__extend_i__3213E83FF8C01FA1] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table extend_msg
-- ----------------------------
CREATE NONCLUSTERED INDEX [tempate_id_topic_content]
ON [dbo].[extend_msg] (
  [template_code] ASC,
  [title] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table extend_msg
-- ----------------------------
ALTER TABLE [dbo].[extend_msg] ADD CONSTRAINT [PK__extend_m__3213E83F2DC811FE] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table extend_msg_recipient
-- ----------------------------
CREATE NONCLUSTERED INDEX [task_id_tel_num]
ON [dbo].[extend_msg_recipient] (
  [msg_id] ASC,
  [recipient] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table extend_msg_recipient
-- ----------------------------
ALTER TABLE [dbo].[extend_msg_recipient] ADD CONSTRAINT [PK__extend_m__3213E83F024D1CAC] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table extend_msg_template
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [UK_EX_MSG_TEMPLATE_CODE]
ON [dbo].[extend_msg_template] (
  [code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table extend_msg_template
-- ----------------------------
ALTER TABLE [dbo].[extend_msg_template] ADD CONSTRAINT [PK__extend_m__3213E83FF81D58B4] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table extend_notice
-- ----------------------------
ALTER TABLE [dbo].[extend_notice] ADD CONSTRAINT [PK__extend_n__3213E83F8A3F0518] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

