/*
 Navicat Premium Data Transfer

 Source Server         : 172.26.3.67
 Source Server Type    : SQL Server
 Source Server Version : 15004236
 Source Host           : 117.187.194.249:1433
 Source Catalog        : lamp_defaults
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15004236
 File Encoding         : 65001

 Date: 28/08/2022 20:52:51
*/


-- ----------------------------
-- Table structure for c_appendix
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_appendix]') AND type IN ('U'))
	DROP TABLE [dbo].[c_appendix]
GO

CREATE TABLE [dbo].[c_appendix] (
  [id] bigint  NOT NULL,
  [biz_id] bigint DEFAULT ((0)) NOT NULL,
  [biz_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [file_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [bucket] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [path] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [original_file_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [content_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [size_] bigint DEFAULT ((0)) NULL,
  [create_time] datetime  NOT NULL,
  [created_by] bigint  NULL,
  [update_time] datetime  NOT NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[c_appendix] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务id',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix',
'COLUMN', N'biz_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务类型',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix',
'COLUMN', N'biz_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件类型',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix',
'COLUMN', N'file_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'桶',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix',
'COLUMN', N'bucket'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件相对地址',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix',
'COLUMN', N'path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'原始文件名',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix',
'COLUMN', N'original_file_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件类型',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix',
'COLUMN', N'content_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'大小',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix',
'COLUMN', N'size_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务附件',
'SCHEMA', N'dbo',
'TABLE', N'c_appendix'
GO


-- ----------------------------
-- Table structure for c_datasource_config
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_datasource_config]') AND type IN ('U'))
	DROP TABLE [dbo].[c_datasource_config]
GO

CREATE TABLE [dbo].[c_datasource_config] (
  [id] bigint  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [username] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [password] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [url] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [driver_class_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL,
  [update_time] datetime  NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[c_datasource_config] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键ID',
'SCHEMA', N'dbo',
'TABLE', N'c_datasource_config',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'c_datasource_config',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'账号',
'SCHEMA', N'dbo',
'TABLE', N'c_datasource_config',
'COLUMN', N'username'
GO

EXEC sp_addextendedproperty
'MS_Description', N'密码',
'SCHEMA', N'dbo',
'TABLE', N'c_datasource_config',
'COLUMN', N'password'
GO

EXEC sp_addextendedproperty
'MS_Description', N'链接',
'SCHEMA', N'dbo',
'TABLE', N'c_datasource_config',
'COLUMN', N'url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'驱动',
'SCHEMA', N'dbo',
'TABLE', N'c_datasource_config',
'COLUMN', N'driver_class_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_datasource_config',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'c_datasource_config',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'c_datasource_config',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'c_datasource_config',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据源',
'SCHEMA', N'dbo',
'TABLE', N'c_datasource_config'
GO


-- ----------------------------
-- Table structure for c_tenant
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_tenant]') AND type IN ('U'))
	DROP TABLE [dbo].[c_tenant]
GO

CREATE TABLE [dbo].[c_tenant] (
  [id] bigint  NOT NULL,
  [code] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [type] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [connect_type] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [status] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [readonly_] bit DEFAULT ((0)) NULL,
  [duty] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [expiration_time] datetime  NULL,
  [logo] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [describe_] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL,
  [update_time] datetime  NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[c_tenant] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键ID',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'企业编码',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'企业名称',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类型;
#{CREATE:创建;REGISTER:注册}',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'链接类型;
#TenantConnectTypeEnum{SYSTEM:系统;CUSTOM:自定义}',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'connect_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态;
#{NORMAL:正常;WAIT_INIT:待初始化;FORBIDDEN:禁用;WAITING:待审核;REFUSE:拒绝;DELETE:已删除}',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'内置',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'readonly_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'责任人',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'duty'
GO

EXEC sp_addextendedproperty
'MS_Description', N'有效期;
为空表示永久',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'expiration_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'logo地址',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'logo'
GO

EXEC sp_addextendedproperty
'MS_Description', N'企业简介',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'describe_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'企业',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant'
GO


-- ----------------------------
-- Records of c_tenant
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[c_tenant]  VALUES (N'1', N'0000', N'最后内置的运营&超级租户', N'CREATE', N'SYSTEM', N'NORMAL', N'1', N'最后', NULL, NULL, N'内置租户,用于测试租户系统所有功能, 用于管理整个系统.请勿删除', N'2019-08-29 16:50:35.000', N'1', N'2019-08-29 16:50:35.000', N'1')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for c_tenant_datasource_config
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_tenant_datasource_config]') AND type IN ('U'))
	DROP TABLE [dbo].[c_tenant_datasource_config]
GO

CREATE TABLE [dbo].[c_tenant_datasource_config] (
  [id] bigint  NOT NULL,
  [tenant_id] bigint  NOT NULL,
  [datasource_config_id] bigint  NOT NULL,
  [db_prefix] varchar(100) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[c_tenant_datasource_config] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant_datasource_config',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'租户id',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant_datasource_config',
'COLUMN', N'tenant_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据源id',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant_datasource_config',
'COLUMN', N'datasource_config_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据库前缀',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant_datasource_config',
'COLUMN', N'db_prefix'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant_datasource_config',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant_datasource_config',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'租户数据源关系',
'SCHEMA', N'dbo',
'TABLE', N'c_tenant_datasource_config'
GO


-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[undo_log]') AND type IN ('U'))
	DROP TABLE [dbo].[undo_log]
GO

CREATE TABLE [dbo].[undo_log] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [branch_id] bigint  NOT NULL,
  [xid] varchar(100) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [context] varchar(128) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [rollback_info] text COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [log_status] int  NOT NULL,
  [log_created] datetime  NULL,
  [log_modified] datetime  NULL
)
GO

ALTER TABLE [dbo].[undo_log] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'increment;id',
'SCHEMA', N'dbo',
'TABLE', N'undo_log',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'branch;transaction id',
'SCHEMA', N'dbo',
'TABLE', N'undo_log',
'COLUMN', N'branch_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'global;transaction id',
'SCHEMA', N'dbo',
'TABLE', N'undo_log',
'COLUMN', N'xid'
GO

EXEC sp_addextendedproperty
'MS_Description', N'undo_log;context,such as serialization',
'SCHEMA', N'dbo',
'TABLE', N'undo_log',
'COLUMN', N'context'
GO

EXEC sp_addextendedproperty
'MS_Description', N'rollback;info',
'SCHEMA', N'dbo',
'TABLE', N'undo_log',
'COLUMN', N'rollback_info'
GO

EXEC sp_addextendedproperty
'MS_Description', N'0:normal;status,1:defense status',
'SCHEMA', N'dbo',
'TABLE', N'undo_log',
'COLUMN', N'log_status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'create;datetime',
'SCHEMA', N'dbo',
'TABLE', N'undo_log',
'COLUMN', N'log_created'
GO

EXEC sp_addextendedproperty
'MS_Description', N'modify;datetime',
'SCHEMA', N'dbo',
'TABLE', N'undo_log',
'COLUMN', N'log_modified'
GO

EXEC sp_addextendedproperty
'MS_Description', N'AT;transaction mode undo table',
'SCHEMA', N'dbo',
'TABLE', N'undo_log'
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
  [modified] datetime  NULL,
  [created] datetime  NULL
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
-- Primary Key structure for table c_appendix
-- ----------------------------
ALTER TABLE [dbo].[c_appendix] ADD CONSTRAINT [PK__c_append__3213E83F98E83F4E] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table c_datasource_config
-- ----------------------------
ALTER TABLE [dbo].[c_datasource_config] ADD CONSTRAINT [PK__c_dataso__3213E83F10725CD7] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table c_tenant
-- ----------------------------
CREATE NONCLUSTERED INDEX [uk_code]
ON [dbo].[c_tenant] (
  [code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table c_tenant
-- ----------------------------
ALTER TABLE [dbo].[c_tenant] ADD CONSTRAINT [PK__c_tenant__3213E83FAF848805] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table c_tenant_datasource_config
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_tenan_application]
ON [dbo].[c_tenant_datasource_config] (
  [tenant_id] ASC,
  [datasource_config_id] ASC,
  [db_prefix] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table c_tenant_datasource_config
-- ----------------------------
ALTER TABLE [dbo].[c_tenant_datasource_config] ADD CONSTRAINT [PK__c_tenant__3213E83F9A81512E] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table undo_log
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [ux_undo_log]
ON [dbo].[undo_log] (
  [xid] ASC,
  [branch_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table undo_log
-- ----------------------------
ALTER TABLE [dbo].[undo_log] ADD CONSTRAINT [PK__undo_log__3213E83F4F202001] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table WORKER_NODE
-- ----------------------------
ALTER TABLE [dbo].[WORKER_NODE] ADD CONSTRAINT [PK__WORKER_N__3213E83F446B4500] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

