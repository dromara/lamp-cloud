# zuihou-admin-cloud

## 简介：
基于`SpringCloud(Greenwich.RELEASE)`  + `SpringBoot(2.1.2.RELEASE)` 的微服务 脚手架，
具有统一授权、认证后台管理系统，其中包含具备用户管理、配置中心、存储系统、资源权限管理、
网关API、OpenAPI管理、日志分析、任务和通知等多个模块，
支持多服务并行开发，可以作为后端服务的开发脚手架。代码简洁，架构清晰，非常适合学习使用。
核心技术采用Eureka、Fegin、Ribbon、Zuul、Hystrix、JWT Token、Mybatis、SpringBoot、Redis、
RibbitMQ、FastDFS等主要框架和中间件。

希望能努力打造一套从 `基础框架` - `分布式微服务架构` - `持续集成` - 
`自动化部署` - `系统监测` 的解决方案。

该项目为本人在学习过程中通过一些`其他的开源项目`，`资料`，`文章`进行整合的一个提供基础功能的项目。`本项目旨在实现基础能力，不设计具体业务。`
目前国内的一些资料讲解和使用的SpringCloud版本都比较低，自己在基于现有的开源项目和资料学习的同时，
一边以自己的一些想法改造搭建一个相对较新版本的项目。

部署方面, 可以采用以下3种方式，并会陆续公布jenkins集合以下3种部署方式的脚本和配置文件：
- jar部署
- docker部署 
- k8s部署

## 项目地址
[github] https://github.com/zuihou/zuihou-admin-cloud

[gitee] https://gitee.com/zuihou111/zuihou-admin-cloud  (同步的github的代码，需要最新代码的同学请关注github项目)

[代码生成器] https://github.com/zuihou/zuihou-generator  (提示缺少 zuihou-generator 包，需要下载该项目，执行编译)

[前端] https://github.com/zuihou/zuihou-admin-ui  (规划中)

## 模块详解:
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

实现了简单的数据权限

 - 定时任务调度器：

基于xxl-jobs进行了功能增强。（如：指定时间发送任务、执行器和调度器合并项目、多数据源） 



## 项目架构图
![frame.jpg](doc/image/项目相关/frame.jpg)

## 技术栈/版本介绍：
- 所涉及的相关的技术有：
    - JSON序列化:Jackson
    - 缓存：Redis 4.0.6
    - 消息队列：RibbitMQ
    - 数据库： MySQL 5.7.9 (驱动6.0.6)
    - 定时器：采用xxl-jobs项目进行二次改造
    - Java模版：Thymeleaf  3.0.6.RELEASE
    - 前端：Bootstrap + Vue2.0
    - API网关：Zuul 
    - 服务注册与发现：Eureka 
    - 代码生成器：Mybatis-plus  https://github.com/zuihou/zuihou-generator.git
    - 服务消费：OpenFeign
    - 负载均衡：Ribbon
    - 配置中心：Nacos
    - 服务熔断：Hystrix
    - 项目构建：Maven 3.3
    - 文件服务器：FastDFS 5.0.5
    - Nginx
- 部署方面：
    - 服务器：CentOS
    - Jenkins
    - Docker
    - Kubernetes

本代码采用 Intellij IDEA(2018.1 EAP) 来编写，但源码与具体的 IDE 无关。

## 约定：

- zuihou-xxx-api 项目中提供feign客户端，dto
- 区分po、dto，不要把po中的所有字段都返回给前端。 前端需要什么字段，就返回什么字段
- 类名：首字母大写驼峰规则；方法名：首字母小写驼峰规则；常量：全大写；变量：首字母小写驼峰规则，尽量非缩写
- 业务模块接口层命名为`项目`-`业务-api`，如`zuihou-authority-api`
- 业务模块业务层命名为`项目`-`业务-biz`，如`zuihou-authority-biz`
- 业务模块控制层命名为`项目`-`业务-controller`，如`zuihou-authority-controller`
- 业务模块容器命名为`项目`-`业务-server`，如`zuihou-authority-server`
- 数据表命名为：`子系统`_`表`，如`b_role`
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
- 多线程编译： clean install -T8 
- mapper类上增加注解@Repository, 防止 IDEA 提示注入报错。
- IDEA提交代码时，勾选Reformat code、Rearrange code、Optimize imports, 让代码更整洁


## 期待您的加入：
    1，前端哥哥
    2，后端哥哥
    3，土豪哥哥(求赞助服务器)
    4，小姐姐

