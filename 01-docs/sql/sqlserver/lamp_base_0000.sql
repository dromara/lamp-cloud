/*
 Navicat Premium Data Transfer

 Source Server         : 172.26.3.67
 Source Server Type    : SQL Server
 Source Server Version : 15004236
 Source Host           : 117.187.194.249:1433
 Source Catalog        : lamp_base_0000
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15004236
 File Encoding         : 65001

 Date: 28/08/2022 20:53:49
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
-- Table structure for c_application
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_application]') AND type IN ('U'))
	DROP TABLE [dbo].[c_application]
GO

CREATE TABLE [dbo].[c_application] (
  [id] bigint  NOT NULL,
  [client_id] varchar(24) COLLATE Chinese_PRC_CI_AS  NULL,
  [client_secret] varchar(32) COLLATE Chinese_PRC_CI_AS  NULL,
  [website] varchar(100) COLLATE Chinese_PRC_CI_AS  NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [icon] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [app_type] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [describe_] varchar(200) COLLATE Chinese_PRC_CI_AS  NULL,
  [state] bit DEFAULT ((1)) NULL,
  [created_by] bigint  NULL,
  [create_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [update_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[c_application] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_application',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'客户端ID',
'SCHEMA', N'dbo',
'TABLE', N'c_application',
'COLUMN', N'client_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'客户端密码',
'SCHEMA', N'dbo',
'TABLE', N'c_application',
'COLUMN', N'client_secret'
GO

EXEC sp_addextendedproperty
'MS_Description', N'官网',
'SCHEMA', N'dbo',
'TABLE', N'c_application',
'COLUMN', N'website'
GO

EXEC sp_addextendedproperty
'MS_Description', N'应用名称',
'SCHEMA', N'dbo',
'TABLE', N'c_application',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'应用图标',
'SCHEMA', N'dbo',
'TABLE', N'c_application',
'COLUMN', N'icon'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类型;
#{SERVER:服务应用;APP:手机应用;PC:PC网页应用;WAP:手机网页应用}',
'SCHEMA', N'dbo',
'TABLE', N'c_application',
'COLUMN', N'app_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'c_application',
'COLUMN', N'describe_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'c_application',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'c_application',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_application',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人id',
'SCHEMA', N'dbo',
'TABLE', N'c_application',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'c_application',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'应用',
'SCHEMA', N'dbo',
'TABLE', N'c_application'
GO


-- ----------------------------
-- Records of c_application
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[c_application]  VALUES (N'1', N'lamp_web_plus', N'lamp_web_plus_secret', N'https://tangyh.top', N'lamp快速开发平台', NULL, N'PC', N'内置', N'1', N'1', N'2020-04-02 15:05:14.000', N'1', N'2020-04-02 15:05:17.000')
GO

INSERT INTO [dbo].[c_application]  VALUES (N'2', N'lamp_web', N'lamp_web_secret', N'https://tangyh.top', N'lamp快速开发平台', NULL, N'PC', N'内置', N'1', N'1', N'2020-04-02 15:05:14.000', N'1', N'2020-04-02 15:05:17.000')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for c_area
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_area]') AND type IN ('U'))
	DROP TABLE [dbo].[c_area]
GO

CREATE TABLE [dbo].[c_area] (
  [id] bigint  NOT NULL,
  [code] varchar(64) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [label] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [full_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [sort_value] int DEFAULT ((1)) NULL,
  [longitude] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [latitude] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [level_] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [source_] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [state] bit DEFAULT ((0)) NULL,
  [parent_id] bigint DEFAULT ((0)) NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL,
  [update_time] datetime  NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[c_area] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'id',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编码',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'label'
GO

EXEC sp_addextendedproperty
'MS_Description', N'全名',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'full_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'经度',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'longitude'
GO

EXEC sp_addextendedproperty
'MS_Description', N'维度',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'latitude'
GO

EXEC sp_addextendedproperty
'MS_Description', N'行政区级;
@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.AREA_LEVEL)',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'level_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据来源',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'source_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'父ID',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人',
'SCHEMA', N'dbo',
'TABLE', N'c_area',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'地区表',
'SCHEMA', N'dbo',
'TABLE', N'c_area'
GO


-- ----------------------------
-- Table structure for c_dictionary
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_dictionary]') AND type IN ('U'))
	DROP TABLE [dbo].[c_dictionary]
GO

CREATE TABLE [dbo].[c_dictionary] (
  [id] bigint  NOT NULL,
  [type] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [label] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [code] varchar(64) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [name] varchar(64) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [state] bit DEFAULT ((1)) NULL,
  [describe_] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [sort_value] int DEFAULT ((1)) NULL,
  [icon] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [css_style] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [css_class] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [readonly_] bit DEFAULT ((0)) NULL,
  [created_by] bigint  NULL,
  [create_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [update_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[c_dictionary] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类型',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类型标签',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'label'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编码',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'describe_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'图标',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'icon'
GO

EXEC sp_addextendedproperty
'MS_Description', N'css样式',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'css_style'
GO

EXEC sp_addextendedproperty
'MS_Description', N'css;class',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'css_class'
GO

EXEC sp_addextendedproperty
'MS_Description', N'内置',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'readonly_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人id',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'字典项',
'SCHEMA', N'dbo',
'TABLE', N'c_dictionary'
GO


-- ----------------------------
-- Records of c_dictionary
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'1', N'AREA_LEVEL', N'行政区划', N'01', N'国家', N'1', N'', N'1', N'', N'', N'', N'1', N'3', N'2020-01-20 15:12:57.000', N'3', N'2020-01-20 15:12:57.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'2', N'AREA_LEVEL', N'行政区划', N'02', N'省份/直辖市', N'1', N'', N'2', N'', N'', N'', N'1', N'3', N'2020-01-20 15:13:45.000', N'3', N'2020-01-20 15:13:45.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'3', N'AREA_LEVEL', N'行政区划', N'03', N'地市', N'1', N'', N'3', N'', N'', N'', N'1', N'3', N'2020-01-20 15:14:16.000', N'3', N'2020-01-20 15:14:16.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'4', N'AREA_LEVEL', N'行政区划', N'04', N'区县', N'1', N'', N'4', N'', N'', N'', N'1', N'3', N'2020-01-20 15:14:54.000', N'3', N'2020-01-20 15:14:54.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'5', N'AREA_LEVEL', N'行政区划', N'05', N'乡镇', N'1', N'', N'5', N'', N'', N'', N'1', N'3', N'2020-03-09 23:33:46.000', N'3', N'2020-03-09 23:33:46.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'10', N'EDUCATION', N'学历', N'01', N'小学', N'1', N'', N'1', N'', N'', N'', N'1', N'3', N'2020-03-09 23:34:13.000', N'3', N'2020-03-09 23:34:13.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'11', N'EDUCATION', N'学历', N'02', N'中学', N'1', N'', N'2', N'', N'', N'', N'1', N'3', N'2020-03-09 23:34:32.000', N'3', N'2020-03-09 23:34:32.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'12', N'EDUCATION', N'学历', N'03', N'高中', N'1', N'', N'3', N'', N'', N'', N'1', N'3', N'2020-03-09 23:34:40.000', N'3', N'2020-03-09 23:34:40.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'13', N'EDUCATION', N'学历', N'04', N'专科', N'1', N'', N'4', N'', N'', N'', N'1', N'1', N'2019-06-04 11:36:29.000', N'1', N'2019-06-04 11:36:29.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'14', N'EDUCATION', N'学历', N'05', N'本科', N'1', N'', N'5', N'', N'', N'', N'1', N'1', N'2019-06-04 11:36:19.000', N'1', N'2019-06-04 11:36:19.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'15', N'EDUCATION', N'学历', N'06', N'硕士', N'1', N'', N'6', N'', N'', N'', N'1', N'1', N'2019-06-04 11:36:29.000', N'1', N'2019-06-04 11:36:29.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'16', N'EDUCATION', N'学历', N'07', N'博士', N'1', N'', N'7', N'', N'', N'', N'1', N'1', N'2019-06-04 11:36:29.000', N'1', N'2019-06-04 11:36:29.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'17', N'EDUCATION', N'学历', N'08', N'博士后', N'1', N'', N'8', N'', N'', N'', N'1', N'1', N'2019-06-04 11:36:29.000', N'1', N'2019-06-04 11:36:29.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'29', N'EDUCATION', N'学历', N'20', N'其他', N'1', N'', N'20', N'', N'', N'', N'1', N'3', N'2020-03-09 23:34:54.000', N'3', N'2020-03-09 23:34:54.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'40', N'ORG_TYPE', N'机构类型', N'01', N'单位', N'1', N'', N'1', N'', N'', N'', N'1', N'1', N'2020-11-23 21:13:17.000', N'1', N'2020-11-23 21:13:17.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'41', N'ORG_TYPE', N'机构类型', N'02', N'部门', N'1', N'', N'2', N'', N'', N'', N'1', N'1', N'2020-11-23 21:13:17.000', N'1', N'2020-11-23 21:13:17.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'43', N'NATION', N'民族', N'01', N'汉族', N'1', N'', N'0', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'44', N'NATION', N'民族', N'02', N'壮族', N'1', N'', N'1', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'45', N'NATION', N'民族', N'03', N'满族', N'1', N'', N'2', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'46', N'NATION', N'民族', N'04', N'回族', N'1', N'', N'3', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'47', N'NATION', N'民族', N'05', N'苗族', N'1', N'', N'4', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'48', N'NATION', N'民族', N'06', N'维吾尔族', N'1', N'', N'5', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'49', N'NATION', N'民族', N'07', N'土家族', N'1', N'', N'6', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'50', N'NATION', N'民族', N'08', N'彝族', N'1', N'', N'7', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'51', N'NATION', N'民族', N'09', N'蒙古族', N'1', N'', N'8', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'52', N'NATION', N'民族', N'10', N'藏族', N'1', N'', N'9', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'53', N'NATION', N'民族', N'11', N'布依族', N'1', N'', N'10', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'54', N'NATION', N'民族', N'12', N'侗族', N'1', N'', N'11', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'55', N'NATION', N'民族', N'13', N'瑶族', N'1', N'', N'12', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'56', N'NATION', N'民族', N'14', N'朝鲜族', N'1', N'', N'13', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'57', N'NATION', N'民族', N'15', N'白族', N'1', N'', N'14', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'58', N'NATION', N'民族', N'16', N'哈尼族', N'1', N'', N'15', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'59', N'NATION', N'民族', N'17', N'哈萨克族', N'1', N'', N'16', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'60', N'NATION', N'民族', N'18', N'黎族', N'1', N'', N'17', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'61', N'NATION', N'民族', N'19', N'傣族', N'1', N'', N'18', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'62', N'NATION', N'民族', N'20', N'畲族', N'1', N'', N'19', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'63', N'NATION', N'民族', N'21', N'傈僳族', N'1', N'', N'20', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'64', N'NATION', N'民族', N'22', N'仡佬族', N'1', N'', N'21', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'65', N'NATION', N'民族', N'23', N'东乡族', N'1', N'', N'22', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'66', N'NATION', N'民族', N'24', N'高山族', N'1', N'', N'23', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'67', N'NATION', N'民族', N'25', N'拉祜族', N'1', N'', N'24', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'68', N'NATION', N'民族', N'26', N'水族', N'1', N'', N'25', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'69', N'NATION', N'民族', N'27', N'佤族', N'1', N'', N'26', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'70', N'NATION', N'民族', N'28', N'纳西族', N'1', N'', N'27', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'71', N'NATION', N'民族', N'29', N'羌族', N'1', N'', N'28', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'72', N'NATION', N'民族', N'30', N'土族', N'1', N'', N'29', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'73', N'NATION', N'民族', N'31', N'仫佬族', N'1', N'', N'30', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'74', N'NATION', N'民族', N'32', N'锡伯族', N'1', N'', N'31', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'75', N'NATION', N'民族', N'33', N'柯尔克孜族', N'1', N'', N'32', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'76', N'NATION', N'民族', N'34', N'达斡尔族', N'1', N'', N'33', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'77', N'NATION', N'民族', N'35', N'景颇族', N'1', N'', N'34', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'78', N'NATION', N'民族', N'36', N'毛南族', N'1', N'', N'35', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'79', N'NATION', N'民族', N'37', N'撒拉族', N'1', N'', N'36', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'80', N'NATION', N'民族', N'38', N'塔吉克族', N'1', N'', N'37', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'81', N'NATION', N'民族', N'39', N'阿昌族', N'1', N'', N'38', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'82', N'NATION', N'民族', N'40', N'普米族', N'1', N'', N'39', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'83', N'NATION', N'民族', N'41', N'鄂温克族', N'1', N'', N'40', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'84', N'NATION', N'民族', N'42', N'怒族', N'1', N'', N'41', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'85', N'NATION', N'民族', N'43', N'京族', N'1', N'', N'42', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'86', N'NATION', N'民族', N'44', N'基诺族', N'1', N'', N'43', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'87', N'NATION', N'民族', N'45', N'德昂族', N'1', N'', N'44', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'88', N'NATION', N'民族', N'46', N'保安族', N'1', N'', N'45', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'89', N'NATION', N'民族', N'47', N'俄罗斯族', N'1', N'', N'46', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'90', N'NATION', N'民族', N'48', N'裕固族', N'1', N'', N'47', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'91', N'NATION', N'民族', N'49', N'乌兹别克族', N'1', N'', N'48', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'92', N'NATION', N'民族', N'50', N'门巴族', N'1', N'', N'49', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'93', N'NATION', N'民族', N'51', N'鄂伦春族', N'1', N'', N'50', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'94', N'NATION', N'民族', N'52', N'独龙族', N'1', N'', N'51', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'95', N'NATION', N'民族', N'53', N'塔塔尔族', N'1', N'', N'52', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'96', N'NATION', N'民族', N'54', N'赫哲族', N'1', N'', N'53', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'97', N'NATION', N'民族', N'55', N'珞巴族', N'1', N'', N'54', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'98', N'NATION', N'民族', N'56', N'布朗族', N'1', N'', N'55', N'', N'', N'', N'1', N'1', N'2018-03-15 20:11:01.000', N'1', N'2018-03-15 20:11:04.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'99', N'NATION', N'民族', N'57', N'其他', N'1', N'', N'100', N'', N'', N'', N'1', N'3', N'2020-03-09 23:38:29.000', N'3', N'2020-03-09 23:38:29.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'110', N'POSITION_STATUS', N'职位状态', N'01', N'在职', N'1', N'', N'1', N'', N'', N'', N'1', N'1', N'2019-06-04 11:38:16.000', N'1', N'2019-06-04 11:38:16.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'111', N'POSITION_STATUS', N'职位状态', N'02', N'请假', N'1', N'', N'2', N'', N'', N'', N'1', N'3', N'2020-03-09 23:39:30.000', N'3', N'2020-03-09 23:39:30.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'112', N'POSITION_STATUS', N'职位状态', N'03', N'离职', N'1', N'', N'3', N'', N'', N'', N'1', N'1', N'2019-06-04 11:38:50.000', N'1', N'2019-06-04 11:38:50.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'1486198107023605760', N'RESOURCE_TYPE', N'资源类型', N'20', N'菜单', N'1', N'', N'2', N'', N'', N'', N'1', N'2', N'2022-01-26 12:43:36.000', N'2', N'2022-01-26 12:46:58.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'1486198623417925632', N'RESOURCE_DATA_SCOPE', N'数据范围', N'01', N'全部', N'1', N'', N'1', N'', N'', N'', N'1', N'2', N'2022-01-26 12:45:39.000', N'2', N'2022-01-26 12:47:45.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'1486198858865180672', N'ROLE_CATEGORY', N'角色类别', N'10', N'功能角色', N'1', N'', N'1', N'', N'', N'', N'1', N'2', N'2022-01-26 12:46:35.000', N'2', N'2022-01-26 12:51:54.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'1486198996287356928', N'RESOURCE_TYPE', N'资源类型', N'60', N'数据', N'1', N'', N'6', N'', N'', N'', N'1', N'2', N'2022-01-26 12:47:08.000', N'2', N'2022-01-26 12:47:08.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'1486199651718660096', N'RESOURCE_DATA_SCOPE', N'数据范围', N'02', N'本单位及子级', N'1', N'', N'2', N'', N'', N'', N'1', N'2', N'2022-01-26 12:49:44.000', N'2', N'2022-01-26 12:49:44.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'1486199679237488640', N'RESOURCE_DATA_SCOPE', N'数据范围', N'03', N'本单位', N'1', N'', N'3', N'', N'', N'', N'1', N'2', N'2022-01-26 12:49:50.000', N'2', N'2022-01-26 12:49:50.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'1486199726792507392', N'RESOURCE_DATA_SCOPE', N'数据范围', N'04', N'本部门及子级', N'1', N'', N'4', N'', N'', N'', N'1', N'2', N'2022-01-26 12:50:02.000', N'2', N'2022-01-26 12:50:02.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'1486199753644441600', N'RESOURCE_DATA_SCOPE', N'数据范围', N'05', N'本部门', N'1', N'', N'5', N'', N'', N'', N'1', N'2', N'2022-01-26 12:50:08.000', N'2', N'2022-01-26 12:50:08.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'1486199809227358208', N'RESOURCE_DATA_SCOPE', N'数据范围', N'06', N'个人', N'1', N'', N'6', N'', N'', N'', N'1', N'2', N'2022-01-26 12:50:21.000', N'2', N'2022-01-26 12:50:21.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'1486199838218387456', N'RESOURCE_DATA_SCOPE', N'数据范围', N'07', N'自定义', N'1', N'', N'7', N'', N'', N'', N'1', N'2', N'2022-01-26 12:50:28.000', N'2', N'2022-01-26 12:53:23.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'1486200233623814144', N'ROLE_CATEGORY', N'角色类别', N'20', N'桌面角色', N'1', N'', N'2', N'', N'', N'', N'1', N'2', N'2022-01-26 12:52:03.000', N'2', N'2022-01-26 12:52:03.000')
GO

INSERT INTO [dbo].[c_dictionary]  VALUES (N'1486200358744096768', N'ROLE_CATEGORY', N'角色类别', N'30', N'数据角色', N'1', N'', N'3', N'', N'', N'', N'1', N'2', N'2022-01-26 12:52:32.000', N'2', N'2022-01-26 12:52:32.000')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for c_file
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_file]') AND type IN ('U'))
	DROP TABLE [dbo].[c_file]
GO

CREATE TABLE [dbo].[c_file] (
  [id] bigint  NOT NULL,
  [biz_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [file_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [storage_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [bucket] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [path] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [url] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [unique_file_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [file_md5] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [original_file_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [content_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [suffix] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [size_] bigint DEFAULT ((0)) NULL,
  [create_time] datetime  NOT NULL,
  [created_by] bigint  NOT NULL,
  [update_time] datetime  NOT NULL,
  [updated_by] bigint  NOT NULL
)
GO

ALTER TABLE [dbo].[c_file] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务类型',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'biz_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件类型',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'file_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'存储类型
LOCAL;FAST_DFS MIN_IO ALI',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'storage_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'桶',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'bucket'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件相对地址',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件访问地址',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'唯一文件名',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'unique_file_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件md5',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'file_md5'
GO

EXEC sp_addextendedproperty
'MS_Description', N'原始文件名',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'original_file_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件类型',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'content_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'后缀',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'suffix'
GO

EXEC sp_addextendedproperty
'MS_Description', N'大小',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'size_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'c_file',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'增量文件上传日志',
'SCHEMA', N'dbo',
'TABLE', N'c_file'
GO


-- ----------------------------
-- Table structure for c_login_log
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_login_log]') AND type IN ('U'))
	DROP TABLE [dbo].[c_login_log]
GO

CREATE TABLE [dbo].[c_login_log] (
  [id] bigint  NOT NULL,
  [request_ip] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [user_id] bigint  NULL,
  [user_name] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [account] varchar(30) COLLATE Chinese_PRC_CI_AS  NULL,
  [description] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [login_date] char(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [ua] text COLLATE Chinese_PRC_CI_AS  NULL,
  [browser] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [browser_version] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [operating_system] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [location] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[c_login_log] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录IP',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'request_ip'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录人ID',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'user_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录人姓名',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'user_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录人账号',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'account'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录描述',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录时间',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'login_date'
GO

EXEC sp_addextendedproperty
'MS_Description', N'浏览器请求头',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'ua'
GO

EXEC sp_addextendedproperty
'MS_Description', N'浏览器名称',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'browser'
GO

EXEC sp_addextendedproperty
'MS_Description', N'浏览器版本',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'browser_version'
GO

EXEC sp_addextendedproperty
'MS_Description', N'操作系统',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'operating_system'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录地点',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'location'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录日志',
'SCHEMA', N'dbo',
'TABLE', N'c_login_log'
GO


-- ----------------------------
-- Table structure for c_menu
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_menu]') AND type IN ('U'))
	DROP TABLE [dbo].[c_menu]
GO

CREATE TABLE [dbo].[c_menu] (
  [id] bigint  NOT NULL,
  [label] varchar(20) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [resource_type] char(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [tree_grade] int  NULL,
  [tree_path] varchar(512) COLLATE Chinese_PRC_CI_AS  NULL,
  [describe_] varchar(200) COLLATE Chinese_PRC_CI_AS  NULL,
  [is_general] bit DEFAULT ((0)) NULL,
  [path] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [component] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [state] bit DEFAULT ((1)) NULL,
  [sort_value] int DEFAULT ((1)) NULL,
  [icon] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [group_] varchar(20) COLLATE Chinese_PRC_CI_AS  NULL,
  [data_scope] char(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [custom_class] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [is_def] bit DEFAULT ((0)) NULL,
  [parent_id] bigint DEFAULT ((0)) NULL,
  [readonly_] bit DEFAULT ((0)) NULL,
  [created_by] bigint  NULL,
  [create_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [update_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[c_menu] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'label'
GO

EXEC sp_addextendedproperty
'MS_Description', N'资源类型;[20-菜单 60-数据];@Echo(api = DICTIONARY_ITEM_FEIGN_CLASS,dictType = EchoDictType.RESOURCE_TYPE)',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'resource_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'树层级',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'tree_grade'
GO

EXEC sp_addextendedproperty
'MS_Description', N'树路径',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'tree_path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'describe_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'通用菜单;True表示无需分配所有人就可以访问的',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'is_general'
GO

EXEC sp_addextendedproperty
'MS_Description', N'路径',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'组件',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'component'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'菜单图标',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'icon'
GO

EXEC sp_addextendedproperty
'MS_Description', N'分组',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'group_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据范围;[01-全部 02-本单位及子级 03-本单位 04-本部门 05-本部门及子级 06-个人 07-自定义]',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'data_scope'
GO

EXEC sp_addextendedproperty
'MS_Description', N'实现类',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'custom_class'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否默认',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'is_def'
GO

EXEC sp_addextendedproperty
'MS_Description', N'父级菜单ID',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'内置',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'readonly_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人id',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'c_menu',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'菜单',
'SCHEMA', N'dbo',
'TABLE', N'c_menu'
GO


-- ----------------------------
-- Records of c_menu
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'10', N'租户设置', N'20', N'0', N',', N'', N'0', N'/tenant', N'Layout', N'1', N'10', N'ant-design:group-outlined', N'', NULL, NULL, NULL, N'0', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'20', N'工作台', N'20', N'0', N',', N'', N'0', N'/workbench', N'Layout', N'1', N'20', N'ant-design:dashboard-filled', N'', NULL, NULL, NULL, N'0', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'30', N'组织管理', N'20', N'0', N',', N'', N'0', N'/org', N'Layout', N'1', N'30', N'ant-design:cluster-outlined', N'', NULL, NULL, NULL, N'0', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'40', N'资源中心', N'20', N'0', N',', N'', N'0', N'/resources', N'Layout', N'1', N'40', N'ant-design:cloud-server-outlined', N'', NULL, NULL, NULL, N'0', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'50', N'流程管理', N'20', N'0', N',', N'', N'0', N'/activiti', N'Layout', N'1', N'50', N'tabler:chart-dots', N'', NULL, NULL, NULL, N'0', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'60', N'系统设置', N'20', N'0', N',', N'', N'0', N'/system', N'Layout', N'1', N'60', N'ant-design:setting-outlined', N'', NULL, NULL, NULL, N'0', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'70', N'网关管理', N'20', N'0', N',', N'', N'0', N'/gateway', N'Layout', N'1', N'70', N'ant-design:sliders-filled', N'', NULL, NULL, NULL, N'0', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'80', N'开发者管理', N'20', N'0', N',', N'', N'0', N'/developer', N'Layout', N'1', N'80', N'ant-design:bug-filled', N'', NULL, NULL, NULL, N'0', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'90', N'了解lamp', N'20', N'0', N',', N'', N'1', N'/community', N'Layout', N'1', N'90', N'ant-design:github-filled', N'', NULL, NULL, NULL, N'0', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'100', N'更多功能', N'20', N'0', N',', N'', N'1', N'/more', N'Layout', N'1', N'100', N'ant-design:appstore-add-outlined', N'', NULL, NULL, NULL, N'0', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'110', N'租户管理', N'20', N'1', N',10,', N'', N'0', N'/tenant/tenant', N'lamp/tenant/tenant/index', N'1', N'20', N'', N'', NULL, NULL, NULL, N'10', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-25 16:20:26.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'111', N'超级用户', N'20', N'1', N',10,', N'', N'0', N'/tenant/user', N'lamp/tenant/user/index', N'1', N'30', N'', N'', NULL, NULL, NULL, N'10', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-25 16:20:30.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'112', N'数据源配置', N'20', N'1', N',10,', N'', N'0', N'/tenant/datasourceConfig', N'lamp/tenant/datasourceConfig/index', N'1', N'10', N'', N'', NULL, NULL, NULL, N'10', N'0', N'1', N'2020-11-25 16:20:21.000', N'1', N'2020-11-25 16:20:21.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'120', N'通知公告', N'20', N'1', N',20,', N'', N'0', N'/workbench/notice', N'lamp/workbench/notice/index', N'1', N'10', N'', N'', NULL, NULL, NULL, N'20', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'121', N'待我审批', N'20', N'1', N',20,', N'', N'0', N'/workbench/todo', N'lamp/workbench/todo/index', N'1', N'20', N'', N'', NULL, NULL, NULL, N'20', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'122', N'我已审批', N'20', N'1', N',20,', N'', N'0', N'/workbench/done', N'lamp/workbench/done/index', N'1', N'30', N'', N'', NULL, NULL, NULL, N'20', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'123', N'我发起的', N'20', N'1', N',20,', N'', N'0', N'/workbench/started', N'lamp/workbench/started/index', N'1', N'40', N'', N'', NULL, NULL, NULL, N'20', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'130', N'机构管理', N'20', N'1', N',30,', N'', N'0', N'/org/org', N'lamp/org/org/index', N'1', N'10', N'', N'', NULL, NULL, NULL, N'30', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'131', N'岗位管理', N'20', N'1', N',30,', N'', N'0', N'/org/station', N'lamp/org/station/index', N'1', N'20', N'', N'', NULL, NULL, NULL, N'30', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'132', N'用户管理', N'20', N'1', N',30,', N'', N'0', N'/org/user', N'lamp/org/user/index', N'1', N'30', N'', N'', NULL, NULL, NULL, N'30', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'140', N'消息中心', N'20', N'1', N',40,', N'', N'0', N'/resources/msg', N'lamp/resources/msg/index', N'1', N'10', N'', N'', NULL, NULL, NULL, N'40', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'141', N'短信模版', N'20', N'1', N',40,', N'', N'0', N'/resources/smsTemplate', N'lamp/resources/smsTemplate/index', N'1', N'20', N'', N'', NULL, NULL, NULL, N'40', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'142', N'短信中心', N'20', N'1', N',40,', N'', N'0', N'/resources/sms', N'lamp/resources/sms/index', N'1', N'30', N'', N'', NULL, NULL, NULL, N'40', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'143', N'附件管理', N'20', N'1', N',40,', N'', N'0', N'/resources/attachment', N'lamp/resources/attachment/index', N'1', N'40', N'', N'', NULL, NULL, NULL, N'40', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'150', N'流程部署', N'20', N'1', N',50,', N'', N'0', N'/activiti/deploymentManager', N'lamp/activiti/deploymentManager/index', N'1', N'10', N'', N'', NULL, NULL, NULL, N'50', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'151', N'模型管理', N'20', N'1', N',50,', N'', N'0', N'/activiti/modelManager', N'lamp/activiti/modelManager/index', N'1', N'20', N'', N'', NULL, NULL, NULL, N'50', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'152', N'请假流程', N'20', N'1', N',50,', N'', N'0', N'/activiti/level', N'Layout', N'1', N'30', N'', N'', NULL, NULL, NULL, N'50', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'153', N'请假管理', N'20', N'2', N',50,152,', N'', N'0', N'/activiti/leave/instant', N'lamp/activiti/leave/instantManager/index', N'1', N'1', N'', N'', NULL, NULL, NULL, N'152', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'154', N'请假任务', N'20', N'2', N',50,152,', N'', N'0', N'/activiti/leave/ruTask', N'lamp/activiti/leave/ruTask/index', N'1', N'2', N'', N'', NULL, NULL, NULL, N'152', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'155', N'报销流程', N'20', N'1', N',50,', N'', N'0', N'/activiti/reimbursement', N'Layout', N'1', N'40', N'', N'', NULL, NULL, NULL, N'50', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'156', N'报销管理', N'20', N'2', N',50,155,', N'', N'0', N'/activiti/reimbursement/instantManager', N'lamp/activiti/reimbursement/instantManager/index', N'1', N'1', N'', N'', NULL, NULL, NULL, N'155', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'157', N'报销任务', N'20', N'2', N',50,155,', N'', N'0', N'/activiti/reimbursement/ruTask', N'lamp/activiti/reimbursement/ruTask/index', N'1', N'2', N'', N'', NULL, NULL, NULL, N'155', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'160', N'菜单管理', N'20', N'1', N',60,', N'', N'0', N'/system/menu', N'lamp/system/menu/index', N'1', N'10', N'', N'', NULL, NULL, NULL, N'60', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'161', N'角色管理', N'20', N'1', N',60,', N'', N'0', N'/system/role', N'lamp/system/role/index', N'1', N'20', N'', N'', NULL, NULL, NULL, N'60', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'162', N'字典管理', N'20', N'1', N',60,', N'', N'0', N'/system/dictionary', N'lamp/system/dictionary/index', N'1', N'30', N'', N'', NULL, NULL, NULL, N'60', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'163', N'地区管理', N'20', N'1', N',60,', N'', N'0', N'/system/area', N'lamp/system/area/index', N'1', N'40', N'', N'', NULL, NULL, NULL, N'60', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'164', N'参数管理', N'20', N'1', N',60,', N'', N'0', N'/system/parameter', N'lamp/system/parameter/index', N'1', N'50', N'', N'', NULL, NULL, NULL, N'60', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'165', N'操作日志', N'20', N'1', N',60,', N'', N'0', N'/system/optLog', N'lamp/system/optLog/index', N'1', N'60', N'', N'', NULL, NULL, NULL, N'60', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'166', N'登录日志', N'20', N'1', N',60,', N'', N'0', N'/system/loginLog', N'lamp/system/loginLog/index', N'1', N'70', N'', N'', NULL, NULL, NULL, N'60', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'167', N'在线用户', N'20', N'1', N',60,', N'', N'0', N'/system/online', N'lamp/system/online/index', N'1', N'80', N'', N'', NULL, NULL, NULL, N'60', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'168', N'应用管理', N'20', N'1', N',60,', N'', N'0', N'/system/application', N'lamp/system/application/index', N'1', N'90', N'', N'', NULL, NULL, NULL, N'60', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'180', N'限流规则', N'20', N'1', N',70,', N'', N'0', N'/gateway/ratelimiter', N'lamp/gateway/ratelimiter/index', N'1', N'10', N'', N'', NULL, NULL, NULL, N'70', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'181', N'阻止访问', N'20', N'1', N',70,', N'', N'0', N'/gateway/blocklist', N'lamp/gateway/blocklist/index', N'1', N'20', N'', N'', NULL, NULL, NULL, N'70', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'190', N'定时任务', N'20', N'1', N',80,', N'', N'0', N'http://127.0.0.1:8767/xxl-job-admin', N'Layout', N'1', N'10', N'', N'', NULL, NULL, NULL, N'80', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'191', N'接口文档', N'20', N'1', N',80,', N'', N'0', N'http://127.0.0.1:8760/api/gate/doc.html', N'Layout', N'1', N'20', N'', N'', NULL, NULL, NULL, N'80', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'192', N'nacos', N'20', N'1', N',80,', N'', N'0', N'http://127.0.0.1:8848/nacos', N'Layout', N'1', N'30', N'', N'', NULL, NULL, NULL, N'80', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'193', N'服务监控', N'20', N'1', N',80,', N'', N'0', N'http://127.0.0.1:8762/lamp-monitor', N'Layout', N'1', N'40', N'', N'', NULL, NULL, NULL, N'80', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'194', N'数据库监控', N'20', N'1', N',80,', N'', N'0', N'/developer/db', N'lamp/developer/db/index', N'1', N'50', N'', N'', NULL, NULL, NULL, N'80', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'195', N'缓存监控', N'20', N'1', N',80,', N'', N'0', N'https://github.com/junegunn/redis-stat', N'Layout', N'1', N'60', N'', N'', NULL, NULL, NULL, N'80', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'196', N'zipkin监控', N'20', N'1', N',80,', N'', N'0', N'http://127.0.0.1:8772/zipkin', N'Layout', N'1', N'70', N'', N'', NULL, NULL, NULL, N'80', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'197', N'SkyWalking监控', N'20', N'1', N',80,', N'', N'0', N'http://127.0.0.1:12080', N'Layout', N'1', N'80', N'', N'', NULL, NULL, NULL, N'80', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'210', N'在线文档', N'20', N'1', N',90,', N'', N'1', N'https://www.kancloud.cn/zuihou/zuihou-admin-cloud', N'Layout', N'1', N'10', N'', N'', NULL, NULL, NULL, N'90', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'211', N'会员版', N'20', N'1', N',90,', N'', N'1', N'https://www.kancloud.cn/zuihou/zuihou-admin-cloud/2003629', N'Layout', N'1', N'20', N'', N'', NULL, NULL, NULL, N'90', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'212', N'获取源码', N'20', N'1', N',90,', N'', N'1', N'https://github.com/zuihou', N'Layout', N'1', N'30', N'', N'', NULL, NULL, NULL, N'90', N'1', N'1', N'2020-11-23 11:47:31.000', N'2', N'2020-12-01 11:34:20.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'213', N'问题反馈', N'20', N'1', N',90,', N'', N'1', N'https://github.com/dromara/lamp-cloud/issues', N'Layout', N'1', N'40', N'', N'', NULL, NULL, NULL, N'90', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'214', N'更新日志', N'20', N'1', N',90,', N'', N'1', N'https://www.kancloud.cn/zuihou/zuihou-admin-cloud/1465302', N'Layout', N'1', N'50', N'', N'', NULL, NULL, NULL, N'90', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'215', N'蓝图', N'20', N'1', N',90,', N'', N'1', N'https://www.kancloud.cn/zuihou/zuihou-admin-cloud/2003640', N'Layout', N'1', N'60', N'', N'', NULL, NULL, NULL, N'90', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'220', N'多级菜单', N'20', N'1', N',100,', N'', N'1', N'/more/multiMenu', N'Layout', N'1', N'1', N'', N'', NULL, NULL, NULL, N'100', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'221', N'菜单1-1', N'20', N'2', N',100,220,', N'', N'1', N'/more/multiMenu/menu1-1', N'Layout', N'1', N'1', N'', N'', NULL, NULL, NULL, N'220', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'222', N'菜单1-1-1', N'20', N'3', N',100,220,221,', N'', N'1', N'/more/multiMenu/menu1-1/menu1-1-1', N'lamp/more/multiMenu/menu1-1/menu1-1-1/index', N'1', N'1', N'', N'', NULL, NULL, NULL, N'221', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'223', N'菜单1-1-2', N'20', N'3', N',100,220,221,', N'', N'1', N'/more/multiMenu/menu1-1/menu1-1-2', N'lamp/more/multiMenu/menu1-1/menu1-1-2/index', N'1', N'2', N'', N'', NULL, NULL, NULL, N'221', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'224', N'菜单1-2', N'20', N'2', N',100,220,', N'', N'1', N'/more/multiMenu/menu1-2', N'lamp/more/multiMenu/menu1-2/index', N'1', N'2', N'', N'', NULL, NULL, NULL, N'220', N'1', N'1', N'2020-11-23 11:47:31.000', N'1', N'2020-11-23 11:47:31.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'1486201050779090944', N'查看全部', N'60', N'2', N',30,132,', N'', N'0', NULL, NULL, N'1', N'1', N'', N'', N'01', NULL, N'0', N'132', N'1', N'2', N'2022-01-26 12:55:17.000', N'2', N'2022-01-26 12:55:17.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'1486201491877265408', N'查看本单位及子单位', N'60', N'2', N',30,132,', N'', N'0', NULL, N'', N'1', N'2', N'', N'', N'02', NULL, N'0', N'132', N'1', N'2', N'2022-01-26 12:57:03.000', N'2', N'2022-01-26 12:57:03.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'1486201562119274496', N'查看本单位', N'60', N'2', N',30,132,', N'', N'0', NULL, N'', N'1', N'3', N'', N'', N'06', NULL, N'0', N'132', N'1', N'2', N'2022-01-26 12:57:19.000', N'2', N'2022-01-26 12:57:19.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'1486201715840516096', N'查看本部门及子部门', N'60', N'2', N',30,132,', N'', N'0', NULL, N'', N'1', N'4', N'', N'', N'04', NULL, N'0', N'132', N'1', N'2', N'2022-01-26 12:57:56.000', N'2', N'2022-01-26 12:57:56.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'1486201804327747584', N'查看本部门', N'60', N'2', N',30,132,', N'', N'0', NULL, N'', N'1', N'5', N'', N'', N'05', NULL, N'0', N'132', N'1', N'2', N'2022-01-26 12:58:17.000', N'2', N'2022-01-26 12:58:17.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'1486201872350969856', N'查看个人', N'60', N'2', N',30,132,', N'', N'0', NULL, N'', N'1', N'6', N'', N'', N'06', NULL, N'1', N'132', N'1', N'2', N'2022-01-26 12:58:33.000', N'2', N'2022-01-26 12:58:33.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'1486201971705643008', N'查看自定义数据', N'60', N'2', N',30,132,', N'', N'0', NULL, N'', N'1', N'7', N'', N'', N'07', N'DATA_SCOPE_MY_TEST', N'0', N'132', N'1', N'2', N'2022-01-26 12:58:57.000', N'2', N'2022-01-26 12:59:07.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'1486619304207056896', N'查看全部', N'60', N'2', N',30,131,', N'', N'0', NULL, N'', N'1', N'1', N'', N'', N'01', NULL, N'0', N'131', N'1', N'2', N'2022-01-27 16:37:17.000', N'2', N'2022-01-27 16:37:17.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'1486619392048365568', N'查看本单位及子单位', N'60', N'2', N',30,131,', N'', N'0', NULL, N'', N'1', N'2', N'', N'', N'02', NULL, N'0', N'131', N'1', N'2', N'2022-01-27 16:37:38.000', N'2', N'2022-01-27 16:37:38.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'1486620313587286016', N'查看本单位', N'60', N'2', N',30,131,', N'', N'0', NULL, N'', N'1', N'3', N'', N'', N'03', NULL, N'0', N'131', N'1', N'2', N'2022-01-27 16:41:17.000', N'2', N'2022-01-27 16:41:17.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'1486620425789112320', N'查看本部门及子部门', N'60', N'2', N',30,131,', N'', N'0', NULL, N'', N'1', N'4', N'', N'', N'04', NULL, N'0', N'131', N'1', N'2', N'2022-01-27 16:41:44.000', N'2', N'2022-01-27 16:41:44.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'1486620477806870528', N'查看本部门', N'60', N'2', N',30,131,', N'', N'0', NULL, N'', N'1', N'5', N'', N'', N'05', NULL, N'0', N'131', N'1', N'2', N'2022-01-27 16:41:57.000', N'2', N'2022-01-27 16:41:57.000')
GO

INSERT INTO [dbo].[c_menu]  VALUES (N'1486620546081751040', N'查看个人', N'60', N'2', N',30,131,', N'', N'0', NULL, N'', N'1', N'6', N'', N'', N'06', NULL, N'1', N'131', N'1', N'2', N'2022-01-27 16:42:13.000', N'2', N'2022-01-27 16:42:13.000')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for c_opt_log
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_opt_log]') AND type IN ('U'))
	DROP TABLE [dbo].[c_opt_log]
GO

CREATE TABLE [dbo].[c_opt_log] (
  [id] bigint  NOT NULL,
  [request_ip] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [type] varchar(5) COLLATE Chinese_PRC_CI_AS  NULL,
  [user_name] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [description] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [class_path] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [action_method] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [request_uri] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [http_method] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [start_time] datetime  NULL,
  [finish_time] datetime  NULL,
  [consuming_time] bigint  NULL,
  [ua] varchar(500) COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[c_opt_log] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'操作IP',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'request_ip'
GO

EXEC sp_addextendedproperty
'MS_Description', N'日志类型;
#LogType{OPT:操作类型;EX:异常类型}',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'操作人',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'user_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'操作描述',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类路径',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'class_path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求方法',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'action_method'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求地址',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'request_uri'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求类型;
#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'http_method'
GO

EXEC sp_addextendedproperty
'MS_Description', N'开始时间',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'start_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'完成时间',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'finish_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消耗时间',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'consuming_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'浏览器',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'ua'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'系统日志',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log'
GO


-- ----------------------------
-- Table structure for c_opt_log_ext
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_opt_log_ext]') AND type IN ('U'))
	DROP TABLE [dbo].[c_opt_log_ext]
GO

CREATE TABLE [dbo].[c_opt_log_ext] (
  [id] bigint  NOT NULL,
  [params] text COLLATE Chinese_PRC_CI_AS  NULL,
  [result] text COLLATE Chinese_PRC_CI_AS  NULL,
  [ex_detail] text COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[c_opt_log_ext] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log_ext',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求参数',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log_ext',
'COLUMN', N'params'
GO

EXEC sp_addextendedproperty
'MS_Description', N'返回值',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log_ext',
'COLUMN', N'result'
GO

EXEC sp_addextendedproperty
'MS_Description', N'异常描述',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log_ext',
'COLUMN', N'ex_detail'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log_ext',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log_ext',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'系统日志扩展',
'SCHEMA', N'dbo',
'TABLE', N'c_opt_log_ext'
GO


-- ----------------------------
-- Table structure for c_org
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_org]') AND type IN ('U'))
	DROP TABLE [dbo].[c_org]
GO

CREATE TABLE [dbo].[c_org] (
  [id] bigint  NOT NULL,
  [label] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [type_] char(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [abbreviation] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [parent_id] bigint DEFAULT ((0)) NULL,
  [tree_path] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [sort_value] int DEFAULT ((1)) NULL,
  [state] bit DEFAULT ((1)) NULL,
  [describe_] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL,
  [update_time] datetime  NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[c_org] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_org',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'c_org',
'COLUMN', N'label'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类型;
@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.ORG_TYPE)',
'SCHEMA', N'dbo',
'TABLE', N'c_org',
'COLUMN', N'type_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'简称',
'SCHEMA', N'dbo',
'TABLE', N'c_org',
'COLUMN', N'abbreviation'
GO

EXEC sp_addextendedproperty
'MS_Description', N'父ID',
'SCHEMA', N'dbo',
'TABLE', N'c_org',
'COLUMN', N'parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'树结构',
'SCHEMA', N'dbo',
'TABLE', N'c_org',
'COLUMN', N'tree_path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'c_org',
'COLUMN', N'sort_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'c_org',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'c_org',
'COLUMN', N'describe_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_org',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'c_org',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'c_org',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'c_org',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'组织',
'SCHEMA', N'dbo',
'TABLE', N'c_org'
GO


-- ----------------------------
-- Records of c_org
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[c_org]  VALUES (N'1', N'1', N'1 ', N'1', N'0', NULL, N'1', N'1', NULL, NULL, NULL, NULL, NULL)
GO

COMMIT
GO


-- ----------------------------
-- Table structure for c_parameter
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_parameter]') AND type IN ('U'))
	DROP TABLE [dbo].[c_parameter]
GO

CREATE TABLE [dbo].[c_parameter] (
  [id] bigint  NOT NULL,
  [key_] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [value] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [describe_] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [state] bit DEFAULT ((1)) NULL,
  [readonly_] bit DEFAULT ((0)) NULL,
  [created_by] bigint  NULL,
  [create_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [update_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[c_parameter] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_parameter',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数键',
'SCHEMA', N'dbo',
'TABLE', N'c_parameter',
'COLUMN', N'key_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数值',
'SCHEMA', N'dbo',
'TABLE', N'c_parameter',
'COLUMN', N'value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数名称',
'SCHEMA', N'dbo',
'TABLE', N'c_parameter',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'c_parameter',
'COLUMN', N'describe_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'c_parameter',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'内置',
'SCHEMA', N'dbo',
'TABLE', N'c_parameter',
'COLUMN', N'readonly_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'c_parameter',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_parameter',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人id',
'SCHEMA', N'dbo',
'TABLE', N'c_parameter',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'c_parameter',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数配置',
'SCHEMA', N'dbo',
'TABLE', N'c_parameter'
GO


-- ----------------------------
-- Records of c_parameter
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[c_parameter]  VALUES (N'1', N'LoginPolicy', N'登录策略', N'MANY', N'ONLY_ONE:一个用户只能登录一次; MANY:用户可以任意登录; ONLY_ONE_CLIENT:一个用户在一个应用只能登录一次', N'1', N'1', N'1', N'2020-04-02 21:56:19.000', N'1', N'2020-04-03 01:12:32.000')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for c_resource
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_resource]') AND type IN ('U'))
	DROP TABLE [dbo].[c_resource]
GO

CREATE TABLE [dbo].[c_resource] (
  [id] bigint  NOT NULL,
  [code] varchar(500) COLLATE Chinese_PRC_CI_AS  NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [menu_id] bigint  NULL,
  [describe_] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [readonly_] bit DEFAULT ((1)) NULL,
  [created_by] bigint  NULL,
  [create_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [update_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[c_resource] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_resource',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编码',
'SCHEMA', N'dbo',
'TABLE', N'c_resource',
'COLUMN', N'code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'c_resource',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'菜单;#c_menu',
'SCHEMA', N'dbo',
'TABLE', N'c_resource',
'COLUMN', N'menu_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'c_resource',
'COLUMN', N'describe_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'内置',
'SCHEMA', N'dbo',
'TABLE', N'c_resource',
'COLUMN', N'readonly_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'c_resource',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_resource',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人id',
'SCHEMA', N'dbo',
'TABLE', N'c_resource',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'c_resource',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'资源',
'SCHEMA', N'dbo',
'TABLE', N'c_resource'
GO


-- ----------------------------
-- Records of c_resource
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'1', N'authority:menu:add', N'新增', N'160', N'', N'1', N'1', N'2020-11-23 21:12:57.000', N'1', N'2020-11-23 21:12:57.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'2', N'authority:menu:edit', N'编辑', N'160', N'', N'1', N'1', N'2020-11-23 21:12:57.000', N'1', N'2020-11-23 21:12:57.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'3', N'authority:menu:delete', N'删除', N'160', N'', N'1', N'1', N'2020-11-23 21:12:57.000', N'1', N'2020-11-23 21:12:57.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'4', N'authority:menu:view;authority:resource:view', N'查看', N'160', N'', N'1', N'1', N'2020-11-23 21:12:57.000', N'1', N'2020-11-23 21:12:57.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'5', N'authority:menu:import', N'导入', N'160', N'', N'1', N'1', N'2020-11-23 21:12:57.000', N'1', N'2020-11-23 21:12:57.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'6', N'authority:menu:export', N'导出', N'160', N'', N'1', N'1', N'2020-11-23 21:12:57.000', N'1', N'2020-11-23 21:12:57.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'7', N'authority:resource:add', N'新增', N'160', N'', N'1', N'1', N'2020-11-23 21:12:57.000', N'1', N'2020-11-23 21:12:57.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'8', N'authority:resource:edit', N'编辑', N'160', N'', N'1', N'1', N'2020-11-23 21:12:57.000', N'1', N'2020-11-23 21:12:57.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'9', N'authority:resource:delete', N'删除', N'160', N'', N'1', N'1', N'2020-11-23 21:12:57.000', N'1', N'2020-11-23 21:12:57.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872491', N'authority:org:add', N'新增', N'130', N'', N'1', N'1', N'2020-11-23 22:02:52.000', N'1', N'2020-11-23 22:02:52.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872496', N'authority:org:edit', N'编辑', N'130', N'', N'1', N'1', N'2020-11-23 22:03:13.000', N'1', N'2020-11-23 22:03:13.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872501', N'authority:org:delete', N'删除', N'130', N'', N'1', N'1', N'2020-11-23 22:03:33.000', N'1', N'2020-11-23 22:03:33.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872506', N'authority:org:view', N'查看', N'130', N'', N'1', N'1', N'2020-11-23 22:03:47.000', N'1', N'2020-11-23 22:03:47.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872513', N'authority:station:add', N'新增', N'131', N'', N'1', N'1', N'2020-11-23 22:04:08.000', N'1', N'2020-11-23 22:04:08.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872518', N'authority:station:edit', N'编辑', N'131', N'', N'1', N'1', N'2020-11-23 22:04:17.000', N'1', N'2020-11-23 22:04:17.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872523', N'authority:station:delete', N'删除', N'131', N'', N'1', N'1', N'2020-11-23 22:04:28.000', N'1', N'2020-11-23 22:04:28.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872528', N'authority:station:view;authority:org:view', N'查看', N'131', N'', N'1', N'1', N'2020-11-23 22:04:41.000', N'1', N'2020-11-23 22:04:41.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872537', N'authority:user:add', N'新增', N'132', N'', N'1', N'1', N'2020-11-23 22:05:24.000', N'1', N'2020-11-23 22:05:24.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872542', N'authority:user:edit', N'编辑', N'132', N'', N'1', N'1', N'2020-11-23 22:05:33.000', N'1', N'2020-11-23 22:05:33.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872547', N'authority:user:delete', N'删除', N'132', N'', N'1', N'1', N'2020-11-23 22:05:41.000', N'1', N'2020-11-23 22:05:41.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872552', N'authority:user:view;authority:station:view;authority:org:view', N'查看', N'132', N'', N'1', N'1', N'2020-11-23 22:05:47.000', N'1', N'2020-11-23 22:05:47.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872557', N'authority:user:resetPassword', N'重置密码', N'132', N'', N'1', N'1', N'2020-11-23 22:06:09.000', N'1', N'2020-11-23 22:06:09.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872562', N'authority:user:updateState', N'修改状态', N'132', N'', N'1', N'1', N'2020-11-23 22:06:42.000', N'1', N'2020-11-23 22:06:42.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872569', N'msg:msg:add;authority:user:view;authority:role:view', N'新增', N'140', N'', N'1', N'1', N'2020-11-23 22:08:07.000', N'1', N'2020-11-23 22:08:07.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872574', N'msg:msg:edit;authority:user:view;authority:role:view', N'编辑', N'140', N'', N'1', N'1', N'2020-11-23 22:08:23.000', N'1', N'2020-11-23 22:08:23.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872579', N'msg:msg:delete', N'删除', N'140', N'', N'1', N'1', N'2020-11-23 22:08:32.000', N'1', N'2020-11-23 22:08:32.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872584', N'msg:msg:view', N'查看', N'140', N'', N'1', N'1', N'2020-11-23 22:08:38.000', N'1', N'2020-11-23 22:08:38.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872589', N'msg:msg:mark', N'标记已读', N'140', N'', N'1', N'1', N'2020-11-23 22:10:36.000', N'1', N'2020-11-23 22:10:36.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872596', N'authority:org:import', N'导入', N'130', N'', N'1', N'1', N'2020-11-23 22:11:11.000', N'1', N'2020-11-23 22:11:11.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872601', N'authority:org:export', N'导出', N'130', N'', N'1', N'1', N'2020-11-23 22:11:21.000', N'1', N'2020-11-23 22:11:21.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872610', N'authority:station:import', N'导入', N'131', N'', N'1', N'1', N'2020-11-23 22:11:41.000', N'1', N'2020-11-23 22:11:41.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872615', N'authority:station:export', N'导出', N'131', N'', N'1', N'1', N'2020-11-23 22:11:50.000', N'1', N'2020-11-23 22:11:50.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872622', N'authority:user:import', N'导入', N'132', N'', N'1', N'1', N'2020-11-23 22:12:16.000', N'1', N'2020-11-23 22:12:16.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872627', N'authority:user:export', N'导出', N'132', N'', N'1', N'1', N'2020-11-23 22:12:24.000', N'1', N'2020-11-23 22:12:24.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872646', N'msg:msg:export', N'导出', N'140', N'', N'1', N'1', N'2020-11-23 22:15:21.000', N'1', N'2020-11-23 22:15:21.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872657', N'msg:sms:add', N'新增', N'142', N'', N'1', N'1', N'2020-11-23 22:16:55.000', N'1', N'2020-11-23 22:16:55.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872662', N'msg:sms:edit', N'编辑', N'142', N'', N'1', N'1', N'2020-11-23 22:17:03.000', N'1', N'2020-11-23 22:17:03.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872667', N'msg:sms:delete', N'删除', N'142', N'', N'1', N'1', N'2020-11-23 22:17:15.000', N'1', N'2020-11-23 22:17:15.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872672', N'msg:sms:view;msg:smsTemplate:view', N'查看', N'142', N'', N'1', N'1', N'2020-11-23 22:17:24.000', N'1', N'2020-11-23 22:17:24.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872679', N'msg:smsTemplate:add', N'新增', N'141', N'', N'1', N'1', N'2020-11-23 22:18:00.000', N'1', N'2020-11-23 22:18:00.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872684', N'msg:smsTemplate:edit', N'编辑', N'141', N'', N'1', N'1', N'2020-11-23 22:18:07.000', N'1', N'2020-11-23 22:18:07.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872689', N'msg:smsTemplate:delete', N'删除', N'141', N'', N'1', N'1', N'2020-11-23 22:18:13.000', N'1', N'2020-11-23 22:18:13.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872694', N'msg:smsTemplate:view', N'查看', N'141', N'', N'1', N'1', N'2020-11-23 22:18:20.000', N'1', N'2020-11-23 22:18:20.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872703', N'file:attachment:add', N'新增', N'143', N'', N'1', N'1', N'2020-11-23 22:19:58.000', N'1', N'2020-11-23 22:19:58.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872708', N'file:attachment:edit', N'编辑', N'143', N'', N'1', N'1', N'2020-11-23 22:20:07.000', N'1', N'2020-11-23 22:20:07.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872713', N'file:attachment:delete', N'删除', N'143', N'', N'1', N'1', N'2020-11-23 22:20:13.000', N'1', N'2020-11-23 22:20:13.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872718', N'file:attachment:view', N'查看', N'143', N'', N'1', N'1', N'2020-11-23 22:20:19.000', N'1', N'2020-11-23 22:20:19.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872723', N'file:attachment:download', N'下载', N'143', N'', N'1', N'1', N'2020-11-23 22:20:30.000', N'1', N'2020-11-23 22:20:30.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872736', N'authority:role:add;authority:org:view', N'新增', N'161', N'', N'1', N'1', N'2020-11-23 22:23:25.000', N'1', N'2020-11-23 22:23:25.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872741', N'authority:role:edit;authority:org:view', N'编辑', N'161', N'', N'1', N'1', N'2020-11-23 22:23:33.000', N'1', N'2020-11-23 22:23:33.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872746', N'authority:role:delete', N'删除', N'161', N'', N'1', N'1', N'2020-11-23 22:23:41.000', N'1', N'2020-11-23 22:23:41.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872751', N'authority:role:view', N'查看', N'161', N'', N'1', N'1', N'2020-11-23 22:24:50.000', N'1', N'2020-11-23 22:24:50.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872756', N'authority:role:config;authority:user:view', N'配置权限', N'161', N'', N'1', N'1', N'2020-11-23 22:25:26.000', N'1', N'2020-11-23 22:25:26.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872761', N'authority:role:auth;authority:menu:view;authority:resource:view', N'授权', N'161', N'', N'1', N'1', N'2020-11-23 22:25:39.000', N'1', N'2020-11-23 22:25:39.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872768', N'authority:dictionary:add', N'新增', N'162', N'', N'1', N'1', N'2020-11-23 22:27:08.000', N'1', N'2020-11-23 22:27:08.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872773', N'authority:dictionary:edit', N'编辑', N'162', N'', N'1', N'1', N'2020-11-23 22:27:15.000', N'1', N'2020-11-23 22:27:15.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872778', N'authority:dictionary:delete', N'删除', N'162', N'', N'1', N'1', N'2020-11-23 22:27:22.000', N'1', N'2020-11-23 22:27:22.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872783', N'authority:dictionary:view', N'查看', N'162', N'', N'1', N'1', N'2020-11-23 22:27:28.000', N'1', N'2020-11-23 22:27:28.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872790', N'authority:area:add', N'新增', N'163', N'', N'1', N'1', N'2020-11-23 22:27:56.000', N'1', N'2020-11-23 22:27:56.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872795', N'authority:area:edit', N'编辑', N'163', N'', N'1', N'1', N'2020-11-23 22:28:04.000', N'1', N'2020-11-23 22:28:04.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872800', N'authority:area:delete', N'删除', N'163', N'', N'1', N'1', N'2020-11-23 22:28:11.000', N'1', N'2020-11-23 22:28:11.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872805', N'authority:area:view', N'查看', N'163', N'', N'1', N'1', N'2020-11-23 22:28:25.000', N'1', N'2020-11-23 22:28:25.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872812', N'authority:parameter:add', N'新增', N'164', N'', N'1', N'1', N'2020-11-23 22:29:01.000', N'1', N'2020-11-23 22:29:01.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872817', N'authority:parameter:edit', N'编辑', N'164', N'', N'1', N'1', N'2020-11-23 22:29:08.000', N'1', N'2020-11-23 22:29:08.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872822', N'authority:parameter:delete', N'删除', N'164', N'', N'1', N'1', N'2020-11-23 22:29:18.000', N'1', N'2020-11-23 22:29:18.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872827', N'authority:parameter:view', N'查看', N'164', N'', N'1', N'1', N'2020-11-23 22:29:26.000', N'1', N'2020-11-23 22:29:26.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872838', N'authority:optLog:delete', N'删除', N'165', N'', N'1', N'1', N'2020-11-23 22:30:00.000', N'1', N'2020-11-23 22:30:00.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872843', N'authority:optLog:view', N'查看', N'165', N'', N'1', N'1', N'2020-11-23 22:30:08.000', N'1', N'2020-11-23 22:30:08.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872850', N'authority:loginLog:delete', N'删除', N'166', N'', N'1', N'1', N'2020-11-23 22:30:27.000', N'1', N'2020-11-23 22:30:27.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872857', N'authority:loginLog:view', N'查看', N'166', N'', N'1', N'1', N'2020-11-23 22:30:43.000', N'1', N'2020-11-23 22:30:43.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872864', N'authority:online:view', N'查看', N'167', N'', N'1', N'1', N'2020-11-23 22:31:11.000', N'1', N'2020-11-23 22:31:11.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872869', N'authority:online:delete', N'删除', N'167', N'', N'1', N'1', N'2020-11-23 22:31:19.000', N'1', N'2020-11-23 22:31:19.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872876', N'authority:application:add', N'新增', N'168', N'', N'1', N'1', N'2020-11-23 22:31:37.000', N'1', N'2020-11-23 22:31:37.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872881', N'authority:application:edit', N'编辑', N'168', N'', N'1', N'1', N'2020-11-23 22:31:43.000', N'1', N'2020-11-23 22:31:43.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872886', N'authority:application:delete', N'删除', N'168', N'', N'1', N'1', N'2020-11-23 22:31:49.000', N'1', N'2020-11-23 22:31:49.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'25938183673872891', N'authority:application:view', N'查看', N'168', N'', N'1', N'1', N'2020-11-23 22:31:55.000', N'1', N'2020-11-23 22:31:55.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'26578833880645891', N'tenant:datasourceConfig:add', N'新增', N'112', N'', N'1', N'2', N'2020-11-25 17:05:43.000', N'2', N'2020-11-25 17:05:43.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'26578833880645896', N'tenant:datasourceConfig:edit', N'编辑', N'112', N'', N'1', N'2', N'2020-11-25 17:06:41.000', N'2', N'2020-11-25 17:06:41.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'26578833880645901', N'tenant:datasourceConfig:delete', N'删除', N'112', N'', N'1', N'2', N'2020-11-25 17:12:41.000', N'2', N'2020-11-25 17:12:41.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'26578833880645906', N'tenant:datasourceConfig:view', N'查看', N'112', N'', N'1', N'2', N'2020-11-25 17:12:56.000', N'2', N'2020-11-25 17:12:56.000')
GO

INSERT INTO [dbo].[c_resource]  VALUES (N'1400107570902859776', N'authority:resource:view', N'查看', N'160', N'', N'1', N'1', N'2020-11-23 21:12:57.000', N'1', N'2020-11-23 21:12:57.000')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for c_role
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_role]') AND type IN ('U'))
	DROP TABLE [dbo].[c_role]
GO

CREATE TABLE [dbo].[c_role] (
  [id] bigint  NOT NULL,
  [category] char(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [name] varchar(30) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [code] varchar(20) COLLATE Chinese_PRC_CI_AS  NULL,
  [describe_] varchar(100) COLLATE Chinese_PRC_CI_AS  NULL,
  [state] bit DEFAULT ((1)) NULL,
  [readonly_] bit DEFAULT ((0)) NULL,
  [created_by] bigint  NULL,
  [create_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [update_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[c_role] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_role',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色类别;[10-功能角色 20-桌面角色 30-数据角色]',
'SCHEMA', N'dbo',
'TABLE', N'c_role',
'COLUMN', N'category'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'c_role',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编码',
'SCHEMA', N'dbo',
'TABLE', N'c_role',
'COLUMN', N'code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'c_role',
'COLUMN', N'describe_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'c_role',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'内置角色',
'SCHEMA', N'dbo',
'TABLE', N'c_role',
'COLUMN', N'readonly_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'c_role',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_role',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人id',
'SCHEMA', N'dbo',
'TABLE', N'c_role',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'c_role',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色',
'SCHEMA', N'dbo',
'TABLE', N'c_role'
GO


-- ----------------------------
-- Records of c_role
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[c_role]  VALUES (N'1', N'10', N'超级管理员', N'SUPER_ADMIN', N'内置管理员(二次开发必须保留)', N'1', N'1', N'1', N'2020-11-22 23:46:00.000', N'1', N'2020-11-22 23:46:00.000')
GO

INSERT INTO [dbo].[c_role]  VALUES (N'2', N'10', N'平台管理员', N'PT_ADMIN', N'内置运营专用平台管理员(二次开发必须保留)', N'1', N'1', N'1', N'2020-11-22 23:46:00.000', N'1', N'2020-11-22 23:46:00.000')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for c_role_authority
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_role_authority]') AND type IN ('U'))
	DROP TABLE [dbo].[c_role_authority]
GO

CREATE TABLE [dbo].[c_role_authority] (
  [id] bigint  NOT NULL,
  [authority_id] bigint  NOT NULL,
  [authority_type] varchar(10) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [role_id] bigint  NOT NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[c_role_authority] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'c_role_authority',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'资源id;
#c_resource #c_menu',
'SCHEMA', N'dbo',
'TABLE', N'c_role_authority',
'COLUMN', N'authority_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'权限类型;
#AuthorizeType{MENU:菜单;RESOURCE:资源;}',
'SCHEMA', N'dbo',
'TABLE', N'c_role_authority',
'COLUMN', N'authority_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色id;
#c_role',
'SCHEMA', N'dbo',
'TABLE', N'c_role_authority',
'COLUMN', N'role_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_role_authority',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'c_role_authority',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色的资源',
'SCHEMA', N'dbo',
'TABLE', N'c_role_authority'
GO


-- ----------------------------
-- Records of c_role_authority
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645921', N'1', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645922', N'25938183673872761', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645923', N'25938183673872506', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645924', N'2', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645925', N'26578833880645906', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645926', N'3', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645927', N'25938183673872891', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645928', N'4', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645929', N'5', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645930', N'6', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645931', N'7', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645932', N'25938183673872496', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645933', N'8', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645934', N'9', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645935', N'25938183673872881', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645936', N'10', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645937', N'25938183673872627', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645938', N'25938183673872756', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645939', N'25938183673872501', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645940', N'25938183673872886', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645941', N'26578833880645891', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645942', N'25938183673872746', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645943', N'25938183673872491', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645944', N'25938183673872876', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645945', N'25938183673872622', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645946', N'25938183673872751', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645947', N'25938183673872736', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645948', N'25938183673872864', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645949', N'26578833880645896', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645950', N'25938183673872610', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645951', N'26578833880645901', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645952', N'25938183673872484', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645953', N'25938183673872741', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645954', N'25938183673872869', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645955', N'25938183673872615', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645956', N'25938183673872472', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645957', N'25938183673872601', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645958', N'25938183673872857', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645959', N'25938183673872477', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645960', N'25938183673872850', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645961', N'25938183673872467', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645962', N'25938183673872723', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645963', N'25938183673872596', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645964', N'25938183673872584', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645965', N'25938183673872713', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645966', N'25938183673872843', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645967', N'25938183673872460', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645968', N'25938183673872589', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645969', N'25938183673872718', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645970', N'25938183673872450', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645971', N'25938183673872579', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645972', N'25938183673872708', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645973', N'25938183673872838', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645974', N'25938183673872455', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645975', N'25938183673872440', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645976', N'25938183673872569', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645977', N'25938183673872827', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645978', N'25938183673872445', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645979', N'25938183673872574', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645980', N'25938183673872703', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645981', N'25938183673872689', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645982', N'25938183673872817', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645983', N'25938183673872562', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645984', N'25938183673872694', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645985', N'25938183673872822', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645986', N'25938183673872552', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645987', N'25938183673872684', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645988', N'25938183673872812', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645989', N'25938183673872557', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645990', N'25938183673872672', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645991', N'25938183673872800', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645992', N'25938183673872547', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645993', N'25938183673872805', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645994', N'25938183673872679', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645995', N'25938183673872537', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645996', N'25938183673872667', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645997', N'25938183673872795', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645998', N'25938183673872542', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880645999', N'25938183673872528', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646000', N'25938183673872657', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646001', N'25938183673872662', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646002', N'25938183673872790', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646003', N'25938183673872778', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646004', N'25938183673872523', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646005', N'25938183673872783', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646006', N'25938183673872768', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646007', N'25938183673872513', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646008', N'25938183673872773', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646009', N'25938183673872518', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646010', N'25938183673872646', N'RESOURCE', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646011', N'130', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646012', N'131', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646013', N'132', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646014', N'10', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646015', N'140', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646016', N'141', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646017', N'142', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646018', N'143', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646019', N'20', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646020', N'150', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646021', N'151', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646022', N'152', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646023', N'153', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646024', N'154', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646025', N'155', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646026', N'156', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646027', N'157', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646028', N'30', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646029', N'160', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646030', N'161', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646031', N'162', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646032', N'163', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646033', N'164', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646034', N'165', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646035', N'166', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646036', N'167', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646037', N'40', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646038', N'168', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646039', N'50', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646040', N'180', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646041', N'181', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646042', N'60', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646043', N'190', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646044', N'191', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646045', N'192', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646046', N'193', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646047', N'194', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646048', N'195', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646049', N'196', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646050', N'197', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646051', N'70', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646052', N'80', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646053', N'210', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646054', N'211', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646055', N'212', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646056', N'213', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646057', N'214', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646058', N'215', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646059', N'90', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646060', N'220', N'MENU', N'2', N'2020-11-25 17:13:10.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646061', N'221', N'MENU', N'2', N'2020-11-25 17:13:11.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646062', N'222', N'MENU', N'2', N'2020-11-25 17:13:11.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646063', N'223', N'MENU', N'2', N'2020-11-25 17:13:11.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646064', N'224', N'MENU', N'2', N'2020-11-25 17:13:11.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646065', N'100', N'MENU', N'2', N'2020-11-25 17:13:11.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646066', N'110', N'MENU', N'2', N'2020-11-25 17:13:11.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646067', N'111', N'MENU', N'2', N'2020-11-25 17:13:11.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646068', N'112', N'MENU', N'2', N'2020-11-25 17:13:11.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646069', N'120', N'MENU', N'2', N'2020-11-25 17:13:11.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646070', N'121', N'MENU', N'2', N'2020-11-25 17:13:11.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646071', N'122', N'MENU', N'2', N'2020-11-25 17:13:11.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'26578833880646072', N'123', N'MENU', N'2', N'2020-11-25 17:13:11.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633784', N'25938183673872761', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633785', N'1', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633786', N'2', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633787', N'25938183673872506', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633788', N'25938183673872891', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633789', N'3', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633790', N'4', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633791', N'5', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633792', N'6', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633793', N'7', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633794', N'8', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633795', N'25938183673872496', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633796', N'25938183673872881', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633797', N'9', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633799', N'25938183673872627', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633800', N'25938183673872756', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633801', N'25938183673872501', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633802', N'25938183673872886', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633803', N'25938183673872746', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633804', N'25938183673872491', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633805', N'25938183673872876', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633806', N'25938183673872622', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633807', N'25938183673872751', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633808', N'25938183673872864', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633809', N'25938183673872736', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633810', N'25938183673872610', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633811', N'25938183673872869', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633812', N'25938183673872741', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633813', N'25938183673872615', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633814', N'25938183673872857', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633815', N'25938183673872601', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633816', N'25938183673872850', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633817', N'25938183673872723', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633818', N'25938183673872596', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633819', N'25938183673872584', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633820', N'25938183673872713', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633821', N'25938183673872843', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633822', N'25938183673872589', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633823', N'25938183673872718', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633824', N'25938183673872579', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633825', N'25938183673872708', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633826', N'25938183673872838', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633827', N'25938183673872569', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633828', N'25938183673872827', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633829', N'25938183673872574', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633830', N'25938183673872703', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633831', N'25938183673872817', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633832', N'25938183673872689', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633833', N'25938183673872562', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633834', N'25938183673872822', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633835', N'25938183673872694', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633836', N'25938183673872552', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633837', N'25938183673872812', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633838', N'25938183673872684', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633839', N'25938183673872557', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633840', N'25938183673872800', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633841', N'25938183673872672', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633842', N'25938183673872547', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633843', N'25938183673872805', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633844', N'25938183673872679', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633845', N'25938183673872537', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633846', N'25938183673872795', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633847', N'25938183673872667', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633848', N'25938183673872542', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633849', N'25938183673872528', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633850', N'25938183673872657', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633851', N'25938183673872790', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633852', N'25938183673872662', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633853', N'25938183673872778', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633854', N'25938183673872523', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633855', N'25938183673872783', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633856', N'25938183673872768', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633857', N'25938183673872513', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633858', N'25938183673872773', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633859', N'25938183673872646', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633860', N'25938183673872518', N'RESOURCE', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633861', N'130', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633862', N'131', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633863', N'132', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633864', N'140', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633865', N'141', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633866', N'142', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633867', N'143', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633868', N'20', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633869', N'150', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633870', N'151', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633871', N'152', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633872', N'153', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633873', N'154', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633874', N'155', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633875', N'156', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633876', N'157', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633877', N'30', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633878', N'160', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633879', N'161', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633880', N'162', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633881', N'163', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633882', N'164', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633883', N'165', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633884', N'166', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633885', N'167', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633886', N'40', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633887', N'168', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633888', N'50', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633889', N'180', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633890', N'181', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633891', N'60', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633900', N'70', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633902', N'120', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633903', N'121', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633904', N'122', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

INSERT INTO [dbo].[c_role_authority]  VALUES (N'28369762228633905', N'123', N'MENU', N'1', N'2020-11-30 11:21:50.000', N'2')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for c_role_org
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_role_org]') AND type IN ('U'))
	DROP TABLE [dbo].[c_role_org]
GO

CREATE TABLE [dbo].[c_role_org] (
  [id] bigint  NOT NULL,
  [role_id] bigint  NOT NULL,
  [org_id] bigint  NOT NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[c_role_org] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_role_org',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色;#c_role',
'SCHEMA', N'dbo',
'TABLE', N'c_role_org',
'COLUMN', N'role_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'部门;#c_org',
'SCHEMA', N'dbo',
'TABLE', N'c_role_org',
'COLUMN', N'org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_role_org',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'c_role_org',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色组织关系',
'SCHEMA', N'dbo',
'TABLE', N'c_role_org'
GO


-- ----------------------------
-- Table structure for c_station
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_station]') AND type IN ('U'))
	DROP TABLE [dbo].[c_station]
GO

CREATE TABLE [dbo].[c_station] (
  [id] bigint  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [org_id] bigint  NULL,
  [state] bit DEFAULT ((1)) NULL,
  [describe_] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL,
  [update_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[c_station] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_station',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'c_station',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'组织;#c_org;@Echo(api = ORG_ID_CLASS,  beanClass = Org.class)',
'SCHEMA', N'dbo',
'TABLE', N'c_station',
'COLUMN', N'org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'c_station',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'c_station',
'COLUMN', N'describe_'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_station',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'c_station',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'c_station',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'c_station',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建者所属机构',
'SCHEMA', N'dbo',
'TABLE', N'c_station',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'岗位',
'SCHEMA', N'dbo',
'TABLE', N'c_station'
GO


-- ----------------------------
-- Table structure for c_user
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_user]') AND type IN ('U'))
	DROP TABLE [dbo].[c_user]
GO

CREATE TABLE [dbo].[c_user] (
  [id] bigint  NOT NULL,
  [account] varchar(30) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [name] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [org_id] bigint  NULL,
  [station_id] bigint  NULL,
  [readonly] bit DEFAULT ((0)) NOT NULL,
  [email] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [mobile] varchar(20) COLLATE Chinese_PRC_CI_AS  NULL,
  [sex] varchar(1) COLLATE Chinese_PRC_CI_AS DEFAULT ('M') NULL,
  [state] bit DEFAULT ((1)) NULL,
  [avatar] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [nation] char(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [education] char(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [position_status] char(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [work_describe] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [password_error_last_time] datetime  NULL,
  [password_error_num] int DEFAULT ((0)) NULL,
  [password_expire_time] datetime  NULL,
  [password] varchar(64) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [salt] varchar(20) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [last_login_time] datetime  NULL,
  [created_by] bigint  NULL,
  [create_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [update_time] datetime  NULL,
  [created_org_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[c_user] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'账号',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'account'
GO

EXEC sp_addextendedproperty
'MS_Description', N'姓名',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'组织;#c_org;@Echo(api = ORG_ID_CLASS,  beanClass = Org.class)',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'岗位;#c_station;@Echo(api = STATION_ID_CLASS)',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'station_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'内置',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'readonly'
GO

EXEC sp_addextendedproperty
'MS_Description', N'邮箱',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'email'
GO

EXEC sp_addextendedproperty
'MS_Description', N'手机',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'mobile'
GO

EXEC sp_addextendedproperty
'MS_Description', N'性别;
#Sex{W:女;M:男;N:未知}',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'sex'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'头像',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'avatar'
GO

EXEC sp_addextendedproperty
'MS_Description', N'民族;
@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.NATION)',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'nation'
GO

EXEC sp_addextendedproperty
'MS_Description', N'学历;
@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.EDUCATION)',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'education'
GO

EXEC sp_addextendedproperty
'MS_Description', N'职位状态;
@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.POSITION_STATUS)',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'position_status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'工作描述',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'work_describe'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后一次输错密码时间',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'password_error_last_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'密码错误次数',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'password_error_num'
GO

EXEC sp_addextendedproperty
'MS_Description', N'密码过期时间',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'password_expire_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'密码',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'password'
GO

EXEC sp_addextendedproperty
'MS_Description', N'密码盐',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'salt'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后登录时间',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'last_login_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新人id',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建者所属机构',
'SCHEMA', N'dbo',
'TABLE', N'c_user',
'COLUMN', N'created_org_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户',
'SCHEMA', N'dbo',
'TABLE', N'c_user'
GO


-- ----------------------------
-- Records of c_user
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[c_user]  VALUES (N'1', N'lampAdmin', N'内置管理员', N'1', N'1', N'1', N'15217781234@qq.com', N'15217781234', NULL, N'1', N'17e420c250804efe904a09a33796d5a10.jpg', N'01', N'01', N'01', N'不想上班!', NULL, N'0', NULL, N'0d70cc96860681487869a0304139d3410044298da40fe5b2d7acff76f83d79c8', N'ki5pj8dv44i14yu4nbhh', N'2022-08-23 16:35:07.103', N'1', N'2020-11-22 23:03:15.000', N'1', N'2020-11-22 23:03:15.000', N'1')
GO

INSERT INTO [dbo].[c_user]  VALUES (N'2', N'lamp', N'超级管理员', N'1533406176778125312', N'1', N'0', N'5', N'3', N'', N'1', N'20180414165815.jpg', N'02', N'04', N'03', N'不想上班!', NULL, N'0', NULL, N'0d70cc96860681487869a0304139d3410044298da40fe5b2d7acff76f83d79c8', N'ki5pj8dv44i14yu4nbhh', N'2022-08-26 15:54:04.360', N'1', N'2020-11-22 23:03:15.000', N'2', N'2022-06-05 23:02:13.000', N'1')
GO

INSERT INTO [dbo].[c_user]  VALUES (N'3', N'lamp_pt', N'平台管理员', N'1', N'1', N'0', N'2', N'3', NULL, N'1', N'a3b10296862e40edb811418d64455d00.jpeg', N'05', N'06', N'02', N'不想上班!', NULL, N'0', NULL, N'0d70cc96860681487869a0304139d3410044298da40fe5b2d7acff76f83d79c8', N'ki5pj8dv44i14yu4nbhh', N'2022-08-26 16:02:35.316', N'1', N'2020-11-22 23:03:15.000', N'2', N'2022-06-05 21:46:27.000', N'1')
GO

INSERT INTO [dbo].[c_user]  VALUES (N'4', N'general', N'普通管理员', N'1415864608656195584', N'1', N'0', N'', N'', N'N', N'1', N'', N'01', N'01', N'01', N'不想上班!', N'2022-06-05 21:47:47.000', N'0', NULL, N'0d70cc96860681487869a0304139d3410044298da40fe5b2d7acff76f83d79c8', N'ki5pj8dv44i14yu4nbhh', N'2022-06-05 21:47:47.000', N'1', N'2020-11-22 23:03:15.000', N'2', N'2022-06-05 21:46:08.000', N'1')
GO

INSERT INTO [dbo].[c_user]  VALUES (N'5', N'normal', N'普通用户', N'1533438004188676096', N'1', N'0', N'', N'', N'M', N'1', N'', N'02', N'02', N'02', N'不想上班!', N'2022-01-27 12:21:39.000', N'0', NULL, N'0d70cc96860681487869a0304139d3410044298da40fe5b2d7acff76f83d79c8', N'ki5pj8dv44i14yu4nbhh', N'2022-01-27 12:21:39.000', N'1', N'2020-11-22 23:03:15.000', N'2', N'2022-06-05 21:46:20.000', N'1')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for c_user_role
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[c_user_role]') AND type IN ('U'))
	DROP TABLE [dbo].[c_user_role]
GO

CREATE TABLE [dbo].[c_user_role] (
  [id] bigint  NOT NULL,
  [role_id] bigint  NOT NULL,
  [user_id] bigint  NOT NULL,
  [created_by] bigint  NULL,
  [create_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[c_user_role] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'c_user_role',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色;#c_role',
'SCHEMA', N'dbo',
'TABLE', N'c_user_role',
'COLUMN', N'role_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户;#c_user',
'SCHEMA', N'dbo',
'TABLE', N'c_user_role',
'COLUMN', N'user_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人ID',
'SCHEMA', N'dbo',
'TABLE', N'c_user_role',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'c_user_role',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色分配;账号角色绑定',
'SCHEMA', N'dbo',
'TABLE', N'c_user_role'
GO


-- ----------------------------
-- Records of c_user_role
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[c_user_role]  VALUES (N'1', N'1', N'1', N'1', N'2020-11-23 14:19:09.000')
GO

INSERT INTO [dbo].[c_user_role]  VALUES (N'2', N'1', N'2', N'1', N'2022-08-23 16:35:53.000')
GO

INSERT INTO [dbo].[c_user_role]  VALUES (N'3', N'2', N'3', N'1', NULL)
GO

COMMIT
GO


-- ----------------------------
-- Primary Key structure for table c_appendix
-- ----------------------------
ALTER TABLE [dbo].[c_appendix] ADD CONSTRAINT [PK__c_append__3213E83F9ACB4124] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table c_application
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_client_id]
ON [dbo].[c_application] (
  [client_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table c_application
-- ----------------------------
ALTER TABLE [dbo].[c_application] ADD CONSTRAINT [PK__c_applic__3213E83FDEEFE350] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table c_area
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_code]
ON [dbo].[c_area] (
  [code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table c_area
-- ----------------------------
ALTER TABLE [dbo].[c_area] ADD CONSTRAINT [PK__c_area__3213E83F88FA92A4] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table c_dictionary
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_type_code]
ON [dbo].[c_dictionary] (
  [type] ASC,
  [code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table c_dictionary
-- ----------------------------
ALTER TABLE [dbo].[c_dictionary] ADD CONSTRAINT [PK__c_dictio__3213E83FC872DB36] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table c_file
-- ----------------------------
ALTER TABLE [dbo].[c_file] ADD CONSTRAINT [PK__c_file__3213E83F3B44E45D] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table c_login_log
-- ----------------------------
ALTER TABLE [dbo].[c_login_log] ADD CONSTRAINT [PK__c_login___3213E83F263778E3] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table c_menu
-- ----------------------------
ALTER TABLE [dbo].[c_menu] ADD CONSTRAINT [PK__c_menu__3213E83F947FB256] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table c_opt_log
-- ----------------------------
ALTER TABLE [dbo].[c_opt_log] ADD CONSTRAINT [PK__c_opt_lo__3213E83F74DC5282] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table c_opt_log_ext
-- ----------------------------
ALTER TABLE [dbo].[c_opt_log_ext] ADD CONSTRAINT [PK__c_opt_lo__3213E83F26B2D55B] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table c_org
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_name]
ON [dbo].[c_org] (
  [label] ASC
)
GO

CREATE NONCLUSTERED INDEX [fu_path]
ON [dbo].[c_org] (
  [tree_path] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table c_org
-- ----------------------------
ALTER TABLE [dbo].[c_org] ADD CONSTRAINT [PK__c_org__3213E83F9BD8D0C2] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table c_parameter
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_key]
ON [dbo].[c_parameter] (
  [key_] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table c_parameter
-- ----------------------------
ALTER TABLE [dbo].[c_parameter] ADD CONSTRAINT [PK__c_parame__3213E83FBF48999E] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table c_resource
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_code]
ON [dbo].[c_resource] (
  [code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table c_resource
-- ----------------------------
ALTER TABLE [dbo].[c_resource] ADD CONSTRAINT [PK__c_resour__3213E83F8041318C] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table c_role
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_code]
ON [dbo].[c_role] (
  [code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table c_role
-- ----------------------------
ALTER TABLE [dbo].[c_role] ADD CONSTRAINT [PK__c_role__3213E83F5FFDE612] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table c_role_authority
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_role_authority]
ON [dbo].[c_role_authority] (
  [authority_id] ASC,
  [authority_type] ASC,
  [role_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table c_role_authority
-- ----------------------------
ALTER TABLE [dbo].[c_role_authority] ADD CONSTRAINT [PK__c_role_a__3213E83FF325540F] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table c_role_org
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_role_org]
ON [dbo].[c_role_org] (
  [org_id] ASC,
  [role_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table c_role_org
-- ----------------------------
ALTER TABLE [dbo].[c_role_org] ADD CONSTRAINT [PK__c_role_o__3213E83F622942DF] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table c_station
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_name]
ON [dbo].[c_station] (
  [name] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table c_station
-- ----------------------------
ALTER TABLE [dbo].[c_station] ADD CONSTRAINT [PK__c_statio__3213E83F467E78F0] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table c_user
-- ----------------------------
ALTER TABLE [dbo].[c_user] ADD CONSTRAINT [PK__c_user__3213E83FD5702954] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table c_user_role
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [uk_user_role]
ON [dbo].[c_user_role] (
  [role_id] ASC,
  [user_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table c_user_role
-- ----------------------------
ALTER TABLE [dbo].[c_user_role] ADD CONSTRAINT [PK__c_user_r__3213E83F0719334B] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]
GO

