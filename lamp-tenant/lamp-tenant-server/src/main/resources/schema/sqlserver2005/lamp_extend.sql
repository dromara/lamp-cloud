IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[e_block_list]') AND type in (N'U')) DROP TABLE [dbo].[e_block_list];
CREATE TABLE [dbo].[e_block_list](
                                     id BIGINT NOT NULL,
                                     ip VARCHAR(20),
    request_uri VARCHAR(255),
    request_method VARCHAR(10),
    limit_start VARCHAR(8),
    limit_end VARCHAR(8),
    state BIT DEFAULT  0,
    create_time DATETIME,
    created_by BIGINT,
    update_time DATETIME,
    updated_by BIGINT,
    PRIMARY KEY (id)
    );

EXEC sp_addextendedproperty 'MS_Description', '黑名单', 'SCHEMA', dbo, 'table', e_block_list, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', e_block_list, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '阻止访问ip', 'SCHEMA', dbo, 'table', e_block_list, 'column', ip;
EXEC sp_addextendedproperty 'MS_Description', '请求URI', 'SCHEMA', dbo, 'table', e_block_list, 'column', request_uri;
EXEC sp_addextendedproperty 'MS_Description', '请求方法;
如果为ALL则表示对所有方法生效', 'SCHEMA', dbo, 'table', e_block_list, 'column', request_method;
EXEC sp_addextendedproperty 'MS_Description', '限制时间起', 'SCHEMA', dbo, 'table', e_block_list, 'column', limit_start;
EXEC sp_addextendedproperty 'MS_Description', '限制时间止', 'SCHEMA', dbo, 'table', e_block_list, 'column', limit_end;
EXEC sp_addextendedproperty 'MS_Description', '状态', 'SCHEMA', dbo, 'table', e_block_list, 'column', state;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', e_block_list, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', e_block_list, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', dbo, 'table', e_block_list, 'column', update_time;
EXEC sp_addextendedproperty 'MS_Description', '修改人', 'SCHEMA', dbo, 'table', e_block_list, 'column', updated_by;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[e_msg]') AND type in (N'U')) DROP TABLE [dbo].[e_msg];
CREATE TABLE [dbo].[e_msg](
                              id BIGINT NOT NULL,
                              biz_id VARCHAR(64),
    biz_type VARCHAR(64),
    msg_type VARCHAR(20) NOT NULL,
    title VARCHAR(255),
    content TEXT,
    author VARCHAR(50),
    handler_url VARCHAR(255),
    handler_params VARCHAR(500),
    is_single_handle BIT DEFAULT  1,
    create_time DATETIME,
    created_by BIGINT,
    update_time DATETIME,
    updated_by BIGINT,
    PRIMARY KEY (id)
    );

EXEC sp_addextendedproperty 'MS_Description', '消息表', 'SCHEMA', dbo, 'table', e_msg, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', e_msg, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '业务ID', 'SCHEMA', dbo, 'table', e_msg, 'column', biz_id;
EXEC sp_addextendedproperty 'MS_Description', '业务类型;
#MsgBizType{USER_LOCK:账号锁定;USER_REG:账号申请;WORK_APPROVAL:考勤审批;}', 'SCHEMA', dbo, 'table', e_msg, 'column', biz_type;
EXEC sp_addextendedproperty 'MS_Description', '消息类型;
#MsgType{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}', 'SCHEMA', dbo, 'table', e_msg, 'column', msg_type;
EXEC sp_addextendedproperty 'MS_Description', '标题', 'SCHEMA', dbo, 'table', e_msg, 'column', title;
EXEC sp_addextendedproperty 'MS_Description', '内容', 'SCHEMA', dbo, 'table', e_msg, 'column', content;
EXEC sp_addextendedproperty 'MS_Description', '发布人', 'SCHEMA', dbo, 'table', e_msg, 'column', author;
EXEC sp_addextendedproperty 'MS_Description', '处理地址;
以http开头时直接跳转，否则与#c_application表拼接后跳转http可带参数', 'SCHEMA', dbo, 'table', e_msg, 'column', handler_url;
EXEC sp_addextendedproperty 'MS_Description', '处理参数', 'SCHEMA', dbo, 'table', e_msg, 'column', handler_params;
EXEC sp_addextendedproperty 'MS_Description', '是否单人处理', 'SCHEMA', dbo, 'table', e_msg, 'column', is_single_handle;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', e_msg, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人id', 'SCHEMA', dbo, 'table', e_msg, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '最后修改时间', 'SCHEMA', dbo, 'table', e_msg, 'column', update_time;
EXEC sp_addextendedproperty 'MS_Description', '最后修改人', 'SCHEMA', dbo, 'table', e_msg, 'column', updated_by;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[e_msg_receive]') AND type in (N'U')) DROP TABLE [dbo].[e_msg_receive];
CREATE TABLE [dbo].[e_msg_receive](
                                      id BIGINT NOT NULL,
                                      msg_id BIGINT NOT NULL,
                                      user_id BIGINT NOT NULL,
                                      is_read BIT DEFAULT  0,
                                      create_time DATETIME,
                                      created_by BIGINT,
                                      update_time DATETIME,
                                      updated_by BIGINT,
                                      PRIMARY KEY (id)
    );

