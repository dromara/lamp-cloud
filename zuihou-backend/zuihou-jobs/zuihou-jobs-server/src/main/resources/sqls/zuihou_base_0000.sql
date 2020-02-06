/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 127.0.0.1:3306
 Source Schema         : zuihou_base_0000

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 06/02/2020 16:47:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_auth_application
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_application`;
CREATE TABLE `c_auth_application` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `app_key` varchar(24) DEFAULT NULL COMMENT 'AppKey',
  `app_secret` varchar(32) DEFAULT NULL COMMENT 'AppSecret',
  `website` varchar(100) DEFAULT '' COMMENT '官网',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '应用名称',
  `icon` varchar(255) DEFAULT '' COMMENT '应用图标',
  `app_type` varchar(10) DEFAULT NULL COMMENT '类型\n#{SERVER:服务应用;APP:手机应用;PC:PC网页应用;WAP:手机网页应用}\n',
  `describe_` varchar(200) DEFAULT '' COMMENT '备注',
  `status` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `UN_APP_KEY` (`app_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用';

-- ----------------------------
-- Records of c_auth_application
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_application` VALUES (1, 'asdfghjkl', 'asdfghjkl', 'http://127.0.0.1/zuihou-center', '系统综合平台', '', 'SERVER', '', b'1', 1, '2019-09-17 10:08:09', 1, '2019-09-17 10:08:22');
INSERT INTO `c_auth_application` VALUES (2, 'asdfghjk2', 'qwertyuiop', 'http:/127.0.0.1/zuihou/authority', '权限管理系统', '', 'APP', '', b'1', 1, '2018-04-13 19:12:09', 1, '2018-04-13 19:12:09');
INSERT INTO `c_auth_application` VALUES (3, 'asdfghjk3', 'zxcvbnm', 'http://127.0.0.1/zuihou/file', '文件管理系统', '', 'PC', '', b'1', 1, '2018-04-13 09:33:12', 1, '2018-05-09 10:15:05');
INSERT INTO `c_auth_application` VALUES (4, 'asdfghjk4', 'asfdghnbvcxz', 'http://127.0.0.1/zuihou/msgs', '消息管理系统', '', 'WAP', '', b'1', 1, '2018-04-13 09:33:12', 1, '2018-04-13 09:33:12');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_application_system_api
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_application_system_api`;
CREATE TABLE `c_auth_application_system_api` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `application_id` bigint(20) DEFAULT NULL COMMENT '应用id',
  `system_api_id` bigint(20) DEFAULT NULL COMMENT '资源id',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用接口';

-- ----------------------------
-- Table structure for c_auth_menu
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_menu`;
CREATE TABLE `c_auth_menu` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `describe_` varchar(200) DEFAULT '' COMMENT '功能描述',
  `is_public` bit(1) DEFAULT b'0' COMMENT '是否公开菜单\r\n就是无需分配就可以访问的。所有人可见',
  `path` varchar(255) DEFAULT '' COMMENT '对应路由path',
  `component` varchar(255) DEFAULT NULL COMMENT '对应路由组件component',
  `is_enable` bit(1) DEFAULT b'1' COMMENT '状态',
  `sort_value` int(11) DEFAULT '1' COMMENT '排序',
  `icon` varchar(255) DEFAULT '' COMMENT '菜单图标',
  `group_` varchar(20) DEFAULT '' COMMENT '菜单分组',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父级菜单id',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `INX_STATUS` (`is_enable`,`is_public`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单';

-- ----------------------------
-- Records of c_auth_menu
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_menu` VALUES (101, '用户中心', '用户组织机构', b'0', '/user', 'Layout', b'1', 0, 'el-icon-user-solid', '', 0, 1, '2019-07-25 15:35:12', 3, '2019-11-11 14:32:02');
INSERT INTO `c_auth_menu` VALUES (102, '权限管理', '管理权限相关', b'0', '/auth', 'Layout', b'1', 1, 'el-icon-lock', '', 0, 1, '2019-07-27 11:48:49', 3, '2019-11-11 14:35:39');
INSERT INTO `c_auth_menu` VALUES (103, '基础配置', '基础的配置', b'0', '/base', 'Layout', b'1', 2, 'el-icon-set-up', '', 0, 1, '2019-11-11 14:38:29', 3, '2019-11-11 14:35:42');
INSERT INTO `c_auth_menu` VALUES (104, '开发者管理', '开发者', b'0', '/developer', 'Layout', b'1', 3, 'el-icon-user-solid', '', 0, 1, '2019-11-11 14:38:34', 3, '2019-11-11 14:35:44');
INSERT INTO `c_auth_menu` VALUES (105, '消息中心', '站内信', b'0', '/msgs', 'Layout', b'1', 4, 'el-icon-chat-line-square', '', 0, 1, '2019-11-11 14:38:32', 3, '2019-11-11 14:35:47');
INSERT INTO `c_auth_menu` VALUES (106, '短信中心', '短信接口', b'0', '/sms', 'Layout', b'1', 5, 'el-icon-chat-line-round', '', 0, 1, '2019-11-11 14:38:36', 3, '2019-11-11 14:35:49');
INSERT INTO `c_auth_menu` VALUES (107, '文件中心', '附件接口', b'0', '/file', 'Layout', b'1', 6, 'el-icon-folder-add', '', 0, 1, '2019-11-11 14:38:38', 3, '2019-11-11 14:35:51');
INSERT INTO `c_auth_menu` VALUES (603976297063910529, '菜单配置', '', b'0', '/auth/menu', 'zuihou/auth/menu/Index', b'1', 0, '', '', 102, 1, '2019-07-25 15:46:11', 3, '2019-11-11 14:31:52');
INSERT INTO `c_auth_menu` VALUES (603981723864141121, '角色管理', '', b'0', '/auth/role', 'zuihou/auth/role/Index', b'1', 1, '', '', 102, 1, '2019-07-25 16:07:45', 3, '2019-11-11 14:31:57');
INSERT INTO `c_auth_menu` VALUES (603982542332235201, '组织管理', '', b'0', '/user/org', 'zuihou/user/org/Index', b'1', 0, '', '', 101, 1, '2019-07-25 16:11:00', 3, '2019-11-11 14:28:40');
INSERT INTO `c_auth_menu` VALUES (603982713849908801, '岗位管理', '', b'0', '/user/station', 'zuihou/user/station/Index', b'1', 1, '', '', 101, 1, '2019-07-25 16:11:41', 3, '2019-11-11 14:28:43');
INSERT INTO `c_auth_menu` VALUES (603983082961243905, '用户管理', '', b'0', '/user/user', 'zuihou/user/user/Index', b'1', 2, '', '', 101, 1, '2019-07-25 16:13:09', 3, '2019-11-11 14:28:49');
INSERT INTO `c_auth_menu` VALUES (605078371293987105, '数据字典维护', '', b'0', '/base/dict', 'zuihou/base/dict/Index', b'1', 0, '', '', 103, 1, '2019-07-28 16:45:26', 3, '2019-11-11 14:34:23');
INSERT INTO `c_auth_menu` VALUES (605078463069552993, '地区信息维护', '', b'0', '/base/area', 'zuihou/base/area/Index', b'1', 1, '', '', 103, 1, '2019-07-28 16:45:48', 3, '2019-11-11 14:34:26');
INSERT INTO `c_auth_menu` VALUES (605078538881597857, '应用管理', '', b'0', '/developer/application', 'zuihou/developer/application/Index', b'1', 0, '', '', 104, 1, '2019-07-28 16:46:06', 3, '2019-12-25 16:19:43');
INSERT INTO `c_auth_menu` VALUES (605078672772170209, '操作日志', '', b'0', '/developer/optLog', 'zuihou/developer/optLog/Index', b'1', 3, '', '', 104, 1, '2019-07-28 16:46:38', 3, '2019-11-11 14:35:14');
INSERT INTO `c_auth_menu` VALUES (605078979149300257, '数据库监控', '', b'0', '/developer/db', 'zuihou/developer/db/Index', b'1', 2, '', '', 104, 1, '2019-07-28 16:47:51', 3, '2019-11-16 16:35:50');
INSERT INTO `c_auth_menu` VALUES (605079239015793249, '接口文档', '', b'0', 'http://tangyh.top:10000/api/gate/doc.html', 'Layout', b'1', 5, '', '', 104, 1, '2019-07-28 16:48:53', 3, '2019-11-16 10:55:03');
INSERT INTO `c_auth_menu` VALUES (605079411338773153, '注册&配置中心', '', b'0', 'http://tangyh.top:10000/nacos', 'Layout', b'1', 6, '', '', 104, 1, '2019-07-28 16:49:34', 3, '2019-11-16 10:55:06');
INSERT INTO `c_auth_menu` VALUES (605079545585861345, '缓存监控', '', b'0', 'http://www.baidu.com', 'Layout', b'1', 7, '', '', 104, 1, '2019-07-28 16:50:06', 3, '2019-11-16 10:55:08');
INSERT INTO `c_auth_menu` VALUES (605079658416833313, '服务器监控', '', b'0', 'http://127.0.0.1:8762/zuihou-monitor', 'Layout', b'1', 8, '', '', 104, 1, '2019-07-28 16:50:33', 3, '2019-11-16 10:55:15');
INSERT INTO `c_auth_menu` VALUES (605079751035454305, '消息推送', '', b'0', '/msgs/sendMsgs', 'zuihou/msgs/sendMsgs/Index', b'1', 0, '', '', 105, 1, '2019-07-28 16:50:55', 3, '2019-11-11 14:28:30');
INSERT INTO `c_auth_menu` VALUES (605080023753294753, '我的消息', '', b'0', '/msgs/myMsgs', 'zuihou/msgs/myMsgs/Index', b'1', 1, '', '', 105, 1, '2019-07-28 16:52:00', 3, '2019-11-11 14:28:27');
INSERT INTO `c_auth_menu` VALUES (605080107379327969, '账号配置', '', b'0', '/sms/template', 'zuihou/sms/template/Index', b'1', 1, '', '', 106, 1, '2019-07-28 16:52:20', 3, '2019-11-21 19:53:17');
INSERT INTO `c_auth_menu` VALUES (605080359394083937, '短信管理', '', b'0', '/sms/manage', 'zuihou/sms/manage/Index', b'1', 0, '', '', 106, 1, '2019-07-28 16:53:20', 3, '2019-11-21 19:53:09');
INSERT INTO `c_auth_menu` VALUES (605080648767505601, '附件列表', '', b'0', '/file/attachment', 'zuihou/file/attachment/Index', b'1', 0, '', '', 107, 1, '2019-07-28 16:54:29', 3, '2019-11-11 14:28:07');
INSERT INTO `c_auth_menu` VALUES (605080816296396097, '定时调度中心', '', b'0', 'http://42.202.130.216:10000/zuihou-jobs-server', 'Layout', b'1', 9, '', '', 104, 1, '2019-07-28 16:55:09', 3, '2019-11-16 10:55:18');
INSERT INTO `c_auth_menu` VALUES (605424535633666945, '接口查询', '', b'0', '/developer/systemApi', 'zuihou/developer/systemApi/Index', b'1', 1, '', '', 104, 1, '2019-07-29 15:40:58', 3, '2019-12-24 14:40:47');
INSERT INTO `c_auth_menu` VALUES (643464272629728001, '务必详看', '', b'1', '/doc', 'zuihou/doc/Index', b'1', 0, 'el-icon-notebook-1', '', 0, 3, '2019-11-11 14:57:18', 3, '2019-11-11 15:01:31');
INSERT INTO `c_auth_menu` VALUES (643464392888812545, '后端代码', '', b'1', 'https://github.com/zuihou/zuihou-admin-cloud', 'Layout', b'1', 1, '', '', 643464272629728001, 3, '2019-11-11 14:57:46', 3, '2019-11-11 15:00:05');
INSERT INTO `c_auth_menu` VALUES (643464517879071841, '租户平台-前端代码', '', b'1', 'https://github.com/zuihou/zuihou-ui', 'Layout', b'1', 2, '', '', 643464272629728001, 3, '2019-11-11 14:58:16', 3, '2019-11-11 15:00:09');
INSERT INTO `c_auth_menu` VALUES (643464706228487361, '运营平台-前端代码', '', b'1', 'https://github.com/zuihou/zuihou-admin-ui', 'Layout', b'1', 3, '', '', 643464272629728001, 3, '2019-11-11 14:59:01', 3, '2019-11-11 15:00:11');
INSERT INTO `c_auth_menu` VALUES (643464953478514081, '在线文档', '', b'1', 'https://www.kancloud.cn/zuihou/zuihou-admin-cloud', 'Layout', b'1', 0, '', '', 643464272629728001, 3, '2019-11-11 15:00:00', 3, '2019-11-11 15:01:36');
INSERT INTO `c_auth_menu` VALUES (643874916004790785, '运营平台演示地址', NULL, b'1', 'http://42.202.130.216:10000/zuihou-admin-ui', 'Layout', b'1', 4, NULL, NULL, 643464272629728001, 3, '2019-11-12 18:09:03', 641577229343523041, '2019-12-04 16:20:13');
INSERT INTO `c_auth_menu` VALUES (644111530555611361, '链路调用监控', '', b'0', 'http://tangyh.top:10000/zipkin/', 'Layout', b'1', 10, '', '', 104, 3, '2019-11-13 09:49:16', 3, '2019-11-13 09:56:51');
INSERT INTO `c_auth_menu` VALUES (645215230518909025, '登录日志', '', b'0', '/developer/loginLog', 'zuihou/developer/loginLog/Index', b'1', 4, '', '', 104, 3, '2019-11-16 10:54:59', 3, '2019-11-16 10:54:59');
INSERT INTO `c_auth_menu` VALUES (1225042542827929600, '参数配置', '', b'0', '/base/parameter', 'zuihou/base/parameter/Index', b'1', 3, '', '', 103, 3, '2020-02-05 21:04:37', 3, '2020-02-05 21:04:37');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_resource
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_resource`;
CREATE TABLE `c_auth_resource` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `code` varchar(255) DEFAULT '' COMMENT '资源编码\n规则：\n链接：\n数据列：\n按钮：',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '接口名称',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID\n#c_auth_menu',
  `describe_` varchar(255) DEFAULT '' COMMENT '接口描述',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_CODE` (`code`) COMMENT '编码唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源';

