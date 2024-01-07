/*
 Navicat Premium Data Transfer

 Source Server         : 172.26.3.67
 Source Server Type    : SQL Server
 Source Server Version : 15004236
 Source Host           : 172.26.3.67:1433
 Source Catalog        : lamp_none
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15004236
 File Encoding         : 65001

 Date: 17/08/2022 09:57:26
*/


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
  [last_company_id] bigint  NULL,
  [last_dept_id] bigint  NULL,
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
'MS_Description', N'上次登录公司ID',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'last_company_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'上次登录部门ID',
'SCHEMA', N'dbo',
'TABLE', N'base_employee',
'COLUMN', N'last_dept_id'
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
-- Records of base_employee
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[base_employee]  VALUES (N'160566476187631622', N'1', N'1451532876054003712', N'1', N'1451532667655815168', NULL, N'超管', N'20', N'10', N'1', N'1452186486253289472', N'2021-11-21 16:45:25.000', N'1452186486253289472', N'2021-11-21 16:45:25.000', N'1')
GO

INSERT INTO [dbo].[base_employee]  VALUES (N'1452186486492364800', N'1', N'1451532876054003712', N'1452186486253289472', N'1451532773234835456', NULL, N'内置超管-啊汤哥', N'20', N'10', N'1', N'2', N'2021-10-24 16:13:33.000', N'1452186486253289472', N'2021-11-09 20:36:44.000', N'2')
GO

INSERT INTO [dbo].[base_employee]  VALUES (N'1454329823978586112', N'1', N'1451532876054003712', N'1454329823852756992', N'1451532773234835456', NULL, N'门店管理员-最后哥', N'20', N'10', N'1', N'1451549146992345088', N'2021-10-30 14:10:25.000', N'1451549146992345088', N'2021-10-30 14:10:25.000', N'1')
GO

INSERT INTO [dbo].[base_employee]  VALUES (N'1457904456589901824', N'1', N'1458051094994223104', N'1457904455960756224', N'1451532773234835456', NULL, N'普通用户-小沙比', N'20', N'10', N'1', N'1451549146992345088', N'2021-11-09 10:54:44.000', N'1452186486253289472', N'2022-01-15 00:36:45.000', N'0')
GO

COMMIT
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
-- Records of base_employee_org_rel
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[base_employee_org_rel]  VALUES (N'180724033313046577', N'1451532821628715008', N'1457904456589901824', N'1452186486253289472', N'2022-01-15 00:36:45.000', N'1452186486253289472', N'2022-01-15 00:36:45.000', NULL)
GO

INSERT INTO [dbo].[base_employee_org_rel]  VALUES (N'1454329824028917760', N'1451532773234835456', N'1454329823978586112', N'1451549146992345088', N'2021-10-30 14:10:25.000', N'1451549146992345088', N'2021-10-30 14:10:25.000', N'0')
GO

INSERT INTO [dbo].[base_employee_org_rel]  VALUES (N'1458050924701286400', N'1451532773234835456', N'1452186486492364800', N'1452186486253289472', N'2021-11-09 20:36:44.000', N'1452186486253289472', N'2021-11-09 20:36:44.000', N'0')
GO

INSERT INTO [dbo].[base_employee_org_rel]  VALUES (N'1458050924713869312', N'1451532821628715008', N'1452186486492364800', N'1452186486253289472', N'2021-11-09 20:36:44.000', N'1452186486253289472', N'2021-11-09 20:36:44.000', N'0')
GO

COMMIT
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
-- Records of base_employee_role_rel
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[base_employee_role_rel]  VALUES (N'174418767919448069', N'1452496398934081536', N'160566476187631622', N'1452186486253289472', N'2021-12-29 00:39:22.000', N'1452186486253289472', N'2021-12-29 00:39:22.000', N'0')
GO

INSERT INTO [dbo].[base_employee_role_rel]  VALUES (N'179609240191631371', N'1', N'1457904456589901824', N'1452186486253289472', N'2022-01-12 00:23:15.000', N'1452186486253289472', N'2022-01-12 00:23:15.000', NULL)
GO

INSERT INTO [dbo].[base_employee_role_rel]  VALUES (N'189453146320273417', N'1460615729169563648', N'1457904456589901824', N'1452186486253289472', N'2022-02-07 13:06:46.000', N'1452186486253289472', N'2022-02-07 13:06:46.000', NULL)
GO

INSERT INTO [dbo].[base_employee_role_rel]  VALUES (N'1452186486492364800', N'1452496398934081536', N'1452186486492364800', N'1', N'2021-12-29 00:40:52.000', N'1', N'2021-12-29 00:40:57.000', N'0')
GO

INSERT INTO [dbo].[base_employee_role_rel]  VALUES (N'1457914363284291584', N'1452944729753780224', N'1454329823978586112', N'1451549146992345088', N'2021-11-09 11:34:06.000', N'1451549146992345088', N'2021-11-09 11:34:06.000', N'0')
GO

COMMIT
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
-- Records of base_org
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[base_org]  VALUES (N'3', N'公司经营层', N'20', NULL, N'1451532773234835456', N'2', N'/1451532773234835456/1451532667655815168/', N'1', N'1', NULL, NULL, N'0', N'2022-01-15 00:28:54.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_org]  VALUES (N'180724033313046540', N'阿狸子公司', N'10', NULL, N'1451532667655815168', N'1', N'/1451532667655815168/', N'1', N'1', NULL, N'2022-01-15 00:26:00.000', N'1452186486253289472', N'2022-01-15 00:26:00.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_org]  VALUES (N'180724033313046543', N'业务部', N'20', NULL, N'180724033313046540', N'2', N'/180724033313046540/1451532667655815168/', N'1', N'1', NULL, N'2022-01-15 00:26:19.000', N'1452186486253289472', N'2022-01-15 00:26:19.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_org]  VALUES (N'180724033313046546', N'市场部', N'20', NULL, N'180724033313046540', N'2', N'/180724033313046540/1451532667655815168/', N'2', N'1', NULL, N'2022-01-15 00:26:30.000', N'1452186486253289472', N'2022-01-15 00:26:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_org]  VALUES (N'180724033313046553', N'公司管理层', N'20', N'', N'1451532773234835456', N'2', N'/1451532773234835456/1451532667655815168/', N'2', N'1', N'', N'2022-01-15 00:29:08.000', N'1452186486253289472', N'2022-01-15 00:29:08.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_org]  VALUES (N'221743466365845511', N'333', N'20', N'333', N'0', N'0', N'/', N'1', N'1', NULL, N'2022-05-05 15:53:08.000', N'1452186486253289472', N'2022-05-05 15:53:08.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_org]  VALUES (N'1451532667655815168', N'阿里集团', N'10', N'阿里', N'0', N'0', N'/', N'1', N'1', NULL, N'2021-10-22 20:55:31.000', N'2', N'2021-10-22 20:55:31.000', N'2', N'0')
GO

INSERT INTO [dbo].[base_org]  VALUES (N'1451532727697276928', N'企鹅集团', N'10', N'1', N'0', N'0', N'/', N'1', N'1', NULL, N'2021-10-22 20:55:45.000', N'2', N'2021-10-22 20:55:45.000', N'2', N'0')
GO

INSERT INTO [dbo].[base_org]  VALUES (N'1451532773234835456', N'领导班子', N'20', NULL, N'1451532667655815168', N'1', N'/1451532667655815168/', N'2', N'1', NULL, N'2021-10-22 20:55:56.000', N'2', N'2022-01-15 00:26:58.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_org]  VALUES (N'1451532821628715008', N'产品部', N'20', NULL, N'1451532727697276928', N'1', N'/1451532727697276928/', N'1', N'1', NULL, N'2021-10-22 20:56:08.000', N'2', N'2021-10-22 20:56:08.000', N'2', N'0')
GO

COMMIT
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
-- Records of base_position
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[base_position]  VALUES (N'1451532876054003712', N'架构师', N'1451532667655815168', N'1', N'1', N'2021-10-22 20:56:20.000', N'2', N'2021-10-22 20:56:20.000', N'2', N'0')
GO

INSERT INTO [dbo].[base_position]  VALUES (N'1458051094994223104', N'产品经理', N'1451532821628715008', N'1', N'', N'2021-11-09 20:37:25.000', N'1452186486253289472', N'2021-11-09 20:37:25.000', N'1452186486253289472', N'0')
GO

COMMIT
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
-- Records of base_role
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[base_role]  VALUES (N'1', N'30', N'20', N'查询机构数据', N'2000', NULL, N'1', N'0', N'2', NULL, N'2', NULL, N'0')
GO

INSERT INTO [dbo].[base_role]  VALUES (N'1452496398934081536', N'10', N'10', N'租户管理员', N'TENANT_ADMIN', N'租户管理员', N'1', N'1', N'2', N'2021-10-25 12:45:02.000', N'2', N'2021-10-26 18:25:50.000', N'0')
GO

INSERT INTO [dbo].[base_role]  VALUES (N'1452944729753780224', N'10', N'20', N'机构管理员', N'ORG_ADMIN', N'单位(门店)管理员', N'1', N'0', N'2', N'2021-10-26 18:26:33.000', N'2', N'2021-10-26 18:26:33.000', N'0')
GO

INSERT INTO [dbo].[base_role]  VALUES (N'1460615729169563648', N'10', N'20', N'普通用户', N'1000', NULL, N'1', N'0', N'1452186486253289472', N'2021-11-16 22:28:21.000', N'1452186486253289472', N'2021-11-16 22:28:21.000', N'0')
GO

COMMIT
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
-- Records of base_role_resource_rel
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'167990275619160169', N'1449732868459724800', N'1', N'167640240079503484', N'2021-12-12 01:04:23.000', N'1452186486253289472', N'2021-12-12 01:04:23.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'174370075875213404', N'1449732868459724800', N'1', N'174370075875213393', N'2021-12-28 21:33:13.000', N'1452186486253289472', N'2021-12-28 21:33:13.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'174370075875213405', N'1457601147127726080', N'1', N'174370075875213393', N'2021-12-28 21:33:13.000', N'1452186486253289472', N'2021-12-28 21:33:13.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631377', N'1449733521265393664', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631378', N'1449732868459724800', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631379', N'1449733787893104640', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631380', N'1460468030063509504', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631381', N'1460537476991942656', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631382', N'1449734007292952576', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631383', N'1460436763976663040', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631384', N'1460436934051495936', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631385', N'1460537873248813056', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631386', N'1449738581135327232', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631387', N'1449732267470487552', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631388', N'1457620528469639168', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631389', N'1457620470995091456', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631390', N'1457620585302458368', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631391', N'1461609523809615872', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631392', N'1449739134456299520', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631393', N'1457665354649042944', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631394', N'1457665399683284992', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631395', N'1457665444381982720', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631396', N'1457665503664275456', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631397', N'1449738119237599232', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631398', N'1457665635705159680', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631399', N'1457665696765837312', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631400', N'1457665749857337344', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631401', N'1449731618892677120', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631402', N'144313439471271936', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631403', N'1460538485118074880', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631404', N'1460538532253663232', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631405', N'144313439471271937', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631406', N'144313439471271938', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631407', N'144313439471271940', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631408', N'1449730828442533888', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'179609240191631409', N'1449730881009745920', N'1', N'1460615729169563648', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'2022-01-12 00:23:30.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'181070207677104144', N'1449733521265393664', N'1', N'1', N'2022-01-16 10:51:54.000', N'1452186486253289472', N'2022-01-16 10:51:54.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'181070207677104145', N'1449734007292952576', N'1', N'1', N'2022-01-16 10:51:54.000', N'1452186486253289472', N'2022-01-16 10:51:54.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'181070207677104146', N'179582070228516868', N'1', N'1', N'2022-01-16 10:51:54.000', N'1452186486253289472', N'2022-01-16 10:51:54.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233581195264', N'1449734450995789824', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233589583872', N'144313439471271947', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233589583873', N'1449732267470487552', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233589583874', N'1449738119237599232', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233589583875', N'1449738581135327232', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233593778176', N'1449739134456299520', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233593778177', N'1449732868459724800', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233593778178', N'1449733787893104640', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233593778179', N'1449733521265393664', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233593778180', N'1460537476991942656', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233593778181', N'1460468030063509504', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233593778182', N'1449734007292952576', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233602166784', N'1460436763976663040', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233602166785', N'1460436856054218752', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233602166786', N'1460436934051495936', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233602166787', N'1460537873248813056', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233602166788', N'1457620408604819456', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233602166789', N'1457620470995091456', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233606361088', N'1457620528469639168', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233606361089', N'1457620585302458368', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233606361090', N'1457665587088982016', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233606361091', N'1457665635705159680', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233606361092', N'1457665696765837312', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233610555392', N'1457665749857337344', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233610555393', N'1457665354649042944', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233610555394', N'1457665399683284992', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233610555395', N'1457665444381982720', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233610555396', N'1457665503664275456', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233610555397', N'1457668749124435968', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

INSERT INTO [dbo].[base_role_resource_rel]  VALUES (N'1460621233610555398', N'1457668844297388032', N'1', N'1452944729753780224', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'2021-11-16 22:50:14.000', N'1452186486253289472', N'0')
GO

COMMIT
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
  [size_] bigint DEFAULT ((0)) NULL,
  [created_time] datetime  NOT NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NOT NULL,
  [updated_by] bigint  NULL,
  [created_org_id] bigint  NULL
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
'MS_Description', N'大小',
'SCHEMA', N'dbo',
'TABLE', N'com_file',
'COLUMN', N'size_'
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
'MS_Description', N'增量文件上传日志',
'SCHEMA', N'dbo',
'TABLE', N'com_file'
GO


-- ----------------------------
-- Table structure for def_application
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_application]') AND type IN ('U'))
	DROP TABLE [dbo].[def_application]
GO

CREATE TABLE [dbo].[def_application] (
  [id] bigint  NOT NULL,
  [app_key] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [app_secret] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [version] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [type] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('10') NOT NULL,
  [introduce] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [remark] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [url] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [is_general] bit DEFAULT ((0)) NULL,
  [is_visible] bit DEFAULT ((1)) NULL,
  [sort_value] int DEFAULT ((1)) NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[def_application] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'应用标识',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'app_key'
GO

EXEC sp_addextendedproperty
'MS_Description', N'应用秘钥',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'app_secret'
GO

EXEC sp_addextendedproperty
'MS_Description', N'应用名称',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'版本',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'version'
GO

EXEC sp_addextendedproperty
'MS_Description', N'应用类型;[10-自建应用 20-第三方应用]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.APPLICATION_TYPE)',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'简介',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'introduce'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'remark'
GO

EXEC sp_addextendedproperty
'MS_Description', N'应用地址',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否公共应用;0-否 1-是',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'is_general'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否可见;0-否 1-是',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'is_visible'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新人',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新时间',
'SCHEMA', N'dbo',
'TABLE', N'def_application',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'应用',
'SCHEMA', N'dbo',
'TABLE', N'def_application'
GO


-- ----------------------------
-- Records of def_application
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[def_application]  VALUES (N'1', N'basicPlatform', N'uhpe70w9rw0qjyp1hd6rae58ioa7anycc00p', N'基础平台', N'1', N'10', N'租户的工作台，最基础的功能。', N'基础平台是整个平台最基础，最核心的功能，所有租户都拥有。可以理解为用户的工作台，跳转其他业务系统的控制台等。', N'', N'1', N'1', N'1', N'2', N'2021-09-16 12:37:34.000', N'1452186486253289472', N'2021-11-10 21:06:45.000')
GO

INSERT INTO [dbo].[def_application]  VALUES (N'2', N'devOperation', N'ymyqj01qvmz7bpkne5li81cvxma2bebrzb57', N'开发运营系统', N'1', N'10', N'开发者或运营者使用，系统级功能，不能分配给租户。', N'开发运营系统是给 开发者和运营者公司的用户使用的，主要维护一些系统级的配置和数据，不能分配给普通租户使用。', N'', N'0', N'1', N'2', N'2', N'2021-09-18 12:49:26.000', N'1452186486253289472', N'2021-11-10 21:18:15.000')
GO

INSERT INTO [dbo].[def_application]  VALUES (N'3', N'businessSystem', N'c2mn7qb9i194mcypuletfxv8qe182e61awut', N'业务系统', N'1', N'10', N'根据不同业务开发出来的系统，租户需要购买方可使用。', N'根据各自的业务，开发出来的系统。根据不同的租户需求，购买后获得此系统的功能。 （这个系统需要你们根据自己情况自行二次开发）', N'', N'0', N'1', N'3', N'2', N'2021-09-24 21:11:00.000', N'1452186486253289472', N'2022-03-29 09:42:07.000')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for def_area
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_area]') AND type IN ('U'))
	DROP TABLE [dbo].[def_area]
GO

CREATE TABLE [dbo].[def_area] (
  [id] bigint  NOT NULL,
  [code] varchar(64) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [division_code] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [full_name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [sort_value] int DEFAULT ((1)) NULL,
  [longitude] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [latitude] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [source_] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [state] bit DEFAULT ((0)) NULL,
  [tree_grade] int DEFAULT ((0)) NULL,
  [tree_path] varchar(512) COLLATE Chinese_PRC_CI_AS DEFAULT ('/') NULL,
  [parent_id] bigint  NULL,
  [created_time] datetime  NOT NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NOT NULL,
  [updated_by] bigint  NULL,
  [level_] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('10') NULL
)
GO

ALTER TABLE [dbo].[def_area] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'id',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编码;统计用区划代码',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'城乡划分代码',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'division_code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'全名',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'full_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'经度',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'longitude'
GO

EXEC sp_addextendedproperty
'MS_Description', N'维度',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'latitude'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据来源;[10-爬取 20-新增]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.AREA_SOURCE)',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'source_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'树层级',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'tree_grade'
GO

EXEC sp_addextendedproperty
'MS_Description', N'树路径',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'tree_path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'父ID',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'行政级别;[10-国家 20-省份/直辖市 30-地市 40-区县 50-乡镇]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.AREA_LEVEL)',
'SCHEMA', N'dbo',
'TABLE', N'def_area',
'COLUMN', N'level_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'地区表',
'SCHEMA', N'dbo',
'TABLE', N'def_area'
GO


-- ----------------------------
-- Table structure for def_client
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_client]') AND type IN ('U'))
	DROP TABLE [dbo].[def_client]
GO

CREATE TABLE [dbo].[def_client] (
  [id] bigint  NOT NULL,
  [client_id] varchar(24) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [client_secret] varchar(32) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [type] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('10') NULL,
  [remarks] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [state] bit DEFAULT ((1)) NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[def_client] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'def_client',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'客户端ID',
'SCHEMA', N'dbo',
'TABLE', N'def_client',
'COLUMN', N'client_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'客户端密码',
'SCHEMA', N'dbo',
'TABLE', N'def_client',
'COLUMN', N'client_secret'
GO

EXEC sp_addextendedproperty
'MS_Description', N'客户端名称',
'SCHEMA', N'dbo',
'TABLE', N'def_client',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类型;[10-WEB网站;15-移动端应用;20-手机H5网页;25-内部服务; 30-第三方应用]
@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.CLIENT_TYPE)',
'SCHEMA', N'dbo',
'TABLE', N'def_client',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'def_client',
'COLUMN', N'remarks'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'def_client',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'def_client',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_client',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人id',
'SCHEMA', N'dbo',
'TABLE', N'def_client',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'def_client',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'客户端',
'SCHEMA', N'dbo',
'TABLE', N'def_client'
GO


-- ----------------------------
-- Records of def_client
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[def_client]  VALUES (N'1448881860003233792', N'lamp_web_pro', N'lamp_web_pro_secret', N'lamp-web-pro', N'10', N'', N'1', N'2', N'2021-10-15 13:22:09.000', N'2', N'2021-10-16 22:33:39.000')
GO

INSERT INTO [dbo].[def_client]  VALUES (N'1449383073153024000', N'lamp_web', N'lamp_web_secret', N'lamp-web', N'10', N'', N'1', N'2', N'2021-10-16 22:33:48.000', N'2', N'2021-10-16 22:33:48.000')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for def_datasource_config
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_datasource_config]') AND type IN ('U'))
	DROP TABLE [dbo].[def_datasource_config]
GO

