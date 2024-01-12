/*
由于 nacos  不支持 SQL Server，若您的项目确实需要使用 SQL Server 数据库，可以采用以下方案：
1. 修改nacos官方源码，使之支持 SQL Server （网上搜索解决方案）
2. 去Nacos官方提交申请，让他支持 SQL Server
3.（推荐）nacos 使用MySQL数据库，《灯灯》业务系统使用SQL Server

column 和 none 模式没有使用 Seata
*/
-- nacos 数据库
CREATE DATABASE lamp_nacos COLLATE Chinese_PRC_CI_AS;
GO
-- SkyWalking 数据库
CREATE DATABASE lamp_sw COLLATE Chinese_PRC_CI_AS;
GO


-- lamp-cloud-pro-none 和 lamp-boot-pro-none 数据库
CREATE DATABASE lamp_none COLLATE Chinese_PRC_CI_AS;
GO
