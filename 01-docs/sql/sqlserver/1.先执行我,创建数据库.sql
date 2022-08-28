/*
lamp_nacos、lamp_seata、lamp_sw是第三方组件的库， 分别为：nacos、seata、SkyWalking服务端需要的库

lamp_none、lamp_activiti、lamp_column、lamp_defaults、lamp_base_0000、lamp_extend_0000 是lamp-cloud项目需要的库
 */
-- 三方组件
CREATE DATABASE lamp_seata COLLATE Chinese_PRC_CI_AS;
CREATE DATABASE lamp_seata COLLATE Chinese_PRC_CI_AS;
CREATE DATABASE lamp_sw COLLATE Chinese_PRC_CI_AS;
GO

-- none 模式 业务库
CREATE DATABASE lamp_none COLLATE Chinese_PRC_CI_AS;
CREATE DATABASE lamp_activiti COLLATE Chinese_PRC_CI_AS;
GO

-- column 模式 业务库
CREATE DATABASE lamp_column COLLATE Chinese_PRC_CI_AS;
CREATE DATABASE lamp_activiti COLLATE Chinese_PRC_CI_AS;
GO

-- schema 模式、datasource模式  业务库（暂不支持工作流）
CREATE DATABASE lamp_defaults COLLATE Chinese_PRC_CI_AS;
CREATE DATABASE lamp_base_0000 COLLATE Chinese_PRC_CI_AS;
CREATE DATABASE lamp_extend_0000 COLLATE Chinese_PRC_CI_AS;
GO
