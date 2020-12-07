/*
lamp_nacos、lamp_seata、lamp_sw、lamp_zipkin是第三方组件的库， 分别为：nacos、seata、SkyWalking、zipkin 服务端需要的库

lamp_none、lamp_activiti、lamp_column、lamp_defaults、lamp_base_0000、lamp_extend_0000 是lamp-cloud项目需要的库
 */
-- none 模式
CREATE DATABASE IF NOT EXISTS `lamp_nacos` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `lamp_seata` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `lamp_sw` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `lamp_zipkin` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS `lamp_none` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `lamp_activiti` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- column 模式
CREATE DATABASE IF NOT EXISTS `lamp_nacos` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `lamp_seata` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `lamp_sw` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `lamp_zipkin` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS `lamp_column` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `lamp_activiti` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- schema 模式、datasource模式 （暂不支持工作流）
CREATE DATABASE IF NOT EXISTS `lamp_nacos` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `lamp_seata` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `lamp_zipkin` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `lamp_sw` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS `lamp_defaults` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `lamp_base_0000` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `lamp_extend_0000` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


