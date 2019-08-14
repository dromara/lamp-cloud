# 第三方组件启动说明
## 大文件上传JS
详情： http://fex.baidu.com/webuploader/
采用百度的Webupload.js + FileChunkController 实现的大文件/分片/续传功能

## Nacos
详情： https://nacos.io/zh-cn/docs/what-is-nacos.html

### 创建数据库，导入数据
- 创建nacos_test数据库
- 导入 third-party/nacos/conf/nacos-mysql.sql

### 修改配置：
vim third-party/nacos/conf/application.properties
修改数据库配置

###启动：
cd third-party/nacos

Linux/Unix/Mac
启动命令(standalone代表着单机模式运行，非集群模式):
sh bin/startup.sh -m standalone

Windows
启动命令：
cmd bin/startup.cmd

###关闭：
Linux/Unix/Mac
sh bin/shutdown.sh
Windows
cmd bin/shutdown.cmd

### 访问
http://localhost:8848/nacos/
nacos/nacos


## seata
http://blog.itpub.net/31555607/viewspace-2640669/
### 创建数据库，导入数据
- 创建seata数据库
- 导入 third-party/seata/conf/db_store.sql

### 修改配置
cd third-party/seata/conf/
file.conf: 
```
store {
  ## store mode: file、db
  mode = "db"
  ## database store
  db {
     ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.
     datasource = "dbcp"
     ## mysql/oracle/h2/oceanbase etc.
     db-type = "mysql"
     url = "jdbc:mysql://127.0.0.1:3306/seata"
     user = "root"
     password = "root"
     min-conn = 1
     max-conn = 3
     global.table = "global_table"
     branch.table = "branch_table"
     lock-table = "lock_table"
     query-limit = 100
   }
}
```
logback.xml: (可选)
```
<property name="LOG_HOME" value="./logs/seata"/>
```
registry.conf
```
registry {
  # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  type = "eureka"
  eureka {
      serviceUrl = "http://zuihou:zuihou@127.0.0.1:8761/zuihou-eureka/eureka"
      application = "zuihou-admin-seata"
      weight = "1"
  }  
}
```
###启动
cd third-party/seata/
sh bin/seata-server.sh -p 8091 -h 192.168.1.34 -m db

参数解释：


window:
bin/seata-server.bat -p 8091 -h 192.168.1.34 -m db

### 在所有业务库中增加表：
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100),
  PRIMARY KEY (`id`),
  KEY `idx_unionkey` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
