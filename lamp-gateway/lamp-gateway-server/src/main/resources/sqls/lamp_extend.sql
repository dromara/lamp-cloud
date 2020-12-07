/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 127.0.0.1:3306
 Source Schema         : lamp_extend_0000

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 25/11/2020 22:39:12
*/

SET NAMES utf8mb4;

-- ----------------------------
-- Table structure for e_block_list
-- ----------------------------
DROP TABLE IF EXISTS `e_block_list`;
CREATE TABLE `e_block_list` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `ip` varchar(20) DEFAULT '' COMMENT '阻止访问ip',
  `request_uri` varchar(255) DEFAULT '' COMMENT '请求URI',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方法 \n如果为ALL则表示对所有方法生效',
  `limit_start` varchar(8) DEFAULT '' COMMENT '限制时间起',
  `limit_end` varchar(8) DEFAULT '' COMMENT '限制时间止',
  `state` bit(1) DEFAULT b'0' COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='阻止访问';

-- ----------------------------
-- Table structure for e_rate_limiter
-- ----------------------------
DROP TABLE IF EXISTS `e_rate_limiter`;
CREATE TABLE `e_rate_limiter` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `count` int(10) DEFAULT '0' COMMENT '次数',
  `request_uri` varchar(255) DEFAULT '' COMMENT '请求URI',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方法 \n如果为ALL则表示对所有方法生效',
  `limit_start` varchar(8) DEFAULT '' COMMENT '限制时间起',
  `limit_end` varchar(8) DEFAULT '' COMMENT '限制时间止',
  `state` bit(1) DEFAULT b'0' COMMENT '状态',
  `interval_sec` bigint(20) DEFAULT '0' COMMENT '时间窗口',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='限流';

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT 'increment id',
    `branch_id`     BIGINT(20)   NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(100) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='AT transaction mode undo table';

