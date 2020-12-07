-- column模式 新建lamp_column， 导入base_0000.sql + extend_0000.sql + defaults.sql ，在执行本脚本

-- 新增租户编码字段

ALTER TABLE `b_order` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `b_product` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_application` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_area` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_attachment` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_dictionary` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_login_log` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_menu` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_opt_log` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_opt_log_ext` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_org` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_parameter` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_resource` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_role` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_role_authority` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_role_org` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_station` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_user` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `c_user_role` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `e_block_list` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `e_msg` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `e_msg_receive` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `e_rate_limiter` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `e_sms_send_status` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `e_sms_task` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;
ALTER TABLE `e_sms_template` ADD COLUMN `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码' ;

-- 重建索引
ALTER TABLE `c_application` DROP INDEX `uk_client_id`;
ALTER TABLE `c_application` ADD UNIQUE INDEX `uk_client_id`(`tenant_code`, `client_id`) USING BTREE;
ALTER TABLE `c_area` DROP INDEX `uk_code`;
ALTER TABLE `c_area` ADD UNIQUE INDEX `uk_code`(`tenant_code`, `code`) USING BTREE;
ALTER TABLE `c_dictionary` DROP INDEX `uk_type_code`;
ALTER TABLE `c_dictionary` ADD UNIQUE INDEX `uk_type_code`(`tenant_code`, `type`,`code`) USING BTREE;
ALTER TABLE `c_parameter` DROP INDEX `uk_key`;
ALTER TABLE `c_parameter` ADD UNIQUE INDEX `uk_key`(`tenant_code`, `key_`) USING BTREE;
ALTER TABLE `c_menu` DROP INDEX `uk_path`;
ALTER TABLE `c_menu` ADD UNIQUE INDEX `uk_path`(`tenant_code`, `path`) USING BTREE;
ALTER TABLE `c_resource` DROP INDEX `uk_code`;
ALTER TABLE `c_resource` ADD UNIQUE INDEX `uk_code`(`tenant_code`, `code`) USING BTREE;
ALTER TABLE `c_role` DROP INDEX `uk_code`;
ALTER TABLE `c_role` ADD UNIQUE INDEX `uk_code`(`tenant_code`, `code`) USING BTREE;
ALTER TABLE `c_role_authority` DROP INDEX `uk_role_authority`;
ALTER TABLE `c_role_authority` ADD UNIQUE INDEX `uk_role_authority`(`tenant_code`, `authority_id`,`authority_type`,`role_id`) USING BTREE;
ALTER TABLE `c_role_org` DROP INDEX `uk_role_org`;
ALTER TABLE `c_role_org` ADD UNIQUE INDEX `uk_code`(`tenant_code`, `org_id`,`role_id`) USING BTREE;
ALTER TABLE `c_user` DROP INDEX `uk_account`;
ALTER TABLE `c_user` ADD UNIQUE INDEX `uk_account`(`tenant_code`, `account`) USING BTREE;
ALTER TABLE `c_user_role` DROP INDEX `uk_user_role`;
ALTER TABLE `c_user_role` ADD UNIQUE INDEX `uk_user_role`(`tenant_code`, `role_id`,`user_id`) USING BTREE;

-- 修改数据
update c_application set tenant_code = '0000';
update c_dictionary set tenant_code = '0000';
update c_menu set tenant_code = '0000';
update c_org set tenant_code = '0000';
update c_parameter set tenant_code = '0000';
update c_resource set tenant_code = '0000';
update c_role set tenant_code = '0000';
update c_role_authority set tenant_code = '0000';
update c_role_org set tenant_code = '0000';
update c_station set tenant_code = '0000';
update c_user set tenant_code = '0000';
update c_user_role set tenant_code = '0000';