## 启动指南

## 环境须知：

- nginx (文件下载、预览时需要使用)
- mysql 5.7.9+，redis 4+ ，rabbitmq 3.6+
- JDK8
- IDE插件一个(Eclipse, IDEA都需要安装插件)，`lombok插件`

## 项目结构:

```
├─zuihou-admin-cloud
│  │  
│  ├─zuihou-backend---------------------------后端服务
│  |  ├─zuihou-client-------------------------业务客服端/常用API
│  |  ├─zuihou-authority----------------------后端管理服务[正在开发]
│  |  |  ├─zuihou-admin-biz-------------------后端管理业务/持久层
│  |  |  ├─zuihou-admin-controller------------后端管理业务/持久层
│  |  |  ├─zuihou-admin-server----------------后端管理服务
│  |  ├─zuihou-file---------------------------文件模块服务[正在开发]
│  |  ├─zuihou-msgs---------------------------消息模块服务[正在开发]
│  |  ├─zuihou-gateway------------------------统一网关负载中心
│  |  |  |─zuihou-gateway-ratelimit-----------网关限流插件[未开始]
│  |  |  |─zuihou-gateway-server--------------项目网关服务[未开始]
│  |  ├─zuihou-jobs---------------------------定时任务调度执行器
│  │ 
│  ├─zuihou-commons--------------------------公共模块（这里一直没想好怎么调整，有想法的朋友可以给我留言）
│  |  ├─zuihou-common------------------------项目公共模块
│  |  ├─zuihou-core--------------------------项目核心模块
│  |  ├─zuihou-databases---------------------项目数据源配置模块
│  |  ├─zuihou-log---------------------------项目日志模块
│  |  ├─zuihou-swagger2-starter--------------项目SwaggerUI文档配置
│  │ 
│  ├─zuihou-frontend--------------------------项目前端
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
 接下来分别介绍开发环境(dev)和正式环境(prod1,prod2)的运行步骤. 
 生产环境所有服务单例运行，生产环境所有服务运行2个实例（除了zuihou-monitor,zuihou-zipkin.这2个监控服务）

### 开发环境
- 1, 依次运行数据库脚本：
    - doc/sql/zuihou_authority_dev.sql
    - doc/sql/zuihou_file_dev.sql

- 2, 修改配置数据库/redis/rabbitMQ配置：

- 3， 在IDE中启动：
- 3.1， 在IDE中启动：编译代码，修改启动参数：
    - 以IDEA为例， Eclipse 请自行意淫 (图片看不清，请看doc/image/**)
    - ![eureka.png](doc/image/启动配置/eureka(dev)启动配置.png)
    - ![authority.png](doc/image/启动配置/admin(dev)启动配置.png)
    - 这里只演示其中几个服务， 剩余的服务，按照相同的方法配置
    - 最终运行实例: ![启动.png](doc/image/启动配置/开发环境运行实例.png)
- 3.2，按`顺序`运行main类：
    - EurekaApplication（zuihou-eureka）  # 第一步
    - GatewayServerApplication（zuihou-gateway-server）#下面的顺序无所谓
    - AuthorityServerApplication（zuihou-authority-server）  
    - FileServerApplication（zuihou-file-server）  
    - MonitorApplication（zuihou-monitor）
    - ZipkinApplication（zuihou-zipkin）

- 4， 命令行启动:
    - 先cd 到各个服务的target目录，依次启动即可：
    - java -jar -Dspring.profiles.active=dev zuihou-eureka.jar 
    - java -jar -Dspring.profiles.active=dev zuihou-***.jar  

## 端口号介绍（dev）:

| 服务 | 端口号 |
|:----:|:----:|
| zuihou-eureka | 8500 |  ​
| zuihou-zipkin | 8510 |  ​ 
| zuihou-monitor | 8515,8516 |  ​
| - | - |​- | ​
| zuihou-gateway-server | 9770 |  ​
| zuihou-authority-server | 9765 |  ​
| zuihou-file-server | 9755 |  ​
| zuihou-msgs-server | 9745 |  ​


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
     
     
## 写在最后：
    本项目正在开发阶段，由于码主白天要上班，只有晚上、周末能挤点时间来敲敲代码，所以进度可能比较慢，文档、注释也不齐全。 
    各位大侠就将就着看，但随着时间的推移。文档，注释，启动说明等码主我一定会补全的（对自己负责，也是对大家负责）。   