# zuihou-admin-cloud
基于SpringCloud(Finchley.M2)  + SpringBoot(2.0.0.M2) 的微服务 SaaS 开发平台，
具有统一授权、认证后台管理系统，其中包含具备用户管理、资源权限管理、网关API、OpenAPI管理等多个模块，
支持多业务系统并行开发，可以作为后端服务的开发脚手架。代码简洁，架构清晰，适合学习和直接项目中使用。
核心技术采用Eureka、Fegin、Ribbon、Zuul、Hystrix、JWT Token、Mybatis、SpringBoot、Redis、
RibbitMQ、FastDFS等主要框架和中间件。

该项目为本人在学习过程中通过一些其他的开源项目，资料，文章进行整合的一个项目。
目前国内的一些资料讲解和使用的SpringCloud版本都比较低，自己在基于现有的开源项目和资料学习的同时，
一边以自己的一些想法改造搭建一个相对较新版本的项目。

后期会引入Docker（慢慢学，还没到这一步）

## 架构详解:
服务鉴权:

通过JWT的方式来加强服务之间调度的权限验证，保证内部服务的安全性。

OpenAPI鉴权：

通过JWT的方式来加强对外暴露接口调度的权限验证，保证api接口的安全性。

监控：

利用Spring Boot Admin 来监控各个独立Service的运行状态；利用turbine来实时查看接口的运行状态和调用频率；通过Zipkin来查看各个服务之间的调用链等。

负载均衡：

将服务保留的rest进行代理和网关控制，除了平常经常使用的node.js、nginx外，Spring Cloud系列的zuul和rebbion，可以帮我们进行正常的网关管控和负载均衡。其中扩展和借鉴国外项目的扩展基于JWT的Zuul限流插件，方面进行限流。

服务注册与调用：

基于Eureka来实现的服务注册与调用，在Spring Cloud中使用Feign, 我们可以做到使用HTTP请求远程服务时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。

熔断机制：

因为采取了服务的分布，为了避免服务之间的调用“雪崩”，采用了Hystrix的作为熔断器，避免了服务之间的“雪崩”。

# 启动指南

### 环境须知：

- nginx (暂时没用到)
- mysql ，redis ，rabbitmq
- jdk1.8
- IDE插件一个(Eclipse, IDEA都需要安装插件)，`lombok插件`

### 项目结构:

```
├─zuihou-admin-cloud
│  │  
│  ├─zuihou-backend---------------------------后端服务
│  |  ├─zuihou-admin--------------------------后端管理服务
│  |  |  ├─zuihou-admin-api-------------------后端管理服务api接口
│  |  |  ├─zuihou-admin-api-impl--------------后端管理服务api实现
│  |  |  ├─zuihou-admin-repository------------后端管理业务/持久层
│  |  |  ├─zuihou-admin-server----------------后端管理服务
│  |  |─zuihou-base---------------------------基础模块服务
│  |  |  ├─zuihou-base-api--------------------基础模块接口/dto
│  |  |  ├─zuihou-base-api-impl---------------基础模块实现/po
│  |  |  ├─zuihou-base-repository-------------基础模块业务/持久层
│  |  |  ├─zuihou-base-server-----------------基础模块服务
│  |  ├─zuihou-file---------------------------文件模块服务
│  |  ├─zuihou-mail---------------------------邮件模块服务
│  |  ├─zuihou-sms----------------------------短信模块服务
│  |  ├─zuihou-auth---------------------------鉴权中心
│  |  |  |─zuihou-auth-client-----------------鉴权中心客户端
│  |  |  |─zuihou-auth-common-----------------鉴权中心公共包
│  |  |  |─zuihou-auth-server-----------------鉴权中心服务
│  |  ├─zuihou-gateway------------------------统一网关负载中心
│  |  |  |─zuihou-gateway-ratelimit--------------网关限流插件
│  |  |  |─zuihou-gateway-server-----------------项目网关服务
│  │ 
│  ├─zuihou-commons---------------------------公共模块（这里一直没想好怎么调整，有想法的朋友可以给我留言）
│  |  ├─zuihou-commons------------------------项目公共包
│  |  ├─zuihou-core---------------------------项目核心包
│  │ 
│  ├─zuihou-frontend--------------------------项目前端
│  |  ├─zuihou-admin-web----------------------项目前端
│  |  ├─zuihou-sso-client---------------------项目单点登录client
│  │
│  ├─zuihou-config-repo-----------------------远程配置文件仓库
│  │
│  ├─zuihou-service---------------------------服务模块
│  |  ├─zuihou-config-------------------------鉴权中心
│  |  ├─zuihou-eureka-------------------------鉴权中心
│  |  ├─zuihou-monitor------------------------spring-boot-admin监控中心
│  |  ├─zuihou-zipkin-------------------------zipkin分布式链路跟踪
│  │
│  │-...
```

### 运行步骤: （未完善 by 2018年1月17日 16:15:45）
- 运行环境分为2个，开发环境(dev),生产环境(prod), 开发环境部署单机，生产环境统一每个服务部署2台服务器
- 开发环境(dev)Hosts文件配置：
```
127.0.0.1 casserver.zuihou.com
127.0.0.1 eureka.zuihou.com eureka1.zuihou.com eureka2.zuihou.com
127.0.0.1 config.zuihou.com
127.0.0.1 admin.zuihou.com
127.0.0.1 open.zuihou.com
127.0.0.1 gateway.zuihou.com
127.0.0.1 auth.zuihou.com
127.0.0.1 monitor.zuihou.com
127.0.0.1 zipkin.zuihou.com
127.0.0.1 zuihou.rabbitmq.host
```
- 生产环境(prod)Hosts文件配置：

```
127.0.0.1 casserver.zuihou.com
127.0.0.1 eureka1.zuihou.com eureka2.zuihou.com
127.0.0.1 config.zuihou.com
127.0.0.1 admin.zuihou.com
127.0.0.1 open.zuihou.com
127.0.0.1 gateway.zuihou.com
127.0.0.1 auth.zuihou.com
127.0.0.1 monitor.zuihou.com
127.0.0.1 zipkin.zuihou.com
127.0.0.1 zuihou.rabbitmq.host
```

- 依次运行数据库脚本：
    - zuihou-backend/zuihou-base/zuihou-base-server/init.sql
    - zuihou-backend/zuihou-admin/zuihou-admin-server/init.sql

- 通过一下方法，进行密码加密：
```
    public static void main(String[] args) throws Exception {
        System.out.println(ConfigTools.encrypt("your mysql password"));
    }
```    

- 修改配置数据库配置：
    - zuihou-config-repo/zuihou-backend/zuihou-admin/application.yml
    - zuihou-config-repo/zuihou-backend/zuihou-base/application.yml

- 按`顺序`运行main类：
    - ZuihouEurekaApplication（zuihou-eureka）、
    - ZuihouOpenServerApplication（zuihou-open）、
    - ZuihouAdminServerApplication（zuihou-admin）、
    - ZuihouAuthApplication（zuihou-auth-server）、
    - ZuihouGateServerApplication（zuihou-gate）

  ​