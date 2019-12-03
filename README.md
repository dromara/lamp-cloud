# zuihou-admin-cloud

[![AUR](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://github.com/zuihou/zuihou-admin-cloud/blob/master/LICENSE)
[![](https://img.shields.io/badge/Author-zuihou-orange.svg)](https://github.com/zuihou/zuihou-admin-cloud)
[![](https://img.shields.io/badge/version-1.0-brightgreen.svg)](https://github.com/zuihou/zuihou-admin-cloud)
[![GitHub stars](https://img.shields.io/github/stars/zuihou/zuihou-admin-cloud.svg?style=social&label=Stars)](https://github.com/zuihou/zuihou-admin-cloud/stargazers)
[![star](https://gitee.com/zuihou111/zuihou-admin-cloud/badge/star.svg?theme=white)](https://gitee.com/zuihou111/zuihou-admin-cloud/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/zuihou/zuihou-admin-cloud.svg?style=social&label=Fork)](https://github.com/zuihou/zuihou-admin-cloud/network/members)
[![fork](https://gitee.com/zuihou111/zuihou-admin-cloud/badge/fork.svg?theme=white)](https://gitee.com/zuihou111/zuihou-admin-cloud/members)


## 简介：
基于`SpringCloud(Greenwich.RELEASE)`  + `SpringBoot(2.1.2.RELEASE)` 的 SaaS型微服务脚手架，具备用户管理、资源权限管理、网关统一鉴权、Xss防跨站攻击、自动代码生成、多存储系统、分布式事务、分布式定时任务等多个模块，支持多业务系统并行开发，
支持多服务并行开发，可以作为后端服务的开发脚手架。代码简洁，架构清晰，非常适合学习使用。核心技术采用Eureka、Fegin、Ribbon、Zuul、Hystrix、JWT Token、Mybatis、SpringBoot、Seata、Nacos、Sentinel、
RibbitMQ、FastDFS等主要框架和中间件。

希望能努力打造一套从 `SaaS基础框架` - `分布式微服务架构` - `持续集成` - `自动化部署` - `系统监测` 的解决方案。`本项目旨在实现基础能力，不涉及具体业务。`

部署方面, 可以采用以下4种方式，并会陆续公布jenkins集合以下3种部署方式的脚本和配置文件：
- IDEA 启动
- jar部署
- docker部署 
- k8s部署

## 如果您觉得有帮助，请点右上角 "Star" 支持一下，谢谢！

## 详细文档: https://www.kancloud.cn/zuihou/zuihou-admin-cloud
http://doc.tangyh.top/zuihou-admin-cloud


## 项目代码地址
微服务后端 代码：

[gitee] https://gitee.com/zuihou111/zuihou-admin-cloud  /[github] https://github.com/zuihou/zuihou-admin-cloud 

租户系统 代码：

[gitee] https://gitee.com/zuihou111/zuihou-ui  / [github] https://github.com/zuihou/zuihou-ui 

开发&运营管理系统 代码：

[gitee] https://gitee.com/zuihou111/zuihou-admin-ui  / [github] https://github.com/zuihou/zuihou-admin-ui  

[代码生成器] https://github.com/zuihou/zuihou-generator  


## 演示地址       （演示账号没有写权限，只能查询）  
[租户系统演示环境] http://tangyh.top:10000/zuihou-ui/

平台管理员账号/密码： zuihou/zuihou
普通用户账号/密码: test/zuiou

[开发&运营平台演示环境] http://tangyh.top:180/zuihou-admin-ui/

账号/密码： demoAdmin/zuihou

以下演示地址为老版本的演示，代码已经不在维护

[演示环境] http://42.202.130.216:10000/zuihou-ui                 

[注册&配置中心] http://42.202.130.216:10000/nacos/      

[在线文档] http://42.202.130.216:10000/api/gate/doc.html           

[定时任务] http://42.202.130.216:10000/zuihou-jobs-server

[监控中心] http://42.202.130.216:10000/zuihou-monitor/

[全链路监控中心] http://42.202.130.216:10000/zipkin/

## 交流群： 63202894
`一键加群` <a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=489800b9d07d017fa0b5104608a4bf755f1f38276b79f0ac5e6225d0d9897efb"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="zuihou-admin-cloud 交流" title="zuihou-admin-cloud 交流"></a>

![qq群.png](docs/image/qq群.png)

## 功能点介绍:
 - **服务注册&发现与调用：**

基于Nacos来实现的服务注册与发现，使用使用Feign来实现服务互调, 可以做到使用HTTP请求远程调用时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。

 - **服务鉴权:**

通过JWT的方式来加强服务之间调度的权限验证，保证内部服务的安全性。

 - **负载均衡：**

将服务保留的rest进行代理和网关控制，除了平常经常使用的node.js、nginx外，Spring Cloud系列的zuul和rebbion，可以帮我们进行正常的网关管控和负载均衡。其中扩展和借鉴国外项目的扩展基于JWT的Zuul限流插件，方面进行限流。

 - **熔断机制：**

因为采取了服务的分布，为了避免服务之间的调用“雪崩”，采用了Hystrix的作为熔断器，避免了服务之间的“雪崩”。

 - **监控：**

利用Spring Boot Admin 来监控各个独立Service的运行状态；利用turbine来实时查看接口的运行状态和调用频率；通过Zipkin来查看各个服务之间的调用链等。

 - **数据权限**

利用基于Mybatis的DataScopeInterceptor拦截器实现了简单的数据权限

 - **SaaS的无感解决方案**
 
 使用Mybatis拦截器实现对所有SQL的拦截，修改默认的Schema，从而实现多租户数据隔离的目的。
 
- **二级缓存**

采用J2Cache操作缓存，第一级缓存使用内存(Caffeine)，第二级缓存使用 Redis。 由于大量的缓存读取会导致 L2 的网络成为整个系统的瓶颈，因此 L1 的目标是降低对 L2 的读取次数。 
该缓存框架主要用于集群环境中。单机也可使用，用于避免应用重启导致的缓存冷启动后对后端业务的冲击。

- **优雅的Bean转换**

采用Dozer组件来对 DTO、DO、PO等对象的优化转换

- **前后端统一表单验证**

严谨的表单验证通常需要 前端+后端同时验证， 但传统的项目，均只能前后端各做一次检验， 后期规则变更，又得前后端同时修改。
故在`hibernate-validator`的基础上封装了`zuihou-validator-starter`起步依赖，提供一个通用接口，可以获取需要校验表单的规则，然后前端使用后端返回的规则，
以后若规则改变，只需要后端修改即可。

- **防跨站脚本攻击（XSS）**
- **当前用户信息注入器**
- **在线API**

由于原生swagger-ui某些功能支持不够友好，故采用了国内开源的`swagger-bootstrap-ui`，并制作了stater，方便springboot用户使用。

- **代码生成器**

基于Mybatis-plus-generator自定义了一套代码生成器， 通过配置数据库字段的注释，自动生成枚举类、数据字典注解、SaveDTO、UpdateDTO、表单验证规则注解、Swagger注解等。

- **定时任务调度器**：

基于xxl-jobs进行了功能增强。（如：指定时间发送任务、执行器和调度器合并项目、多数据源）

- **大文件/断点/分片续传**

前端采用webupload.js、后端采用NIO实现了大文件断点分片续传，启动Eureka、Zuul、File服务后，直接打开docs/chunkUploadDemo/demo.html即可进行测试。
经测试，本地限制堆栈最大内存128M启动File服务,5分钟内能成功上传4.6G+的大文件，正式服耗时则会受到用户带宽和服务器带宽的影响，时间比较长。

- **分布式事务**   
集成了阿里的分布式事务中间件：seata，以 **高效** 并且对业务 **0侵入** 的方式，解决 微服务 场景下面临的分布式事务问题。
   
## 项目架构图:  架构图.xml -> https://www.draw.io/
![架构图.png](docs/image/项目相关/架构图.png)

## 技术栈/版本介绍：
- 所涉及的相关的技术有：
    - JSON序列化:Jackson
    - 消息队列：RibbitMQ
    - 缓存：Redis 
    - 缓存框架：J2Cache 
    - 数据库： MySQL 5.7.9 (驱动6.0.6)
    - 定时器：采用xxl-jobs项目进行二次改造
    - 前端：vue 
    - 持久层框架： Mybatis-plus 
    - 代码生成器：基于Mybatis-plus-generator自定义  [https://github.com/zuihou/zuihou-generator.git]
    - API网关：Zuul 
    - 服务注册与发现：Eureka -> Nacos
    - 服务消费：OpenFeign
    - 负载均衡：Ribbon
    - 配置中心：Nacos
    - 服务熔断：Hystrix
    - 项目构建：Maven 3.3
    - 分布式事务： seata
    - 分布式系统的流量防卫兵： Sentinel
    - 监控： spring-boot-admin 2.x
    - 链路调用跟踪： zipkin 2.x
    - 文件服务器：FastDFS 5.0.5/阿里云OSS/本地存储
    - Nginx
- 部署方面：
    - 服务器：CentOS
    - Jenkins
    - Docker 18.09
    - Kubernetes 1.12

本代码采用 Intellij IDEA(2018.1 EAP) 来编写，但源码与具体的 IDE 无关。

PS: Lombok版本过低会导致枚举类型的参数无法正确获取参数，经过调试发现因为版本多低后，导致EnumDeserializer的 Object obj = p.getCurrentValue();取的值为空。


## 期待您的加入：
    1，前端  （急需！！只要你懂点vue、热爱开源，请加入我们的队伍吧）
    2，后端
    3，土豪哥哥(求赞助服务器)
    4，有想要合作或者赞助服务器的朋友加群（63202894）联系群主

## 如何贡献代码    
    1，Fork
    2，修改代码后提交pr
    3，等待合并
    4，合并超过5次的朋友，直接拉为项目开发者
    
    
## 项目截图：
spring-boot-admin监控界面:
![SBA监控.png](docs/image/监控界面/sba-1.png)
spring-boot-admin监控界面:
![SBA监控.png](docs/image/监控界面/sba-2.png)
API 界面:
![admin-api.png](docs/image/项目相关/admin-api.png)
注册中心界面:
![eureka注册中心界面.png](docs/image/项目相关/eureka.png)
定时任务调度界面:
![eureka注册中心界面.png](docs/image/项目相关/zuihou-jobs-server.png)

## 感谢：
- swagger-bootstrap-ui
- mybatis-plus
- xxl-jobs
- hutool
- guava
- 等等



## 写在最后：
    本项目正在开发阶段，由于码主白天要上班，只有晚上、周末能挤点时间来敲敲代码，所以进度可能比较慢，文档、注释也不齐全。 
    各位大侠就将就着看，但随着时间的推移。文档，注释，启动说明等码主我一定会补全的（对自己负责，也是对大家负责）。   
    
## 请作者买瓶防脱发药水吧~~~ o(╥﹏╥)o
![QnOjKA.png](https://s2.ax1x.com/2019/12/02/QnOjKA.png)
----
<img src="https://s2.ax1x.com/2019/12/02/QnjpLR.png" alt="QnjpLR.png" border="0" />