EXEC sp_addextendedproperty 'MS_Description', '消息接收表', 'SCHEMA', dbo, 'table', e_msg_receive, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', e_msg_receive, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '消息ID;
#msg', 'SCHEMA', dbo, 'table', e_msg_receive, 'column', msg_id;
EXEC sp_addextendedproperty 'MS_Description', '接收人ID;
#c_user', 'SCHEMA', dbo, 'table', e_msg_receive, 'column', user_id;
EXEC sp_addextendedproperty 'MS_Description', '是否已读', 'SCHEMA', dbo, 'table', e_msg_receive, 'column', is_read;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', e_msg_receive, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', e_msg_receive, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '最后修改时间', 'SCHEMA', dbo, 'table', e_msg_receive, 'column', update_time;
EXEC sp_addextendedproperty 'MS_Description', '最后修改人', 'SCHEMA', dbo, 'table', e_msg_receive, 'column', updated_by;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[e_rate_limiter]') AND type in (N'U')) DROP TABLE [dbo].[e_rate_limiter];
CREATE TABLE [dbo].[e_rate_limiter](
                                       id BIGINT NOT NULL,
                                       count INT DEFAULT  0,
                                       request_uri VARCHAR(255),
    request_method VARCHAR(10),
    limit_start VARCHAR(8),
    limit_end VARCHAR(8),
    state BIT DEFAULT  0,
    interval_sec BIGINT DEFAULT  0,
    create_time DATETIME,
    created_by BIGINT,
    update_time DATETIME,
    updated_by BIGINT,
    PRIMARY KEY (id)
    );

EXEC sp_addextendedproperty 'MS_Description', '限流', 'SCHEMA', dbo, 'table', e_rate_limiter, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', e_rate_limiter, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '次数', 'SCHEMA', dbo, 'table', e_rate_limiter, 'column', count;
EXEC sp_addextendedproperty 'MS_Description', '请求URI', 'SCHEMA', dbo, 'table', e_rate_limiter, 'column', request_uri;
EXEC sp_addextendedproperty 'MS_Description', '请求方法;
如果为ALL则表示对所有方法生效', 'SCHEMA', dbo, 'table', e_rate_limiter, 'column', request_method;
EXEC sp_addextendedproperty 'MS_Description', '限制时间起', 'SCHEMA', dbo, 'table', e_rate_limiter, 'column', limit_start;
EXEC sp_addextendedproperty 'MS_Description', '限制时间止', 'SCHEMA', dbo, 'table', e_rate_limiter, 'column', limit_end;
EXEC sp_addextendedproperty 'MS_Description', '状态', 'SCHEMA', dbo, 'table', e_rate_limiter, 'column', state;
EXEC sp_addextendedproperty 'MS_Description', '时间窗口', 'SCHEMA', dbo, 'table', e_rate_limiter, 'column', interval_sec;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', e_rate_limiter, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', e_rate_limiter, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', dbo, 'table', e_rate_limiter, 'column', update_time;
EXEC sp_addextendedproperty 'MS_Description', '修改人', 'SCHEMA', dbo, 'table', e_rate_limiter, 'column', updated_by;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[e_sms_send_status]') AND type in (N'U')) DROP TABLE [dbo].[e_sms_send_status];
CREATE TABLE [dbo].[e_sms_send_status](
                                          id BIGINT NOT NULL,
                                          task_id BIGINT NOT NULL,
                                          send_status VARCHAR(10) NOT NULL,
    tel_num VARCHAR(20) NOT NULL,
    biz_id VARCHAR(255),
    ext VARCHAR(255),
    code VARCHAR(255),
    message VARCHAR(500),
    fee INT,
    created_by BIGINT,
    create_time DATETIME,
    updated_by BIGINT,
    update_time DATETIME,
    PRIMARY KEY (id)
    );

