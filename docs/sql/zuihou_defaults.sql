/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 127.0.0.1:3306
 Source Schema         : zuihou_defaults

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 26/04/2020 23:28:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for XXL_JOB_QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `XXL_JOB_QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `XXL_JOB_QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `XXL_JOB_QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for XXL_JOB_QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `XXL_JOB_QRTZ_CALENDARS`;
CREATE TABLE `XXL_JOB_QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for XXL_JOB_QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `XXL_JOB_QRTZ_CRON_TRIGGERS`;
CREATE TABLE `XXL_JOB_QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `XXL_JOB_QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for XXL_JOB_QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `XXL_JOB_QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `XXL_JOB_QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for XXL_JOB_QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `XXL_JOB_QRTZ_JOB_DETAILS`;
CREATE TABLE `XXL_JOB_QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for XXL_JOB_QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `XXL_JOB_QRTZ_LOCKS`;
CREATE TABLE `XXL_JOB_QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of XXL_JOB_QRTZ_LOCKS
-- ----------------------------
BEGIN;
INSERT INTO `XXL_JOB_QRTZ_LOCKS` VALUES ('DefaultQuartzScheduler', 'STATE_ACCESS');
INSERT INTO `XXL_JOB_QRTZ_LOCKS` VALUES ('DefaultQuartzScheduler', 'TRIGGER_ACCESS');
INSERT INTO `XXL_JOB_QRTZ_LOCKS` VALUES ('getSchedulerFactoryBean', 'STATE_ACCESS');
INSERT INTO `XXL_JOB_QRTZ_LOCKS` VALUES ('getSchedulerFactoryBean', 'TRIGGER_ACCESS');
COMMIT;

-- ----------------------------
-- Table structure for XXL_JOB_QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `XXL_JOB_QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `XXL_JOB_QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for XXL_JOB_QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `XXL_JOB_QRTZ_SCHEDULER_STATE`;
CREATE TABLE `XXL_JOB_QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of XXL_JOB_QRTZ_SCHEDULER_STATE
-- ----------------------------
BEGIN;
INSERT INTO `XXL_JOB_QRTZ_SCHEDULER_STATE` VALUES ('DefaultQuartzScheduler', 'tangyhMacBookPro.local1587831484902', 1587831708064, 5000);
INSERT INTO `XXL_JOB_QRTZ_SCHEDULER_STATE` VALUES ('getSchedulerFactoryBean', 'tangyhMacBookPro.local1553850279059', 1553850304933, 5000);
COMMIT;

-- ----------------------------
-- Table structure for XXL_JOB_QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `XXL_JOB_QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `XXL_JOB_QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `XXL_JOB_QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for XXL_JOB_QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `XXL_JOB_QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `XXL_JOB_QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `XXL_JOB_QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for XXL_JOB_QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `XXL_JOB_QRTZ_TRIGGERS`;
CREATE TABLE `XXL_JOB_QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `XXL_JOB_QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for d_global_user
-- ----------------------------
DROP TABLE IF EXISTS `d_global_user`;
CREATE TABLE `d_global_user` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(10) NOT NULL COMMENT '租户编号',
  `account` varchar(30) NOT NULL COMMENT '账号',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `readonly` bit(1) DEFAULT b'0' COMMENT '是否内置',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='全局账号';

-- ----------------------------
-- Records of d_global_user
-- ----------------------------
BEGIN;
INSERT INTO `d_global_user` VALUES (1, 'admin', 'admin', '10086', '超级管理员', '244387066@qq.com', '1fdbcfb7a0a8c138c7eedbd205639853', b'1', '2019-08-29 16:50:35', 1, '2019-08-29 16:50:35', 1);
INSERT INTO `d_global_user` VALUES (2, 'admin', 'demoAdmin', '10086', '超级管理员[演示]', '244387066@qq.com', 'd9d17d88918aa72834289edaf38f42e2', b'1', '2019-10-30 10:29:21', 1, '2019-10-30 10:29:23', 1);
COMMIT;

-- ----------------------------
-- Table structure for d_tenant
-- ----------------------------
DROP TABLE IF EXISTS `d_tenant`;
CREATE TABLE `d_tenant` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `code` varchar(20) DEFAULT '' COMMENT '企业编码',
  `name` varchar(255) DEFAULT '' COMMENT '企业名称',
  `type` varchar(10) DEFAULT 'CREATE' COMMENT '类型\n#{CREATE:创建;REGISTER:注册}',
  `status` varchar(10) DEFAULT 'NORMAL' COMMENT '状态\n#{NORMAL:正常;FORBIDDEN:禁用;WAITING:待审核;REFUSE:拒绝}',
  `readonly` bit(1) DEFAULT b'0' COMMENT '是否内置',
  `duty` varchar(50) DEFAULT NULL COMMENT '责任人',
  `expiration_time` datetime DEFAULT NULL COMMENT '有效期\n为空表示永久',
  `logo` varchar(255) DEFAULT '' COMMENT 'logo地址',
  `describe_` varchar(255) DEFAULT '' COMMENT '企业简介',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_CODE` (`code`) USING BTREE COMMENT '租户唯一编码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业';