CREATE TABLE [dbo].[def_datasource_config] (
  [id] bigint  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [username] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [password] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [url] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [driver_class_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [created_time] datetime  NOT NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NOT NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[def_datasource_config] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键ID',
'SCHEMA', N'dbo',
'TABLE', N'def_datasource_config',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'def_datasource_config',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'账号',
'SCHEMA', N'dbo',
'TABLE', N'def_datasource_config',
'COLUMN', N'username'
GO

EXEC sp_addextendedproperty
'MS_Description', N'密码',
'SCHEMA', N'dbo',
'TABLE', N'def_datasource_config',
'COLUMN', N'password'
GO

EXEC sp_addextendedproperty
'MS_Description', N'链接',
'SCHEMA', N'dbo',
'TABLE', N'def_datasource_config',
'COLUMN', N'url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'驱动',
'SCHEMA', N'dbo',
'TABLE', N'def_datasource_config',
'COLUMN', N'driver_class_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_datasource_config',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'def_datasource_config',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'def_datasource_config',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'def_datasource_config',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据源',
'SCHEMA', N'dbo',
'TABLE', N'def_datasource_config'
GO


-- ----------------------------
-- Table structure for def_dict
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_dict]') AND type IN ('U'))
	DROP TABLE [dbo].[def_dict]
GO

CREATE TABLE [dbo].[def_dict] (
  [id] bigint  NOT NULL,
  [parent_id] bigint DEFAULT ((0)) NOT NULL,
  [parent_key] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [classify] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('20') NOT NULL,
  [key_] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [state] bit DEFAULT ((1)) NULL,
  [remark] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [sort_value] int DEFAULT ((1)) NULL,
  [icon] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [css_style] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [css_class] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NOT NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NOT NULL
)
GO

ALTER TABLE [dbo].[def_dict] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'字典ID',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'父字典标识',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'parent_key'
GO

EXEC sp_addextendedproperty
'MS_Description', N'分类;[10-系统字典 20-业务字典]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.DICT_CLASSIFY)',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'classify'
GO

EXEC sp_addextendedproperty
'MS_Description', N'标识',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'key_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'remark'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'图标',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'icon'
GO

EXEC sp_addextendedproperty
'MS_Description', N'css样式',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'css_style'
GO

EXEC sp_addextendedproperty
'MS_Description', N'css类元素',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'css_class'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人id',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'def_dict',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'字典',
'SCHEMA', N'dbo',
'TABLE', N'def_dict'
GO


-- ----------------------------
-- Records of def_dict
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1', N'0', N'', N'10', N'TENANT_RESOURCE_TYPE', N'资源类型', N'1', N'[20-菜单 30-视图 40-按钮 50-字段]', N'1', N'', N'', N'', N'2', N'2021-10-07 23:25:48.000', N'2', N'2021-10-07 23:25:48.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'2', N'1', N'TENANT_RESOURCE_TYPE', N'10', N'20', N'菜单', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-07 23:25:48.000', N'2', N'2021-10-07 23:25:48.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'3', N'1', N'TENANT_RESOURCE_TYPE', N'10', N'30', N'视图', N'1', N'', N'2', N'', N'', N'', N'2', N'2021-10-07 23:25:48.000', N'2', N'2021-10-07 23:25:48.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'4', N'1', N'TENANT_RESOURCE_TYPE', N'10', N'40', N'按钮', N'1', N'', N'3', N'', N'', N'', N'2', N'2021-10-07 23:25:48.000', N'2', N'2021-10-07 23:25:48.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'5', N'1', N'TENANT_RESOURCE_TYPE', N'10', N'50', N'字段', N'1', N'', N'4', N'', N'', N'', N'2', N'2021-10-07 23:25:48.000', N'2', N'2021-10-07 23:25:48.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'6', N'1', N'TENANT_RESOURCE_TYPE', N'10', N'60', N'数据', N'1', N'', N'5', N'', N'', N'', N'2', N'2022-01-11 12:27:39.000', N'2', N'2022-01-11 12:27:47.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'43', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'01', N'汉族', N'1', N'', N'0', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'44', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'02', N'壮族', N'1', N'', N'1', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'45', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'03', N'满族', N'1', N'', N'2', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'46', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'04', N'回族', N'1', N'', N'3', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'47', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'05', N'苗族', N'1', N'', N'4', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'48', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'06', N'维吾尔族', N'1', N'', N'5', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'49', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'07', N'土家族', N'1', N'', N'6', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'50', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'08', N'彝族', N'1', N'', N'7', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'51', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'09', N'蒙古族', N'1', N'', N'8', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'52', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'10', N'藏族', N'1', N'', N'9', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'53', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'11', N'布依族', N'1', N'', N'10', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'54', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'12', N'侗族', N'1', N'', N'11', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'55', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'13', N'瑶族', N'1', N'', N'12', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'56', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'14', N'朝鲜族', N'1', N'', N'13', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'57', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'15', N'白族', N'1', N'', N'14', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'58', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'16', N'哈尼族', N'1', N'', N'15', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'59', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'17', N'哈萨克族', N'1', N'', N'16', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'60', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'18', N'黎族', N'1', N'', N'17', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'61', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'19', N'傣族', N'1', N'', N'18', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'62', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'20', N'畲族', N'1', N'', N'19', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'63', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'21', N'傈僳族', N'1', N'', N'20', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'64', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'22', N'仡佬族', N'1', N'', N'21', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'65', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'23', N'东乡族', N'1', N'', N'22', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'66', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'24', N'高山族', N'1', N'', N'23', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'67', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'25', N'拉祜族', N'1', N'', N'24', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'68', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'26', N'水族', N'1', N'', N'25', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'69', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'27', N'佤族', N'1', N'', N'26', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'70', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'28', N'纳西族', N'1', N'', N'27', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'71', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'29', N'羌族', N'1', N'', N'28', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'72', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'30', N'土族', N'1', N'', N'29', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'73', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'31', N'仫佬族', N'1', N'', N'30', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'74', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'32', N'锡伯族', N'1', N'', N'31', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'75', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'33', N'柯尔克孜族', N'1', N'', N'32', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'76', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'34', N'达斡尔族', N'1', N'', N'33', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'77', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'35', N'景颇族', N'1', N'', N'34', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'78', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'36', N'毛南族', N'1', N'', N'35', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'79', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'37', N'撒拉族', N'1', N'', N'36', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'80', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'38', N'塔吉克族', N'1', N'', N'37', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'81', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'39', N'阿昌族', N'1', N'', N'38', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'82', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'40', N'普米族', N'1', N'', N'39', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'83', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'41', N'鄂温克族', N'1', N'', N'40', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'84', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'42', N'怒族', N'1', N'', N'41', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'85', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'43', N'京族', N'1', N'', N'42', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'86', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'44', N'基诺族', N'1', N'', N'43', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'87', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'45', N'德昂族', N'1', N'', N'44', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'88', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'46', N'保安族', N'1', N'', N'45', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'89', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'47', N'俄罗斯族', N'1', N'', N'46', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'90', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'48', N'裕固族', N'1', N'', N'47', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'91', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'49', N'乌兹别克族', N'1', N'', N'48', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'92', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'50', N'门巴族', N'1', N'', N'49', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'93', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'51', N'鄂伦春族', N'1', N'', N'50', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'94', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'52', N'独龙族', N'1', N'', N'51', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'95', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'53', N'塔塔尔族', N'1', N'', N'52', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'96', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'54', N'赫哲族', N'1', N'', N'53', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'97', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'55', N'珞巴族', N'1', N'', N'54', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'98', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'56', N'布朗族', N'1', N'', N'55', N'', N'', N'', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'99', N'1451108231005863936', N'GLOBAL_NATION', N'20', N'57', N'其他', N'1', N'', N'100', N'', N'', N'', N'3', N'2020-03-09 23:38:29.000', N'3', N'2020-03-09 23:38:29.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'143963605795078147', N'0', N'', N'10', N'GLOBAL_SEX', N'性别', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-07 23:25:48.000', N'2', N'2021-10-07 23:25:48.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'143963605795078148', N'0', N'', N'20', N'TENANT_APPLICATION_TYPE', N'应用类型', N'1', N'[10-自建应用 20-第三方应用]', N'1', N'', N'', N'', N'2', N'2021-10-07 23:28:18.000', N'2', N'2021-10-07 23:28:18.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'143963605795078149', N'0', N'', N'20', N'TENANT_APPLICATION_GRANT_TYPE', N'授权类型', N'1', N'[10-应用授权 20-应用续期 30-取消授权]', N'1', N'', N'', N'', N'2', N'2021-10-07 23:30:31.000', N'2', N'2021-10-07 23:30:31.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'143963605795078150', N'143963605795078149', N'TENANT_APPLICATION_GRANT_TYPE', N'20', N'10', N'应用授权', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-07 23:34:19.000', N'2', N'2021-10-07 23:34:19.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'143963605795078151', N'143963605795078149', N'TENANT_APPLICATION_GRANT_TYPE', N'20', N'20', N'应用续期', N'1', N'', N'2', N'', N'', N'', N'2', N'2021-10-07 23:34:35.000', N'2', N'2021-10-07 23:34:35.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'143963605795078152', N'143963605795078149', N'TENANT_APPLICATION_GRANT_TYPE', N'10', N'30', N'取消授权', N'1', N'', N'3', N'', N'', N'', N'2', N'2021-10-07 23:34:44.000', N'1', N'2021-11-22 12:58:24.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'143963605795078153', N'143963605795078148', N'TENANT_APPLICATION_TYPE', N'20', N'10', N'自建应用', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-07 23:37:30.000', N'2', N'2021-10-07 23:37:30.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'143963605795078154', N'143963605795078148', N'TENANT_APPLICATION_TYPE', N'20', N'20', N'第三方应用', N'1', N'', N'2', N'', N'', N'', N'2', N'2021-10-07 23:37:42.000', N'2', N'2021-10-07 23:37:42.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'143963605795078155', N'143963605795078147', N'GLOBAL_SEX', N'10', N'1', N'男', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-07 23:38:04.000', N'2', N'2021-10-07 23:38:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'143963605795078156', N'143963605795078147', N'GLOBAL_SEX', N'10', N'2', N'女', N'1', N'', N'2', N'', N'', N'', N'2', N'2021-10-07 23:38:15.000', N'2', N'2021-10-07 23:38:15.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'160561957882036228', N'0', N'', N'10', N'TENANT_RESOURCE_OPEN_WITH', N'打开方式', N'1', N'[01-组件 02-内链 03-外链]', N'1', N'', N'', N'', N'1', N'2021-11-21 20:03:45.000', N'1', N'2021-11-21 20:03:45.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'160561957882036229', N'160561957882036228', N'TENANT_RESOURCE_OPEN_WITH', N'10', N'01', N'组件', N'1', N'', N'1', N'', N'', N'', N'1', N'2021-11-21 20:03:59.000', N'1', N'2021-11-21 20:03:59.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'160561957882036230', N'160561957882036228', N'TENANT_RESOURCE_OPEN_WITH', N'10', N'02', N'内链', N'1', N'', N'2', N'', N'', N'', N'1', N'2021-11-21 20:04:09.000', N'1', N'2021-11-21 20:04:09.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'160561957882036231', N'160561957882036228', N'TENANT_RESOURCE_OPEN_WITH', N'10', N'03', N'外链', N'1', N'', N'3', N'', N'', N'', N'1', N'2021-11-21 20:04:17.000', N'1', N'2021-11-21 20:04:17.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'179467712429293568', N'0', N'', N'10', N'BASE_ROLE_CATEGORY', N'角色类别', N'1', N'[10-功能角色 20-桌面角色 30-数据角色]', N'1', N'', N'', N'', N'1452186486253289472', N'2022-01-11 15:09:27.000', N'1452186486253289472', N'2022-01-11 15:09:27.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'179467712429293569', N'0', N'', N'10', N'TENANT_RESOURCE_DATA_SCOPE', N'数据范围', N'1', N'[01-全部 02-本单位及子级 03-本单位 04-本部门及子级 05-本部门 06-个人 07-自定义]', N'1', N'', N'', N'', N'1452186486253289472', N'2022-01-11 15:33:55.000', N'1452186486253289472', N'2022-01-11 15:33:55.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'179467712429293570', N'179467712429293569', N'TENANT_RESOURCE_DATA_SCOPE', N'10', N'01', N'全部', N'1', N'', N'1', N'', N'', N'', N'1452186486253289472', N'2022-01-11 15:40:35.000', N'1452186486253289472', N'2022-01-11 15:40:35.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'179467712429293571', N'179467712429293569', N'TENANT_RESOURCE_DATA_SCOPE', N'10', N'02', N'本单位及子级', N'1', N'', N'2', N'', N'', N'', N'1452186486253289472', N'2022-01-11 15:40:44.000', N'1452186486253289472', N'2022-01-11 15:40:44.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'179467712429293572', N'179467712429293569', N'TENANT_RESOURCE_DATA_SCOPE', N'10', N'03', N'本单位', N'1', N'', N'3', N'', N'', N'', N'1452186486253289472', N'2022-01-11 15:40:51.000', N'1452186486253289472', N'2022-01-11 15:40:51.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'179467712429293573', N'179467712429293569', N'TENANT_RESOURCE_DATA_SCOPE', N'10', N'04', N'本部门及子级', N'1', N'', N'4', N'', N'', N'', N'1452186486253289472', N'2022-01-11 15:40:59.000', N'1452186486253289472', N'2022-01-11 15:40:59.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'179467712429293574', N'179467712429293569', N'TENANT_RESOURCE_DATA_SCOPE', N'10', N'05', N'本部门', N'1', N'', N'5', N'', N'', N'', N'1452186486253289472', N'2022-01-11 15:41:09.000', N'1452186486253289472', N'2022-01-11 15:41:09.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'179467712429293575', N'179467712429293569', N'TENANT_RESOURCE_DATA_SCOPE', N'10', N'06', N'个人', N'1', N'', N'6', N'', N'', N'', N'1452186486253289472', N'2022-01-11 15:41:17.000', N'1452186486253289472', N'2022-01-11 15:41:17.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'179467712429293576', N'179467712429293569', N'TENANT_RESOURCE_DATA_SCOPE', N'10', N'07', N'自定义', N'1', N'', N'7', N'', N'', N'', N'1452186486253289472', N'2022-01-11 15:41:26.000', N'1452186486253289472', N'2022-01-11 15:41:26.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'179467712429293577', N'179467712429293568', N'BASE_ROLE_CATEGORY', N'10', N'10', N'功能角色', N'1', N'', N'1', N'', N'', N'', N'1452186486253289472', N'2022-01-11 15:41:43.000', N'1452186486253289472', N'2022-01-11 15:41:43.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'179467712429293578', N'179467712429293568', N'BASE_ROLE_CATEGORY', N'10', N'20', N'桌面角色', N'1', N'', N'2', N'', N'', N'', N'1452186486253289472', N'2022-01-11 15:41:50.000', N'1452186486253289472', N'2022-01-11 15:41:50.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'179467712429293579', N'179467712429293568', N'BASE_ROLE_CATEGORY', N'10', N'30', N'数据角色', N'1', N'', N'1', N'', N'', N'', N'1452186486253289472', N'2022-01-11 15:41:57.000', N'1452186486253289472', N'2022-01-11 15:41:57.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'216686795209834626', N'0', N'', N'20', N'TEST_ADD_DICT', N'整形字典', N'1', N'[1-测试 2-新增 aad-haha]', N'1', N'', N'', N'', N'1452186486253289472', N'2022-04-21 22:29:03.000', N'1452186486253289472', N'2022-04-21 22:29:03.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'216686795209834627', N'216686795209834626', N'TEST_ADD_DICT', N'20', N'1', N'测试', N'1', N'', N'1', N'', N'', N'', N'1452186486253289472', N'2022-04-21 22:29:04.000', N'1452186486253289472', N'2022-04-21 22:29:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'216686795209834628', N'216686795209834626', N'TEST_ADD_DICT', N'20', N'2', N'新增', N'1', N'', N'1', N'', N'', N'', N'1452186486253289472', N'2022-04-21 22:29:04.000', N'1452186486253289472', N'2022-04-21 22:29:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'216686795209834629', N'216686795209834626', N'TEST_ADD_DICT', N'20', N'aad', N'haha', N'1', N'', N'1', N'', N'', N'', N'1452186486253289472', N'2022-04-21 22:29:04.000', N'1452186486253289472', N'2022-04-21 22:29:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'244439645515939840', N'0', N'', N'10', N'INTERFACE_EXEC_MODE', N'执行模式', N'1', N'接口执行模式 [01-实现类 02-脚本]', N'1', N'', N'', N'', N'1452186486253289472', N'2022-07-05 20:18:23.000', N'1452186486253289472', N'2022-07-05 20:18:23.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'244439645515939841', N'244439645515939840', N'INTERFACE_EXEC_MODE', N'10', N'01', N'实现类', N'1', N'', N'1', N'', N'', N'', N'1452186486253289472', N'2022-07-05 21:31:15.000', N'1452186486253289472', N'2022-07-05 21:31:15.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'244439645515939842', N'244439645515939840', N'INTERFACE_EXEC_MODE', N'10', N'02', N'脚本', N'1', N'', N'2', N'', N'', N'', N'1452186486253289472', N'2022-07-05 21:31:25.000', N'1452186486253289472', N'2022-07-05 21:31:25.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'245619503096922112', N'0', N'', N'10', N'MSG_TEMPLATE_TYPE', N'消息类型', N'1', N'[01-短信 02-邮件 03-站内信]', N'1', N'', N'', N'', N'1452186486253289472', N'2022-07-08 23:35:49.000', N'1452186486253289472', N'2022-07-08 23:35:49.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'245619503096922113', N'245619503096922112', N'MSG_TEMPLATE_TYPE', N'10', N'01', N'短信', N'1', N'', N'1', N'', N'', N'', N'1452186486253289472', N'2022-07-08 23:36:06.000', N'1452186486253289472', N'2022-07-08 23:36:06.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'245619503096922114', N'245619503096922112', N'MSG_TEMPLATE_TYPE', N'10', N'02', N'邮件', N'1', N'', N'2', N'', N'', N'', N'1452186486253289472', N'2022-07-08 23:36:18.000', N'1452186486253289472', N'2022-07-08 23:36:18.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'245619503096922115', N'245619503096922112', N'MSG_TEMPLATE_TYPE', N'10', N'03', N'站内信', N'1', N'', N'3', N'', N'', N'', N'1452186486253289472', N'2022-07-08 23:36:28.000', N'1452186486253289472', N'2022-07-08 23:36:28.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'245619503096922116', N'0', N'', N'10', N'NOTICE_TARGET', N'打开方式', N'1', N'[01-页面 02-弹窗 03-新开窗口]', N'1', N'', N'', N'', N'1452186486253289472', N'2022-07-08 23:37:03.000', N'1452186486253289472', N'2022-07-08 23:37:03.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'245619503096922117', N'245619503096922116', N'NOTICE_TARGET', N'10', N'01', N'页面', N'1', N'', N'1', N'', N'', N'', N'1452186486253289472', N'2022-07-08 23:37:19.000', N'1452186486253289472', N'2022-07-08 23:37:19.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'245619503096922118', N'245619503096922116', N'NOTICE_TARGET', N'10', N'02', N'弹窗', N'1', N'', N'2', N'', N'', N'', N'1452186486253289472', N'2022-07-08 23:37:33.000', N'1452186486253289472', N'2022-07-08 23:37:33.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'245619503096922119', N'245619503096922116', N'NOTICE_TARGET', N'10', N'03', N'新开窗口', N'1', N'', N'3', N'', N'', N'', N'1452186486253289472', N'2022-07-08 23:37:44.000', N'1452186486253289472', N'2022-07-08 23:37:54.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'245619503096922120', N'0', N'', N'10', N'NOTICE_REMIND_MODE', N'提醒方式', N'1', N'[01-待办 02-预警 03-提醒]', N'1', N'', N'', N'', N'1452186486253289472', N'2022-07-08 23:38:27.000', N'1452186486253289472', N'2022-07-08 23:38:27.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'245619503096922121', N'245619503096922120', N'NOTICE_REMIND_MODE', N'10', N'01', N'待办', N'1', N'', N'1', N'', N'', N'', N'1452186486253289472', N'2022-07-08 23:38:46.000', N'1452186486253289472', N'2022-07-08 23:38:46.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'245619503096922122', N'245619503096922120', N'NOTICE_REMIND_MODE', N'10', N'02', N'预警', N'1', N'', N'2', N'', N'', N'', N'1452186486253289472', N'2022-07-08 23:38:58.000', N'1452186486253289472', N'2022-07-08 23:38:58.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'245619503096922123', N'245619503096922120', N'NOTICE_REMIND_MODE', N'10', N'03', N'提醒', N'1', N'', N'3', N'', N'', N'', N'1452186486253289472', N'2022-07-08 23:39:06.000', N'1452186486253289472', N'2022-07-08 23:39:06.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'249679058940461056', N'0', N'', N'10', N'MSG_INTERFACE_LOGGING_STATUS', N'接口日志执行状态', N'1', N'[01-初始化 02-成功 03-失败]', N'1', N'', N'', N'', N'1452186486253289472', N'2022-07-19 20:52:27.000', N'1452186486253289472', N'2022-07-19 20:52:27.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'249679058940461057', N'249679058940461056', N'MSG_INTERFACE_LOGGING_STATUS', N'10', N'01', N'初始化', N'1', N'[01-初始化 02-成功 03-失败]', N'1', N'', N'', N'', N'1452186486253289472', N'2022-07-19 20:52:38.000', N'1452186486253289472', N'2022-07-19 20:52:38.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'249679058940461058', N'249679058940461056', N'MSG_INTERFACE_LOGGING_STATUS', N'10', N'02', N'成功', N'1', N'[01-初始化 02-成功 03-失败]', N'2', N'', N'', N'', N'1452186486253289472', N'2022-07-19 20:52:49.000', N'1452186486253289472', N'2022-07-19 20:52:49.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'249679058940461059', N'249679058940461056', N'MSG_INTERFACE_LOGGING_STATUS', N'10', N'03', N'失败', N'1', N'[01-初始化 02-成功 03-失败]', N'3', N'', N'', N'', N'1452186486253289472', N'2022-07-19 20:52:58.000', N'1452186486253289472', N'2022-07-19 20:52:58.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1445949268236959744', N'0', N'', N'10', N'TENANT_DICT_CLASSIFY', N'字典分类', N'1', N'[10-系统字典 20-业务字典]', N'1', N'', N'', N'', N'2', N'2021-10-07 11:09:05.000', N'2', N'2021-10-07 11:09:05.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1445983002105479168', N'1445949268236959744', N'TENANT_DICT_CLASSIFY', N'10', N'10', N'系统字典', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-07 13:23:07.000', N'2', N'2021-10-07 13:23:07.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1445983070812372992', N'1445949268236959744', N'TENANT_DICT_CLASSIFY', N'10', N'20', N'业务字典', N'1', N'', N'2', N'', N'', N'', N'2', N'2021-10-07 13:23:24.000', N'2', N'2021-10-07 13:23:24.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448505925026447360', N'0', N'', N'10', N'GLOBAL_AREA_LEVEL', N'行政级别', N'1', N'[10-国家 20-省份/直辖市 30-地市 40-区县 50-乡镇]', N'1', N'', N'', N'', N'2', N'2021-10-14 12:28:19.000', N'2', N'2021-10-14 12:28:19.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448506028873220096', N'0', N'', N'10', N'TENANT_CLIENT_TYPE', N'客户端类型', N'1', N'[10-WEB网站;15-移动端应用;20-手机H5网页;25-内部服务; 30-第三方应用]', N'1', N'', N'', N'', N'2', N'2021-10-14 12:28:44.000', N'2', N'2021-10-14 12:28:44.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448506127556804608', N'0', N'', N'10', N'TENANT_AREA_SOURCE', N'地区来源', N'1', N'[10-爬取 20-新增]', N'1', N'', N'', N'', N'2', N'2021-10-14 12:29:07.000', N'2', N'2021-10-14 12:29:07.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448506284952256512', N'0', N'', N'10', N'TENANT_PARAMETER_TYPE', N'参数类型', N'1', N'[10-系统参数 20-业务参数]', N'1', N'', N'', N'', N'2', N'2021-10-14 12:29:45.000', N'2', N'2021-10-14 12:29:45.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448515694336409600', N'1448506284952256512', N'TENANT_PARAMETER_TYPE', N'10', N'10', N'系统参数', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-14 13:07:08.000', N'2', N'2021-10-14 13:07:08.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448515728603873280', N'1448506284952256512', N'TENANT_PARAMETER_TYPE', N'10', N'20', N'业务参数', N'1', N'', N'2', N'', N'', N'', N'2', N'2021-10-14 13:07:16.000', N'2', N'2021-10-14 13:07:16.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448515804071985152', N'1448506127556804608', N'TENANT_AREA_SOURCE', N'10', N'10', N'爬取', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-14 13:07:34.000', N'2', N'2021-10-14 13:20:12.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448515832505171968', N'1448506127556804608', N'TENANT_AREA_SOURCE', N'10', N'20', N'新增', N'1', N'', N'2', N'', N'', N'', N'2', N'2021-10-14 13:07:41.000', N'2', N'2021-10-14 13:07:41.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448636555584339968', N'1448505925026447360', N'GLOBAL_AREA_LEVEL', N'10', N'10', N'国家', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-14 21:07:24.000', N'2', N'2021-10-14 21:07:24.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448636622080835584', N'1448505925026447360', N'GLOBAL_AREA_LEVEL', N'10', N'20', N'省份/直辖市', N'1', N'', N'2', N'', N'', N'', N'2', N'2021-10-14 21:07:40.000', N'2', N'2021-10-14 21:07:40.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448636660773289984', N'1448505925026447360', N'GLOBAL_AREA_LEVEL', N'10', N'30', N'地市', N'1', N'', N'3', N'', N'', N'', N'2', N'2021-10-14 21:07:49.000', N'2', N'2021-10-14 21:07:49.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448636742079873024', N'1448505925026447360', N'GLOBAL_AREA_LEVEL', N'10', N'40', N'区县', N'1', N'', N'4', N'', N'', N'', N'2', N'2021-10-14 21:08:08.000', N'2', N'2021-10-14 21:08:08.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448636799311151104', N'1448505925026447360', N'GLOBAL_AREA_LEVEL', N'10', N'50', N'乡镇', N'1', N'', N'5', N'', N'', N'', N'2', N'2021-10-14 21:08:22.000', N'2', N'2021-10-14 21:08:22.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448646183458177024', N'1448506028873220096', N'TENANT_CLIENT_TYPE', N'10', N'10', N'WEB网站', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-14 21:45:39.000', N'2', N'2021-10-14 21:45:39.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448650608742498304', N'1448506028873220096', N'TENANT_CLIENT_TYPE', N'10', N'15', N'移动端应用', N'1', N'', N'2', N'', N'', N'', N'2', N'2021-10-14 22:03:14.000', N'2', N'2021-10-14 22:03:14.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448650640468213760', N'1448506028873220096', N'TENANT_CLIENT_TYPE', N'10', N'20', N'手机H5网页', N'1', N'', N'3', N'', N'', N'', N'2', N'2021-10-14 22:03:22.000', N'2', N'2021-10-14 22:03:22.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448650682050543616', N'1448506028873220096', N'TENANT_CLIENT_TYPE', N'10', N'25', N'内部服务', N'1', N'', N'4', N'', N'', N'', N'2', N'2021-10-14 22:03:32.000', N'2', N'2021-10-14 22:03:32.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1448650761264168960', N'1448506028873220096', N'TENANT_CLIENT_TYPE', N'10', N'30', N'第三方应用', N'1', N'', N'5', N'', N'', N'', N'2', N'2021-10-14 22:03:51.000', N'2', N'2021-10-14 22:03:51.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451099664811032576', N'0', N'', N'10', N'BASE_ORG_TYPE', N'机构类型', N'1', N'[10-单位/门店 20-部门]', N'1', N'', N'', N'', N'2', N'2021-10-21 16:14:55.000', N'2', N'2021-10-21 16:14:55.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451099742934138880', N'1451099664811032576', N'BASE_ORG_TYPE', N'10', N'10', N'单位/门店', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-21 16:15:14.000', N'2', N'2021-10-21 16:15:14.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451099769471500288', N'1451099664811032576', N'BASE_ORG_TYPE', N'10', N'20', N'部门', N'1', N'', N'2', N'', N'', N'', N'2', N'2021-10-21 16:15:20.000', N'2', N'2021-10-21 16:15:20.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451100760304517120', N'0', N'', N'10', N'BASE_POSITION_STATUS', N'职位状态', N'1', N'[10-在职 20-离职 30-未激活]', N'1', N'', N'', N'', N'2', N'2021-10-21 16:19:16.000', N'2', N'2021-10-21 16:19:16.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451100809038135296', N'1451100760304517120', N'BASE_POSITION_STATUS', N'10', N'10', N'在职', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-21 16:19:28.000', N'2', N'2021-10-21 16:19:28.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451100851467714560', N'1451100760304517120', N'BASE_POSITION_STATUS', N'10', N'20', N'离职', N'1', N'', N'2', N'', N'', N'', N'2', N'2021-10-21 16:19:38.000', N'2', N'2021-10-21 16:19:38.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451103343890923520', N'0', N'', N'10', N'GLOBAL_EDUCATION', N'学历', N'1', N'[01-小学 02-中学 03-高中 04-专科 05-本科 06-硕士 07-博士 08-博士后 09-其他]', N'1', N'', N'', N'', N'2', N'2021-10-21 16:29:32.000', N'2', N'2021-10-21 16:29:32.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451103396311334912', N'1451103343890923520', N'GLOBAL_EDUCATION', N'10', N'01', N'小学', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-21 16:29:45.000', N'2', N'2021-10-21 16:30:05.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451103460706484224', N'1451103343890923520', N'GLOBAL_EDUCATION', N'10', N'02', N'中学', N'1', N'', N'2', N'', N'', N'', N'2', N'2021-10-21 16:30:00.000', N'2', N'2021-10-21 16:30:00.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451105115103559680', N'1451103343890923520', N'GLOBAL_EDUCATION', N'10', N'03', N'高中', N'1', N'', N'3', N'', N'', N'', N'2', N'2021-10-21 16:36:34.000', N'2', N'2021-10-21 16:36:34.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451105202630295552', N'1451103343890923520', N'GLOBAL_EDUCATION', N'10', N'04', N'专科', N'1', N'', N'4', N'', N'', N'', N'2', N'2021-10-21 16:36:55.000', N'2', N'2021-10-21 16:36:55.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451105239401758720', N'1451103343890923520', N'GLOBAL_EDUCATION', N'10', N'05', N'本科', N'1', N'', N'5', N'', N'', N'', N'2', N'2021-10-21 16:37:04.000', N'2', N'2021-10-21 16:37:04.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451105277691559936', N'1451103343890923520', N'GLOBAL_EDUCATION', N'10', N'06', N'硕士', N'1', N'', N'6', N'', N'', N'', N'2', N'2021-10-21 16:37:13.000', N'2', N'2021-10-21 16:37:13.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451105309626990592', N'1451103343890923520', N'GLOBAL_EDUCATION', N'10', N'07', N'博士', N'1', N'', N'7', N'', N'', N'', N'2', N'2021-10-21 16:37:21.000', N'2', N'2021-10-21 16:37:21.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451105398009364480', N'1451103343890923520', N'GLOBAL_EDUCATION', N'10', N'08', N'博士后', N'1', N'', N'8', N'', N'', N'', N'2', N'2021-10-21 16:37:42.000', N'2', N'2021-10-21 16:37:42.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451105441353302016', N'1451103343890923520', N'GLOBAL_EDUCATION', N'10', N'99', N'其他', N'1', N'', N'99', N'', N'', N'', N'2', N'2021-10-21 16:37:52.000', N'2', N'2021-10-21 16:37:52.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1451108231005863936', N'0', N'', N'10', N'GLOBAL_NATION', N'民族', N'1', N'', N'1', N'', N'', N'', N'2', N'2021-10-21 16:48:57.000', N'2', N'2021-10-21 16:48:57.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1454333051138998272', N'0', N'', N'10', N'TENANT_TENANT_STATUS', N'租户审核状态', N'1', N'[05-正常 10-待初始化 15-已撤回 20-待审核 25-已拒绝 30-已同意]', N'1', N'', N'', N'', N'1451549146992345088', N'2021-10-30 14:23:14.000', N'1452186486253289472', N'2021-11-14 20:17:15.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1454333103441969152', N'1454333051138998272', N'TENANT_TENANT_STATUS', N'10', N'05', N'正常', N'1', N'', N'1', N'', N'', N'', N'1451549146992345088', N'2021-10-30 14:23:27.000', N'1451549146992345088', N'2021-10-30 14:23:27.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1454333135360622592', N'1454333051138998272', N'TENANT_TENANT_STATUS', N'10', N'10', N'待初始化结构', N'1', N'', N'2', N'', N'', N'', N'1451549146992345088', N'2021-10-30 14:23:34.000', N'1451549146992345088', N'2021-10-30 14:23:34.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1454333161860235264', N'1454333051138998272', N'TENANT_TENANT_STATUS', N'10', N'15', N'待初始化数据源', N'1', N'', N'3', N'', N'', N'', N'1451549146992345088', N'2021-10-30 14:23:41.000', N'1452186486253289472', N'2021-11-14 19:48:22.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1454333189936906240', N'1454333051138998272', N'TENANT_TENANT_STATUS', N'10', N'20', N'已撤回', N'1', N'', N'4', N'', N'', N'', N'1451549146992345088', N'2021-10-30 14:23:47.000', N'1451549146992345088', N'2021-11-05 16:23:17.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1454333229619216384', N'1454333051138998272', N'TENANT_TENANT_STATUS', N'10', N'25', N'待审核', N'1', N'', N'5', N'', N'', N'', N'1451549146992345088', N'2021-10-30 14:23:57.000', N'1451549146992345088', N'2021-10-30 14:23:57.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1454333259683987456', N'1454333051138998272', N'TENANT_TENANT_STATUS', N'10', N'30', N'已拒绝', N'1', N'', N'6', N'', N'', N'', N'1451549146992345088', N'2021-10-30 14:24:04.000', N'1452186486253289472', N'2021-11-15 12:42:21.000')
GO

INSERT INTO [dbo].[def_dict]  VALUES (N'1454333259683987458', N'1454333051138998272', N'TENANT_TENANT_STATUS', N'10', N'35', N'已同意', N'1', N'', N'7', N'', N'', N'', N'1451549146992345088', N'2021-10-30 14:24:04.000', N'1452186486253289472', N'2021-11-15 12:42:21.000')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for def_gen_table
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_gen_table]') AND type IN ('U'))
	DROP TABLE [dbo].[def_gen_table]
GO

