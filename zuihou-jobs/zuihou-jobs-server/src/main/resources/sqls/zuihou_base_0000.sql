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

 Date: 10/03/2020 23:39:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_auth_application
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_application`;
CREATE TABLE `c_auth_application` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
-- Table structure for c_auth_application_system_api
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_application_system_api`;
CREATE TABLE `c_auth_application_system_api` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `label` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `describe_` varchar(200) DEFAULT '' COMMENT '描述',
  `is_public` bit(1) DEFAULT b'0' COMMENT '公共菜单\nTrue是无需分配所有人就可以访问的',
  `path` varchar(255) DEFAULT '' COMMENT '路径',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `is_enable` bit(1) DEFAULT b'1' COMMENT '状态',
  `sort_value` int(11) DEFAULT '1' COMMENT '排序',
  `icon` varchar(255) DEFAULT '' COMMENT '菜单图标',
  `group_` varchar(20) DEFAULT '' COMMENT '分组',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父级菜单ID',
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
INSERT INTO `c_auth_menu` VALUES (101, '0000', '用户中心', '用户组织机构', b'0', '/user', 'Layout', b'1', 1, 'el-icon-user-solid', '', 0, 1, '2019-07-25 15:35:12', 3, '2019-11-11 14:32:02');
INSERT INTO `c_auth_menu` VALUES (102, '0000', '权限管理', '管理权限相关', b'0', '/auth', 'Layout', b'1', 2, 'el-icon-lock', '', 0, 1, '2019-07-27 11:48:49', 3, '2019-11-11 14:35:39');
INSERT INTO `c_auth_menu` VALUES (103, '0000', '基础配置', '基础的配置', b'0', '/base', 'Layout', b'1', 3, 'el-icon-set-up', '', 0, 1, '2019-11-11 14:38:29', 3, '2019-11-11 14:35:42');
INSERT INTO `c_auth_menu` VALUES (104, '0000', '开发者管理', '开发者', b'0', '/developer', 'Layout', b'1', 4, 'el-icon-user-solid', '', 0, 1, '2019-11-11 14:38:34', 3, '2019-11-11 14:35:44');
INSERT INTO `c_auth_menu` VALUES (105, '0000', '消息中心', '站内信', b'0', '/msgs', 'Layout', b'1', 5, 'el-icon-chat-line-square', '', 0, 1, '2019-11-11 14:38:32', 3, '2019-11-11 14:35:47');
INSERT INTO `c_auth_menu` VALUES (106, '0000', '短信中心', '短信接口', b'0', '/sms', 'Layout', b'1', 6, 'el-icon-chat-line-round', '', 0, 1, '2019-11-11 14:38:36', 3, '2019-11-11 14:35:49');
INSERT INTO `c_auth_menu` VALUES (107, '0000', '文件中心', '附件接口', b'0', '/file', 'Layout', b'1', 7, 'el-icon-folder-add', '', 0, 1, '2019-11-11 14:38:38', 3, '2019-11-11 14:35:51');
INSERT INTO `c_auth_menu` VALUES (603976297063910529, '0000', '菜单配置', '', b'0', '/auth/menu', 'zuihou/auth/menu/Index', b'1', 0, '', '', 102, 1, '2019-07-25 15:46:11', 3, '2019-11-11 14:31:52');
INSERT INTO `c_auth_menu` VALUES (603981723864141121, '0000', '角色管理', '', b'0', '/auth/role', 'zuihou/auth/role/Index', b'1', 1, '', '', 102, 1, '2019-07-25 16:07:45', 3, '2019-11-11 14:31:57');
INSERT INTO `c_auth_menu` VALUES (603982542332235201, '0000', '组织管理', '', b'0', '/user/org', 'zuihou/user/org/Index', b'1', 0, '', '', 101, 1, '2019-07-25 16:11:00', 3, '2019-11-11 14:28:40');
INSERT INTO `c_auth_menu` VALUES (603982713849908801, '0000', '岗位管理', '', b'0', '/user/station', 'zuihou/user/station/Index', b'1', 1, '', '', 101, 1, '2019-07-25 16:11:41', 3, '2019-11-11 14:28:43');
INSERT INTO `c_auth_menu` VALUES (603983082961243905, '0000', '用户管理', '', b'0', '/user/user', 'zuihou/user/user/Index', b'1', 2, '', '', 101, 1, '2019-07-25 16:13:09', 3, '2019-11-11 14:28:49');
INSERT INTO `c_auth_menu` VALUES (605078371293987105, '0000', '数据字典维护', '', b'0', '/base/dict', 'zuihou/base/dict/Index', b'1', 0, '', '', 103, 1, '2019-07-28 16:45:26', 3, '2019-11-11 14:34:23');
INSERT INTO `c_auth_menu` VALUES (605078463069552993, '0000', '地区信息维护', '', b'0', '/base/area', 'zuihou/base/area/Index', b'1', 1, '', '', 103, 1, '2019-07-28 16:45:48', 3, '2019-11-11 14:34:26');
INSERT INTO `c_auth_menu` VALUES (605078538881597857, '0000', '应用管理', '', b'0', '/developer/application', 'zuihou/developer/application/Index', b'1', 0, '', '', 104, 1, '2019-07-28 16:46:06', 3, '2019-12-25 16:19:43');
INSERT INTO `c_auth_menu` VALUES (605078672772170209, '0000', '操作日志', '', b'0', '/developer/optLog', 'zuihou/developer/optLog/Index', b'1', 3, '', '', 104, 1, '2019-07-28 16:46:38', 3, '2019-11-11 14:35:14');
INSERT INTO `c_auth_menu` VALUES (605078979149300257, '0000', '数据库监控', '', b'0', '/developer/db', 'zuihou/developer/db/Index', b'1', 2, '', '', 104, 1, '2019-07-28 16:47:51', 3, '2019-11-16 16:35:50');
INSERT INTO `c_auth_menu` VALUES (605079239015793249, '0000', '接口文档', '', b'0', 'http://tangyh.top:10000/api/gate/doc.html', 'Layout', b'1', 5, '', '', 104, 1, '2019-07-28 16:48:53', 3, '2019-11-16 10:55:03');
INSERT INTO `c_auth_menu` VALUES (605079411338773153, '0000', '注册&配置中心', '', b'0', 'http://tangyh.top:10000/nacos', 'Layout', b'1', 6, '', '', 104, 1, '2019-07-28 16:49:34', 3, '2019-11-16 10:55:06');
INSERT INTO `c_auth_menu` VALUES (605079545585861345, '0000', '缓存监控', '', b'0', 'http://www.baidu.com', 'Layout', b'1', 7, '', '', 104, 1, '2019-07-28 16:50:06', 3, '2019-11-16 10:55:08');
INSERT INTO `c_auth_menu` VALUES (605079658416833313, '0000', '服务器监控', '', b'0', 'http://127.0.0.1:8762/zuihou-monitor', 'Layout', b'1', 8, '', '', 104, 1, '2019-07-28 16:50:33', 3, '2019-11-16 10:55:15');
INSERT INTO `c_auth_menu` VALUES (605079751035454305, '0000', '消息推送', '', b'0', '/msgs/sendMsgs', 'zuihou/msgs/sendMsgs/Index', b'1', 0, '', '', 105, 1, '2019-07-28 16:50:55', 3, '2019-11-11 14:28:30');
INSERT INTO `c_auth_menu` VALUES (605080023753294753, '0000', '我的消息', '', b'0', '/msgs/myMsgs', 'zuihou/msgs/myMsgs/Index', b'1', 1, '', '', 105, 1, '2019-07-28 16:52:00', 3, '2019-11-11 14:28:27');
INSERT INTO `c_auth_menu` VALUES (605080107379327969, '0000', '账号配置', '', b'0', '/sms/template', 'zuihou/sms/template/Index', b'1', 1, '', '', 106, 1, '2019-07-28 16:52:20', 3, '2019-11-21 19:53:17');
INSERT INTO `c_auth_menu` VALUES (605080359394083937, '0000', '短信管理', '', b'0', '/sms/manage', 'zuihou/sms/manage/Index', b'1', 0, '', '', 106, 1, '2019-07-28 16:53:20', 3, '2019-11-21 19:53:09');
INSERT INTO `c_auth_menu` VALUES (605080648767505601, '0000', '附件列表', '', b'0', '/file/attachment', 'zuihou/file/attachment/Index', b'1', 0, '', '', 107, 1, '2019-07-28 16:54:29', 3, '2019-11-11 14:28:07');
INSERT INTO `c_auth_menu` VALUES (605080816296396097, '0000', '定时调度中心', '', b'0', 'http://42.202.130.216:10000/zuihou-jobs-server', 'Layout', b'1', 9, '', '', 104, 1, '2019-07-28 16:55:09', 3, '2019-11-16 10:55:18');
INSERT INTO `c_auth_menu` VALUES (605424535633666945, '0000', '接口查询', '', b'0', '/developer/systemApi', 'zuihou/developer/systemApi/Index', b'1', 1, '', '', 104, 1, '2019-07-29 15:40:58', 3, '2019-12-24 14:40:47');
INSERT INTO `c_auth_menu` VALUES (643464272629728001, '0000', '务必详看', '', b'1', '/doc', 'zuihou/doc/Index', b'1', 0, 'el-icon-notebook-1', '', 0, 3, '2019-11-11 14:57:18', 3, '2019-11-11 15:01:31');
INSERT INTO `c_auth_menu` VALUES (643464392888812545, '0000', '后端代码', '', b'1', 'https://github.com/zuihou/zuihou-admin-cloud', 'Layout', b'1', 1, '', '', 643464272629728001, 3, '2019-11-11 14:57:46', 3, '2019-11-11 15:00:05');
INSERT INTO `c_auth_menu` VALUES (643464517879071841, '0000', '租户平台-前端代码', '', b'1', 'https://github.com/zuihou/zuihou-ui', 'Layout', b'1', 2, '', '', 643464272629728001, 3, '2019-11-11 14:58:16', 3, '2019-11-11 15:00:09');
INSERT INTO `c_auth_menu` VALUES (643464706228487361, '0000', '运营平台-前端代码', '', b'1', 'https://github.com/zuihou/zuihou-admin-ui', 'Layout', b'1', 3, '', '', 643464272629728001, 3, '2019-11-11 14:59:01', 3, '2019-11-11 15:00:11');
INSERT INTO `c_auth_menu` VALUES (643464953478514081, '0000', '在线文档', '', b'1', 'https://www.kancloud.cn/zuihou/zuihou-admin-cloud', 'Layout', b'1', 0, '', '', 643464272629728001, 3, '2019-11-11 15:00:00', 3, '2019-11-11 15:01:36');
INSERT INTO `c_auth_menu` VALUES (643874916004790785, '0000', '运营平台演示地址', NULL, b'1', 'http://42.202.130.216:10000/zuihou-admin-ui', 'Layout', b'1', 4, NULL, NULL, 643464272629728001, 3, '2019-11-12 18:09:03', 641577229343523041, '2019-12-04 16:20:13');
INSERT INTO `c_auth_menu` VALUES (644111530555611361, '0000', '链路调用监控', '', b'0', 'http://tangyh.top:10000/zipkin', 'Layout', b'1', 10, '', '', 104, 3, '2019-11-13 09:49:16', 3, '2019-11-13 09:56:51');
INSERT INTO `c_auth_menu` VALUES (645215230518909025, '0000', '登录日志', '', b'0', '/developer/loginLog', 'zuihou/developer/loginLog/Index', b'1', 4, '', '', 104, 3, '2019-11-16 10:54:59', 3, '2019-11-16 10:54:59');
INSERT INTO `c_auth_menu` VALUES (1225042542827929600, '0000', '参数配置', '', b'0', '/base/parameter', 'zuihou/base/parameter/Index', b'1', 3, '', '', 103, 3, '2020-02-05 21:04:37', 3, '2020-02-05 21:04:37');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_resource
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_resource`;
CREATE TABLE `c_auth_resource` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `code` varchar(50) DEFAULT '' COMMENT '编码\n规则：\n链接：\n数据列：\n按钮：',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID\n#c_auth_menu',
  `describe_` varchar(255) DEFAULT '' COMMENT '描述',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_CODE` (`code`,`tenant_code`) USING BTREE COMMENT '编码唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源';