EXEC sp_addextendedproperty 'MS_Description', '短信发送状态', 'SCHEMA', dbo, 'table', e_sms_send_status, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'ID', 'SCHEMA', dbo, 'table', e_sms_send_status, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '任务ID;
#e_sms_task', 'SCHEMA', dbo, 'table', e_sms_send_status, 'column', task_id;
EXEC sp_addextendedproperty 'MS_Description', '发送状态;
#SendStatus{WAITING:等待发送;SUCCESS:发送成功;FAIL:发送失败}', 'SCHEMA', dbo, 'table', e_sms_send_status, 'column', send_status;
EXEC sp_addextendedproperty 'MS_Description', '接收者手机
单个手机号;
阿里：发送回执ID,可根据该ID查询具体的发送状态  腾讯：sid 标识本次发送id，标识一次短信下发记录  百度：requestId 短信发送请求唯一流水ID', 'SCHEMA', dbo, 'table', e_sms_send_status, 'column', tel_num;
EXEC sp_addextendedproperty 'MS_Description', '发送回执ID', 'SCHEMA', dbo, 'table', e_sms_send_status, 'column', biz_id;
EXEC sp_addextendedproperty 'MS_Description', '发送返回;
阿里：RequestId 请求ID  腾讯：ext：用户的session内容，腾讯server回包中会原样返回   百度：无', 'SCHEMA', dbo, 'table', e_sms_send_status, 'column', ext;
EXEC sp_addextendedproperty 'MS_Description', '状态码;阿里：返回OK代表请求成功,其他错误码详见错误码列表  腾讯：0表示成功(计费依据)，非0表示失败  百度：1000 表示成功', 'SCHEMA', dbo, 'table', e_sms_send_status, 'column', code;
EXEC sp_addextendedproperty 'MS_Description', '状态码的描述', 'SCHEMA', dbo, 'table', e_sms_send_status, 'column', message;
EXEC sp_addextendedproperty 'MS_Description', '短信计费的条数;腾讯专用', 'SCHEMA', dbo, 'table', e_sms_send_status, 'column', fee;
EXEC sp_addextendedproperty 'MS_Description', '创建人', 'SCHEMA', dbo, 'table', e_sms_send_status, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', e_sms_send_status, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '最后修改人', 'SCHEMA', dbo, 'table', e_sms_send_status, 'column', updated_by;
EXEC sp_addextendedproperty 'MS_Description', '最后修改时间', 'SCHEMA', dbo, 'table', e_sms_send_status, 'column', update_time;


CREATE INDEX task_id_tel_num ON e_sms_send_status(task_id,tel_num);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[e_sms_task]') AND type in (N'U')) DROP TABLE [dbo].[e_sms_task];
CREATE TABLE [dbo].[e_sms_task](
                                   id BIGINT NOT NULL,
                                   template_id BIGINT NOT NULL,
                                   status VARCHAR(10),
    source_type VARCHAR(10),
    topic VARCHAR(255),
    template_params VARCHAR(500),
    send_time DATETIME,
    content VARCHAR(500),
    draft BIT DEFAULT  0,
    created_by BIGINT,
    create_time DATETIME,
    updated_by BIGINT,
    update_time DATETIME,
    PRIMARY KEY (id)
    );