CREATE TABLE [dbo].[def_gen_table] (
  [id] bigint  NOT NULL,
  [name] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [swagger_comment] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [ds_id] bigint  NOT NULL,
  [author] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [sub_id] bigint  NULL,
  [sub_java_field_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [entity_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [entity_super_class] char(2) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [super_class] char(2) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [parent] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [plus_application_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [plus_module_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [service_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [module_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [child_package_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [is_tenant_line] bit DEFAULT ((0)) NOT NULL,
  [ds_value] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [is_ds] bit DEFAULT ((0)) NOT NULL,
  [is_lombok] bit DEFAULT ((1)) NOT NULL,
  [is_chain] bit DEFAULT ((1)) NOT NULL,
  [is_column_constant] bit DEFAULT ((0)) NOT NULL,
  [gen_type] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('01') NOT NULL,
  [output_dir] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT ('/') NULL,
  [front_output_dir] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [tpl_type] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('01') NOT NULL,
  [popup_type] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('01') NOT NULL,
  [add_auth] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [edit_auth] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [delete_auth] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [view_auth] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [copy_auth] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [add_show] bit DEFAULT ((1)) NOT NULL,
  [edit_show] bit DEFAULT ((1)) NOT NULL,
  [delete_show] bit DEFAULT ((1)) NOT NULL,
  [copy_show] bit DEFAULT ((1)) NOT NULL,
  [view_show] bit DEFAULT ((1)) NOT NULL,
  [options] varchar(1000) COLLATE Chinese_PRC_CI_AS  NULL,
  [remark] varchar(500) COLLATE Chinese_PRC_CI_AS  NULL,
  [menu_parent_id] bigint  NULL,
  [menu_application_id] bigint  NULL,
  [menu_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [menu_icon] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [tree_parent_id] bigint  NULL,
  [tree_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NOT NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NOT NULL,
  [comment_] varchar(500) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[def_gen_table] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'编号',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'表名称',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'swagger描述',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'swagger_comment'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据源',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'ds_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'作者',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'author'
GO

EXEC sp_addextendedproperty
'MS_Description', N'关联子表的ID',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'sub_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'子表关联的外键Java字段名',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'sub_java_field_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'实体类名称',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'entity_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'实体父类;
#EntitySuperClassEnum{SUPER_ENTITY:01}',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'entity_super_class'
GO

EXEC sp_addextendedproperty
'MS_Description', N'父类;

#SuperClassEnum{SUPER_CLASS:01}',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'super_class'
GO

EXEC sp_addextendedproperty
'MS_Description', N'基础包路径',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'parent'
GO

EXEC sp_addextendedproperty
'MS_Description', N'前端应用名;如：src/views目录下的basic和devOperation,basic表示基础平台。devOperation表示开发运营系统。xxx 表示你们自己新建的xxx系统。',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'plus_application_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'前端模块名;如：src/views/devOperation目录下的文件夹名
如：src/views/basic 目录下的文件夹名',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'plus_module_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'服务名',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'service_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模块名',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'module_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'子包名',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'child_package_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'行级租户注解',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'is_tenant_line'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据源',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'ds_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据源级租户注解',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'is_ds'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否为lombok模型',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'is_lombok'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否为链式模型',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'is_chain'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否生成字段常量',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'is_column_constant'
GO

EXEC sp_addextendedproperty
'MS_Description', N'生成代码方式; [01-zip压缩包 02-自定义路径]
#GenTypeEnum{GEN:01,直接生成;ZIP:02,打包下载;}
',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'gen_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'生成路径;（不填默认项目路径）',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'output_dir'
GO

EXEC sp_addextendedproperty
'MS_Description', N'前端生成路径;（不填默认项目路径）',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'front_output_dir'
GO

EXEC sp_addextendedproperty
'MS_Description', N'使用的模板; #TplEnum{SIMPLE:01,单表;TREE:02,树结构;MAIN_SUB:03,主从结构}',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'tpl_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'弹窗方式; #PopupTypeEnum{MODAL:01,对话框;DRAWER:02,抽屉;}',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'popup_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'新增按钮权限编码',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'add_auth'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编辑按钮权限编码',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'edit_auth'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除按钮权限编码',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'delete_auth'
GO

EXEC sp_addextendedproperty
'MS_Description', N'查看按钮权限编码',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'view_auth'
GO

EXEC sp_addextendedproperty
'MS_Description', N'复制按钮权限编码',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'copy_auth'
GO

EXEC sp_addextendedproperty
'MS_Description', N'新增按钮是否显示',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'add_show'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编辑按钮是否显示',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'edit_show'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除按钮是否显示',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'delete_show'
GO

EXEC sp_addextendedproperty
'MS_Description', N'复制按钮是否显示',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'copy_show'
GO

EXEC sp_addextendedproperty
'MS_Description', N'详情按钮是否显示',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'view_show'
GO

EXEC sp_addextendedproperty
'MS_Description', N'其它生成选项',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'options'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'remark'
GO

EXEC sp_addextendedproperty
'MS_Description', N'上级菜单ID',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'menu_parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属应用ID',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'menu_application_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'菜单名称',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'menu_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'菜单图标',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'menu_icon'
GO

EXEC sp_addextendedproperty
'MS_Description', N'父ID字段名',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'tree_parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称字段名',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'tree_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建者',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新者',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'表描述',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table',
'COLUMN', N'comment_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'代码生成',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table'
GO


-- ----------------------------
-- Table structure for def_gen_table_column
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_gen_table_column]') AND type IN ('U'))
	DROP TABLE [dbo].[def_gen_table_column]
GO

CREATE TABLE [dbo].[def_gen_table_column] (
  [id] bigint  NOT NULL,
  [table_id] bigint  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [swagger_comment] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [type] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [java_type] varchar(512) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [java_field] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [ts_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [is_pk] bit DEFAULT ((0)) NOT NULL,
  [is_increment] bit DEFAULT ((0)) NOT NULL,
  [is_required] bit DEFAULT ((0)) NOT NULL,
  [is_logic_delete_field] bit DEFAULT ((0)) NOT NULL,
  [is_version_field] bit DEFAULT ((0)) NOT NULL,
  [fill] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [is_edit] bit DEFAULT ((1)) NOT NULL,
  [is_list] bit DEFAULT ((1)) NOT NULL,
  [is_query] bit DEFAULT ((0)) NOT NULL,
  [width] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [query_type] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT ('03') NULL,
  [component] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [vxe_component] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [dict_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [echo_str] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [enum_str] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [sort_value] int DEFAULT ((0)) NULL,
  [edit_def_value] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [edit_help_message] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [index_help_message] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [comment_] varchar(512) COLLATE Chinese_PRC_CI_AS  NULL,
  [size_] bigint DEFAULT ((0)) NOT NULL,
  [digit] int DEFAULT ((0)) NULL
)
GO

ALTER TABLE [dbo].[def_gen_table_column] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属表ID',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'table_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'列名称',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文档描述',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'swagger_comment'
GO

EXEC sp_addextendedproperty
'MS_Description', N'列类型',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'JAVA类型',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'java_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'JAVA字段名',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'java_field'
GO

EXEC sp_addextendedproperty
'MS_Description', N'TS类型',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'ts_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'is_pk'
GO

EXEC sp_addextendedproperty
'MS_Description', N'自增',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'is_increment'
GO

EXEC sp_addextendedproperty
'MS_Description', N'必填',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'is_required'
GO

EXEC sp_addextendedproperty
'MS_Description', N'逻辑删除',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'is_logic_delete_field'
GO

EXEC sp_addextendedproperty
'MS_Description', N'乐观锁',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'is_version_field'
GO

EXEC sp_addextendedproperty
'MS_Description', N'填充类型;
#FieldFill{INSERT:1}',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'fill'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编辑',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'is_edit'
GO

EXEC sp_addextendedproperty
'MS_Description', N'列表',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'is_list'
GO

EXEC sp_addextendedproperty
'MS_Description', N'查询',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'is_query'
GO

EXEC sp_addextendedproperty
'MS_Description', N'宽度',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'width'
GO

EXEC sp_addextendedproperty
'MS_Description', N'查询方式;
#SqlConditionEnum{EQUAL:01}
（等于、不等于、大于、小于、范围）',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'query_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'组件;（文本框、文本域、下拉框、复选框、单选框、日期控件）',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'component'
GO

EXEC sp_addextendedproperty
'MS_Description', N'Vxe组件;（文本框、文本域、下拉框、复选框、单选框、日期控件）',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'vxe_component'
GO

EXEC sp_addextendedproperty
'MS_Description', N'字典类型',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'dict_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'Echo',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'echo_str'
GO

EXEC sp_addextendedproperty
'MS_Description', N'枚举',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'enum_str'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'默认值',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'edit_def_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'提示信息',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'edit_help_message'
GO

EXEC sp_addextendedproperty
'MS_Description', N'列表提示信息',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'index_help_message'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建者',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新者',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'列描述',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'comment_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'长度',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'size_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'小数位数',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column',
'COLUMN', N'digit'
GO

EXEC sp_addextendedproperty
'MS_Description', N'代码生成字段',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_table_column'
GO


-- ----------------------------
-- Table structure for def_gen_test_simple
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_gen_test_simple]') AND type IN ('U'))
	DROP TABLE [dbo].[def_gen_test_simple]
GO

CREATE TABLE [dbo].[def_gen_test_simple] (
  [id] bigint  NOT NULL,
  [name] varchar(24) COLLATE Chinese_PRC_CI_AS DEFAULT ('大法师') NOT NULL,
  [parent_id] bigint  NULL,
  [sort_value] int DEFAULT ((22)) NULL,
  [stock] int  NOT NULL,
  [created_time] datetime  NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [updated_by] bigint DEFAULT ((123123)) NULL,
  [type_] text COLLATE Chinese_PRC_CI_AS  NULL,
  [type2] text COLLATE Chinese_PRC_CI_AS  NULL,
  [type3] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [state] bit DEFAULT ((0)) NULL,
  [test4] int  NULL,
  [test5] date  NULL,
  [test6] datetime  NULL,
  [label] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [test7] char(10) COLLATE Chinese_PRC_CI_AS DEFAULT ('01') NULL,
  [test12] int  NULL,
  [user_id] bigint  NULL,
  [org_id] bigint  NULL,
  [test8] decimal(16,4) DEFAULT ((21.2300)) NULL,
  [test9] float(53)  NULL,
  [test10] decimal(24,6)  NULL,
  [test11] decimal(2)  NULL
)
GO

ALTER TABLE [dbo].[def_gen_test_simple] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'父id',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'库存',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'stock'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'商品类型;;
#ProductType{ordinary:普通;gift:赠品}',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'type_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'商品类型2;;
#{ordinary:01,普通;gift:02,赠品;}',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'type2'
GO

EXEC sp_addextendedproperty
'MS_Description', N'学历;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS,  dictType = EchoDictType.Global.EDUCATION)',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'type3'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'测试',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'test4'
GO

EXEC sp_addextendedproperty
'MS_Description', N'时间',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'test5'
GO

EXEC sp_addextendedproperty
'MS_Description', N'日期',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'test6'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'label'
GO

EXEC sp_addextendedproperty
'MS_Description', N'字符字典;@Echo(api = "ttop.tangyh.lamp.common.api.DictApi", dictType="GLOBAL_SEX")',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'test7'
GO

EXEC sp_addextendedproperty
'MS_Description', N'整形字典;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.TEST_ADD_DICT)[1-测试 2-新增 aad-haha]',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'test12'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户;@Echo(api = EchoApi.POSITION_ID_CLASS)',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'user_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'组织;@Echo(api = EchoApi.ORG_ID_CLASS)',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'小数',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'test8'
GO

EXEC sp_addextendedproperty
'MS_Description', N'浮点2',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'test9'
GO

EXEC sp_addextendedproperty
'MS_Description', N'浮点',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'test10'
GO

EXEC sp_addextendedproperty
'MS_Description', N'xiao树',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple',
'COLUMN', N'test11'
GO

EXEC sp_addextendedproperty
'MS_Description', N'测试树结构',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_simple'
GO


-- ----------------------------
-- Table structure for def_gen_test_tree
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_gen_test_tree]') AND type IN ('U'))
	DROP TABLE [dbo].[def_gen_test_tree]
GO

CREATE TABLE [dbo].[def_gen_test_tree] (
  [id] bigint  NOT NULL,
  [name] varchar(24) COLLATE Chinese_PRC_CI_AS DEFAULT ('大法师') NOT NULL,
  [parent_id] bigint  NULL,
  [sort_value] int DEFAULT ((22)) NULL,
  [stock] int  NOT NULL,
  [created_time] datetime  NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [updated_by] bigint DEFAULT ((123123)) NULL,
  [type_] text COLLATE Chinese_PRC_CI_AS  NULL,
  [type2] text COLLATE Chinese_PRC_CI_AS  NULL,
  [type3] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [state] bit DEFAULT ((0)) NULL,
  [test4] int  NULL,
  [test5] date  NULL,
  [test6] datetime  NULL,
  [label] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [test7] char(10) COLLATE Chinese_PRC_CI_AS DEFAULT ('01') NULL,
  [test12] int  NULL,
  [user_id] bigint  NULL,
  [org_id] bigint  NULL,
  [test8] decimal(16,4) DEFAULT ((21.2300)) NULL,
  [test9] float(53)  NULL,
  [test10] decimal(24,6)  NULL,
  [test11] decimal(2)  NULL
)
GO

ALTER TABLE [dbo].[def_gen_test_tree] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'父id',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'库存',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'stock'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'商品类型;
#ProductType{ordinary:普通;gift:赠品}',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'type_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'商品类型2;#{ordinary:01,普通;gift:02,赠品;}',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'type2'
GO

EXEC sp_addextendedproperty
'MS_Description', N'学历;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS,  dictType = EchoDictType.Global.EDUCATION)',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'type3'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'测试',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'test4'
GO

EXEC sp_addextendedproperty
'MS_Description', N'时间',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'test5'
GO

EXEC sp_addextendedproperty
'MS_Description', N'日期',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'test6'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'label'
GO

EXEC sp_addextendedproperty
'MS_Description', N'字符字典;@Echo(api = "ttop.tangyh.lamp.common.api.DictApi", dictType="GLOBAL_SEX")',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'test7'
GO

EXEC sp_addextendedproperty
'MS_Description', N'整形字典;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.TEST_ADD_DICT)[1-测试 2-新增 aad-haha]',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'test12'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户;@Echo(api = EchoApi.POSITION_ID_CLASS)[1-aa 2-ddd]',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'user_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'组织;@Echo(api = EchoApi.ORG_ID_CLASS)',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'小数',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'test8'
GO

EXEC sp_addextendedproperty
'MS_Description', N'浮点2',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'test9'
GO

EXEC sp_addextendedproperty
'MS_Description', N'浮点',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'test10'
GO

EXEC sp_addextendedproperty
'MS_Description', N'xiao树',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree',
'COLUMN', N'test11'
GO

EXEC sp_addextendedproperty
'MS_Description', N'测试树结构',
'SCHEMA', N'dbo',
'TABLE', N'def_gen_test_tree'
GO


-- ----------------------------
-- Table structure for def_interface
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_interface]') AND type IN ('U'))
	DROP TABLE [dbo].[def_interface]
GO

CREATE TABLE [dbo].[def_interface] (
  [id] bigint  NOT NULL,
  [code] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [exec_mode] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('01') NULL,
  [script] text COLLATE Chinese_PRC_CI_AS  NULL,
  [impl_class] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [state] bit DEFAULT ((1)) NULL,
  [created_time] datetime  NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[def_interface] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口编码',
'SCHEMA', N'dbo',
'TABLE', N'def_interface',
'COLUMN', N'code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口名称',
'SCHEMA', N'dbo',
'TABLE', N'def_interface',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'执行方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.INTERFACE_EXEC_MODE)[01-实现类 02-脚本]',
'SCHEMA', N'dbo',
'TABLE', N'def_interface',
'COLUMN', N'exec_mode'
GO

EXEC sp_addextendedproperty
'MS_Description', N'实现脚本',
'SCHEMA', N'dbo',
'TABLE', N'def_interface',
'COLUMN', N'script'
GO

EXEC sp_addextendedproperty
'MS_Description', N'实现类',
'SCHEMA', N'dbo',
'TABLE', N'def_interface',
'COLUMN', N'impl_class'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'def_interface',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_interface',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'def_interface',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'def_interface',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'def_interface',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口',
'SCHEMA', N'dbo',
'TABLE', N'def_interface'
GO


-- ----------------------------
-- Records of def_interface
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[def_interface]  VALUES (N'244439130119864323', N'ALI_SMS', N'阿里短信', N'01', N'', N'aliSmsMsgStrategyImpl', N'1', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface]  VALUES (N'244881451621810192', N'TENCENT_MAIL', N'腾讯邮件', N'01', N'', N'tencentMailMsgStrategyImpl', N'1', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface]  VALUES (N'244913337459015682', N'CHUANGLAN_SMS', N'创蓝短信', N'01', N'', N'clSmsMsgStrategyImpl', N'1', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface]  VALUES (N'250025856074776718', N'BAIDU_SMS', N'百度短信', N'01', NULL, N'baiduSmsMsgStrategyImpl', N'1', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface]  VALUES (N'250025856074776719', N'TENCENT_SMS', N'腾讯短信', N'01', NULL, N'tencentSmsMsgStrategyImpl', N'1', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface]  VALUES (N'251763346439667712', N'TEST', N'测试通过脚本执行接口逻辑', N'02', N'package top.tangyh.lamp.msg.strategy.impl;\n\nimport org.slf4j.Logger;\nimport org.slf4j.LoggerFactory;\nimport top.tangyh.lamp.msg.entity.ExtendMsg;\nimport top.tangyh.lamp.msg.service.ExtendMsgService;\nimport top.tangyh.lamp.msg.strategy.MsgStrategy;\nimport top.tangyh.lamp.msg.strategy.domain.MsgParam;\nimport top.tangyh.lamp.msg.strategy.domain.MsgResult;\n\nimport javax.annotation.Resource;\n\n/**\n * @author zuihou\n * @date 2022/7/11 0011 10:29\n */\npublic class TestMsgStrategyImpl implements MsgStrategy {\n    private static final Logger log = LoggerFactory.getLogger(TestMsgStrategyImpl.class);\n\n    @Resource\n    private ExtendMsgService extendMsgService;\n\n    @Override\n    public MsgResult exec(MsgParam msgParam) {\n        System.out.println(\" 请开始你的接口逻辑 \");\n\n        ExtendMsg a = extendMsgService.getById(msgParam.getExtendMsg().getId());\n        log.info(\"a {}\", a);\n\n        return MsgResult.builder().result(\"保存成功\").build();\n    }\n}', N'', N'1', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for def_interface_property
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_interface_property]') AND type IN ('U'))
	DROP TABLE [dbo].[def_interface_property]
GO

CREATE TABLE [dbo].[def_interface_property] (
  [id] bigint  NOT NULL,
  [interface_id] bigint  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [key_] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [value] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [sort_value] int DEFAULT ((0)) NULL,
  [remarks] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [created_time] datetime  NOT NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NOT NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[def_interface_property] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'def_interface_property',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口ID',
'SCHEMA', N'dbo',
'TABLE', N'def_interface_property',
'COLUMN', N'interface_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数名称',
'SCHEMA', N'dbo',
'TABLE', N'def_interface_property',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数键',
'SCHEMA', N'dbo',
'TABLE', N'def_interface_property',
'COLUMN', N'key_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数值',
'SCHEMA', N'dbo',
'TABLE', N'def_interface_property',
'COLUMN', N'value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'顺序号',
'SCHEMA', N'dbo',
'TABLE', N'def_interface_property',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'def_interface_property',
'COLUMN', N'remarks'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_interface_property',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'def_interface_property',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'def_interface_property',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'def_interface_property',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口属性',
'SCHEMA', N'dbo',
'TABLE', N'def_interface_property'
GO


-- ----------------------------
-- Records of def_interface_property
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'245606910252810240', N'244913337459015682', N'是否debug模式', N'debug', N'0', N'0', N'1不发短信 0发短信', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'245606910252810244', N'244913337459015682', N'普通短信接口', N'endPoint', N'http://smssh1.253.com/msg/v1/send/json', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'245606910252810245', N'244913337459015682', N'签名', N'sign', N'闪购网', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'245606910252810246', N'244913337459015682', N'变量短信接口', N'variableEndPoint', N'http://smssh1.253.com/msg/variable/json', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'245606910252810248', N'244913337459015682', N'云通讯API密码', N'password', N'请填写正确的秘钥', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'245606910252810252', N'244913337459015682', N'云通讯API账号', N'account', N'请填写正确的秘钥', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'246604205953908742', N'244913337459015682', N'是否变量短信', N'variable', N'true', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'246756273565990915', N'244881451621810192', N'发送邮箱地址', N'fromEmail', N'admin@tangyh.top', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'246756273565990916', N'244881451621810192', N'发送邮箱名称', N'fromName', N'闪购网', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'246756273565990917', N'244881451621810192', N'邮件服务器地址', N'hostName', N'smtp.qq.com', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'246756273565990918', N'244881451621810192', N'密码', N'password', N'1234', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'246756273565990919', N'244881451621810192', N'用户名', N'username', N'admin@qq.com', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'246756273565990920', N'244881451621810192', N'端口', N'smtpPort', N'25', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'246756273565990921', N'244881451621810192', N'是否ssl', N'ssl', N'true', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'246756273565990922', N'244881451621810192', N'字符集', N'charset', N'UTF-8', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250025856074776720', N'244439130119864323', N'是否调试', N'debug', N'0', N'0', N'1-不发短信 0-发短信', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250025856074776721', N'244439130119864323', N'Access Key ID', N'accessKeyId', N'请填写正确的秘钥', N'0', N'发送账号安全认证的Access Key ID', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250025856074776722', N'244439130119864323', N'Secret Access Key', N'accessKeySecret', N'请填写正确的秘钥', N'0', N'发送账号安全认证的Secret Access Key', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250025856074776723', N'244439130119864323', N'发送使用签名', N'signName', N'闪购网', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250025856074776724', N'244439130119864323', N'地域ID', N'regionId', N'cn-hangzhou', N'0', N'https://help.aliyun.com/document_detail/419270.html', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250025856074776726', N'244439130119864323', N'域名', N'endpoint', N'dysmsapi.aliyuncs.com', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250660012290998272', N'250025856074776718', N'域名', N'endPoint', N'http://smsv3.bj.baidubce.com', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250660012290998273', N'250025856074776718', N'secretKey', N'secretKey', N'bacbedee5a604a939cc4c996371856b8', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250660012290998274', N'250025856074776718', N'accessKeyId', N'accessKeyId', N'626e6a284eac4e3f97cc30b38ffea6a4', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250660012290998276', N'250025856074776718', N'是否调试模式', N'debug', N'0', N'0', N'0-非调试模式,发送短信  1-调试模式,不发短信', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250660012290998277', N'250025856074776719', N'短信 SdkAppId', N'sdkAppId', N'1400006666', N'0', N'在 短信控制台 添加应用后生成的实际 SdkAppId，示例如1400006666', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250660012290998278', N'250025856074776719', N'指定接入地域域名', N'region', N'ap-beijing', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250660012290998279', N'250025856074776719', N'secretId', N'secretId', N'44', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250660012290998280', N'250025856074776719', N'secretKey', N'secretKey', N'123', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

INSERT INTO [dbo].[def_interface_property]  VALUES (N'250660012290998281', N'250025856074776719', N'地域域名', N'endpoint', N'sms.tencentcloudapi.com', N'0', N'', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for def_login_log
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_login_log]') AND type IN ('U'))
	DROP TABLE [dbo].[def_login_log]
GO

CREATE TABLE [dbo].[def_login_log] (
  [id] bigint  NOT NULL,
  [tenant_id] bigint  NULL,
  [employee_id] bigint  NULL,
  [user_id] bigint  NULL,
  [request_ip] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [nick_name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [username] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [status] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('01') NULL,
  [description] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [login_date] char(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [ua] varchar(512) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [browser] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [browser_version] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [operating_system] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [location] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [created_time] datetime  NULL,
  [created_by] bigint  NULL,
  [updated_time] datetime  NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[def_login_log] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属企业',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'tenant_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录员工',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'employee_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录用户',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'user_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录IP',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'request_ip'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录人姓名',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'nick_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录人账号',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'username'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录状态;[01-登录成功 02-验证码错误 03-密码错误 04-账号锁定]
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.LOGIN_STATUS)',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录描述',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录时间',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'login_date'
GO

EXEC sp_addextendedproperty
'MS_Description', N'浏览器请求头',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'ua'
GO

EXEC sp_addextendedproperty
'MS_Description', N'浏览器名称',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'browser'
GO

EXEC sp_addextendedproperty
'MS_Description', N'浏览器版本',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'browser_version'
GO

EXEC sp_addextendedproperty
'MS_Description', N'操作系统',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'operating_system'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录地点',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'location'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新时间',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新人',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录日志',
'SCHEMA', N'dbo',
'TABLE', N'def_login_log'
GO


-- ----------------------------
-- Table structure for def_msg_template
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_msg_template]') AND type IN ('U'))
	DROP TABLE [dbo].[def_msg_template]
GO

CREATE TABLE [dbo].[def_msg_template] (
  [id] bigint  NOT NULL,
  [interface_id] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
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

ALTER TABLE [dbo].[def_msg_template] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板ID',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口ID',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'interface_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消息类型;[01-短信 02-邮件 03-站内信];@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板标识',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板名称',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板编码',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'template_code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'签名',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'sign'
GO

EXEC sp_addextendedproperty
'MS_Description', N'标题',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'title'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板内容',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'content'
GO

EXEC sp_addextendedproperty
'MS_Description', N'脚本',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'script'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板参数',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'param'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'remarks'
GO

EXEC sp_addextendedproperty
'MS_Description', N'打开方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)[01-页面 02-弹窗 03-新开窗口]',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'target_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'自动已读',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'auto_read'
GO

EXEC sp_addextendedproperty
'MS_Description', N'提醒方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)[01-待办 02-预警 03-提醒]',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'remind_mode'
GO

EXEC sp_addextendedproperty
'MS_Description', N'跳转地址',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人ID',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消息模板',
'SCHEMA', N'dbo',
'TABLE', N'def_msg_template'
GO


-- ----------------------------
-- Records of def_msg_template
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[def_msg_template]  VALUES (N'245657569392066565', N'244881451621810192', N'02', N'TENCENT_EMAIL', N'腾讯邮件', N'1', NULL, N'', N'你的一份邮件', N'邮件内容3 ${xx}, ddd, 

<br/>
<p style="color: red;">red</p>', N'', N'3', N'3', N'02', N'1', N'02', N'44', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2022-08-17 09:55:38.930')
GO

INSERT INTO [dbo].[def_msg_template]  VALUES (N'245891967232245772', N'244913337459015682', N'01', N'CHUAGNLAN_REG_SMS', N'注册短信', N'1', NULL, N'盘江煤电', N'注册', N'采购项目【${xmmc}】发起了${lbmc}质疑，等待您的答复', N'', N'', N'chuanglan短信', NULL, N'1', NULL, N'', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000')
GO

INSERT INTO [dbo].[def_msg_template]  VALUES (N'250055645967941632', N'244439130119864323', N'01', N'REGISTER_SMS', N'注册成功短信', N'1', N'SMS_99185070', N'闪购网', N'', N'尊敬的用户，欢迎注册闪购网，您的注册验证码：${code},有效期5分钟。请勿将短信验证码告知他人！', N'', N'', N'', NULL, N'1', NULL, N'', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000')
GO

INSERT INTO [dbo].[def_msg_template]  VALUES (N'250660012290998275', N'250025856074776718', N'01', N'BAIDU_SMS_REG', N'百度注册短信', N'1', N'sms-tmpl-awKvRY85349', N'sms-signQxkiwz88470', NULL, N'您的验证码为：${code}, ${minute}分钟内有效', N'', N'', N'', NULL, N'1', NULL, N'', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000')
GO

INSERT INTO [dbo].[def_msg_template]  VALUES (N'250660012290998282', N'250025856074776719', N'01', N'TX_SMS', N'腾讯注册短信', N'1', N'1234', N'腾讯云', NULL, N'你的验证阿妈为： ${code}', NULL, N'', N'', NULL, N'1', NULL, N'', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000')
GO

INSERT INTO [dbo].[def_msg_template]  VALUES (N'251763346439667713', N'251763346439667712', N'03', N'TEST', N'测试', N'1', NULL, N'', N'发送一个xx', N'发送 ${xmmc}, 哈哈哈 ${name}.', NULL, N'', N'', N'02', N'1', N'01', N'', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000')
GO

INSERT INTO [dbo].[def_msg_template]  VALUES (N'252969897242394624', N'244439130119864323', N'01', N'MOBILE_LOGIN', N'手机登录短信', N'1', N'SMS_99185070', N'闪购网', N'', N'本次验证码为：${code}', N'', N'', N'', NULL, N'1', NULL, N'', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2021-12-12 12:12:12.000')
GO

INSERT INTO [dbo].[def_msg_template]  VALUES (N'252969897242394625', N'244881451621810192', N'02', N'REGISTER_EMAIL', N'注册邮件验证码', N'1', NULL, N'', N'欢迎注册${applicationName}', N'尊敬的用户：
	欢迎注册${applicationName}，您的注册验证码为：${code}', N'// 逻辑


// 返回
[
  applicationName: "《灯灯中后台快速开发平台》",
  code: code
]', N'', N'', NULL, N'1', NULL, N'', N'1452186486253289472', N'2021-12-12 12:12:12.000', N'1452186486253289472', N'2022-08-17 09:54:39.606')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for def_parameter
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_parameter]') AND type IN ('U'))
	DROP TABLE [dbo].[def_parameter]
GO

CREATE TABLE [dbo].[def_parameter] (
  [id] bigint  NOT NULL,
  [key_] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [value] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [remarks] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [state] bit DEFAULT ((1)) NULL,
  [param_type] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('20') NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NOT NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NOT NULL
)
GO

ALTER TABLE [dbo].[def_parameter] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'def_parameter',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数键',
'SCHEMA', N'dbo',
'TABLE', N'def_parameter',
'COLUMN', N'key_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数值',
'SCHEMA', N'dbo',
'TABLE', N'def_parameter',
'COLUMN', N'value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数名称',
'SCHEMA', N'dbo',
'TABLE', N'def_parameter',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'def_parameter',
'COLUMN', N'remarks'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'def_parameter',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类型;[10-系统参数 20-业务参数]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.PARAMETER_TYPE)',
'SCHEMA', N'dbo',
'TABLE', N'def_parameter',
'COLUMN', N'param_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'def_parameter',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_parameter',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人id',
'SCHEMA', N'dbo',
'TABLE', N'def_parameter',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'def_parameter',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数配置',
'SCHEMA', N'dbo',
'TABLE', N'def_parameter'
GO


-- ----------------------------
-- Table structure for def_resource
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_resource]') AND type IN ('U'))
	DROP TABLE [dbo].[def_resource]
GO

CREATE TABLE [dbo].[def_resource] (
  [id] bigint  NOT NULL,
  [application_id] bigint  NOT NULL,
  [code] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [resource_type] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('20') NOT NULL,
  [parent_id] bigint  NOT NULL,
  [open_with] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT ('01') NULL,
  [describe_] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [path] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [component] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [redirect] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [icon] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [is_general] bit DEFAULT ((0)) NULL,
  [state] bit DEFAULT ((1)) NULL,
  [sort_value] int DEFAULT ((1)) NULL,
  [sub_group] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [field_is_secret] bit DEFAULT ((0)) NULL,
  [field_is_edit] bit DEFAULT ((1)) NULL,
  [data_scope] char(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [custom_class] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [is_def] bit  NULL,
  [tree_path] varchar(512) COLLATE Chinese_PRC_CI_AS DEFAULT ('/') NULL,
  [tree_grade] int DEFAULT ((0)) NULL,
  [meta_json] varchar(512) COLLATE Chinese_PRC_CI_AS DEFAULT ('{}') NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[def_resource] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'应用ID;#def_application',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'application_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编码;唯一编码，用于区分资源',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类型;[20-菜单 30-视图 40-按钮 50-字段 60-数据]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS,dictType = EchoDictType.System.RESOURCE_TYPE)菜单即左侧显示的菜单视图即隐藏的菜单(需要配置在路由中)和页面上点击后需要通过路由打开的页面功能即页面上的非视图的按钮字段即列表页或编辑页的字段接口即后台的访问接口',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'resource_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'父级ID',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'打开方式;[01-组件 02-内链 03-外链]
@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.RESOURCE_OPEN_WITH)',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'open_with'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述;resource_type=接口时表示接口说明',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'describe_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'地址栏路径;用于resource_type=菜单和视图和接口.resource_type=菜单和视图，表示地址栏地址, http开头表示外链, is_frame_src 为true表示在框架类打开.resource_type=接口，表示后端接口请求地址.',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'页面路径;用于resource_type=菜单和视图. 前端页面在src/views目录下的相对地址.',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'component'
GO

EXEC sp_addextendedproperty
'MS_Description', N'重定向;用于resource_type=菜单和视图',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'redirect'
GO

EXEC sp_addextendedproperty
'MS_Description', N'图标',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'icon'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否公共资源;1-无需分配所有人就可以访问的',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'is_general'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态;[0-禁用 1-启用]',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序;默认升序',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'分组',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'sub_group'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否脱敏;显示时是否需要脱敏实现 (用于resource_type=字段)',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'field_is_secret'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否可以编辑;是否可以编辑(用于resource_type=字段)',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'field_is_edit'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据范围;[01-全部 02-本单位及子级 03-本单位 04-本部门及子级 05-本部门 06-个人 07-自定义]',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'data_scope'
GO

EXEC sp_addextendedproperty
'MS_Description', N'实现类;自定义实现类全类名',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'custom_class'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否默认',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'is_def'
GO

EXEC sp_addextendedproperty
'MS_Description', N'树路径',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'tree_path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'树层级',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'tree_grade'
GO

EXEC sp_addextendedproperty
'MS_Description', N'元数据;菜单视图的元数据',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'meta_json'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人id',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'def_resource',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'资源',
'SCHEMA', N'dbo',
'TABLE', N'def_resource'
GO


-- ----------------------------
-- Records of def_resource
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'137848577387921409', N'2', N'tenant:application', N'全局管理', N'20', N'0', N'01', N'', N'/application', N'LAYOUT', NULL, N'ant-design:appstore-add-outlined', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{}', N'2', N'2021-09-21 13:16:29.000', N'1452186486253289472', N'2022-05-05 16:38:07.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'137848577387921412', N'2', N'tenant:tenant:user', N'用户维护', N'20', N'137848577387921409', N'01', N'', N'/tenant/user', N'/devOperation/tenant/defUser/index', NULL, N'ant-design:user-add-outlined', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/', N'1', N'{}', N'2', N'2021-09-21 13:37:56.000', N'1452186486253289472', N'2021-12-12 13:14:03.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'137848577387921413', N'2', N'tenant:application:application', N'应用维护', N'20', N'137848577387921409', N'01', N'', N'application', N'/devOperation/application/defApplication/index', NULL, N'ant-design:appstore-twotone', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/', N'1', N'{"hideChildrenInMenu":true}', N'2', N'2021-09-21 21:57:06.000', N'1452186486253289472', N'2021-12-12 13:24:38.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'137848577387921414', N'2', N'tenant:application:resource', N'资源维护', N'20', N'137848577387921409', N'01', N'', N'resource', N'/devOperation/application/defResource/index', NULL, N'ant-design:menu-unfold-outlined', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/', N'1', N'{}', N'2', N'2021-09-21 21:58:07.000', N'1452186486253289472', N'2021-12-12 17:29:18.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'137848577387921417', N'2', N'tenant:application:application:add', N'新增', N'40', N'137848577387921413', N'01', N'', N'', N'', NULL, N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921413/', N'2', N'{}', N'2', N'2021-09-21 22:51:42.000', N'1452186486253289472', N'2021-12-12 13:26:16.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'137848577387921418', N'2', N'tenant:application:application:edit', N'编辑', N'40', N'137848577387921413', N'01', N'', N'', N'', NULL, N'', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921413/', N'2', N'{}', N'2', N'2021-09-21 22:52:00.000', N'1452186486253289472', N'2021-12-12 15:49:05.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'137848577387921419', N'2', N'tenant:application:application:copy', N'复制', N'40', N'137848577387921413', N'01', N'', N'', N'', NULL, N'', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921413/', N'2', N'{}', N'2', N'2021-09-21 22:52:14.000', N'1452186486253289472', N'2021-12-12 15:48:50.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'137848577387921420', N'2', N'tenant:application:application:delete', N'删除', N'40', N'137848577387921413', N'01', N'', N'', N'', NULL, N'', N'0', N'1', N'40', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921413/', N'2', N'{}', N'2', N'2021-09-21 22:52:27.000', N'2', N'2021-09-21 22:52:27.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'137848577387921421', N'2', N'tenant:application:application:resource', N'应用资源维护', N'30', N'137848577387921413', N'01', N'', N'resource/:id', N'/devOperation/application/defResource/index', NULL, N'', N'0', N'1', N'50', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921413/', N'2', N'{}', N'2', N'2021-09-21 22:54:48.000', N'1452186486253289472', N'2021-12-12 15:53:35.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'137848577387921422', N'2', N'tenant:application:application:resource:add', N'新增', N'40', N'137848577387921421', N'01', N'', N'', N'', NULL, N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921413/137848577387921421/', N'3', N'{}', N'2', N'2021-09-21 22:55:28.000', N'1452186486253289472', N'2021-12-12 15:58:47.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138191972908138496', N'3', N'111', N'1级菜单', N'20', N'0', N'01', N'', N'/test1', N'lamp/test/index1', NULL, N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{}', N'2', N'2021-09-22 18:10:29.000', N'1452186486253289472', N'2021-11-17 15:19:49.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138191972908138497', N'3', N'22', N'多级', N'20', N'0', N'01', N'', N'/test2', N'LAYOUT', NULL, N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{}', N'2', N'2021-09-22 18:11:01.000', N'1', N'2021-11-22 14:44:30.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138191972908138498', N'3', N'222-1', N'多级-菜单1', N'20', N'138191972908138497', N'01', N'', N'/test2/test1', N'lamp/test/index1', NULL, N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/', N'1', N'{}', N'2', N'2021-09-22 18:12:01.000', N'1', N'2021-11-22 14:51:04.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138191972908138499', N'3', N'333', N'多级-菜单2', N'20', N'138191972908138497', N'01', N'', N'/test2/test2', N'lamp/test/index4', NULL, N'', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/', N'1', N'{}', N'2', N'2021-09-22 18:12:21.000', N'1', N'2021-11-22 14:51:09.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138191972908138500', N'3', N'444', N'多级-资源3', N'30', N'138191972908138497', N'01', N'', N'/test2/test3', N'lamp/test/index3', NULL, N'', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/', N'1', N'{}', N'2', N'2021-09-22 18:12:48.000', N'1', N'2021-11-22 14:50:59.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138191972908138501', N'3', N'555', N'多级-菜单4', N'20', N'138191972908138497', N'01', N'', N'/test2/test4', N'lamp/test/index4', NULL, N'', N'0', N'1', N'40', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/', N'1', N'{}', N'2', N'2021-09-22 18:13:23.000', N'1', N'2021-11-22 14:51:14.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138191972908138502', N'3', N'555:1', N'多级-菜单4-视图1', N'30', N'138191972908138501', N'01', N'', N'/test2/test4/test1', N'lamp/test/index1', NULL, N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/138191972908138501/', N'2', N'{}', N'2', N'2021-09-22 18:14:04.000', N'2', N'2021-09-22 18:14:04.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138191972908138503', N'3', N'555:2', N'多级-菜单4-菜单2', N'20', N'138191972908138501', N'01', N'', N'/test2/test4/test2', N'lamp/test/index2', NULL, N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/138191972908138501/', N'2', N'{}', N'2', N'2021-09-22 18:14:57.000', N'2', N'2021-09-22 18:14:57.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138375999371870208', N'3', N'55555', N'1级下有视图', N'20', N'0', N'01', N'', N'/test3', N'lamp/test/index5', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{"hideChildrenInMenu":true}', N'2', N'2021-09-22 22:43:25.000', N'2', N'2021-09-22 22:46:47.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138375999371870209', N'3', N'5555:1', N'1级下面视图1', N'30', N'138375999371870208', N'01', N'', N'/test3/test1', N'lamp/test/index1', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/138375999371870208/', N'1', N'{}', N'2', N'2021-09-22 22:45:10.000', N'1', N'2021-11-22 16:56:18.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138375999371870210', N'3', N'5555:2', N'1级下面视图2', N'30', N'138375999371870208', N'01', N'', N'/test3/test2', N'lamp/test/index2', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/138375999371870208/', N'1', N'{}', N'2', N'2021-09-22 22:45:48.000', N'2', N'2021-09-27 21:17:54.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138555971386474496', N'3', N'1级外链', N'1级外链', N'20', N'0', N'03', N'', N'https://vvbin.cn/doc-next', N'IFRAME', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{}', N'2', N'2021-09-23 10:12:57.000', N'1', N'2021-11-22 16:10:46.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138555971386474497', N'3', N'1级内嵌', N'1级内嵌', N'20', N'0', N'02', N'', N'/1jiinner', N'https://pro.tangyh.top/api/doc.html', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{}', N'2', N'2021-09-23 10:13:31.000', N'1', N'2021-11-22 17:17:45.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138555971386474498', N'3', N'2级外链', N'2级外链', N'20', N'138191972908138497', N'03', N'', N'https://cn.bing.com/', N'IFRAME', N'', N'', N'0', N'1', N'50', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/', N'1', N'{}', N'2', N'2021-09-23 10:14:14.000', N'1', N'2021-11-22 14:51:27.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138555971386474499', N'3', N'2级内嵌', N'2级内嵌', N'20', N'138191972908138497', N'02', N'', N'google', N'https://bm.ruankao.org.cn/sign/welcome#test=2', N'', N'', N'0', N'1', N'60', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/', N'1', N'{}', N'2', N'2021-09-23 10:14:37.000', N'1', N'2021-11-22 17:17:18.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138555971386474501', N'3', N'菜单下面创建菜单', N'菜单下面创建菜单', N'20', N'138191972908138498', N'01', N'', N'index222', N'lamp/test/index2', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/138191972908138498/', N'2', N'{}', N'2', N'2021-09-23 17:03:43.000', N'1', N'2021-11-22 14:44:04.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138555971386474502', N'3', N'资源下面创建资源', N'资源下面创建资源', N'30', N'138191972908138500', N'01', N'', N'123111111', N'lamp/test/index4', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/138191972908138500/', N'2', N'{}', N'2', N'2021-09-23 17:04:54.000', N'1', N'2021-11-22 15:05:09.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138555971386474503', N'3', N'资源下面创建功能', N'资源下面创建功能', N'40', N'138191972908138500', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/138191972908138500/', N'2', N'{}', N'2', N'2021-09-23 17:05:20.000', N'2', N'2021-09-23 17:05:20.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138555971386474504', N'3', N'资源下面创建字段', N'资源下面创建字段', N'50', N'138191972908138500', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/138191972908138500/', N'2', N'{}', N'2', N'2021-09-23 17:05:35.000', N'2', N'2021-09-23 17:05:35.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138555971386474505', N'3', N'功能下创建功能', N'功能下创建功能', N'40', N'138555971386474503', N'01', N'', N'111', N'1', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/138191972908138500/138555971386474503/', N'3', N'{}', N'2', N'2021-09-23 17:06:30.000', N'1', N'2021-11-22 16:09:48.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'138555971386474506', N'3', N'功能下创建字段', N'功能下创建字段', N'50', N'138555971386474503', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/138191972908138500/138555971386474503/', N'3', N'{}', N'2', N'2021-09-23 17:07:17.000', N'2', N'2021-09-23 17:07:17.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'139496646533709824', N'2', N'tenant:application:resource:add', N'新增', N'40', N'137848577387921414', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921414/', N'2', N'{}', N'2', N'2021-09-25 22:27:33.000', N'1452186486253289472', N'2021-12-12 17:29:49.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'139702946697838592', N'2', N'tenant:application:resource:edit', N'编辑', N'40', N'137848577387921414', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921414/137848577387921409/', N'2', N'{}', N'2', N'2021-09-26 14:41:46.000', N'1452186486253289472', N'2022-03-28 11:21:57.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'139702946697838593', N'2', N'tenant:application:resource:delete', N'删除', N'40', N'137848577387921414', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921414/', N'2', N'{}', N'2', N'2021-09-26 14:42:04.000', N'1452186486253289472', N'2021-12-12 17:30:39.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'140208842305699844', N'3', N'testlxq:menu', N'测试菜单', N'20', N'0', N'01', N'', N'/member/sign', N'lamp/test/index5', N'', N'ant-design:read-outlined', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{}', N'2', N'2021-09-27 22:20:56.000', N'1', N'2021-11-22 17:21:55.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'140208842305699846', N'3', N'testlxq:menu:view1', N'视图1', N'30', N'140208842305699844', N'01', N'', N'/member/sign1', N'lamp/test/index5', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/140208842305699844/', N'1', N'{}', N'2', N'2021-09-27 22:32:35.000', N'1', N'2021-11-22 16:12:20.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'140208842305699852', N'3', N'testlxq:menu:add', N'新增', N'40', N'140208842305699844', N'01', N'', N'', N'', N'', N'ant-design:file-add-outlined', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/140208842305699844/', N'1', N'{}', N'2', N'2021-09-27 22:35:15.000', N'2', N'2021-09-27 22:35:15.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'143911967403278336', N'2', N'tenant:system', N'系统管理', N'20', N'0', N'01', N'', N'/system', N'LAYOUT', N'', N'ant-design:setting-outlined', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{}', N'2', N'2021-10-07 20:06:24.000', N'2', N'2021-10-07 20:08:51.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'143911967403278337', N'2', N'tenant:system:dict2', N'数据字典', N'20', N'143911967403278336', N'01', N'', N'/system/dict2', N'/devOperation/system/defDict/index', N'', N'ant-design:book-outlined', N'0', N'1', N'15', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/', N'1', N'{"content":"v2"}', N'2', N'2021-10-07 20:08:39.000', N'1452186486253289472', N'2021-11-09 21:02:24.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'143911967403278338', N'2', N'tenant:system:dict2:add', N'新增', N'40', N'143911967403278337', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/143911967403278337/', N'2', N'{}', N'2', N'2021-10-07 20:16:19.000', N'2', N'2021-10-07 20:16:38.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'143911967403278339', N'2', N'tenant:system:dict2:edit', N'编辑', N'40', N'143911967403278337', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/143911967403278337/', N'2', N'{}', N'2', N'2021-10-07 20:16:32.000', N'2', N'2021-10-07 20:16:32.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'143911967403278340', N'2', N'tenant:system:dict2:delete', N'删除', N'40', N'143911967403278337', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/143911967403278337/', N'2', N'{}', N'2', N'2021-10-07 20:16:51.000', N'2', N'2021-10-07 20:16:51.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'143911967403278341', N'2', N'tenant:system:dict2:copy', N'复制', N'40', N'143911967403278337', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'40', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/143911967403278337/', N'2', N'{}', N'2', N'2021-10-07 20:17:11.000', N'2', N'2021-10-07 20:17:11.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'143911967403278342', N'2', N'tenant:system:dict2:dictItem', N'字典项维护', N'30', N'143911967403278337', N'01', N'', N'/system/dict/:dictId', N'/devOperation/system/defDictItem/index', N'', N'ant-design:book-twotone', N'0', N'1', N'50', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278337/143911967403278336/', N'2', N'{}', N'2', N'2021-10-07 20:19:57.000', N'1452186486253289472', N'2022-04-18 15:11:51.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'143911967403278343', N'2', N'tenant:system:dict2:dictItem:add', N'新增', N'40', N'143911967403278342', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/143911967403278337/143911967403278342/', N'3', N'{}', N'2', N'2021-10-07 20:20:41.000', N'2', N'2021-10-07 20:20:41.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'143911967403278344', N'2', N'tenant:system:dict2:dictItem:edit', N'编辑', N'40', N'143911967403278342', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/143911967403278337/143911967403278342/', N'3', N'{}', N'2', N'2021-10-07 20:20:54.000', N'2', N'2021-10-07 20:20:54.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'143911967403278345', N'2', N'tenant:system:dict2:dictItem:delete', N'删除', N'40', N'143911967403278342', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/143911967403278337/143911967403278342/', N'3', N'{}', N'2', N'2021-10-07 20:21:08.000', N'2', N'2021-10-07 20:21:08.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'143911967403278346', N'2', N'tenant:system:dict2:dictItem:copy', N'复制', N'40', N'143911967403278342', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'40', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/143911967403278337/143911967403278342/', N'3', N'{}', N'2', N'2021-10-07 20:21:24.000', N'2', N'2021-10-07 20:21:24.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'144313439471271947', N'1', N'basic:system', N'系统功能', N'20', N'0', N'01', N'', N'/sysFunction', N'LAYOUT', N'', N'ant-design:setting-outlined', N'0', N'1', N'60', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{}', N'2', N'2021-10-08 21:46:36.000', N'2', N'2021-10-08 21:48:25.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160561957882036227', N'2', N'tenant:tenant:user:reset:password', N'重置密码', N'40', N'137848577387921412', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'5', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921412/', N'2', N'{}', N'1', N'2021-11-21 19:56:05.000', N'1452186486253289472', N'2021-12-12 13:22:52.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938432', N'3', N'aaa:1', N'test公共', N'20', N'0', N'01', N'', N'/111', N'LAYOUT', N'', N'', N'1', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{}', N'1', N'2021-11-22 10:05:44.000', N'1', N'2021-11-22 17:46:45.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938433', N'3', N'aaa:111:11', N'test公共1', N'20', N'160833820721938432', N'02', N'', N'/111/111', N'LAYOUT', N'', N'', N'1', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938432/', N'1', N'{}', N'1', N'2021-11-22 10:06:13.000', N'1', N'2021-11-22 17:20:28.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938434', N'3', N'aa:11:111:111', N'公共1-1', N'20', N'160833820721938433', N'01', N'', N'1-1-1-1', N'lamp/test/index5', N'', N'', N'1', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938432/160833820721938433/', N'2', N'{}', N'1', N'2021-11-22 10:06:36.000', N'1', N'2021-11-22 17:20:54.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938435', N'3', N'11231', N'外链', N'20', N'160833820721938433', N'03', N'', N'https://www.baidu.com/', N'IFRAME', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938432/160833820721938433/', N'2', N'{}', N'1', N'2021-11-22 10:16:01.000', N'1', N'2021-11-22 10:17:06.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938436', N'3', N'33331123', N'内链', N'20', N'160833820721938433', N'02', N'', N'inner3', N'https://vvbin.cn/doc-next/', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938432/160833820721938433/', N'2', N'{}', N'1', N'2021-11-22 11:02:12.000', N'1', N'2021-11-22 17:21:35.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938437', N'2', N'tenant:developer', N'开发者管理', N'20', N'0', N'01', N'', N'/developer', N'LAYOUT', N'', N'ant-design:bug-outlined', N'0', N'1', N'50', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{}', N'1', N'2021-11-22 11:13:11.000', N'1', N'2021-11-22 11:13:25.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938438', N'2', N'tenant:developer:doc', N'Swagger文档', N'20', N'160833820721938437', N'02', N'', N'/developer/doc', N'https://none.tangyh.top/api/base/doc.html', N'', N'ant-design:file-word-outlined', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938437/', N'1', N'{}', N'1', N'2021-11-22 11:16:28.000', N'1452186486253289472', N'2022-06-29 14:18:14.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938439', N'2', N'tenant:developer:nacos', N'nacos', N'20', N'160833820721938437', N'03', N'', N'https://tangyh.top/nacos/', N'IFRAME', N'', N'ant-design:aliyun-outlined', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938437/', N'1', N'{}', N'1', N'2021-11-22 11:18:10.000', N'1452186486253289472', N'2022-03-25 11:41:25.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938440', N'2', N'tenant:developer:skyWalking', N'SkyWalking', N'20', N'160833820721938437', N'03', N'', N'http://sky.tangyh.top/', N'IFRAME', N'', N'ant-design:fund-projection-screen-outlined', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938437/', N'1', N'{}', N'1', N'2021-11-22 11:23:57.000', N'1452186486253289472', N'2022-03-25 11:41:44.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938441', N'2', N'tenant:developer:db', N'数据库监控', N'20', N'160833820721938437', N'02', N'', N'/developer/druid', N'https://none.tangyh.top/druid/index.html', N'', N'ant-design:console-sql-outlined', N'0', N'1', N'40', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938437/', N'1', N'{}', N'1', N'2021-11-22 11:31:59.000', N'1452186486253289472', N'2022-06-29 15:20:09.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938442', N'2', N'tenant:about', N'了解lamp', N'20', N'0', N'01', N'', N'/lamp', N'LAYOUT', N'', N'ant-design:github-filled', N'0', N'1', N'60', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{}', N'1', N'2021-11-22 11:40:23.000', N'1', N'2021-11-22 11:40:23.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938443', N'2', N'tenant:about:doc', N'在线文档', N'20', N'160833820721938442', N'03', N'', N'https://www.kancloud.cn/zuihou/zuihou-admin-cloud', N'IFRAME', N'', N'ant-design:file-pdf-outlined', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938442/', N'1', N'{}', N'1', N'2021-11-22 11:41:44.000', N'1', N'2021-11-22 11:41:44.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938444', N'2', N'tenant:about:vip', N'会员版', N'20', N'160833820721938442', N'03', N'', N'https://tangyh.top/pages/vip/', N'IFRAME', N'', N'ant-design:eye-invisible-outlined', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938442/', N'1', N'{}', N'1', N'2021-11-22 11:43:10.000', N'1452186486253289472', N'2022-06-29 14:10:30.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938445', N'2', N'tenant:about:opensource', N'开源版', N'20', N'160833820721938442', N'03', N'', N'https://github.com/zuihou', N'IFRAME', N'', N'ant-design:gitlab-outlined', N'0', N'1', N'6', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938442/', N'1', N'{}', N'1', N'2021-11-22 11:44:41.000', N'1', N'2021-11-22 11:47:50.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938446', N'2', N'tenant:about:landscape', N'蓝图', N'20', N'160833820721938442', N'03', N'', N'https://tangyh.top/pages/roadmap/', N'IFRAME', N'', N'ant-design:rise-outlined', N'0', N'1', N'5', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938442/', N'1', N'{}', N'1', N'2021-11-22 11:47:35.000', N'1452186486253289472', N'2022-06-29 14:10:01.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160833820721938447', N'2', N'tenant:about:boot', N'boot版', N'20', N'160833820721938442', N'03', N'', N'http://boot.tangyh.top/', N'IFRAME', N'', N'ant-design:ie-outlined', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938442/', N'1', N'{}', N'1', N'2021-11-22 11:50:40.000', N'1', N'2021-11-22 11:50:40.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'160874872019353785', N'3', N'test:menu:view', N'菜单下面有视图', N'30', N'138191972908138498', N'01', N'', N'view123', N'lamp/test/index3', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/138191972908138497/138191972908138498/', N'2', N'{}', N'1', N'2021-11-22 14:45:24.000', N'1', N'2021-11-22 14:45:24.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'172353511420329984', N'2', N'tenant:system:file', N'附件管理', N'20', N'143911967403278336', N'01', N'', N'/system/file', N'/devOperation/system/defFile/index', N'', N'ant-design:file-zip-outlined', N'0', N'1', N'70', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/', N'1', N'{}', N'1452186486253289472', N'2021-12-23 11:16:29.000', N'1452186486253289472', N'2021-12-23 11:16:29.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'172353511420329986', N'2', N'tenant:system:file:upload', N'上传', N'40', N'172353511420329984', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/172353511420329984/', N'2', N'{}', N'1452186486253289472', N'2021-12-23 11:17:50.000', N'1452186486253289472', N'2021-12-23 11:17:50.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'172353511420329987', N'2', N'tenant:system:file:debug:upload', N'调试上传', N'40', N'172353511420329984', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/172353511420329984/', N'2', N'{}', N'1452186486253289472', N'2021-12-23 11:18:09.000', N'1452186486253289472', N'2021-12-23 11:18:09.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'172353511420329988', N'2', N'tenant:system:file:download', N'下载', N'40', N'172353511420329984', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'5', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/172353511420329984/', N'2', N'{}', N'1452186486253289472', N'2021-12-23 11:18:29.000', N'1452186486253289472', N'2021-12-23 11:18:29.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'172353511420329989', N'2', N'tenant:system:file:delete', N'删除', N'40', N'172353511420329984', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/172353511420329984/', N'2', N'{}', N'1452186486253289472', N'2021-12-23 11:19:09.000', N'1452186486253289472', N'2021-12-23 11:19:09.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'179582070228516864', N'1', N'basic:msg:msg:self', N'查看个人消息', N'60', N'1449734007292952576', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1000', N'', N'0', N'1', N'06', NULL, N'1', N'/1449734007292952576/1449733521265393664/', N'2', N'', N'1452186486253289472', N'2022-01-11 22:30:34.000', N'1452186486253289472', N'2022-01-11 22:33:09.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'179582070228516865', N'1', N'basic:msg:msg:all', N'查看全部数据', N'60', N'1449734007292952576', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1010', N'', N'0', N'1', N'01', NULL, N'0', N'/1449734007292952576/1449733521265393664/', N'2', N'', N'1452186486253289472', N'2022-01-11 22:31:38.000', N'1452186486253289472', N'2022-01-11 22:31:38.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'179582070228516866', N'1', N'basic:msg:msg:company:children', N'查看本单位其子单位数据', N'60', N'1449734007292952576', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1001', N'', N'0', N'1', N'02', NULL, N'0', N'/1449734007292952576/1449733521265393664/', N'2', N'', N'1452186486253289472', N'2022-01-11 22:33:02.000', N'1452186486253289472', N'2022-01-11 22:33:02.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'179582070228516867', N'1', N'basic:msg:msg:company', N'查看本单位数据', N'60', N'1449734007292952576', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1002', N'', N'0', N'1', N'03', NULL, N'0', N'/1449734007292952576/1449733521265393664/', N'2', N'', N'1452186486253289472', N'2022-01-11 22:34:01.000', N'1452186486253289472', N'2022-01-11 22:34:01.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'179582070228516868', N'1', N'basic:msg:msg:dept', N'查看本部门数据', N'60', N'1449734007292952576', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1005', N'', N'0', N'1', N'05', NULL, N'0', N'/1449734007292952576/1449733521265393664/', N'2', N'', N'1452186486253289472', N'2022-01-11 22:34:21.000', N'1452186486253289472', N'2022-01-11 22:34:21.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'179582070228516869', N'1', N'basic:msg:msg:dept:children', N'查看本部及其子部门数据', N'60', N'1449734007292952576', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1006', N'', N'0', N'1', N'04', NULL, N'0', N'/1449734007292952576/1449733521265393664/', N'2', N'', N'1452186486253289472', N'2022-01-11 22:35:23.000', N'1452186486253289472', N'2022-01-11 22:35:23.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'179582070228516870', N'1', N'basic:msg:msg:custom', N'查看自定义的数据', N'60', N'1449734007292952576', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1020', N'', N'0', N'1', N'07', N'DATA_SCOPE_TEST', N'0', N'/1449734007292952576/1449733521265393664/', N'2', N'', N'1452186486253289472', N'2022-01-11 22:36:25.000', N'1452186486253289472', N'2022-01-11 22:36:25.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'198310764748996608', N'2', N'tenant:developer:tools', N'开发工具', N'20', N'160833820721938437', N'01', N'', N'/developer/tools', N'LAYOUT', N'', N'ant-design:code-twotone', N'0', N'1', N'0', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938437/', N'1', N'', N'1452186486253289472', N'2022-03-03 12:44:56.000', N'1452186486253289472', N'2022-03-03 12:45:13.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'198310764748996609', N'2', N'tenant:developer:tools:generator', N'代码生成', N'20', N'198310764748996608', N'01', N'', N'/developer/tools/generator', N'/devOperation/developer/defGenTable/index', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/198310764748996608/160833820721938437/', N'2', N'{"content":"new"}', N'1452186486253289472', N'2022-03-03 12:48:30.000', N'1452186486253289472', N'2022-04-11 13:54:00.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'199848844077301779', N'1', N'basic:user:employee:invitation', N'邀请', N'40', N'1449738581135327232', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/1449738581135327232/1449732267470487552/', N'2', N'', N'1452186486253289472', N'2022-03-07 14:13:32.000', N'1452186486253289472', N'2022-03-07 14:13:32.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'201343651610099712', N'2', N'tenant:developer:tools:generator:edit', N'修改代码配置', N'30', N'198310764748996609', N'01', N'', N'edit/:id', N'/devOperation/developer/defGenTable/Edit', N'', N'', N'0', N'1', N'5', N'', N'0', N'1', NULL, NULL, NULL, N'/198310764748996609/198310764748996608/160833820721938437/', N'3', N'{"currentActiveMenu":"/developer/tools/generator"}', N'1452186486253289472', N'2022-03-11 14:00:58.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'206499429136465920', N'2', N'tenant:developer:tools:generator:project', N'项目生成', N'20', N'198310764748996608', N'01', N'', N'project', N'devOperation/developer/genProject/index', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/198310764748996608/160833820721938437/', N'2', N'{"content":"new"}', N'1452186486253289472', N'2022-03-25 11:39:55.000', N'1452186486253289472', N'2022-06-16 20:40:41.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'207209017863307264', N'2', N'tenant:developer:demo', N'开发示例', N'20', N'160833820721938437', N'01', N'', N'demo', N'LAYOUT', N'', N'ant-design:code-outlined', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938437/', N'1', N'', N'1452186486253289472', N'2022-03-27 11:10:44.000', N'1452186486253289472', N'2022-03-27 11:10:44.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'207209017863307265', N'2', N'tenant:developer:demo:simple', N'单表CRUD', N'20', N'207209017863307264', N'01', N'', N'simple', N'/devOperation/developer/defGenTestSimple/index', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/207209017863307264/160833820721938437/', N'2', N'', N'1452186486253289472', N'2022-03-27 11:15:53.000', N'1452186486253289472', N'2022-03-29 10:57:05.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'207209017863307266', N'2', N'tenant:developer:demo:tree', N'树CRUD', N'20', N'207209017863307264', N'01', N'', N'tree', N'/devOperation/developer/defGenTestTree/index', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/207209017863307264/160833820721938437/', N'2', N'', N'1452186486253289472', N'2022-03-27 11:18:14.000', N'1452186486253289472', N'2022-03-29 10:57:45.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'207209017863307267', N'2', N'tenant:developer:demo:m_s', N'主从CRUD', N'20', N'207209017863307264', N'01', N'', N'mainSub', N'/devOperation/developer/defGenTestMainSub/index', N'/system/defDict', N'', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/207209017863307264/160833820721938437/', N'2', N'{}', N'1452186486253289472', N'2022-03-27 11:19:30.000', N'1452186486253289472', N'2022-04-20 00:06:35.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'207592996529504364', N'2', N'tenant:developer:tools:generator:import', N'导入', N'40', N'198310764748996609', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/198310764748996609/198310764748996608/160833820721938437/', N'3', N'', N'1452186486253289472', N'2022-03-28 11:39:54.000', N'1452186486253289472', N'2022-04-11 14:20:56.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'207592996529504370', N'2', N'tenant:developer:tools:generator:sync', N'同步', N'40', N'198310764748996609', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/198310764748996609/198310764748996608/160833820721938437/', N'3', N'', N'1452186486253289472', N'2022-03-28 11:47:13.000', N'1452186486253289472', N'2022-03-28 11:47:13.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'207644798130061312', N'2', N'tenant:developer:tools:generator:delete', N'删除', N'40', N'198310764748996609', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'40', N'', N'0', N'1', NULL, NULL, NULL, N'/198310764748996609/198310764748996608/160833820721938437/', N'3', N'', N'1452186486253289472', N'2022-03-28 14:14:35.000', N'1452186486253289472', N'2022-03-28 14:14:35.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'207644798130061314', N'2', N'tenant:developer:tools:generator:preview', N'预览', N'40', N'198310764748996609', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'50', N'', N'0', N'1', NULL, NULL, NULL, N'/198310764748996609/198310764748996608/160833820721938437/', N'3', N'', N'1452186486253289472', N'2022-03-28 14:15:59.000', N'1452186486253289472', N'2022-04-11 15:13:27.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'207644798130061318', N'2', N'tenant:developer:tools:generator:edit:delete', N'删除', N'40', N'201343651610099712', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/201343651610099712/198310764748996609/198310764748996608/160833820721938437/', N'4', N'', N'1452186486253289472', N'2022-03-28 14:20:22.000', N'1452186486253289472', N'2022-03-28 14:20:22.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'207644798130061323', N'2', N'tenant:developer:tools:generator:edit:edit', N'修改', N'40', N'201343651610099712', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/201343651610099712/198310764748996609/198310764748996608/160833820721938437/', N'4', N'', N'1452186486253289472', N'2022-03-28 14:21:34.000', N'1452186486253289472', N'2022-03-28 14:21:34.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'207644798130061325', N'2', N'tenant:developer:tools:generator:edit:sync', N'同步', N'40', N'201343651610099712', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/201343651610099712/198310764748996609/198310764748996608/160833820721938437/', N'4', N'', N'1452186486253289472', N'2022-03-28 14:22:12.000', N'1452186486253289472', N'2022-04-15 17:17:38.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'215458769570627596', N'2', N'tenant:system:defDict', N'字典管理', N'20', N'143911967403278336', N'01', N'', N'/system/defDict', N'/devOperation/system/defDictManager/index', N'', N'ant-design:book-filled', N'0', N'1', N'17', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/', N'1', N'{"content":"一对多"}', N'1452186486253289472', N'2022-04-18 15:17:44.000', N'1452186486253289472', N'2022-04-18 15:21:07.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'215458769570627601', N'2', N'tenant:system:defDict:add', N'新增', N'40', N'215458769570627596', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/215458769570627596/143911967403278336/', N'2', N'', N'1452186486253289472', N'2022-04-18 15:19:27.000', N'1452186486253289472', N'2022-04-18 15:19:27.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'215458769570627602', N'2', N'tenant:system:defDict:edit', N'编辑', N'40', N'215458769570627596', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/215458769570627596/143911967403278336/', N'2', N'', N'1452186486253289472', N'2022-04-18 15:19:43.000', N'1452186486253289472', N'2022-04-18 15:19:43.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'215458769570627603', N'2', N'tenant:system:defDict:delete', N'删除', N'40', N'215458769570627596', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/215458769570627596/143911967403278336/', N'2', N'', N'1452186486253289472', N'2022-04-18 15:20:03.000', N'1452186486253289472', N'2022-04-18 15:20:03.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'215458769570627604', N'2', N'tenant:system:defDict:copy', N'复制', N'40', N'215458769570627596', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'4', N'', N'0', N'1', NULL, NULL, NULL, N'/215458769570627596/143911967403278336/', N'2', N'', N'1452186486253289472', N'2022-04-18 15:20:23.000', N'1452186486253289472', N'2022-04-18 15:20:23.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'219171794567823360', N'1', N'basic:user:org:bind', N'绑定', N'40', N'1449738119237599232', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/1449738119237599232/1449732267470487552/', N'2', N'', N'1452186486253289472', N'2022-04-28 17:59:31.000', N'1452186486253289472', N'2022-04-28 17:59:31.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'221743822848131072', N'2', N'tenant:developer:tools:datasourceConfig', N'数据源维护', N'20', N'198310764748996608', N'01', N'', N'/developer/tools/datasource', N'/devOperation/tenant/defDatasourceConfig/index', N'', N'ant-design:database-filled', N'0', N'1', N'0', N'', N'0', N'1', NULL, NULL, NULL, N'/198310764748996608/160833820721938437/', N'2', N'', N'1452186486253289472', N'2022-05-05 16:04:46.000', N'1452186486253289472', N'2022-05-05 16:08:17.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'221743822848131074', N'2', N'tenant:tenant:datasourceConfig:add', N'新增', N'40', N'221743822848131072', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/221743822848131072/198310764748996608/160833820721938437/', N'3', N'', N'1452186486253289472', N'2022-05-05 16:05:50.000', N'1452186486253289472', N'2022-05-05 16:05:50.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'221743822848131076', N'2', N'tenant:tenant:datasourceConfig:edit', N'编辑', N'40', N'221743822848131072', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/221743822848131072/198310764748996608/160833820721938437/', N'3', N'', N'1452186486253289472', N'2022-05-05 16:07:00.000', N'1452186486253289472', N'2022-05-05 16:07:00.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'221743822848131078', N'2', N'tenant:tenant:datasourceConfig:delete', N'删除', N'40', N'221743822848131072', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/221743822848131072/198310764748996608/160833820721938437/', N'3', N'', N'1452186486253289472', N'2022-05-05 16:07:32.000', N'1452186486253289472', N'2022-05-05 16:07:32.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'221743822848131080', N'2', N'tenant:tenant:datasourceConfig:test', N'测试', N'40', N'221743822848131072', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'15', N'', N'0', N'1', NULL, NULL, NULL, N'/221743822848131072/198310764748996608/160833820721938437/', N'3', N'', N'1452186486253289472', N'2022-05-05 16:07:58.000', N'1452186486253289472', N'2022-05-05 16:07:58.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'239326285086588928', N'1', N'basic:flow', N'流程管理', N'20', N'0', N'01', N'', N'/flow', N'LAYOUT', N'', N'ant-design:facebook-outlined', N'0', N'1', N'70', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'', N'1452186486253289472', N'2022-06-21 22:47:37.000', N'1452186486253289472', N'2022-06-21 22:47:37.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'239326285086588929', N'1', N'basic:flow:activiti', N'activiti', N'20', N'239326285086588928', N'01', N'', N'/flow/activiti', N'LAYOUT', N'', N'ant-design:amazon-outlined', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588928/', N'1', N'', N'1452186486253289472', N'2022-06-21 22:50:09.000', N'1452186486253289472', N'2022-06-21 22:51:28.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'239326285086588930', N'1', N'basic:flow:activiti:deployment', N'流程部署', N'20', N'239326285086588929', N'01', N'', N'/flow/activiti/deployment', N'basic/activiti/deployment/index', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588929/239326285086588928/', N'2', N'', N'1452186486253289472', N'2022-06-21 22:52:53.000', N'1452186486253289472', N'2022-06-26 15:07:04.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'239326285086588931', N'1', N'basic:flow:activiti:model', N'模型管理', N'20', N'239326285086588929', N'01', N'', N'/flow/activiti/model', N'/basic/activiti/model/index', N'', N'', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588929/239326285086588928/', N'2', N'', N'1452186486253289472', N'2022-06-21 22:54:10.000', N'1452186486253289472', N'2022-06-26 15:37:09.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'241043868278063105', N'1', N'basic:flow:activiti:deployment:upload', N'上传', N'40', N'239326285086588930', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588930/239326285086588929/239326285086588928/', N'3', N'', N'1452186486253289472', N'2022-06-26 15:08:14.000', N'1452186486253289472', N'2022-06-26 15:08:14.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'241043868278063107', N'1', N'basic:flow:activiti:deployment:tran', N'转换', N'40', N'239326285086588930', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588930/239326285086588929/239326285086588928/', N'3', N'', N'1452186486253289472', N'2022-06-26 15:22:29.000', N'1452186486253289472', N'2022-06-26 15:22:29.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'241043868278063109', N'1', N'basic:flow:activiti:deployment:state', N'修改状态', N'40', N'239326285086588930', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588930/239326285086588929/239326285086588928/', N'3', N'', N'1452186486253289472', N'2022-06-26 15:23:50.000', N'1452186486253289472', N'2022-06-26 15:23:50.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'241043868278063111', N'1', N'basic:flow:activiti:deployment:draw', N'流程图', N'40', N'239326285086588930', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'4', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588930/239326285086588929/239326285086588928/', N'3', N'', N'1452186486253289472', N'2022-06-26 15:24:35.000', N'1452186486253289472', N'2022-06-26 15:24:35.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'241043868278063113', N'1', N'basic:flow:activiti:deployment:xml', N'xml', N'40', N'239326285086588930', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'5', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588930/239326285086588929/239326285086588928/', N'3', N'', N'1452186486253289472', N'2022-06-26 15:35:36.000', N'1452186486253289472', N'2022-06-26 15:35:36.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'241043868278063115', N'1', N'basic:flow:activiti:deployment:delete', N'删除', N'40', N'239326285086588930', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588930/239326285086588929/239326285086588928/', N'3', N'', N'1452186486253289472', N'2022-06-26 15:35:56.000', N'1452186486253289472', N'2022-06-26 15:35:56.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'241043868278063118', N'1', N'basic:flow:activiti:model:add', N'新增', N'40', N'239326285086588931', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588931/239326285086588929/239326285086588928/', N'3', N'', N'1452186486253289472', N'2022-06-26 15:38:27.000', N'1452186486253289472', N'2022-06-26 15:38:27.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'241043868278063120', N'1', N'basic:flow:activiti:model:edit', N'编辑', N'40', N'239326285086588931', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588931/239326285086588929/239326285086588928/', N'3', N'', N'1452186486253289472', N'2022-06-26 15:40:28.000', N'1452186486253289472', N'2022-06-26 15:40:28.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'241043868278063121', N'1', N'basic:flow:activiti:model:publish', N'发布', N'40', N'239326285086588931', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588931/239326285086588929/239326285086588928/', N'3', N'', N'1452186486253289472', N'2022-06-26 15:40:57.000', N'1452186486253289472', N'2022-06-26 15:40:57.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'241043868278063123', N'1', N'basic:flow:activiti:model:download', N'下载', N'40', N'239326285086588931', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588931/239326285086588929/239326285086588928/', N'3', N'', N'1452186486253289472', N'2022-06-26 15:42:21.000', N'1452186486253289472', N'2022-06-26 23:12:52.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'241043868278063125', N'1', N'basic:flow:activiti:model:delete', N'删除', N'40', N'239326285086588931', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/239326285086588931/239326285086588929/239326285086588928/', N'3', N'', N'1452186486253289472', N'2022-06-26 15:42:50.000', N'1452186486253289472', N'2022-06-26 15:42:50.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'242152648445263888', N'2', N'tenant:developer:minio', N'MinIO', N'20', N'160833820721938437', N'02', N'', N'/developer/minio', N'https://static.tangyh.top/minio/', N'', N'ant-design:folder-open-filled', N'0', N'1', N'50', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938437/', N'1', N'', N'1452186486253289472', N'2022-06-29 14:14:43.000', N'1452186486253289472', N'2022-06-29 14:14:56.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'242152648445263893', N'2', N'tenant:developer:job', N'分布式定时任务', N'20', N'160833820721938437', N'03', N'', N'https://none.tangyh.top/xxl-job-admin/', N'IFRAME', N'', N'ant-design:project-outlined', N'0', N'1', N'60', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938437/', N'1', N'', N'1452186486253289472', N'2022-06-29 14:15:49.000', N'1452186486253289472', N'2022-06-29 15:20:18.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'242152648445263898', N'2', N'tenant:developer:file', N'文件预览', N'20', N'160833820721938437', N'02', N'', N'/developer/filepreview', N'http://106.53.26.9:8012/index', N'', N'ant-design:file-exclamation-outlined', N'0', N'1', N'70', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938437/', N'1', N'', N'1452186486253289472', N'2022-06-29 14:17:56.000', N'1452186486253289472', N'2022-06-29 14:17:56.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'242181583639937024', N'2', N'tenant:developer:server', N'服务器监控', N'20', N'160833820721938437', N'01', N'', N'/developer/srever', N'/devOperation/developer/srever/index', N'', N'ant-design:aim-outlined', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938437/', N'1', N'', N'1452186486253289472', N'2022-06-29 15:12:43.000', N'1452186486253289472', N'2022-06-30 14:22:57.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'242300348075606021', N'2', N'tenant:developer:jenkins', N'Jenkins', N'20', N'160833820721938437', N'03', N'', N'http://jenkins.tangyh.top/', N'IFRAME', N'', N'ant-design:dropbox-outlined', N'0', N'1', N'80', N'', N'0', N'1', NULL, NULL, NULL, N'/160833820721938437/', N'1', N'', N'1452186486253289472', N'2022-06-29 23:00:02.000', N'1452186486253289472', N'2022-06-29 23:00:02.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'249954310509559808', N'2', N'tenant:ops', N'运维平台', N'20', N'0', N'01', N'', N'/ops', N'LAYOUT', N'', N'ant-design:tool-outlined', N'0', N'1', N'60', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'', N'1452186486253289472', N'2022-07-20 17:09:20.000', N'1452186486253289472', N'2022-07-20 17:09:20.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'249954310509559809', N'2', N'tenant:ops:interfaces', N'接口管理', N'20', N'249954310509559808', N'01', N'', N'/ops/interface', N'/devOperation/ops/defInterface/index', N'', N'ant-design:node-expand-outlined', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559808/', N'1', N'', N'1452186486253289472', N'2022-07-20 17:13:33.000', N'1452186486253289472', N'2022-07-30 23:41:10.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'249954310509559810', N'2', N'tenant:ops:interface:log', N'接口日志', N'20', N'249954310509559808', N'01', N'', N'/ops/log', N'/devOperation/ops/defInterfaceLog/index', N'', N'ant-design:customer-service-outlined', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559808/', N'1', N'', N'1452186486253289472', N'2022-07-20 17:15:28.000', N'1452186486253289472', N'2022-07-31 00:02:14.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'249954310509559811', N'2', N'tenant:ops:template', N'消息模板', N'20', N'249954310509559808', N'01', N'', N'/ops/template', N'/devOperation/ops/defMsgTemplate/index', N'', N'ant-design:medium-outlined', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559808/', N'1', N'', N'1452186486253289472', N'2022-07-20 17:17:13.000', N'1452186486253289472', N'2022-07-30 23:47:54.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'249954310509559812', N'2', N'tenant:ops:interfaces:property', N'接口设置', N'30', N'249954310509559809', N'01', N'', N'/ops/interface/property/:id', N'/devOperation/ops/defInterface/property/index', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559809/249954310509559808/', N'2', N'', N'1452186486253289472', N'2022-07-20 17:19:03.000', N'1452186486253289472', N'2022-07-30 23:44:33.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'249954310509559813', N'2', N'tenant:ops:interfaces:log:logging', N'日志执行记录', N'30', N'249954310509559810', N'01', N'', N'/ops/log/:id', N'/devOperation/ops/defInterfaceLogging/index', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559810/249954310509559808/', N'2', N'', N'1452186486253289472', N'2022-07-20 17:19:58.000', N'1452186486253289472', N'2022-07-30 23:47:07.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'250025856074776576', N'2', N'tenant:ops:interfaces:add', N'新增', N'40', N'249954310509559809', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559809/249954310509559808/', N'2', N'', N'1452186486253289472', N'2022-07-20 19:22:23.000', N'1452186486253289472', N'2022-07-31 00:01:33.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'250025856074776577', N'2', N'tenant:ops:interfaces:edit', N'编辑', N'40', N'249954310509559809', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559809/249954310509559808/', N'2', N'{}', N'1452186486253289472', N'2022-07-20 19:24:14.000', N'1452186486253289472', N'2022-07-31 00:01:13.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'250025856074776578', N'2', N'tenant:ops:interfaces:view', N'查看', N'40', N'249954310509559809', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'4', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559809/249954310509559808/', N'2', N'{}', N'1452186486253289472', N'2022-07-20 19:24:44.000', N'1452186486253289472', N'2022-07-20 19:24:44.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'250025856074776579', N'2', N'tenant:ops:interfaces:delete', N'删除', N'40', N'249954310509559809', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'5', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559809/249954310509559808/', N'2', N'{}', N'1452186486253289472', N'2022-07-20 19:24:57.000', N'1452186486253289472', N'2022-07-30 23:44:00.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'250025856074776580', N'2', N'tenant:ops:interfaces:log:delete', N'删除', N'40', N'249954310509559810', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559810/249954310509559808/', N'2', N'', N'1452186486253289472', N'2022-07-20 19:26:01.000', N'1452186486253289472', N'2022-07-30 23:46:48.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'250025856074776581', N'2', N'tenant:ops:interfaces:log:logging:delete', N'删除', N'40', N'249954310509559813', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559813/249954310509559810/249954310509559808/', N'3', N'', N'1452186486253289472', N'2022-07-20 19:26:43.000', N'1452186486253289472', N'2022-07-30 23:47:29.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'250025856074776582', N'2', N'tenant:ops:template:add', N'新增', N'40', N'249954310509559811', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559811/249954310509559808/', N'2', N'', N'1452186486253289472', N'2022-07-20 19:27:15.000', N'1452186486253289472', N'2022-07-31 00:02:55.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'250025856074776583', N'2', N'tenant:ops:template:edit', N'编辑', N'40', N'249954310509559811', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559811/249954310509559808/', N'2', N'', N'1452186486253289472', N'2022-07-20 19:27:26.000', N'1452186486253289472', N'2022-07-31 00:03:15.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'250025856074776584', N'2', N'tenant:ops:template:delete', N'删除', N'40', N'249954310509559811', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/249954310509559811/249954310509559808/', N'2', N'', N'1452186486253289472', N'2022-07-20 19:27:42.000', N'1452186486253289472', N'2022-07-30 23:48:49.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1448315264151060480', N'2', N'tenant:system:param', N'参数维护', N'20', N'143911967403278336', N'01', N'', N'/system/parameter', N'/devOperation/system/defParameter/index', N'', N'ant-design:profile-outlined', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/', N'1', N'{}', N'2', N'2021-10-13 23:50:42.000', N'1452186486253289472', N'2021-12-12 18:13:51.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1448315406962917376', N'2', N'tenant:system:area', N'地区维护', N'20', N'143911967403278336', N'01', N'', N'/system/area', N'/devOperation/system/defArea/index', N'', N'ant-design:area-chart-outlined', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/', N'1', N'{}', N'2', N'2021-10-13 23:51:16.000', N'1452186486253289472', N'2021-12-12 18:15:18.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1448315861369618432', N'2', N'tenant:system:client', N'客户端维护', N'20', N'143911967403278336', N'01', N'', N'/system/client', N'/devOperation/system/defClient/index', N'', N'ant-design:android-outlined', N'0', N'1', N'40', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/', N'1', N'{}', N'2', N'2021-10-13 23:53:04.000', N'1452186486253289472', N'2021-12-12 18:16:25.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449391740313141248', N'2', N'tenant:system:dict', N'字典维护', N'20', N'143911967403278336', N'01', N'', N'/system/dict', N'/devOperation/system/dict/index', N'', N'ant-design:book-twotone', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/', N'1', N'{}', N'2', N'2021-10-16 23:08:14.000', N'1452186486253289472', N'2021-12-12 17:43:22.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449723704727568384', N'2', N'tenant:system:dict:add', N'新增字典', N'40', N'1449391740313141248', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1449391740313141248/', N'2', N'{}', N'2', N'2021-10-17 21:07:20.000', N'1452186486253289472', N'2021-12-12 17:57:01.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449723831227777024', N'2', N'tenant:system:dict:edit', N'编辑字典', N'40', N'1449391740313141248', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1449391740313141248/', N'2', N'{}', N'2', N'2021-10-17 21:07:51.000', N'1452186486253289472', N'2021-12-12 17:57:12.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449723907798990848', N'2', N'tenant:system:dict:delete', N'删除字典', N'40', N'1449391740313141248', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1449391740313141248/', N'2', N'{}', N'2', N'2021-10-17 21:08:09.000', N'1452186486253289472', N'2021-12-12 17:44:32.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449724021556903936', N'2', N'tenant:system:dict:addItem', N'新增字典项', N'40', N'1449391740313141248', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1449391740313141248/', N'2', N'{}', N'2', N'2021-10-17 21:08:36.000', N'1452186486253289472', N'2021-12-12 18:12:49.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449724119569399808', N'2', N'tenant:system:dict:editItem', N'编辑字典项', N'40', N'1449391740313141248', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'11', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1449391740313141248/', N'2', N'{}', N'2', N'2021-10-17 21:08:59.000', N'1452186486253289472', N'2021-12-12 18:13:02.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449724196207722496', N'2', N'tenant:system:dict:deleteItem', N'删除字典项', N'40', N'1449391740313141248', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'13', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1449391740313141248/', N'2', N'{}', N'2', N'2021-10-17 21:09:18.000', N'1452186486253289472', N'2021-12-12 18:13:17.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449732267470487552', N'1', N'basic:user', N'用户中心', N'20', N'0', N'01', N'', N'/user', N'LAYOUT', N'', N'ant-design:usergroup-add-outlined', N'0', N'1', N'40', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{}', N'2', N'2021-10-17 21:41:22.000', N'2', N'2021-10-17 22:05:29.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449733521265393664', N'1', N'basic:msg', N'消息中心', N'20', N'0', N'01', N'', N'/msg', N'LAYOUT', N'', N'ant-design:message-outlined', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/', N'0', N'{}', N'2', N'2021-10-17 21:46:21.000', N'1452186486253289472', N'2021-11-16 12:59:15.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449733787893104640', N'1', N'basic:msg:myMsg', N'我的消息', N'20', N'1449733521265393664', N'01', N'', N'/msg/myMsg', N'/basic/msg/extendNotice/index', N'', N'wpf-my-topic', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/1449733521265393664/', N'1', N'{}', N'2', N'2021-10-17 21:47:24.000', N'1452186486253289472', N'2022-07-04 15:32:59.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449734007292952576', N'1', N'basic:msg:msg', N'消息管理', N'20', N'1449733521265393664', N'01', N'', N'/msg/msg', N'/basic/msg/extendMsg/index', N'', N'codicon-repo-push', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/1449733521265393664/', N'1', N'{"content":"数据权限"}', N'2', N'2021-10-17 21:48:17.000', N'1452186486253289472', N'2022-08-01 21:51:02.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449734450995789824', N'1', N'basic:system:role', N'角色权限维护', N'20', N'144313439471271947', N'01', N'', N'/system/role', N'/basic/system/baseRole/index', N'', N'ant-design:lock-outlined', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/', N'1', N'{}', N'2', N'2021-10-17 21:50:03.000', N'1452186486253289472', N'2022-01-12 22:35:53.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449734707225821184', N'1', N'basic:system:appendix', N'附件管理', N'20', N'144313439471271947', N'01', N'', N'/system/file', N'/basic/system/baseFile/index', N'', N'ant-design:file-sync-outlined', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/', N'1', N'{}', N'2', N'2021-10-17 21:51:04.000', N'1452186486253289472', N'2021-12-12 00:50:10.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449734944434683904', N'1', N'basic:system:webLog', N'操作日志', N'20', N'144313439471271947', N'01', N'', N'/system/operationLog', N'/basic/system/baseOperationLog/index', N'', N'ant-design:reconciliation-outlined', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/', N'1', N'{}', N'2', N'2021-10-17 21:52:00.000', N'1452186486253289472', N'2021-12-12 00:30:33.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449735103088427008', N'1', N'basic:system:loginLog', N'登录日志', N'20', N'144313439471271947', N'01', N'', N'/sysFunction/loginLog', N'/basic/system/baseLoginLog/index', N'', N'ant-design:login-outlined', N'0', N'1', N'40', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/', N'1', N'{}', N'2', N'2021-10-17 21:52:38.000', N'1452186486253289472', N'2021-12-12 00:28:52.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449738119237599232', N'1', N'basic:user:org', N'组织机构', N'20', N'1449732267470487552', N'01', N'', N'/user/org', N'/basic/user/baseOrg/index', N'', N'ant-design:cluster-outlined', N'0', N'1', N'20', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/', N'1', N'{}', N'2', N'2021-10-17 22:04:37.000', N'1452186486253289472', N'2021-12-11 23:22:44.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449738581135327232', N'1', N'basic:user:employee', N'员工维护', N'20', N'1449732267470487552', N'01', N'', N'/user/employee', N'/basic/user/baseEmployee/index', N'', N'ant-design:user-add-outlined', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/', N'1', N'{}', N'2', N'2021-10-17 22:06:27.000', N'1452186486253289472', N'2021-12-14 23:31:16.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1449739134456299520', N'1', N'basic:user:position', N'岗位维护', N'20', N'1449732267470487552', N'01', N'', N'/user/position', N'/basic/user/basePosition/index', N'', N'eos-icons:job', N'0', N'1', N'30', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/', N'1', N'{}', N'2', N'2021-10-17 22:08:39.000', N'1452186486253289472', N'2021-12-11 23:39:12.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457620408604819456', N'1', N'basic:user:employee:add', N'新增', N'40', N'1449738581135327232', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/1449738581135327232/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 16:06:01.000', N'1452186486253289472', N'2021-12-14 23:16:27.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457620470995091456', N'1', N'basic:user:employee:edit', N'编辑', N'40', N'1449738581135327232', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/1449738581135327232/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 16:06:16.000', N'1452186486253289472', N'2021-12-11 20:46:52.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457620528469639168', N'1', N'basic:user:employee:delete', N'删除', N'40', N'1449738581135327232', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/1449738581135327232/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 16:06:30.000', N'1452186486253289472', N'2021-12-11 20:45:26.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457620585302458368', N'1', N'basic:user:employee:view', N'查看', N'40', N'1449738581135327232', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'4', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/1449738581135327232/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 16:06:43.000', N'1452186486253289472', N'2021-12-11 20:40:32.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457665354649042944', N'1', N'basic:user:position:add', N'新增', N'40', N'1449739134456299520', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/1449739134456299520/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 19:04:37.000', N'1452186486253289472', N'2021-12-11 23:39:45.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457665399683284992', N'1', N'basic:user:position:edit', N'编辑', N'40', N'1449739134456299520', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/1449739134456299520/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 19:04:48.000', N'1452186486253289472', N'2021-12-11 23:41:09.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457665444381982720', N'1', N'basic:user:position:delete', N'删除', N'40', N'1449739134456299520', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/1449739134456299520/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 19:04:59.000', N'1452186486253289472', N'2021-12-11 23:41:21.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457665503664275456', N'1', N'basic:user:position:view', N'查看', N'40', N'1449739134456299520', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'4', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/1449739134456299520/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 19:05:13.000', N'1451549146992345088', N'2021-11-08 19:05:13.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457665587088982016', N'1', N'basic:user:org:add', N'新增', N'40', N'1449738119237599232', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/1449738119237599232/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 19:05:33.000', N'1452186486253289472', N'2021-12-11 23:30:43.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457665635705159680', N'1', N'basic:user:org:edit', N'编辑', N'40', N'1449738119237599232', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/1449738119237599232/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 19:05:44.000', N'1452186486253289472', N'2021-12-11 23:31:01.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457665696765837312', N'1', N'basic:user:org:delete', N'删除', N'40', N'1449738119237599232', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/1449738119237599232/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 19:05:59.000', N'1452186486253289472', N'2021-12-11 23:31:16.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457665749857337344', N'1', N'basic:user:org:switch', N'切换', N'40', N'1449738119237599232', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'4', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/1449738119237599232/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 19:06:12.000', N'1452186486253289472', N'2021-12-11 23:31:53.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457668537614073856', N'1', N'basic:system:role:add', N'新增', N'40', N'1449734450995789824', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/1449734450995789824/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 19:17:16.000', N'1452186486253289472', N'2021-12-12 01:00:43.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457668602952941568', N'1', N'basic:system:role:edit', N'编辑', N'40', N'1449734450995789824', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/1449734450995789824/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 19:17:32.000', N'1452186486253289472', N'2021-12-12 01:00:58.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457668655000059904', N'1', N'basic:system:role:delete', N'删除', N'40', N'1449734450995789824', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/1449734450995789824/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 19:17:44.000', N'1452186486253289472', N'2021-12-12 01:01:13.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457668749124435968', N'1', N'basic:system:role:bindUser', N'绑定用户', N'40', N'1449734450995789824', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/1449734450995789824/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 19:18:07.000', N'1452186486253289472', N'2021-12-12 01:04:05.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1457668844297388032', N'1', N'basic:system:role:bindResource', N'配置资源', N'40', N'1449734450995789824', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'6', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/1449734450995789824/', N'2', N'{}', N'1451549146992345088', N'2021-11-08 19:18:29.000', N'1452186486253289472', N'2021-12-12 01:04:38.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1459061181095346176', N'2', N'tenant:system:loginLog', N'登录日志', N'20', N'143911967403278336', N'01', N'', N'/system/loginLog', N'/devOperation/system/defLoginLog/index', N'', N'ant-design:login-outlined', N'0', N'1', N'60', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/', N'1', N'{}', N'1452186486253289472', N'2021-11-12 15:31:08.000', N'1452186486253289472', N'2021-12-12 20:20:49.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460436763976663040', N'1', N'basic:msg:msg:add', N'发布消息', N'30', N'1449734007292952576', N'01', N'', N'/msg/msg/:type/:id', N'/basic/msg/extendMsg/Edit', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/1449734007292952576/1449733521265393664/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 10:37:13.000', N'1452186486253289472', N'2022-08-01 21:51:57.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460436856054218752', N'1', N'basic:msg:msg:edit', N'编辑', N'40', N'1449734007292952576', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/1449734007292952576/1449733521265393664/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 10:37:35.000', N'1452186486253289472', N'2022-08-01 21:52:20.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460436934051495936', N'1', N'basic:msg:msg:delete', N'删除', N'40', N'1449734007292952576', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'5', N'', N'0', N'1', NULL, NULL, NULL, N'/1449734007292952576/1449733521265393664/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 10:37:53.000', N'1452186486253289472', N'2022-08-01 21:52:35.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460468030063509504', N'1', N'basic:msg:myMsg:edit', N'查看我的消息', N'30', N'1449733787893104640', N'01', N'', N'/msg/myMsg/:type/:id', N'/basic/msg/extendNotice/Edit', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/1449733787893104640/1449733521265393664/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 12:41:27.000', N'1452186486253289472', N'2022-07-17 13:20:39.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460537476991942656', N'1', N'basic:msg:myMsg:delete', N'删除', N'40', N'1449733787893104640', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/1449733521265393664/1449733787893104640/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:17:25.000', N'1452186486253289472', N'2021-11-16 17:17:25.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460537873248813056', N'1', N'basic:msg:msg:view', N'查看', N'40', N'1449734007292952576', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/1449733521265393664/1449734007292952576/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:18:59.000', N'1452186486253289472', N'2021-12-11 13:27:53.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460538960118808576', N'1', N'basic:system:webLog:delete', N'删除', N'40', N'1449734944434683904', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/1449734944434683904/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:23:18.000', N'1452186486253289472', N'2021-12-12 00:31:01.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460539047851065344', N'1', N'basic:system:webLog:view', N'查看', N'40', N'1449734944434683904', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/1449734944434683904/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:23:39.000', N'1452186486253289472', N'2021-12-12 00:31:30.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460540557393657856', N'1', N'basic:system:loginLog:delete', N'删除', N'40', N'1449735103088427008', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/1449735103088427008/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:29:39.000', N'1452186486253289472', N'2021-12-12 00:29:25.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460540612146102272', N'1', N'basic:system:loginLog:view', N'查看', N'40', N'1449735103088427008', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/1449735103088427008/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:29:52.000', N'1452186486253289472', N'2021-12-12 00:30:14.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460540797257515008', N'1', N'basic:system:appendix:upload', N'上传', N'40', N'1449734707225821184', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/1449734707225821184/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:30:36.000', N'1452186486253289472', N'2021-11-16 17:30:36.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460540870838190080', N'1', N'basic:system:appendix:debug:upload', N'调试上传', N'40', N'1449734707225821184', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/1449734707225821184/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:30:54.000', N'1452186486253289472', N'2021-11-16 17:30:54.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460540935568883712', N'1', N'basic:system:appendix:download', N'下载', N'40', N'1449734707225821184', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/1449734707225821184/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:31:09.000', N'1452186486253289472', N'2021-11-16 17:31:09.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460540979420332032', N'1', N'basic:system:appendix:delete', N'删除', N'40', N'1449734707225821184', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/144313439471271947/1449734707225821184/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:31:20.000', N'1452186486253289472', N'2021-12-12 00:57:27.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460542581971615744', N'2', N'tenant:tenant:user:add', N'新增', N'40', N'137848577387921412', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921412/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:37:42.000', N'1452186486253289472', N'2021-12-12 13:15:38.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460542629845401600', N'2', N'tenant:tenant:user:edit', N'编辑', N'40', N'137848577387921412', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921412/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:37:53.000', N'1452186486253289472', N'2021-12-12 13:21:18.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460542675554926592', N'2', N'tenant:tenant:user:delete', N'删除', N'40', N'137848577387921412', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921412/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:38:04.000', N'1452186486253289472', N'2021-12-12 13:21:32.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460543317040168960', N'2', N'tenant:tenant:user:view', N'查看', N'40', N'137848577387921412', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'4', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921412/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:40:37.000', N'1452186486253289472', N'2021-11-16 17:40:37.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460547251804831744', N'2', N'tenant:application:application:resource:edit', N'编辑', N'40', N'137848577387921421', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921413/137848577387921421/', N'3', N'{}', N'1452186486253289472', N'2021-11-16 17:56:15.000', N'1452186486253289472', N'2021-12-12 15:59:07.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460547377491345408', N'2', N'tenant:application:application:resource:delete', N'删除', N'40', N'137848577387921421', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/137848577387921409/137848577387921413/137848577387921421/', N'3', N'{}', N'1452186486253289472', N'2021-11-16 17:56:45.000', N'1452186486253289472', N'2021-12-12 15:57:35.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460547739937931264', N'2', N'tenant:system:param:add', N'新增', N'40', N'1448315264151060480', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1448315264151060480/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:58:12.000', N'1452186486253289472', N'2021-12-12 18:14:07.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460547794912673792', N'2', N'tenant:system:param:edit', N'编辑', N'40', N'1448315264151060480', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1448315264151060480/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:58:25.000', N'1452186486253289472', N'2021-12-12 18:14:20.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460547848918532096', N'2', N'tenant:system:param:view', N'查看', N'40', N'1448315264151060480', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1448315264151060480/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:58:38.000', N'1452186486253289472', N'2021-11-16 17:58:38.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460547893147467776', N'2', N'tenant:system:param:delete', N'删除', N'40', N'1448315264151060480', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'4', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1448315264151060480/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:58:48.000', N'1452186486253289472', N'2021-12-12 18:14:36.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460547974479216640', N'2', N'tenant:system:area:add', N'新增', N'40', N'1448315406962917376', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1448315406962917376/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:59:07.000', N'1452186486253289472', N'2021-12-12 18:15:32.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460548071271170048', N'2', N'tenant:system:area:edit', N'编辑', N'40', N'1448315406962917376', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1448315406962917376/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 17:59:31.000', N'1452186486253289472', N'2021-12-12 18:15:48.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460548273847664640', N'2', N'tenant:system:area:delete', N'删除', N'40', N'1448315406962917376', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1448315406962917376/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 18:00:19.000', N'1452186486253289472', N'2021-12-12 18:16:00.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460548462427766784', N'2', N'tenant:system:client:add', N'新增', N'40', N'1448315861369618432', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1448315861369618432/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 18:01:04.000', N'1452186486253289472', N'2021-12-12 18:59:33.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460548519843594240', N'2', N'tenant:system:client:edit', N'编辑', N'40', N'1448315861369618432', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1448315861369618432/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 18:01:17.000', N'1452186486253289472', N'2021-12-12 19:00:03.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460548553880371200', N'2', N'tenant:system:client:delete', N'删除', N'40', N'1448315861369618432', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'3', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1448315861369618432/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 18:01:26.000', N'1452186486253289472', N'2021-12-12 19:00:24.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460548589812973568', N'2', N'tenant:system:client:view', N'查看', N'40', N'1448315861369618432', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'4', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1448315861369618432/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 18:01:34.000', N'1452186486253289472', N'2021-11-16 18:01:34.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460548658754748416', N'2', N'tenant:system:loginLog:delete', N'删除', N'40', N'1459061181095346176', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'1', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1459061181095346176/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 18:01:51.000', N'1452186486253289472', N'2021-12-12 20:21:06.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1460548689041817600', N'2', N'tenant:system:loginLog:view', N'查看', N'40', N'1459061181095346176', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'2', N'', N'0', N'1', NULL, NULL, NULL, N'/143911967403278336/1459061181095346176/', N'2', N'{}', N'1452186486253289472', N'2021-11-16 18:01:58.000', N'1452186486253289472', N'2021-12-12 20:21:42.000')
GO

INSERT INTO [dbo].[def_resource]  VALUES (N'1461609523809615872', N'1', N'basic:user:employee:bindRole', N'绑定角色', N'40', N'1449738581135327232', N'01', N'', N'', N'', N'', N'', N'0', N'1', N'10', N'', N'0', N'1', NULL, NULL, NULL, N'/1449732267470487552/1449738581135327232/', N'2', N'{}', N'1452186486253289472', N'2021-11-19 16:17:21.000', N'1452186486253289472', N'2021-12-11 20:49:19.000')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for def_resource_api
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_resource_api]') AND type IN ('U'))
	DROP TABLE [dbo].[def_resource_api]
GO

CREATE TABLE [dbo].[def_resource_api] (
  [id] bigint  NOT NULL,
  [resource_id] bigint  NOT NULL,
  [controller] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [spring_application_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [request_method] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [uri] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [is_input] bit  NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[def_resource_api] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'def_resource_api',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'资源ID',
'SCHEMA', N'dbo',
'TABLE', N'def_resource_api',
'COLUMN', N'resource_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'控制器类名',
'SCHEMA', N'dbo',
'TABLE', N'def_resource_api',
'COLUMN', N'controller'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属服务;取配置文件中 spring.application.name ',
'SCHEMA', N'dbo',
'TABLE', N'def_resource_api',
'COLUMN', N'spring_application_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求类型',
'SCHEMA', N'dbo',
'TABLE', N'def_resource_api',
'COLUMN', N'request_method'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口名;接口上的注释',
'SCHEMA', N'dbo',
'TABLE', N'def_resource_api',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口地址;lamp-cloud版：uri需要拼接上gateway中路径前缀lamp-boot版: uri需要不需要拼接前缀',
'SCHEMA', N'dbo',
'TABLE', N'def_resource_api',
'COLUMN', N'uri'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否手动录入',
'SCHEMA', N'dbo',
'TABLE', N'def_resource_api',
'COLUMN', N'is_input'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'def_resource_api',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_resource_api',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新人',
'SCHEMA', N'dbo',
'TABLE', N'def_resource_api',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新时间',
'SCHEMA', N'dbo',
'TABLE', N'def_resource_api',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'资源接口',
'SCHEMA', N'dbo',
'TABLE', N'def_resource_api'
GO


-- ----------------------------
-- Records of def_resource_api
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167906188749438980', N'1460537873248813056', N'DefUserController', N'lamp-system-server', N'POST', N'用户-查找同一企业下的用户', N'/system/defUser/pageUser', N'0', N'1452186486253289472', N'2021-12-11 13:27:53.000', N'1452186486253289472', N'2021-12-11 13:27:53.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167906188749438981', N'1460537873248813056', N'MsgController', N'lamp-base-server', N'GET', N'消息表-查询消息中心', N'/base/msg/{id}', N'0', N'1452186486253289472', N'2021-12-11 13:27:53.000', N'1452186486253289472', N'2021-12-11 13:27:53.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167906188749438982', N'1460537873248813056', N'BaseRoleController', N'lamp-base-server', N'POST', N'角色-批量查询', N'/base/baseRole/query', N'0', N'1452186486253289472', N'2021-12-11 13:27:53.000', N'1452186486253289472', N'2021-12-11 13:27:53.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225477', N'1457620585302458368', N'BaseEmployeeController', N'lamp-base-server', N'GET', N'员工-单体查询', N'/base/baseEmployee/{id}', N'0', N'1452186486253289472', N'2021-12-11 20:40:32.000', N'1452186486253289472', N'2021-12-11 20:40:32.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225478', N'1457620528469639168', N'BaseEmployeeController', N'lamp-base-server', N'DELETE', N'员工-删除', N'/base/baseEmployee', N'0', N'1452186486253289472', N'2021-12-11 20:45:26.000', N'1452186486253289472', N'2021-12-11 20:45:26.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225479', N'1457620470995091456', N'BaseEmployeeController', N'lamp-base-server', N'PUT', N'员工-修改', N'/base/baseEmployee', N'0', N'1452186486253289472', N'2021-12-11 20:46:52.000', N'1452186486253289472', N'2021-12-11 20:46:52.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225480', N'1461609523809615872', N'BaseEmployeeController', N'lamp-base-server', N'GET', N'员工-查询员工的角色', N'/base/baseEmployee/findEmployeeRoleByEmployeeId', N'0', N'1452186486253289472', N'2021-12-11 20:49:19.000', N'1452186486253289472', N'2021-12-11 20:49:19.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225481', N'1461609523809615872', N'BaseRoleController', N'lamp-base-server', N'POST', N'角色-分页查询员工的角色', N'/base/baseRole/pageMyRole', N'0', N'1452186486253289472', N'2021-12-11 20:49:19.000', N'1452186486253289472', N'2021-12-11 20:49:19.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225482', N'1461609523809615872', N'BaseEmployeeController', N'lamp-base-server', N'POST', N'员工-给员工分配角色', N'/base/baseEmployee/employeeRole', N'0', N'1452186486253289472', N'2021-12-11 20:49:19.000', N'1452186486253289472', N'2021-12-11 20:49:19.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225483', N'1449738119237599232', N'BaseOrgController', N'lamp-base-server', N'POST', N'组织-按树结构查询地区', N'/base/baseOrg/tree', N'0', N'1452186486253289472', N'2021-12-11 23:22:45.000', N'1452186486253289472', N'2021-12-11 23:22:45.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225484', N'1457665587088982016', N'BaseOrgController', N'lamp-base-server', N'POST', N'组织-新增', N'/base/baseOrg', N'0', N'1452186486253289472', N'2021-12-11 23:30:43.000', N'1452186486253289472', N'2021-12-11 23:30:43.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225485', N'1457665635705159680', N'BaseOrgController', N'lamp-base-server', N'PUT', N'组织-修改', N'/base/baseOrg', N'0', N'1452186486253289472', N'2021-12-11 23:31:01.000', N'1452186486253289472', N'2021-12-11 23:31:01.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225486', N'1457665696765837312', N'BaseOrgController', N'lamp-base-server', N'DELETE', N'组织-删除', N'/base/baseOrg', N'0', N'1452186486253289472', N'2021-12-11 23:31:16.000', N'1452186486253289472', N'2021-12-11 23:31:16.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225487', N'1449739134456299520', N'BasePositionController', N'lamp-base-server', N'POST', N'岗位-分页列表查询', N'/base/basePosition/page', N'0', N'1452186486253289472', N'2021-12-11 23:39:12.000', N'1452186486253289472', N'2021-12-11 23:39:12.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225488', N'1457665354649042944', N'BaseOrgController', N'lamp-base-server', N'POST', N'组织-按树结构查询地区', N'/base/baseOrg/tree', N'0', N'1452186486253289472', N'2021-12-11 23:39:45.000', N'1452186486253289472', N'2021-12-11 23:39:45.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225489', N'1457665354649042944', N'BasePositionController', N'lamp-base-server', N'POST', N'岗位-新增', N'/base/basePosition', N'0', N'1452186486253289472', N'2021-12-11 23:39:45.000', N'1452186486253289472', N'2021-12-11 23:39:45.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225490', N'1457665399683284992', N'BasePositionController', N'lamp-base-server', N'PUT', N'岗位-修改', N'/base/basePosition', N'0', N'1452186486253289472', N'2021-12-11 23:41:09.000', N'1452186486253289472', N'2021-12-11 23:41:09.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225491', N'1457665444381982720', N'BasePositionController', N'lamp-base-server', N'DELETE', N'岗位-删除', N'/base/basePosition', N'0', N'1452186486253289472', N'2021-12-11 23:41:21.000', N'1452186486253289472', N'2021-12-11 23:41:21.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225504', N'1449735103088427008', N'BaseLoginLogController', N'lamp-base-server', N'POST', N'登录日志-分页列表查询', N'/base/baseLoginLog/page', N'0', N'1452186486253289472', N'2021-12-12 00:28:52.000', N'1452186486253289472', N'2021-12-12 00:28:52.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225505', N'1460540557393657856', N'BaseLoginLogController', N'lamp-base-server', N'DELETE', N'登录日志-清空日志', N'/base/baseLoginLog/clear', N'0', N'1452186486253289472', N'2021-12-12 00:29:25.000', N'1452186486253289472', N'2021-12-12 00:29:25.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225506', N'1460540557393657856', N'BaseLoginLogController', N'lamp-base-server', N'DELETE', N'登录日志-删除', N'/base/baseLoginLog', N'0', N'1452186486253289472', N'2021-12-12 00:29:25.000', N'1452186486253289472', N'2021-12-12 00:29:25.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225507', N'1460540612146102272', N'BaseLoginLogController', N'lamp-base-server', N'GET', N'登录日志-查询单体详情', N'/base/baseLoginLog/detail', N'0', N'1452186486253289472', N'2021-12-12 00:30:14.000', N'1452186486253289472', N'2021-12-12 00:30:14.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225508', N'1449734944434683904', N'BaseOperationLogController', N'lamp-base-server', N'POST', N'操作日志-分页列表查询', N'/base/baseOperationLog/page', N'0', N'1452186486253289472', N'2021-12-12 00:30:33.000', N'1452186486253289472', N'2021-12-12 00:30:33.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225509', N'1460538960118808576', N'BaseOperationLogController', N'lamp-base-server', N'DELETE', N'操作日志-清空日志', N'/base/baseOperationLog/clear', N'0', N'1452186486253289472', N'2021-12-12 00:31:01.000', N'1452186486253289472', N'2021-12-12 00:31:01.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225510', N'1460538960118808576', N'BaseOperationLogController', N'lamp-base-server', N'DELETE', N'操作日志-删除', N'/base/baseOperationLog', N'0', N'1452186486253289472', N'2021-12-12 00:31:01.000', N'1452186486253289472', N'2021-12-12 00:31:01.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225511', N'1460539047851065344', N'BaseOperationLogController', N'lamp-base-server', N'GET', N'操作日志-查询单体详情', N'/base/baseOperationLog/detail', N'0', N'1452186486253289472', N'2021-12-12 00:31:30.000', N'1452186486253289472', N'2021-12-12 00:31:30.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225512', N'1449734707225821184', N'FileController', N'lamp-base-server', N'POST', N'文件实时上传-分页列表查询', N'/base/file/page', N'0', N'1452186486253289472', N'2021-12-12 00:50:10.000', N'1452186486253289472', N'2021-12-12 00:50:10.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225513', N'1460540979420332032', N'FileController', N'lamp-base-server', N'DELETE', N'文件实时上传-删除', N'/base/file', N'0', N'1452186486253289472', N'2021-12-12 00:57:27.000', N'1452186486253289472', N'2021-12-12 00:57:27.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225517', N'1457668537614073856', N'BaseRoleController', N'lamp-base-server', N'POST', N'角色-新增', N'/base/baseRole', N'0', N'1452186486253289472', N'2021-12-12 01:00:43.000', N'1452186486253289472', N'2021-12-12 01:00:43.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225518', N'1457668602952941568', N'BaseRoleController', N'lamp-base-server', N'PUT', N'角色-修改', N'/base/baseRole', N'0', N'1452186486253289472', N'2021-12-12 01:00:58.000', N'1452186486253289472', N'2021-12-12 01:00:58.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225519', N'1457668655000059904', N'BaseRoleController', N'lamp-base-server', N'DELETE', N'角色-删除', N'/base/baseRole', N'0', N'1452186486253289472', N'2021-12-12 01:01:13.000', N'1452186486253289472', N'2021-12-12 01:01:13.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225520', N'1457668749124435968', N'BaseOrgController', N'lamp-base-server', N'POST', N'组织-按树结构查询地区', N'/base/baseOrg/tree', N'0', N'1452186486253289472', N'2021-12-12 01:04:05.000', N'1452186486253289472', N'2021-12-12 01:04:05.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225521', N'1457668749124435968', N'BaseRoleController', N'lamp-base-server', N'GET', N'角色-查询角色绑定的员工', N'/base/baseRole/employeeList', N'0', N'1452186486253289472', N'2021-12-12 01:04:05.000', N'1452186486253289472', N'2021-12-12 01:04:05.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225522', N'1457668749124435968', N'BaseEmployeeController', N'lamp-base-server', N'POST', N'员工-分页列表查询', N'/base/baseEmployee/page', N'0', N'1452186486253289472', N'2021-12-12 01:04:05.000', N'1452186486253289472', N'2021-12-12 01:04:05.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225523', N'1457668749124435968', N'BaseRoleController', N'lamp-base-server', N'POST', N'角色-给角色分配员工', N'/base/baseRole/roleEmployee', N'0', N'1452186486253289472', N'2021-12-12 01:04:05.000', N'1452186486253289472', N'2021-12-12 01:04:05.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225524', N'1457668844297388032', N'BaseRoleController', N'lamp-base-server', N'POST', N'角色-给角色配置资源', N'/base/baseRole/roleResource', N'0', N'1452186486253289472', N'2021-12-12 01:04:38.000', N'1452186486253289472', N'2021-12-12 01:04:38.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225551', N'137848577387921412', N'DefUserController', N'lamp-system-server', N'POST', N'用户-分页列表查询', N'/system/defUser/page', N'0', N'1452186486253289472', N'2021-12-12 13:14:03.000', N'1452186486253289472', N'2021-12-12 13:14:03.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225552', N'1460542581971615744', N'DefUserController', N'lamp-system-server', N'POST', N'用户-新增', N'/system/defUser', N'0', N'1452186486253289472', N'2021-12-12 13:15:38.000', N'1452186486253289472', N'2021-12-12 13:15:38.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225553', N'1460542629845401600', N'DefUserController', N'lamp-system-server', N'PUT', N'用户-修改', N'/system/defUser', N'0', N'1452186486253289472', N'2021-12-12 13:21:18.000', N'1452186486253289472', N'2021-12-12 13:21:18.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225554', N'1460542675554926592', N'DefUserController', N'lamp-system-server', N'DELETE', N'用户-删除', N'/system/defUser', N'0', N'1452186486253289472', N'2021-12-12 13:21:32.000', N'1452186486253289472', N'2021-12-12 13:21:32.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225555', N'160561957882036227', N'DefUserController', N'lamp-system-server', N'PUT', N'用户-重置密码', N'/system/defUser/resetPassword', N'0', N'1452186486253289472', N'2021-12-12 13:22:52.000', N'1452186486253289472', N'2021-12-12 13:22:52.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225556', N'137848577387921413', N'DefApplicationController', N'lamp-system-server', N'POST', N'应用-分页列表查询', N'/system/defApplication/page', N'0', N'1452186486253289472', N'2021-12-12 13:24:38.000', N'1452186486253289472', N'2021-12-12 13:24:38.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225557', N'137848577387921417', N'DefApplicationController', N'lamp-system-server', N'POST', N'应用-新增', N'/system/defApplication', N'0', N'1452186486253289472', N'2021-12-12 13:26:16.000', N'1452186486253289472', N'2021-12-12 13:26:16.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225558', N'137848577387921419', N'DefApplicationController', N'lamp-system-server', N'POST', N'应用-新增', N'/system/defApplication', N'0', N'1452186486253289472', N'2021-12-12 15:48:50.000', N'1452186486253289472', N'2021-12-12 15:48:50.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225559', N'137848577387921418', N'DefApplicationController', N'lamp-system-server', N'PUT', N'应用-修改', N'/system/defApplication', N'0', N'1452186486253289472', N'2021-12-12 15:49:05.000', N'1452186486253289472', N'2021-12-12 15:49:05.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225562', N'137848577387921421', N'DefApplicationController', N'lamp-system-server', N'POST', N'应用-批量查询', N'/system/defApplication/query', N'0', N'1452186486253289472', N'2021-12-12 15:53:35.000', N'1452186486253289472', N'2021-12-12 15:53:35.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225563', N'137848577387921421', N'DefResourceController', N'lamp-system-server', N'POST', N'资源-查询系统所有的资源', N'/system/defResource/tree', N'0', N'1452186486253289472', N'2021-12-12 15:53:35.000', N'1452186486253289472', N'2021-12-12 15:53:35.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225564', N'137848577387921421', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-单体查询', N'/system/defResource/{id}', N'0', N'1452186486253289472', N'2021-12-12 15:53:35.000', N'1452186486253289472', N'2021-12-12 15:53:35.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225568', N'1460547377491345408', N'DefResourceController', N'lamp-system-server', N'DELETE', N'资源-删除', N'/system/defResource', N'0', N'1452186486253289472', N'2021-12-12 15:57:35.000', N'1452186486253289472', N'2021-12-12 15:57:35.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225569', N'137848577387921422', N'DefResourceController', N'lamp-system-server', N'POST', N'资源-新增', N'/system/defResource', N'0', N'1452186486253289472', N'2021-12-12 15:58:48.000', N'1452186486253289472', N'2021-12-12 15:58:48.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225570', N'137848577387921422', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-检测资源编码是否可用', N'/system/defResource/check', N'0', N'1452186486253289472', N'2021-12-12 15:58:48.000', N'1452186486253289472', N'2021-12-12 15:58:48.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225571', N'137848577387921422', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-检测资源路径是否可用', N'/system/defResource/checkPath', N'0', N'1452186486253289472', N'2021-12-12 15:58:48.000', N'1452186486253289472', N'2021-12-12 15:58:48.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225572', N'137848577387921422', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-检测资源名称是否可用', N'/system/defResource/checkName', N'0', N'1452186486253289472', N'2021-12-12 15:58:48.000', N'1452186486253289472', N'2021-12-12 15:58:48.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225573', N'1460547251804831744', N'DefResourceController', N'lamp-system-server', N'PUT', N'资源-修改', N'/system/defResource', N'0', N'1452186486253289472', N'2021-12-12 15:59:08.000', N'1452186486253289472', N'2021-12-12 15:59:08.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225574', N'1460547251804831744', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-单体查询', N'/system/defResource/{id}', N'0', N'1452186486253289472', N'2021-12-12 15:59:08.000', N'1452186486253289472', N'2021-12-12 15:59:08.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225575', N'1460547251804831744', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-检测资源路径是否可用', N'/system/defResource/checkPath', N'0', N'1452186486253289472', N'2021-12-12 15:59:08.000', N'1452186486253289472', N'2021-12-12 15:59:08.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225576', N'1460547251804831744', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-检测资源编码是否可用', N'/system/defResource/check', N'0', N'1452186486253289472', N'2021-12-12 15:59:08.000', N'1452186486253289472', N'2021-12-12 15:59:08.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225577', N'1460547251804831744', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-检测资源名称是否可用', N'/system/defResource/checkName', N'0', N'1452186486253289472', N'2021-12-12 15:59:08.000', N'1452186486253289472', N'2021-12-12 15:59:08.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225578', N'137848577387921414', N'DefApplicationController', N'lamp-system-server', N'POST', N'应用-批量查询', N'/system/defApplication/query', N'0', N'1452186486253289472', N'2021-12-12 17:29:18.000', N'1452186486253289472', N'2021-12-12 17:29:18.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225579', N'137848577387921414', N'DefResourceController', N'lamp-system-server', N'POST', N'资源-查询系统所有的资源', N'/system/defResource/tree', N'0', N'1452186486253289472', N'2021-12-12 17:29:18.000', N'1452186486253289472', N'2021-12-12 17:29:18.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225580', N'137848577387921414', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-单体查询', N'/system/defResource/{id}', N'0', N'1452186486253289472', N'2021-12-12 17:29:18.000', N'1452186486253289472', N'2021-12-12 17:29:18.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225581', N'139496646533709824', N'DefResourceController', N'lamp-system-server', N'POST', N'资源-新增', N'/system/defResource', N'0', N'1452186486253289472', N'2021-12-12 17:29:49.000', N'1452186486253289472', N'2021-12-12 17:29:49.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225582', N'139496646533709824', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-检测资源路径是否可用', N'/system/defResource/checkPath', N'0', N'1452186486253289472', N'2021-12-12 17:29:49.000', N'1452186486253289472', N'2021-12-12 17:29:49.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225583', N'139496646533709824', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-检测资源编码是否可用', N'/system/defResource/check', N'0', N'1452186486253289472', N'2021-12-12 17:29:49.000', N'1452186486253289472', N'2021-12-12 17:29:49.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225584', N'139496646533709824', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-检测资源名称是否可用', N'/system/defResource/checkName', N'0', N'1452186486253289472', N'2021-12-12 17:29:49.000', N'1452186486253289472', N'2021-12-12 17:29:49.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225590', N'139702946697838593', N'DefResourceController', N'lamp-system-server', N'DELETE', N'资源-删除', N'/system/defResource', N'0', N'1452186486253289472', N'2021-12-12 17:30:39.000', N'1452186486253289472', N'2021-12-12 17:30:39.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225605', N'1449391740313141248', N'DefDictController', N'lamp-system-server', N'POST', N'字典-分页列表查询', N'/system/defDict/page', N'0', N'1452186486253289472', N'2021-12-12 17:43:22.000', N'1452186486253289472', N'2021-12-12 17:43:22.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225606', N'1449391740313141248', N'DefDictItemController', N'lamp-system-server', N'POST', N'字典项-分页列表查询', N'/system/defDictItem/page', N'0', N'1452186486253289472', N'2021-12-12 17:43:22.000', N'1452186486253289472', N'2021-12-12 17:43:22.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225609', N'1449723907798990848', N'DefDictController', N'lamp-system-server', N'DELETE', N'字典-删除', N'/system/defDict', N'0', N'1452186486253289472', N'2021-12-12 17:44:32.000', N'1452186486253289472', N'2021-12-12 17:44:32.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225610', N'1449723704727568384', N'DefDictController', N'lamp-system-server', N'POST', N'字典-新增', N'/system/defDict', N'0', N'1452186486253289472', N'2021-12-12 17:57:01.000', N'1452186486253289472', N'2021-12-12 17:57:01.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225611', N'1449723704727568384', N'DefDictController', N'lamp-system-server', N'GET', N'字典-检测字典标识是否可用', N'/system/defDict/check', N'0', N'1452186486253289472', N'2021-12-12 17:57:01.000', N'1452186486253289472', N'2021-12-12 17:57:01.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225612', N'1449723831227777024', N'DefDictController', N'lamp-system-server', N'PUT', N'字典-修改', N'/system/defDict', N'0', N'1452186486253289472', N'2021-12-12 17:57:13.000', N'1452186486253289472', N'2021-12-12 17:57:13.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225613', N'1449723831227777024', N'DefDictController', N'lamp-system-server', N'GET', N'字典-检测字典标识是否可用', N'/system/defDict/check', N'0', N'1452186486253289472', N'2021-12-12 17:57:13.000', N'1452186486253289472', N'2021-12-12 17:57:13.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225614', N'1449724021556903936', N'DefDictItemController', N'lamp-system-server', N'POST', N'字典项-新增', N'/system/defDictItem', N'0', N'1452186486253289472', N'2021-12-12 18:12:49.000', N'1452186486253289472', N'2021-12-12 18:12:49.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225615', N'1449724119569399808', N'DefDictItemController', N'lamp-system-server', N'PUT', N'字典项-修改', N'/system/defDictItem', N'0', N'1452186486253289472', N'2021-12-12 18:13:02.000', N'1452186486253289472', N'2021-12-12 18:13:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225616', N'1449724196207722496', N'DefDictItemController', N'lamp-system-server', N'DELETE', N'字典项-删除', N'/system/defDictItem', N'0', N'1452186486253289472', N'2021-12-12 18:13:17.000', N'1452186486253289472', N'2021-12-12 18:13:17.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225617', N'1448315264151060480', N'DefParameterController', N'lamp-system-server', N'POST', N'参数配置-分页列表查询', N'/system/defParameter/page', N'0', N'1452186486253289472', N'2021-12-12 18:13:51.000', N'1452186486253289472', N'2021-12-12 18:13:51.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225618', N'1460547739937931264', N'DefParameterController', N'lamp-system-server', N'POST', N'参数配置-新增', N'/system/defParameter', N'0', N'1452186486253289472', N'2021-12-12 18:14:07.000', N'1452186486253289472', N'2021-12-12 18:14:07.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225619', N'1460547794912673792', N'DefParameterController', N'lamp-system-server', N'PUT', N'参数配置-修改', N'/system/defParameter', N'0', N'1452186486253289472', N'2021-12-12 18:14:20.000', N'1452186486253289472', N'2021-12-12 18:14:20.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225620', N'1460547893147467776', N'DefParameterController', N'lamp-system-server', N'DELETE', N'参数配置-删除', N'/system/defParameter', N'0', N'1452186486253289472', N'2021-12-12 18:14:36.000', N'1452186486253289472', N'2021-12-12 18:14:36.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225621', N'1448315406962917376', N'DefAreaController', N'lamp-system-server', N'POST', N'地区表-按树结构查询地区', N'/system/defArea/tree', N'0', N'1452186486253289472', N'2021-12-12 18:15:18.000', N'1452186486253289472', N'2021-12-12 18:15:18.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225622', N'1460547974479216640', N'DefAreaController', N'lamp-system-server', N'POST', N'地区表-新增', N'/system/defArea', N'0', N'1452186486253289472', N'2021-12-12 18:15:32.000', N'1452186486253289472', N'2021-12-12 18:15:32.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225623', N'1460548071271170048', N'DefAreaController', N'lamp-system-server', N'PUT', N'地区表-修改', N'/system/defArea', N'0', N'1452186486253289472', N'2021-12-12 18:15:48.000', N'1452186486253289472', N'2021-12-12 18:15:48.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225624', N'1460548273847664640', N'DefAreaController', N'lamp-system-server', N'DELETE', N'地区表-删除', N'/system/defArea', N'0', N'1452186486253289472', N'2021-12-12 18:16:00.000', N'1452186486253289472', N'2021-12-12 18:16:00.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225625', N'1448315861369618432', N'DefClientController', N'lamp-system-server', N'POST', N'客户端-分页列表查询', N'/system/defClient/page', N'0', N'1452186486253289472', N'2021-12-12 18:16:25.000', N'1452186486253289472', N'2021-12-12 18:16:25.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225626', N'1460548462427766784', N'DefClientController', N'lamp-system-server', N'POST', N'客户端-新增', N'/system/defClient', N'0', N'1452186486253289472', N'2021-12-12 18:59:33.000', N'1452186486253289472', N'2021-12-12 18:59:33.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225627', N'1460548519843594240', N'DefClientController', N'lamp-system-server', N'PUT', N'客户端-修改', N'/system/defClient', N'0', N'1452186486253289472', N'2021-12-12 19:00:03.000', N'1452186486253289472', N'2021-12-12 19:00:03.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225628', N'1460548553880371200', N'DefClientController', N'lamp-system-server', N'DELETE', N'客户端-删除', N'/system/defClient', N'0', N'1452186486253289472', N'2021-12-12 19:00:24.000', N'1452186486253289472', N'2021-12-12 19:00:24.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225629', N'1459061181095346176', N'DefLoginLogController', N'lamp-system-server', N'POST', N'登录日志-分页列表查询', N'/system/defLoginLog/page', N'0', N'1452186486253289472', N'2021-12-12 20:20:49.000', N'1452186486253289472', N'2021-12-12 20:20:49.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225630', N'1460548658754748416', N'DefLoginLogController', N'lamp-system-server', N'DELETE', N'登录日志-清空日志', N'/system/defLoginLog/clear', N'0', N'1452186486253289472', N'2021-12-12 20:21:06.000', N'1452186486253289472', N'2021-12-12 20:21:06.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225631', N'1460548658754748416', N'DefLoginLogController', N'lamp-system-server', N'DELETE', N'登录日志-删除', N'/system/defLoginLog', N'0', N'1452186486253289472', N'2021-12-12 20:21:06.000', N'1452186486253289472', N'2021-12-12 20:21:06.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'167990267029225632', N'1460548689041817600', N'DefLoginLogController', N'lamp-system-server', N'GET', N'登录日志-查询单体详情', N'/system/defLoginLog/detail', N'0', N'1452186486253289472', N'2021-12-12 20:21:42.000', N'1452186486253289472', N'2021-12-12 20:21:42.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169176494046707717', N'1457620408604819456', N'BasePositionController', N'lamp-base-server', N'POST', N'岗位-批量查询', N'/base/basePosition/query', N'0', N'1452186486253289472', N'2021-12-14 23:16:27.000', N'1452186486253289472', N'2021-12-14 23:16:27.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169176494046707718', N'1457620408604819456', N'BaseOrgController', N'lamp-base-server', N'POST', N'组织-按树结构查询地区', N'/base/baseOrg/tree', N'0', N'1452186486253289472', N'2021-12-14 23:16:27.000', N'1452186486253289472', N'2021-12-14 23:16:27.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169176494046707719', N'1457620408604819456', N'BaseEmployeeController', N'lamp-base-server', N'POST', N'员工-新增', N'/base/baseEmployee', N'0', N'1452186486253289472', N'2021-12-14 23:16:27.000', N'1452186486253289472', N'2021-12-14 23:16:27.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169176494046707720', N'1457620408604819456', N'DefUserController', N'lamp-system-server', N'GET', N'用户-检测手机号是否存在', N'/system/defUser/checkMobile', N'0', N'1452186486253289472', N'2021-12-14 23:16:27.000', N'1452186486253289472', N'2021-12-14 23:16:27.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169176494046707721', N'1449738581135327232', N'BaseEmployeeController', N'lamp-base-server', N'POST', N'员工-分页列表查询', N'/base/baseEmployee/page', N'0', N'1452186486253289472', N'2021-12-14 23:31:16.000', N'1452186486253289472', N'2021-12-14 23:31:16.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169176494046707722', N'1449738581135327232', N'BasePositionController', N'lamp-base-server', N'POST', N'岗位-批量查询', N'/base/basePosition/query', N'0', N'1452186486253289472', N'2021-12-14 23:31:16.000', N'1452186486253289472', N'2021-12-14 23:31:16.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169176494046707723', N'1449738581135327232', N'BaseOrgController', N'lamp-base-server', N'POST', N'组织-按树结构查询地区', N'/base/baseOrg/tree', N'0', N'1452186486253289472', N'2021-12-14 23:31:16.000', N'1452186486253289472', N'2021-12-14 23:31:16.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169380096971374660', N'1460542581971615744', N'DefUserController', N'lamp-system-server', N'GET', N'用户-检测手机号是否存在', N'/system/defUser/checkMobile', N'0', N'1452186486253289472', N'2021-12-15 16:44:28.000', N'1452186486253289472', N'2021-12-15 16:44:28.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169380096971374661', N'1460542581971615744', N'DefUserController', N'lamp-system-server', N'GET', N'用户-检测用户名是否存在', N'/system/defUser/checkUsername', N'0', N'1452186486253289472', N'2021-12-15 16:44:28.000', N'1452186486253289472', N'2021-12-15 16:44:28.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169380096971374662', N'1460542581971615744', N'DefUserController', N'lamp-system-server', N'GET', N'用户-检测邮箱是否存在', N'/system/defUser/checkEmail', N'0', N'1452186486253289472', N'2021-12-15 16:44:28.000', N'1452186486253289472', N'2021-12-15 16:44:28.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169380096971374663', N'1460542581971615744', N'DefUserController', N'lamp-system-server', N'GET', N'用户-检测身份证是否存在', N'/system/defUser/checkIdCard', N'0', N'1452186486253289472', N'2021-12-15 16:44:28.000', N'1452186486253289472', N'2021-12-15 16:44:28.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169380096971374665', N'1460542629845401600', N'DefUserController', N'lamp-system-server', N'GET', N'用户-检测手机号是否存在', N'/system/defUser/checkMobile', N'0', N'1452186486253289472', N'2021-12-15 16:47:16.000', N'1452186486253289472', N'2021-12-15 16:47:16.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169380096971374666', N'1460542629845401600', N'DefUserController', N'lamp-system-server', N'GET', N'用户-检测用户名是否存在', N'/system/defUser/checkUsername', N'0', N'1452186486253289472', N'2021-12-15 16:47:16.000', N'1452186486253289472', N'2021-12-15 16:47:16.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169380096971374667', N'1460542629845401600', N'DefUserController', N'lamp-system-server', N'GET', N'用户-检测邮箱是否存在', N'/system/defUser/checkEmail', N'0', N'1452186486253289472', N'2021-12-15 16:47:16.000', N'1452186486253289472', N'2021-12-15 16:47:16.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169380096971374668', N'1460542629845401600', N'DefUserController', N'lamp-system-server', N'GET', N'用户-检测身份证是否存在', N'/system/defUser/checkIdCard', N'0', N'1452186486253289472', N'2021-12-15 16:47:16.000', N'1452186486253289472', N'2021-12-15 16:47:16.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169380096971374673', N'137848577387921420', N'DefApplicationController', N'lamp-system-server', N'DELETE', N'应用-删除', N'/system/defApplication', N'0', N'1452186486253289472', N'2021-12-15 17:01:38.000', N'1452186486253289472', N'2021-12-15 17:01:38.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169380096971374676', N'1449724021556903936', N'DefDictItemController', N'lamp-system-server', N'GET', N'字典项-检测字典项标识是否可用', N'/system/defDictItem/check', N'0', N'1452186486253289472', N'2021-12-15 17:13:15.000', N'1452186486253289472', N'2021-12-15 17:13:15.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169380096971374678', N'1449724119569399808', N'DefDictItemController', N'lamp-system-server', N'GET', N'字典项-检测字典项标识是否可用', N'/system/defDictItem/check', N'0', N'1452186486253289472', N'2021-12-15 17:13:36.000', N'1452186486253289472', N'2021-12-15 17:13:36.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169380096971374682', N'1460547739937931264', N'DefParameterController', N'lamp-system-server', N'GET', N'参数配置-检测参数键是否可用', N'/system/defParameter/check', N'0', N'1452186486253289472', N'2021-12-15 17:16:37.000', N'1452186486253289472', N'2021-12-15 17:16:37.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'169380096971374684', N'1460547794912673792', N'DefParameterController', N'lamp-system-server', N'GET', N'参数配置-检测参数键是否可用', N'/system/defParameter/check', N'0', N'1452186486253289472', N'2021-12-15 17:16:55.000', N'1452186486253289472', N'2021-12-15 17:16:55.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'172353511420329985', N'172353511420329984', N'FileDefController', N'lamp-base-server', N'POST', N'默认库-文件操作接口-分页列表查询', N'/base/file/def/page', N'0', N'1452186486253289472', N'2021-12-23 11:16:30.000', N'1452186486253289472', N'2021-12-23 11:16:30.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'172353511420329990', N'172353511420329989', N'FileDefController', N'lamp-base-server', N'DELETE', N'默认库-文件操作接口-删除', N'/base/file/def', N'0', N'1452186486253289472', N'2021-12-23 11:19:09.000', N'1452186486253289472', N'2021-12-23 11:19:09.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'179951682229108736', N'1449734450995789824', N'DefApplicationController', N'lamp-system-server', N'GET', N'应用-查询可用的应用资源列表', N'/system/defApplication/findAvailableApplicationResourceList', N'0', N'1452186486253289472', N'2022-01-12 22:35:53.000', N'1452186486253289472', N'2022-01-12 22:35:53.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'179951682229108737', N'1449734450995789824', N'BaseRoleController', N'lamp-base-server', N'GET', N'角色-查询角色拥有的资源id集合', N'/base/baseRole/resourceList', N'0', N'1452186486253289472', N'2022-01-12 22:35:53.000', N'1452186486253289472', N'2022-01-12 22:35:53.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'179951682229108738', N'1449734450995789824', N'BaseRoleController', N'lamp-base-server', N'POST', N'角色-分页列表查询', N'/base/baseRole/page', N'0', N'1452186486253289472', N'2022-01-12 22:35:53.000', N'1452186486253289472', N'2022-01-12 22:35:53.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'179951682229108739', N'1449734450995789824', N'BaseRoleController', N'lamp-base-server', N'GET', N'角色-查询角色拥有的数据权限ID', N'/base/baseRole/findResourceDataScopeIdByRoleId', N'0', N'1452186486253289472', N'2022-01-12 22:35:53.000', N'1452186486253289472', N'2022-01-12 22:35:53.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'179951682229108740', N'1449734450995789824', N'DefApplicationController', N'lamp-system-server', N'GET', N'应用-查询可用的应用数据权限列表', N'/system/defApplication/findAvailableApplicationDataScopeList', N'0', N'1452186486253289472', N'2022-01-12 22:35:53.000', N'1452186486253289472', N'2022-01-12 22:35:53.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'199848844077301780', N'199848844077301779', N'BaseEmployeeController', N'lamp-boot-server', N'POST', N'员工-租户绑定或解绑用户', N'/base/baseEmployee/invitationUser', N'0', N'1452186486253289472', N'2022-03-07 14:13:32.000', N'1452186486253289472', N'2022-03-07 14:13:32.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'199848844077301781', N'199848844077301779', N'DefUserController', N'lamp-boot-server', N'POST', N'用户-邀请员工进入企业前精确查询用户', N'/system/defUser/queryUser', N'0', N'1452186486253289472', N'2022-03-07 14:13:32.000', N'1452186486253289472', N'2022-03-07 14:13:32.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'207592996529504256', N'139702946697838592', N'DefResourceController', N'lamp-system-server', N'PUT', N'资源-修改', N'/system/defResource', N'0', N'1452186486253289472', N'2022-03-28 11:21:57.000', N'1452186486253289472', N'2022-03-28 11:21:57.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'207592996529504257', N'139702946697838592', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-检测资源路径是否可用', N'/system/defResource/checkPath', N'0', N'1452186486253289472', N'2022-03-28 11:21:57.000', N'1452186486253289472', N'2022-03-28 11:21:57.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'207592996529504258', N'139702946697838592', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-检测资源编码是否可用', N'/system/defResource/check', N'0', N'1452186486253289472', N'2022-03-28 11:21:57.000', N'1452186486253289472', N'2022-03-28 11:21:57.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'207592996529504259', N'139702946697838592', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-检测资源名称是否可用', N'/system/defResource/checkName', N'0', N'1452186486253289472', N'2022-03-28 11:21:57.000', N'1452186486253289472', N'2022-03-28 11:21:57.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'207592996529504260', N'139702946697838592', N'DefResourceController', N'lamp-system-server', N'GET', N'资源-单体查询', N'/system/defResource/{id}', N'0', N'1452186486253289472', N'2022-03-28 11:21:57.000', N'1452186486253289472', N'2022-03-28 11:21:57.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'207592996529504261', N'139702946697838592', N'GeneratorController', N'lamp-gateway-server', N'GET', N'查询在线服务的前缀', N'/gateway/findOnlineService', N'0', N'1452186486253289472', N'2022-03-28 11:21:57.000', N'1452186486253289472', N'2022-03-28 11:21:57.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'207592996529504371', N'207592996529504370', N'DefGenTableController', N'lamp-generator-server', N'POST', N'代码生成-同步表的字段', N'/generator/defGenTable/syncField', N'0', N'1452186486253289472', N'2022-03-28 11:47:13.000', N'1452186486253289472', N'2022-03-28 11:47:13.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'207644798130061313', N'207644798130061312', N'DefGenTableController', N'lamp-generator-server', N'DELETE', N'代码生成-删除', N'/generator/defGenTable', N'0', N'1452186486253289472', N'2022-03-28 14:14:35.000', N'1452186486253289472', N'2022-03-28 14:14:35.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'207644798130061319', N'207644798130061318', N'DefGenTableColumnController', N'lamp-generator-server', N'DELETE', N'代码生成字段-删除', N'/generator/defGenTableColumn', N'0', N'1452186486253289472', N'2022-03-28 14:20:22.000', N'1452186486253289472', N'2022-03-28 14:20:22.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'207644798130061324', N'207644798130061323', N'DefGenTableColumnController', N'lamp-generator-server', N'PUT', N'代码生成字段-修改', N'/generator/defGenTableColumn', N'0', N'1452186486253289472', N'2022-03-28 14:21:34.000', N'1452186486253289472', N'2022-03-28 14:21:34.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'207950410822975489', N'207209017863307265', N'DefGenTestSimpleController', N'generator', N'ALL', N'测试', N'/**/defGenTestSimple/**', N'1', N'1452186486253289472', N'2022-03-29 10:57:05.000', N'1452186486253289472', N'2022-03-29 10:57:05.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'207950410822975490', N'207209017863307266', N'DefGenTestTreeController', N'generator', N'ALL', N'测试', N'/**/defGenTestTree/**', N'1', N'1452186486253289472', N'2022-03-29 10:57:45.000', N'1452186486253289472', N'2022-03-29 10:57:45.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'212844732215197696', N'198310764748996609', N'DefGenTableController', N'lamp-generator-server', N'POST', N'代码生成-分页列表查询', N'/generator/defGenTable/page', N'0', N'1452186486253289472', N'2022-04-11 13:54:00.000', N'1452186486253289472', N'2022-04-11 13:54:00.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'212844732215197697', N'198310764748996609', N'DefGenTableController', N'lamp-generator-server', N'GET', N'代码生成-查询单体详情', N'/generator/defGenTable/detail', N'0', N'1452186486253289472', N'2022-04-11 13:54:00.000', N'1452186486253289472', N'2022-04-11 13:54:00.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'212844732215197704', N'207592996529504364', N'DefGenTableController', N'lamp-generator-server', N'POST', N'代码生成-导入检测', N'/generator/defGenTable/importCheck', N'0', N'1452186486253289472', N'2022-04-11 14:20:56.000', N'1452186486253289472', N'2022-04-11 14:20:56.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'212844732215197705', N'207592996529504364', N'DefGenTableController', N'lamp-generator-server', N'POST', N'代码生成-导入表结构', N'/generator/defGenTable/importTable', N'0', N'1452186486253289472', N'2022-04-11 14:20:56.000', N'1452186486253289472', N'2022-04-11 14:20:56.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'212844732215197706', N'207592996529504364', N'DefGenTableController', N'lamp-generator-server', N'POST', N'代码生成-分页查询代码生成表', N'/generator/defGenTable/selectTableList', N'0', N'1452186486253289472', N'2022-04-11 14:20:56.000', N'1452186486253289472', N'2022-04-11 14:20:56.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'212844732215197707', N'207592996529504364', N'DefDatasourceConfigController', N'lamp-system-server', N'POST', N'数据源-批量查询', N'/system/defDatasourceConfig/query', N'0', N'1452186486253289472', N'2022-04-11 14:20:56.000', N'1452186486253289472', N'2022-04-11 14:20:56.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'212844732215197717', N'207644798130061314', N'DefGenTableController', N'lamp-generator-server', N'POST', N'代码生成-预览', N'/generator/defGenTable/previewCode', N'0', N'1452186486253289472', N'2022-04-11 15:13:28.000', N'1452186486253289472', N'2022-04-11 15:13:28.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'212844732215197718', N'207644798130061314', N'DefGenTableController', N'lamp-generator-server', N'GET', N'代码生成-批量生成代码', N'/generator/defGenTable/generatorCode', N'0', N'1452186486253289472', N'2022-04-11 15:13:28.000', N'1452186486253289472', N'2022-04-11 15:13:28.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'212844732215197719', N'207644798130061314', N'DefGenTableController', N'lamp-generator-server', N'GET', N'代码生成-批量下载代码', N'/generator/defGenTable/downloadZip', N'0', N'1452186486253289472', N'2022-04-11 15:13:28.000', N'1452186486253289472', N'2022-04-11 15:13:28.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'214351621490999296', N'207644798130061325', N'DefGenTableColumnController', N'lamp-generator-server', N'POST', N'代码生成字段-同步字段结构', N'/generator/defGenTableColumn/syncField', N'0', N'1452186486253289472', N'2022-04-15 17:17:39.000', N'1452186486253289472', N'2022-04-15 17:17:39.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'215458769570627595', N'143911967403278342', N'DefDictItemController', N'system', N'ALL', N'字典项全称', N'/system/defDictItem/**', N'1', N'1452186486253289472', N'2022-04-18 15:11:52.000', N'1452186486253289472', N'2022-04-18 15:11:52.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'215458769570627605', N'215458769570627596', N'DefDictController', N'system', N'ALL', N'test', N'/system/defDict/**', N'1', N'1452186486253289472', N'2022-04-18 15:21:08.000', N'1452186486253289472', N'2022-04-18 15:21:08.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'215458769570627606', N'215458769570627596', N'DefDictItemController', N'system', N'ALL', N'test', N'/system/defDictItem/**', N'1', N'1452186486253289472', N'2022-04-18 15:21:08.000', N'1452186486253289472', N'2022-04-18 15:21:08.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'215942494557306881', N'207209017863307267', N'DefGenTestMainSubController', N'generator', N'ALL', N'测试', N'/**/defGenTestMainSub/**', N'1', N'1452186486253289472', N'2022-04-20 00:06:35.000', N'1452186486253289472', N'2022-04-20 00:06:35.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'219171794567823361', N'219171794567823360', N'BaseRoleController', N'lamp-base-server', N'POST', N'角色-分页查询员工的角色', N'/base/baseRole/pageMyRole', N'0', N'1452186486253289472', N'2022-04-28 17:59:32.000', N'1452186486253289472', N'2022-04-28 17:59:32.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'219171794567823362', N'219171794567823360', N'BaseOrgController', N'lamp-base-server', N'GET', N'组织-查询机构的角色', N'/base/baseOrg/findOrgRoleByOrgId', N'0', N'1452186486253289472', N'2022-04-28 17:59:32.000', N'1452186486253289472', N'2022-04-28 17:59:32.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'219171794567823363', N'219171794567823360', N'BaseOrgController', N'lamp-base-server', N'POST', N'组织-给机构分配角色', N'/base/baseOrg/orgRole', N'0', N'1452186486253289472', N'2022-04-28 17:59:32.000', N'1452186486253289472', N'2022-04-28 17:59:32.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'221743822848131075', N'221743822848131074', N'DefDatasourceConfigController', N'lamp-system-server', N'POST', N'数据源-新增', N'/system/defDatasourceConfig', N'0', N'1452186486253289472', N'2022-05-05 16:05:50.000', N'1452186486253289472', N'2022-05-05 16:05:50.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'221743822848131077', N'221743822848131076', N'DefDatasourceConfigController', N'lamp-system-server', N'PUT', N'数据源-修改', N'/system/defDatasourceConfig', N'0', N'1452186486253289472', N'2022-05-05 16:07:00.000', N'1452186486253289472', N'2022-05-05 16:07:00.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'221743822848131079', N'221743822848131078', N'DefDatasourceConfigController', N'lamp-system-server', N'DELETE', N'数据源-删除', N'/system/defDatasourceConfig', N'0', N'1452186486253289472', N'2022-05-05 16:07:32.000', N'1452186486253289472', N'2022-05-05 16:07:32.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'221743822848131081', N'221743822848131080', N'DefDatasourceConfigController', N'lamp-system-server', N'POST', N'数据源-测试数据库链接', N'/system/defDatasourceConfig/testConnect', N'0', N'1452186486253289472', N'2022-05-05 16:07:58.000', N'1452186486253289472', N'2022-05-05 16:07:58.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'221743822848131082', N'221743822848131072', N'DefDatasourceConfigController', N'lamp-system-server', N'POST', N'数据源-分页列表查询', N'/system/defDatasourceConfig/page', N'0', N'1452186486253289472', N'2022-05-05 16:08:17.000', N'1452186486253289472', N'2022-05-05 16:08:17.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799437', N'206499429136465920', N'DefGenProjectController', N'lamp-generator-server', N'POST', N'项目生成-生成项目', N'/generator/defGenProject/generator', N'0', N'1452186486253289472', N'2022-06-16 20:40:41.000', N'1452186486253289472', N'2022-06-16 20:40:41.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799438', N'206499429136465920', N'DefGenProjectController', N'lamp-generator-server', N'POST', N'项目生成-获取默认配置', N'/generator/defGenProject/getDef', N'0', N'1452186486253289472', N'2022-06-16 20:40:41.000', N'1452186486253289472', N'2022-06-16 20:40:41.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799439', N'206499429136465920', N'DefGenProjectController', N'lamp-boot-server-none', N'POST', N'项目生成-下载项目', N'/generator/defGenProject/download', N'0', N'1452186486253289472', N'2022-06-16 20:40:41.000', N'1452186486253289472', N'2022-06-16 20:40:41.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799442', N'201343651610099712', N'DefGenTableController', N'lamp-generator-server', N'GET', N'代码生成-查询单体详情', N'/generator/defGenTable/detail', N'0', N'1452186486253289472', N'2022-06-16 20:41:02.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799443', N'201343651610099712', N'DefGenTableColumnController', N'lamp-generator-server', N'POST', N'代码生成字段-分页列表查询', N'/generator/defGenTableColumn/page', N'0', N'1452186486253289472', N'2022-06-16 20:41:02.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799444', N'201343651610099712', N'DefGenTableController', N'lamp-generator-server', N'PUT', N'代码生成-修改', N'/generator/defGenTable', N'0', N'1452186486253289472', N'2022-06-16 20:41:02.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799445', N'201343651610099712', N'DefResourceController', N'lamp-system-server', N'POST', N'资源-查询系统所有的资源', N'/system/defResource/tree', N'0', N'1452186486253289472', N'2022-06-16 20:41:02.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799446', N'201343651610099712', N'DefApplicationController', N'lamp-system-server', N'POST', N'应用-批量查询', N'/system/defApplication/query', N'0', N'1452186486253289472', N'2022-06-16 20:41:02.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799447', N'201343651610099712', N'GeneratorController', N'system', N'GET', N'查询在线服务', N'/gateway/findOnlineService', N'1', N'1452186486253289472', N'2022-06-16 20:41:02.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799448', N'201343651610099712', N'DefGenTableController', N'lamp-generator-server', N'POST', N'代码生成-预览', N'/generator/defGenTable/previewCode', N'0', N'1452186486253289472', N'2022-06-16 20:41:02.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799449', N'201343651610099712', N'DefGenTableController', N'lamp-generator-server', N'POST', N'代码生成-批量生成代码', N'/generator/defGenTable/generatorCode', N'0', N'1452186486253289472', N'2022-06-16 20:41:02.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799450', N'201343651610099712', N'DefGenTableController', N'lamp-generator-server', N'GET', N'代码生成-批量下载代码', N'/generator/defGenTable/downloadZip', N'0', N'1452186486253289472', N'2022-06-16 20:41:02.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799451', N'201343651610099712', N'DefGenTableController', N'lamp-generator-server', N'GET', N'代码生成-获取生成代码是否覆盖的默认配置', N'/generator/defGenTable/getDefFileOverrideStrategy', N'0', N'1452186486253289472', N'2022-06-16 20:41:02.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799452', N'201343651610099712', N'DefGenTableController', N'lamp-generator-server', N'GET', N'代码生成-获取字段模板映射', N'/generator/defGenTable/getFieldTemplate', N'0', N'1452186486253289472', N'2022-06-16 20:41:02.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799453', N'201343651610099712', N'DefGenTableController', N'lamp-generator-server', N'POST', N'代码生成-批量查询', N'/generator/defGenTable/query', N'0', N'1452186486253289472', N'2022-06-16 20:41:02.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'237425014733799454', N'201343651610099712', N'DefGenTableController', N'lamp-boot-server-none', N'POST', N'代码生成-批量查询', N'/generator/defGenTable/findTableList', N'0', N'1452186486253289472', N'2022-06-16 20:41:02.000', N'1452186486253289472', N'2022-06-16 20:41:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'241043868278063104', N'239326285086588930', N'DefinitionController', N'lamp-activiti-server', N'POST', N'page', N'/activiti/definition/page', N'0', N'1452186486253289472', N'2022-06-26 15:07:04.000', N'1452186486253289472', N'2022-06-26 15:07:04.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'241043868278063106', N'241043868278063105', N'DefinitionController', N'lamp-activiti-server', N'POST', N'upload', N'/activiti/definition/upload', N'0', N'1452186486253289472', N'2022-06-26 15:08:14.000', N'1452186486253289472', N'2022-06-26 15:08:14.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'241043868278063108', N'241043868278063107', N'DefinitionController', N'lamp-activiti-server', N'POST', N'saveModelByProcessDefinition', N'/activiti/definition/saveModelByProcessDefinition', N'0', N'1452186486253289472', N'2022-06-26 15:22:29.000', N'1452186486253289472', N'2022-06-26 15:22:29.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'241043868278063110', N'241043868278063109', N'DefinitionController', N'lamp-activiti-server', N'PUT', N'updateState', N'/activiti/definition/updateState', N'0', N'1452186486253289472', N'2022-06-26 15:23:50.000', N'1452186486253289472', N'2022-06-26 15:23:50.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'241043868278063112', N'241043868278063111', N'DefinitionController', N'lamp-activiti-server', N'GET', N'getDiagram', N'/activiti/definition/getDiagram', N'0', N'1452186486253289472', N'2022-06-26 15:24:35.000', N'1452186486253289472', N'2022-06-26 15:24:35.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'241043868278063114', N'241043868278063113', N'DefinitionController', N'lamp-activiti-server', N'GET', N'getXml', N'/activiti/definition/getXml', N'0', N'1452186486253289472', N'2022-06-26 15:35:36.000', N'1452186486253289472', N'2022-06-26 15:35:36.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'241043868278063116', N'241043868278063115', N'DefinitionController', N'lamp-activiti-server', N'DELETE', N'delete', N'/activiti/definition/delete', N'0', N'1452186486253289472', N'2022-06-26 15:35:56.000', N'1452186486253289472', N'2022-06-26 15:35:56.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'241043868278063117', N'239326285086588931', N'ModelController', N'lamp-activiti-server', N'POST', N'page', N'/activiti/model/page', N'0', N'1452186486253289472', N'2022-06-26 15:37:09.000', N'1452186486253289472', N'2022-06-26 15:37:09.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'241043868278063119', N'241043868278063118', N'ModelController', N'lamp-activiti-server', N'POST', N'save', N'/activiti/model/save', N'0', N'1452186486253289472', N'2022-06-26 15:38:27.000', N'1452186486253289472', N'2022-06-26 15:38:27.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'241043868278063122', N'241043868278063121', N'ModelController', N'lamp-activiti-server', N'POST', N'publish', N'/activiti/model/publish', N'0', N'1452186486253289472', N'2022-06-26 15:40:57.000', N'1452186486253289472', N'2022-06-26 15:40:57.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'241043868278063126', N'241043868278063125', N'ModelController', N'lamp-activiti-server', N'DELETE', N'deleteProcessInstance', N'/activiti/model/delete', N'0', N'1452186486253289472', N'2022-06-26 15:42:50.000', N'1452186486253289472', N'2022-06-26 15:42:50.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'241189033877700608', N'241043868278063123', N'ModelController', N'lamp-activiti-server', N'GET', N'exportXmlByModelId', N'/activiti/model/download', N'0', N'1452186486253289472', N'2022-06-26 23:12:52.000', N'1452186486253289472', N'2022-06-26 23:12:52.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'242540067380264963', N'242181583639937024', N'DefServerController', N'lamp-system-server', N'GET', N'服务监控-server', N'/system/defServer', N'0', N'1452186486253289472', N'2022-06-30 14:22:57.000', N'1452186486253289472', N'2022-06-30 14:22:57.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213194', N'249954310509559809', N'DefInterfaceController', N'lamp-system-server', N'POST', N'接口-分页列表查询', N'/system/defInterface/page', N'0', N'1452186486253289472', N'2022-07-30 23:41:11.000', N'1452186486253289472', N'2022-07-30 23:41:11.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213197', N'250025856074776579', N'DefInterfaceController', N'lamp-system-server', N'DELETE', N'接口-删除', N'/system/defInterface', N'0', N'1452186486253289472', N'2022-07-30 23:44:00.000', N'1452186486253289472', N'2022-07-30 23:44:00.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213198', N'249954310509559812', N'DefInterfacePropertyController', N'lamp-system-server', N'POST', N'接口属性-保存', N'/system/defInterfaceProperty/batchSave', N'0', N'1452186486253289472', N'2022-07-30 23:44:33.000', N'1452186486253289472', N'2022-07-30 23:44:33.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213199', N'249954310509559812', N'DefInterfacePropertyController', N'lamp-system-server', N'POST', N'接口属性-分页列表查询', N'/system/defInterfaceProperty/page', N'0', N'1452186486253289472', N'2022-07-30 23:44:33.000', N'1452186486253289472', N'2022-07-30 23:44:33.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213202', N'250025856074776580', N'ExtendInterfaceLogController', N'lamp-base-server', N'DELETE', N'接口执行日志-删除', N'/base/extendInterfaceLog', N'0', N'1452186486253289472', N'2022-07-30 23:46:48.000', N'1452186486253289472', N'2022-07-30 23:46:48.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213203', N'249954310509559813', N'ExtendInterfaceLoggingController', N'lamp-base-server', N'POST', N'接口执行日志记录-分页列表查询', N'/base/extendInterfaceLogging/page', N'0', N'1452186486253289472', N'2022-07-30 23:47:07.000', N'1452186486253289472', N'2022-07-30 23:47:07.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213204', N'250025856074776581', N'ExtendInterfaceLoggingController', N'lamp-base-server', N'DELETE', N'接口执行日志记录-删除', N'/base/extendInterfaceLogging', N'0', N'1452186486253289472', N'2022-07-30 23:47:29.000', N'1452186486253289472', N'2022-07-30 23:47:29.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213205', N'249954310509559811', N'DefMsgTemplateController', N'lamp-system-server', N'POST', N'消息模板-分页列表查询', N'/system/defMsgTemplate/page', N'0', N'1452186486253289472', N'2022-07-30 23:47:54.000', N'1452186486253289472', N'2022-07-30 23:47:54.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213208', N'250025856074776584', N'DefMsgTemplateController', N'lamp-system-server', N'DELETE', N'消息模板-删除', N'/system/defMsgTemplate', N'0', N'1452186486253289472', N'2022-07-30 23:48:49.000', N'1452186486253289472', N'2022-07-30 23:48:49.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213234', N'250025856074776577', N'DefInterfaceController', N'lamp-system-server', N'PUT', N'接口-修改', N'/system/defInterface', N'0', N'1452186486253289472', N'2022-07-31 00:01:13.000', N'1452186486253289472', N'2022-07-31 00:01:13.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213235', N'250025856074776577', N'DefInterfaceController', N'lamp-system-server', N'GET', N'接口-检测资源编码是否可用', N'/system/defInterface/check', N'0', N'1452186486253289472', N'2022-07-31 00:01:13.000', N'1452186486253289472', N'2022-07-31 00:01:13.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213236', N'250025856074776576', N'DefInterfaceController', N'lamp-system-server', N'POST', N'接口-新增', N'/system/defInterface', N'0', N'1452186486253289472', N'2022-07-31 00:01:34.000', N'1452186486253289472', N'2022-07-31 00:01:34.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213237', N'250025856074776576', N'DefInterfaceController', N'lamp-system-server', N'GET', N'接口-检测资源编码是否可用', N'/system/defInterface/check', N'0', N'1452186486253289472', N'2022-07-31 00:01:34.000', N'1452186486253289472', N'2022-07-31 00:01:34.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213238', N'249954310509559810', N'ExtendInterfaceLogController', N'lamp-base-server', N'POST', N'接口执行日志-分页列表查询', N'/base/extendInterfaceLog/page', N'0', N'1452186486253289472', N'2022-07-31 00:02:14.000', N'1452186486253289472', N'2022-07-31 00:02:14.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213239', N'249954310509559810', N'DefTenantController', N'lamp-system-server', N'POST', N'企业-批量查询', N'/system/defTenant/query', N'0', N'1452186486253289472', N'2022-07-31 00:02:14.000', N'1452186486253289472', N'2022-07-31 00:02:14.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213240', N'250025856074776582', N'DefMsgTemplateController', N'lamp-system-server', N'POST', N'消息模板-新增', N'/system/defMsgTemplate', N'0', N'1452186486253289472', N'2022-07-31 00:02:55.000', N'1452186486253289472', N'2022-07-31 00:02:55.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213241', N'250025856074776582', N'DefMsgTemplateController', N'lamp-system-server', N'GET', N'消息模板-检测资源编码是否可用', N'/system/defMsgTemplate/check', N'0', N'1452186486253289472', N'2022-07-31 00:02:55.000', N'1452186486253289472', N'2022-07-31 00:02:55.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213242', N'250025856074776582', N'DefInterfaceController', N'lamp-system-server', N'POST', N'接口-批量查询', N'/system/defInterface/query', N'0', N'1452186486253289472', N'2022-07-31 00:02:55.000', N'1452186486253289472', N'2022-07-31 00:02:55.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213243', N'250025856074776583', N'DefMsgTemplateController', N'lamp-system-server', N'PUT', N'消息模板-修改', N'/system/defMsgTemplate', N'0', N'1452186486253289472', N'2022-07-31 00:03:15.000', N'1452186486253289472', N'2022-07-31 00:03:15.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213244', N'250025856074776583', N'DefInterfaceController', N'lamp-system-server', N'POST', N'接口-批量查询', N'/system/defInterface/query', N'0', N'1452186486253289472', N'2022-07-31 00:03:15.000', N'1452186486253289472', N'2022-07-31 00:03:15.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'253596752014213245', N'250025856074776583', N'DefMsgTemplateController', N'lamp-system-server', N'GET', N'消息模板-检测资源编码是否可用', N'/system/defMsgTemplate/check', N'0', N'1452186486253289472', N'2022-07-31 00:03:15.000', N'1452186486253289472', N'2022-07-31 00:03:15.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'254523790640283648', N'1449734007292952576', N'ExtendMsgController', N'lamp-base-server', N'POST', N'消息-分页列表查询', N'/base/extendMsg/page', N'0', N'1452186486253289472', N'2022-08-01 21:51:02.000', N'1452186486253289472', N'2022-08-01 21:51:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'254523790640283649', N'1449734007292952576', N'BaseEmployeeController', N'lamp-base-server', N'POST', N'员工-批量查询', N'/base/baseEmployee/query', N'0', N'1452186486253289472', N'2022-08-01 21:51:02.000', N'1452186486253289472', N'2022-08-01 21:51:02.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'254523790640283650', N'1460436763976663040', N'ExtendMsgController', N'lamp-base-server', N'GET', N'消息-查询消息中心', N'/base/extendMsg/{id}', N'0', N'1452186486253289472', N'2022-08-01 21:51:57.000', N'1452186486253289472', N'2022-08-01 21:51:57.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'254523790640283651', N'1460436763976663040', N'ExtendMsgController', N'lamp-base-server', N'POST', N'消息-发布站内信', N'/base/extendMsg/publish', N'0', N'1452186486253289472', N'2022-08-01 21:51:57.000', N'1452186486253289472', N'2022-08-01 21:51:57.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'254523790640283652', N'1460436763976663040', N'ExtendMsgController', N'lamp-base-server', N'GET', N'消息-查询单体详情', N'/base/extendMsg/detail', N'0', N'1452186486253289472', N'2022-08-01 21:51:57.000', N'1452186486253289472', N'2022-08-01 21:51:57.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'254523790640283653', N'1460436856054218752', N'ExtendMsgController', N'lamp-base-server', N'POST', N'消息-发布站内信', N'/base/extendMsg/publish', N'0', N'1452186486253289472', N'2022-08-01 21:52:20.000', N'1452186486253289472', N'2022-08-01 21:52:20.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'254523790640283654', N'1460436856054218752', N'ExtendMsgController', N'lamp-base-server', N'GET', N'消息-查询消息中心', N'/base/extendMsg/{id}', N'0', N'1452186486253289472', N'2022-08-01 21:52:20.000', N'1452186486253289472', N'2022-08-01 21:52:20.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'254523790640283655', N'1460436856054218752', N'ExtendMsgController', N'lamp-base-server', N'GET', N'消息-查询单体详情', N'/base/extendMsg/detail', N'0', N'1452186486253289472', N'2022-08-01 21:52:20.000', N'1452186486253289472', N'2022-08-01 21:52:20.000')
GO

INSERT INTO [dbo].[def_resource_api]  VALUES (N'254523790640283656', N'1460436934051495936', N'ExtendMsgController', N'lamp-base-server', N'DELETE', N'消息-删除', N'/base/extendMsg', N'0', N'1452186486253289472', N'2022-08-01 21:52:35.000', N'1452186486253289472', N'2022-08-01 21:52:35.000')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for def_user
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_user]') AND type IN ('U'))
	DROP TABLE [dbo].[def_user]
GO

CREATE TABLE [dbo].[def_user] (
  [id] bigint  NOT NULL,
  [username] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [nick_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [email] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [mobile] varchar(11) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [id_card] varchar(18) COLLATE Chinese_PRC_CI_AS  NULL,
  [wx_open_id] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [dd_open_id] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [readonly] bit DEFAULT ((0)) NOT NULL,
  [nation] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [education] char(2) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [sex] char(1) COLLATE Chinese_PRC_CI_AS DEFAULT ('1') NULL,
  [state] bit DEFAULT ((1)) NULL,
  [work_describe] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [password_error_last_time] datetime  NULL,
  [password_error_num] int DEFAULT ((0)) NULL,
  [password_expire_time] datetime  NULL,
  [password] varchar(64) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [salt] varchar(20) COLLATE Chinese_PRC_CI_AS DEFAULT '' NOT NULL,
  [last_login_time] datetime  NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[def_user] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户名;大小写数字下划线',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'username'
GO

EXEC sp_addextendedproperty
'MS_Description', N'昵称',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'nick_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'邮箱',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'email'
GO

EXEC sp_addextendedproperty
'MS_Description', N'手机;1开头11位纯数字',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'mobile'
GO

EXEC sp_addextendedproperty
'MS_Description', N'身份证;15或18位',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'id_card'
GO

EXEC sp_addextendedproperty
'MS_Description', N'微信OpenId',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'wx_open_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'钉钉OpenId',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'dd_open_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'内置;[0-否 1-是]',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'readonly'
GO

EXEC sp_addextendedproperty
'MS_Description', N'民族;[01-汉族 99-其他]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.NATION)',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'nation'
GO

EXEC sp_addextendedproperty
'MS_Description', N'学历;[01-小学 02-中学 03-高中 04-专科 05-本科 06-硕士 07-博士 08-博士后 99-其他]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.EDUCATION)',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'education'
GO

EXEC sp_addextendedproperty
'MS_Description', N'性别;[1-男 2-女 3-未知]
@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.SEX)',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'sex'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态;[0-禁用 1-启用]',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'工作描述',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'work_describe'
GO

EXEC sp_addextendedproperty
'MS_Description', N'输错密码时间',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'password_error_last_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'密码错误次数',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'password_error_num'
GO

EXEC sp_addextendedproperty
'MS_Description', N'密码过期时间',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'password_expire_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'密码',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'password'
GO

EXEC sp_addextendedproperty
'MS_Description', N'密码盐',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'salt'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后登录时间',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'last_login_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人id',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'def_user',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户',
'SCHEMA', N'dbo',
'TABLE', N'def_user'
GO


-- ----------------------------
-- Records of def_user
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[def_user]  VALUES (N'1', N'superAdmin', N'超管', NULL, N'13000000000', NULL, NULL, NULL, N'0', N'01', N'02', N'1', N'1', N'', N'2021-11-22 11:22:42.000', N'0', NULL, N'ed731dc9c61991487cb3721864141ae78a83169b2ea4202d7f958d6052221b64', N'mie9yvvuinijost6j8s7', N'2021-11-22 11:22:42.000', N'1452186486253289472', N'2021-11-21 16:45:24.000', N'1452186486253289472', N'2021-11-21 16:45:24.000')
GO

INSERT INTO [dbo].[def_user]  VALUES (N'206280269840252999', N'user_123', N'11111', NULL, N'15211111144', NULL, NULL, NULL, N'0', N'  ', N'  ', N'M', N'1', N'', N'2022-03-24 21:46:56.000', N'0', NULL, N'024b91ce49c338bb6e64614cfebec34fb3634f106fbc703619ac27272c91932d', N'gl5bolk6h5e2u1gaund8', N'2022-03-24 21:46:56.000', N'1452186486253289472', N'2022-03-24 21:46:13.000', N'1452186486253289472', N'2022-03-24 21:46:13.000')
GO

INSERT INTO [dbo].[def_user]  VALUES (N'1452186486253289472', N'lamp', N'内置超管-啊汤哥', N'306479353@qq.com', N'15211111111', N'52212119', N'1', N'2', N'0', N'10', N'01', N'1', N'1', N'老子今天不上班，爽翻，巴适得板。', NULL, N'0', NULL, N'47f7a9c31a8c1bc0f11b1f7d57dd7642578839b5fa502c517a4c730c39d15af9', N'u7lftmhjpppsio79ld7e', N'2022-08-17 09:29:46.766', N'1', N'2021-10-24 16:13:33.000', N'1452186486253289472', N'2021-11-11 12:56:11.000')
GO

INSERT INTO [dbo].[def_user]  VALUES (N'1454329823852756992', N'test1', N'门店管理员-最后哥', NULL, N'15211111112', NULL, N'', N'', N'0', N'02', N'02', N'1', N'1', N'', N'2021-11-16 23:22:58.000', N'0', NULL, N'47f7a9c31a8c1bc0f11b1f7d57dd7642578839b5fa502c517a4c730c39d15af9', N'u7lftmhjpppsio79ld7e', N'2021-11-16 23:22:58.000', N'1', N'2021-10-30 14:10:25.000', N'1', N'2021-11-22 13:14:04.000')
GO

INSERT INTO [dbo].[def_user]  VALUES (N'1457904455960756224', N'test2', N'普通用户-小沙比', NULL, N'15211111113', NULL, N'', N'', N'0', N'02', N'06', N'1', N'1', N'', N'2022-01-16 10:51:07.000', N'0', NULL, N'47f7a9c31a8c1bc0f11b1f7d57dd7642578839b5fa502c517a4c730c39d15af9', N'u7lftmhjpppsio79ld7e', N'2022-01-16 10:51:07.000', N'1', N'2021-11-09 10:54:44.000', N'1', N'2021-11-22 13:14:05.000')
GO

INSERT INTO [dbo].[def_user]  VALUES (N'1459157721822527488', N'test_33', N'测试号', NULL, N'15211111114', NULL, N'', N'', N'0', N'  ', NULL, N'2', N'1', N'', N'2022-03-06 22:08:04.000', N'0', NULL, N'3834d8640e723952345f9d4d8373dceac3e3fb5665e1931590192bdf44b362b0', N'f93zwp2mgg12wslbdbu2', N'2022-03-06 22:08:04.000', N'1452186486253289472', N'2021-11-12 21:54:45.000', N'1452186486253289472', N'2021-11-21 17:08:32.000')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for def_user_application
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[def_user_application]') AND type IN ('U'))
	DROP TABLE [dbo].[def_user_application]
GO

CREATE TABLE [dbo].[def_user_application] (
  [id] bigint  NOT NULL,
  [user_id] bigint  NOT NULL,
  [application_id] bigint  NOT NULL,
  [created_by] bigint  NULL,
  [created_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [updated_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[def_user_application] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'def_user_application',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属用户ID',
'SCHEMA', N'dbo',
'TABLE', N'def_user_application',
'COLUMN', N'user_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属应用ID',
'SCHEMA', N'dbo',
'TABLE', N'def_user_application',
'COLUMN', N'application_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'def_user_application',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'def_user_application',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新人',
'SCHEMA', N'dbo',
'TABLE', N'def_user_application',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后更新时间',
'SCHEMA', N'dbo',
'TABLE', N'def_user_application',
'COLUMN', N'updated_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户的默认应用',
'SCHEMA', N'dbo',
'TABLE', N'def_user_application'
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
-- Records of extend_msg
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[extend_msg]  VALUES (N'260273682402770944', NULL, N'03', N'SUCCESS', N'APP', NULL, N'123123', N'<p>234124</p>', NULL, NULL, NULL, N'01', NULL, N'1452186486253289472', N'2022-08-17 09:35:12.020', N'1452186486253289472', N'2022-08-17 09:35:12.020', N'1451532773234835456')
GO

COMMIT
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
-- Records of extend_msg_recipient
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[extend_msg_recipient]  VALUES (N'260273682402770945', N'260273682402770944', N'1452186486492364800', NULL, N'1452186486253289472', N'2022-08-17 09:35:12.063', N'1452186486253289472', N'2022-08-17 09:35:12.063')
GO

COMMIT
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
-- Records of extend_notice
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[extend_notice]  VALUES (N'260273682402770946', N'260273682402770944', NULL, NULL, N'1452186486492364800', N'01', N'123123', N'<p>234124</p>', NULL, NULL, NULL, N'1', NULL, NULL, N'0', N'0', N'2022-08-17 09:35:12.020', N'1452186486253289472', N'2022-08-17 09:35:12.020', N'1452186486253289472', N'1451532773234835456')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for WORKER_NODE
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[WORKER_NODE]') AND type IN ('U'))
	DROP TABLE [dbo].[WORKER_NODE]
GO

CREATE TABLE [dbo].[WORKER_NODE] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [host_name] varchar(64) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [port] varchar(64) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [type] int  NOT NULL,
  [launch_date] date  NOT NULL,
  [modified] datetime DEFAULT ('CURRENT_TIMESTAMP') NOT NULL,
  [created] datetime DEFAULT ('CURRENT_TIMESTAMP') NOT NULL
)
GO

ALTER TABLE [dbo].[WORKER_NODE] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'auto;increment id',
'SCHEMA', N'dbo',
'TABLE', N'WORKER_NODE',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'主机名',
'SCHEMA', N'dbo',
'TABLE', N'WORKER_NODE',
'COLUMN', N'host_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'端口',
'SCHEMA', N'dbo',
'TABLE', N'WORKER_NODE',
'COLUMN', N'port'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点类型:;ACTUAL 或者 CONTAINER',
'SCHEMA', N'dbo',
'TABLE', N'WORKER_NODE',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'上线日期',
'SCHEMA', N'dbo',
'TABLE', N'WORKER_NODE',
'COLUMN', N'launch_date'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'WORKER_NODE',
'COLUMN', N'modified'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'WORKER_NODE',
'COLUMN', N'created'
GO

EXEC sp_addextendedproperty
'MS_Description', N'DB;WorkerID Assigner for UID Generator',
'SCHEMA', N'dbo',
'TABLE', N'WORKER_NODE'
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
ALTER TABLE [dbo].[com_appendix] ADD CONSTRAINT [PK__com_appe__3213E83F3CEB3B57] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table com_file
-- ----------------------------
ALTER TABLE [dbo].[com_file] ADD CONSTRAINT [PK__com_file__3213E83F18249230] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table def_application
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_application_key]
ON [dbo].[def_application] (
  [app_key] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table def_application
-- ----------------------------
ALTER TABLE [dbo].[def_application] ADD CONSTRAINT [PK__def_appl__3213E83FF409984F] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table def_area
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_area_code]
ON [dbo].[def_area] (
  [code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table def_area
-- ----------------------------
ALTER TABLE [dbo].[def_area] ADD CONSTRAINT [PK__def_area__3213E83F0AF1FB21] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table def_client
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_client_client_id]
ON [dbo].[def_client] (
  [client_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table def_client
-- ----------------------------
ALTER TABLE [dbo].[def_client] ADD CONSTRAINT [PK__def_clie__3213E83F31C7994E] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table def_datasource_config
-- ----------------------------
ALTER TABLE [dbo].[def_datasource_config] ADD CONSTRAINT [PK__def_data__3213E83F22E55E2D] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table def_dict
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_dict_key]
ON [dbo].[def_dict] (
  [parent_id] ASC,
  [key_] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table def_dict
-- ----------------------------
ALTER TABLE [dbo].[def_dict] ADD CONSTRAINT [PK__def_dict__3213E83F1F1CD2F5] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table def_gen_table
-- ----------------------------
ALTER TABLE [dbo].[def_gen_table] ADD CONSTRAINT [PK__def_gen___3213E83FDDF7EA09] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table def_gen_table_column
-- ----------------------------
ALTER TABLE [dbo].[def_gen_table_column] ADD CONSTRAINT [PK__def_gen___3213E83F76F5446B] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table def_gen_test_simple
-- ----------------------------
ALTER TABLE [dbo].[def_gen_test_simple] ADD CONSTRAINT [PK__def_gen___3213E83F1B1D6785] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table def_gen_test_tree
-- ----------------------------
ALTER TABLE [dbo].[def_gen_test_tree] ADD CONSTRAINT [PK__def_gen___3213E83F30A65AD8] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table def_interface
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [UK_INTERFACE_CODE]
ON [dbo].[def_interface] (
  [code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table def_interface
-- ----------------------------
ALTER TABLE [dbo].[def_interface] ADD CONSTRAINT [PK__def_inte__3213E83F5B38EF0B] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table def_interface_property
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [UK_I_P_INTERFACE_ID_KEY]
ON [dbo].[def_interface_property] (
  [interface_id] ASC,
  [key_] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table def_interface_property
-- ----------------------------
ALTER TABLE [dbo].[def_interface_property] ADD CONSTRAINT [PK__def_inte__3213E83F25AAB8B4] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table def_login_log
-- ----------------------------
ALTER TABLE [dbo].[def_login_log] ADD CONSTRAINT [PK__def_logi__3213E83F2B57E13A] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table def_msg_template
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [UK_MSG_TEMPLATE_CODE]
ON [dbo].[def_msg_template] (
  [code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table def_msg_template
-- ----------------------------
ALTER TABLE [dbo].[def_msg_template] ADD CONSTRAINT [PK__def_msg___3213E83F4B243025] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table def_parameter
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_param_key]
ON [dbo].[def_parameter] (
  [key_] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table def_parameter
-- ----------------------------
ALTER TABLE [dbo].[def_parameter] ADD CONSTRAINT [PK__def_para__3213E83F493E00C3] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table def_resource
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_resource_code]
ON [dbo].[def_resource] (
  [code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table def_resource
-- ----------------------------
ALTER TABLE [dbo].[def_resource] ADD CONSTRAINT [PK__def_reso__3213E83F5066422E] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table def_resource_api
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_res_api_resource_id]
ON [dbo].[def_resource_api] (
  [resource_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table def_resource_api
-- ----------------------------
ALTER TABLE [dbo].[def_resource_api] ADD CONSTRAINT [PK__def_reso__3213E83F80944E07] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table def_user
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_user_username]
ON [dbo].[def_user] (
  [username] ASC
)
GO

CREATE UNIQUE NONCLUSTERED INDEX [uk_user_mobile]
ON [dbo].[def_user] (
  [mobile] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table def_user
-- ----------------------------
ALTER TABLE [dbo].[def_user] ADD CONSTRAINT [PK__def_user__3213E83F62751CF2] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table def_user_application
-- ----------------------------
ALTER TABLE [dbo].[def_user_application] ADD CONSTRAINT [PK__def_user__3213E83F6A8A254A] PRIMARY KEY CLUSTERED ([id])
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
-- Primary Key structure for table extend_notice
-- ----------------------------
ALTER TABLE [dbo].[extend_notice] ADD CONSTRAINT [PK__extend_n__3213E83F8A3F0518] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table WORKER_NODE
-- ----------------------------
ALTER TABLE [dbo].[WORKER_NODE] ADD CONSTRAINT [PK__WORKER_N__3213E83FAD7312FE] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