-- ----------------------------
-- Records of c_auth_resource
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_resource` VALUES (643444685339100193, 'org:add', '添加', 603982542332235201, '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (643444685339100194, 'role:config', '配置', 603981723864141121, '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (643444685339100195, 'resource:add', '添加', 603976297063910529, '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (643444685339100196, 'resource:update', '修改', 603976297063910529, '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (643444685339100197, 'resource:delete', '删除', 603976297063910529, '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (643444819758154945, 'org:update', '修改', 603982542332235201, '', 3, '2019-11-11 13:40:00', 3, '2019-11-11 13:40:00');
INSERT INTO `c_auth_resource` VALUES (643444858974897441, 'org:delete', '删除', 603982542332235201, '', 3, '2019-11-11 13:40:09', 3, '2019-11-11 13:40:09');
INSERT INTO `c_auth_resource` VALUES (643444897201784193, 'org:view', '查询', 603982542332235201, '', 3, '2019-11-11 13:40:18', 3, '2019-11-11 13:40:18');
INSERT INTO `c_auth_resource` VALUES (643444992357959137, 'org:import', '导入', 603982542332235201, '', 3, '2019-11-11 13:40:41', 3, '2019-11-11 13:40:41');
INSERT INTO `c_auth_resource` VALUES (643445016773002817, 'org:export', '导出', 603982542332235201, '', 3, '2019-11-11 13:40:47', 3, '2019-11-11 13:40:47');
INSERT INTO `c_auth_resource` VALUES (643445116756821697, 'station:add', '添加', 603982713849908801, '', 3, '2019-11-11 13:41:11', 3, '2019-11-11 13:41:11');
INSERT INTO `c_auth_resource` VALUES (643445162915137313, 'station:update', '修改', 603982713849908801, '', 3, '2019-11-11 13:41:22', 3, '2019-11-11 13:41:22');
INSERT INTO `c_auth_resource` VALUES (643445197954353025, 'station:delete', '删除', 603982713849908801, '', 3, '2019-11-11 13:41:30', 3, '2019-11-11 13:41:30');
INSERT INTO `c_auth_resource` VALUES (643445229575210977, 'station:view', '查看', 603982713849908801, '', 3, '2019-11-11 13:41:38', 3, '2019-11-11 13:41:38');
INSERT INTO `c_auth_resource` VALUES (643445262110427201, 'station:export', '导出', 603982713849908801, '', 3, '2019-11-11 13:41:45', 3, '2019-11-11 13:41:45');
INSERT INTO `c_auth_resource` VALUES (643445283996305569, 'station:import', '导入', 603982713849908801, '', 3, '2019-11-11 13:41:51', 3, '2019-11-11 13:41:51');
INSERT INTO `c_auth_resource` VALUES (643445352703199521, 'user:add', '添加', 603983082961243905, '', 3, '2019-11-11 13:42:07', 3, '2019-11-11 13:42:07');
INSERT INTO `c_auth_resource` VALUES (643445412774021505, 'user:update', '修改', 603983082961243905, '', 3, '2019-11-11 13:42:21', 3, '2019-11-11 13:42:21');
INSERT INTO `c_auth_resource` VALUES (643445448081672673, 'user:delete', '删除', 603983082961243905, '', 3, '2019-11-11 13:42:30', 3, '2019-11-11 13:42:30');
INSERT INTO `c_auth_resource` VALUES (643445477274028609, 'user:view', '查看', 603983082961243905, '', 3, '2019-11-11 13:42:37', 3, '2019-11-11 13:42:37');
INSERT INTO `c_auth_resource` VALUES (643445514607528609, 'user:import', '导入', 603983082961243905, '', 3, '2019-11-11 13:42:46', 3, '2019-11-11 13:42:46');
INSERT INTO `c_auth_resource` VALUES (643445542076025601, 'user:export', '导出', 603983082961243905, '', 3, '2019-11-11 13:42:52', 3, '2019-11-11 13:42:52');
INSERT INTO `c_auth_resource` VALUES (643445641149680705, 'menu:add', '添加', 603976297063910529, '', 3, '2019-11-11 13:43:16', 3, '2019-11-11 13:43:16');
INSERT INTO `c_auth_resource` VALUES (643445674330819745, 'menu:update', '修改', 603976297063910529, '', 3, '2019-11-11 13:43:24', 3, '2019-11-11 13:43:24');
INSERT INTO `c_auth_resource` VALUES (643445704177487105, 'menu:delete', '删除', 603976297063910529, '', 3, '2019-11-11 13:43:31', 3, '2019-11-11 13:43:31');
INSERT INTO `c_auth_resource` VALUES (643445747320098145, 'menu:view', '查看', 603976297063910529, '', 3, '2019-11-11 13:43:41', 3, '2019-11-11 13:43:41');
INSERT INTO `c_auth_resource` VALUES (643445774687931841, 'menu:export', '导出', 603976297063910529, '', 3, '2019-11-11 13:43:48', 3, '2019-11-11 13:43:48');
INSERT INTO `c_auth_resource` VALUES (643445802106097185, 'menu:import', '导入', 603976297063910529, '', 3, '2019-11-11 13:43:54', 3, '2019-11-11 13:43:54');
INSERT INTO `c_auth_resource` VALUES (643448338154263521, 'role:add', '添加', 603981723864141121, '', 3, '2019-11-11 13:53:59', 3, '2019-11-11 13:53:59');
INSERT INTO `c_auth_resource` VALUES (643448369779315777, 'role:update', '修改', 603981723864141121, '', 3, '2019-11-11 13:54:06', 3, '2019-11-11 13:54:06');
INSERT INTO `c_auth_resource` VALUES (643448507767723169, 'role:delete', '删除', 603981723864141121, '', 3, '2019-11-11 13:54:39', 3, '2019-11-11 13:54:39');
INSERT INTO `c_auth_resource` VALUES (643448611161511169, 'role:view', '查看', 603981723864141121, '', 3, '2019-11-11 13:55:04', 3, '2019-11-11 13:55:04');
INSERT INTO `c_auth_resource` VALUES (643448656451605857, 'role:export', '导出', 603981723864141121, '', 3, '2019-11-11 13:55:15', 3, '2019-11-11 13:55:15');
INSERT INTO `c_auth_resource` VALUES (643448730950833601, 'role:import', '导入', 603981723864141121, '', 3, '2019-11-11 13:55:32', 3, '2019-11-11 13:55:32');
INSERT INTO `c_auth_resource` VALUES (643448826945869409, 'role:auth', '授权', 603981723864141121, '', 3, '2019-11-11 13:55:55', 3, '2019-11-11 13:55:55');
INSERT INTO `c_auth_resource` VALUES (643449540959016737, 'dict:add', '添加', 605078371293987105, '', 3, '2019-11-11 13:58:45', 3, '2019-11-11 13:58:45');
INSERT INTO `c_auth_resource` VALUES (643449573045442433, 'dict:update', '修改', 605078371293987105, '', 3, '2019-11-11 13:58:53', 3, '2019-11-11 13:58:53');
INSERT INTO `c_auth_resource` VALUES (643449629173618657, 'dict:view', '查看', 605078371293987105, '', 3, '2019-11-11 13:59:07', 3, '2019-11-11 13:59:07');
INSERT INTO `c_auth_resource` VALUES (643449944866297985, 'dict:delete', '删除', 605078371293987105, '', 3, '2019-11-11 14:00:22', 3, '2019-11-11 14:00:22');
INSERT INTO `c_auth_resource` VALUES (643449998909905121, 'dict:export', '导出', 605078371293987105, '', 3, '2019-11-11 14:00:35', 3, '2019-11-11 14:00:35');
INSERT INTO `c_auth_resource` VALUES (643450072595437889, 'dict:import', '导入', 605078371293987105, '', 3, '2019-11-11 14:00:52', 3, '2019-11-11 14:00:52');
INSERT INTO `c_auth_resource` VALUES (643450171333548481, 'area:add', '添加', 605078463069552993, '', 3, '2019-11-11 14:01:16', 3, '2019-11-11 14:01:16');
INSERT INTO `c_auth_resource` VALUES (643450210449627681, 'area:update', '修改', 605078463069552993, '', 3, '2019-11-11 14:01:25', 3, '2019-11-11 14:01:25');
INSERT INTO `c_auth_resource` VALUES (643450295858240129, 'area:delete', '删除', 605078463069552993, '', 3, '2019-11-11 14:01:45', 3, '2019-11-11 14:01:45');
INSERT INTO `c_auth_resource` VALUES (643450326619265761, 'area:view', '查看', 605078463069552993, '', 3, '2019-11-11 14:01:53', 3, '2019-11-11 14:01:53');
INSERT INTO `c_auth_resource` VALUES (643450551291353921, 'area:export', '导出', 605078463069552993, '', 3, '2019-11-11 14:02:46', 3, '2019-11-11 14:02:46');
INSERT INTO `c_auth_resource` VALUES (643450602218593185, 'area:import', '导入', 605078463069552993, '', 3, '2019-11-11 14:02:59', 3, '2019-11-11 14:02:59');
INSERT INTO `c_auth_resource` VALUES (643450770317909249, 'optLog:view', '查看', 605078672772170209, '', 3, '2019-11-11 14:03:39', 3, '2019-11-11 14:03:39');
INSERT INTO `c_auth_resource` VALUES (643450853134441825, 'optLog:export', '导出', 605078672772170209, '', 3, '2019-11-11 14:03:58', 3, '2019-11-11 14:03:58');
INSERT INTO `c_auth_resource` VALUES (643451893279892129, 'msgs:view', '查看', 605080023753294753, '', 3, '2019-11-11 14:08:06', 3, '2019-11-11 14:08:06');
INSERT INTO `c_auth_resource` VALUES (643451945020826369, 'msgs:delete', '删除', 605080023753294753, '', 3, '2019-11-11 14:08:19', 3, '2019-11-11 14:08:19');
INSERT INTO `c_auth_resource` VALUES (643451994945626977, 'msgs:update', '修改', 605080023753294753, '', 3, '2019-11-11 14:08:31', 3, '2019-11-11 14:08:31');
INSERT INTO `c_auth_resource` VALUES (643452086981239745, 'msgs:export', '导出', 605080023753294753, '', 3, '2019-11-11 14:08:53', 3, '2019-11-11 14:08:53');
INSERT INTO `c_auth_resource` VALUES (643452393857492033, 'sms:template:add', '添加', 605080107379327969, '', 3, '2019-11-11 14:10:06', 3, '2019-11-11 14:10:06');
INSERT INTO `c_auth_resource` VALUES (643452429496493217, 'sms:template:update', '修改', 605080107379327969, '', 3, '2019-11-11 14:10:14', 3, '2019-11-11 14:10:14');
INSERT INTO `c_auth_resource` VALUES (643452458693043457, 'sms:template:view', '查看', 605080107379327969, '', 3, '2019-11-11 14:10:21', 3, '2019-11-11 14:10:21');
INSERT INTO `c_auth_resource` VALUES (643452488447436129, 'sms:template:delete', '删除', 605080107379327969, '', 3, '2019-11-11 14:10:28', 3, '2019-11-11 14:10:28');
INSERT INTO `c_auth_resource` VALUES (643452536954561985, 'sms:template:import', '导入', 605080107379327969, '', 3, '2019-11-11 14:10:40', 3, '2019-11-11 14:10:40');
INSERT INTO `c_auth_resource` VALUES (643452571645650465, 'sms:template:export', '导入', 605080107379327969, '', 3, '2019-11-11 14:10:48', 3, '2019-11-11 14:10:48');
INSERT INTO `c_auth_resource` VALUES (643454073500084577, 'sms:manage:add', '添加', 605080359394083937, '', 3, '2019-11-11 14:16:46', 3, '2019-11-11 14:16:46');
INSERT INTO `c_auth_resource` VALUES (643454110992968129, 'sms:manage:update', '修改', 605080359394083937, '', 3, '2019-11-11 14:16:55', 3, '2019-11-11 14:16:55');
INSERT INTO `c_auth_resource` VALUES (643454150905965089, 'sms:manage:view', '查看', 605080359394083937, '', 3, '2019-11-11 14:17:05', 3, '2019-11-11 14:17:05');
INSERT INTO `c_auth_resource` VALUES (643454825052252961, 'sms:manage:delete', '删除', 605080359394083937, '', 3, '2019-11-11 14:19:45', 3, '2019-11-11 14:19:45');
INSERT INTO `c_auth_resource` VALUES (643455175503129569, 'sms:manage:export', '导出', 605080359394083937, '', 3, '2019-11-11 14:21:09', 3, '2019-11-11 14:26:05');
INSERT INTO `c_auth_resource` VALUES (643455720519380097, 'sms:manage:import', '导入', 605080359394083937, '', 3, '2019-11-11 14:23:19', 3, '2019-11-11 14:23:19');
INSERT INTO `c_auth_resource` VALUES (643456690892582401, 'file:add', '添加', 605080648767505601, '', 3, '2019-11-11 14:27:10', 3, '2019-11-11 14:27:10');
INSERT INTO `c_auth_resource` VALUES (643456724186967649, 'file:update', '修改', 605080648767505601, '', 3, '2019-11-11 14:27:18', 3, '2019-11-11 14:27:18');
INSERT INTO `c_auth_resource` VALUES (643456761927315137, 'file:delete', '删除', 605080648767505601, '', 3, '2019-11-11 14:27:27', 3, '2019-11-11 14:27:27');
INSERT INTO `c_auth_resource` VALUES (643456789920100129, 'file:view', '查看', 605080648767505601, '', 3, '2019-11-11 14:27:34', 3, '2019-11-11 14:27:34');
INSERT INTO `c_auth_resource` VALUES (643456884694593409, 'file:download', '下载', 605080648767505601, '', 3, '2019-11-11 14:27:56', 3, '2019-11-11 14:27:56');
INSERT INTO `c_auth_resource` VALUES (645288214990422241, 'optLog:delete', '删除', 605078672772170209, '', 3, '2019-11-16 15:45:00', 3, '2019-11-16 15:45:00');
INSERT INTO `c_auth_resource` VALUES (645288283693121889, 'loginLog:delete', '删除', 645215230518909025, '', 3, '2019-11-16 15:45:16', 3, '2019-11-16 15:45:16');
INSERT INTO `c_auth_resource` VALUES (645288375300915649, 'loginLog:export', '导出', 645215230518909025, '', 3, '2019-11-16 15:45:38', 3, '2019-11-16 15:45:38');
INSERT INTO `c_auth_resource` VALUES (648846610591122721, 'sms:manage:copy', '复制', 605080359394083937, '', 3, '2019-11-26 11:24:47', 3, '2019-11-26 11:24:47');
INSERT INTO `c_auth_resource` VALUES (658002570005972001, 'msgs:add', '新增', 605080023753294753, '', 3, '2019-12-21 17:47:18', 3, '2019-12-21 17:47:18');
INSERT INTO `c_auth_resource` VALUES (658002632467547265, 'msgs:mark', '标记已读', 605080023753294753, '', 3, '2019-12-21 17:47:33', 3, '2019-12-21 17:47:33');
INSERT INTO `c_auth_resource` VALUES (659045058871296641, 'systemApi:add', '新增', 605424535633666945, '', 3, '2019-12-24 14:49:47', 3, '2019-12-24 14:49:47');
INSERT INTO `c_auth_resource` VALUES (659045100646564577, 'systemApi:delete', '删除', 605424535633666945, '', 3, '2019-12-24 14:49:57', 3, '2019-12-24 14:49:57');
INSERT INTO `c_auth_resource` VALUES (659045145735332673, 'systemApi:update', '修改', 605424535633666945, '', 3, '2019-12-24 14:50:07', 3, '2019-12-24 14:50:07');
INSERT INTO `c_auth_resource` VALUES (659045207890723745, 'systemApi:export', '导出', 605424535633666945, '', 3, '2019-12-24 14:50:22', 3, '2019-12-24 14:50:22');
INSERT INTO `c_auth_resource` VALUES (659430164874134689, 'systemApi:view', '查看', 605424535633666945, '', 3, '2019-12-25 16:20:03', 3, '2019-12-25 16:20:03');
INSERT INTO `c_auth_resource` VALUES (659702641965662497, 'application:add', '新增', 605078538881597857, '', 3, '2019-12-26 10:22:47', 3, '2019-12-26 10:22:47');
INSERT INTO `c_auth_resource` VALUES (659702674622513537, 'application:update', '修改', 605078538881597857, '', 3, '2019-12-26 10:22:55', 3, '2019-12-26 10:22:55');
INSERT INTO `c_auth_resource` VALUES (659702756889592289, 'application:delete', '删除', 605078538881597857, '', 3, '2019-12-26 10:23:14', 3, '2019-12-26 10:23:14');
INSERT INTO `c_auth_resource` VALUES (659702791312245313, 'application:view', '查看', 605078538881597857, '', 3, '2019-12-26 10:23:22', 3, '2019-12-26 10:23:22');
INSERT INTO `c_auth_resource` VALUES (659702853945787041, 'application:export', '导出', 605078538881597857, '', 3, '2019-12-26 10:23:37', 3, '2019-12-26 10:23:37');
INSERT INTO `c_auth_resource` VALUES (1225042691843162112, 'parameter:add', '添加', 1225042542827929600, '', 3, '2020-02-05 21:05:13', 3, '2020-02-05 21:05:13');
INSERT INTO `c_auth_resource` VALUES (1225042821497487360, 'parameter:update', '修改', 1225042542827929600, '', 3, '2020-02-05 21:05:43', 3, '2020-02-05 21:05:43');
INSERT INTO `c_auth_resource` VALUES (1225042887989788672, 'parameter:copy', '复制', 1225042542827929600, '', 3, '2020-02-05 21:05:59', 3, '2020-02-05 21:05:59');
INSERT INTO `c_auth_resource` VALUES (1225042949172101120, 'parameter:delete', '删除', 1225042542827929600, '', 3, '2020-02-05 21:06:14', 3, '2020-02-05 21:06:14');
INSERT INTO `c_auth_resource` VALUES (1225043012455759872, 'parameter:export', '导出', 1225042542827929600, '', 3, '2020-02-05 21:06:29', 3, '2020-02-05 21:06:29');
INSERT INTO `c_auth_resource` VALUES (1225312194581757952, 'product:add', 'add', 1225312124415246336, '', 3, '2020-02-06 14:56:07', 3, '2020-02-06 14:56:07');
INSERT INTO `c_auth_resource` VALUES (1225312231512604672, 'product:update', 'update', 1225312124415246336, '', 3, '2020-02-06 14:56:16', 3, '2020-02-06 14:56:16');
INSERT INTO `c_auth_resource` VALUES (1225312270565769216, 'product:delete', 'del', 1225312124415246336, '', 3, '2020-02-06 14:56:25', 3, '2020-02-06 14:56:25');
INSERT INTO `c_auth_resource` VALUES (1225312319639126016, 'product:export', 'export', 1225312124415246336, '', 3, '2020-02-06 14:56:37', 3, '2020-02-06 14:56:37');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_role`;
CREATE TABLE `c_auth_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '角色名称',
  `code` varchar(20) DEFAULT '' COMMENT '角色编码',
  `describe_` varchar(100) DEFAULT '' COMMENT '功能描述',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `readonly` bit(1) DEFAULT b'0' COMMENT '是否内置角色',
  `ds_type` varchar(20) NOT NULL DEFAULT 'SELF' COMMENT '数据权限类型\n#DataScopeType{ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人;}',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of c_auth_role
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_role` VALUES (100, '平台管理员', 'PT_ADMIN', '平台内置管理员', b'1', b'1', 'ALL', 1, '2019-10-25 13:46:00', 1, '2019-10-25 13:46:04');
INSERT INTO `c_auth_role` VALUES (643779012732130273, '普通员工', 'BASE_USER', '只有最基本的权限', b'1', b'0', 'SELF', 3, '2019-11-12 11:47:58', 3, '2019-11-16 09:47:11');
INSERT INTO `c_auth_role` VALUES (645198153556958497, '部门经理', 'DEPT_MANAGER', '管理本级以及子级用户', b'1', b'0', 'THIS_LEVEL_CHILDREN', 3, '2019-11-16 09:47:07', 3, '2019-11-26 11:06:58');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_role_authority`;
CREATE TABLE `c_auth_role_authority` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `authority_id` bigint(20) NOT NULL COMMENT '资源id\n#c_auth_resource\n#c_auth_menu',
  `authority_type` varchar(10) NOT NULL DEFAULT 'MENU' COMMENT '权限类型\n#AuthorizeType{MENU:菜单;RESOURCE:资源;}',
  `role_id` bigint(20) NOT NULL COMMENT '角色id\n#c_auth_role',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `IDX_KEY` (`role_id`,`authority_type`,`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色的资源';

