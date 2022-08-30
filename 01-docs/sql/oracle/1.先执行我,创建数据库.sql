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

-- none 模式 业务库
CREATE DATABASE lamp_none COLLATE Chinese_PRC_CI_AS;
GO

-- column 模式 业务库
CREATE DATABASE lamp_column COLLATE Chinese_PRC_CI_AS;
GO

-- schema 模式 暂不支持 oracle


-- 给用户授权
grant create session,create table,create sequence,create view,resource to lamp_nacos;
grant create session,create table,create sequence,create view,resource to lamp_seata;
grant create session,create table,create sequence,create view,resource to lamp_sw;
grant create session,create table,create sequence,create view,resource to lamp_none;
grant create session,create table,create sequence,create view,resource to lamp_column;