-- ----------------------------
-- Records of d_tenant
-- ----------------------------
BEGIN;
INSERT INTO `d_tenant` VALUES (616676078974402977, '0000', '最后的内置企业', 'CREATE', 'NORMAL', b'1', '最后', NULL, NULL, '内置企业，请勿删除', '2019-08-29 16:50:35', 1, '2019-08-29 16:50:35', 1);
COMMIT;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'increment id',
  `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime NOT NULL COMMENT 'create datetime',
  `log_modified` datetime NOT NULL COMMENT 'modify datetime',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='AT transaction mode undo table';

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_group`;
CREATE TABLE `xxl_job_qrtz_trigger_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) NOT NULL COMMENT '执行器名称',
  `order` tinyint(4) NOT NULL DEFAULT '0' COMMENT '排序',
  `address_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` varchar(512) DEFAULT NULL COMMENT '执行器地址列表，多地址逗号分隔',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_APP_NAME` (`app_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT=' 任务组';

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_group
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_qrtz_trigger_group` VALUES (1, 'zuihou-jobs-server', 'zuihou执行器', 1, 0, '127.0.0.1:8771');
INSERT INTO `xxl_job_qrtz_trigger_group` VALUES (2, 'zuihou-executor-server', '分布式执行器', 2, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_info`;
CREATE TABLE `xxl_job_qrtz_trigger_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_cron` varchar(128) DEFAULT NULL COMMENT '任务执行CRON',
  `start_execute_time` datetime DEFAULT NULL COMMENT '执行时间 和 job_cron人选其一',
  `end_execute_time` datetime DEFAULT NULL COMMENT '执行时间 和 job_cron人选其一',
  `type_` int(11) NOT NULL DEFAULT '1' COMMENT '执行类型 1：cron  2：定时',
  `job_desc` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) DEFAULT NULL COMMENT '报警邮件',
  `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `interval_seconds` int(11) DEFAULT NULL COMMENT '间隔秒数',
  `repeat_count` int(11) DEFAULT NULL COMMENT '重复次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_info
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (41, 1, NULL, '2019-07-08 21:45:00', NULL, 2, '123', '2019-07-05 10:12:50', '2019-07-08 21:44:36', '最后', '', 'FIRST', 'demo2JobHandler', 'hello', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2019-07-05 10:12:50', '', 0, 0);
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (42, 1, '*/10 * * * * ? ', NULL, NULL, 1, '本地执行', '2019-07-07 18:33:16', '2019-07-08 14:49:19', '最后', '', 'FIRST', 'demo2JobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2019-07-07 18:33:16', '', 0, 0);
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (43, 1, '0 0 2 * * ?', NULL, NULL, 1, '重置租户', '2020-01-16 18:08:12', '2020-01-16 18:08:12', '最后', '', 'FIRST', 'restTenantJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2020-01-16 18:08:12', '', 0, 0);
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (44, 1, '0 0 2 * * ?', NULL, NULL, 1, '重置默认租户数据', '2020-01-16 18:09:53', '2020-01-16 18:09:53', '最后', '', 'FIRST', 'restBase0000JobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2020-01-16 18:09:53', '', 0, 0);
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (45, 1, '0 0 0/2 * * ?', NULL, NULL, 1, '删除过期在线用户', '2020-04-03 10:44:29', '2020-04-03 10:44:29', '最后', '', 'FIRST', 'userTokenRestJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2020-04-03 10:44:29', '', 0, 0);
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (46, 2, '*/10 * * * * ?', NULL, NULL, 1, '演示分布式', '2020-04-11 13:32:34', '2020-04-11 13:32:34', '最后', '', 'FIRST', 'demo2JobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2020-04-11 13:32:34', '', 0, 0);
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (47, 1, NULL, '2020-04-18 00:19:00', NULL, 2, '任务描述', '2020-04-18 00:15:00', '2020-04-18 00:15:00', 'admin', NULL, 'FIRST', 'smsSendJobHandler', '{\"id\":1251182376256536576,\"tenant\":\"1111\"}', 'SERIAL_EXECUTION', 0, 0, 'BEAN', NULL, NULL, '2020-04-18 00:15:00', NULL, 0, 0);
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (48, 1, NULL, '2020-04-18 10:28:00', NULL, 2, '任务描述', '2020-04-18 10:23:41', '2020-04-18 10:23:41', 'admin', NULL, 'FIRST', 'smsSendJobHandler', '{\"id\":1251335539450183680,\"tenant\":\"1111\"}', 'SERIAL_EXECUTION', 0, 0, 'BEAN', NULL, NULL, '2020-04-18 10:23:41', NULL, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_log`;
CREATE TABLE `xxl_job_qrtz_trigger_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text COMMENT '执行-日志',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `I_trigger_time` (`trigger_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_logglue`;
CREATE TABLE `xxl_job_qrtz_trigger_logglue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
  `add_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_registry`;
CREATE TABLE `xxl_job_qrtz_trigger_registry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(255) NOT NULL,
  `registry_key` varchar(255) NOT NULL,
  `registry_value` varchar(255) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_registry
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_qrtz_trigger_registry` VALUES (7, 'EXECUTOR', 'zuihou-jobs-server', '127.0.0.1:8771', '2020-04-26 00:21:44');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
