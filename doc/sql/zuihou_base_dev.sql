/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50709
Source Host           : 127.0.0.1:3306
Source Database       : zuihou_base_dev

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2018-01-17 09:25:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_department
-- ----------------------------
DROP TABLE IF EXISTS `base_department`;
CREATE TABLE `base_department` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `code` varchar(255) NOT NULL COMMENT '部门编码',
  `name` varchar(255) DEFAULT '' COMMENT '部门名称',
  `parent_id` bigint(11) NOT NULL COMMENT '父id',
  `path` varchar(255) DEFAULT NULL COMMENT '层级路径  顶级默认:,   [父path]父code, ',
  `is_enable` bit(1) DEFAULT b'1' COMMENT '是否启用\r\n            1：启用\r\n            0：禁用',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除 \r\n            1：已删除\r\n            0：未删除',
  `type` varchar(64) DEFAULT 'DEPA' COMMENT '类型 默认为DEPA 为以后扩展准备',
  `description` varchar(255) DEFAULT '',
  `create_user` varchar(64) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_depa_appid_code_del` (`app_id`,`code`,`is_delete`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of base_department
-- ----------------------------
INSERT INTO `base_department` VALUES ('1', 'zkhyiGC1g4eZ', 'yfb', 'zzzz', '-1', ',', '\0', '\0', 'DEPART', 'zzz', '', '2017-12-27 21:06:21', '', '2017-12-28 10:04:09');
INSERT INTO `base_department` VALUES ('2', 'zkhyiGC1g4eZ', 'yfb2', '研发2', '1', ',yfb,', '', '\0', 'DEPART', '研发2', '', '2017-12-27 21:06:37', '', '2017-12-27 21:28:41');
INSERT INTO `base_department` VALUES ('3', 'zkhyiGC1g4eZ', 'yfb3', '研发3', '2', ',yfb,yfb2,', '', '\0', 'DEPART', '研发3', '', '2017-12-27 21:12:23', '', '2017-12-27 21:28:41');
INSERT INTO `base_department` VALUES ('4', 'zkhyiGC1g4eZ', 'yfb4', '研发4', '-1', ',', '', '\0', 'DEPART', '研发4', '', '2017-12-27 21:18:47', '', '2017-12-27 21:18:47');

-- ----------------------------
-- Table structure for base_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `base_dictionary`;
CREATE TABLE `base_dictionary` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `dictionary_type_id` bigint(11) NOT NULL COMMENT '类型id',
  `dictionary_type_code` varchar(100) NOT NULL COMMENT '类型编码',
  `dictionary_type_name` varchar(255) DEFAULT NULL COMMENT '字典类型名称',
  `code` varchar(100) NOT NULL COMMENT '字典编码',
  `name` varchar(255) DEFAULT '' COMMENT '字典条目名称',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除 \r\n            1：已删除\r\n            0：未删除',
  `is_enable` bit(1) DEFAULT b'1' COMMENT '启用状态\r\n                        1：启用\r\n                        0：禁用',
  `order_num` int(11) DEFAULT '0' COMMENT '排序号',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT '' COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(64) DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_dictionary_appid_tid_code_del` (`app_id`,`dictionary_type_id`,`code`,`is_delete`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Records of base_dictionary
-- ----------------------------
INSERT INTO `base_dictionary` VALUES ('1', 'zkhyiGC1g4eZ', '1', 'd1', 'd1', 'code4', '中文额22222', '\0', '', '1', '1', '2017-12-26 14:12:01', null, '2017-12-26 14:12:01', null);
INSERT INTO `base_dictionary` VALUES ('2', 'zkhyiGC1g4eZ', '2', 'd', 'd', 'code2', '中文额111', '\0', '', '1', null, '2017-12-26 14:12:01', null, '2017-12-26 14:12:01', null);
INSERT INTO `base_dictionary` VALUES ('4', 'zkhyiGC1g4eZ', '3', 'user.area', '用户地区', 'code1', '111', '\0', '', '1', '3', '2017-12-26 14:09:29', null, '2017-12-29 10:28:28', null);

-- ----------------------------
-- Table structure for base_dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `base_dictionary_type`;
CREATE TABLE `base_dictionary_type` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `code` varchar(100) NOT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT '' COMMENT '名称',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除 \r\n            1：已删除\r\n            0：未删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT '' COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(64) DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_dt_appid_code_del` (`app_id`,`code`,`is_delete`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='数据字典类型表';

-- ----------------------------
-- Records of base_dictionary_type
-- ----------------------------
INSERT INTO `base_dictionary_type` VALUES ('2', 'zkhyiGC1g4eZ', 'd', '修改1', '修改1', '\0', '2017-12-28 10:05:55', '', '2017-12-28 10:16:37', '');
INSERT INTO `base_dictionary_type` VALUES ('3', 'zkhyiGC1g4eZ', 'user.area', '用户地区', '用户所属地区', '\0', '2017-12-28 10:06:55', '', '2017-12-28 10:06:55', '');
INSERT INTO `base_dictionary_type` VALUES ('4', 'zkhyiGC1g4eZ', 'user.type', '用户类型', '', '\0', '2017-12-28 10:09:47', '', '2017-12-28 10:09:47', '');
INSERT INTO `base_dictionary_type` VALUES ('5', 'zkhyiGC1g4eZ', 'user.type2', '用户类型', '', '\0', '2017-12-28 10:15:10', '', '2017-12-28 10:15:10', '');
INSERT INTO `base_dictionary_type` VALUES ('6', 'zkhyiGC1g4eZ', '111', '111', '111', '\0', '2017-12-29 10:25:51', '', '2017-12-29 10:25:51', '');

-- ----------------------------
-- Table structure for base_menu_group
-- ----------------------------
DROP TABLE IF EXISTS `base_menu_group`;
CREATE TABLE `base_menu_group` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='菜单组 一个应用系统，不同的位置、不同的子系统可能会存在多组菜单';

-- ----------------------------
-- Records of base_menu_group
-- ----------------------------
INSERT INTO `base_menu_group` VALUES ('1', 'zkhyiGC1g4eZ', 'LEFT', '后台管理左侧菜单', '', '\0', '', '2017-12-19 09:16:37', '', '2017-12-19 09:16:39');
INSERT INTO `base_menu_group` VALUES ('2', 'zkhyiGC1g4eZ', '111', '444', '444', '\0', null, '2017-12-19 17:56:55', null, '2017-12-19 17:58:12');
INSERT INTO `base_menu_group` VALUES ('3', 'zkhyiGC1g4eZ', 'top', 'top', 'top', '\0', null, '2017-12-29 13:58:13', null, '2017-12-29 13:58:13');

-- ----------------------------
-- Table structure for base_resources
-- ----------------------------
DROP TABLE IF EXISTS `base_resources`;
CREATE TABLE `base_resources` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `code` varchar(255) NOT NULL COMMENT '资源编码',
  `name` varchar(255) DEFAULT '' COMMENT '资源名称',
  `menu_group_code` varchar(255) DEFAULT 'DEF' COMMENT '资源类型为菜单时，对应菜单的组(base_menu_group),  当系统只存在一组菜单时，该字段可以为空',
  `parent_id` bigint(11) NOT NULL COMMENT '父菜单',
  `type` varchar(255) NOT NULL COMMENT '资源类型 menu:菜单  button:按钮 \r\n page_uri:页面上的url  \r\n api:api',
  `href` varchar(255) DEFAULT '' COMMENT '菜单url',
  `path` varchar(255) DEFAULT ',' COMMENT '层级路径  顶级默认:,   [父path]父code, ',
  `is_enable` bit(1) DEFAULT b'1' COMMENT '是否启用\r\n            1：启用\r\n            0：禁用',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除 \r\n            1：已删除\r\n            0：未删除',
  `method` varchar(10) DEFAULT 'GET' COMMENT '资源请求方式 POST/GET/PUT/DELETE',
  `targets` varchar(255) DEFAULT '_self' COMMENT '菜单打开方式 \r\n  _self：相同框架 \r\n  _top：整页 \r\n   _blank：新建一个窗口\r\n    _paren：t父窗口  ',
  `icon` varchar(255) DEFAULT '' COMMENT '菜单图标',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `create_user` varchar(64) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_appid_code_del` (`app_id`,`code`,`is_delete`) USING BTREE,
  KEY `idx_resource_appid_id` (`app_id`,`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8 COMMENT='资源 需要控制权限的资源，包括菜单，页面上的uri，对外api';

-- ----------------------------
-- Records of base_resources
-- ----------------------------
INSERT INTO `base_resources` VALUES ('100', 'zkhyiGC1g4eZ', 'openSys', '统一平台', 'LEFT', '-1', 'DIR', 'javascript:void(0);', ',', '', '\0', 'GET', '_self', '', '0', '', '', null, '', null);
INSERT INTO `base_resources` VALUES ('101', 'zkhyiGC1g4eZ', 'openBase', '基础配置', 'LEFT', '100', 'DIR', 'javascript:void(0);', ',openSys,', '', '\0', 'GET', '_self', '', '1', '', '', null, '', null);
INSERT INTO `base_resources` VALUES ('102', 'zkhyiGC1g4eZ', 'openUser', '用户管理', 'LEFT', '101', 'MENU', '', ',openSys,openBase,', '', '\0', 'GET', '_self', '', '0', '', '', null, '', null);
INSERT INTO `base_resources` VALUES ('103', 'zkhyiGC1g4eZ', 'openMenuGroup', '菜单组管理', 'LEFT', '101', 'MENU', '', ',openSys,openBase,', '', '\0', 'GET', '_self', '', '1', '', '', null, '', null);
INSERT INTO `base_resources` VALUES ('104', 'zkhyiGC1g4eZ', 'openResource', '菜单管理', 'LEFT', '101', 'MENU', '', ',openSys,openBase,', '', '\0', 'GET', '_self', '', '2', '', '', null, '', null);
INSERT INTO `base_resources` VALUES ('105', 'zkhyiGC1g4eZ', 'openRole', '角色管理', 'LEFT', '101', 'MENU', '', ',openSys,openBase,', '', '\0', 'GET', '_self', '', '3', '', '', null, '', null);
INSERT INTO `base_resources` VALUES ('106', 'zkhyiGC1g4eZ', 'openDepart', '部门管理', 'LEFT', '101', 'MENU', '', ',openSys,openBase,', '', '\0', 'GET', '_self', '', '4', '', '', null, '', null);
INSERT INTO `base_resources` VALUES ('107', 'zkhyiGC1g4eZ', 'openDIctionary', '数据字典', 'LEFT', '101', 'MENU', '', ',openSys,openBase,', '', '\0', 'GET', '_self', '', '5', '', '', null, '', null);
INSERT INTO `base_resources` VALUES ('108', 'zkhyiGC1g4eZ', 'zzz', '测试', 'LEFT', '100', 'DIR', 'javascript:void(0);', ',openSys,', '', '\0', 'GET', '_self', '', '2', '', '', null, '', null);
INSERT INTO `base_resources` VALUES ('109', 'zkhyiGC1g4eZ', 'aaa', 'aaa', 'LEFT', '108', 'DIR', '', ',openSys,zzz,', '', '\0', 'GET', '_self', '', '0', '', '', null, '', null);
INSERT INTO `base_resources` VALUES ('110', 'zkhyiGC1g4eZ', 'bbb', 'bbb', 'LEFT', '108', 'MENU', 'www', ',openSys,zzz,', '', '\0', 'GET', '_self', '', '0', '', '', null, '', null);
INSERT INTO `base_resources` VALUES ('111', 'zkhyiGC1g4eZ', 'ccc', 'ccc', 'LEFT', '109', 'DIR', 'www', ',openSys,zzz,aaa,', '', '\0', 'GET', '_self', '', '0', '', '', null, '', null);
INSERT INTO `base_resources` VALUES ('112', 'zkhyiGC1g4eZ', 'ddd', 'ddd', 'LEFT', '111', 'BUTTON', 'www', ',openSys,zzz,aaa,ccc,', '', '\0', 'GET', '_self', '', '0', '', '', null, '', null);

-- ----------------------------
-- Table structure for base_resource_authority
-- ----------------------------
DROP TABLE IF EXISTS `base_resource_authority`;
CREATE TABLE `base_resource_authority` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `authority_id` bigint(11) NOT NULL COMMENT '权限Id 角色id(base_role) 或 部门id(base_department)',
  `authority_type` varchar(255) DEFAULT '' COMMENT '权限类型 ROLE/DEPARTMENT',
  `resource_id` bigint(11) NOT NULL COMMENT '资源id base_resources表',
  `menu_group_code` varchar(255) DEFAULT 'DEF' COMMENT '菜单组',
  `create_user` varchar(64) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_auth_resource_appid_aid_rid` (`app_id`,`authority_id`,`resource_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='资源(base_resources)对应的权限(base_role/base_department)';

-- ----------------------------
-- Records of base_resource_authority
-- ----------------------------
INSERT INTO `base_resource_authority` VALUES ('3', 'zkhyiGC1g4eZ', '1', 'ROLE', '112', 'LEFT', null, '2017-12-29 11:18:45', null, '2017-12-29 11:18:45');
INSERT INTO `base_resource_authority` VALUES ('4', 'zkhyiGC1g4eZ', '1', 'ROLE', '100', 'LEFT', null, '2017-12-29 11:18:45', null, '2017-12-29 11:18:45');
INSERT INTO `base_resource_authority` VALUES ('5', 'zkhyiGC1g4eZ', '1', 'ROLE', '108', 'LEFT', null, '2017-12-29 11:18:45', null, '2017-12-29 11:18:45');
INSERT INTO `base_resource_authority` VALUES ('6', 'zkhyiGC1g4eZ', '1', 'ROLE', '109', 'LEFT', null, '2017-12-29 11:18:45', null, '2017-12-29 11:18:45');
INSERT INTO `base_resource_authority` VALUES ('7', 'zkhyiGC1g4eZ', '1', 'ROLE', '111', 'LEFT', null, '2017-12-29 11:18:45', null, '2017-12-29 11:18:45');

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `code` varchar(255) NOT NULL COMMENT '角色编码',
  `name` varchar(255) DEFAULT '' COMMENT '角色名称',
  `is_enable` bit(1) DEFAULT b'1' COMMENT '是否启用\r\n            1：启用\r\n            0：禁用',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除 \r\n            1：已删除\r\n            0：未删除',
  `type` varchar(64) DEFAULT 'ROLE' COMMENT '类型 默认为ROLE 为以后扩展准备',
  `description` varchar(255) DEFAULT '',
  `create_user` varchar(64) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_appid_code_del` (`app_id`,`code`,`is_delete`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES ('1', 'zkhyiGC1g4eZ', 'test1', '修改1', '', '\0', 'ROLE', '修改', null, '2017-12-21 10:45:32', null, '2017-12-21 10:50:00');
INSERT INTO `base_role` VALUES ('2', 'zkhyiGC1g4eZ', 'test2', '角色1', '', '\0', 'ROLE', 'test1', null, '2017-12-21 10:46:55', null, '2017-12-21 10:46:55');
INSERT INTO `base_role` VALUES ('3', 'zkhyiGC1g4eZ', 'test3', '角色1', '', '\0', 'ROLE', 'test1', null, '2017-12-21 10:47:31', null, '2017-12-21 10:47:31');
INSERT INTO `base_role` VALUES ('4', 'zkhyiGC1g4eZ', 'test4', '角色4', '', '\0', 'ROLE', 'test4', null, '2017-12-21 13:47:30', null, '2017-12-21 13:47:30');
INSERT INTO `base_role` VALUES ('5', 'zkhyiGC1g4eZ', '1', '2', '', '\0', 'ROLE', '2', null, '2017-12-29 10:36:04', null, '2017-12-29 10:36:26');

-- ----------------------------
-- Table structure for base_role_user
-- ----------------------------
DROP TABLE IF EXISTS `base_role_user`;
CREATE TABLE `base_role_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `role_id` bigint(11) NOT NULL COMMENT '角色Id (base_role) ',
  `user_id` bigint(11) NOT NULL COMMENT '用户id base_user表',
  `create_user` varchar(64) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_ru_aip_rid_uid` (`app_id`,`role_id`,`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of base_role_user
-- ----------------------------
INSERT INTO `base_role_user` VALUES ('2', 'zkhyiGC1g4eZ', '1', '1', null, '2017-12-29 10:40:24', null, '2017-12-29 10:40:24');
INSERT INTO `base_role_user` VALUES ('3', 'zkhyiGC1g4eZ', '2', '1', null, '2017-12-29 10:40:24', null, '2017-12-29 10:40:24');
INSERT INTO `base_role_user` VALUES ('4', 'zkhyiGC1g4eZ', '1', '2', null, '2017-12-29 10:40:24', null, '2017-12-29 10:40:24');

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(64) NOT NULL COMMENT '开发者ID，提供给各个应用的设别码',
  `username` varchar(64) NOT NULL COMMENT '帐号',
  `password` varchar(64) NOT NULL COMMENT '登录密码',
  `name` varchar(64) NOT NULL COMMENT '姓名',
  `email` varchar(64) DEFAULT '' COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT '' COMMENT '手机',
  `logo_url` varchar(255) DEFAULT '' COMMENT '头像',
  `is_enable` bit(1) DEFAULT b'1' COMMENT '是否禁用 \r\n            1：启用\r\n            0：禁用',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除 \r\n            1：已删除\r\n            0：未删除',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `create_user` varchar(64) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT '',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_appid_name_del` (`app_id`,`username`,`is_delete`) USING BTREE,
  KEY `idx_user_appid_name_pwd` (`app_id`,`username`,`password`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户管理';

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES ('1', 'zkhyiGC1g4eZ', 'test', '$2a$12$JEEjdBQDoQWQIH57kQsaturh9pQBZILmvDijPy76lu3pY5YDIjLH.', 'string', 'string', 'string', 'string', '\0', '\0', 'string', '', '2017-12-29 10:31:28', '', '2017-12-29 10:34:27');
INSERT INTO `base_user` VALUES ('2', 'zkhyiGC1g4eZ', 'test1', '$2a$12$lsuSUKcXtK1G.2nCO5155.CQaO.xAv8raufShwx2r1CI2hp2Sutia', 'string', 'string', 'string', 'string', '\0', '\0', 'string', '', '2017-12-29 10:33:15', '', '2017-12-29 10:34:46');
