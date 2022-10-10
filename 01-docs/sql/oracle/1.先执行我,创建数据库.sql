/*
lamp_nacos、lamp_seata、lamp_sw是第三方组件的库， 分别为：nacos、seata、SkyWalking服务端需要的库

 */
-- 三方组件
-- nacos 数据库
create user lamp_nacos identified by lamp_nacos;
-- seata 数据库
create user lamp_seata identified by lamp_seata;
-- SkyWalking 数据库
create user lamp_sw identified by lamp_sw;
grant create session,create table,create sequence,create view,resource to lamp_nacos;
grant create session,create table,create sequence,create view,resource to lamp_seata;
grant create session,create table,create sequence,create view,resource to lamp_sw;


-- none 模式 业务库
create user lamp_none identified by lamp_none;
grant create session,create table,create sequence,create view,resource to lamp_none;

-- column 模式 业务库
create user lamp_column identified by lamp_column;
grant create session,create table,create sequence,create view,resource to lamp_column;


-- schema 模式 暂不支持 oracle

-- datasource 模式 业务库
create user lamp_defaults identified by lamp_defaults;
create user lamp_base_0000 identified by lamp_base_0000;
create user lamp_extend_0000 identified by lamp_extend_0000;
grant create session,create table,create sequence,create view,resource,dba to lamp_defaults;
grant create session,create table,create sequence,create view,resource to lamp_base_0000;
grant create session,create table,create sequence,create view,resource to lamp_extend_0000;

