SET NAMES utf8mb4;
-- ----------------------------
-- Table structure for e_msg
-- ----------------------------
DROP TABLE IF EXISTS `e_msg`;
CREATE TABLE `e_msg` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `biz_id` varchar(64) DEFAULT '' COMMENT '业务ID',
  `biz_type` varchar(64) DEFAULT '' COMMENT '业务类型 \n#MsgBizType{USER_LOCK:账号锁定;USER_REG:账号申请;WORK_APPROVAL:考勤审批;}',
  `msg_type` varchar(20) NOT NULL COMMENT '消息类型 \n#MsgType{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `content` text COMMENT '内容',
  `author` varchar(50) DEFAULT '' COMMENT '发布人',
  `handler_url` varchar(255) DEFAULT '' COMMENT '处理地址 \n以http开头时直接跳转，否则与#c_application表拼接后跳转http可带参数',
  `handler_params` varchar(500) DEFAULT '' COMMENT '处理参数',
  `is_single_handle` bit(1) DEFAULT b'1' COMMENT '是否单人处理',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息表';

-- ----------------------------
-- Table structure for e_msg_receive
-- ----------------------------
DROP TABLE IF EXISTS `e_msg_receive`;
CREATE TABLE `e_msg_receive` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `msg_id` bigint(20) NOT NULL COMMENT '消息ID \n#msg',
  `user_id` bigint(20) NOT NULL COMMENT '接收人ID \n#c_user',
  `is_read` bit(1) DEFAULT b'0' COMMENT '是否已读',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息接收表';


-- ----------------------------
-- Table structure for e_sms_send_status
-- ----------------------------
DROP TABLE IF EXISTS `e_sms_send_status`;
CREATE TABLE `e_sms_send_status` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `task_id` bigint(20) NOT NULL COMMENT '任务ID \n#e_sms_task',
  `send_status` varchar(10) NOT NULL COMMENT '发送状态 \n#SendStatus{WAITING:等待发送;SUCCESS:发送成功;FAIL:发送失败}',
  `receiver` varchar(20) NOT NULL COMMENT '接收者手机号\n单个手机号 \n阿里：发送回执ID,可根据该ID查询具体的发送状态  腾讯：sid 标识本次发送id，标识一次短信下发记录  百度：requestId 短信发送请求唯一流水ID',
  `biz_id` varchar(255) DEFAULT '' COMMENT '发送回执ID',
  `ext` varchar(255) DEFAULT '' COMMENT '发送返回 \n阿里：RequestId 请求ID  腾讯：ext：用户的session内容，腾讯server回包中会原样返回   百度：无',
  `code` varchar(255) DEFAULT '' COMMENT '状态码 \n阿里：返回OK代表请求成功,其他错误码详见错误码列表  腾讯：0表示成功(计费依据)，非0表示失败  百度：1000 表示成功',
  `message` varchar(500) DEFAULT '' COMMENT '状态码的描述',
  `fee` int(10) DEFAULT NULL COMMENT '短信计费的条数\n腾讯专用',
  `create_month` char(7) DEFAULT '' COMMENT '创建时年月 \n格式：yyyy-MM 用于统计',
  `create_week` char(7) DEFAULT '' COMMENT '创建时年周 \n创建时处于当年的第几周 yyyy-ww 用于统计',
  `create_date` char(10) DEFAULT '' COMMENT '创建时年月日 \n格式： yyyy-MM-dd 用于统计',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信发送状态';

-- ----------------------------
-- Table structure for e_sms_task
-- ----------------------------
DROP TABLE IF EXISTS `e_sms_task`;
CREATE TABLE `e_sms_task` (
  `id` bigint(20) NOT NULL COMMENT '短信记录ID',
  `template_id` bigint(20) NOT NULL COMMENT '模板ID \n#e_sms_template',
  `status` varchar(10) DEFAULT '' COMMENT '执行状态 \n(手机号具体发送状态看sms_send_status表) \n#TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}',
  `source_type` varchar(10) DEFAULT '' COMMENT '来源类型 \n#SourceType{APP:应用;SERVICE:服务}',
  `receiver` text COMMENT '接收者手机号 \n群发用英文逗号分割.\n支持2种 格式:1: 手机号,手机号  格式2: 姓名<手机号>,姓名<手机号>',
  `topic` varchar(255) DEFAULT '' COMMENT '主题',
  `template_params` varchar(500) DEFAULT '' COMMENT '参数 \n需要封装为{‘key’:’value’, ...}格式且key必须有序',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `content` varchar(500) DEFAULT '' COMMENT '发送内容 \n需要封装正确格式化: 您好，张三，您有一个新的快递。',
  `draft` bit(1) DEFAULT b'0' COMMENT '是否草稿',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发送任务';

-- ----------------------------
-- Table structure for e_sms_template
-- ----------------------------
DROP TABLE IF EXISTS `e_sms_template`;
CREATE TABLE `e_sms_template` (
  `id` bigint(20) NOT NULL COMMENT '模板ID',
  `provider_type` varchar(10) NOT NULL DEFAULT '' COMMENT '供应商类型 \n#ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}',
  `app_id` varchar(255) NOT NULL DEFAULT '' COMMENT '应用ID',
  `app_secret` varchar(255) NOT NULL DEFAULT '' COMMENT '应用密码',
  `url` varchar(255) DEFAULT '' COMMENT 'SMS服务域名 \n百度、其他厂商会用',
  `custom_code` varchar(20) NOT NULL DEFAULT '' COMMENT '模板编码\n用于api发送',
  `name` varchar(255) DEFAULT '' COMMENT '模板名称',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '模板内容',
  `template_params` varchar(255) NOT NULL DEFAULT '' COMMENT '模板参数',
  `template_code` varchar(50) NOT NULL DEFAULT '' COMMENT '模板CODE',
  `sign_name` varchar(100) DEFAULT '' COMMENT '签名',
  `template_describe` varchar(255) DEFAULT '' COMMENT '备注',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信模板';

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT 'increment id',
    `branch_id`     BIGINT(20)   NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(100) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='AT transaction mode undo table';
