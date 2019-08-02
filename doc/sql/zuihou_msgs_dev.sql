/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 127.0.0.1:3306
 Source Schema         : zuihou_msgs_dev

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 02/08/2019 10:48:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mail_provider
-- ----------------------------
DROP TABLE IF EXISTS `mail_provider`;
CREATE TABLE `mail_provider` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ID',
  `mail_type` varchar(10) DEFAULT 'TENCENT' COMMENT '邮箱类型\n#MailType{SINA:新浪;QQ:腾讯;WY163:网易}',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱授权码',
  `host` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主机',
  `port` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '端口',
  `protocol` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '协议',
  `auth` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否进行用户名密码校验',
  `name` varchar(60) DEFAULT NULL COMMENT '名称',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `properties` varchar(500) DEFAULT NULL COMMENT '属性',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='邮件供应商';

-- ----------------------------
-- Table structure for mail_send_status
-- ----------------------------
DROP TABLE IF EXISTS `mail_send_status`;
CREATE TABLE `mail_send_status` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ID',
  `task_id` bigint(20) NOT NULL COMMENT '任务id\n#mail_task',
  `email` varchar(64) NOT NULL COMMENT '收件邮箱',
  `mail_status` varchar(255) NOT NULL DEFAULT 'UNREAD' COMMENT '邮件状态\r\n#MailStatus{UNREAD:未读;READ:已读;DELETED:已删除;ABNORMAL:异常;VIRUSES:病毒;TRASH:垃圾}',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='邮件发送状态';

-- ----------------------------
-- Table structure for mail_task
-- ----------------------------
DROP TABLE IF EXISTS `mail_task`;
CREATE TABLE `mail_task` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ID',
  `status` varchar(10) DEFAULT 'WAITING' COMMENT '执行状态\r\n#TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}',
  `provider_id` bigint(20) DEFAULT NULL COMMENT '发件人id\n#mail_provider',
  `to` varchar(500) DEFAULT '' COMMENT '收件人\n多个,号分割',
  `cc` varchar(255) DEFAULT '' COMMENT '抄送人\n多个,分割',
  `bcc` varchar(255) DEFAULT '' COMMENT '密送人\n多个,分割',
  `subject` varchar(255) DEFAULT '' COMMENT '主题',
  `body` text CHARACTER SET utf8 COMMENT '正文',
  `err_msg` varchar(500) DEFAULT '' COMMENT '发送失败原因',
  `sender_code` varchar(64) DEFAULT '' COMMENT '发送商编码',
  `plan_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '计划发送时间\n(默认当前时间，可定时发送)',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='邮件任务';

-- ----------------------------
-- Table structure for msgs_center_info
-- ----------------------------
DROP TABLE IF EXISTS `msgs_center_info`;
CREATE TABLE `msgs_center_info` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `biz_id` varchar(64) DEFAULT NULL COMMENT '业务ID\n业务表的唯一id',
  `biz_type` varchar(64) NOT NULL COMMENT '业务类型\n#MsgsBizType.code',
  `msgs_center_type` varchar(20) NOT NULL DEFAULT 'NOTIFY' COMMENT '消息类型\n#MsgsCenterType{WAIT:待办;NOTIFY:通知;PUBLICITY:公示公告;WARN:预警;}',
  `title` varchar(100) DEFAULT '' COMMENT '标题',
  `content` varchar(500) DEFAULT '' COMMENT '内容',
  `author` varchar(50) DEFAULT '' COMMENT '作者名称',
  `handler_url` varchar(200) DEFAULT '' COMMENT '处理地址\n以http开头时直接跳转，否则与#c_application表拼接后跳转\nhttp可带参数',
  `handler_params` varchar(400) DEFAULT '' COMMENT '处理参数',
  `is_single_handle` bit(1) DEFAULT b'1' COMMENT '是否单人处理后就标记已处理',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除\n业务数据删除后，会调用接口删除该消息',
  `app_code` varchar(64) DEFAULT '' COMMENT '应用code',
  `app_name` varchar(255) DEFAULT '' COMMENT '应用名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='消息中心';

-- ----------------------------
-- Records of msgs_center_info
-- ----------------------------
BEGIN;
INSERT INTO `msgs_center_info` VALUES (588744392009842721, '1', 'DEVELOPER_MODULE_AUDIT', 'WAIT', '您有一条权限系统的代办事项', '您有一条权限系统的代办事项', '汤云汉', 'http://www.baidu.com', '', b'1', b'0', '', '', '2019-06-13 15:00:02', 1, '2019-06-13 15:00:02', 1);
INSERT INTO `msgs_center_info` VALUES (588745183332401249, '1', 'DEVELOPER_MODULE_AUDIT', 'WAIT', '您有一条权限系统的代办事项', '您有一条权限系统的代办事项', '汤云汉', 'http://www.baidu.com', '', b'1', b'0', '', '', '2019-06-13 15:03:10', 1, '2019-06-13 15:03:10', 1);
INSERT INTO `msgs_center_info` VALUES (588745603350003937, '1', 'DEVELOPER_MODULE_AUDIT', 'NOTIFY', '您有一封新邮件', '您有一封新邮件', '汤云汉', 'http://www.baidu.com', '', b'1', b'0', '', '', '2019-06-13 15:04:50', 1, '2019-06-13 15:04:50', 1);
INSERT INTO `msgs_center_info` VALUES (588745891741958433, '1', 'DEVELOPER_MODULE_AUDIT', 'NOTIFY', '您有一封新邮件', '您有一封新邮件', '汤云汉', 'http://www.baidu.com', '', b'1', b'0', '', '', '2019-06-13 15:05:59', 1, '2019-06-13 15:05:59', 1);
INSERT INTO `msgs_center_info` VALUES (588746158852014433, '1', 'DEVELOPER_MODULE_AUDIT', 'PUBLICITY', '停机维护通知', '尊敬的用户，您好，本系统将于2019年6月20日22点停机升级，请见谅', '汤云汉', 'http://www.baidu.com', '', b'1', b'0', '', '', '2019-06-13 15:07:03', 1, '2019-06-13 15:07:03', 1);
COMMIT;

-- ----------------------------
-- Table structure for msgs_center_info_receive
-- ----------------------------
DROP TABLE IF EXISTS `msgs_center_info_receive`;
CREATE TABLE `msgs_center_info_receive` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `msgs_center_id` bigint(20) NOT NULL COMMENT '消息中心ID\n#msgs_center_info',
  `user_id` bigint(20) NOT NULL COMMENT '接收人ID\n#c_user',
  `is_read` bit(1) DEFAULT b'0' COMMENT '是否已读\n#BooleanStatus',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_user` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='消息中心 接收表\n全量数据';

