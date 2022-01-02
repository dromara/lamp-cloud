# lamp 快速开发平台

[![Language](https://img.shields.io/badge/语言-Java%20%7C%20SpringCloud%20%7C%20Vue3%20%7C%20...-red?style=flat-square&color=42b883)](https://github.com/zuihou/lamp-cloud)
[![License](https://img.shields.io/github/license/zuihou/lamp-cloud?color=42b883&style=flat-square)](https://github.com/zuihou/lamp-cloud/blob/master/LICENSE)
[![Author](https://img.shields.io/badge/作者-zuihou-orange.svg)](https://github.com/zuihou)
[![Version](https://img.shields.io/badge/版本-3.5.7-brightgreen.svg)](https://github.com/zuihou/lamp-cloud)
[![Star](https://img.shields.io/github/stars/zuihou/lamp-cloud?color=42b883&logo=github&style=flat-square)](https://github.com/zuihou/lamp-cloud/stargazers)
[![Fork](https://img.shields.io/github/forks/zuihou/lamp-cloud?color=42b883&logo=github&style=flat-square)](https://github.com/zuihou/lamp-cloud/network/members)
[![Star](https://gitee.com/zuihou111/lamp-cloud/badge/star.svg?theme=gray)](https://gitee.com/zuihou111/lamp-cloud/stargazers)
[![Fork](https://gitee.com/zuihou111/lamp-cloud/badge/fork.svg?theme=gray)](https://gitee.com/zuihou111/lamp-cloud/members)

# 推广
- 【阿里云】超品周津贴: [点我进入](https://www.aliyun.com/minisite/goods?taskPkg=1212cpz&pkgSid=183200&userCode=uk5ga6sq)
- 【阿里云】服务器折扣场：[点我进入](https://www.aliyun.com/minisite/goods?userCode=uk5ga6sq)
- 【阿里云】服务器优惠券：[点我领取](https://www.aliyun.com/daily-act/ecs/activity_selection?userCode=uk5ga6sq)
- 【腾讯云】境外1核2G服务器低至2折，半价续费券限量免费领取！：[点我进入](https://cloud.tencent.com/act/cps/redirect?redirect=1068&cps_key=970c3dc91a95510c5a474f54eac73ac7&from=console)
- 【腾讯云】DNSPod解析套餐全面升配降价，更高的套餐配置规格，更优的价格方案，全面提升可用性及响应率，专业版限时99元/年！[点我进入](https://cloud.tencent.com/act/cps/redirect?redirect=1542&cps_key=970c3dc91a95510c5a474f54eac73ac7&from=console)
- 通过以上链接任意购买金额大于等于50元的商品，联系作者可赠送 [看云文档](https://www.kancloud.cn/zuihou/zuihou-admin-cloud) 一份。

# 官网
[https://tangyh.top](https://tangyh.top)

# lamp 项目名字由来
在一个夜黑风高的晚上，小孩吵着要出去玩，于是和`程序员老婆`一起带小孩出去放风，路上顺便讨论起项目要换个什么名字，在各自想出的名字都被对方一一否决后，大家陷入了沉思。 走着走着，在一盏路灯下，小孩指着前方的路灯打破宁静，喊出：灯灯～
我和媳妇相视一笑，然后一起说：哈哈，就用这个名字！！！

[点我了解项目详细介绍](https://tangyh.top)

`灯灯`(简称灯， 英文名：lamp)，他是一个项目的统称，由"工具集"、"后端"、"前端"组成，包含以下几个子项目

## 工具集

| 项目 | gitee | github | 备注 |
| --- | --- | --- | --- |
| lamp-util | [lamp-util](https://gitee.com/zuihou111/lamp-util) | [lamp-util](https://github.com/zuihou/lamp-util) | 核心工具集 |
| lamp-generator | [lamp-generator](https://gitee.com/zuihou111/lamp-generator) | [lamp-generator](https://github.com/zuihou/lamp-generator) | 代码生成器 |
| lamp-job | [lamp-job](https://gitee.com/zuihou111/lamp-job) | [lamp-job](https://github.com/zuihou/lamp-job) | 分布式定时调度器 |

## 后端

| 项目 | gitee | github | 备注 |
| --- | --- | --- | --- |
| lamp-cloud | [lamp-cloud](https://gitee.com/zuihou111/lamp-cloud) |  [lamp-cloud](https://github.com/zuihou/lamp-cloud) | SpringCloud(微服务)版 |
| lamp-boot | [lamp-boot](https://gitee.com/zuihou111/lamp-boot) |  [lamp-boot](https://github.com/zuihou/lamp-boot) | SpringBoot(单体)版 |
| 微服务版示例 | [lamp-samples](https://github.com/zuihou/lamp-samples) | [lamp-samples](https://github.com/zuihou/lamp-samples) | 常用示例 |

## 前端

| 项目 | gitee | github | 备注 | 演示地址 |
| --- | --- | --- | --- | --- |
| lamp-web-plus(强烈推荐！👏👏👏) | [lamp-web-plus](https://gitee.com/zuihou111/lamp-web-plus) | [lamp-web-plus](https://github.com/zuihou/lamp-web-plus) | 基于 vue-vben-admin （vue 3 + ant design vue 2） | https://pro.tangyh.top |
| lamp-web | [lamp-web](https://gitee.com/zuihou111/lamp-web) | [lamp-web](https://github.com/zuihou/lamp-web) | 基于 vue-admin-element (element-ui) | https://pro.tangyh.top/lamp-web |

# lamp-cloud 简介

`lamp-cloud`只是`lamp`项目的其中一个项目，她基于`jdk11/jdk8` + `SpringCloud`  + `SpringBoot` 开发，
她是一个微服务中后台快速开发平台，可以通过插件无缝切换是否启用SaaS模式、通过配置切换SaaS模式采用独立数据库模式还是字段模式。

她具备SaaS模式切换、完备的RBAC功能、网关统一鉴权、灰度发布、数据权限、可插拔缓存、统一封装缓存的key、表单校验前后端统一验证、字典数据自动回显、Xss防跨站攻击、自动生成前后端代码、多种存储系统、分布式事务、分布式定时任务等多个功能和模块，
支持多业务系统并行开发， 支持多服务并行开发，是中后台系统开发脚手架的最佳选择。代码简洁，注释齐全，架构清晰，非常适合学习和企业作为基础框架使用。

核心技术采用Spring Cloud Alibaba、SpringBoot、Mybatis、Seata、Sentinel、RabbitMQ、FastDFS/MinIO、SkyWalking等主要框架和中间件。
希望能努力打造一套从 `Web基础框架` - `分布式微服务架构` - `持续集成` - `系统监测` 的解决方案。本项目旨在实现基础框架能力，不涉及具体业务。

# 会员版演示地址
- 官网：[https://tangyh.top](https://tangyh.top)
- 4.0版本：后端使用lamp-cloud-pro， 前端使用lamp-web-pro。演示地址：   [https://pro.tangyh.top](https://pro.tangyh.top)
- 3.x版本：后端使用lamp-boot-plus， 前端使用lamp-web-plus。演示地址：  [https://boot.tangyh.top](https://boot.tangyh.top)
- 3.x版本：后端使用lamp-boot-plus， 前端使用lamp-web。演示地址：     [https://boot.tangyh.top/lamp-web](https://boot.tangyh.top/lamp-web)

> 4.0 企业版源码已经发布，开源版和个人版(4.0功能可能有所不同)发布暂缓，详情咨询作者


# 快速上手
- 入门到精通，参考 [在线文档](https://www.kancloud.cn/zuihou/zuihou-admin-cloud)
- 发现bug和建议，请提交 [issue](https://github.com/zuihou/lamp-cloud/issues)
- 常见问题，请参考 [Discussions](https://github.com/zuihou/lamp-cloud/discussions)

# 项目截图：

| 预览 | 预览 |
|---|---|
| ![预览.png](01-docs/image/架构图/lamp-cloud架构图.png) | ![预览.png](01-docs/image/业务/swagger.png) |
| ![预览.png](01-docs/image/业务/nacos.jpg) | ![预览.png](01-docs/image/业务/工作流.png) |
| ![预览.png](01-docs/image/业务/项目预览1.png) | ![预览.png](01-docs/image/业务/项目预览2.png) |
| ![预览.png](01-docs/image/监控/sba1.png) | ![预览.png](01-docs/image/监控/sba2.png) |
| ![预览.png](01-docs/image/监控/sw拓扑图.png) | ![预览.png](01-docs/image/监控/sw追踪列表.png)  |
| ![预览.png](01-docs/image/1000star.png) | ![预览.png](01-docs/image/软著V2.5.0.jpg) |

# 会员版
本项目分为开源版、个人学习版和企业商用版，github和gitee上能搜索到的为开源版本，遵循Apache协议。 个人和企业版源码在私有gitlab托管，购买后开通账号。

区别请看：[会员版](https://tangyh.top/pages/vip/)

# 开源协议
Apache Licence 2.0 Licence是著名的非盈利开源组织Apache采用的协议。该协议和BSD类似，同样鼓励代码共享和尊重原作者的著作权，同样允许代码修改，再发布（作为开源或商业软件）。 需要满足的条件如下：

- 需要给代码的用户一份Apache Licence
- 如果你修改了代码，需要在被修改的文件中说明。
- 在延伸的代码中（修改和有源代码衍生的代码中）需要带有原来代码中的协议，商标，专利声明和其他原来作者规定需要包含的说明。
- 如果再发布的产品中包含一个Notice文件，则在Notice文件中需要带有Apache Licence。你可以在Notice中增加自己的许可，但不可以表现为对Apache Licence构成更改。 Apache Licence也是对商业应用友好的许可。使用者也可以在需要的时候修改代码来满足需要并作为开源或商业产品发布/销售。
- 若你借鉴或学习了本项目的源码，请你在你的项目源码和说明文档中显著的表明引用于本项目，并附上本项目的github访问地址。（https://github.com/zuihou/lamp-cloud）