EXEC sp_addextendedproperty 'MS_Description', '发送任务', 'SCHEMA', dbo, 'table', e_sms_task, null, null;
EXEC sp_addextendedproperty 'MS_Description', '短信记录ID', 'SCHEMA', dbo, 'table', e_sms_task, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '短信模板;@Echo(api = SMS_TEMPLATE_ID_CLASS)#e_sms_template', 'SCHEMA', dbo, 'table', e_sms_task, 'column', template_id;
EXEC sp_addextendedproperty 'MS_Description', '执行状态;
(手机号具体发送状态看sms_send_status表)
#TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}', 'SCHEMA', dbo, 'table', e_sms_task, 'column', status;
EXEC sp_addextendedproperty 'MS_Description', '发送渠道;#SourceType{APP:应用;SERVICE:服务}', 'SCHEMA', dbo, 'table', e_sms_task, 'column', source_type;
EXEC sp_addextendedproperty 'MS_Description', '主题', 'SCHEMA', dbo, 'table', e_sms_task, 'column', topic;
EXEC sp_addextendedproperty 'MS_Description', '参数;
需要封装为{‘key’:’value’, ...}格式且key必须有序', 'SCHEMA', dbo, 'table', e_sms_task, 'column', template_params;
EXEC sp_addextendedproperty 'MS_Description', '发送时间', 'SCHEMA', dbo, 'table', e_sms_task, 'column', send_time;
EXEC sp_addextendedproperty 'MS_Description', '发送内容;
需要封装正确格式化: 您好，张三，您有一个新的快递。', 'SCHEMA', dbo, 'table', e_sms_task, 'column', content;
EXEC sp_addextendedproperty 'MS_Description', '是否草稿', 'SCHEMA', dbo, 'table', e_sms_task, 'column', draft;
EXEC sp_addextendedproperty 'MS_Description', '创建人ID', 'SCHEMA', dbo, 'table', e_sms_task, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', e_sms_task, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '最后修改人', 'SCHEMA', dbo, 'table', e_sms_task, 'column', updated_by;
EXEC sp_addextendedproperty 'MS_Description', '最后修改时间', 'SCHEMA', dbo, 'table', e_sms_task, 'column', update_time;


CREATE INDEX tempate_id_topic_content ON e_sms_task(template_id,topic,content);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[e_sms_template]') AND type in (N'U')) DROP TABLE [dbo].[e_sms_template];
CREATE TABLE [dbo].[e_sms_template](
                                       id BIGINT NOT NULL,
                                       provider_type VARCHAR(10) NOT NULL,
    app_id VARCHAR(255) NOT NULL,
    app_secret VARCHAR(255) NOT NULL,
    url VARCHAR(255),
    name VARCHAR(255),
    content VARCHAR(255) NOT NULL,
    template_params VARCHAR(255) NOT NULL,
    template_code VARCHAR(50) NOT NULL,
    sign_name VARCHAR(100),
    template_describe VARCHAR(255),
    created_by BIGINT,
    create_time DATETIME,
    updated_by BIGINT,
    update_time DATETIME,
    PRIMARY KEY (id)
    );

EXEC sp_addextendedproperty 'MS_Description', '短信模板', 'SCHEMA', dbo, 'table', e_sms_template, null, null;
EXEC sp_addextendedproperty 'MS_Description', '模板ID', 'SCHEMA', dbo, 'table', e_sms_template, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '供应商类型;
#ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}', 'SCHEMA', dbo, 'table', e_sms_template, 'column', provider_type;
EXEC sp_addextendedproperty 'MS_Description', '应用ID', 'SCHEMA', dbo, 'table', e_sms_template, 'column', app_id;
EXEC sp_addextendedproperty 'MS_Description', '应用密码', 'SCHEMA', dbo, 'table', e_sms_template, 'column', app_secret;
EXEC sp_addextendedproperty 'MS_Description', 'SMS服务域名;
百度、其他厂商会用', 'SCHEMA', dbo, 'table', e_sms_template, 'column', url;
EXEC sp_addextendedproperty 'MS_Description', '模板名称', 'SCHEMA', dbo, 'table', e_sms_template, 'column', name;
EXEC sp_addextendedproperty 'MS_Description', '模板内容', 'SCHEMA', dbo, 'table', e_sms_template, 'column', content;
EXEC sp_addextendedproperty 'MS_Description', '模板参数', 'SCHEMA', dbo, 'table', e_sms_template, 'column', template_params;
EXEC sp_addextendedproperty 'MS_Description', '模板编码', 'SCHEMA', dbo, 'table', e_sms_template, 'column', template_code;
EXEC sp_addextendedproperty 'MS_Description', '签名', 'SCHEMA', dbo, 'table', e_sms_template, 'column', sign_name;
EXEC sp_addextendedproperty 'MS_Description', '备注', 'SCHEMA', dbo, 'table', e_sms_template, 'column', template_describe;
EXEC sp_addextendedproperty 'MS_Description', '创建人ID', 'SCHEMA', dbo, 'table', e_sms_template, 'column', created_by;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', e_sms_template, 'column', create_time;
EXEC sp_addextendedproperty 'MS_Description', '最后修改人', 'SCHEMA', dbo, 'table', e_sms_template, 'column', updated_by;
EXEC sp_addextendedproperty 'MS_Description', '最后修改时间', 'SCHEMA', dbo, 'table', e_sms_template, 'column', update_time;

