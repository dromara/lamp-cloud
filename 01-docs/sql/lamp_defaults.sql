/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 127.0.0.1:3306
 Source Schema         : lamp_defaults

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 10/01/2021 10:00:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_datasource_config
-- ----------------------------
DROP TABLE IF EXISTS `c_datasource_config`;
CREATE TABLE `c_datasource_config` (
                                       `id`                bigint(20) NOT NULL COMMENT '主键ID',
                                       `name`              varchar(255) NOT NULL COMMENT '名称',
                                       `username`          varchar(255) NOT NULL COMMENT '账号',
                                       `password`          varchar(255) NOT NULL COMMENT '密码',
                                       `url`               varchar(255) NOT NULL COMMENT '链接',
                                       `driver_class_name` varchar(255) NOT NULL COMMENT '驱动',
                                       `create_time`       datetime DEFAULT NULL COMMENT '创建时间',
                                       `created_by`        bigint(20) DEFAULT NULL COMMENT '创建人',
                                       `update_time`       datetime DEFAULT NULL COMMENT '修改时间',
                                       `updated_by`        bigint(20) DEFAULT NULL COMMENT '修改人',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据源';

-- ----------------------------
-- Records of c_datasource_config
-- ----------------------------
BEGIN;
INSERT INTO `c_datasource_config`
VALUES (1339508377687949312, '1111-基础服务', 'root', 'root',
        'jdbc:mysql://127.0.0.1:3306/lamp_base_ddddd?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true',
        'com.mysql.cj.jdbc.Driver', '2020-12-17 17:50:59', 3, '2021-01-03 20:21:30', 3);
INSERT INTO `c_datasource_config`
VALUES (1345706960036560896, '1111-扩展服务', 'root', 'root',
        'jdbc:mysql://127.0.0.1:3306/lamp_extend_ddddd?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true',
        'com.mysql.cj.jdbc.Driver', '2021-01-03 20:21:56', 3, '2021-01-03 20:21:56', 3);
COMMIT;

-- ----------------------------
-- Table structure for c_tenant
-- ----------------------------
DROP TABLE IF EXISTS `c_tenant`;
CREATE TABLE `c_tenant`
(
    `id`            bigint(20) NOT NULL COMMENT '主键ID',
    `code`          varchar(20) NOT NULL COMMENT '企业编码',
    `name`          varchar(255) DEFAULT '' COMMENT '企业名称',
    `type`          varchar(10)  DEFAULT '' COMMENT '类型 \n#{CREATE:创建;REGISTER:注册}',
  `connect_type` varchar(10) DEFAULT '' COMMENT '链接类型 \n#TenantConnectTypeEnum{LOCAL:本地;REMOTE:远程}',
  `status` varchar(10) DEFAULT '' COMMENT '状态 \n#{NORMAL:正常;WAIT_INIT:待初始化;FORBIDDEN:禁用;WAITING:待审核;REFUSE:拒绝;DELETE:已删除}',
  `readonly_` bit(1) DEFAULT b'0' COMMENT '内置',
  `duty` varchar(50) DEFAULT '' COMMENT '责任人',
  `expiration_time` datetime DEFAULT NULL COMMENT '有效期 \n为空表示永久',
  `logo` varchar(255) DEFAULT '' COMMENT 'logo地址',
  `describe_` varchar(255) DEFAULT '' COMMENT '企业简介',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业';

-- ----------------------------
-- Records of c_tenant
-- ----------------------------
BEGIN;
INSERT INTO `c_tenant` VALUES (1, '0000', '最后内置的运营&超级租户', 'CREATE', 'LOCAL', 'NORMAL', b'1', '最后', NULL, NULL, '内置租户,用于测试租户系统所有功能, 用于管理整个系统.请勿删除', '2019-08-29 16:50:35', 1, '2019-08-29 16:50:35', 1);
COMMIT;

-- ----------------------------
-- Table structure for c_tenant_datasource_config
-- ----------------------------
DROP TABLE IF EXISTS `c_tenant_datasource_config`;
CREATE TABLE `c_tenant_datasource_config` (
                                              `id`                   bigint(20) NOT NULL COMMENT 'ID',
                                              `tenant_id`            bigint(20) NOT NULL COMMENT '租户id',
                                              `datasource_config_id` bigint(20) NOT NULL COMMENT '数据源id',
                                              `application`          varchar(100) NOT NULL DEFAULT '' COMMENT '服务',
                                              `create_time`          datetime              DEFAULT NULL COMMENT '创建时间',
                                              `created_by`           bigint(20) DEFAULT NULL COMMENT '创建人',
                                              PRIMARY KEY (`id`) USING BTREE,
                                              UNIQUE KEY `uk_tenan_application` (`tenant_id`,`datasource_config_id`,`application`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='租户数据源关系';

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'increment id',
    `branch_id`     bigint(20) NOT NULL COMMENT 'branch transaction id',
    `xid`           varchar(100) NOT NULL COMMENT 'global transaction id',
    `context`       varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` longblob     NOT NULL COMMENT 'rollback info',
    `log_status`    int(11) NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   datetime(6) NOT NULL COMMENT 'create datetime',
    `log_modified`  datetime(6) NOT NULL COMMENT 'modify datetime',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='AT transaction mode undo table';

-- ----------------------------
-- Table structure for worker_node
-- ----------------------------
DROP TABLE IF EXISTS `worker_node`;
CREATE TABLE `worker_node` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
   `host_name` varchar(64) NOT NULL COMMENT '主机名',
   `port` varchar(64) NOT NULL COMMENT '端口',
   `type` int(11) NOT NULL COMMENT '节点类型: ACTUAL 或者 CONTAINER',
   `launch_date` date NOT NULL COMMENT '上线日期',
   `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   `created`     timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='DB WorkerID Assigner for UID Generator';

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `app_name`     varchar(64) NOT NULL COMMENT '执行器AppName',
    `title`        varchar(12) NOT NULL COMMENT '执行器名称',
    `address_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
    `address_list` text COMMENT '执行器地址列表，多地址逗号分隔',
    `update_time`  datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_group`
VALUES (1, 'lamp-base-executor', '基础库执行器', 0, NULL, '2021-01-09 23:08:34');
INSERT INTO `xxl_job_group`
VALUES (2, 'lamp-extend-executor', '扩展库执行器', 0, NULL, '2021-01-09 23:08:34');
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info`
(
    `id`                        int(11) NOT NULL AUTO_INCREMENT,
    `job_group`                 int(11) NOT NULL COMMENT '执行器主键ID',
    `job_desc`                  varchar(255) NOT NULL,
    `add_time`                  datetime              DEFAULT NULL,
    `update_time`               datetime              DEFAULT NULL,
    `author`                    varchar(64)           DEFAULT NULL COMMENT '作者',
    `alarm_email`               varchar(255)          DEFAULT NULL COMMENT '报警邮件',
    `schedule_type`             varchar(50)  NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
    `schedule_conf`             varchar(128)          DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
    `misfire_strategy`          varchar(50)  NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
    `executor_route_strategy`   varchar(50)           DEFAULT NULL COMMENT '执行器路由策略',
    `executor_handler`          varchar(255)          DEFAULT NULL COMMENT '执行器任务handler',
    `executor_param`            varchar(512)          DEFAULT NULL COMMENT '执行器任务参数',
    `executor_block_strategy`   varchar(50)           DEFAULT NULL COMMENT '阻塞处理策略',
    `executor_timeout`          int(11) NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
    `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
    `glue_type`                 varchar(50)  NOT NULL COMMENT 'GLUE类型',
    `glue_source`               mediumtext COMMENT 'GLUE源代码',
    `glue_remark`               varchar(128)          DEFAULT NULL COMMENT 'GLUE备注',
    `glue_updatetime`           datetime              DEFAULT NULL COMMENT 'GLUE更新时间',
    `child_jobid`               varchar(255)          DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
    `trigger_status`            tinyint(4) NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
    `trigger_last_time`         bigint(13) NOT NULL DEFAULT '0' COMMENT '上次调度时间',
    `trigger_next_time`         bigint(13) NOT NULL DEFAULT '0' COMMENT '下次调度时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_info`
VALUES (1, 1, '测试任务1', '2018-11-03 22:21:31', '2018-11-03 22:21:31', 'XXL', '', 'CRON', '0 0 0 * * ? *', 'DO_NOTHING',
        'FIRST', 'demoJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2018-11-03 22:21:31', '', 0,
        0, 0);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock`
(
    `lock_name` varchar(50) NOT NULL COMMENT '锁名称',
    PRIMARY KEY (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_lock`
VALUES ('schedule_lock');
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log`
(
    `id`                        bigint(20) NOT NULL AUTO_INCREMENT,
    `job_group`                 int(11) NOT NULL COMMENT '执行器主键ID',
    `job_id`                    int(11) NOT NULL COMMENT '任务，主键ID',
    `executor_address`          varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
    `executor_handler`          varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
    `executor_param`            varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
    `executor_sharding_param`   varchar(20)  DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
    `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
    `trigger_time`              datetime     DEFAULT NULL COMMENT '调度-时间',
    `trigger_code`              int(11) NOT NULL COMMENT '调度-结果',
    `trigger_msg`               text COMMENT '调度-日志',
    `handle_time`               datetime     DEFAULT NULL COMMENT '执行-时间',
    `handle_code`               int(11) NOT NULL COMMENT '执行-状态',
    `handle_msg`                text COMMENT '执行-日志',
    `alarm_status`              tinyint(4) NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
    PRIMARY KEY (`id`),
    KEY                         `I_trigger_time` (`trigger_time`),
    KEY                         `I_handle_code` (`handle_code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT,
    `trigger_day`   datetime DEFAULT NULL COMMENT '调度-时间',
    `running_count` int(11) NOT NULL DEFAULT '0' COMMENT '运行中-日志数量',
    `suc_count`     int(11) NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
    `fail_count`    int(11) NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
    `update_time`   datetime DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `job_id`      int(11) NOT NULL COMMENT '任务，主键ID',
    `glue_type`   varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
    `glue_source` mediumtext COMMENT 'GLUE源代码',
    `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
    `add_time`    datetime    DEFAULT NULL,
    `update_time` datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `registry_group` varchar(50)  NOT NULL,
    `registry_key`   varchar(255) NOT NULL,
    `registry_value` varchar(255) NOT NULL,
    `update_time`    datetime DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY              `i_g_k_v` (`registry_group`,`registry_key`,`registry_value`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `username`   varchar(50) NOT NULL COMMENT '账号',
    `password`   varchar(50) NOT NULL COMMENT '密码',
    `role`       tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
    `permission` varchar(255) DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
    PRIMARY KEY (`id`),
    UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_user`
VALUES (1, 'lamp', '263addb7611158a2c3db7c164a20cc2f', 1, NULL);
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;
