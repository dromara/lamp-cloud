/*
1. 模式 新建lamp_column库， 导入lamp_base_0000.sql + lamp_extend_0000.sql + lamp_defaults.sql
2. 在执行本脚本
*/
-- 新增租户编码字段

ALTER TABLE c_application ADD  tenant_code varchar(20)   ;
ALTER TABLE c_area ADD  tenant_code varchar(20)   ;
ALTER TABLE c_file ADD  tenant_code varchar(20)   ;
ALTER TABLE c_appendix ADD  tenant_code varchar(20)   ;
ALTER TABLE c_dictionary ADD  tenant_code varchar(20)   ;
ALTER TABLE c_login_log ADD  tenant_code varchar(20)   ;
ALTER TABLE c_menu ADD  tenant_code varchar(20)   ;
ALTER TABLE c_opt_log ADD  tenant_code varchar(20)   ;
ALTER TABLE c_opt_log_ext ADD  tenant_code varchar(20)   ;
ALTER TABLE c_org ADD  tenant_code varchar(20)   ;
ALTER TABLE c_parameter ADD  tenant_code varchar(20)   ;
ALTER TABLE c_resource ADD  tenant_code varchar(20)   ;
ALTER TABLE c_role ADD  tenant_code varchar(20)   ;
ALTER TABLE c_role_authority ADD  tenant_code varchar(20)   ;
ALTER TABLE c_role_org ADD  tenant_code varchar(20)   ;
ALTER TABLE c_station ADD  tenant_code varchar(20)   ;
ALTER TABLE c_user ADD  tenant_code varchar(20)   ;
ALTER TABLE c_user_role ADD  tenant_code varchar(20)   ;
ALTER TABLE e_block_list ADD  tenant_code varchar(20)   ;
ALTER TABLE e_msg ADD  tenant_code varchar(20)   ;
ALTER TABLE e_msg_receive ADD  tenant_code varchar(20)   ;
ALTER TABLE e_rate_limiter ADD  tenant_code varchar(20)   ;
ALTER TABLE e_sms_send_status ADD  tenant_code varchar(20)   ;
ALTER TABLE e_sms_task ADD  tenant_code varchar(20)   ;
ALTER TABLE e_sms_template ADD  tenant_code varchar(20)   ;

COMMENT ON COLUMN c_application.tenant_code IS '租户编码';
COMMENT ON COLUMN c_area.tenant_code IS '租户编码';
COMMENT ON COLUMN c_file.tenant_code IS '租户编码';
COMMENT ON COLUMN c_appendix.tenant_code IS '租户编码';
COMMENT ON COLUMN c_dictionary.tenant_code IS '租户编码';
COMMENT ON COLUMN c_login_log.tenant_code IS '租户编码';
COMMENT ON COLUMN c_menu.tenant_code IS '租户编码';
COMMENT ON COLUMN c_opt_log.tenant_code IS '租户编码';
COMMENT ON COLUMN c_opt_log_ext.tenant_code IS '租户编码';
COMMENT ON COLUMN c_org.tenant_code IS '租户编码';
COMMENT ON COLUMN c_parameter.tenant_code IS '租户编码';
COMMENT ON COLUMN c_resource.tenant_code IS '租户编码';
COMMENT ON COLUMN c_role.tenant_code IS '租户编码';
COMMENT ON COLUMN c_role_authority.tenant_code IS '租户编码';
COMMENT ON COLUMN c_role_org.tenant_code IS '租户编码';
COMMENT ON COLUMN c_station.tenant_code IS '租户编码';
COMMENT ON COLUMN c_user.tenant_code IS '租户编码';
COMMENT ON COLUMN c_user_role.tenant_code IS '租户编码';
COMMENT ON COLUMN e_block_list.tenant_code IS '租户编码';
COMMENT ON COLUMN e_msg.tenant_code IS '租户编码';
COMMENT ON COLUMN e_msg_receive.tenant_code IS '租户编码';
COMMENT ON COLUMN e_rate_limiter.tenant_code IS '租户编码';
COMMENT ON COLUMN e_sms_send_status.tenant_code IS '租户编码';
COMMENT ON COLUMN e_sms_task.tenant_code IS '租户编码';
COMMENT ON COLUMN e_sms_template.tenant_code IS '租户编码';

-- 重建索引
drop index uk_client_id;
CREATE UNIQUE INDEX uk_client_id ON c_application(tenant_code, client_id);
drop index uk_area_code;
CREATE UNIQUE INDEX uk_area_code ON c_area(tenant_code, code);
drop index uk_type_code;
CREATE UNIQUE INDEX uk_type_code ON c_dictionary(tenant_code, type,code) ;
drop index uk_param_key;
CREATE UNIQUE INDEX uk_param_key ON c_parameter(tenant_code, key_) ;
drop index uk_res_code;
CREATE UNIQUE INDEX uk_res_code ON c_resource(tenant_code, code) ;
drop index uk_role_code;
CREATE UNIQUE INDEX uk_role_code ON c_role(tenant_code, code) ;
drop index uk_role_authority;
CREATE UNIQUE INDEX uk_role_authority ON c_role_authority(tenant_code, authority_id,authority_type,role_id) ;
drop index uk_role_org;
CREATE UNIQUE INDEX uk_role_org ON c_role_org(tenant_code, org_id,role_id) ;
drop index uk_user_account;
CREATE UNIQUE INDEX uk_user_account ON c_user(tenant_code, account) ;
drop index uk_user_role;
CREATE UNIQUE INDEX uk_user_role ON c_user_role(tenant_code, role_id,user_id) ;

-- 修改数据
update c_application set tenant_code = '0000';
update c_dictionary set tenant_code = '0000';
update c_appendix set tenant_code = '0000';
update c_file set tenant_code = '0000';
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