-- ----------------------------
-- Records of c_auth_resource
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_resource` VALUES (643444685339100193, '0000', 'org:add', '添加', 603982542332235201, '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (643444685339100194, '0000', 'role:config', '配置', 603981723864141121, '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (643444685339100195, '0000', 'resource:add', '添加', 603976297063910529, '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (643444685339100196, '0000', 'resource:update', '修改', 603976297063910529, '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (643444685339100197, '0000', 'resource:delete', '删除', 603976297063910529, '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (643444819758154945, '0000', 'org:update', '修改', 603982542332235201, '', 3, '2019-11-11 13:40:00', 3, '2019-11-11 13:40:00');
INSERT INTO `c_auth_resource` VALUES (643444858974897441, '0000', 'org:delete', '删除', 603982542332235201, '', 3, '2019-11-11 13:40:09', 3, '2019-11-11 13:40:09');
INSERT INTO `c_auth_resource` VALUES (643444897201784193, '0000', 'org:view', '查询', 603982542332235201, '', 3, '2019-11-11 13:40:18', 3, '2019-11-11 13:40:18');
INSERT INTO `c_auth_resource` VALUES (643444992357959137, '0000', 'org:import', '导入', 603982542332235201, '', 3, '2019-11-11 13:40:41', 3, '2019-11-11 13:40:41');
INSERT INTO `c_auth_resource` VALUES (643445016773002817, '0000', 'org:export', '导出', 603982542332235201, '', 3, '2019-11-11 13:40:47', 3, '2019-11-11 13:40:47');
INSERT INTO `c_auth_resource` VALUES (643445116756821697, '0000', 'station:add', '添加', 603982713849908801, '', 3, '2019-11-11 13:41:11', 3, '2019-11-11 13:41:11');
INSERT INTO `c_auth_resource` VALUES (643445162915137313, '0000', 'station:update', '修改', 603982713849908801, '', 3, '2019-11-11 13:41:22', 3, '2019-11-11 13:41:22');
INSERT INTO `c_auth_resource` VALUES (643445197954353025, '0000', 'station:delete', '删除', 603982713849908801, '', 3, '2019-11-11 13:41:30', 3, '2019-11-11 13:41:30');
INSERT INTO `c_auth_resource` VALUES (643445229575210977, '0000', 'station:view', '查看', 603982713849908801, '', 3, '2019-11-11 13:41:38', 3, '2019-11-11 13:41:38');
INSERT INTO `c_auth_resource` VALUES (643445262110427201, '0000', 'station:export', '导出', 603982713849908801, '', 3, '2019-11-11 13:41:45', 3, '2019-11-11 13:41:45');
INSERT INTO `c_auth_resource` VALUES (643445283996305569, '0000', 'station:import', '导入', 603982713849908801, '', 3, '2019-11-11 13:41:51', 3, '2019-11-11 13:41:51');
INSERT INTO `c_auth_resource` VALUES (643445352703199521, '0000', 'user:add', '添加', 603983082961243905, '', 3, '2019-11-11 13:42:07', 3, '2019-11-11 13:42:07');
INSERT INTO `c_auth_resource` VALUES (643445412774021505, '0000', 'user:update', '修改', 603983082961243905, '', 3, '2019-11-11 13:42:21', 3, '2019-11-11 13:42:21');
INSERT INTO `c_auth_resource` VALUES (643445448081672673, '0000', 'user:delete', '删除', 603983082961243905, '', 3, '2019-11-11 13:42:30', 3, '2019-11-11 13:42:30');
INSERT INTO `c_auth_resource` VALUES (643445477274028609, '0000', 'user:view', '查看', 603983082961243905, '', 3, '2019-11-11 13:42:37', 3, '2019-11-11 13:42:37');
INSERT INTO `c_auth_resource` VALUES (643445514607528609, '0000', 'user:import', '导入', 603983082961243905, '', 3, '2019-11-11 13:42:46', 3, '2019-11-11 13:42:46');
INSERT INTO `c_auth_resource` VALUES (643445542076025601, '0000', 'user:export', '导出', 603983082961243905, '', 3, '2019-11-11 13:42:52', 3, '2019-11-11 13:42:52');
INSERT INTO `c_auth_resource` VALUES (643445641149680705, '0000', 'menu:add', '添加', 603976297063910529, '', 3, '2019-11-11 13:43:16', 3, '2019-11-11 13:43:16');
INSERT INTO `c_auth_resource` VALUES (643445674330819745, '0000', 'menu:update', '修改', 603976297063910529, '', 3, '2019-11-11 13:43:24', 3, '2019-11-11 13:43:24');
INSERT INTO `c_auth_resource` VALUES (643445704177487105, '0000', 'menu:delete', '删除', 603976297063910529, '', 3, '2019-11-11 13:43:31', 3, '2019-11-11 13:43:31');
INSERT INTO `c_auth_resource` VALUES (643445747320098145, '0000', 'menu:view', '查看', 603976297063910529, '', 3, '2019-11-11 13:43:41', 3, '2019-11-11 13:43:41');
INSERT INTO `c_auth_resource` VALUES (643445774687931841, '0000', 'menu:export', '导出', 603976297063910529, '', 3, '2019-11-11 13:43:48', 3, '2019-11-11 13:43:48');
INSERT INTO `c_auth_resource` VALUES (643445802106097185, '0000', 'menu:import', '导入', 603976297063910529, '', 3, '2019-11-11 13:43:54', 3, '2019-11-11 13:43:54');
INSERT INTO `c_auth_resource` VALUES (643448338154263521, '0000', 'role:add', '添加', 603981723864141121, '', 3, '2019-11-11 13:53:59', 3, '2019-11-11 13:53:59');
INSERT INTO `c_auth_resource` VALUES (643448369779315777, '0000', 'role:update', '修改', 603981723864141121, '', 3, '2019-11-11 13:54:06', 3, '2019-11-11 13:54:06');
INSERT INTO `c_auth_resource` VALUES (643448507767723169, '0000', 'role:delete', '删除', 603981723864141121, '', 3, '2019-11-11 13:54:39', 3, '2019-11-11 13:54:39');
INSERT INTO `c_auth_resource` VALUES (643448611161511169, '0000', 'role:view', '查看', 603981723864141121, '', 3, '2019-11-11 13:55:04', 3, '2019-11-11 13:55:04');
INSERT INTO `c_auth_resource` VALUES (643448656451605857, '0000', 'role:export', '导出', 603981723864141121, '', 3, '2019-11-11 13:55:15', 3, '2019-11-11 13:55:15');
INSERT INTO `c_auth_resource` VALUES (643448730950833601, '0000', 'role:import', '导入', 603981723864141121, '', 3, '2019-11-11 13:55:32', 3, '2019-11-11 13:55:32');
INSERT INTO `c_auth_resource` VALUES (643448826945869409, '0000', 'role:auth', '授权', 603981723864141121, '', 3, '2019-11-11 13:55:55', 3, '2019-11-11 13:55:55');
INSERT INTO `c_auth_resource` VALUES (643449540959016737, '0000', 'dict:add', '添加', 605078371293987105, '', 3, '2019-11-11 13:58:45', 3, '2019-11-11 13:58:45');
INSERT INTO `c_auth_resource` VALUES (643449573045442433, '0000', 'dict:update', '修改', 605078371293987105, '', 3, '2019-11-11 13:58:53', 3, '2019-11-11 13:58:53');
INSERT INTO `c_auth_resource` VALUES (643449629173618657, '0000', 'dict:view', '查看', 605078371293987105, '', 3, '2019-11-11 13:59:07', 3, '2019-11-11 13:59:07');
INSERT INTO `c_auth_resource` VALUES (643449944866297985, '0000', 'dict:delete', '删除', 605078371293987105, '', 3, '2019-11-11 14:00:22', 3, '2019-11-11 14:00:22');
INSERT INTO `c_auth_resource` VALUES (643449998909905121, '0000', 'dict:export', '导出', 605078371293987105, '', 3, '2019-11-11 14:00:35', 3, '2019-11-11 14:00:35');
INSERT INTO `c_auth_resource` VALUES (643450072595437889, '0000', 'dict:import', '导入', 605078371293987105, '', 3, '2019-11-11 14:00:52', 3, '2019-11-11 14:00:52');
INSERT INTO `c_auth_resource` VALUES (643450171333548481, '0000', 'area:add', '添加', 605078463069552993, '', 3, '2019-11-11 14:01:16', 3, '2019-11-11 14:01:16');
INSERT INTO `c_auth_resource` VALUES (643450210449627681, '0000', 'area:update', '修改', 605078463069552993, '', 3, '2019-11-11 14:01:25', 3, '2019-11-11 14:01:25');
INSERT INTO `c_auth_resource` VALUES (643450295858240129, '0000', 'area:delete', '删除', 605078463069552993, '', 3, '2019-11-11 14:01:45', 3, '2019-11-11 14:01:45');
INSERT INTO `c_auth_resource` VALUES (643450326619265761, '0000', 'area:view', '查看', 605078463069552993, '', 3, '2019-11-11 14:01:53', 3, '2019-11-11 14:01:53');
INSERT INTO `c_auth_resource` VALUES (643450551291353921, '0000', 'area:export', '导出', 605078463069552993, '', 3, '2019-11-11 14:02:46', 3, '2019-11-11 14:02:46');
INSERT INTO `c_auth_resource` VALUES (643450602218593185, '0000', 'area:import', '导入', 605078463069552993, '', 3, '2019-11-11 14:02:59', 3, '2019-11-11 14:02:59');
INSERT INTO `c_auth_resource` VALUES (643450770317909249, '0000', 'optLog:view', '查看', 605078672772170209, '', 3, '2019-11-11 14:03:39', 3, '2019-11-11 14:03:39');
INSERT INTO `c_auth_resource` VALUES (643450853134441825, '0000', 'optLog:export', '导出', 605078672772170209, '', 3, '2019-11-11 14:03:58', 3, '2019-11-11 14:03:58');
INSERT INTO `c_auth_resource` VALUES (643451893279892129, '0000', 'msgs:view', '查看', 605080023753294753, '', 3, '2019-11-11 14:08:06', 3, '2019-11-11 14:08:06');
INSERT INTO `c_auth_resource` VALUES (643451945020826369, '0000', 'msgs:delete', '删除', 605080023753294753, '', 3, '2019-11-11 14:08:19', 3, '2019-11-11 14:08:19');
INSERT INTO `c_auth_resource` VALUES (643451994945626977, '0000', 'msgs:update', '修改', 605080023753294753, '', 3, '2019-11-11 14:08:31', 3, '2019-11-11 14:08:31');
INSERT INTO `c_auth_resource` VALUES (643452086981239745, '0000', 'msgs:export', '导出', 605080023753294753, '', 3, '2019-11-11 14:08:53', 3, '2019-11-11 14:08:53');
INSERT INTO `c_auth_resource` VALUES (643452393857492033, '0000', 'sms:template:add', '添加', 605080107379327969, '', 3, '2019-11-11 14:10:06', 3, '2019-11-11 14:10:06');
INSERT INTO `c_auth_resource` VALUES (643452429496493217, '0000', 'sms:template:update', '修改', 605080107379327969, '', 3, '2019-11-11 14:10:14', 3, '2019-11-11 14:10:14');
INSERT INTO `c_auth_resource` VALUES (643452458693043457, '0000', 'sms:template:view', '查看', 605080107379327969, '', 3, '2019-11-11 14:10:21', 3, '2019-11-11 14:10:21');
INSERT INTO `c_auth_resource` VALUES (643452488447436129, '0000', 'sms:template:delete', '删除', 605080107379327969, '', 3, '2019-11-11 14:10:28', 3, '2019-11-11 14:10:28');
INSERT INTO `c_auth_resource` VALUES (643452536954561985, '0000', 'sms:template:import', '导入', 605080107379327969, '', 3, '2019-11-11 14:10:40', 3, '2019-11-11 14:10:40');
INSERT INTO `c_auth_resource` VALUES (643452571645650465, '0000', 'sms:template:export', '导入', 605080107379327969, '', 3, '2019-11-11 14:10:48', 3, '2019-11-11 14:10:48');
INSERT INTO `c_auth_resource` VALUES (643454073500084577, '0000', 'sms:manage:add', '添加', 605080359394083937, '', 3, '2019-11-11 14:16:46', 3, '2019-11-11 14:16:46');
INSERT INTO `c_auth_resource` VALUES (643454110992968129, '0000', 'sms:manage:update', '修改', 605080359394083937, '', 3, '2019-11-11 14:16:55', 3, '2019-11-11 14:16:55');
INSERT INTO `c_auth_resource` VALUES (643454150905965089, '0000', 'sms:manage:view', '查看', 605080359394083937, '', 3, '2019-11-11 14:17:05', 3, '2019-11-11 14:17:05');
INSERT INTO `c_auth_resource` VALUES (643454825052252961, '0000', 'sms:manage:delete', '删除', 605080359394083937, '', 3, '2019-11-11 14:19:45', 3, '2019-11-11 14:19:45');
INSERT INTO `c_auth_resource` VALUES (643455175503129569, '0000', 'sms:manage:export', '导出', 605080359394083937, '', 3, '2019-11-11 14:21:09', 3, '2019-11-11 14:26:05');
INSERT INTO `c_auth_resource` VALUES (643455720519380097, '0000', 'sms:manage:import', '导入', 605080359394083937, '', 3, '2019-11-11 14:23:19', 3, '2019-11-11 14:23:19');
INSERT INTO `c_auth_resource` VALUES (643456690892582401, '0000', 'file:add', '添加', 605080648767505601, '', 3, '2019-11-11 14:27:10', 3, '2019-11-11 14:27:10');
INSERT INTO `c_auth_resource` VALUES (643456724186967649, '0000', 'file:update', '修改', 605080648767505601, '', 3, '2019-11-11 14:27:18', 3, '2019-11-11 14:27:18');
INSERT INTO `c_auth_resource` VALUES (643456761927315137, '0000', 'file:delete', '删除', 605080648767505601, '', 3, '2019-11-11 14:27:27', 3, '2019-11-11 14:27:27');
INSERT INTO `c_auth_resource` VALUES (643456789920100129, '0000', 'file:view', '查看', 605080648767505601, '', 3, '2019-11-11 14:27:34', 3, '2019-11-11 14:27:34');
INSERT INTO `c_auth_resource` VALUES (643456884694593409, '0000', 'file:download', '下载', 605080648767505601, '', 3, '2019-11-11 14:27:56', 3, '2019-11-11 14:27:56');
INSERT INTO `c_auth_resource` VALUES (645288214990422241, '0000', 'optLog:delete', '删除', 605078672772170209, '', 3, '2019-11-16 15:45:00', 3, '2019-11-16 15:45:00');
INSERT INTO `c_auth_resource` VALUES (645288283693121889, '0000', 'loginLog:delete', '删除', 645215230518909025, '', 3, '2019-11-16 15:45:16', 3, '2019-11-16 15:45:16');
INSERT INTO `c_auth_resource` VALUES (645288375300915649, '0000', 'loginLog:export', '导出', 645215230518909025, '', 3, '2019-11-16 15:45:38', 3, '2019-11-16 15:45:38');
INSERT INTO `c_auth_resource` VALUES (658002570005972001, '0000', 'msgs:add', '新增', 605080023753294753, '', 3, '2019-12-21 17:47:18', 3, '2019-12-21 17:47:18');
INSERT INTO `c_auth_resource` VALUES (658002632467547265, '0000', 'msgs:mark', '标记已读', 605080023753294753, '', 3, '2019-12-21 17:47:33', 3, '2019-12-21 17:47:33');
INSERT INTO `c_auth_resource` VALUES (659045058871296641, '0000', 'systemApi:add', '新增', 605424535633666945, '', 3, '2019-12-24 14:49:47', 3, '2019-12-24 14:49:47');
INSERT INTO `c_auth_resource` VALUES (659045100646564577, '0000', 'systemApi:delete', '删除', 605424535633666945, '', 3, '2019-12-24 14:49:57', 3, '2019-12-24 14:49:57');
INSERT INTO `c_auth_resource` VALUES (659045145735332673, '0000', 'systemApi:update', '修改', 605424535633666945, '', 3, '2019-12-24 14:50:07', 3, '2019-12-24 14:50:07');
INSERT INTO `c_auth_resource` VALUES (659045207890723745, '0000', 'systemApi:export', '导出', 605424535633666945, '', 3, '2019-12-24 14:50:22', 3, '2019-12-24 14:50:22');
INSERT INTO `c_auth_resource` VALUES (659430164874134689, '0000', 'systemApi:view', '查看', 605424535633666945, '', 3, '2019-12-25 16:20:03', 3, '2019-12-25 16:20:03');
INSERT INTO `c_auth_resource` VALUES (659702641965662497, '0000', 'application:add', '新增', 605078538881597857, '', 3, '2019-12-26 10:22:47', 3, '2019-12-26 10:22:47');
INSERT INTO `c_auth_resource` VALUES (659702674622513537, '0000', 'application:update', '修改', 605078538881597857, '', 3, '2019-12-26 10:22:55', 3, '2019-12-26 10:22:55');
INSERT INTO `c_auth_resource` VALUES (659702756889592289, '0000', 'application:delete', '删除', 605078538881597857, '', 3, '2019-12-26 10:23:14', 3, '2019-12-26 10:23:14');
INSERT INTO `c_auth_resource` VALUES (659702791312245313, '0000', 'application:view', '查看', 605078538881597857, '', 3, '2019-12-26 10:23:22', 3, '2019-12-26 10:23:22');
INSERT INTO `c_auth_resource` VALUES (659702853945787041, '0000', 'application:export', '导出', 605078538881597857, '', 3, '2019-12-26 10:23:37', 3, '2019-12-26 10:23:37');
INSERT INTO `c_auth_resource` VALUES (1225042691843162112, '0000', 'parameter:add', '添加', 1225042542827929600, '', 3, '2020-02-05 21:05:13', 3, '2020-02-05 21:05:13');
INSERT INTO `c_auth_resource` VALUES (1225042821497487360, '0000', 'parameter:update', '修改', 1225042542827929600, '', 3, '2020-02-05 21:05:43', 3, '2020-02-05 21:05:43');
INSERT INTO `c_auth_resource` VALUES (1225042949172101120, '0000', 'parameter:delete', '删除', 1225042542827929600, '', 3, '2020-02-05 21:06:14', 3, '2020-02-05 21:06:14');
INSERT INTO `c_auth_resource` VALUES (1225043012455759872, '0000', 'parameter:export', '导出', 1225042542827929600, '', 3, '2020-02-05 21:06:29', 3, '2020-02-05 21:06:29');
INSERT INTO `c_auth_resource` VALUES (1237035586045345792, '0000', 'parameter:import', '导入', 1225042542827929600, '', 3, '2020-03-09 23:20:41', 3, '2020-03-09 23:20:41');
INSERT INTO `c_auth_resource` VALUES (1237035798587506688, '0000', 'msgs:import', '导入', 605080023753294753, '', 3, '2020-03-09 23:21:32', 3, '2020-03-09 23:21:32');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_role`;
CREATE TABLE `c_auth_role` (
  `id` bigint(20) NOT NULL,
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '名称',
  `code` varchar(20) DEFAULT '' COMMENT '编码',
  `describe_` varchar(100) DEFAULT '' COMMENT '描述',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `readonly` bit(1) DEFAULT b'0' COMMENT '是否内置角色',
  `ds_type` varchar(20) NOT NULL DEFAULT 'SELF' COMMENT '数据权限类型\n#DataScopeType{ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人;}',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_CODE` (`code`,`tenant_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of c_auth_role
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_role` VALUES (100, '0000', '平台管理员', 'PT_ADMIN', '平台内置管理员', b'1', b'1', 'ALL', 1, '2019-10-25 13:46:00', 3, '2020-02-13 11:05:28');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_role_authority`;
CREATE TABLE `c_auth_role_authority` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
INSERT INTO `c_auth_role_authority` VALUES (1237036116683522048, '0000', 643444685339100197, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116700299264, '0000', 643445116756821697, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116700299265, '0000', 643444685339100196, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116704493568, '0000', 643450072595437889, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116704493569, '0000', 643444685339100193, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116708687872, '0000', 643444685339100195, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116708687873, '0000', 659702853945787041, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116712882176, '0000', 643444685339100194, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116717076480, '0000', 1225042949172101120, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116717076481, '0000', 643445197954353025, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116717076482, '0000', 1232574830335754240, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116721270784, '0000', 1225043012455759872, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116721270785, '0000', 643445262110427201, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116721270786, '0000', 643450853134441825, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116725465088, '0000', 643445774687931841, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116729659392, '0000', 1225984228617879552, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116729659393, '0000', 659702756889592289, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116733853696, '0000', 643450551291353921, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116733853697, '0000', 643452458693043457, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116738048000, '0000', 643454110992968129, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116738048001, '0000', 643445016773002817, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116738048002, '0000', 659430164874134689, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116742242304, '0000', 643445162915137313, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116746436608, '0000', 643445229575210977, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116750630912, '0000', 1225984389242945536, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116750630913, '0000', 643448369779315777, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116750630914, '0000', 643454825052252961, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116754825216, '0000', 643456884694593409, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116754825217, '0000', 658002570005972001, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116754825218, '0000', 643451893279892129, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116754825219, '0000', 643445704177487105, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116759019520, '0000', 659045058871296641, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116759019521, '0000', 643445283996305569, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116759019522, '0000', 643451994945626977, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116763213824, '0000', 643445542076025601, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116763213825, '0000', 645288214990422241, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116763213826, '0000', 643450326619265761, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116767408128, '0000', 643450602218593185, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116767408129, '0000', 643456724186967649, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116767408130, '0000', 643445448081672673, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116771602432, '0000', 643448611161511169, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116771602433, '0000', 643445802106097185, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116771602434, '0000', 643448826945869409, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116775796736, '0000', 643452536954561985, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116775796737, '0000', 1225984359173980160, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116775796738, '0000', 643445514607528609, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116779991040, '0000', 643448507767723169, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116779991041, '0000', 643450295858240129, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116779991042, '0000', 643449998909905121, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116784185344, '0000', 645288283693121889, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116784185345, '0000', 643444897201784193, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116784185346, '0000', 643448730950833601, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116788379648, '0000', 643456690892582401, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116788379649, '0000', 643445412774021505, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116792573952, '0000', 1237035586045345792, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116796768256, '0000', 643448656451605857, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116796768257, '0000', 643450171333548481, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116796768258, '0000', 643456761927315137, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116800962560, '0000', 643445477274028609, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116800962561, '0000', 1225984257483079680, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116805156865, '0000', 643445352703199521, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116809351168, '0000', 643445747320098145, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116809351169, '0000', 643452571645650465, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116809351170, '0000', 643444858974897441, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116813545472, '0000', 643449944866297985, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116813545473, '0000', 643450770317909249, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116813545474, '0000', 1225984321857257472, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116817739776, '0000', 643455720519380097, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116817739777, '0000', 643450210449627681, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116817739778, '0000', 659045100646564577, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116821934080, '0000', 643444992357959137, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116821934081, '0000', 643456789920100129, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116821934082, '0000', 658002632467547265, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116826128384, '0000', 659702641965662497, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116826128385, '0000', 643445641149680705, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116826128386, '0000', 643444819758154945, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116830322688, '0000', 643449629173618657, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116830322689, '0000', 643451945020826369, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116834516992, '0000', 1225042691843162112, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116834516993, '0000', 643448338154263521, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116834516994, '0000', 643449573045442433, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116838711296, '0000', 659702791312245313, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116838711297, '0000', 659045207890723745, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116838711298, '0000', 643452429496493217, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116838711299, '0000', 643454073500084577, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116842905600, '0000', 643455175503129569, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116842905601, '0000', 643445674330819745, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116842905602, '0000', 643452393857492033, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116847099904, '0000', 1237035798587506688, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116847099905, '0000', 643454150905965089, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116847099906, '0000', 643452086981239745, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116851294208, '0000', 643452488447436129, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116851294209, '0000', 645288375300915649, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116851294210, '0000', 659702674622513537, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116851294211, '0000', 1225042821497487360, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116855488512, '0000', 659045145735332673, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116855488513, '0000', 643449540959016737, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116855488514, '0000', 645215230518909025, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116859682816, '0000', 605079411338773153, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116859682817, '0000', 603982542332235201, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116859682818, '0000', 605078371293987105, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116859682819, '0000', 644111530555611361, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116863877120, '0000', 605079751035454305, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116863877121, '0000', 605078672772170209, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116863877122, '0000', 603976297063910529, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116863877123, '0000', 605079545585861345, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116868071424, '0000', 605080107379327969, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116868071425, '0000', 605078979149300257, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116868071426, '0000', 605080359394083937, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116872265728, '0000', 605078463069552993, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116872265729, '0000', 605080816296396097, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116872265730, '0000', 1225042542827929600, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116872265731, '0000', 603982713849908801, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116876460032, '0000', 605079658416833313, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116876460033, '0000', 605078538881597857, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116876460034, '0000', 605080648767505601, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116880654336, '0000', 603983082961243905, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116880654337, '0000', 603981723864141121, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116880654338, '0000', 605424535633666945, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116884848640, '0000', 605080023753294753, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116884848641, '0000', 605079239015793249, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116884848642, '0000', 101, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116884848643, '0000', 102, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116889042944, '0000', 103, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116889042945, '0000', 104, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116889042946, '0000', 105, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116889042947, '0000', 106, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116893237248, '0000', 107, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116893237249, '0000', 643464272629728001, 'MENU', 100, '2020-03-09 23:22:48', 3);
COMMIT;

-- ----------------------------
-- Table structure for c_auth_role_org
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_role_org`;
CREATE TABLE `c_auth_role_org` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
INSERT INTO `c_auth_role_org` VALUES (1227790863468331008, '0000', 100, 100, '2020-02-13 11:05:28', 3);
INSERT INTO `c_auth_role_org` VALUES (1227790863493496832, '0000', 100, 101, '2020-02-13 11:05:28', 3);
INSERT INTO `c_auth_role_org` VALUES (1227790863497691136, '0000', 100, 102, '2020-02-13 11:05:28', 3);
INSERT INTO `c_auth_role_org` VALUES (1227790863497691137, '0000', 100, 643775612976106881, '2020-02-13 11:05:28', 3);
INSERT INTO `c_auth_role_org` VALUES (1227790863501885440, '0000', 100, 643775664683486689, '2020-02-13 11:05:28', 3);
INSERT INTO `c_auth_role_org` VALUES (1227790863501885441, '0000', 100, 643775904077582049, '2020-02-13 11:05:28', 3);
INSERT INTO `c_auth_role_org` VALUES (1227790863506079744, '0000', 100, 643776324342648929, '2020-02-13 11:05:28', 3);
INSERT INTO `c_auth_role_org` VALUES (1227790863506079745, '0000', 100, 643776407691858113, '2020-02-13 11:05:28', 3);
INSERT INTO `c_auth_role_org` VALUES (1227790863510274048, '0000', 100, 643776508795556193, '2020-02-13 11:05:28', 3);
INSERT INTO `c_auth_role_org` VALUES (1227790863514468352, '0000', 100, 643776594376135105, '2020-02-13 11:05:28', 3);
INSERT INTO `c_auth_role_org` VALUES (1227790863514468353, '0000', 100, 643776633823564321, '2020-02-13 11:05:28', 3);
INSERT INTO `c_auth_role_org` VALUES (1227790863514468354, '0000', 100, 643776668917305985, '2020-02-13 11:05:28', 3);
INSERT INTO `c_auth_role_org` VALUES (1227790863518662656, '0000', 100, 643776713909605089, '2020-02-13 11:05:28', 3);
INSERT INTO `c_auth_role_org` VALUES (1227790863518662657, '0000', 100, 643776757199016769, '2020-02-13 11:05:28', 3);
COMMIT;

-- ----------------------------
-- Table structure for c_auth_system_api
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_system_api`;
CREATE TABLE `c_auth_system_api` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `describe_` varchar(100) DEFAULT NULL COMMENT '描述',
  `request_method` varchar(255) DEFAULT NULL COMMENT '请求方式',
  `content_type` varchar(255) DEFAULT NULL COMMENT '响应类型',
  `service_id` varchar(50) NOT NULL COMMENT '服务ID',
  `path` varchar(255) DEFAULT NULL COMMENT '请求路径',
  `status` bit(1) DEFAULT b'1' COMMENT '状态\n:0-无效 1-有效',
  `is_persist` bit(1) DEFAULT b'0' COMMENT '保留数据 \n0-否 1-是 系统内资数据,不允许删除',
  `is_auth` bit(1) DEFAULT b'1' COMMENT '是否需要认证\n: 0-无认证 1-身份认证 默认:1',
  `is_open` bit(1) DEFAULT b'0' COMMENT '是否公开\n: 0-内部的 1-公开的',
  `class_name` varchar(255) DEFAULT NULL COMMENT '类名',
  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UNX_ID` (`id`) USING BTREE,
  UNIQUE KEY `UNX_CODE` (`code`(100),`tenant_code`) USING BTREE,
  KEY `service_id` (`service_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='API接口';

-- ----------------------------
-- Table structure for c_auth_user
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_user`;
CREATE TABLE `c_auth_user` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `account` varchar(30) NOT NULL COMMENT '账号',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织ID\n#c_core_org\n@InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class) RemoteData<Long, com.github.zuihou.authority.entity.core.Org>',
  `station_id` bigint(20) DEFAULT NULL COMMENT '岗位ID\n#c_core_station\n@InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD) RemoteData<Long, String>',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT '' COMMENT '手机',
  `sex` varchar(1) DEFAULT 'N' COMMENT '性别\n#Sex{W:女;M:男;N:未知}',
  `status` bit(1) DEFAULT b'0' COMMENT '状态 \n1启用 0禁用',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像',
  `nation` varchar(20) DEFAULT NULL COMMENT '民族\n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>',
  `education` varchar(20) DEFAULT NULL COMMENT '学历\n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>',
  `position_status` varchar(20) DEFAULT NULL COMMENT '职位状态\n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>',
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
  UNIQUE KEY `UN_ACCOUNT` (`account`,`tenant_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of c_auth_user
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_user` VALUES (3, '0000', 'zuihou', '平台超管', 100, 100, '244387061@qq.com', '15218869992', 'W', b'1', 'BiazfanxmamNRoxxVxka.png', 'mz_hanz', 'BOSHI', 'WORKING', '疯狂加班3~', '2020-03-10 22:13:08', 0, NULL, 'd9d17d88918aa72834289edaf38f42e2', '2020-03-10 22:13:09', 1, '2019-09-02 11:32:02', 3, '2019-12-11 18:00:25');
INSERT INTO `c_auth_user` VALUES (641577229343523041, '0000', 'test', '总经理', 102, 100, '', '', 'N', b'1', 'http://127.0.0.1:10000/file/0000/2019/11/c8df3238-ebca-42b3-baeb-37896468f028.png', 'mz_zz', 'COLLEGE', 'WORKING', '', '2019-12-21 16:45:13', 0, NULL, 'd9d17d88918aa72834289edaf38f42e2', '2019-12-21 16:45:14', 3, '2019-11-06 09:58:56', 3, '2019-11-26 11:02:42');
INSERT INTO `c_auth_user` VALUES (641590096981656001, '0000', 'manong', '码农', 643776594376135105, 642032719487828225, '', '', 'M', b'1', 'http://192.168.1.34:10000/file/0000/2019/11/6a759cd8-40f6-46d2-9487-6bd18a6695f2.jpg', 'mz_mz', 'ZHUANKE', 'LEAVE', '122', '2020-02-22 12:32:35', 0, NULL, 'd9d17d88918aa72834289edaf38f42e2', '2020-02-22 12:32:35', 3, '2019-11-06 10:50:01', 3, '2019-11-26 20:27:48');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_user_role`;
CREATE TABLE `c_auth_user_role` (
  `id` bigint(20) NOT NULL,
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
INSERT INTO `c_auth_user_role` VALUES (634707503061401697, '0000', 100, 3, 1, '2019-10-18 11:01:01');
COMMIT;

-- ----------------------------
-- Table structure for c_common_area
-- ----------------------------
DROP TABLE IF EXISTS `c_common_area`;
CREATE TABLE `c_common_area` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
  UNIQUE KEY `UN_CODE` (`tenant_code`,`code`) USING BTREE,
  KEY `IDX_PARENT_ID` (`parent_id`,`label`) USING BTREE COMMENT '查询'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='地区表';

-- ----------------------------
-- Table structure for c_common_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `c_common_dictionary`;
CREATE TABLE `c_common_dictionary` (
  `id` bigint(20) NOT NULL,
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `type_` varchar(64) NOT NULL DEFAULT '' COMMENT '编码\r\n一颗树仅仅有一个统一的编码',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `describe_` varchar(200) DEFAULT '' COMMENT '描述',
  `status_` bit(1) DEFAULT b'1' COMMENT '状态',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典类型';

-- ----------------------------
-- Records of c_common_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `c_common_dictionary` VALUES (1, '0000', 'NATION', '民族', '', b'1', 1, '2019-06-01 09:42:50', 1, '2019-06-01 09:42:54');
INSERT INTO `c_common_dictionary` VALUES (2, '0000', 'POSITION_STATUS', '在职状态', '', b'1', 1, '2019-06-04 11:37:15', 1, '2019-06-04 11:37:15');
INSERT INTO `c_common_dictionary` VALUES (3, '0000', 'EDUCATION', '学历', '', b'1', 1, '2019-06-04 11:33:52', 1, '2019-06-04 11:33:52');
INSERT INTO `c_common_dictionary` VALUES (4, '0000', 'AREA_LEVEL', '行政区级', '', b'1', 3, '2020-01-20 15:12:05', 3, '2020-01-20 15:12:05');
COMMIT;

-- ----------------------------
-- Table structure for c_common_dictionary_item
-- ----------------------------
DROP TABLE IF EXISTS `c_common_dictionary_item`;
CREATE TABLE `c_common_dictionary_item` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `dictionary_id` bigint(20) NOT NULL COMMENT '类型ID',
  `dictionary_type` varchar(64) NOT NULL COMMENT '类型',
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
  KEY `dict_code_item_code_uniq` (`dictionary_type`,`code`) USING BTREE COMMENT '字典编码与字典项目编码联合唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典项';

-- ----------------------------
-- Records of c_common_dictionary_item
-- ----------------------------
BEGIN;
INSERT INTO `c_common_dictionary_item` VALUES (1, '0000', 4, 'AREA_LEVEL', 'COUNTRY', '国家', b'1', '', 1, 3, '2020-01-20 15:12:57', 3, '2020-01-20 15:12:57');
INSERT INTO `c_common_dictionary_item` VALUES (2, '0000', 4, 'AREA_LEVEL', 'PROVINCE', '省份/直辖市', b'1', '', 2, 3, '2020-01-20 15:13:45', 3, '2020-01-20 15:13:45');
INSERT INTO `c_common_dictionary_item` VALUES (3, '0000', 4, 'AREA_LEVEL', 'CITY', '地市', b'1', '', 3, 3, '2020-01-20 15:14:16', 3, '2020-01-20 15:14:16');
INSERT INTO `c_common_dictionary_item` VALUES (4, '0000', 4, 'AREA_LEVEL', 'COUNTY', '区县', b'1', '', 4, 3, '2020-01-20 15:14:54', 3, '2020-01-20 15:14:54');
INSERT INTO `c_common_dictionary_item` VALUES (38, '0000', 3, 'EDUCATION', 'ZHUANKE', '专科', b'1', '', 4, 1, '2019-06-04 11:36:29', 1, '2019-06-04 11:36:29');
INSERT INTO `c_common_dictionary_item` VALUES (39, '0000', 3, 'EDUCATION', 'COLLEGE', '本科', b'1', '', 5, 1, '2019-06-04 11:36:19', 1, '2019-06-04 11:36:19');
INSERT INTO `c_common_dictionary_item` VALUES (40, '0000', 3, 'EDUCATION', 'SUOSHI', '硕士', b'1', '', 6, 1, '2019-06-04 11:36:29', 1, '2019-06-04 11:36:29');
INSERT INTO `c_common_dictionary_item` VALUES (41, '0000', 3, 'EDUCATION', 'BOSHI', '博士', b'1', '', 7, 1, '2019-06-04 11:36:29', 1, '2019-06-04 11:36:29');
INSERT INTO `c_common_dictionary_item` VALUES (42, '0000', 3, 'EDUCATION', 'BOSHIHOU', '博士后', b'1', '', 8, 1, '2019-06-04 11:36:29', 1, '2019-06-04 11:36:29');
INSERT INTO `c_common_dictionary_item` VALUES (43, '0000', 1, 'NATION', 'mz_hanz', '汉族', b'1', '', 0, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (44, '0000', 1, 'NATION', 'mz_zz', '壮族', b'1', '', 1, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (45, '0000', 1, 'NATION', 'mz_mz', '满族', b'1', '', 2, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (46, '0000', 1, 'NATION', 'mz_hz', '回族', b'1', '', 3, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (47, '0000', 1, 'NATION', 'mz_miaoz', '苗族', b'1', '', 4, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (48, '0000', 1, 'NATION', 'mz_wwez', '维吾尔族', b'1', '', 5, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (49, '0000', 1, 'NATION', 'mz_tjz', '土家族', b'1', '', 6, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (50, '0000', 1, 'NATION', 'mz_yz', '彝族', b'1', '', 7, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (51, '0000', 1, 'NATION', 'mz_mgz', '蒙古族', b'1', '', 8, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (52, '0000', 1, 'NATION', 'mz_zhangz', '藏族', b'1', '', 9, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (53, '0000', 1, 'NATION', 'mz_byz', '布依族', b'1', '', 10, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (54, '0000', 1, 'NATION', 'mz_dz', '侗族', b'1', '', 11, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (55, '0000', 1, 'NATION', 'mz_yaoz', '瑶族', b'1', '', 12, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (56, '0000', 1, 'NATION', 'mz_cxz', '朝鲜族', b'1', '', 13, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (57, '0000', 1, 'NATION', 'mz_bz', '白族', b'1', '', 14, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (58, '0000', 1, 'NATION', 'mz_hnz', '哈尼族', b'1', '', 15, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (59, '0000', 1, 'NATION', 'mz_hskz', '哈萨克族', b'1', '', 16, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (60, '0000', 1, 'NATION', 'mz_lz', '黎族', b'1', '', 17, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (61, '0000', 1, 'NATION', 'mz_daiz', '傣族', b'1', '', 18, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (62, '0000', 1, 'NATION', 'mz_sz', '畲族', b'1', '', 19, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (63, '0000', 1, 'NATION', 'mz_llz', '傈僳族', b'1', '', 20, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (64, '0000', 1, 'NATION', 'mz_glz', '仡佬族', b'1', '', 21, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (65, '0000', 1, 'NATION', 'mz_dxz', '东乡族', b'1', '', 22, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (66, '0000', 1, 'NATION', 'mz_gsz', '高山族', b'1', '', 23, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (67, '0000', 1, 'NATION', 'mz_lhz', '拉祜族', b'1', '', 24, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (68, '0000', 1, 'NATION', 'mz_shuiz', '水族', b'1', '', 25, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (69, '0000', 1, 'NATION', 'mz_wz', '佤族', b'1', '', 26, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (70, '0000', 1, 'NATION', 'mz_nxz', '纳西族', b'1', '', 27, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (71, '0000', 1, 'NATION', 'mz_qz', '羌族', b'1', '', 28, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (72, '0000', 1, 'NATION', 'mz_tz', '土族', b'1', '', 29, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (73, '0000', 1, 'NATION', 'mz_zlz', '仫佬族', b'1', '', 30, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (74, '0000', 1, 'NATION', 'mz_xbz', '锡伯族', b'1', '', 31, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (75, '0000', 1, 'NATION', 'mz_kehzz', '柯尔克孜族', b'1', '', 32, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (76, '0000', 1, 'NATION', 'mz_dwz', '达斡尔族', b'1', '', 33, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (77, '0000', 1, 'NATION', 'mz_jpz', '景颇族', b'1', '', 34, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (78, '0000', 1, 'NATION', 'mz_mlz', '毛南族', b'1', '', 35, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (79, '0000', 1, 'NATION', 'mz_slz', '撒拉族', b'1', '', 36, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (80, '0000', 1, 'NATION', 'mz_tjkz', '塔吉克族', b'1', '', 37, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (81, '0000', 1, 'NATION', 'mz_acz', '阿昌族', b'1', '', 38, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (82, '0000', 1, 'NATION', 'mz_pmz', '普米族', b'1', '', 39, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (83, '0000', 1, 'NATION', 'mz_ewkz', '鄂温克族', b'1', '', 40, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (84, '0000', 1, 'NATION', 'mz_nz', '怒族', b'1', '', 41, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (85, '0000', 1, 'NATION', 'mz_jz', '京族', b'1', '', 42, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (86, '0000', 1, 'NATION', 'mz_jnz', '基诺族', b'1', '', 43, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (87, '0000', 1, 'NATION', 'mz_daz', '德昂族', b'1', '', 44, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (88, '0000', 1, 'NATION', 'mz_baz', '保安族', b'1', '', 45, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (89, '0000', 1, 'NATION', 'mz_elsz', '俄罗斯族', b'1', '', 46, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (90, '0000', 1, 'NATION', 'mz_ygz', '裕固族', b'1', '', 47, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (91, '0000', 1, 'NATION', 'mz_wzbkz', '乌兹别克族', b'1', '', 48, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (92, '0000', 1, 'NATION', 'mz_mbz', '门巴族', b'1', '', 49, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (93, '0000', 1, 'NATION', 'mz_elcz', '鄂伦春族', b'1', '', 50, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (94, '0000', 1, 'NATION', 'mz_dlz', '独龙族', b'1', '', 51, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (95, '0000', 1, 'NATION', 'mz_tkez', '塔塔尔族', b'1', '', 52, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (96, '0000', 1, 'NATION', 'mz_hzz', '赫哲族', b'1', '', 53, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (97, '0000', 1, 'NATION', 'mz_lbz', '珞巴族', b'1', '', 54, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (98, '0000', 1, 'NATION', 'mz_blz', '布朗族', b'1', '', 55, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (99, '0000', 2, 'POSITION_STATUS', 'WORKING', '在职', b'1', '', 1, 1, '2019-06-04 11:38:16', 1, '2019-06-04 11:38:16');
INSERT INTO `c_common_dictionary_item` VALUES (100, '0000', 2, 'POSITION_STATUS', 'QUIT', '离职', b'1', '', 2, 1, '2019-06-04 11:38:50', 1, '2019-06-04 11:38:50');
INSERT INTO `c_common_dictionary_item` VALUES (1237038877428940800, '0000', 4, 'AREA_LEVEL', 'TOWNS', '乡镇', b'1', '', 5, 3, '2020-03-09 23:33:46', 3, '2020-03-09 23:33:46');
INSERT INTO `c_common_dictionary_item` VALUES (1237038991044247552, '0000', 3, 'EDUCATION', 'XIAOXUE', '小学', b'1', '', 1, 3, '2020-03-09 23:34:13', 3, '2020-03-09 23:34:13');
INSERT INTO `c_common_dictionary_item` VALUES (1237039071537135616, '0000', 3, 'EDUCATION', 'ZHONGXUE', '中学', b'1', '', 2, 3, '2020-03-09 23:34:32', 3, '2020-03-09 23:34:32');
INSERT INTO `c_common_dictionary_item` VALUES (1237039105171259392, '0000', 3, 'EDUCATION', 'GAOZHONG', '高中', b'1', '', 3, 3, '2020-03-09 23:34:40', 3, '2020-03-09 23:34:40');
INSERT INTO `c_common_dictionary_item` VALUES (1237039160271831040, '0000', 3, 'EDUCATION', 'QITA', '其他', b'1', '', 20, 3, '2020-03-09 23:34:54', 3, '2020-03-09 23:34:54');
INSERT INTO `c_common_dictionary_item` VALUES (1237040064488275968, '0000', 1, 'NATION', 'mz_qt', '其他', b'1', '', 100, 3, '2020-03-09 23:38:29', 3, '2020-03-09 23:38:29');
INSERT INTO `c_common_dictionary_item` VALUES (1237040319480987648, '0000', 2, 'POSITION_STATUS', 'LEAVE', '请假', b'1', '', 3, 3, '2020-03-09 23:39:30', 3, '2020-03-09 23:39:30');
COMMIT;

-- ----------------------------
-- Table structure for c_common_login_log
-- ----------------------------
DROP TABLE IF EXISTS `c_common_login_log`;
CREATE TABLE `c_common_login_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `request_ip` varchar(50) DEFAULT '' COMMENT '登录IP',
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
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_KEY` (`tenant_code`,`key_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='参数配置';

-- ----------------------------
-- Table structure for c_core_org
-- ----------------------------
DROP TABLE IF EXISTS `c_core_org`;
CREATE TABLE `c_core_org` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `label` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
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
INSERT INTO `c_core_org` VALUES (100, '0000', '最后集团股份有限公司', '最后集团2', 0, ',', 1, b'1', '初始化数据', '2019-07-10 17:02:18', 1, '2019-07-10 17:02:16', 1);
INSERT INTO `c_core_org` VALUES (101, '0000', '最后集团股份有限公司广州子公司', '广州最后集团', 100, ',100,', 0, b'1', '初始化数据', '2019-08-06 09:10:53', 1, '2019-11-12 11:36:48', 3);
INSERT INTO `c_core_org` VALUES (102, '0000', '最后集团股份有限公司北京分公司', '北京最后集团', 100, ',100,', 1, b'1', '初始化数据', '2019-11-07 16:13:09', 1, '2019-11-07 16:13:12', 1);
INSERT INTO `c_core_org` VALUES (643775612976106881, '0000', '综合部', '', 101, ',100,101,', 0, b'1', '前台&HR', '2019-11-12 11:34:27', 3, '2019-11-12 11:34:27', 3);
INSERT INTO `c_core_org` VALUES (643775664683486689, '0000', '管理层', '', 100, ',100,', 3, b'1', '', '2019-11-12 11:34:39', 3, '2019-11-12 11:36:16', 3);
INSERT INTO `c_core_org` VALUES (643775904077582049, '0000', '总经办', '', 100, ',100,', 2, b'1', '', '2019-11-12 11:35:37', 3, '2019-11-12 11:36:52', 3);
INSERT INTO `c_core_org` VALUES (643776324342648929, '0000', '财务部', '', 100, ',100,', 4, b'1', '', '2019-11-12 11:37:17', 3, '2019-11-12 11:37:40', 3);
INSERT INTO `c_core_org` VALUES (643776407691858113, '0000', '市场部', '', 100, ',100,', 5, b'1', '', '2019-11-12 11:37:37', 3, '2019-11-12 11:37:37', 3);
INSERT INTO `c_core_org` VALUES (643776508795556193, '0000', '销售部', '', 100, ',100,', 6, b'1', '', '2019-11-12 11:38:01', 3, '2019-11-12 11:38:01', 3);
INSERT INTO `c_core_org` VALUES (643776594376135105, '0000', '研发部', '', 101, ',100,101,', 1, b'1', '', '2019-11-12 11:38:21', 3, '2019-11-12 11:38:21', 3);
INSERT INTO `c_core_org` VALUES (643776633823564321, '0000', '产品部', '', 101, ',100,101,', 2, b'1', '', '2019-11-12 11:38:31', 3, '2019-11-12 11:38:31', 3);
INSERT INTO `c_core_org` VALUES (643776668917305985, '0000', '综合部', '', 102, ',100,102,', 0, b'1', '', '2019-11-12 11:38:39', 3, '2019-11-12 11:38:39', 3);
INSERT INTO `c_core_org` VALUES (643776713909605089, '0000', '研发部', '', 102, ',100,102,', 0, b'1', '', '2019-11-12 11:38:50', 3, '2019-11-12 11:38:50', 3);
INSERT INTO `c_core_org` VALUES (643776757199016769, '0000', '销售部', '', 102, ',100,102,', 2, b'1', '', '2019-11-12 11:39:00', 3, '2019-11-12 11:39:00', 3);
COMMIT;

-- ----------------------------
-- Table structure for c_core_station
-- ----------------------------
DROP TABLE IF EXISTS `c_core_station`;
CREATE TABLE `c_core_station` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
INSERT INTO `c_core_station` VALUES (100, '0000', '总经理', 643775904077582049, b'1', '总部-1把手', '2019-07-10 17:03:03', 1, '2019-11-16 09:59:17', 3);
INSERT INTO `c_core_station` VALUES (101, '0000', '副总经理', 643775904077582049, b'1', '总部-2把手', '2019-07-22 17:07:55', 1, '2019-11-16 09:59:21', 3);
INSERT INTO `c_core_station` VALUES (642032719487828225, '0000', '研发经理', 643776594376135105, b'1', '子公司-研发部老大', '2019-11-07 16:08:49', 3, '2019-11-16 09:53:42', 3);
INSERT INTO `c_core_station` VALUES (645199319300842081, '0000', '副总经理', 101, b'1', '子公司-老大', '2019-11-16 09:51:45', 3, '2019-11-16 09:53:50', 3);
INSERT INTO `c_core_station` VALUES (645199745026892801, '0000', '产品经理', 643776633823564321, b'1', '子公司-产品部老大', '2019-11-16 09:53:27', 3, '2019-11-16 09:54:01', 3);
INSERT INTO `c_core_station` VALUES (645200064280536545, '0000', '人事经理', 643775612976106881, b'1', '子公司-综合老大', '2019-11-16 09:54:43', 3, '2019-11-16 09:54:43', 3);
INSERT INTO `c_core_station` VALUES (645200151886964289, '0000', 'Java工程师', 643776594376135105, b'1', '普通员工', '2019-11-16 09:55:04', 3, '2019-11-16 09:55:04', 3);
INSERT INTO `c_core_station` VALUES (645200250243393185, '0000', '需求工程师', 643776633823564321, b'1', '普通员工', '2019-11-16 09:55:27', 3, '2019-11-16 09:55:27', 3);
INSERT INTO `c_core_station` VALUES (645200304014370561, '0000', 'UI工程师', 643776633823564321, b'1', '普通员工', '2019-11-16 09:55:40', 3, '2019-11-16 09:55:40', 3);
INSERT INTO `c_core_station` VALUES (645200358959753057, '0000', '运维工程师', 643776594376135105, b'1', '普通员工', '2019-11-16 09:55:53', 3, '2019-11-16 09:55:53', 3);
INSERT INTO `c_core_station` VALUES (645200405453612993, '0000', '前台小姐姐', 643775612976106881, b'1', '普通员工', '2019-11-16 09:56:04', 3, '2019-11-16 09:56:04', 3);
INSERT INTO `c_core_station` VALUES (645200545698555937, '0000', '人事经理', 643776668917305985, b'1', '北京分公司-综合部老大', '2019-11-16 09:56:38', 3, '2019-11-16 09:56:38', 3);
INSERT INTO `c_core_station` VALUES (645200670781089921, '0000', '研发经理', 643776713909605089, b'1', '北京分公司-研发部老大', '2019-11-16 09:57:07', 3, '2019-11-16 09:57:07', 3);
INSERT INTO `c_core_station` VALUES (645200806559099105, '0000', '销售经理', 643776757199016769, b'1', '北京销售部老大', '2019-11-16 09:57:40', 3, '2019-11-16 09:57:40', 3);
INSERT INTO `c_core_station` VALUES (645200885772724545, '0000', '行政', 643776668917305985, b'1', '普通员工', '2019-11-16 09:57:59', 3, '2019-11-16 09:57:59', 3);
INSERT INTO `c_core_station` VALUES (645200938289605025, '0000', '大前端工程师', 643776713909605089, b'1', '普通员工', '2019-11-16 09:58:11', 3, '2019-11-16 09:58:11', 3);
INSERT INTO `c_core_station` VALUES (645201064705927681, '0000', '销售员工', 643776757199016769, b'1', '普通员工', '2019-11-16 09:58:41', 3, '2019-11-16 09:58:41', 3);
INSERT INTO `c_core_station` VALUES (645201184268757601, '0000', '销售总监', 643775664683486689, b'1', '总部2把手', '2019-11-16 09:59:10', 3, '2019-11-16 09:59:10', 3);
INSERT INTO `c_core_station` VALUES (645201307765844833, '0000', '财务总监', 643776324342648929, b'1', '总部2把手', '2019-11-16 09:59:39', 3, '2019-11-16 09:59:39', 3);
INSERT INTO `c_core_station` VALUES (645201405757369281, '0000', '市场经理', 643776407691858113, b'1', '总部市场部老大', '2019-11-16 10:00:03', 3, '2019-11-16 10:00:03', 3);
INSERT INTO `c_core_station` VALUES (645201481133206561, '0000', '销售总监', 643776508795556193, b'1', '总部销售部老大', '2019-11-16 10:00:21', 3, '2019-11-16 10:00:21', 3);
INSERT INTO `c_core_station` VALUES (645201573391117441, '0000', '前端工程师', 643776594376135105, b'1', '普通员工', '2019-11-16 10:00:43', 3, '2019-11-16 10:00:43', 3);
COMMIT;

-- ----------------------------
-- Table structure for f_attachment
-- ----------------------------
DROP TABLE IF EXISTS `f_attachment`;
CREATE TABLE `f_attachment` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
-- Table structure for f_file
-- ----------------------------
DROP TABLE IF EXISTS `f_file`;
CREATE TABLE `f_file` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `code` varchar(255) DEFAULT NULL COMMENT '编号',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='订单(用于测试)';

-- ----------------------------
-- Table structure for m_product
-- ----------------------------
DROP TABLE IF EXISTS `m_product`;
CREATE TABLE `m_product` (
  `id` bigint(20) NOT NULL,
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `type_` text COMMENT '商品类型\n#ProductType{ordinary:普通;gift:赠品}',
  `type2` longtext COMMENT '商品类型2\n#{ordinary:普通;gift:赠品;}',
  `type3` varchar(255) DEFAULT NULL COMMENT '学历\n@InjectionField(api = DICTIONARY_ITEM_FEIGN_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>\n',
  `status` bit(1) DEFAULT NULL COMMENT '状态',
  `test4` tinyint(10) DEFAULT NULL,
  `test5` date DEFAULT NULL COMMENT '时间',
  `test6` datetime DEFAULT NULL COMMENT '日期',
  `parent_id` bigint(20) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL COMMENT '名称',
  `sort_value` int(11) DEFAULT NULL,
  `test7` char(10) DEFAULT NULL COMMENT 'xxx\n@InjectionField(api = “userApi”, method = USER_ID_NAME_METHOD) RemoteData<Long, String>',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户\n@InjectionField(api = USER_ID_FEIGN_CLASS, method = USER_ID_NAME_METHOD) RemoteData<Long, String>',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织\n@InjectionField(api = ORG_ID_FEIGN_CLASS, method = "findOrgNameByIds") RemoteData<Long, String>',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品(用于测试)';

-- ----------------------------
-- Table structure for mail_provider
-- ----------------------------
DROP TABLE IF EXISTS `mail_provider`;
CREATE TABLE `mail_provider` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='邮件供应商';

-- ----------------------------
-- Table structure for mail_send_status
-- ----------------------------
DROP TABLE IF EXISTS `mail_send_status`;
CREATE TABLE `mail_send_status` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `task_id` bigint(20) NOT NULL COMMENT '任务id\n#mail_task',
  `email` varchar(64) NOT NULL COMMENT '收件邮箱',
  `mail_status` varchar(255) NOT NULL DEFAULT 'UNREAD' COMMENT '邮件状态\r\n#MailStatus{UNREAD:未读;READ:已读;DELETED:已删除;ABNORMAL:异常;VIRUSES:病毒;TRASH:垃圾}',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='邮件发送状态';

-- ----------------------------
-- Table structure for mail_task
-- ----------------------------
DROP TABLE IF EXISTS `mail_task`;
CREATE TABLE `mail_task` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='邮件任务';

-- ----------------------------
-- Table structure for msgs_center_info
-- ----------------------------
DROP TABLE IF EXISTS `msgs_center_info`;
CREATE TABLE `msgs_center_info` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `biz_id` varchar(64) DEFAULT NULL COMMENT '业务ID\n业务表的唯一id',
  `biz_type` varchar(64) DEFAULT NULL COMMENT '业务类型\n#MsgsBizType{USER_LOCK:账号锁定;USER_REG:账号申请;WORK_APPROVAL:考勤审批;}',
  `msgs_center_type` varchar(20) NOT NULL DEFAULT 'NOTIFY' COMMENT '消息类型\n#MsgsCenterType{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `content` text COMMENT '内容',
  `author` varchar(50) DEFAULT '' COMMENT '发布人',
  `handler_url` varchar(255) DEFAULT '' COMMENT '处理地址\n以http开头时直接跳转，否则与#c_application表拼接后跳转\nhttp可带参数',
  `handler_params` varchar(400) DEFAULT '' COMMENT '处理参数',
  `is_single_handle` bit(1) DEFAULT b'1' COMMENT '是否单人处理',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='消息中心表';

-- ----------------------------
-- Table structure for msgs_center_info_receive
-- ----------------------------
DROP TABLE IF EXISTS `msgs_center_info_receive`;
CREATE TABLE `msgs_center_info_receive` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `msgs_center_id` bigint(20) NOT NULL COMMENT '消息中心ID\n#msgs_center_info',
  `user_id` bigint(20) NOT NULL COMMENT '接收人ID\n#c_user',
  `is_read` bit(1) DEFAULT b'0' COMMENT '是否已读\n#BooleanStatus',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_user` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='消息中心接收表';

-- ----------------------------
-- Table structure for sms_send_status
-- ----------------------------
DROP TABLE IF EXISTS `sms_send_status`;
CREATE TABLE `sms_send_status` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='短信发送状态';

-- ----------------------------
-- Table structure for sms_task
-- ----------------------------
DROP TABLE IF EXISTS `sms_task`;
CREATE TABLE `sms_task` (
  `id` bigint(20) NOT NULL COMMENT '短信记录ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='发送任务\n所有的短息发送调用，都视为是一次短信任务，任务表只保存数据和执行状态等信息，\n具体的发送状态查看发送状态（#sms_send_status）表';

-- ----------------------------
-- Table structure for sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template` (
  `id` bigint(20) NOT NULL COMMENT '模板ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
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
  UNIQUE KEY `UN_CODE` (`custom_code`,`tenant_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='短信模板';

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
