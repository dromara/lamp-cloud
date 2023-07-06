# lamp 快速开发平台

[![Language](https://img.shields.io/badge/语言-Java%20%7C%20SpringCloud%20%7C%20Vue3%20%7C%20...-red?style=flat-square&color=42b883)](https://github.com/dromara/lamp-cloud)
[![License](https://img.shields.io/github/license/dromara/lamp-cloud?color=42b883&style=flat-square)](https://github.com/dromara/lamp-cloud/blob/master/LICENSE)
[![Author](https://img.shields.io/badge/作者-zuihou-orange.svg)](https://github.com/zuihou)
[![Version](https://img.shields.io/badge/版本-3.10.0-brightgreen.svg)](https://github.com/dromara/lamp-cloud)
[![Star](https://img.shields.io/github/stars/dromara/lamp-cloud?color=42b883&logo=github&style=flat-square)](https://github.com/dromara/lamp-cloud/stargazers)
[![Fork](https://img.shields.io/github/forks/dromara/lamp-cloud?color=42b883&logo=github&style=flat-square)](https://github.com/dromara/lamp-cloud/network/members)
[![Star](https://gitee.com/dromara/lamp-cloud/badge/star.svg?theme=gray)](https://gitee.com/dromara/lamp-cloud/stargazers)
[![Fork](https://gitee.com/dromara/lamp-cloud/badge/fork.svg?theme=gray)](https://gitee.com/dromara/lamp-cloud/members)

# 官网
[https://tangyh.top](https://tangyh.top)

# 说明
本项目开源版本已于2022年11月正式加入 [Dromara 开源社区](https://dromara.org/)，因此项目地址发生了变更，原来 `git clone` 到本地的代码，需要自行修改`.git/config`中的项目地址，或者直接重新 git clone 代码，以免影响代码的更新。

> 开源版完全免费开源，没有任何限制，请放心使用。企业版是为了让作者能更有动力、资金来维护开源版以及服务器等。

# 分支说明

- master：稳定版；功能稳定，bug少
    - jdk 8
    - spring cloud 2021.0.5
    - spring cloud alibaba 2021.0.4.0
    - nacos.version 2.1.2
    - spring boot 2.7.6
- java17: 激进版；技术栈最新，可能存在未知bug
    - jdk 17
    - spring cloud 2022.0.0
    - spring cloud alibaba 2022.0.0.0-RC1
    - nacos.version 2.2.0
    - spring boot 3.0.0

# lamp 项目名字由来
在一个夜黑风高的晚上，小孩吵着要出去玩，于是和`程序员老婆`一起带小孩出去放风，路上顺便讨论起项目要换个什么名字，在各自想出的名字都被对方一一否决后，大家陷入了沉思。 走着走着，在一盏路灯下，小孩指着前方的路灯打破宁静，喊出：灯灯～
我和媳妇相视一笑，然后一起说：哈哈，就用这个名字！！！

[点我了解项目详细介绍](https://tangyh.top)

`灯灯`(简称灯， 英文名：lamp)，她是一个项目集，由"工具集"、"后端"、"前端"组成，为满足高内聚低耦合设计原则，将一个**大项目**拆解为以下几个子项目

## 工具集

| 项目 | gitee | github | 备注 |
| --- | --- | --- | --- |
| lamp-util | [lamp-util](https://gitee.com/zuihou111/lamp-util) | [lamp-util](https://github.com/zuihou/lamp-util) | 核心工具集 |
| lamp-generator | [lamp-generator](https://gitee.com/zuihou111/lamp-generator) | [lamp-generator](https://github.com/zuihou/lamp-generator) | 代码生成器 |
| lamp-job | [lamp-job](https://gitee.com/zuihou111/lamp-job) | [lamp-job](https://github.com/zuihou/lamp-job) | 分布式定时调度器 |

## 后端

| 项目 | gitee | github | 备注 |
| --- | --- | --- | --- |
| lamp-cloud | [lamp-cloud](https://gitee.com/dromara/lamp-cloud) |  [lamp-cloud](https://github.com/dromara/lamp-cloud) | SpringCloud(微服务)版 |
| lamp-boot | [lamp-boot](https://gitee.com/zuihou111/lamp-boot) |  [lamp-boot](https://github.com/zuihou/lamp-boot) | SpringBoot(单体)版 |
| 微服务版示例 | [lamp-samples](https://github.com/zuihou/lamp-samples) | [lamp-samples](https://github.com/zuihou/lamp-samples) | 常用示例 |

## 前端

| 项目 | gitee | github | 备注 | 演示地址 |
| --- | --- | --- | --- | --- |
| lamp-web-plus(强烈推荐！👏👏👏) | [lamp-web-plus](https://gitee.com/zuihou111/lamp-web-plus) | [lamp-web-plus](https://github.com/zuihou/lamp-web-plus) | 基于 vue-vben-admin （vue 3 + ant design vue 2） | https://pro.tangyh.top |
| lamp-web | [lamp-web](https://gitee.com/zuihou111/lamp-web) | [lamp-web](https://github.com/zuihou/lamp-web) | 基于 vue-admin-element (element-ui) | https://pro.tangyh.top/lamp-web |

## 注意：
- lamp-cloud 和 lamp-boot 依赖 lamp-util
- lamp-job 依赖 lamp-cloud 或 lamp-boot
- lamp-generator 依赖 lamp-cloud 或 lamp-boot

所以，你想运行 lamp-cloud 和 lamp-boot ，请先自行下载并编译(mvn install) lamp-util， lamp-job、lamp-generator同理！

# lamp-cloud 简介
`lamp-cloud`是基于`jdk11/jdk8` + `SpringCloudAlibaba` +  `SpringCloud` + `SpringBoot` 开发的微服务中后台快速开发平台，专注于多租户(SaaS架构)解决方案，亦可作为普通项目（非SaaS架构）的基础开发框架使用，目前已实现插拔式 **数据库隔离**、**SCHEMA隔离**、**字段隔离** 等租户隔离方案。

她拥有自研RBAC（基于租户应用的角色权限控制体系）、网关统一鉴权、数据权限、优雅缓存解决方案、防缓存击穿、前后端统一表单校验、字典数据自动回显、可视化前后端代码生成器、支持多种文件存储、支持多种短信邮件发送接口、灰度发布、防XSS攻击、防SQL注入、分布式事务、分布式定时任务等功能； 支持多业务系统并行开发， 支持多服务并行开发，是中后台系统开发脚手架的最佳选择。

lamp-cloud 代码简洁，注释齐全，架构清晰，非常适合个人学习以及中小企业作为基础框架使用。采用Spring Cloud Alibaba、SpringBoot、Mybatis、Seata、Sentinel、RabbitMQ、FastDFS/MinIO、SkyWalking等主要框架和中间件。 本项目旨在实现基础框架能力，不涉及具体业务。

![架构图.png](01-docs/image/架构图/lamp-cloud架构图.png)

# 演示地址
- 官网：[https://tangyh.top](https://tangyh.top)
- 4.0版本：后端使用lamp-cloud-pro-datasource-column， 前端使用lamp-web-pro。演示地址：   [https://datasource.tangyh.top](https://datasource.tangyh.top)
- 4.0版本：后端使用lamp-cloud-pro-column， 前端使用lamp-web-pro。演示地址：   [https://column.tangyh.top](https://column.tangyh.top)
- 4.0版本：后端使用lamp-cloud-pro-none， 前端使用lamp-web-pro。演示地址：   [https://none.tangyh.top](https://none.tangyh.top)
- 3.x版本：后端使用lamp-boot-plus， 前端使用lamp-web-plus。演示地址：  [https://boot.tangyh.top](https://boot.tangyh.top)
- 3.x版本：后端使用lamp-boot-plus， 前端使用lamp-web。演示地址：     [https://boot.tangyh.top/lamp-web](https://boot.tangyh.top/lamp-web)

> 4.0 企业版源码已经发布，开源版和个人版(4.0功能可能有所不同)发布暂缓，详情咨询作者

# 快速上手
- 入门到精通，参考 [在线文档](https://www.kancloud.cn/zuihou/zuihou-admin-cloud)
- 发现bug和建议，请提交 [issue](https://github.com/dromara/lamp-cloud/issues)
- 常见问题，请参考 [Discussions](https://github.com/dromara/lamp-cloud/discussions)

# 作者参与过或PR过的项目
- [vue-vben-admin](https://github.com/vbenjs/vue-vben-admin)
- [knife4j](https://gitee.com/xiaoym/knife4j)
- [x-easypdf](https://gitee.com/dromara/x-easypdf)
- [J2Cache](https://gitee.com/ld/J2Cache)

# 项目截图：

| 预览 | 预览 |
|---|---|
| ![预览.png](01-docs/image/架构图/lamp-cloud架构图.png) | ![预览.png](01-docs/image/业务/swagger.png) |
| ![预览.png](01-docs/image/业务/nacos.jpg) | ![预览.png](01-docs/image/业务/工作流.png) |
| ![预览.png](01-docs/image/业务/项目预览1.png) | ![预览.png](01-docs/image/业务/项目预览2.png) |
| ![预览.png](01-docs/image/监控/sba1.png) | ![预览.png](01-docs/image/监控/sba2.png) |
| ![预览.png](01-docs/image/监控/sw拓扑图.png) | ![预览.png](01-docs/image/监控/sw追踪列表.png)  |
| ![预览.png](01-docs/image/1000star.png) | ![预览.png](01-docs/image/软著V2.5.0.jpg) |

# 推广
- 【阿里云】超品周津贴: [点我进入](https://www.aliyun.com/minisite/goods?taskPkg=1212cpz&pkgSid=183200&userCode=uk5ga6sq)
- 【阿里云】服务器折扣场：[点我进入](https://www.aliyun.com/minisite/goods?userCode=uk5ga6sq)
- 【阿里云】服务器优惠券：[点我领取](https://www.aliyun.com/daily-act/ecs/activity_selection?userCode=uk5ga6sq)
- 【腾讯云】境外1核2G服务器低至2折，半价续费券限量免费领取！：[点我进入](https://cloud.tencent.com/act/cps/redirect?redirect=1068&cps_key=970c3dc91a95510c5a474f54eac73ac7&from=console)
- 【腾讯云】DNSPod解析套餐全面升配降价，更高的套餐配置规格，更优的价格方案，全面提升可用性及响应率，专业版限时99元/年！[点我进入](https://cloud.tencent.com/act/cps/redirect?redirect=1542&cps_key=970c3dc91a95510c5a474f54eac73ac7&from=console)
- 通过以上链接任意购买金额大于等于50元的商品，联系作者可赠送 [看云文档](https://www.kancloud.cn/zuihou/zuihou-admin-cloud) 一份。

#  Dromara 成员项目

<p align="center">
<a href="https://gitee.com/dromara/TLog" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/tlog2.png" title="一个轻量级的分布式日志标记追踪神器，10分钟即可接入，自动对日志打标签完成微服务的链路追踪" width="15%">
</a>
<a href="https://gitee.com/dromara/liteFlow" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/liteflow.png" title="轻量，快速，稳定，可编排的组件式流程引擎" width="15%">
</a>
<a href="https://hutool.cn/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/hutool.jpg" title="小而全的Java工具类库，使Java拥有函数式语言般的优雅，让Java语言也可以“甜甜的”。" width="15%">
</a>
<a href="https://sa-token.dev33.cn/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/sa-token.png" title="一个轻量级 java 权限认证框架，让鉴权变得简单、优雅！" width="15%">
</a>
<a href="https://gitee.com/dromara/hmily" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/hmily.png" title="高性能一站式分布式事务解决方案。" width="15%">
</a>
<a href="https://gitee.com/dromara/Raincat" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/raincat.png" title="强一致性分布式事务解决方案。" width="15%">
</a>
</p>
<p align="center">
<a href="https://gitee.com/dromara/myth" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/myth.png" title="可靠消息分布式事务解决方案。" width="15%">
</a>
<a href="https://cubic.jiagoujishu.com/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/cubic.png" title="一站式问题定位平台，以agent的方式无侵入接入应用，完整集成arthas功能模块，致力于应用级监控，帮助开发人员快速定位问题" width="15%">
</a>
<a href="https://maxkey.top/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/maxkey.png" title="业界领先的身份管理和认证产品" width="15%">
</a>
<a href="http://forest.dtflyx.com/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/forest-logo.png" title="Forest能够帮助您使用更简单的方式编写Java的HTTP客户端" width="15%">
</a>
<a href="https://jpom.io/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/jpom.png" title="一款简而轻的低侵入式在线构建、自动部署、日常运维、项目监控软件" width="15%">
</a>
<a href="https://su.usthe.com/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/sureness.png" title="面向 REST API 的高性能认证鉴权框架" width="15%">
</a>
</p>
<p align="center">
<a href="https://easy-es.cn/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/easy-es2.png" title="傻瓜级ElasticSearch搜索引擎ORM框架" width="15%">
</a>
<a href="https://gitee.com/dromara/northstar" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/northstar_logo.png" title="Northstar盈富量化交易平台" width="15%">
</a>
<a href="https://hertzbeat.com/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/hertzbeat_brand.jpg" title="易用友好的云监控系统" width="15%">
</a>
<a href="https://plugins.sheng90.wang/fast-request/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/fast-request.gif" title="Idea 版 Postman，为简化调试API而生" width="15%">
</a>
<a href="https://www.jeesuite.com/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/mendmix.png" title="开源分布式云原生架构一站式解决方案" width="15%">
</a>
<a href="https://gitee.com/dromara/koalas-rpc" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/koalas-rpc2.png" title="企业生产级百亿日PV高可用可拓展的RPC框架。" width="15%">
</a>
</p>
<p align="center">
<a href="https://async.sizegang.cn/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/gobrs-async.png" title="配置极简功能强大的异步任务动态编排框架" width="15%">
</a>
<a href="https://dynamictp.cn/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/dynamic-tp.png" title="基于配置中心的轻量级动态可监控线程池" width="15%">
</a>
<a href="https://www.x-easypdf.cn" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/x-easypdf.png" title="一个用搭积木的方式构建pdf的框架（基于pdfbox）" width="15%">
</a>
<a href="http://dromara.gitee.io/image-combiner" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/image-combiner.png" title="一个专门用于图片合成的工具，没有很复杂的功能，简单实用，却不失强大" width="15%">
</a>
<a href="https://www.herodotus.cn/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/dante-cloud2.png" title="Dante-Cloud 是一款企业级微服务架构和服务能力开发平台。" width="15%">
</a>
<a href="https://dromara.org/zh/projects/" target="_blank">
<img src="https://oss.dev33.cn/sa-token/link/dromara.png" title="让每一位开源爱好者，体会到开源的快乐。" width="15%">
</a>
</p>
<p align="center">
为往圣继绝学，一个人或许能走的更快，但一群人会走的更远。
</p>


# 企业版
本项目分为开源版、个人学习版和企业商用版，github和gitee上能搜索到的为开源版本，遵循Apache协议。 个人和企业版源码在私有gitlab托管，购买后开通账号。

区别请看：[会员版](https://tangyh.top/vip/%E6%8E%88%E6%9D%83%E8%B4%B9%E7%94%A8)

# 开源协议
Apache Licence 2.0 Licence是著名的非盈利开源组织Apache采用的协议。该协议和BSD类似，同样鼓励代码共享和尊重原作者的著作权，同样允许代码修改，再发布（作为开源或商业软件）。 需要满足的条件如下：

- 需要给代码的用户一份Apache Licence
- 如果你修改了代码，需要在被修改的文件中说明。
- 在延伸的代码中（修改和有源代码衍生的代码中）需要带有原来代码中的协议，商标，专利声明和其他原来作者规定需要包含的说明。
- 如果再发布的产品中包含一个Notice文件，则在Notice文件中需要带有Apache Licence。你可以在Notice中增加自己的许可，但不可以表现为对Apache Licence构成更改。 Apache Licence也是对商业应用友好的许可。使用者也可以在需要的时候修改代码来满足需要并作为开源或商业产品发布/销售。
- 若你借鉴或学习了本项目的源码，请你在你的项目源码和说明文档中显著的表明引用于本项目，并附上本项目的github访问地址。（https://github.com/dromara/lamp-cloud）
