/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50709
Source Host           : 127.0.0.1:3306
Source Database       : zuihou_a_dev

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2018-01-17 09:24:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for a_admin
-- ----------------------------
DROP TABLE IF EXISTS `a_admin`;
CREATE TABLE `a_admin` (
  `id` bigint(11) NOT NULL,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `type` int(11) DEFAULT '1' COMMENT '帐号类型： 0:超级管理 1:普通管理',
  `username` varchar(64) NOT NULL COMMENT '帐号',
  `password` varchar(64) NOT NULL COMMENT '登录密码',
  `name` varchar(255) DEFAULT '' COMMENT '昵称',
  `email` varchar(64) DEFAULT '' COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT '' COMMENT '手机',
  `logo_url` varchar(255) DEFAULT '' COMMENT '头像',
  `is_enable` bit(1) DEFAULT b'1' COMMENT '是否禁用 \r\n  1：启用\r\n    0：禁用',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除 \r\n  1：已删除\r\n   0：未删除',
  `description` varchar(255) DEFAULT '',
  `create_user` varchar(64) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_a_username` (`username`) USING BTREE,
  KEY `idx_a_name_pwd` (`username`,`password`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台登录管理帐号';

-- ----------------------------
-- Records of a_admin
-- ----------------------------
INSERT INTO `a_admin` VALUES ('1', '10000', '0', 'admin', '$2a$12$S/yLlj9kzi5Dgsz97H4rAekxrPlk/10eXp1lUJcAVAx.2M9tOpWie', 'ttt', 'test@qq.com', '10086', '', '', '\0', '超级系统管理员', '', '2017-12-14 23:18:54', '', '2017-12-14 23:18:57');
INSERT INTO `a_admin` VALUES ('2', '10000', '1', 'test', '$2a$12$zWe6knO6rGp15UVfdWTTxu.Ykt.k3QnD5FPoj6a1cnL63csHY2A1S', 'aaa', 'test@qq.com', '10086', '', '', '\0', '系统子帐号', '', '2017-12-14 23:18:54', '', '2017-12-14 23:18:54');

-- ----------------------------
-- Table structure for a_applications
-- ----------------------------
DROP TABLE IF EXISTS `a_applications`;
CREATE TABLE `a_applications` (
  `id` bigint(11) NOT NULL,
  `app_name` varchar(64) NOT NULL COMMENT '应用名称',
  `app_type` varchar(64) DEFAULT 'app' COMMENT '应用类型  system:系统管理 app:应用',
  `is_enable` bit(1) DEFAULT b'1' COMMENT '启用状态 \r\n   1：启用\r\n    0：禁用',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除 \r\n   1：已删除\r\n  0：未删除',
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `app_secret` varchar(64) NOT NULL COMMENT '开发者密码是校验开发者身份的密码，具有极高的安全性',
  `url` varchar(64) DEFAULT '' COMMENT '应用url',
  `logo_url` varchar(64) DEFAULT '' COMMENT '应用logo',
  `comment` varchar(255) DEFAULT '' COMMENT '备注',
  `order_num` int(11) DEFAULT '0' COMMENT '排序字段',
  `create_user` varchar(64) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_application_app_id` (`app_id`) USING BTREE,
  KEY `idx_app_id_app_secret` (`app_id`,`app_secret`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用表 存储12大子应用';

-- ----------------------------
-- Records of a_applications
-- ----------------------------
INSERT INTO `a_applications` VALUES ('1', '统一平台', 'SYSTEM', '', '\0', '10000', '10000', '', '', '统一平台系统帐号', '0', '', '2017-12-14 23:18:14', '', '2017-12-14 23:18:16');
INSERT INTO `a_applications` VALUES ('2', '测试注册', 'APP', '', '\0', 'zkhyiGC1g4eZ', '9ea1c4282f2d4cf6b756516cda6c80f3', null, null, null, null, 'post', '2017-12-16 21:25:10', 'post', '2017-12-16 21:25:10');

-- ----------------------------
-- Table structure for a_application_role
-- ----------------------------
DROP TABLE IF EXISTS `a_application_role`;
CREATE TABLE `a_application_role` (
  `id` bigint(11) NOT NULL,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `role_id` bigint(11) NOT NULL COMMENT '权限Id 角色id(a_role) ',
  `applications_id` bigint(11) NOT NULL COMMENT 'app 主键 id a_applications表',
  `create_user` varchar(64) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_app_role_appid_rid_aid` (`app_id`,`role_id`,`applications_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用(a_applications)对应的角色(a_role)';

-- ----------------------------
-- Records of a_application_role
-- ----------------------------
INSERT INTO `a_application_role` VALUES ('1', '10000', '1', '1', '', null, null, '');
INSERT INTO `a_application_role` VALUES ('2', '10000', '2', '2', '', null, null, '');
INSERT INTO `a_application_role` VALUES ('3', '10000', '3', '1', '', null, null, '');

-- ----------------------------
-- Table structure for a_menu_group
-- ----------------------------
DROP TABLE IF EXISTS `a_menu_group`;
CREATE TABLE `a_menu_group` (
  `id` bigint(11) NOT NULL,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `code` varchar(255) NOT NULL COMMENT '菜单类型编码',
  `name` varchar(255) DEFAULT '' COMMENT '菜单类型名称',
  `description` varchar(255) DEFAULT '' COMMENT '菜单类型描述',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除 \r\n            1：已删除\r\n            0：未删除',
  `create_user` varchar(64) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_menu_group_appid_code_del` (`app_id`,`code`,`is_delete`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单组 一个应用系统，不同的位置、不同的子系统可能会存在多组菜单';

-- ----------------------------
-- Records of a_menu_group
-- ----------------------------
INSERT INTO `a_menu_group` VALUES ('1', '10000', 'SYSTEM_LEFT', '超级管理系统左侧菜单', '', '\0', '', null, '', null);
INSERT INTO `a_menu_group` VALUES ('2', '10000', 'APP_LEFT', '应用管理左侧菜单', '', '\0', '', null, '', null);

-- ----------------------------
-- Table structure for a_resources
-- ----------------------------
DROP TABLE IF EXISTS `a_resources`;
CREATE TABLE `a_resources` (
  `id` bigint(11) NOT NULL,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `code` varchar(255) NOT NULL COMMENT '资源编码',
  `name` varchar(255) DEFAULT '' COMMENT '资源名称',
  `menu_group_code` varchar(255) DEFAULT '' COMMENT '资源类型为菜单时，对应菜单的组,  当系统只存在一组菜单时，该字段可以为空',
  `parent_id` bigint(11) NOT NULL COMMENT '父菜单',
  `type` varchar(255) NOT NULL COMMENT '资源类型 MENU:菜单 DIR:目录 \r\n  BUTTON:按钮 URI:页面上的url  \r\n API:api',
  `href` varchar(255) DEFAULT '' COMMENT '菜单url',
  `method` varchar(10) DEFAULT 'GET' COMMENT '资源请求方式 POST/GET/PUT/DELETE',
  `targets` varchar(255) DEFAULT '_self' COMMENT '菜单打开方式 \r\n  _self：相同框架 \r\n  _top：整页 \r\n   _blank：新建一个窗口\r\n    _paren：t父窗口  ',
  `icon` varchar(255) DEFAULT '' COMMENT '菜单图标',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `path` varchar(2000) DEFAULT ',' COMMENT '层级路径  顶级默认:,   [父path]父code, ',
  `is_enable` bit(1) DEFAULT b'1' COMMENT '是否启用\r\n            1：启用\r\n            0：禁用',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除 \r\n            1：已删除\r\n            0：未删除',
  `create_user` varchar(64) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_appid_code` (`app_id`,`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源 需要控制权限的资源，包括菜单，页面上的uri，对外api';

-- ----------------------------
-- Records of a_resources
-- ----------------------------
INSERT INTO `a_resources` VALUES ('1', '10000', 'adminSys', '统一平台', 'SYSTEM_LEFT', '-1', 'DIR', 'javascript:void(0);', 'GET', '_self', 'fa fa-cog fa-spin', '0', '', ',', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('2', '10000', 'adminBase', '基础配置', 'SYSTEM_LEFT', '1', 'DIR', 'javascript:void(0);', 'GET', '_self', 'fa fa-cog fa-spin', '0', '', ',2,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('3', '10000', 'adminAdmin', '帐号管理', 'SYSTEM_LEFT', '2', 'MENU', null, 'GET', '_self', 'fa fa-user', '0', '', ',2,3,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('4', '10000', 'adminApplication', '应用管理', 'SYSTEM_LEFT', '2', 'MENU', null, 'GET', '_self', '', '0', '', ',2,4,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('5', '10000', 'adminMenu', '菜单管理', 'SYSTEM_LEFT', '2', 'MENU', null, 'GET', '_self', '', '0', '', ',2,5,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('6', '10000', 'adminRole', '角色管理', 'SYSTEM_LEFT', '2', 'MENU', null, 'GET', '_self', '', '0', '', ',2,6,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('7', '10000', 'adminApi', 'OPEN API管理', 'SYSTEM_LEFT', '2', 'MENU', null, 'GET', '_self', '', '0', '', ',2,7,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('8', '10000', 'adminLog', '系统日志', 'SYSTEM_LEFT', '2', 'MENU', null, 'GET', '_self', '', '0', '', ',2,8,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('20', '10000', 'adminServiceApi', '服务Api文档', 'SYSTEM_LEFT', '1', 'DIR', 'javascript:void(0);', 'GET', '_self', '', '0', '', ',20,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('21', '10000', 'adminAdminApi', 'Admin API', 'SYSTEM_LEFT', '20', 'MENU', '', 'GET', '_self', '', '0', '', ',20,21,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('22', '10000', 'adminOpenApi', 'Open API', 'SYSTEM_LEFT', '20', 'MENU', '', 'GET', '_self', '', '0', '', ',20,22,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('30', '10000', 'adminGateWay', '网关管理', 'SYSTEM_LEFT', '1', 'DIR', 'javascript:void(0);', 'GET', '_self', '', '0', '', ',30,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('31', '10000', 'adminClient', '微服务客户端管理', 'SYSTEM_LEFT', '30', 'MENU', '', 'GET', '_self', '', '0', '', ',30,31,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('40', '10000', 'adminMonitor', '系统监控', 'SYSTEM_LEFT', '1', 'DIR', 'javascript:void(0);', 'GET', '_self', '', '0', '', ',40,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('41', '10000', 'adminAdminDruid', 'Admin Druid数据监控', 'SYSTEM_LEFT', '40', 'MENU', null, 'GET', '_self', '', '0', '', ',40,41,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('42', '10000', 'adminOpenDruid', 'Open Druid数据监控', 'SYSTEM_LEFT', '40', 'MENU', '', 'GET', '_self', '', '0', '', ',40,42,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('100', '10000', 'openSys', '统一平台', 'APP_LEFT', '-1', 'DIR', 'javascript:void(0);', 'GET', '_self', '', '0', '', ',', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('101', '10000', 'openBase', '基础配置', 'APP_LEFT', '100', 'DIR', 'javascript:void(0);', 'GET', '_self', '', '0', '', ',101,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('102', '10000', 'openUser', '用户管理', 'APP_LEFT', '101', 'MENU', '', 'GET', '_self', '', '0', '', ',101,102,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('103', '10000', 'openMenuGroup', '菜单组管理', 'APP_LEFT', '101', 'MENU', '', 'GET', '_self', '', '0', '', ',101,103,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('104', '10000', 'openResource', '菜单管理', 'APP_LEFT', '101', 'MENU', '', 'GET', '_self', '', '0', '', ',101,104,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('105', '10000', 'openRole', '角色管理', 'APP_LEFT', '101', 'MENU', '', 'GET', '_self', '', '0', '', ',101,105,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('106', '10000', 'openDepart', '部门管理', 'APP_LEFT', '101', 'MENU', '', 'GET', '_self', '', '0', '', ',101,106,', '', '\0', '', null, '', null);
INSERT INTO `a_resources` VALUES ('107', '10000', 'openDIctionary', '数据字典', 'APP_LEFT', '101', 'MENU', '', 'GET', '_self', '', '0', '', ',101,107,', '', '\0', '', null, '', null);

-- ----------------------------
-- Table structure for a_role
-- ----------------------------
DROP TABLE IF EXISTS `a_role`;
CREATE TABLE `a_role` (
  `id` bigint(11) NOT NULL,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `code` varchar(255) NOT NULL COMMENT '角色编码',
  `name` varchar(255) DEFAULT '' COMMENT '角色名称',
  `is_enable` bit(1) DEFAULT b'1' COMMENT '是否启用\r\n            1：启用\r\n            0：禁用',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除 \r\n            1：已删除\r\n            0：未删除',
  `description` varchar(255) DEFAULT '',
  `create_user` varchar(64) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_appid_code` (`app_id`,`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of a_role
-- ----------------------------
INSERT INTO `a_role` VALUES ('1', '10000', 'SYSTEM', '统一平台系统管理员', '', '\0', '统一平台系统管理角色', '', null, '', null);
INSERT INTO `a_role` VALUES ('2', '10000', 'APP', '第三方应用管理员', '', '\0', '', '', null, '', null);
INSERT INTO `a_role` VALUES ('3', '10000', 'SYSTEM_1', 'test', '', '\0', '', '', null, '', null);

-- ----------------------------
-- Table structure for a_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `a_role_resource`;
CREATE TABLE `a_role_resource` (
  `id` bigint(11) NOT NULL,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `role_id` bigint(11) NOT NULL COMMENT '权限Id 角色id(a_role) ',
  `resource_id` bigint(11) NOT NULL COMMENT '资源id a_resources表',
  `create_user` varchar(64) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_resource_appid_rid_reid` (`app_id`,`role_id`,`resource_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色(a_role)对应的资源(a_resources)';

-- ----------------------------
-- Records of a_role_resource
-- ----------------------------
INSERT INTO `a_role_resource` VALUES ('1', '10000', '1', '1', '', null, '', null);
INSERT INTO `a_role_resource` VALUES ('2', '10000', '1', '2', '', null, '', null);
INSERT INTO `a_role_resource` VALUES ('3', '10000', '1', '3', '', null, '', null);
INSERT INTO `a_role_resource` VALUES ('4', '10000', '2', '100', '', null, '', null);
INSERT INTO `a_role_resource` VALUES ('5', '10000', '2', '101', '', null, '', null);
INSERT INTO `a_role_resource` VALUES ('6', '10000', '2', '102', '', null, '', null);
INSERT INTO `a_role_resource` VALUES ('7', '10000', '3', '3', '', null, '', null);
INSERT INTO `a_role_resource` VALUES ('8', '10000', '3', '4', '', null, '', null);
