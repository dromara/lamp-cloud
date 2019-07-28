/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 127.0.0.1:3306
 Source Schema         : zuihou_file_dev

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 28/07/2019 23:39:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `app_code` varchar(64) DEFAULT NULL COMMENT '应用ID\n#c_application',
  `group_` varchar(255) DEFAULT '' COMMENT 'FastDFS返回的组\n用于FastDFS',
  `path` varchar(255) DEFAULT '' COMMENT 'FastDFS的远程文件名\n用于FastDFS',
  `relative_path` varchar(255) DEFAULT '' COMMENT '文件相对路径',
  `url` varchar(255) DEFAULT '' COMMENT '文件访问链接\n需要通过nginx配置路由，才能访问',
  `characters` mediumtext COMMENT '语音转文字',
  `thumbnail` varchar(255) DEFAULT '' COMMENT '视频截图地址',
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
INSERT INTO `f_attachment` VALUES (592768934055247937, '592768933551931425', 'a', 'IMAGE', '2.jpg', '', '', '', '2019/06', 'http://192.168.1.34:8765/file/2019/06/7220cf32-6823-474d-9cd4-ad82c65b1181.jpg', NULL, '', NULL, 'image/jpeg', '7220cf32-6823-474d-9cd4-ad82c65b1181.jpg', 'jpg', 7973, NULL, 'fa-file-photo-o', '2019年06月', '2019年26周', '2019年06月24日', '2019-06-24 17:32:07', 1, '2019-06-24 17:32:07', 1);
INSERT INTO `f_attachment` VALUES (599192059835842625, '599192059416412193', 'a', 'IMAGE', '2.jpg', NULL, '', '', '2019/07', 'http://192.168.1.34:8765/file/2019/07/d3d99fc0-4c77-49d0-86bb-8bd0517564d5.jpg', NULL, '', NULL, 'image/jpeg', 'd3d99fc0-4c77-49d0-86bb-8bd0517564d5.jpg', 'jpg', 7973, NULL, 'fa-file-photo-o', '2019年07月', '2019年28周', '2019年07月12日', '2019-07-12 10:55:20', 2, '2019-07-12 10:55:20', 2);
INSERT INTO `f_attachment` VALUES (604011771941879841, '123', '123', 'IMAGE', '2.jpg', NULL, '', '', '2019/07', 'http://192.168.1.34:8765/file/2019/07/de9a0750-dff0-4f9c-bcc0-58513fe6bfd3.jpg?attname=2.jpg', NULL, '', NULL, 'image/jpeg', 'de9a0750-dff0-4f9c-bcc0-58513fe6bfd3.jpg', 'jpg', 7973, 0, 'fa-file-photo-o', '2019年07月', '2019年30周', '2019年07月25日', '2019-07-25 18:07:09', 0, '2019-07-25 18:07:09', 0);
COMMIT;

-- ----------------------------
-- Table structure for f_down_water
-- ----------------------------
DROP TABLE IF EXISTS `f_down_water`;
CREATE TABLE `f_down_water` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_code` varchar(64) DEFAULT NULL COMMENT '应用CODE\n#c_application',
  `file_id` bigint(11) NOT NULL COMMENT '文件ID\n#f_file',
  `create_month` varchar(10) DEFAULT NULL COMMENT '创建年月\n格式：yyyy-MM 用于统计',
  `create_week` varchar(10) DEFAULT NULL COMMENT '创建年周\nyyyy-ww 用于统计',
  `create_day` varchar(12) DEFAULT NULL COMMENT '创建年月日\n格式： yyyy-MM-dd 用于统计',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件下载流水';

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
  `url` varchar(255) DEFAULT '' COMMENT '文件访问链接\n需要通过nginx配置路由，才能访问',
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
-- Records of f_file
-- ----------------------------
BEGIN;
INSERT INTO `f_file` VALUES (595642561331200033, 'DIR', '你好\"123', ',', 1, b'0', -1, '', 0, '', '', '', '', '', '', '', '', 'fa-folder-o', '2019年07月', '2019年27周', '2019年07月02日', '2019-07-02 15:50:53', 0, '2019-07-02 15:50:53', 0);
COMMIT;

-- ----------------------------
-- Table structure for f_recycle
-- ----------------------------
DROP TABLE IF EXISTS `f_recycle`;
CREATE TABLE `f_recycle` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `data_type` varchar(255) DEFAULT NULL COMMENT '数据类型\n#DataType{DIR:目录;IMAGE:图片;VIDEO:视频;AUDIO:音频;DOC:文档;OTHER:其他}',
  `submitted_file_name` varchar(255) DEFAULT NULL COMMENT '原始文件名\n很长的换行的注释\n很长的换行的注释',
  `tree_path` varchar(255) DEFAULT ',' COMMENT '树形结构\n用,拼接所有父类的id',
  `grade` int(11) DEFAULT '1' COMMENT '层级等级\n从1开始计算',
  `folder_id` bigint(20) DEFAULT NULL COMMENT '文件夹id',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小\n单位字节',
  `folder_name` varchar(255) DEFAULT NULL COMMENT '父文件夹名称',
  `group_` varchar(255) DEFAULT '' COMMENT 'FastDFS组',
  `path` varchar(255) DEFAULT '' COMMENT 'FastDFS远程文件名',
  `relative_path` varchar(255) DEFAULT NULL COMMENT '文件的相对路径 ',
  `file_md5` varchar(255) DEFAULT NULL COMMENT 'md5值',
  `mime` varchar(255) DEFAULT NULL COMMENT '类型',
  `context_type` varchar(255) DEFAULT NULL COMMENT '文件类型',
  `filename` varchar(255) DEFAULT NULL COMMENT '文件名',
  `ext` varchar(64) DEFAULT NULL COMMENT '后缀\n(没有.)',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE,
  FULLTEXT KEY `FU_TREE_PATH` (`tree_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件回收站';

-- ----------------------------
-- Table structure for f_share
-- ----------------------------
DROP TABLE IF EXISTS `f_share`;
CREATE TABLE `f_share` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(255) DEFAULT '' COMMENT '分享名称',
  `password` varchar(32) DEFAULT '' COMMENT '分享密码',
  `icon` varchar(64) DEFAULT '' COMMENT '图标',
  `url` varchar(255) DEFAULT '' COMMENT '链接',
  `expire_time` datetime DEFAULT NULL COMMENT '分享过期时间',
  `thumbs_up` int(11) DEFAULT '0' COMMENT '点赞次数',
  `download_count` int(11) DEFAULT '0' COMMENT '下载次数',
  `save_count` int(11) DEFAULT '0' COMMENT '保存次数',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `create_user_name` varchar(255) DEFAULT '' COMMENT '创建人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分享目录表';

-- ----------------------------
-- Table structure for f_share_file
-- ----------------------------
DROP TABLE IF EXISTS `f_share_file`;
CREATE TABLE `f_share_file` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `account_id` bigint(20) DEFAULT NULL COMMENT '人员ID\n#c_auth_account',
  `share_id` bigint(20) DEFAULT NULL COMMENT '分享ID\n#f_share',
  `file_id` bigint(20) DEFAULT NULL COMMENT '文件ID\n#f_fil',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分享文件表';

SET FOREIGN_KEY_CHECKS = 1;