-- ----------------------------
-- Records of c_auth_role_authority
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_role_authority` VALUES (655083808609012321, 643445116756821697, 'RESOURCE', 643779012732130273, '2019-12-13 16:29:11', 3);
INSERT INTO `c_auth_role_authority` VALUES (655083808613206657, 643445262110427201, 'RESOURCE', 643779012732130273, '2019-12-13 16:29:11', 3);
INSERT INTO `c_auth_role_authority` VALUES (655083808617400993, 643445197954353025, 'RESOURCE', 643779012732130273, '2019-12-13 16:29:11', 3);
INSERT INTO `c_auth_role_authority` VALUES (655083808617401025, 643445162915137313, 'RESOURCE', 643779012732130273, '2019-12-13 16:29:11', 3);
INSERT INTO `c_auth_role_authority` VALUES (655083808617401057, 643445412774021505, 'RESOURCE', 643779012732130273, '2019-12-13 16:29:11', 3);
INSERT INTO `c_auth_role_authority` VALUES (655083808617401089, 643445283996305569, 'RESOURCE', 643779012732130273, '2019-12-13 16:29:11', 3);
INSERT INTO `c_auth_role_authority` VALUES (655083808621595425, 603982713849908801, 'MENU', 643779012732130273, '2019-12-13 16:29:11', 3);
INSERT INTO `c_auth_role_authority` VALUES (655083808621595457, 101, 'MENU', 643779012732130273, '2019-12-13 16:29:11', 3);
INSERT INTO `c_auth_role_authority` VALUES (655083808621595489, 605080023753294753, 'MENU', 643779012732130273, '2019-12-13 16:29:11', 3);
INSERT INTO `c_auth_role_authority` VALUES (655083808621595521, 104, 'MENU', 643779012732130273, '2019-12-13 16:29:11', 3);
INSERT INTO `c_auth_role_authority` VALUES (655083808625789857, 603983082961243905, 'MENU', 643779012732130273, '2019-12-13 16:29:11', 3);
INSERT INTO `c_auth_role_authority` VALUES (655083808625789889, 105, 'MENU', 643779012732130273, '2019-12-13 16:29:11', 3);
INSERT INTO `c_auth_role_authority` VALUES (655083808625789921, 605078538881597857, 'MENU', 643779012732130273, '2019-12-13 16:29:11', 3);
INSERT INTO `c_auth_role_authority` VALUES (660170805178533217, 643445197954353025, 'RESOURCE', 660170723091808833, '2019-12-27 17:23:06', 3);
INSERT INTO `c_auth_role_authority` VALUES (660170805191116161, 643445162915137313, 'RESOURCE', 660170723091808833, '2019-12-27 17:23:06', 3);
INSERT INTO `c_auth_role_authority` VALUES (660170805191116193, 603982713849908801, 'MENU', 660170723091808833, '2019-12-27 17:23:06', 3);
INSERT INTO `c_auth_role_authority` VALUES (660170805191116225, 101, 'MENU', 660170723091808833, '2019-12-27 17:23:06', 3);
INSERT INTO `c_auth_role_authority` VALUES (660170805191116257, 603982542332235201, 'MENU', 660170723091808833, '2019-12-27 17:23:06', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294803058977, 643444685339100197, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294811447617, 643445802106097185, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294811447649, 643444685339100196, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294811447681, 643445674330819745, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294811447713, 643444685339100193, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294811447745, 643444685339100195, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294815642081, 643445774687931841, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294815642113, 643449629173618657, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294815642145, 643445477274028609, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294815642177, 643445197954353025, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294815642209, 643445747320098145, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294815642241, 643449573045442433, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294819836577, 643445704177487105, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294819836609, 643445162915137313, 'RESOURCE', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294819836641, 605080648767505601, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294819836673, 605080107379327969, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294819836705, 101, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294824031041, 605080359394083937, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294824031073, 102, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294824031105, 103, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294824031137, 603983082961243905, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294824031169, 105, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294828225505, 106, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294828225537, 107, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294828225569, 603982542332235201, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294828225601, 605078463069552993, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294828225633, 603981723864141121, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294832419969, 603982713849908801, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294832420001, 605078371293987105, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294832420033, 605079751035454305, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294832420065, 605080023753294753, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (672439294832420097, 603976297063910529, 'MENU', 645198153556958497, '2020-01-30 13:53:41', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396889817088, 643444685339100197, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396906594304, 643445116756821697, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396910788608, 643445802106097185, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396910788609, 643448826945869409, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396914982912, 643444685339100196, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396919177216, 643450072595437889, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396923371520, 643452536954561985, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396923371521, 643444685339100193, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396927565825, 643445514607528609, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396927565826, 643444685339100195, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396931760128, 659702853945787041, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396931760129, 643444685339100194, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396931760130, 643448507767723169, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396935954432, 643450295858240129, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396935954434, 643449998909905121, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396940148736, 1225042949172101120, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396940148737, 643445197954353025, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396940148738, 645288283693121889, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396944343040, 643444897201784193, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396944343041, 643448730950833601, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396944343042, 643456690892582401, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396948537344, 643445412774021505, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396948537345, 1225043012455759872, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396952731648, 643445262110427201, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396952731649, 643448656451605857, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396952731650, 643450853134441825, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396956925952, 643445774687931841, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396956925953, 643450171333548481, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396956925954, 643456761927315137, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396961120256, 643445477274028609, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396961120257, 648846610591122721, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396961120258, 643445352703199521, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396965314560, 659702756889592289, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396965314561, 643450551291353921, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396965314562, 643445747320098145, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396969508864, 643452458693043457, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396969508865, 643454110992968129, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396969508866, 643445016773002817, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396973703169, 659430164874134689, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396977897472, 643445162915137313, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396977897473, 643452571645650465, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396982091776, 643444858974897441, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396982091777, 643449944866297985, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396986286080, 643450770317909249, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396986286081, 643455720519380097, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396986286082, 643450210449627681, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396990480384, 659045100646564577, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396990480385, 643444992357959137, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396990480386, 1225042887989788672, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396994674688, 643456789920100129, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396994674689, 658002632467547265, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396994674690, 659702641965662497, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396994674691, 643445229575210977, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396998868992, 643445641149680705, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396998868993, 643444819758154945, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312396998868994, 643449629173618657, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397003063296, 643451945020826369, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397003063297, 643448369779315777, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397003063298, 643454825052252961, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397003063299, 643456884694593409, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397007257600, 643448338154263521, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397007257601, 643449573045442433, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397007257602, 1225042691843162112, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397011451904, 659702791312245313, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397011451905, 643445704177487105, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397011451906, 643451893279892129, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397011451907, 658002570005972001, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397015646208, 659045058871296641, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397015646209, 659045207890723745, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397015646210, 643452429496493217, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397019840512, 643454073500084577, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397019840513, 643455175503129569, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397019840514, 643445283996305569, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397019840515, 643451994945626977, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397024034816, 643445674330819745, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397024034817, 643445542076025601, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397024034818, 645288214990422241, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397028229120, 643452393857492033, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397028229121, 643450326619265761, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397028229122, 643450602218593185, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397028229123, 643454150905965089, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397032423424, 643452086981239745, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397032423425, 643452488447436129, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397032423426, 645288375300915649, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397036617728, 659702674622513537, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397036617730, 643456724186967649, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397036617731, 1225042821497487360, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397040812032, 643445448081672673, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397040812033, 659045145735332673, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397040812034, 643448611161511169, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397045006336, 643449540959016737, 'RESOURCE', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397045006337, 645215230518909025, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397045006338, 605079411338773153, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397045006339, 603982542332235201, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397049200640, 605078371293987105, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397049200641, 644111530555611361, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397049200642, 605079751035454305, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397053394944, 605078672772170209, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397053394945, 603976297063910529, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397057589248, 605079545585861345, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397057589249, 605080107379327969, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397061783552, 605078979149300257, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397061783553, 605080359394083937, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397061783554, 605078463069552993, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397065977856, 605080816296396097, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397065977857, 1225042542827929600, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397065977858, 603982713849908801, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397065977859, 605079658416833313, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397070172160, 605078538881597857, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397070172161, 605080648767505601, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397070172162, 603983082961243905, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397070172163, 603981723864141121, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397074366464, 605424535633666945, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397074366465, 605080023753294753, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397074366466, 605079239015793249, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397074366467, 101, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397078560768, 102, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397078560769, 103, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397078560770, 104, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397078560771, 105, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397082755072, 106, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397082755073, 107, 'MENU', 100, '2020-02-06 14:56:55', 3);
INSERT INTO `c_auth_role_authority` VALUES (1225312397082755074, 643464272629728001, 'MENU', 100, '2020-02-06 14:56:55', 3);
COMMIT;

-- ----------------------------
-- Table structure for c_auth_role_org
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_role_org`;
CREATE TABLE `c_auth_role_org` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID\n#c_auth_role',
  `org_id` bigint(20) DEFAULT NULL COMMENT '部门ID\n#c_core_org',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色组织关系';