-- ----------------------------
-- Records of msgs_center_info_receive
-- ----------------------------
BEGIN;
INSERT INTO `msgs_center_info_receive` VALUES (588744392093728833, 588744392009842721, 1, b'0', '2019-06-13 15:00:02', 1, 1, '2019-06-13 15:00:02');
INSERT INTO `msgs_center_info_receive` VALUES (588745183407898753, 588745183332401249, 1, b'0', '2019-06-13 15:03:10', 1, 1, '2019-06-13 15:03:10');
INSERT INTO `msgs_center_info_receive` VALUES (588745603517776129, 588745603350003937, 1, b'0', '2019-06-13 15:04:50', 1, 1, '2019-06-13 15:04:50');
INSERT INTO `msgs_center_info_receive` VALUES (588745891813261633, 588745891741958433, 1, b'0', '2019-06-13 15:05:59', 1, 1, '2019-06-13 15:05:59');
INSERT INTO `msgs_center_info_receive` VALUES (588746159023980929, 588746158852014433, 1, b'0', '2019-06-13 15:07:03', 1, 1, '2019-06-13 15:07:03');
COMMIT;

-- ----------------------------
-- Table structure for sms_provider
-- ----------------------------
DROP TABLE IF EXISTS `sms_provider`;
CREATE TABLE `sms_provider` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ID',
  `provider_type` varchar(10) NOT NULL DEFAULT 'TENCENT' COMMENT '供应商类型\n#ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}',
  `app_id` varchar(64) DEFAULT '' COMMENT '应用编码',
  `app_secret` varchar(64) DEFAULT '' COMMENT '应用密钥',
  `url` varchar(255) DEFAULT '' COMMENT 'SMS服务域名\n百度、其他厂商会用',
  `name` varchar(255) DEFAULT '' COMMENT '账号名称',
  `account` varchar(100) DEFAULT '' COMMENT '第三方登录账号',
  `password` varchar(255) DEFAULT NULL COMMENT '第三方登录密码',
  `description` varchar(300) DEFAULT '' COMMENT '描述',
  `existing` bigint(20) DEFAULT '0' COMMENT '现有短信量',
  `used` bigint(20) DEFAULT '0' COMMENT '已用短信量',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='短信供应商';

-- ----------------------------
-- Records of sms_provider
-- ----------------------------
BEGIN;
INSERT INTO `sms_provider` VALUES (606765328403267617, 'ALI', 'LTAI2HEU0Bv8J1fK', 'NxaPZz460Uje1ilscMyzN6Z2DPEXIc', '', '闪购网', '307479353@qq.com', '你想多了吧？', '闪购网测试短信', 0, 0, 0, '2019-08-02 08:28:48', 0, '2019-08-02 08:28:48');
COMMIT;

