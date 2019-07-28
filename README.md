# zuihou-admin-cloud

## 简介：
基于`SpringCloud(Greenwich.RELEASE)`  + `SpringBoot(2.1.2.RELEASE)` 的微服务 脚手架，
具备用户管理、资源权限管理、网关统一鉴权、Xss防跨站攻击、自动代码生成、多存储系统、分布式定时任务等多个模块，支持多业务系统并行开发，
支持多服务并行开发，可以作为后端服务的开发脚手架。代码简洁，架构清晰，非常适合学习使用。
核心技术采用Eureka、Fegin、Ribbon、Zuul、Hystrix、JWT Token、Mybatis、SpringBoot、
RibbitMQ、FastDFS等主要框架和中间件。

希望能努力打造一套从 `基础框架` - `分布式微服务架构` - `持续集成` - `自动化部署` - `系统监测` 的解决方案。

该项目为本人在学习过程中通过一些`其他的开源项目`，`资料`，`文章`进行整合的一个提供基础功能的项目。`本项目旨在实现基础能力，不设计具体业务。`
目前国内的一些资料讲解和使用的SpringCloud版本都比较低，而一些开源项目，则大多不适用于生产，
自己在基于现有的开源项目和资料学习的同时，将一些想法和最佳实践落地为本项目。

部署方面, 可以采用以下4种方式，并会陆续公布jenkins集合以下3种部署方式的脚本和配置文件：
- IDEA 启动
- jar部署
- docker部署 
- k8s部署

## 交流群： 63202894

## 项目地址
[github] https://github.com/zuihou/zuihou-admin-cloud

[gitee] https://gitee.com/zuihou111/zuihou-admin-cloud  (同步的github的代码，需要最新代码的同学请关注github项目)

[代码生成器] https://github.com/zuihou/zuihou-generator  (提示缺少 zuihou-generator 包，需要下载该项目，执行编译)

[前端] https://github.com/zuihou/zuihou-admin-ui  「开发中」

[原型] http://zuihou111.gitee.io/zuihou-admin-rp/

[demo] http://wzroom.cn/zuihou-ui  (admin/123456) 「开发中」

[注册中心] http://wzroom.cn/zuihou-eureka/  (admin/admin) 

[在线文档] http://wzroom.cn/api/gate/doc.html  (admin/admin) 



## 功能点介绍:
 - 服务注册与调用：

基于Eureka来实现的服务注册与调用，在Spring Cloud中使用Feign, 我们可以做到使用HTTP请求远程服务时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。

 - 服务鉴权:

通过JWT的方式来加强服务之间调度的权限验证，保证内部服务的安全性。

 - 负载均衡：

将服务保留的rest进行代理和网关控制，除了平常经常使用的node.js、nginx外，Spring Cloud系列的zuul和rebbion，可以帮我们进行正常的网关管控和负载均衡。其中扩展和借鉴国外项目的扩展基于JWT的Zuul限流插件，方面进行限流。

 - 熔断机制：

因为采取了服务的分布，为了避免服务之间的调用“雪崩”，采用了Hystrix的作为熔断器，避免了服务之间的“雪崩”。

 - 监控：

利用Spring Boot Admin 来监控各个独立Service的运行状态；利用turbine来实时查看接口的运行状态和调用频率；通过Zipkin来查看各个服务之间的调用链等。

 - 数据权限：

利用基于Mybatis的DataScopeInterceptor拦截器实现了简单的数据权限

- 优雅的Bean转换：

采用Dozer组件来对 DTO、DO、PO等对象的优化转换

- 前后端统一表单验证

严禁的表单通常需要 前端+后端同时验证， 但传统的项目，均只能前后端各做一次检验， 后期规则变更，又得前后端同时修改。
故在`hibernate-validator`的基础上封装了`zuihou-validator-starter`起步依赖，提供一个通用接口，可以获取需要校验表单的规则，然后前端使用后端返回的规则，
以后若规则改变，只需要后端修改即可。

- 防跨站脚本攻击（XSS）
- 当前用户信息注入器
- 在线API

由于原生swagger-ui某些功能支持不够友好，故采用了国内开源的`swagger-bootstrap-ui`，并制作了stater，方便springboot用户使用。

- 代码生成器

基于Mybatis-plus-generator自定义了一套代码生成器， 通过配置数据库字段的注释，自动生成枚举类、数据字典注解、SaveDTO、UpdateDTO、表单验证规则注解、Swagger注解等。

- 定时任务调度器：

基于xxl-jobs进行了功能增强。（如：指定时间发送任务、执行器和调度器合并项目、多数据源）




## 项目架构图
![frame.jpg](doc/image/项目相关/frame.jpg)

