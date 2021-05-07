# lamp 快速开发平台

[![AUR](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://github.com/zuihou/lamp-cloud/blob/master/LICENSE)
[![](https://img.shields.io/badge/作者-zuihou-orange.svg)](https://github.com/zuihou)
[![](https://img.shields.io/badge/版本-3.2.0-brightgreen.svg)](https://github.com/zuihou/lamp-cloud)
[![GitHub stars](https://img.shields.io/github/stars/zuihou/lamp-cloud.svg?style=social&label=Stars)](https://github.com/zuihou/lamp-cloud/stargazers)
[![star](https://gitee.com/zuihou111/lamp-cloud/badge/star.svg?theme=white)](https://gitee.com/zuihou111/lamp-cloud/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/zuihou/lamp-cloud.svg?style=social&label=Fork)](https://github.com/zuihou/lamp-cloud/network/members)
[![fork](https://gitee.com/zuihou111/lamp-cloud/badge/fork.svg?theme=white)](https://gitee.com/zuihou111/lamp-cloud/members)

# lamp 项目名字由来
`灯灯`(简称灯， 英文名：lamp)，他是一个项目的统称，由"工具集"、"后端"、"前端"组成，包含以下几个子项目

[点我了解项目详细介绍](简介.md)

## 工具集

| 项目 | gitee | github | 备注 |
| --- | --- | --- | --- |
| lamp-util | https://gitee.com/zuihou111/lamp-util | https://github.com/zuihou/lamp-util | 核心工具集 |
| lamp-generator | https://gitee.com/zuihou111/lamp-generator | https://github.com/zuihou/lamp-generator | 代码生成器 |
| lamp-job | https://gitee.com/zuihou111/lamp-job | https://github.com/zuihou/lamp-job | 分布式定时调度器 |

## 后端

| 项目 | gitee | github | 备注 |
| --- | --- | --- | --- |
| lamp-cloud | https://gitee.com/zuihou111/lamp-cloud | https://github.com/zuihou/lamp-cloud | SpringCloud(微服务)版 |
| lamp-boot | https://gitee.com/zuihou111/lamp-boot | https://github.com/zuihou/lamp-boot | SpringBoot(单体)版 |

## 前端

| 项目 | gitee | github | 备注 | 演示地址 |
| --- | --- | --- | --- | --- |
| lamp-web | https://gitee.com/zuihou111/lamp-web | https://github.com/zuihou/lamp-web | 基于 vue-admin-element (element-ui) | http://tangyh.top:10000 |
| lamp-web-plus(强烈推荐！👏👏👏) | https://gitee.com/zuihou111/lamp-web-plus | https://github.com/zuihou/lamp-web-plus | 基于 vue-vben-admin （vue 3 + ant design vue 2） | http://tangyh.top:3100 |
| lamp-web-beautiful(暂时停更) | https://gitee.com/zuihou111/lamp-web-beautiful | https://github.com/zuihou/lamp-web-beautiful | 基于 vue-admin-beautiful | http://tangyh.top:180 |

# lamp-cloud 简介

`lamp-cloud`只是`lamp`项目的其中一个项目，她基于`jdk11/jdk8` + `SpringCloud(Hoxton.SR10)`  + `SpringBoot(2.3.10.RELEASE)` 开发，
她是一个微服务中后台快速开发平台，可以通过插件无缝切换是否启用SaaS模式、通过配置切换SaaS模式采用独立数据库模式还是字段模式。

她具备SaaS模式切换、完备的RBAC功能、网关统一鉴权、灰度发布、数据权限、可插拔缓存、统一封装缓存的key、表单校验前后端统一验证、字典数据自动回显、Xss防跨站攻击、自动生成前后端代码、多种存储系统、分布式事务、分布式定时任务等多个功能和模块，
支持多业务系统并行开发， 支持多服务并行开发，是中后台系统开发脚手架的最佳选择。代码简洁，注释齐全，架构清晰，非常适合学习和企业作为基础框架使用。

核心技术采用Spring Cloud Alibaba、SpringBoot、Mybatis、Seata、Sentinel、RabbitMQ、FastDFS/MinIO、SkyWalking等主要框架和中间件。
希望能努力打造一套从 `Web基础框架` - `分布式微服务架构` - `持续集成` - `系统监测` 的解决方案。本项目旨在实现基础框架能力，不涉及具体业务。

# lamp 会员版项目演示地址
- lamp-web-plus演示地址： http://tangyh.top:3100
- lamp-web演示地址： http://tangyh.top:10000/lamp-web
- lamp-web-beautiful演示地址： http://tangyh.top:180
- 以下内置账号仅限于内置的0000租户
- 平台管理员： lamp_pt/lamp (内置给公司内部运营人员使用)
- 超级管理员： lamp/lamp
- 普通管理员： general/lamp
- 普通账号： normal/lamp

# 项目截图：

| 预览 | 预览 |
|---|---|
| ![预览.png](01-docs/image/架构图/lamp-cloud架构图.png) | ![预览.png](01-docs/image/业务/swagger.png) |
| ![预览.png](01-docs/image/业务/nacos.jpg) | ![预览.png](01-docs/image/业务/工作流.png) |
| ![预览.png](01-docs/image/业务/项目预览1.png) | ![预览.png](01-docs/image/业务/项目预览2.png) |
| ![预览.png](01-docs/image/监控/sba1.png) | ![预览.png](01-docs/image/监控/sba2.png) |
| ![预览.png](01-docs/image/监控/zipkin1_2_19_2.png) | ![预览.png](01-docs/image/监控/zipkin3_2_19_2.png) |
| ![预览.png](01-docs/image/监控/sw拓扑图.png) | ![预览.png](01-docs/image/监控/sw追踪列表.png)  |
| ![预览.png](01-docs/image/1000star.png) | ![预览.png](01-docs/image/软著V2.5.0.jpg) |

# 会员版
本项目分为开源版和会员版，github和gitee上能搜索到的为开源版本，遵循Apache协议。 会员版源码在私有gitlab托管，购买后开通账号。

会员版和开源版区别请看：[会员版](会员版.md)