-- ----------------------------
-- Table structure for sms_send_status
-- ----------------------------
DROP TABLE IF EXISTS `sms_send_status`;
CREATE TABLE `sms_send_status` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `task_id` bigint(20) NOT NULL COMMENT '任务ID\n#sms_task',
  `send_status` varchar(10) NOT NULL DEFAULT 'WAITING' COMMENT '发送状态\n#SendStatus{WAITING:等待发送;SUCCESS:发送成功;FAIL:发送失败}',
  `receiver` varchar(20) NOT NULL COMMENT '接收者手机号\n单个手机号',
  `biz_id` varchar(255) DEFAULT '' COMMENT '发送回执ID\n阿里：发送回执ID,可根据该ID查询具体的发送状态  腾讯：sid 标识本次发送id，标识一次短信下发记录  百度：requestId 短信发送请求唯一流水ID',
  `ext` varchar(255) DEFAULT '' COMMENT '发送返回\n阿里：RequestId 请求ID  腾讯：ext：用户的session内容，腾讯server回包中会原样返回   百度：无',
  `code` varchar(255) DEFAULT '' COMMENT '状态码\n阿里：返回OK代表请求成功,其他错误码详见错误码列表  腾讯：0表示成功(计费依据)，非0表示失败  百度：1000 表示成功',
  `message` varchar(500) DEFAULT '' COMMENT '状态码的描述',
  `fee` int(11) DEFAULT '0' COMMENT '短信计费的条数\n腾讯专用',
  `create_month` varchar(7) DEFAULT '' COMMENT '创建时年月\n格式：yyyy-MM 用于统计',
  `create_week` varchar(10) DEFAULT '' COMMENT '创建时年周\n创建时处于当年的第几周 yyyy-ww 用于统计',
  `create_date` varchar(10) DEFAULT '' COMMENT '创建时年月日\n格式： yyyy-MM-dd 用于统计',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='短信发送状态';

-- ----------------------------
-- Records of sms_send_status
-- ----------------------------
BEGIN;
INSERT INTO `sms_send_status` VALUES (606767993707298913, 606767990788063297, 'FAIL', '15218869970', '', 'EB2CD01A-9602-4DD7-A6AE-BAEACD90057A', 'isv.AMOUNT_NOT_ENOUGH', '账户余额不足', 0, '', '', '', 0, '2019-08-02 08:39:23', 0, '2019-08-02 08:39:23');
COMMIT;

-- ----------------------------
-- Table structure for sms_task
-- ----------------------------
DROP TABLE IF EXISTS `sms_task`;
CREATE TABLE `sms_task` (
  `id` bigint(20) NOT NULL COMMENT '短信记录ID',
  `provider_id` bigint(20) NOT NULL COMMENT '发送供应商ID\n#sms_provider',
  `template_id` bigint(20) NOT NULL COMMENT '短信模板ID\n#sms_template',
  `status` varchar(10) DEFAULT 'WAITING' COMMENT '任务执行状态\n(手机号具体发送状态看sms_send_status表) \n#TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}',
  `source_type` varchar(10) DEFAULT 'APP' COMMENT '来源类型\n#SourceType{APP:应用;SERVICE:服务}\n',
  `receiver` text COMMENT '接收者手机号\n群发用英文逗号分割.\n支持2种格式:\n1: 手机号,手机号 \n2: 姓名<手机号>,姓名<手机号>',
  `topic` varchar(255) DEFAULT '' COMMENT '主题',
  `template_params` varchar(500) DEFAULT '' COMMENT '短信模板参数 \n需要封装为{‘key’:’value’, ...}格式\n且key必须有序\n\n',
  `send_time` datetime DEFAULT NULL COMMENT '短信发送时间',
  `context` varchar(500) DEFAULT '' COMMENT '发送内容\n需要封装正确格式化: 您好，张三，您有一个新的快递。',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='发送任务\n所有的短息发送调用，都视为是一次短信任务，任务表只保存数据和执行状态等信息，\n具体的发送状态查看发送状态（#sms_send_status）表';

-- ----------------------------
-- Records of sms_task
-- ----------------------------
BEGIN;
INSERT INTO `sms_task` VALUES (606767990788063297, 606765328403267617, 606767517569908769, 'SUCCESS', 'SERVICE', '15218869970', '闪购网', '{\"code\":\"123456\"}', NULL, '尊敬的用户，欢迎注册闪购网，您的注册验证码：123456,有效期5分钟。请勿将短信验证码告知他人！', 0, '2019-08-02 08:39:22', 0, '2019-08-02 08:39:23');
COMMIT;

-- ----------------------------
-- Table structure for sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template` (
  `id` bigint(20) NOT NULL COMMENT '模板ID',
  `provider_id` bigint(20) NOT NULL COMMENT '供应商ID\n#sms_provider',
  `custom_code` varchar(20) NOT NULL DEFAULT '' COMMENT '模板编码\n用于api发送',
  `name` varchar(255) DEFAULT '' COMMENT '模板名称',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '模板内容',
  `template_params` varchar(255) NOT NULL DEFAULT '' COMMENT '模板参数',
  `template_code` varchar(50) DEFAULT '' COMMENT '模板CODE',
  `sign_name` varchar(100) DEFAULT '' COMMENT '模板签名名称',
  `template_describe` varchar(255) DEFAULT '' COMMENT '模板描述',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_CODE` (`custom_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='短信模板';

-- ----------------------------
-- Records of sms_template
-- ----------------------------
BEGIN;
INSERT INTO `sms_template` VALUES (606767517569908769, 606765328403267617, 'SGW_REG', '闪购网', '尊敬的用户，欢迎注册闪购网，您的注册验证码：${code},有效期5分钟。请勿将短信验证码告知他人！', '{\"code\":\"\"}', 'SMS_99185070', '闪购网', '注册验证码', 0, '2019-08-02 08:37:30', 0, '2019-08-02 08:37:30');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