-- ----------------------------
-- Records of c_auth_role_org
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_role_org` VALUES (648842347873832129, 645198153556958497, 100, '2019-11-26 11:07:51', 3);
INSERT INTO `c_auth_role_org` VALUES (648842347882220769, 645198153556958497, 101, '2019-11-26 11:07:51', 3);
INSERT INTO `c_auth_role_org` VALUES (648842347886415105, 645198153556958497, 102, '2019-11-26 11:07:51', 3);
INSERT INTO `c_auth_role_org` VALUES (648842347886415137, 645198153556958497, 643775612976106881, '2019-11-26 11:07:51', 3);
INSERT INTO `c_auth_role_org` VALUES (648842347886415169, 645198153556958497, 643775664683486689, '2019-11-26 11:07:51', 3);
INSERT INTO `c_auth_role_org` VALUES (648842347886415201, 645198153556958497, 643775904077582049, '2019-11-26 11:07:51', 3);
INSERT INTO `c_auth_role_org` VALUES (648842347890609537, 645198153556958497, 643776324342648929, '2019-11-26 11:07:51', 3);
INSERT INTO `c_auth_role_org` VALUES (648842347890609569, 645198153556958497, 643776407691858113, '2019-11-26 11:07:51', 3);
INSERT INTO `c_auth_role_org` VALUES (648842347890609601, 645198153556958497, 643776508795556193, '2019-11-26 11:07:51', 3);
INSERT INTO `c_auth_role_org` VALUES (648842347890609633, 645198153556958497, 643776594376135105, '2019-11-26 11:07:51', 3);
INSERT INTO `c_auth_role_org` VALUES (648842347890609665, 645198153556958497, 643776633823564321, '2019-11-26 11:07:51', 3);
INSERT INTO `c_auth_role_org` VALUES (648842347890609697, 645198153556958497, 643776668917305985, '2019-11-26 11:07:51', 3);
INSERT INTO `c_auth_role_org` VALUES (648842347894804033, 645198153556958497, 643776713909605089, '2019-11-26 11:07:51', 3);
INSERT INTO `c_auth_role_org` VALUES (648842347894804065, 645198153556958497, 643776757199016769, '2019-11-26 11:07:51', 3);
COMMIT;

-- ----------------------------
-- Table structure for c_auth_system_api
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_system_api`;
CREATE TABLE `c_auth_system_api` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '接口编码',
  `name` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '接口名称',
  `describe_` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '资源描述',
  `request_method` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '请求方式',
  `content_type` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '响应类型',
  `service_id` varchar(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '服务ID',
  `path` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '请求路径',
  `status` bit(1) DEFAULT b'1' COMMENT '状态\n:0-无效 1-有效',
  `is_persist` bit(1) DEFAULT b'0' COMMENT '保留数据 \n0-否 1-是 系统内资数据,不允许删除',
  `is_auth` bit(1) DEFAULT b'1' COMMENT '是否需要认证\n: 0-无认证 1-身份认证 默认:1',
  `is_open` bit(1) DEFAULT b'0' COMMENT '是否公开\n: 0-内部的 1-公开的',
  `class_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '类名',
  `method_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '方法名',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UNX_ID` (`id`) USING BTREE,
  UNIQUE KEY `UNX_CODE` (`code`(100)) USING BTREE,
  KEY `service_id` (`service_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='API接口';

-- ----------------------------
-- Records of c_auth_system_api
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_system_api` VALUES (656462286503804961, 'd0833a044b5f8e6b44ff8a1e21446f2f', '查询所有组织', '查询所有组织', 'GET', '', 'zuihou-authority-server', '/orgs', b'1', b'1', b'1', b'0', 'com.github.zuihou.general.controller.GeneralController', 'find', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:36');
INSERT INTO `c_auth_system_api` VALUES (656462286596079681, 'b01d70b66ca411e5bc3f776f2b4b97b9', 'test', '', 'GET', '', 'zuihou-authority-server', '/test', b'1', b'1', b'1', b'0', 'com.github.zuihou.general.controller.GeneralController', 'test', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:36');
INSERT INTO `c_auth_system_api` VALUES (656462286621245537, '7713bbf4377be59e4ef40648616ffb1f', '获取当前系统所有枚举', '获取当前系统所有枚举', 'GET', '', 'zuihou-authority-server', '/enums', b'1', b'1', b'1', b'0', 'com.github.zuihou.general.controller.GeneralController', 'enums', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:36');
INSERT INTO `c_auth_system_api` VALUES (656462286650605697, 'ee95b7e12e6b54b21dc7c54e189c4817', '查询应用', '查询应用', 'GET', '', 'zuihou-authority-server', '/application/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.ApplicationController', 'get', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:36');
INSERT INTO `c_auth_system_api` VALUES (656462286679965857, 'e003b95b633f527246a867f9bc90bb4a', '新增应用', '新增应用不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/application', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.ApplicationController', 'save', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:36');
INSERT INTO `c_auth_system_api` VALUES (656462286851932353, '8a9bb52ab9bf03a76b3251546f6c6024', '分页查询应用', '分页查询应用', 'GET', '', 'zuihou-authority-server', '/application/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.ApplicationController', 'page', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:36');
INSERT INTO `c_auth_system_api` VALUES (656462286881292513, 'a8a12f7d426e81761792d74c4ed31fae', '查询应用接口', '查询应用接口', 'GET', '', 'zuihou-authority-server', '/applicationSystemApi/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.ApplicationSystemApiController', 'get', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:36');
INSERT INTO `c_auth_system_api` VALUES (656462286914846977, 'fbeb18cff593e69a843867d70d6669bf', '新增应用接口', '新增应用接口不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/applicationSystemApi', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.ApplicationSystemApiController', 'save', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:36');
INSERT INTO `c_auth_system_api` VALUES (656462287011316001, '7304b6c81ee0d1ca9e6afd5ee833621f', '分页查询应用接口', '分页查询应用接口', 'GET', '', 'zuihou-authority-server', '/applicationSystemApi/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.ApplicationSystemApiController', 'page', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:36');
INSERT INTO `c_auth_system_api` VALUES (656462287036481857, '678882d4a57f76a62c93488aae93a03c', '验证验证码', '验证验证码', 'GET', '', 'zuihou-authority-server', '/anno/check', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.LoginController', 'check', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:36');
INSERT INTO `c_auth_system_api` VALUES (656462287070036321, 'f65333a9bbac3d4a349bb92147cee6f3', '验证token', '验证token', 'GET', '', 'zuihou-authority-server', '/anno/verify', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.LoginController', 'verify', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:36');
INSERT INTO `c_auth_system_api` VALUES (656462287103590785, '42ca64dee47034fa4d067d52851ac12d', '登录', '登录', 'POST', '', 'zuihou-authority-server', '/anno/login', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.LoginController', 'loginTx', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:36');
INSERT INTO `c_auth_system_api` VALUES (656462287137145249, 'e70c83f57f74ec8ddc5b588a9778a028', '超级管理员登录', '超级管理员登录', 'POST', '', 'zuihou-authority-server', '/anno/admin/login', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.LoginController', 'loginAdminTx', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287162311105, '355be064dc3aeeafb3a817b099bbc714', '仅供测试使用', '仅供测试使用', 'POST', '', 'zuihou-authority-server', '/anno/token', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.LoginController', 'tokenTx', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287191671265, 'c0d7b954ce1953a4522bd1c1d5987057', 'captcha', '', 'GET', '', 'zuihou-authority-server', '/anno/captcha', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.LoginController', 'captcha', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287221031425, '1fd5d37730a2fd5567b0aa980aae6e6e', '查询菜单', '查询菜单', 'GET', '', 'zuihou-authority-server', '/menu/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.MenuController', 'get', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287250391585, '81a6466f3e4be8d531236d85dd69289d', '查询用户可用的所有菜单', '查询用户可用的所有菜单', 'GET', '', 'zuihou-authority-server', '/menu', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.MenuController', 'myMenus', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287355249217, '2bdaa93443715b0716a55658af47b3a0', '分页查询菜单', '分页查询菜单', 'GET', '', 'zuihou-authority-server', '/menu/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.MenuController', 'page', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287413969505, '4e49a2a76a1985d791ad686b1378c2f9', '查询用户可用的所有菜单路由树', '查询用户可用的所有菜单路由树', 'GET', '', 'zuihou-authority-server', '/menu/router', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.MenuController', 'myRouter', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287439135361, 'db398a0e2dc2d1e0728f97491ecf9951', '查询超管菜单路由树', '查询超管菜单路由树', 'GET', '', 'zuihou-authority-server', '/menu/admin/router', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.MenuController', 'adminRouter', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287464301217, '05bfa6333a2a4219fa3a2e61ebefa12d', '查询系统所有的菜单', '查询系统所有的菜单', 'GET', '', 'zuihou-authority-server', '/menu/tree', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.MenuController', 'allTree', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287497855681, '644af62f1ff0b7f672c89e26a1eedfc9', '查询资源', '查询资源', 'GET', '', 'zuihou-authority-server', '/resource/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.ResourceController', 'get', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287523021537, 'e7236c62a99641f27a7b1af638a3fc0c', '查询用户可用的所有资源', '查询用户可用的所有资源', 'GET', '', 'zuihou-authority-server', '/resource', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.ResourceController', 'visible', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287653044993, 'a1bbd89c6ce5181732bc556b32846690', '分页查询资源', '分页查询资源', 'GET', '', 'zuihou-authority-server', '/resource/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.ResourceController', 'page', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287682405153, 'f228d388b7d803ac7c817b3b39f01f55', '查询指定角色关联的菜单和资源', '查询指定角色关联的菜单和资源', 'GET', '', 'zuihou-authority-server', '/roleAuthority/{roleId}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.RoleAuthorityController', 'page', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287707571009, '302c4fa916d8184683ed5abd381811ae', '查询角色', '查询角色', 'GET', '', 'zuihou-authority-server', '/role/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.RoleController', 'get', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287736931169, '42161352297085d54bad1f8728a6b19b', '新增角色', '新增角色不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/role', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.RoleController', 'save', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287799845761, 'ed868d0471522f634f53052070a1ae58', '检测角色编码', '检测角色编码', 'GET', '', 'zuihou-authority-server', '/role/check/{code}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.RoleController', 'check', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287858566049, '97292907dc8836ac3c9b1854674ad0c6', '分页查询角色', '分页查询角色', 'GET', '', 'zuihou-authority-server', '/role/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.RoleController', 'page', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287883731905, '03a26cffab03f29bf38b4fa1ec34ab57', '给用户分配角色', '给用户分配角色', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/role/user', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.RoleController', 'saveUserRole', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287908897761, 'ee2d54108512752de45887e1f60cb9eb', '查询角色的用户', '查询角色的用户', 'GET', '', 'zuihou-authority-server', '/role/user/{roleId}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.RoleController', 'findUserIdByRoleId', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287934063617, 'b38900701680eef9a9cc14e0eda2f69f', '查询角色拥有的资源id集合', '查询角色拥有的资源id集合', 'GET', '', 'zuihou-authority-server', '/role/authority/{roleId}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.RoleController', 'findAuthorityIdByRoleId', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287955035169, '4e3c451da6a66bd55e76888fe673f141', '给角色配置权限', '给角色配置权限', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/role/authority', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.RoleController', 'saveRoleAuthority', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462287980201025, '06596822d699ef7bfe5e131b36d65776', '根据角色编码查询用户ID', '根据角色编码查询用户ID', 'GET', '', 'zuihou-authority-server', '/role/codes', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.RoleController', 'findUserIdByCode', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288005366881, '86acf848bc28ad4249d17833744d1617', '查询API接口', '查询API接口', 'GET', '', 'zuihou-authority-server', '/systemApi/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.SystemApiController', 'get', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288030532737, 'c4e2912bc64ce88324268c3e4ac5a7af', '新增API接口', '新增API接口不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/systemApi', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.SystemApiController', 'save', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288122807457, 'ed8f03aa43fa2b78f42c5ad66034825c', '分页查询API接口', '分页查询API接口', 'GET', '', 'zuihou-authority-server', '/systemApi/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.SystemApiController', 'page', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288147973313, '475e7c2c9467348d1825259951bde2f2', '批量新增API接口', '批量新增API接口不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/systemApi/batch', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.SystemApiController', 'batchSave', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288168944865, '26efb9dba778270aa49bd856f917938a', '查询用户', '查询用户', 'GET', '', 'zuihou-authority-server', '/user/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.UserController', 'get', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288194110721, '1832915cafcb033578fc0c9d05633340', '注册用户', '注册用户', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/user/anno/register', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.UserController', 'register', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288219276577, 'f62e727d9e445f2b0d1e26ca25d68a2e', '新增用户', '新增用户不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/user', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.UserController', 'save', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288324134209, '3dc84fb2cfeffab8a76363a80435e319', '分页查询用户', '分页查询用户', 'GET', '', 'zuihou-authority-server', '/user/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.UserController', 'page', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288345105761, '012cc91e86d356d231dd49af72d5daff', '查询用户权限范围', '根据用户id，查询用户权限范围', 'GET', '', 'zuihou-authority-server', '/user/ds/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.UserController', 'getDataScopeById', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288370271617, 'ce1912ed212f11abfde5fd192ed8c69a', '查询用户详细', '查询用户详细', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/user/anno/id/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.UserController', 'getById', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288399631777, '0927bb92ec0608f9ba0a8ed98d21d1ff', '修改头像', '修改头像', 'PUT', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/user/avatar', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.UserController', 'avatar', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288424797633, 'e5daaab33ba67529a60a5c9b720365c6', '修改密码', '修改密码', 'PUT', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/user/password', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.UserController', 'updatePassword', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288449963489, '2cab52a12a8f55aa8c23620bf7312031', '重置密码', '重置密码', 'GET', '', 'zuihou-authority-server', '/user/reset', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.UserController', 'resetTx', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288470935041, 'd239d9c6971664fdc0df2c3d53b5c31a', '查询角色的已关联用户', '查询角色的已关联用户', 'GET', '', 'zuihou-authority-server', '/user/role/{roleId}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.auth.UserController', 'findUserByRoleId', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288496100897, 'de91592df81d96c6e01e4c82cd5ad1ea', '删除地区表', '根据id物理删除地区表', 'DELETE', '', 'zuihou-authority-server', '/area/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.AreaController', 'delete', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288521266753, '5e4f598b0e37f4728d721b3f6c5779f3', '新增地区表', '新增地区表不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/area', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.AreaController', 'save', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288634512993, '3cb4cff6705839a73395eda8d5169d6c', '分页查询地区表', '分页查询地区表', 'GET', '', 'zuihou-authority-server', '/area/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.AreaController', 'page', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288659678849, 'e0145e055aa0333357d480a84c066274', 'index', '', 'GET', '', 'zuihou-authority-server', '/dashboard/visit', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.DashboardController', 'index', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288684844705, '61a8ec3f9f0fb31c0f0cce677d25fead', 'generate', '', 'GET', '', 'zuihou-authority-server', '/common/generateId', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.DashboardController', 'generate', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288710010561, 'bc47aeaa0295b2fae71979e9980733cd', '删除字典目录', '根据id物理删除字典目录', 'DELETE', '', 'zuihou-authority-server', '/dictionary/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.DictionaryController', 'delete', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288735176417, '5857987ddbd1e203625adb654cd4de38', '新增字典目录', '新增字典目录不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/dictionary', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.DictionaryController', 'save', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288814868225, '393803a8988f92bae1986fe457eaf7ce', '分页查询字典目录', '分页查询字典目录', 'GET', '', 'zuihou-authority-server', '/dictionary/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.DictionaryController', 'page', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288840034081, '6d7ff5849ae6e8cf58be9918813780b7', '删除字典项', '根据id物理删除字典项', 'DELETE', '', 'zuihou-authority-server', '/dictionaryItem/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.DictionaryItemController', 'delete', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288861005633, '9e8f9b5df49cde8cc782516527e5ac36', '新增字典项', '新增字典项不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/dictionaryItem', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.DictionaryItemController', 'save', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288944891745, 'e68dd4f1b81ba9506fc171194e8949a2', '根据字典编码查询字典条目的map集合', '根据字典编码查询字典条目的map集合', 'GET', '', 'zuihou-authority-server', '/dictionaryItem/codes', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.DictionaryItemController', 'map', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462288965863297, 'eede0406d427d008fcf59a75f7b81ad7', '分页查询字典项', '分页查询字典项', 'GET', '', 'zuihou-authority-server', '/dictionaryItem/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.DictionaryItemController', 'page', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289003612065, 'de4868b58e0027583532c4d511b609f7', '数据读取', '数据读取', 'GET', '', 'zuihou-authority-server', '/j2cache/get', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.J2CacheController', 'get', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289028777921, '1bc45ca8e726b0642c1bad8d041bc6a5', '清理', '清理', 'GET', '', 'zuihou-authority-server', '/j2cache/clear', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.J2CacheController', 'clear', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289049749473, 'a2fecc705aae127ed9b9bb023e6e359e', '检测存在那级缓存', '检测存在那级缓存', 'GET', '', 'zuihou-authority-server', '/j2cache/check', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.J2CacheController', 'check', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289074915329, 'e6f67b6bc6084e940332b5f83f4dbb64', '获取缓存的所有key', '获取缓存的所有key', 'GET', '', 'zuihou-authority-server', '/j2cache/keys', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.J2CacheController', 'keys', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289095886881, '03a942e6dd7e1971e695bbf0197fb11c', '数据写入', '数据写入', 'GET', '', 'zuihou-authority-server', '/j2cache/set', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.J2CacheController', 'set', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289116858433, '7035709eae4415c0e3a7b546db37f840', '检测是否存在', '检测是否存在', 'GET', '', 'zuihou-authority-server', '/j2cache/exists', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.J2CacheController', 'exists', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289142024289, '51fc92a32d436cb112ac3a9143a1e40c', '淘汰缓存', '淘汰缓存', 'GET', '', 'zuihou-authority-server', '/j2cache/evict', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.J2CacheController', 'evict', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289167190145, '843c63e6b8cedf399de69c6e5b90ebd2', '删除1级缓存 Region', '删除1级缓存 Region', 'GET', '', 'zuihou-authority-server', '/j2cache/removeRegion', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.J2CacheController', 'regions', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289188161697, '41a6d99e7437962ab827860f8a75e675', '慎用！获取所有的缓存！慎用！', '慎用！获取所有的缓存！慎用！', 'GET', '', 'zuihou-authority-server', '/j2cache/regions', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.J2CacheController', 'regions', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289213327553, '17d09a8fdbf1a0e5fa834d949b494edc', '查询登录日志', '查询登录日志', 'GET', '', 'zuihou-authority-server', '/loginLog/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.LoginLogController', 'get', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289234299105, 'a6d1efc30dd9c4ddf90308704f02605e', '删除日志', '根据id物理删除系统日志', 'DELETE', '', 'zuihou-authority-server', '/loginLog', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.LoginLogController', 'delete', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289259464961, 'b9c64c2bf86f0972c5dbb340189c13d3', '新增登录日志', '新增登录日志不为空的字段', 'GET', '', 'zuihou-authority-server', '/loginLog/success/{account}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.LoginLogController', 'save', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289284630817, '57cb19e3dd883966859e26aef2dd3314', '分页查询登录日志', '分页查询登录日志', 'GET', '', 'zuihou-authority-server', '/loginLog/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.LoginLogController', 'page', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289309796673, 'd237966c9b5a3ac3457617a971f7fe76', '查询系统日志', '查询系统日志', 'GET', '', 'zuihou-authority-server', '/optLog/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.OptLogController', 'get', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289334962529, '22ecf1aba27862c5d96157c4e2bc2980', '保存系统日志', '保存系统日志不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/optLog', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.OptLogController', 'save', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289389488513, '78791709fd8068efe06e594893c43bfe', '分页查询系统日志', '分页查询系统日志', 'GET', '', 'zuihou-authority-server', '/optLog/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.common.OptLogController', 'page', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289410460065, '56fa11905e37596ef87477694ae8042e', '查询组织', '查询组织', 'GET', '', 'zuihou-authority-server', '/org/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.core.OrgController', 'get', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289435625921, 'aa01ef8197a8c1fe940e74adbbc9eae6', '新增组织', '新增组织不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/org', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.core.OrgController', 'save', 0, '2019-12-17 11:46:46', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289569843681, 'd4ac74971c4b8a6d3119a980407dc97b', '查询系统所有的组织树', '查询系统所有的组织树', 'GET', '', 'zuihou-authority-server', '/org/tree', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.core.OrgController', 'tree', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289590815233, 'a3d80d7664a1c7ae151dfda59e93eb5c', '分页查询组织', '分页查询组织', 'GET', '', 'zuihou-authority-server', '/org/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.core.OrgController', 'page', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289615981089, '0837fac3482e18790e5f88269c707726', '查询岗位', '查询岗位', 'GET', '', 'zuihou-authority-server', '/station/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.core.StationController', 'get', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289641146945, 'a0e1f18110c2e946f4abf992aa0b645d', '新增岗位', '新增岗位不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/station', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.core.StationController', 'save', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289720838753, '43ecfbe3c832255d4dec8ad72bfef144', '分页查询岗位', '分页查询岗位', 'GET', '', 'zuihou-authority-server', '/station/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.core.StationController', 'page', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289741810305, '6f5ac6656ce6ad4f0cc7c84a611e001e', '批量删除', '批量删除', 'DELETE', '', 'zuihou-authority-server', '/globalUser/remove', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.defaults.GlobalUserController', 'remove', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289766976161, 'ff02088d4b7bb65d78ca18ddc5b1af04', '查询全局账号', '查询全局账号', 'GET', '', 'zuihou-authority-server', '/globalUser/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.defaults.GlobalUserController', 'get', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289787947713, 'be1504509761142dfe8ff960d4198720', '新增企业管理员', '新增企业管理员', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/globalUser', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.defaults.GlobalUserController', 'save', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289813113569, '4ccae4fa388815756e87ff4f548844f2', '检测账号是否可用', '检测账号是否可用', 'GET', '', 'zuihou-authority-server', '/globalUser/check', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.defaults.GlobalUserController', 'check', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289863445249, 'bb9a36501923558d89150ec08c81f24b', '分页查询全局账号', '分页查询全局账号', 'GET', '', 'zuihou-authority-server', '/globalUser/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.defaults.GlobalUserController', 'page', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289884416801, '05935d3e22bb13369d53adb098ca21e7', '批量删除企业', '批量删除企业', 'DELETE', '', 'zuihou-authority-server', '/tenant/remove', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.defaults.TenantController', 'remove', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289917971265, '0ca8d8991dc2e1b98adaf73e5413c664', '删除企业', '根据id物理删除企业', 'DELETE', '', 'zuihou-authority-server', '/tenant/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.defaults.TenantController', 'delete', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289943137121, 'a71d499f52d93019e8962420f68e767c', '新增企业', '新增企业不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/tenant', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.defaults.TenantController', 'save', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462289997663105, '7f751812592228508b04201cd6858ba2', '检测租户是否存在', '检测租户是否存在', 'GET', '', 'zuihou-authority-server', '/tenant/check/{code}', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.defaults.TenantController', 'check', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462290085743521, '14740fbaed12f08f68da5d093e73cb24', '初始化企业', '快速初始化企业', 'POST', 'application/json;charset=UTF-8', 'zuihou-authority-server', '/tenant/init', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.defaults.TenantController', 'saveInit', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462290106715073, '35782be3401018767662de6d75fe3b56', '分页查询企业', '分页查询企业', 'GET', '', 'zuihou-authority-server', '/tenant/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.authority.controller.defaults.TenantController', 'page', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:37');
INSERT INTO `c_auth_system_api` VALUES (656462290131880929, 'd6bd99b173a824cbb2c5a3c249723257', '查询全局账号', '查询全局账号', 'GET', '', 'zuihou-authority-server', '/systemApiScan', b'1', b'1', b'1', b'0', 'com.github.zuihou.scan.controller.SystemApiScanController', 'scan', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:38');
INSERT INTO `c_auth_system_api` VALUES (656462290152852481, '1e1df4a0465e8232d213fd174ed9f67b', 'swaggerResources', '', '', '', 'zuihou-authority-server', '/swagger-resources', b'1', b'1', b'1', b'0', 'springfox.documentation.swagger.web.ApiResourceController', 'swaggerResources', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:38');
INSERT INTO `c_auth_system_api` VALUES (656462290178018337, '0af79877f2389eba04df4e5996050b1c', 'uiConfiguration', '', '', '', 'zuihou-authority-server', '/swagger-resources/configuration/ui', b'1', b'1', b'1', b'0', 'springfox.documentation.swagger.web.ApiResourceController', 'uiConfiguration', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:38');
INSERT INTO `c_auth_system_api` VALUES (656462290198989889, 'a55f9c5ecb17ea5b6a580b8e3d3606de', 'securityConfiguration', '', '', '', 'zuihou-authority-server', '/swagger-resources/configuration/security', b'1', b'1', b'1', b'0', 'springfox.documentation.swagger.web.ApiResourceController', 'securityConfiguration', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:38');
INSERT INTO `c_auth_system_api` VALUES (656462290219961441, '8e9ab79556cf6ed3e8040b829b72147d', 'apiSorts', '', 'GET', 'application/json,application/hal+json', 'zuihou-authority-server', '/v2/api-docs-ext', b'1', b'1', b'1', b'0', 'com.github.xiaoymin.swaggerbootstrapui.web.SwaggerBootstrapUiController', 'apiSorts', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:38');
INSERT INTO `c_auth_system_api` VALUES (656462290245127297, 'a06429f6687dd2c6b3c3e8ca77d9143b', 'swaggerResources', '', '', '', 'zuihou-authority-server', '/swagger-resources-ext', b'1', b'1', b'1', b'0', 'com.github.xiaoymin.swaggerbootstrapui.web.SwaggerBootstrapUiResourceExtController', 'swaggerResources', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:38');
INSERT INTO `c_auth_system_api` VALUES (656462290266098849, 'ada25aa59870cb6f4d5071c437ee032e', 'standardByPathVar', '', '', '', 'zuihou-authority-server', '/form/validator/**', b'1', b'1', b'1', b'0', 'com.github.zuihou.validator.controller.FormValidatorController', 'standardByPathVar', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:38');
INSERT INTO `c_auth_system_api` VALUES (656462290287070401, '5f891759a7f414e5232cfef691d3b782', 'standardByQueryParam', '', '', '', 'zuihou-authority-server', '/form/validator', b'1', b'1', b'1', b'0', 'com.github.zuihou.validator.controller.FormValidatorController', 'standardByQueryParam', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:38');
INSERT INTO `c_auth_system_api` VALUES (656462290312236257, 'f9a0c1bfbfe0de1f41d0424f09a4ddaf', 'errorHtml', '', '', 'text/html', 'zuihou-authority-server', '/error', b'1', b'1', b'1', b'0', 'org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController', 'errorHtml', 0, '2019-12-17 11:46:47', 0, '2019-12-17 17:53:38');
INSERT INTO `c_auth_system_api` VALUES (656555549764091969, 'd8679a9715f07760b458e336daeee780', '获取当前系统所有枚举', '获取当前系统所有枚举', 'GET', '', 'zuihou-file-server', '/enums', b'1', b'1', b'1', b'0', 'com.github.zuihou.general.controller.GeneralController', 'enums', 1, '2019-12-17 17:57:21', 1, '2019-12-17 17:57:21');
INSERT INTO `c_auth_system_api` VALUES (656555549814423649, 'fe30263aa2af726f36893960452e85a5', '查询附件', '查询附件', 'GET', '', 'zuihou-file-server', '/attachment', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.AttachmentController', 'findAttachment', 1, '2019-12-17 17:57:21', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555549839589505, '0541f747414d2f8bd65f7cf6b6beb302', '根据文件id打包下载', '根据附件id下载多个打包的附件', 'GET', 'application/octet-stream', 'zuihou-file-server', '/attachment/download', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.AttachmentController', 'download', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555549868949665, '5465888589e3a73ada918f3b8c7e9303', '分页查询附件', '分页查询附件', 'GET', '', 'zuihou-file-server', '/attachment/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.AttachmentController', 'page', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555549894115521, 'e49facec7759d23e86518421a14c8697', '附件上传', '附件上传', 'POST', '', 'zuihou-file-server', '/attachment/upload', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.AttachmentController', 'upload', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555549923475681, '7749fa4c856990f1c61ee32d6b850b12', '根据业务类型或业务id删除文件', '根据业务类型或业务id删除文件', 'DELETE', 'application/json;charset=UTF-8', 'zuihou-file-server', '/attachment/biz', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.AttachmentController', 'removeByBizIdAndBizType', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555549986390273, '8ed7cd8e2ab67cda3358d0b52295215b', '根据业务类型或者业务id查询附件', '根据业务类型或者业务id查询附件', 'GET', '', 'zuihou-file-server', '/attachment/{type}', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.AttachmentController', 'findAttachmentByBiz', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550011556129, 'f3255a083eac0c22066b5235d331b043', '根据业务类型/业务id打包下载', '根据业务id下载一个文件或多个文件打包下载', 'GET', 'application/octet-stream', 'zuihou-file-server', '/attachment/download/biz', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.AttachmentController', 'downloadByBiz', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550045110593, '47084f7d346bc469a061f212264b374b', '根据url下载文件(不推荐)', '根据文件的url下载文件(不推荐使用，若要根据url下载，请执行通过nginx)', 'GET', 'application/octet-stream', 'zuihou-file-server', '/attachment/download/url', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.AttachmentController', 'downloadUrl', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550070276449, '197575590570fab327d8096e5c9927b5', '秒传接口，上传文件前先验证， 存在则启动秒传', '前端通过webUploader获取文件md5，上传前的验证', 'POST', '', 'zuihou-file-server', '/chunk/md5', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.FileChunkController', 'saveMd5Check', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550095442305, 'aca3b5926eeb14b09904b8555a1e4d03', '续传接口，检查每个分片存不存在', '断点续传功能检查分片是否存在， 已存在的分片无需重复上传， 达到续传效果', 'POST', 'application/json;charset=UTF-8', 'zuihou-file-server', '/chunk/check', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.FileChunkController', 'chunkCheck', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550128996769, '0c547341cab330eff01c254749361f33', '分片上传', '前端通过webUploader获取截取分片， 然后逐个上传', 'POST', '', 'zuihou-file-server', '/chunk/upload', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.FileChunkController', 'uploadFile', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550154162625, '7f5b96ab7424c7cd212cc59534ac5a64', '分片合并', '所有分片上传成功后，调用该接口对分片进行合并', 'POST', 'application/json;charset=UTF-8', 'zuihou-file-server', '/chunk/merge', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.FileChunkController', 'saveChunksMerge', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550179328481, 'afc331755a769dc4552ce530351224e9', '新增文件夹', '新增文件夹', 'POST', 'application/json;charset=UTF-8', 'zuihou-file-server', '/file', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.FileController', 'saveFolder', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550242243073, 'ca7ac9e6a6e93fdef37d38d067a61994', '下载一个文件或多个文件打包下载', '下载一个文件或多个文件打包下载', 'GET', 'application/octet-stream', 'zuihou-file-server', '/file/download', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.FileController', 'download', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550271603233, '2c068f97bc544e3fd0bceb2117e99262', '分页查询文件', '获取文件分页', 'GET', '', 'zuihou-file-server', '/file/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.FileController', 'page', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550296769089, '40d17ccf7d4812b794d452b33f02d757', '上传文件', '上传文件 ', 'POST', '', 'zuihou-file-server', '/file/upload', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.FileController', 'upload', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550351295073, 'b2d9df15580655c84bff209599471ca4', '根据Ids进行文件删除', '根据Ids进行文件删除  ', 'DELETE', '', 'zuihou-file-server', '/file/ids', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.FileController', 'removeList', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550384849537, 'a02e2e89fc855a03515fa8d0ee745644', 'test1', '', 'GET', '', 'zuihou-file-server', '/statistics/test1', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.StatisticsController', 'test1', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550405821089, 'e9bdf58a0df83d7367acb9c485a06510', '云盘首页数据概览', '云盘首页数据概览', 'GET', '', 'zuihou-file-server', '/statistics/overview', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.StatisticsController', 'overview', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550435181249, '99db9c807463eb65058938306da3e318', '按照类型，统计各种类型的 大小和数量', '按照类型，统计当前登录人各种类型的大小和数量', 'GET', '', 'zuihou-file-server', '/statistics/type', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.StatisticsController', 'findAllByDataType', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550460347105, '1b81f1f3c2e114ec87628034699df8a4', '云盘首页个人文件下载数量排行', '云盘首页个人文件下载数量排行', 'GET', '', 'zuihou-file-server', '/statistics/downTop20', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.StatisticsController', 'downTop20', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550485512961, 'ad8b7a8584d32aedefc474756495581c', '按照时间统计各种类型的文件的数量和大小', '按照时间统计各种类型的文件的数量和大小 不指定时间，默认查询一个月', 'GET', '', 'zuihou-file-server', '/statistics', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.StatisticsController', 'findNumAndSizeToTypeByDate', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550510678817, 'f41f21ae4af795a12188b00f015357b4', '按照时间统计下载数量', '按照时间统计下载数量 不指定时间，默认查询一个月', 'GET', '', 'zuihou-file-server', '/statistics/down', b'1', b'1', b'1', b'0', 'com.github.zuihou.file.controller.StatisticsController', 'findDownSizeByDate', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550535844673, 'c7fa721c17c50583f81181cee9ec84e3', '查询全局账号', '查询全局账号', 'GET', '', 'zuihou-file-server', '/systemApiScan', b'1', b'1', b'1', b'0', 'com.github.zuihou.scan.controller.SystemApiScanController', 'scan', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550561010529, 'd11806860eef7df55e428fade1f23099', 'swaggerResources', '', '', '', 'zuihou-file-server', '/swagger-resources', b'1', b'1', b'1', b'0', 'springfox.documentation.swagger.web.ApiResourceController', 'swaggerResources', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550594564993, '012fd3cf4028caf039484277283056e2', 'securityConfiguration', '', '', '', 'zuihou-file-server', '/swagger-resources/configuration/security', b'1', b'1', b'1', b'0', 'springfox.documentation.swagger.web.ApiResourceController', 'securityConfiguration', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550619730849, '2e7d7a28b5a6ff976dd7975040b04ecc', 'uiConfiguration', '', '', '', 'zuihou-file-server', '/swagger-resources/configuration/ui', b'1', b'1', b'1', b'0', 'springfox.documentation.swagger.web.ApiResourceController', 'uiConfiguration', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550644896705, '00a2363c780edc1fabbacc13fdfc6511', 'apiSorts', '', 'GET', 'application/json,application/hal+json', 'zuihou-file-server', '/v2/api-docs-ext', b'1', b'1', b'1', b'0', 'com.github.xiaoymin.swaggerbootstrapui.web.SwaggerBootstrapUiController', 'apiSorts', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550670062561, '8f3071198fea0275ea850859f727282b', 'swaggerResources', '', '', '', 'zuihou-file-server', '/swagger-resources-ext', b'1', b'1', b'1', b'0', 'com.github.xiaoymin.swaggerbootstrapui.web.SwaggerBootstrapUiResourceExtController', 'swaggerResources', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550695228417, '75c72d9b47acea22e08717ae57b7e9a1', 'standardByQueryParam', '', '', '', 'zuihou-file-server', '/form/validator', b'1', b'1', b'1', b'0', 'com.github.zuihou.validator.controller.FormValidatorController', 'standardByQueryParam', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550720394273, 'f9e8de707f343f52d8098b28597b6437', 'standardByPathVar', '', '', '', 'zuihou-file-server', '/form/validator/**', b'1', b'1', b'1', b'0', 'com.github.zuihou.validator.controller.FormValidatorController', 'standardByPathVar', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555550745560129, '42a441ea69d4d9dbda7a8cce1736a8df', 'errorHtml', '', '', 'text/html', 'zuihou-file-server', '/error', b'1', b'1', b'1', b'0', 'org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController', 'errorHtml', 1, '2019-12-17 17:57:22', 1, '2019-12-17 17:57:22');
INSERT INTO `c_auth_system_api` VALUES (656555605061796993, 'b2cdcdc15c80544802d3e2b778edb06b', '获取当前系统所有枚举', '获取当前系统所有枚举', 'GET', '', 'zuihou-msgs-server', '/enums', b'1', b'1', b'1', b'0', 'com.github.zuihou.general.controller.GeneralController', 'enums', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605091157153, '76295cb22847f3632c44f5e9010153a7', '单体查询', '单体查询', 'GET', '', 'zuihou-msgs-server', '/smsSendStatus/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.sms.controller.SmsSendStatusController', 'get', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605116323009, 'c02b67af6d8b4f6bea8a3e61a6d3acac', '分页查询', '分页查询', 'GET', '', 'zuihou-msgs-server', '/smsSendStatus/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.sms.controller.SmsSendStatusController', 'page', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605137294561, '53e5663ab19784705cddf38f973d180d', '查询发送任务', '查询发送任务', 'GET', '', 'zuihou-msgs-server', '/smsTask/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.sms.controller.SmsTaskController', 'get', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605158266113, '0a66c9794b063560d44842e92eb94caa', '新增发送任务', '新增发送任务不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-msgs-server', '/smsTask', b'1', b'1', b'1', b'0', 'com.github.zuihou.sms.controller.SmsTaskController', 'save', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605242152225, '55da27255f4b0ebe88c77179573eb979', '发送短信', '短信发送，需要先在短信系统，或者短信数据库中进行配置供应商和模板', 'POST', 'application/json;charset=UTF-8', 'zuihou-msgs-server', '/smsTask/send', b'1', b'1', b'1', b'0', 'com.github.zuihou.sms.controller.SmsTaskController', 'save', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605267318081, '79b9f8d462bc4940153398babd5ab134', '分页查询发送任务', '分页查询发送任务', 'GET', '', 'zuihou-msgs-server', '/smsTask/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.sms.controller.SmsTaskController', 'page', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605288289633, 'b6432e7a44ce6296f10d608294e9555c', '查询短信模板', '查询短信模板', 'GET', '', 'zuihou-msgs-server', '/smsTemplate/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.sms.controller.SmsTemplateController', 'get', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605313455489, 'bafc44db1c36582bcdd31ae1784a02aa', '新增短信模板', '新增短信模板不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-msgs-server', '/smsTemplate', b'1', b'1', b'1', b'0', 'com.github.zuihou.sms.controller.SmsTemplateController', 'save', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605372175777, '73473ae1f0e77922f0748cfaa6f35212', '检测自定义编码是否存在', '检测自定义编码是否存在', 'GET', '', 'zuihou-msgs-server', '/smsTemplate/check', b'1', b'1', b'1', b'0', 'com.github.zuihou.sms.controller.SmsTemplateController', 'check', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605435090369, 'b11c00889fbb19782c2eb55068546816', '分页查询短信模板', '分页查询短信模板', 'GET', '', 'zuihou-msgs-server', '/smsTemplate/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.sms.controller.SmsTemplateController', 'page', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605460256225, '64d5a733ee05b88a86759302c4405bfb', '发送验证码', '发送验证码', 'POST', 'application/json;charset=UTF-8', 'zuihou-msgs-server', '/verification/send', b'1', b'1', b'1', b'0', 'com.github.zuihou.sms.controller.VerificationCodeController', 'send', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605481227777, 'cc6e51c0a0f9d92b2f8870ebf94f399f', '验证验证码', '验证验证码', 'POST', 'application/json;charset=UTF-8', 'zuihou-msgs-server', '/verification', b'1', b'1', b'1', b'0', 'com.github.zuihou.sms.controller.VerificationCodeController', 'verification', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605577696801, '85be7f60d75adb77e5f64ea9e5567ac7', '查询消息中心', '查询消息中心', 'GET', '', 'zuihou-msgs-server', '/msgsCenterInfo/{id}', b'1', b'1', b'1', b'0', 'com.github.zuihou.msgs.controller.MsgsCenterInfoController', 'get', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605611251265, 'e3cee880c68823e754c590cd731d9ffc', '新增消息中心', '新增消息中心不为空的字段', 'POST', 'application/json;charset=UTF-8', 'zuihou-msgs-server', '/msgsCenterInfo', b'1', b'1', b'1', b'0', 'com.github.zuihou.msgs.controller.MsgsCenterInfoController', 'save', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605674165857, '8365ec96f575ec023cb0c9f8e691b2dc', '标记消息为已读', '标记消息为已读', 'GET', '', 'zuihou-msgs-server', '/msgsCenterInfo/mark', b'1', b'1', b'1', b'0', 'com.github.zuihou.msgs.controller.MsgsCenterInfoController', 'mark', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605695137409, '94e31e297518e975c6a86131e69d4ae8', '分页查询消息中心', '分页查询消息中心', 'GET', '', 'zuihou-msgs-server', '/msgsCenterInfo/page', b'1', b'1', b'1', b'0', 'com.github.zuihou.msgs.controller.MsgsCenterInfoController', 'page', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605720303265, 'a202a364732389685a4eb3b246ac4e30', '查询全局账号', '查询全局账号', 'GET', '', 'zuihou-msgs-server', '/systemApiScan', b'1', b'1', b'1', b'0', 'com.github.zuihou.scan.controller.SystemApiScanController', 'scan', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605741274817, '710efb000e20f6dfe955f1d9d4b7d73f', 'securityConfiguration', '', '', '', 'zuihou-msgs-server', '/swagger-resources/configuration/security', b'1', b'1', b'1', b'0', 'springfox.documentation.swagger.web.ApiResourceController', 'securityConfiguration', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605766440673, 'bdd8ccfd6442d9510abe978d7ce77a68', 'uiConfiguration', '', '', '', 'zuihou-msgs-server', '/swagger-resources/configuration/ui', b'1', b'1', b'1', b'0', 'springfox.documentation.swagger.web.ApiResourceController', 'uiConfiguration', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605787412225, 'd1517e83be60effce3150f404349e568', 'swaggerResources', '', '', '', 'zuihou-msgs-server', '/swagger-resources', b'1', b'1', b'1', b'0', 'springfox.documentation.swagger.web.ApiResourceController', 'swaggerResources', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605812578081, '029a28562aa0f45b7f8576fc91266621', 'apiSorts', '', 'GET', 'application/json,application/hal+json', 'zuihou-msgs-server', '/v2/api-docs-ext', b'1', b'1', b'1', b'0', 'com.github.xiaoymin.swaggerbootstrapui.web.SwaggerBootstrapUiController', 'apiSorts', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605833549633, '63713e831b50bd2957bf3321b8630070', 'swaggerResources', '', '', '', 'zuihou-msgs-server', '/swagger-resources-ext', b'1', b'1', b'1', b'0', 'com.github.xiaoymin.swaggerbootstrapui.web.SwaggerBootstrapUiResourceExtController', 'swaggerResources', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605858715489, '8968f67314271a198f8a5ae0ad173f98', 'standardByPathVar', '', '', '', 'zuihou-msgs-server', '/form/validator/**', b'1', b'1', b'1', b'0', 'com.github.zuihou.validator.controller.FormValidatorController', 'standardByPathVar', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605879687041, '53eba9d9edf9bdf6268f0ba899eae582', 'standardByQueryParam', '', '', '', 'zuihou-msgs-server', '/form/validator', b'1', b'1', b'1', b'0', 'com.github.zuihou.validator.controller.FormValidatorController', 'standardByQueryParam', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
INSERT INTO `c_auth_system_api` VALUES (656555605900658593, '6bb57ff8eec65907fb91fb706632ba37', 'errorHtml', '', '', 'text/html', 'zuihou-msgs-server', '/error', b'1', b'1', b'1', b'0', 'org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController', 'errorHtml', 1, '2019-12-17 17:57:35', 1, '2019-12-17 17:57:35');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_user
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_user`;
CREATE TABLE `c_auth_user` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `account` varchar(30) NOT NULL COMMENT '账号',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织ID\n#c_core_org\n@InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD) RemoteData<Long, com.github.zuihou.authority.entity.core.Org>',
  `station_id` bigint(20) DEFAULT NULL COMMENT '岗位ID\n#c_core_station\n@InjectionField(api = STATION_ID_CLASS, method = STATION_ID_METHOD) RemoteData<Long, com.github.zuihou.authority.entity.core.Station>',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT '' COMMENT '手机',
  `sex` varchar(1) DEFAULT 'N' COMMENT '性别\n#Sex{W:女;M:男;N:未知}',
  `status` bit(1) DEFAULT b'0' COMMENT '启用状态 1启用 0禁用',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像',
  `work_describe` varchar(255) DEFAULT '' COMMENT '工作描述\r\n比如：  市长、管理员、局长等等   用于登陆展示',
  `password_error_last_time` datetime DEFAULT NULL COMMENT '最后一次输错密码时间',
  `password_error_num` int(11) DEFAULT '0' COMMENT '密码错误次数',
  `password_expire_time` datetime DEFAULT NULL COMMENT '密码过期时间',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_ACCOUNT` (`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of c_auth_user
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_user` VALUES (3, 'zuihou', '平台超管', 100, 100, '244387061@qq.com', '15218869992', 'W', b'1', 'BiazfanxmamNRoxxVxka.png', '疯狂加班3~', '2020-02-06 16:40:31', 0, NULL, 'd9d17d88918aa72834289edaf38f42e2', '2020-02-06 16:40:33', 1, '2019-09-02 11:32:02', 3, '2019-12-11 18:00:25');
INSERT INTO `c_auth_user` VALUES (641577229343523041, 'test', '总经理', 102, 100, '', '', 'N', b'1', 'http://127.0.0.1:10000/file/0000/2019/11/c8df3238-ebca-42b3-baeb-37896468f028.png', '', '2019-12-21 16:45:13', 0, NULL, 'd9d17d88918aa72834289edaf38f42e2', '2019-12-21 16:45:14', 3, '2019-11-06 09:58:56', 3, '2019-11-26 11:02:42');
INSERT INTO `c_auth_user` VALUES (641590096981656001, 'manong', '码农', 643776594376135105, 642032719487828225, '', '', 'M', b'1', 'http://192.168.1.34:10000/file/0000/2019/11/6a759cd8-40f6-46d2-9487-6bd18a6695f2.jpg', '122', '2019-11-15 20:56:40', 0, NULL, 'd9d17d88918aa72834289edaf38f42e2', '2019-11-16 09:28:22', 3, '2019-11-06 10:50:01', 3, '2019-11-26 20:27:48');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_user_role`;
CREATE TABLE `c_auth_user_role` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色ID\n#c_auth_role',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID\n#c_core_accou',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `IDX_KEY` (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色分配\r\n账号角色绑定';

-- ----------------------------
-- Records of c_auth_user_role
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_user_role` VALUES (634707503061401697, 100, 3, 1, '2019-10-18 11:01:01');
INSERT INTO `c_auth_user_role` VALUES (644985983216910529, 643779012732130273, 641590096981656001, 3, '2019-11-15 19:44:02');
INSERT INTO `c_auth_user_role` VALUES (645202375769861985, 645198153556958497, 641577229343523041, 3, '2019-11-16 10:03:54');
INSERT INTO `c_auth_user_role` VALUES (646807547461776193, 646807483838377697, 641590096981656001, 3, '2019-11-20 20:22:17');
INSERT INTO `c_auth_user_role` VALUES (647449120218284897, 647449059488957153, 641577229343523041, 641577229343523041, '2019-11-22 14:51:39');
INSERT INTO `c_auth_user_role` VALUES (660170774677554369, 660170723091808833, 641590096981656001, 3, '2019-12-27 17:22:58');
COMMIT;

-- ----------------------------
-- Table structure for c_common_area
-- ----------------------------
DROP TABLE IF EXISTS `c_common_area`;
CREATE TABLE `c_common_area` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `code` varchar(64) NOT NULL DEFAULT '' COMMENT '编码',
  `label` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `full_name` varchar(255) DEFAULT '' COMMENT '全名',
  `sort_value` int(11) DEFAULT '1' COMMENT '排序',
  `longitude` varchar(255) DEFAULT '' COMMENT '经度',
  `latitude` varchar(255) DEFAULT '' COMMENT '维度',
  `level` varchar(10) DEFAULT '' COMMENT '行政区级\n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>\n\n',
  `source_` varchar(255) DEFAULT NULL COMMENT '数据来源',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_CODE` (`code`),
  KEY `IDX_PARENT_ID` (`parent_id`,`label`) USING BTREE COMMENT '查询'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='地区表';

-- ----------------------------
-- Table structure for c_common_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `c_common_dictionary`;
CREATE TABLE `c_common_dictionary` (
  `id` bigint(20) NOT NULL,
  `code` varchar(64) NOT NULL DEFAULT '' COMMENT '编码\r\n一颗树仅仅有一个统一的编码',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `describe_` varchar(200) DEFAULT '' COMMENT '描述',
  `status_` bit(1) DEFAULT b'1' COMMENT '状态',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典目录';

-- ----------------------------
-- Records of c_common_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `c_common_dictionary` VALUES (1, 'NATION', '民族', '民族', b'1', 1, '2019-06-01 09:42:50', 1, '2019-06-01 09:42:54');
INSERT INTO `c_common_dictionary` VALUES (2, 'POSITION_STATUS', '在职状态', NULL, b'1', 1, '2019-06-04 11:37:15', 1, '2019-06-04 11:37:15');
INSERT INTO `c_common_dictionary` VALUES (3, 'EDUCATION', '学历', NULL, b'1', 1, '2019-06-04 11:33:52', 1, '2019-06-04 11:33:52');
INSERT INTO `c_common_dictionary` VALUES (668835143804256897, 'AREA_LEVEL', '行政区级', '行政区级', b'1', 3, '2020-01-20 15:12:05', 3, '2020-01-20 15:12:05');
COMMIT;

-- ----------------------------
-- Table structure for c_common_dictionary_item
-- ----------------------------
DROP TABLE IF EXISTS `c_common_dictionary_item`;
CREATE TABLE `c_common_dictionary_item` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `dictionary_id` bigint(20) NOT NULL COMMENT '字典id',
  `dictionary_code` varchar(64) NOT NULL COMMENT '字典编码',
  `code` varchar(64) NOT NULL DEFAULT '' COMMENT '编码',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `status_` bit(1) DEFAULT b'1' COMMENT '状态',
  `describe_` varchar(255) DEFAULT '' COMMENT '描述',
  `sort_value` int(11) DEFAULT '1' COMMENT '排序',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `dict_code_item_code_uniq` (`dictionary_code`,`code`) USING BTREE COMMENT '字典编码与字典项目编码联合唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典项';

-- ----------------------------
-- Records of c_common_dictionary_item
-- ----------------------------
BEGIN;
INSERT INTO `c_common_dictionary_item` VALUES (40, 3, 'EDUCATION', 'COLLEGE', '本科', b'1', '', 2, 1, '2019-06-04 11:36:19', 1, '2019-06-04 11:36:19');
INSERT INTO `c_common_dictionary_item` VALUES (41, 3, 'EDUCATION', 'BOSHI', '博士', b'1', '', 1, 1, '2019-06-04 11:36:29', 1, '2019-06-04 11:36:29');
INSERT INTO `c_common_dictionary_item` VALUES (43, 1, 'NATION', 'mz_hanz', '汉族', b'1', '汉族', 0, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (44, 1, 'NATION', 'mz_zz', '壮族', b'1', '壮族', 1, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (45, 1, 'NATION', 'mz_mz', '满族', b'1', '满族', 2, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (46, 1, 'NATION', 'mz_hz', '回族', b'1', '回族', 3, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (47, 1, 'NATION', 'mz_miaoz', '苗族', b'1', '苗族', 4, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (48, 1, 'NATION', 'mz_wwez', '维吾尔族', b'1', '', 5, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (49, 1, 'NATION', 'mz_tjz', '土家族', b'1', '', 6, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (50, 1, 'NATION', 'mz_yz', '彝族', b'1', '', 7, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (51, 1, 'NATION', 'mz_mgz', '蒙古族', b'1', '', 8, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (52, 1, 'NATION', 'mz_zhangz', '藏族', b'1', '', 9, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (53, 1, 'NATION', 'mz_byz', '布依族', b'1', '', 10, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (54, 1, 'NATION', 'mz_dz', '侗族', b'1', '', 11, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (55, 1, 'NATION', 'mz_yaoz', '瑶族', b'1', '', 12, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (56, 1, 'NATION', 'mz_cxz', '朝鲜族', b'1', '', 13, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (57, 1, 'NATION', 'mz_bz', '白族', b'1', '', 14, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (58, 1, 'NATION', 'mz_hnz', '哈尼族', b'1', '', 15, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (59, 1, 'NATION', 'mz_hskz', '哈萨克族', b'1', '', 16, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (60, 1, 'NATION', 'mz_lz', '黎族', b'1', '', 17, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (61, 1, 'NATION', 'mz_daiz', '傣族', b'1', '', 18, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (62, 1, 'NATION', 'mz_sz', '畲族', b'1', '', 19, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (63, 1, 'NATION', 'mz_llz', '傈僳族', b'1', '', 20, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (64, 1, 'NATION', 'mz_glz', '仡佬族', b'1', '', 21, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (65, 1, 'NATION', 'mz_dxz', '东乡族', b'1', '', 22, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (66, 1, 'NATION', 'mz_gsz', '高山族', b'1', '', 23, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (67, 1, 'NATION', 'mz_lhz', '拉祜族', b'1', '', 24, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (68, 1, 'NATION', 'mz_shuiz', '水族', b'1', '', 25, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (69, 1, 'NATION', 'mz_wz', '佤族', b'1', '', 26, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (70, 1, 'NATION', 'mz_nxz', '纳西族', b'1', '', 27, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (71, 1, 'NATION', 'mz_qz', '羌族', b'1', '', 28, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (72, 1, 'NATION', 'mz_tz', '土族', b'1', '', 29, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (73, 1, 'NATION', 'mz_zlz', '仫佬族', b'1', '', 30, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (74, 1, 'NATION', 'mz_xbz', '锡伯族', b'1', '', 31, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (75, 1, 'NATION', 'mz_kehzz', '柯尔克孜族', b'1', '', 32, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (76, 1, 'NATION', 'mz_dwz', '达斡尔族', b'1', '', 33, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (77, 1, 'NATION', 'mz_jpz', '景颇族', b'1', '', 34, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (78, 1, 'NATION', 'mz_mlz', '毛南族', b'1', '', 35, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (79, 1, 'NATION', 'mz_slz', '撒拉族', b'1', '', 36, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (80, 1, 'NATION', 'mz_tjkz', '塔吉克族', b'1', '', 37, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (81, 1, 'NATION', 'mz_acz', '阿昌族', b'1', '', 38, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (82, 1, 'NATION', 'mz_pmz', '普米族', b'1', '', 39, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (83, 1, 'NATION', 'mz_ewkz', '鄂温克族', b'1', '', 40, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (84, 1, 'NATION', 'mz_nz', '怒族', b'1', '', 41, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (85, 1, 'NATION', 'mz_jz', '京族', b'1', '', 42, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (86, 1, 'NATION', 'mz_jnz', '基诺族', b'1', '', 43, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (87, 1, 'NATION', 'mz_daz', '德昂族', b'1', '', 44, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (88, 1, 'NATION', 'mz_baz', '保安族', b'1', '', 45, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (89, 1, 'NATION', 'mz_elsz', '俄罗斯族', b'1', '', 46, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (90, 1, 'NATION', 'mz_ygz', '裕固族', b'1', '', 47, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (91, 1, 'NATION', 'mz_wzbkz', '乌兹别克族', b'1', '', 48, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (92, 1, 'NATION', 'mz_mbz', '门巴族', b'1', '', 49, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (93, 1, 'NATION', 'mz_elcz', '鄂伦春族', b'1', '', 50, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (94, 1, 'NATION', 'mz_dlz', '独龙族', b'1', '', 51, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (95, 1, 'NATION', 'mz_tkez', '塔塔尔族', b'1', '', 52, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (96, 1, 'NATION', 'mz_hzz', '赫哲族', b'1', '', 53, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (97, 1, 'NATION', 'mz_lbz', '珞巴族', b'1', '', 54, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (98, 1, 'NATION', 'mz_blz', '布朗族', b'1', '', 55, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (99, 2, 'POSITION_STATUS', 'WORKING', '在职', b'1', '', 1, 1, '2019-06-04 11:38:16', 1, '2019-06-04 11:38:16');
INSERT INTO `c_common_dictionary_item` VALUES (100, 2, 'POSITION_STATUS', 'LEAVE', '离职', b'1', '', 2, 1, '2019-06-04 11:38:50', 1, '2019-06-04 11:38:50');
INSERT INTO `c_common_dictionary_item` VALUES (668835361035649825, 668835143804256897, 'AREA_LEVEL', 'COUNTRY', '国家', b'1', '', 1, 3, '2020-01-20 15:12:57', 3, '2020-01-20 15:12:57');
INSERT INTO `c_common_dictionary_item` VALUES (668835565717685121, 668835143804256897, 'AREA_LEVEL', 'PROVINCE', '省份/直辖市', b'1', '', 2, 3, '2020-01-20 15:13:45', 3, '2020-01-20 15:13:45');
INSERT INTO `c_common_dictionary_item` VALUES (668835693622985697, 668835143804256897, 'AREA_LEVEL', 'CITY', '地市', b'1', '', 3, 3, '2020-01-20 15:14:16', 3, '2020-01-20 15:14:16');
INSERT INTO `c_common_dictionary_item` VALUES (668835854646510657, 668835143804256897, 'AREA_LEVEL', 'COUNTY', '区县', b'1', '', 4, 3, '2020-01-20 15:14:54', 3, '2020-01-20 15:14:54');
COMMIT;

-- ----------------------------
-- Table structure for c_common_login_log
-- ----------------------------
DROP TABLE IF EXISTS `c_common_login_log`;
CREATE TABLE `c_common_login_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `request_ip` varchar(50) DEFAULT '' COMMENT '操作IP',
  `user_id` bigint(20) DEFAULT NULL COMMENT '登录人ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '登录人姓名',
  `account` varchar(30) DEFAULT '' COMMENT '登录人账号',
  `description` varchar(255) DEFAULT '' COMMENT '登录描述',
  `login_date` date DEFAULT NULL COMMENT '登录时间',
  `ua` varchar(500) DEFAULT '0' COMMENT '浏览器请求头',
  `browser` varchar(255) DEFAULT NULL COMMENT '浏览器名称',
  `browser_version` varchar(255) DEFAULT NULL COMMENT '浏览器版本',
  `operating_system` varchar(255) DEFAULT NULL COMMENT '操作系统',
  `location` varchar(50) DEFAULT '' COMMENT '登录地点',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `IDX_BROWSER` (`browser`),
  KEY `IDX_OPERATING` (`operating_system`),
  KEY `IDX_LOGIN_DATE` (`login_date`,`account`) USING BTREE,
  KEY `IDX_ACCOUNT_IP` (`account`,`request_ip`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='登录日志';

-- ----------------------------
-- Table structure for c_common_opt_log
-- ----------------------------
DROP TABLE IF EXISTS `c_common_opt_log`;
CREATE TABLE `c_common_opt_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `request_ip` varchar(50) DEFAULT '' COMMENT '操作IP',
  `type` varchar(5) DEFAULT 'OPT' COMMENT '日志类型\n#LogType{OPT:操作类型;EX:异常类型}',
  `user_name` varchar(50) DEFAULT '' COMMENT '操作人',
  `description` varchar(255) DEFAULT '' COMMENT '操作描述',
  `class_path` varchar(255) DEFAULT '' COMMENT '类路径',
  `action_method` varchar(50) DEFAULT '' COMMENT '请求方法',
  `request_uri` varchar(50) DEFAULT '' COMMENT '请求地址',
  `http_method` varchar(10) DEFAULT 'GET' COMMENT '请求类型\n#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}',
  `params` longtext COMMENT '请求参数',
  `result` longtext COMMENT '返回值',
  `ex_desc` longtext COMMENT '异常详情信息',
  `ex_detail` longtext COMMENT '异常描述',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `finish_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `consuming_time` bigint(20) DEFAULT '0' COMMENT '消耗时间',
  `ua` varchar(500) DEFAULT '' COMMENT '浏览器',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_type` (`type`) USING BTREE COMMENT '日志类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统日志';

-- ----------------------------
-- Table structure for c_common_parameter
-- ----------------------------
DROP TABLE IF EXISTS `c_common_parameter`;
CREATE TABLE `c_common_parameter` (
  `id` bigint(20) NOT NULL,
  `key_` varchar(255) NOT NULL DEFAULT '' COMMENT '参数键',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '参数名称',
  `value` varchar(255) NOT NULL COMMENT '参数值',
  `describe_` varchar(200) DEFAULT '' COMMENT '描述',
  `status_` bit(1) DEFAULT b'1' COMMENT '状态',
  `readonly_` bit(1) DEFAULT NULL COMMENT '只读',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='参数配置';

-- ----------------------------
-- Table structure for c_core_org
-- ----------------------------
DROP TABLE IF EXISTS `c_core_org`;
CREATE TABLE `c_core_org` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `abbreviation` varchar(255) DEFAULT '' COMMENT '简称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父ID',
  `tree_path` varchar(255) DEFAULT ',' COMMENT '树结构',
  `sort_value` int(11) DEFAULT '1' COMMENT '排序',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `describe_` varchar(255) DEFAULT '' COMMENT '描述',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `FU_PATH` (`tree_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织';

-- ----------------------------
-- Records of c_core_org
-- ----------------------------
BEGIN;
INSERT INTO `c_core_org` VALUES (100, '最后集团股份有限公司', '最后集团2', 0, ',', 1, b'1', '初始化数据', '2019-07-10 17:02:18', 1, '2019-07-10 17:02:16', 1);
INSERT INTO `c_core_org` VALUES (101, '最后集团股份有限公司广州子公司', '广州最后集团', 100, ',100,', 0, b'1', '初始化数据', '2019-08-06 09:10:53', 1, '2019-11-12 11:36:48', 3);
INSERT INTO `c_core_org` VALUES (102, '最后集团股份有限公司北京分公司', '北京最后集团', 100, ',100,', 1, b'1', '初始化数据', '2019-11-07 16:13:09', 1, '2019-11-07 16:13:12', 1);
INSERT INTO `c_core_org` VALUES (643775612976106881, '综合部', '', 101, ',100,101,', 0, b'1', '前台&HR', '2019-11-12 11:34:27', 3, '2019-11-12 11:34:27', 3);
INSERT INTO `c_core_org` VALUES (643775664683486689, '管理层', '', 100, ',100,', 3, b'1', '', '2019-11-12 11:34:39', 3, '2019-11-12 11:36:16', 3);
INSERT INTO `c_core_org` VALUES (643775904077582049, '总经办', '', 100, ',100,', 2, b'1', '', '2019-11-12 11:35:37', 3, '2019-11-12 11:36:52', 3);
INSERT INTO `c_core_org` VALUES (643776324342648929, '财务部', '', 100, ',100,', 4, b'1', '', '2019-11-12 11:37:17', 3, '2019-11-12 11:37:40', 3);
INSERT INTO `c_core_org` VALUES (643776407691858113, '市场部', '', 100, ',100,', 5, b'1', '', '2019-11-12 11:37:37', 3, '2019-11-12 11:37:37', 3);
INSERT INTO `c_core_org` VALUES (643776508795556193, '销售部', '', 100, ',100,', 6, b'1', '', '2019-11-12 11:38:01', 3, '2019-11-12 11:38:01', 3);
INSERT INTO `c_core_org` VALUES (643776594376135105, '研发部', '', 101, ',100,101,', 1, b'1', '', '2019-11-12 11:38:21', 3, '2019-11-12 11:38:21', 3);
INSERT INTO `c_core_org` VALUES (643776633823564321, '产品部', '', 101, ',100,101,', 2, b'1', '', '2019-11-12 11:38:31', 3, '2019-11-12 11:38:31', 3);
INSERT INTO `c_core_org` VALUES (643776668917305985, '综合部', '', 102, ',100,102,', 0, b'1', '', '2019-11-12 11:38:39', 3, '2019-11-12 11:38:39', 3);
INSERT INTO `c_core_org` VALUES (643776713909605089, '研发部', '', 102, ',100,102,', 0, b'1', '', '2019-11-12 11:38:50', 3, '2019-11-12 11:38:50', 3);
INSERT INTO `c_core_org` VALUES (643776757199016769, '销售部', '', 102, ',100,102,', 2, b'1', '', '2019-11-12 11:39:00', 3, '2019-11-12 11:39:00', 3);
COMMIT;

-- ----------------------------
-- Table structure for c_core_station
-- ----------------------------
DROP TABLE IF EXISTS `c_core_station`;
CREATE TABLE `c_core_station` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `org_id` bigint(20) DEFAULT '0' COMMENT '组织ID\n#c_core_org',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `describe_` varchar(255) DEFAULT '' COMMENT '描述',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位';

-- ----------------------------
-- Records of c_core_station
-- ----------------------------
BEGIN;
INSERT INTO `c_core_station` VALUES (100, '总经理', 643775904077582049, b'1', '总部-1把手', '2019-07-10 17:03:03', 1, '2019-11-16 09:59:17', 3);
INSERT INTO `c_core_station` VALUES (101, '副总经理', 643775904077582049, b'1', '总部-2把手', '2019-07-22 17:07:55', 1, '2019-11-16 09:59:21', 3);
INSERT INTO `c_core_station` VALUES (642032719487828225, '研发经理', 643776594376135105, b'1', '子公司-研发部老大', '2019-11-07 16:08:49', 3, '2019-11-16 09:53:42', 3);
INSERT INTO `c_core_station` VALUES (645199319300842081, '副总经理', 101, b'1', '子公司-老大', '2019-11-16 09:51:45', 3, '2019-11-16 09:53:50', 3);
INSERT INTO `c_core_station` VALUES (645199745026892801, '产品经理', 643776633823564321, b'1', '子公司-产品部老大', '2019-11-16 09:53:27', 3, '2019-11-16 09:54:01', 3);
INSERT INTO `c_core_station` VALUES (645200064280536545, '人事经理', 643775612976106881, b'1', '子公司-综合老大', '2019-11-16 09:54:43', 3, '2019-11-16 09:54:43', 3);
INSERT INTO `c_core_station` VALUES (645200151886964289, 'Java工程师', 643776594376135105, b'1', '普通员工', '2019-11-16 09:55:04', 3, '2019-11-16 09:55:04', 3);
INSERT INTO `c_core_station` VALUES (645200250243393185, '需求工程师', 643776633823564321, b'1', '普通员工', '2019-11-16 09:55:27', 3, '2019-11-16 09:55:27', 3);
INSERT INTO `c_core_station` VALUES (645200304014370561, 'UI工程师', 643776633823564321, b'1', '普通员工', '2019-11-16 09:55:40', 3, '2019-11-16 09:55:40', 3);
INSERT INTO `c_core_station` VALUES (645200358959753057, '运维工程师', 643776594376135105, b'1', '普通员工', '2019-11-16 09:55:53', 3, '2019-11-16 09:55:53', 3);
INSERT INTO `c_core_station` VALUES (645200405453612993, '前台小姐姐', 643775612976106881, b'1', '普通员工', '2019-11-16 09:56:04', 3, '2019-11-16 09:56:04', 3);
INSERT INTO `c_core_station` VALUES (645200545698555937, '人事经理', 643776668917305985, b'1', '北京分公司-综合部老大', '2019-11-16 09:56:38', 3, '2019-11-16 09:56:38', 3);
INSERT INTO `c_core_station` VALUES (645200670781089921, '研发经理', 643776713909605089, b'1', '北京分公司-研发部老大', '2019-11-16 09:57:07', 3, '2019-11-16 09:57:07', 3);
INSERT INTO `c_core_station` VALUES (645200806559099105, '销售经理', 643776757199016769, b'1', '北京销售部老大', '2019-11-16 09:57:40', 3, '2019-11-16 09:57:40', 3);
INSERT INTO `c_core_station` VALUES (645200885772724545, '行政', 643776668917305985, b'1', '普通员工', '2019-11-16 09:57:59', 3, '2019-11-16 09:57:59', 3);
INSERT INTO `c_core_station` VALUES (645200938289605025, '大前端工程师', 643776713909605089, b'1', '普通员工', '2019-11-16 09:58:11', 3, '2019-11-16 09:58:11', 3);
INSERT INTO `c_core_station` VALUES (645201064705927681, '销售员工', 643776757199016769, b'1', '普通员工', '2019-11-16 09:58:41', 3, '2019-11-16 09:58:41', 3);
INSERT INTO `c_core_station` VALUES (645201184268757601, '销售总监', 643775664683486689, b'1', '总部2把手', '2019-11-16 09:59:10', 3, '2019-11-16 09:59:10', 3);
INSERT INTO `c_core_station` VALUES (645201307765844833, '财务总监', 643776324342648929, b'1', '总部2把手', '2019-11-16 09:59:39', 3, '2019-11-16 09:59:39', 3);
INSERT INTO `c_core_station` VALUES (645201405757369281, '市场经理', 643776407691858113, b'1', '总部市场部老大', '2019-11-16 10:00:03', 3, '2019-11-16 10:00:03', 3);
INSERT INTO `c_core_station` VALUES (645201481133206561, '销售总监', 643776508795556193, b'1', '总部销售部老大', '2019-11-16 10:00:21', 3, '2019-11-16 10:00:21', 3);
INSERT INTO `c_core_station` VALUES (645201573391117441, '前端工程师', 643776594376135105, b'1', '普通员工', '2019-11-16 10:00:43', 3, '2019-11-16 10:00:43', 3);
COMMIT;

-- ----------------------------
-- Table structure for f_attachment
-- ----------------------------
DROP TABLE IF EXISTS `f_attachment`;
CREATE TABLE `f_attachment` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `biz_id` varchar(64) DEFAULT NULL COMMENT '业务ID',
  `biz_type` varchar(255) DEFAULT NULL COMMENT '业务类型\n#AttachmentType',
  `data_type` varchar(255) DEFAULT 'IMAGE' COMMENT '数据类型\n#DataType{DIR:目录;IMAGE:图片;VIDEO:视频;AUDIO:音频;DOC:文档;OTHER:其他}',
  `submitted_file_name` varchar(255) DEFAULT '' COMMENT '原始文件名',
  `group_` varchar(255) DEFAULT '' COMMENT 'FastDFS返回的组\n用于FastDFS',
  `path` varchar(255) DEFAULT '' COMMENT 'FastDFS的远程文件名\n用于FastDFS',
  `relative_path` varchar(255) DEFAULT '' COMMENT '文件相对路径',
  `url` varchar(255) DEFAULT '' COMMENT '文件访问链接\n需要通过nginx配置路由，才能访问',
  `file_md5` varchar(255) DEFAULT NULL COMMENT '文件md5值',
  `context_type` varchar(255) DEFAULT '' COMMENT '文件上传类型\n取上传文件的值',
  `filename` varchar(255) DEFAULT '' COMMENT '唯一文件名',
  `ext` varchar(64) DEFAULT '' COMMENT '后缀\n (没有.)',
  `size` bigint(20) DEFAULT '0' COMMENT '大小',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织ID\n#c_core_org',
  `icon` varchar(64) DEFAULT '' COMMENT '图标',
  `create_month` varchar(10) DEFAULT NULL COMMENT '创建年月\n格式：yyyy-MM 用于统计',
  `create_week` varchar(10) DEFAULT NULL COMMENT '创建时处于当年的第几周\nyyyy-ww 用于统计',
  `create_day` varchar(12) DEFAULT NULL COMMENT '创建年月日\n格式： yyyy-MM-dd 用于统计',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `update_user` bigint(11) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='附件';

-- ----------------------------
-- Records of f_attachment
-- ----------------------------
BEGIN;
INSERT INTO `f_attachment` VALUES (662723528918827457, '662723528595866017', 'USER_AVATAR', 'IMAGE', '本图片超过6M，翻转60度时报错.jpg', '', '', '0000/2020/01', 'http://192.168.1.34:10000/file/0000/2020/01/3241a5df-4c86-4075-9355-18da455ad672.jpg', NULL, 'image/jpeg', '3241a5df-4c86-4075-9355-18da455ad672.jpg', 'jpg', 6707195, 100, 'el-icon-picture', '2020年01月', '2020年01周', '2020年01月03日', '2020-01-03 18:26:42', 3, '2020-01-03 18:26:42', 3);
INSERT INTO `f_attachment` VALUES (662723654345294337, '662723654110413281', 'USER_AVATAR', 'IMAGE', '本图片超过6M，翻转60度时报错.jpg', '', '', '0000/2020/01', 'http://192.168.1.34:10000/file/0000/2020/01/8df7b1a3-8630-4fe9-8139-be8405a9aff6.jpg', NULL, 'image/jpeg', '8df7b1a3-8630-4fe9-8139-be8405a9aff6.jpg', 'jpg', 6707195, 100, 'el-icon-picture', '2020年01月', '2020年01周', '2020年01月03日', '2020-01-03 18:27:12', 3, '2020-01-03 18:27:12', 3);
INSERT INTO `f_attachment` VALUES (662724113101488737, '662723691150311969', 'USER_AVATAR', 'IMAGE', 'IDEA.png', '', '', '0000/2020/01', 'http://192.168.1.34:10000/file/0000/2020/01/eaa548b3-7e9a-44a0-9067-8ff58fdf8522.png', NULL, 'image/png', 'eaa548b3-7e9a-44a0-9067-8ff58fdf8522.png', 'png', 240790, 100, 'el-icon-picture', '2020年01月', '2020年01周', '2020年01月03日', '2020-01-03 18:29:02', 3, '2020-01-03 18:29:02', 3);
INSERT INTO `f_attachment` VALUES (662724826359661217, '648841103860041025', 'USER_AVATAR', 'IMAGE', '111x.png', '', '', '0000/2020/01', 'http://192.168.1.34:10000/file/0000/2020/01/e36deac6-ceca-4816-80f9-d6892dac127c.png', NULL, 'image/png', 'e36deac6-ceca-4816-80f9-d6892dac127c.png', 'png', 215675, 100, 'el-icon-picture', '2020年01月', '2020年01周', '2020年01月03日', '2020-01-03 18:31:52', 3, '2020-01-03 18:31:52', 3);
INSERT INTO `f_attachment` VALUES (664490768831873089, '664490768693461025', '123', 'IMAGE', '2.jpg', '', '', '0000/2020/01', 'http://192.168.1.34:10000/file/0000/2020/01/ddc3b3cd-43e6-4d2b-962a-2eadb556eada.jpg', NULL, 'image/jpeg', 'ddc3b3cd-43e6-4d2b-962a-2eadb556eada.jpg', 'jpg', 7973, 100, 'el-icon-picture', '2020年01月', '2020年02周', '2020年01月08日', '2020-01-08 15:29:05', 3, '2020-01-08 15:29:05', 3);
INSERT INTO `f_attachment` VALUES (667653459616464929, 'null', '111', 'DOC', 'zuihou-admin-cloud微服务SaaS脚手架开发手册-01160939.pdf', '', '', '0000/2020/01', 'http://192.168.1.34:10000/file/0000/2020/01/dc76ace8-0708-4f33-ae5e-e8023a19ee17.pdf', NULL, 'application/pdf', 'dc76ace8-0708-4f33-ae5e-e8023a19ee17.pdf', 'pdf', 37063890, 0, 'el-icon-question', '2020年01月', '2020年03周', '2020年01月17日', '2020-01-17 08:56:29', 0, '2020-01-17 08:56:29', 0);
COMMIT;

-- ----------------------------
-- Table structure for f_file
-- ----------------------------
DROP TABLE IF EXISTS `f_file`;
CREATE TABLE `f_file` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `data_type` varchar(255) DEFAULT 'IMAGE' COMMENT '数据类型\n#DataType{DIR:目录;IMAGE:图片;VIDEO:视频;AUDIO:音频;DOC:文档;OTHER:其他}',
  `submitted_file_name` varchar(255) DEFAULT '' COMMENT '原始文件名',
  `tree_path` varchar(255) DEFAULT ',' COMMENT '父目录层级关系',
  `grade` int(11) DEFAULT '1' COMMENT '层级等级\n从1开始计算',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除\n#BooleanStatus{TRUE:1,已删除;FALSE:0,未删除}',
  `folder_id` bigint(20) DEFAULT '0' COMMENT '父文件夹ID',
  `url` varchar(1000) DEFAULT '' COMMENT '文件访问链接\n需要通过nginx配置路由，才能访问',
  `size` bigint(20) DEFAULT '0' COMMENT '文件大小\n单位字节',
  `folder_name` varchar(255) DEFAULT '' COMMENT '父文件夹名称',
  `group_` varchar(255) DEFAULT '' COMMENT 'FastDFS组\n用于FastDFS',
  `path` varchar(255) DEFAULT '' COMMENT 'FastDFS远程文件名\n用于FastDFS',
  `relative_path` varchar(255) DEFAULT '' COMMENT '文件的相对路径 ',
  `file_md5` varchar(255) DEFAULT '' COMMENT 'md5值',
  `context_type` varchar(255) DEFAULT '' COMMENT '文件类型\n取上传文件的值',
  `filename` varchar(255) DEFAULT '' COMMENT '唯一文件名',
  `ext` varchar(64) DEFAULT '' COMMENT '文件名后缀 \n(没有.)',
  `icon` varchar(64) DEFAULT '' COMMENT '文件图标\n用于云盘显示',
  `create_month` varchar(10) DEFAULT NULL COMMENT '创建时年月\n格式：yyyy-MM 用于统计',
  `create_week` varchar(10) DEFAULT NULL COMMENT '创建时年周\nyyyy-ww 用于统计',
  `create_day` varchar(12) DEFAULT NULL COMMENT '创建时年月日\n格式： yyyy-MM-dd 用于统计',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE,
  FULLTEXT KEY `FU_TREE_PATH` (`tree_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件表';

-- ----------------------------
-- Table structure for m_order
-- ----------------------------
DROP TABLE IF EXISTS `m_order`;
CREATE TABLE `m_order` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `code` varchar(255) DEFAULT NULL COMMENT '编号',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='订单(用于测试)';

-- ----------------------------
-- Records of m_order
-- ----------------------------
BEGIN;
INSERT INTO `m_order` VALUES (663793429196570657, '123', '12', '2020-01-06 17:18:06', 0, '2020-01-06 17:18:06', 0);
COMMIT;

-- ----------------------------
-- Table structure for m_product
-- ----------------------------
DROP TABLE IF EXISTS `m_product`;
CREATE TABLE `m_product` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `test1` text,
  `test2` longtext,
  `test3` bit(1) DEFAULT NULL,
  `test4` tinyint(10) DEFAULT NULL,
  `test5` date DEFAULT NULL COMMENT '时间',
  `test6` datetime DEFAULT NULL COMMENT '日期',
  `parent_id` bigint(20) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `sort_value` int(11) DEFAULT NULL,
  `test7` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品(用于测试)';

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
  `biz_type` varchar(64) DEFAULT NULL COMMENT '业务类型\n#MsgsBizType{USER_LOCK:账号锁定;USER_REG:账号申请;WORK_APPROVAL:考勤审批;}',
  `msgs_center_type` varchar(20) NOT NULL DEFAULT 'NOTIFY' COMMENT '消息类型\n#MsgsCenterType{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `content` text COMMENT '内容',
  `author` varchar(50) DEFAULT '' COMMENT '作者',
  `handler_url` varchar(255) DEFAULT '' COMMENT '处理地址\n以http开头时直接跳转，否则与#c_application表拼接后跳转\nhttp可带参数',
  `handler_params` varchar(400) DEFAULT '' COMMENT '处理参数',
  `is_single_handle` bit(1) DEFAULT b'1' COMMENT '是否单人处理',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='消息中心表';

-- ----------------------------
-- Records of msgs_center_info
-- ----------------------------
BEGIN;
INSERT INTO `msgs_center_info` VALUES (657993096268611617, '', NULL, 'PUBLICITY', '123', '<p>123123</p>', '平台超管', '', '', b'1', '2019-12-21 17:09:39', 3, '2019-12-21 17:09:39', 3);
INSERT INTO `msgs_center_info` VALUES (657994440748564865, '', NULL, 'NOTIFY', '11', '<p>123</p>', '平台超管', '', '', b'1', '2019-12-21 17:15:00', 3, '2019-12-21 17:15:00', 3);
INSERT INTO `msgs_center_info` VALUES (657994543320269249, '', 'USER_LOCK', 'NOTIFY', '你有通知了', '<p>123123</p>', '平台超管', '', '', b'1', '2019-12-21 17:15:24', 3, '2019-12-21 17:15:24', 3);
INSERT INTO `msgs_center_info` VALUES (658003398183878689, '', 'USER_LOCK', 'PUBLICITY', '你有通知了', '<h2>项目代码地址</h2>\n<table>\n<thead>\n<tr>\n<th>项目</th>\n<th>gitee</th>\n<th>github</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>微服务项目</td>\n<td><a href=\"https://gitee.com/zuihou111/zuihou-admin-cloud\">https://gitee.com/zuihou111/zuihou-admin-cloud</a></td>\n<td><a href=\"https://github.com/zuihou/zuihou-admin-cloud\">https://github.com/zuihou/zuihou-admin-cloud</a></td>\n</tr>\n<tr>\n<td>单体项目</td>\n<td><a href=\"https://gitee.com/zuihou111/zuihou-admin-boot\">https://gitee.com/zuihou111/zuihou-admin-boot</a></td>\n<td><a href=\"https://github.com/zuihou/zuihou-admin-boot\">https://github.com/zuihou/zuihou-admin-boot</a></td>\n</tr>\n<tr>\n<td>租户后台</td>\n<td><a href=\"https://gitee.com/zuihou111/zuihou-ui\">https://gitee.com/zuihou111/zuihou-ui</a></td>\n<td><a href=\"https://github.com/zuihou/zuihou-ui\">https://github.com/zuihou/zuihou-ui</a></td>\n</tr>\n<tr>\n<td>开发&amp;运营后台</td>\n<td><a href=\"https://gitee.com/zuihou111/zuihou-admin-ui\">https://gitee.com/zuihou111/zuihou-admin-ui</a></td>\n<td><a href=\"https://github.com/zuihou/zuihou-admin-ui\">https://github.com/zuihou/zuihou-admin-ui</a></td>\n</tr>\n<tr>\n<td>代码生成器</td>\n<td>无</td>\n<td><a href=\"https://github.com/zuihou/zuihou-generator\">https://github.com/zuihou/zuihou-generator</a></td>\n</tr>\n</tbody>\n</table>', '平台超管', '', '', b'1', '2019-12-21 17:50:35', 3, '2019-12-21 17:50:35', 3);
INSERT INTO `msgs_center_info` VALUES (658003767093887169, '', NULL, 'PUBLICITY', '你有通知了', '<h2>项目代码地址</h2>\n<table>\n<thead>\n<tr>\n<th>项目</th>\n<th>gitee</th>\n<th>github</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>微服务项目</td>\n<td><a href=\"https://gitee.com/zuihou111/zuihou-admin-cloud\">https://gitee.com/zuihou111/zuihou-admin-cloud</a></td>\n<td><a href=\"https://github.com/zuihou/zuihou-admin-cloud\">https://github.com/zuihou/zuihou-admin-cloud</a></td>\n</tr>\n<tr>\n<td>单体项目</td>\n<td><a href=\"https://gitee.com/zuihou111/zuihou-admin-boot\">https://gitee.com/zuihou111/zuihou-admin-boot</a></td>\n<td><a href=\"https://github.com/zuihou/zuihou-admin-boot\">https://github.com/zuihou/zuihou-admin-boot</a></td>\n</tr>\n<tr>\n<td>租户后台</td>\n<td><a href=\"https://gitee.com/zuihou111/zuihou-ui\">https://gitee.com/zuihou111/zuihou-ui</a></td>\n<td><a href=\"https://github.com/zuihou/zuihou-ui\">https://github.com/zuihou/zuihou-ui</a></td>\n</tr>\n<tr>\n<td>开发&amp;运营后台</td>\n<td><a href=\"https://gitee.com/zuihou111/zuihou-admin-ui\">https://gitee.com/zuihou111/zuihou-admin-ui</a></td>\n<td><a href=\"https://github.com/zuihou/zuihou-admin-ui\">https://github.com/zuihou/zuihou-admin-ui</a></td>\n</tr>\n<tr>\n<td>代码生成器</td>\n<td>无</td>\n<td><a href=\"https://github.com/zuihou/zuihou-generator\">https://github.com/zuihou/zuihou-generator</a></td>\n</tr>\n</tbody>\n</table>', '平台超管', '', '', b'1', '2019-12-21 17:52:03', 3, '2019-12-21 17:52:03', 3);
INSERT INTO `msgs_center_info` VALUES (660150352108060705, '', 'USER_LOCK', 'PUBLICITY', '123', '', '123', '', '', b'1', '2019-12-27 16:01:49', 0, '2019-12-27 16:01:49', 0);
INSERT INTO `msgs_center_info` VALUES (660171417777602593, '123', 'USER_REG', 'NOTIFY', '123', '<p>123</p>', '123', '123', '', b'1', '2019-12-27 17:25:32', 3, '2019-12-27 17:25:32', 3);
INSERT INTO `msgs_center_info` VALUES (662356073251864609, '', 'USER_LOCK', 'PUBLICITY', '1231', '<p>123</p>', '平台超管', '', '', b'1', '2020-01-02 18:06:34', 3, '2020-01-02 18:06:34', 3);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='消息中心接收表';

-- ----------------------------
-- Records of msgs_center_info_receive
-- ----------------------------
BEGIN;
INSERT INTO `msgs_center_info_receive` VALUES (657993096457355329, 657993096268611617, 641577229343523041, b'0', '2019-12-21 17:09:39', 3, 3, '2019-12-21 17:09:39');
INSERT INTO `msgs_center_info_receive` VALUES (657993096516075649, 657993096268611617, 648841103860041025, b'0', '2019-12-21 17:09:39', 3, 3, '2019-12-21 17:09:39');
INSERT INTO `msgs_center_info_receive` VALUES (657993096520269985, 657993096268611617, 641590096981656001, b'0', '2019-12-21 17:09:39', 3, 3, '2019-12-21 17:09:39');
INSERT INTO `msgs_center_info_receive` VALUES (657994440777925025, 657994440748564865, 3, b'1', '2019-12-21 17:15:00', 3, 3, '2019-12-21 17:25:47');
INSERT INTO `msgs_center_info_receive` VALUES (657994543337046497, 657994543320269249, -1, b'0', '2019-12-21 17:15:24', 3, 3, '2019-12-21 17:15:24');
INSERT INTO `msgs_center_info_receive` VALUES (657994543349629441, 657994543320269249, 641577229343523041, b'0', '2019-12-21 17:15:24', 3, 3, '2019-12-21 17:15:24');
INSERT INTO `msgs_center_info_receive` VALUES (657994543349629473, 657994543320269249, 3, b'1', '2019-12-21 17:15:24', 3, 3, '2019-12-21 17:26:25');
INSERT INTO `msgs_center_info_receive` VALUES (657994543349629505, 657994543320269249, 648841103860041025, b'0', '2019-12-21 17:15:24', 3, 3, '2019-12-21 17:15:24');
INSERT INTO `msgs_center_info_receive` VALUES (657994543353823841, 657994543320269249, 641590096981656001, b'0', '2019-12-21 17:15:24', 3, 3, '2019-12-21 17:15:24');
INSERT INTO `msgs_center_info_receive` VALUES (658003398490062913, 658003398183878689, 641577229343523041, b'0', '2019-12-21 17:50:36', 3, 3, '2019-12-21 17:50:36');
INSERT INTO `msgs_center_info_receive` VALUES (658003398511034465, 658003398183878689, 3, b'1', '2019-12-21 17:50:36', 3, 3, '2019-12-21 17:50:38');
INSERT INTO `msgs_center_info_receive` VALUES (658003398511034497, 658003398183878689, 648841103860041025, b'0', '2019-12-21 17:50:36', 3, 3, '2019-12-21 17:50:36');
INSERT INTO `msgs_center_info_receive` VALUES (658003398515228833, 658003398183878689, 641590096981656001, b'0', '2019-12-21 17:50:36', 3, 3, '2019-12-21 17:50:36');
INSERT INTO `msgs_center_info_receive` VALUES (658003767119053025, 658003767093887169, 641577229343523041, b'0', '2019-12-21 17:52:03', 3, 3, '2019-12-21 17:52:03');
INSERT INTO `msgs_center_info_receive` VALUES (658003767127441665, 658003767093887169, 3, b'1', '2019-12-21 17:52:03', 3, 3, '2019-12-21 17:52:06');
INSERT INTO `msgs_center_info_receive` VALUES (658003767131636001, 658003767093887169, 648841103860041025, b'0', '2019-12-21 17:52:03', 3, 3, '2019-12-21 17:52:03');
INSERT INTO `msgs_center_info_receive` VALUES (658003767131636033, 658003767093887169, 641590096981656001, b'0', '2019-12-21 17:52:03', 3, 3, '2019-12-21 17:52:03');
INSERT INTO `msgs_center_info_receive` VALUES (660150352640737345, 660150352108060705, 641577229343523041, b'0', '2019-12-27 16:01:49', 0, 0, '2019-12-27 16:01:49');
INSERT INTO `msgs_center_info_receive` VALUES (660150352682680417, 660150352108060705, 3, b'0', '2019-12-27 16:01:49', 0, 0, '2019-12-27 16:01:49');
INSERT INTO `msgs_center_info_receive` VALUES (660150352695263361, 660150352108060705, 648841103860041025, b'0', '2019-12-27 16:01:49', 0, 0, '2019-12-27 16:01:49');
INSERT INTO `msgs_center_info_receive` VALUES (660150352699457697, 660150352108060705, 641590096981656001, b'0', '2019-12-27 16:01:49', 0, 0, '2019-12-27 16:01:49');
INSERT INTO `msgs_center_info_receive` VALUES (660171417878265921, 660171417777602593, 648841103860041025, b'0', '2019-12-27 17:25:32', 3, 3, '2019-12-27 17:25:32');
INSERT INTO `msgs_center_info_receive` VALUES (662356073348333633, 662356073251864609, 641577229343523041, b'0', '2020-01-02 18:06:34', 3, 3, '2020-01-02 18:06:34');
INSERT INTO `msgs_center_info_receive` VALUES (662356073373499489, 662356073251864609, 3, b'0', '2020-01-02 18:06:34', 3, 3, '2020-01-02 18:06:34');
INSERT INTO `msgs_center_info_receive` VALUES (662356073377693825, 662356073251864609, 648841103860041025, b'0', '2020-01-02 18:06:34', 3, 3, '2020-01-02 18:06:34');
INSERT INTO `msgs_center_info_receive` VALUES (662356073381888161, 662356073251864609, 641590096981656001, b'0', '2020-01-02 18:06:34', 3, 3, '2020-01-02 18:06:34');
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
INSERT INTO `sms_send_status` VALUES (648846066950602817, 648846064387883041, 'FAIL', '13012329970', '', '', '', 'InvalidAccessKeyId.NotFound : Specified access key is not found.\r\nRequestId : F4A63CF6-6551-456C-809D-68725CFCFEBC', 0, '', '', '', 3, '2019-11-26 11:22:38', 3, '2019-11-26 11:22:38');
INSERT INTO `sms_send_status` VALUES (648931758980464705, 648929627745550369, 'FAIL', '15218869970', '', '', '', 'InvalidAccessKeyId.NotFound : Specified access key is not found.\r\nRequestId : 13C29092-EAAA-4B4C-AF74-9E7A53DC4345', 0, '', '', '', 3, '2019-11-26 17:03:08', 3, '2019-11-26 17:03:08');
INSERT INTO `sms_send_status` VALUES (648934358828187745, 648934277576130625, 'SUCCESS', '15218869970', '18:e3ba23fe6ac04182866a82c91c9e3db3', '', '0', 'OK', 1, '', '', '', 3, '2019-11-26 17:13:28', 3, '2019-11-26 17:13:28');
INSERT INTO `sms_send_status` VALUES (648934939563131041, 648934938309034113, 'SUCCESS', '13012341234', '2056:132579002015747597463914123', '', '0', 'OK', 1, '', '', '', 3, '2019-11-26 17:15:46', 3, '2019-11-26 17:15:46');
INSERT INTO `sms_send_status` VALUES (648935276659343585, 648935275669487809, 'FAIL', '12345', '', '', '1016', '手机号格式错误', 0, '', '', '', 3, '2019-11-26 17:17:07', 3, '2019-11-26 17:17:07');
INSERT INTO `sms_send_status` VALUES (664490372465950785, 664490370163277857, 'FAIL', '15218876690', '', '', '', 'JSONObject[\"result\"] not found.', 0, '', '', '', 3, '2020-01-08 15:27:31', 3, '2020-01-08 15:27:31');
COMMIT;

-- ----------------------------
-- Table structure for sms_task
-- ----------------------------
DROP TABLE IF EXISTS `sms_task`;
CREATE TABLE `sms_task` (
  `id` bigint(20) NOT NULL COMMENT '短信记录ID',
  `template_id` bigint(20) NOT NULL COMMENT '模板ID\n#sms_template',
  `status` varchar(10) DEFAULT 'WAITING' COMMENT '执行状态\n(手机号具体发送状态看sms_send_status表) \n#TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}',
  `source_type` varchar(10) DEFAULT 'APP' COMMENT '来源类型\n#SourceType{APP:应用;SERVICE:服务}\n',
  `receiver` text COMMENT '接收者手机号\n群发用英文逗号分割.\n支持2种格式:\n1: 手机号,手机号 \n2: 姓名<手机号>,姓名<手机号>',
  `topic` varchar(255) DEFAULT '' COMMENT '主题',
  `template_params` varchar(500) DEFAULT '' COMMENT '参数 \n需要封装为{‘key’:’value’, ...}格式\n且key必须有序\n\n',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `content` varchar(500) DEFAULT '' COMMENT '发送内容\n需要封装正确格式化: 您好，张三，您有一个新的快递。',
  `draft` bit(1) DEFAULT b'0' COMMENT '是否草稿',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='发送任务\n所有的短息发送调用，都视为是一次短信任务，任务表只保存数据和执行状态等信息，\n具体的发送状态查看发送状态（#sms_send_status）表';

-- ----------------------------
-- Records of sms_task
-- ----------------------------
BEGIN;
INSERT INTO `sms_task` VALUES (648846064387883041, 606767517569908769, 'SUCCESS', 'APP', '13012329970', '你好', '{\"code\":\"1111\"}', NULL, '尊敬的用户，欢迎注册闪购网，您的注册验证码：1111,有效期5分钟。请勿将短信验证码告知他人！', b'0', 3, '2019-11-26 11:22:37', 3, '2019-11-26 11:22:38');
INSERT INTO `sms_task` VALUES (648929627745550369, 648846241915994209, 'SUCCESS', 'APP', '15218869970', '123', '{\"xx\":\"123\"}', NULL, '使用 123 作为占位符', b'0', 3, '2019-11-26 16:54:40', 3, '2019-11-26 17:03:08');
INSERT INTO `sms_task` VALUES (648934277576130625, 648933936084287521, 'SUCCESS', 'APP', '15218869970', '哈农', '{\"1\":\"您好，你的短信验证码为：123456\"}', NULL, '您好，你的短信验证码为：123456', b'0', 3, '2019-11-26 17:13:09', 3, '2019-11-26 17:13:28');
INSERT INTO `sms_task` VALUES (648935275669487809, 648933936084287521, 'SUCCESS', 'APP', '12345', '哈农', '{\"1\":\"您好，你的短信验证码为：123456\"}', NULL, '您好，你的短信验证码为：123456', b'0', 3, '2019-11-26 17:17:07', 3, '2019-11-26 17:17:07');
INSERT INTO `sms_task` VALUES (664490370163277857, 648933936084287521, 'SUCCESS', 'APP', '15218876690', '123', '{\"1\":\"123\"}', NULL, '123', b'0', 3, '2020-01-08 15:27:30', 3, '2020-01-08 15:27:31');
COMMIT;

-- ----------------------------
-- Table structure for sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template` (
  `id` bigint(20) NOT NULL COMMENT '模板ID',
  `provider_type` varchar(10) NOT NULL COMMENT '供应商类型\n#ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}',
  `app_id` varchar(255) NOT NULL COMMENT '应用ID',
  `app_secret` varchar(255) NOT NULL COMMENT '应用密码',
  `url` varchar(255) DEFAULT '' COMMENT 'SMS服务域名\n百度、其他厂商会用',
  `custom_code` varchar(20) NOT NULL DEFAULT '' COMMENT '模板编码\n用于api发送',
  `name` varchar(255) DEFAULT '' COMMENT '模板名称',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '模板内容',
  `template_params` varchar(255) NOT NULL DEFAULT '' COMMENT '模板参数',
  `template_code` varchar(50) NOT NULL DEFAULT '' COMMENT '模板CODE',
  `sign_name` varchar(100) DEFAULT '' COMMENT '签名',
  `template_describe` varchar(255) DEFAULT '' COMMENT '备注',
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
INSERT INTO `sms_template` VALUES (606767517569908769, 'ALI', '12', '121', NULL, 'SGW_REG', '闪购网', '尊敬的用户，欢迎注册闪购网，您的注册验证码：${code},有效期5分钟。请勿将短信验证码告知他人！', '{\"code\":\"\"}', 'SMS_99185070', '闪购网', '注册验证码', 0, '2019-08-02 08:37:30', 0, '2019-08-02 08:37:30');
INSERT INTO `sms_template` VALUES (648933936084287521, 'TENCENT', 'test', 'test', '', 'HCQ_MT_REG', 'ce', '{1}', '{\"1\":\"\"}', '2559551', '测试使用', '有短信账号的朋友欢迎赞助，用于演示服务器', 3, '2019-11-26 17:11:47', 3, '2019-11-26 17:18:06');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='AT transaction mode undo table';

SET FOREIGN_KEY_CHECKS = 1;