## 技术栈/版本介绍：
- 所涉及的相关的技术有：
    - JSON序列化:Jackson
    - 消息队列：RibbitMQ
    - 数据库： MySQL 5.7.9 (驱动6.0.6)
    - 定时器：采用xxl-jobs项目进行二次改造
    - Java模版：Thymeleaf  3.0.6.RELEASE
    - 前端：vue 
    - 持久层框架： Mybatis-plus 
    - 代码生成器：基于Mybatis-plus-generator自定义  [https://github.com/zuihou/zuihou-generator.git]
    - API网关：Zuul 
    - 服务注册与发现：Eureka 
    - 服务消费：OpenFeign
    - 负载均衡：Ribbon
    - 配置中心：Nacos
    - 服务熔断：Hystrix
    - 项目构建：Maven 3.3
    - 文件服务器：FastDFS 5.0.5/阿里云OSS/七牛/本地存储
    - Nginx
- 部署方面：
    - 服务器：CentOS
    - Jenkins
    - Docker 18.09
    - Kubernetes 1.12

本代码采用 Intellij IDEA(2018.1 EAP) 来编写，但源码与具体的 IDE 无关。

## 感谢：
- swagger-bootstrap-ui
- mybatis-plus
- xxl-jobs
- hutool
- guava
- 等等

## 约定：

- zuihou-xxx-api 模块中提供feign客户端
- 区分po、dto，不要把po中的所有字段都返回给前端。 前端需要什么字段，就返回什么字段
- 类名：首字母大写驼峰规则；方法名：首字母小写驼峰规则；常量：全大写；变量：首字母小写驼峰规则，尽量非缩写
- 业务模块接口层命名为`项目-业务-api`，如`zuihou-authority-api`
- 业务模块业务层命名为`项目-业务-biz`，如`zuihou-authority-biz`
- 业务模块控制层命名为`项目-业务-controller`，如`zuihou-authority-controller`
- 业务模块容器命名为`项目-业务-server`，如`zuihou-authority-server`
- 数据表命名为：`前缀_[模块_]表名`， 模块可有可无， 如`c_auth_role`、 `f_file`
- 注释：
```
表注释： 第一行用简短的文字来描述表的名称，会体现在Swagger中； 换行后对表进行详细介绍
字段注释： 第一行用简短的文字来描述字段的名称，会体现在Swagger的字段描述上； 换行后对字段进行详细的描述。
        另外，若字段需要使用枚举类型，则字段需要设置成varchar类型， 并在字段注释上使用 #枚举类型{KEY:描述;key2:描述;} 格式来描述枚举类型格式， 代码生成器会自动生成枚举类
        eg: #LogType{OPT:操作日志;EX:异常日志;}
类注释： 用 /** 开头的文档型注释， 并添加 @author @date 等参数
方法注释：  用 /** 开头的文档型注释， 并添加 @param @return 等参数
```
- 更多规范，参考[阿里巴巴Java开发手册] https://gitee.com/zuihou111/zuihou-admin-cloud/attach_files

## 小技巧
- 多线程编译： `clean install -T8`
- mapper类上增加注解`@Repository`, 防止`IDEA`提示注入报错。
- IDEA提示`@Autowired`注入失败时，可以用`@Resource` 防止`IDEA`提示注入报错。
- IDEA提交代码时，勾选Reformat code、Rearrange code、Optimize imports, 让代码更整洁


## 期待您的加入：
    1，前端哥哥
    2，后端哥哥
    3，土豪哥哥(求赞助服务器)
    4，小姐姐
    5，有想要合作或者赞助服务器的朋友加群（63202894）联系群主

## 环境须知：

- nginx (文件下载、预览时需要使用)
- mysql 5.7.9+
- JDK8
- IDE插件一个(Eclipse, IDEA都需要安装插件)，`lombok插件`

## 项目结构:

```
├─zuihou-admin-cloud
│  │  
│  ├─zuihou-backend---------------------------后端服务
│  |  ├─zuihou-api----------------------------常用API
│  |  ├─zuihou-authority----------------------后端管理服务[正在开发]
│  |  |  ├─zuihou-admin-biz-------------------后端管理业务/持久层
│  |  |  ├─zuihou-admin-controller------------后端管理业务/持久层
│  |  |  ├─zuihou-admin-server----------------后端管理服务
│  |  ├─zuihou-file---------------------------文件模块服务[基本完善]
│  |  ├─zuihou-msgs---------------------------消息模块服务[正在开发]
│  |  ├─zuihou-gateway------------------------统一网关负载中心
│  |  |  |─zuihou-gateway-ratelimit-----------网关限流插件[未开始]
│  |  |  |─zuihou-gateway-server--------------项目网关服务[未开始]
│  |  ├─zuihou-jobs---------------------------定时任务调度执行器[完成]
│  │ 
│  ├─zuihou-commons--------------------------公共模块   
│  |  ├─zuihou-common------------------------项目业务模块 （业务模块主要用于存放可能跟业务相关的公共代码）
│  |  ├─zuihou-core--------------------------项目核心模块 （核心模块存放无业务逻辑的公共代码）
│  |  ├─zuihou-databases---------------------项目数据源配置模块
│  |  ├─zuihou-dozer-starter-----------------优雅的bean转换起步依赖
│  |  ├─zuihou-jwt-starter-------------------JWT起步依赖
│  |  ├─zuihou-log-starter-------------------操作日志起步依赖
│  |  ├─zuihou-openfeign-starter-------------OpenFeign常用配置起步依赖
│  |  ├─zuihou-shiro-starter-----------------shiro起步依赖
│  |  ├─zuihou-swagger2-starter--------------SwaggerUI文档配置
│  |  ├─zuihou-user-starter------------------用户信息自动注入起步依赖
│  |  ├─zuihou-validator-starter-------------增强表单前后端统一验证起步依赖
│  |  ├─zuihou-xss-starter-------------------防XSS起步依赖
│  │ 
│  ├─zuihou-dependencies----------------------项目顶级pom
│  │ 
│  ├─zuihou-frontend--------------------------项目前端【考虑废弃】
│  |  ├─zuihou-manage-center------------------管理后台
│  │
│  ├─zuihou-support---------------------------服务模块
│  |  ├─zuihou-eureka-------------------------注册中心[已开发]
│  |  ├─zuihou-monitor------------------------spring-boot-admin监控中心[已开发]
│  |  ├─zuihou-zipkin-------------------------zipkin分布式链路跟踪[已开发]
│  │
│  │-...
```

## 运行步骤: 

- 1, 依次运行数据库脚本(开发阶段，数据库脚本可能更新不及时，有问题github、gitee上留言， 会第一次时间同步)：
    - doc/sql/zuihou_authority_dev.sql
    - doc/sql/zuihou_file_dev.sql

- 2, 在application-dev.yml文件修改配置数据库/redis/rabbitMQ等配置：
    
- 3， 在IDE中启动，编译通过后按如下顺序启动：
    - EurekaApplication
    - GatewayServerApplication
    - AuthorityApplication
    - FileServerApplication (可选)
    - JobsServerApplication (可选)
    - MonitorApplication    (可选)
    - 前端启动，参考 [前端] (https://github.com/zuihou/zuihou-admin-ui?_blank)

- 4， 命令行启动:
    - 先cd 到各个服务的target目录，依次启动即可：
    - java -jar -Dspring.profiles.active=dev zuihou-eureka.jar 
    - java -jar -Dspring.profiles.active=dev zuihou-***.jar  

## 端口号介绍 :

| 服务 | 端口号 |
|:----:|:----:|
| zuihou-eureka | 8761 |  ​
| zuihou-zipkin | 8767 |  ​ 
| zuihou-monitor | 8762,8763 |  ​
| - | - |​- | ​
| zuihou-gateway-server | 8760 |  ​
| zuihou-authority-server | 8764 |  ​
| zuihou-file-server | 8765 |  ​
| zuihou-msgs-server | 8766 |  ​


## 项目截图：
spring-boot-admin监控界面:
![SBA监控.png](doc/image/监控界面/zuihou-monitor监控界面1.png)
spring-boot-admin监控界面:
![SBA监控.png](doc/image/监控界面/zuihou-monitor监控界面2.png)
spring-boot-admin监控界面:
![SBA监控.png](doc/image/监控界面/zuihou-monitor监控界面3.png)
spring-boot-admin监控界面:
![SBA监控.png](doc/image/监控界面/zuihou-monitor监控界面4.png)
spring-boot-admin监控界面:
![SBA监控.png](doc/image/监控界面/zuihou-monitor监控界面5.png)
zipkin监控界面:
![zipkin监控.png](doc/image/监控界面/zuihou-zipkin监控界面1.png)
zipkin监控界面:
![zipkin监控.png](doc/image/监控界面/zuihou-zipkin监控界面2.png)
API 界面:
![admin-api.png](doc/image/项目相关/admin-api.png)
注册中心界面:
![eureka注册中心界面.png](doc/image/项目相关/eureka注册中心界面.png)

## 常见报错：
 - 1, 很多依赖死活都下载不下来？
    - 答： 由于spring-boot和spring-cloud等版本比较新，所以目前国内的一些仓库还没有新版本的jar。
    需要配置spring的maven仓库。 （配置后还是无法下载，就先注释掉settings.xml中其他的仓库，只保留这个）
```
    <mirror>
        <id>spring-milestones</id>
        <name>Spring Milestones</name>
        <url>https://repo.spring.io/libs-milestone</url>
        <mirrorOf>central</mirrorOf>
    </mirror>
```
 - 2, 很多类缺少get/set方法？
    - 答：请用IDEA或Eclipse安装`lombok`插件
    
 - 3, zuihou-generator jar缺失？
    - 答： https://github.com/zuihou/zuihou-generator 。去我github下载这个项目，自行编译。
    
 - 4, 为啥要将调度器和执行器合并在一起？
     - 答： 对于中小型项目，对高可用的需求并不是很大，若把调度器和执行器分开部署，会增加部署成本。    
     
## 查看磁盘空间占用
df -h
## 查看当前目录的磁盘占用
du -ah --max-depth=1
     
## 写在最后：
    本项目正在开发阶段，由于码主白天要上班，只有晚上、周末能挤点时间来敲敲代码，所以进度可能比较慢，文档、注释也不齐全。 
    各位大侠就将就着看，但随着时间的推移。文档，注释，启动说明等码主我一定会补全的（对自己负责，也是对大家负责）。   
