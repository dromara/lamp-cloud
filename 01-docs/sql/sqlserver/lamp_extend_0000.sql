/*
 Navicat Premium Data Transfer

 Source Server         : 172.26.3.67
 Source Server Type    : SQL Server
 Source Server Version : 15004236
 Source Host           : 117.187.194.249:1433
 Source Catalog        : lamp_extend_0000
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15004236
 File Encoding         : 65001

 Date: 28/08/2022 20:56:31
*/


-- ----------------------------
-- Table structure for e_block_list
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[e_block_list]') AND type IN ('U'))
	DROP TABLE [dbo].[e_block_list]
GO

CREATE TABLE [dbo].[e_block_list] (
  [id] bigint  NOT NULL,
  [ip] varchar(20) COLLATE Chinese_PRC_CI_AS  NULL,
  [request_uri] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [request_method] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [limit_start] varchar(8) COLLATE Chinese_PRC_CI_AS  NULL,
  [limit_end] varchar(8) COLLATE Chinese_PRC_CI_AS  NULL,
  [state] bit DEFAULT ((0)) NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL,
  [update_time] datetime  NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[e_block_list] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'e_block_list',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'阻止访问ip',
'SCHEMA', N'dbo',
'TABLE', N'e_block_list',
'COLUMN', N'ip'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求URI',
'SCHEMA', N'dbo',
'TABLE', N'e_block_list',
'COLUMN', N'request_uri'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求方法;
如果为ALL则表示对所有方法生效',
'SCHEMA', N'dbo',
'TABLE', N'e_block_list',
'COLUMN', N'request_method'
GO

EXEC sp_addextendedproperty
'MS_Description', N'限制时间起',
'SCHEMA', N'dbo',
'TABLE', N'e_block_list',
'COLUMN', N'limit_start'
GO

EXEC sp_addextendedproperty
'MS_Description', N'限制时间止',
'SCHEMA', N'dbo',
'TABLE', N'e_block_list',
'COLUMN', N'limit_end'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'e_block_list',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'e_block_list',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'e_block_list',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'e_block_list',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'e_block_list',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'黑名单',
'SCHEMA', N'dbo',
'TABLE', N'e_block_list'
GO


-- ----------------------------
-- Table structure for e_msg
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[e_msg]') AND type IN ('U'))
	DROP TABLE [dbo].[e_msg]
GO

CREATE TABLE [dbo].[e_msg] (
  [id] bigint  NOT NULL,
  [biz_id] varchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [biz_type] varchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [msg_type] varchar(20) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [title] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [content] text COLLATE Chinese_PRC_CI_AS  NULL,
  [author] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [handler_url] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [handler_params] varchar(500) COLLATE Chinese_PRC_CI_AS  NULL,
  [is_single_handle] bit DEFAULT ((1)) NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL,
  [update_time] datetime  NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[e_msg] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务ID',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'biz_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'业务类型;
#MsgBizType{USER_LOCK:账号锁定;USER_REG:账号申请;WORK_APPROVAL:考勤审批;}',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'biz_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消息类型;
#MsgType{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'msg_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'标题',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'title'
GO

EXEC sp_addextendedproperty
'MS_Description', N'内容',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'content'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发布人',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'author'
GO

EXEC sp_addextendedproperty
'MS_Description', N'处理地址;
以http开头时直接跳转，否则与#c_application表拼接后跳转http可带参数',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'handler_url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'处理参数',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'handler_params'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否单人处理',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'is_single_handle'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'e_msg',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消息表',
'SCHEMA', N'dbo',
'TABLE', N'e_msg'
GO


-- ----------------------------
-- Table structure for e_msg_receive
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[e_msg_receive]') AND type IN ('U'))
	DROP TABLE [dbo].[e_msg_receive]
GO

CREATE TABLE [dbo].[e_msg_receive] (
  [id] bigint  NOT NULL,
  [msg_id] bigint  NOT NULL,
  [user_id] bigint  NOT NULL,
  [is_read] bit DEFAULT ((0)) NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL,
  [update_time] datetime  NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[e_msg_receive] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'e_msg_receive',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消息ID;
#msg',
'SCHEMA', N'dbo',
'TABLE', N'e_msg_receive',
'COLUMN', N'msg_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接收人ID;
#c_user',
'SCHEMA', N'dbo',
'TABLE', N'e_msg_receive',
'COLUMN', N'user_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否已读',
'SCHEMA', N'dbo',
'TABLE', N'e_msg_receive',
'COLUMN', N'is_read'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'e_msg_receive',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'e_msg_receive',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'e_msg_receive',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'e_msg_receive',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'消息接收表',
'SCHEMA', N'dbo',
'TABLE', N'e_msg_receive'
GO


-- ----------------------------
-- Table structure for e_rate_limiter
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[e_rate_limiter]') AND type IN ('U'))
	DROP TABLE [dbo].[e_rate_limiter]
GO

CREATE TABLE [dbo].[e_rate_limiter] (
  [id] bigint  NOT NULL,
  [count] int DEFAULT ((0)) NULL,
  [request_uri] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [request_method] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [limit_start] varchar(8) COLLATE Chinese_PRC_CI_AS  NULL,
  [limit_end] varchar(8) COLLATE Chinese_PRC_CI_AS  NULL,
  [state] bit DEFAULT ((0)) NULL,
  [interval_sec] bigint DEFAULT ((0)) NULL,
  [create_time] datetime  NULL,
  [created_by] bigint  NULL,
  [update_time] datetime  NULL,
  [updated_by] bigint  NULL
)
GO

ALTER TABLE [dbo].[e_rate_limiter] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'e_rate_limiter',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'次数',
'SCHEMA', N'dbo',
'TABLE', N'e_rate_limiter',
'COLUMN', N'count'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求URI',
'SCHEMA', N'dbo',
'TABLE', N'e_rate_limiter',
'COLUMN', N'request_uri'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求方法;
如果为ALL则表示对所有方法生效',
'SCHEMA', N'dbo',
'TABLE', N'e_rate_limiter',
'COLUMN', N'request_method'
GO

EXEC sp_addextendedproperty
'MS_Description', N'限制时间起',
'SCHEMA', N'dbo',
'TABLE', N'e_rate_limiter',
'COLUMN', N'limit_start'
GO

EXEC sp_addextendedproperty
'MS_Description', N'限制时间止',
'SCHEMA', N'dbo',
'TABLE', N'e_rate_limiter',
'COLUMN', N'limit_end'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'e_rate_limiter',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'时间窗口',
'SCHEMA', N'dbo',
'TABLE', N'e_rate_limiter',
'COLUMN', N'interval_sec'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'e_rate_limiter',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'e_rate_limiter',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'e_rate_limiter',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'e_rate_limiter',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'限流',
'SCHEMA', N'dbo',
'TABLE', N'e_rate_limiter'
GO


-- ----------------------------
-- Table structure for e_sms_send_status
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[e_sms_send_status]') AND type IN ('U'))
	DROP TABLE [dbo].[e_sms_send_status]
GO

CREATE TABLE [dbo].[e_sms_send_status] (
  [id] bigint  NOT NULL,
  [task_id] bigint  NOT NULL,
  [send_status] varchar(10) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [tel_num] varchar(20) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [biz_id] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [ext] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [code] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [message] varchar(500) COLLATE Chinese_PRC_CI_AS  NULL,
  [fee] int  NULL,
  [created_by] bigint  NULL,
  [create_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [update_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[e_sms_send_status] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'任务ID;
#e_sms_task',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status',
'COLUMN', N'task_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发送状态;
#SendStatus{WAITING:等待发送;SUCCESS:发送成功;FAIL:发送失败}',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status',
'COLUMN', N'send_status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接收者手机
单个手机号;
阿里：发送回执ID,可根据该ID查询具体的发送状态  腾讯：sid 标识本次发送id，标识一次短信下发记录  百度：requestId 短信发送请求唯一流水ID',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status',
'COLUMN', N'tel_num'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发送回执ID',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status',
'COLUMN', N'biz_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发送返回;
阿里：RequestId 请求ID  腾讯：ext：用户的session内容，腾讯server回包中会原样返回   百度：无',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status',
'COLUMN', N'ext'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态码;阿里：返回OK代表请求成功,其他错误码详见错误码列表  腾讯：0表示成功(计费依据)，非0表示失败  百度：1000 表示成功',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status',
'COLUMN', N'code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态码的描述',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status',
'COLUMN', N'message'
GO

EXEC sp_addextendedproperty
'MS_Description', N'短信计费的条数;腾讯专用',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status',
'COLUMN', N'fee'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'短信发送状态',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_send_status'
GO


-- ----------------------------
-- Table structure for e_sms_task
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[e_sms_task]') AND type IN ('U'))
	DROP TABLE [dbo].[e_sms_task]
GO

CREATE TABLE [dbo].[e_sms_task] (
  [id] bigint  NOT NULL,
  [template_id] bigint  NOT NULL,
  [status] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [source_type] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [topic] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [template_params] varchar(500) COLLATE Chinese_PRC_CI_AS  NULL,
  [send_time] datetime  NULL,
  [content] varchar(500) COLLATE Chinese_PRC_CI_AS  NULL,
  [draft] bit DEFAULT ((0)) NULL,
  [created_by] bigint  NULL,
  [create_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [update_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[e_sms_task] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'短信记录ID',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'短信模板;@Echo(api = SMS_TEMPLATE_ID_CLASS)#e_sms_template',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task',
'COLUMN', N'template_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'执行状态;
(手机号具体发送状态看sms_send_status表) 
#TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发送渠道;#SourceType{APP:应用;SERVICE:服务}',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task',
'COLUMN', N'source_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'主题',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task',
'COLUMN', N'topic'
GO

EXEC sp_addextendedproperty
'MS_Description', N'参数;
需要封装为{‘key’:’value’, ...}格式且key必须有序',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task',
'COLUMN', N'template_params'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发送时间',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task',
'COLUMN', N'send_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发送内容;
需要封装正确格式化: 您好，张三，您有一个新的快递。',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task',
'COLUMN', N'content'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否草稿',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task',
'COLUMN', N'draft'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人ID',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发送任务',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_task'
GO


-- ----------------------------
-- Table structure for e_sms_template
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[e_sms_template]') AND type IN ('U'))
	DROP TABLE [dbo].[e_sms_template]
GO

CREATE TABLE [dbo].[e_sms_template] (
  [id] bigint  NOT NULL,
  [provider_type] varchar(10) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [app_id] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [app_secret] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [url] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [content] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [template_params] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [template_code] varchar(50) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [sign_name] varchar(100) COLLATE Chinese_PRC_CI_AS  NULL,
  [template_describe] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [created_by] bigint  NULL,
  [create_time] datetime  NULL,
  [updated_by] bigint  NULL,
  [update_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[e_sms_template] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板ID',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'供应商类型;
#ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'provider_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'应用ID',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'app_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'应用密码',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'app_secret'
GO

EXEC sp_addextendedproperty
'MS_Description', N'SMS服务域名;
百度、其他厂商会用',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板名称',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板内容',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'content'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板参数',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'template_params'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板编码',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'template_code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'签名',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'sign_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'template_describe'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人ID',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'created_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改人',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'updated_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'最后修改时间',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'短信模板',
'SCHEMA', N'dbo',
'TABLE', N'e_sms_template'
GO


-- ----------------------------
-- Primary Key structure for table e_block_list
-- ----------------------------
ALTER TABLE [dbo].[e_block_list] ADD CONSTRAINT [PK__e_block___3213E83FBB83542B] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table e_msg
-- ----------------------------
ALTER TABLE [dbo].[e_msg] ADD CONSTRAINT [PK__e_msg__3213E83F4F27DA2D] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table e_msg_receive
-- ----------------------------
ALTER TABLE [dbo].[e_msg_receive] ADD CONSTRAINT [PK__e_msg_re__3213E83F70317EDE] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table e_rate_limiter
-- ----------------------------
ALTER TABLE [dbo].[e_rate_limiter] ADD CONSTRAINT [PK__e_rate_l__3213E83F1777B478] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table e_sms_send_status
-- ----------------------------
CREATE NONCLUSTERED INDEX [task_id_tel_num]
ON [dbo].[e_sms_send_status] (
  [task_id] ASC,
  [tel_num] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table e_sms_send_status
-- ----------------------------
ALTER TABLE [dbo].[e_sms_send_status] ADD CONSTRAINT [PK__e_sms_se__3213E83FD0903249] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table e_sms_task
-- ----------------------------
CREATE NONCLUSTERED INDEX [tempate_id_topic_content]
ON [dbo].[e_sms_task] (
  [template_id] ASC,
  [topic] ASC,
  [content] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table e_sms_task
-- ----------------------------
ALTER TABLE [dbo].[e_sms_task] ADD CONSTRAINT [PK__e_sms_ta__3213E83F0D27B239] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table e_sms_template
-- ----------------------------
ALTER TABLE [dbo].[e_sms_template] ADD CONSTRAINT [PK__e_sms_te__3213E83F30441B94] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

