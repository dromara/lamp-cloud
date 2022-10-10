CREATE TABLE e_block_list(
    id NUMBER(20) NOT NULL,
    ip VARCHAR2(20),
    request_uri VARCHAR2(255),
    request_method VARCHAR2(10),
    limit_start VARCHAR2(8),
    limit_end VARCHAR2(8),
    state NUMBER(1) DEFAULT  0,
    create_time DATE,
    created_by NUMBER(20),
    update_time DATE,
    updated_by NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE e_block_list IS '黑名单';
COMMENT ON COLUMN e_block_list.id IS 'ID';
COMMENT ON COLUMN e_block_list.ip IS '阻止访问ip';
COMMENT ON COLUMN e_block_list.request_uri IS '请求URI';
COMMENT ON COLUMN e_block_list.request_method IS '请求方法;
如果为ALL则表示对所有方法生效';
COMMENT ON COLUMN e_block_list.limit_start IS '限制时间起';
COMMENT ON COLUMN e_block_list.limit_end IS '限制时间止';
COMMENT ON COLUMN e_block_list.state IS '状态';
COMMENT ON COLUMN e_block_list.create_time IS '创建时间';
COMMENT ON COLUMN e_block_list.created_by IS '创建人';
COMMENT ON COLUMN e_block_list.update_time IS '修改时间';
COMMENT ON COLUMN e_block_list.updated_by IS '修改人';

CREATE TABLE e_msg(
    id NUMBER(20) NOT NULL,
    biz_id VARCHAR2(64),
    biz_type VARCHAR2(64),
    msg_type VARCHAR2(20) NOT NULL,
    title VARCHAR2(255),
    content CLOB,
    author VARCHAR2(50),
    handler_url VARCHAR2(255),
    handler_params VARCHAR2(500),
    is_single_handle NUMBER(1) DEFAULT  1,
    create_time DATE,
    created_by NUMBER(20),
    update_time DATE,
    updated_by NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE e_msg IS '消息表';
COMMENT ON COLUMN e_msg.id IS 'ID';
COMMENT ON COLUMN e_msg.biz_id IS '业务ID';
COMMENT ON COLUMN e_msg.biz_type IS '业务类型;
#MsgBizType{USER_LOCK:账号锁定;USER_REG:账号申请;WORK_APPROVAL:考勤审批;}';
COMMENT ON COLUMN e_msg.msg_type IS '消息类型;
#MsgType{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}';
COMMENT ON COLUMN e_msg.title IS '标题';
COMMENT ON COLUMN e_msg.content IS '内容';
COMMENT ON COLUMN e_msg.author IS '发布人';
COMMENT ON COLUMN e_msg.handler_url IS '处理地址;
以http开头时直接跳转，否则与#c_application表拼接后跳转http可带参数';
COMMENT ON COLUMN e_msg.handler_params IS '处理参数';
COMMENT ON COLUMN e_msg.is_single_handle IS '是否单人处理';
COMMENT ON COLUMN e_msg.create_time IS '创建时间';
COMMENT ON COLUMN e_msg.created_by IS '创建人id';
COMMENT ON COLUMN e_msg.update_time IS '最后修改时间';
COMMENT ON COLUMN e_msg.updated_by IS '最后修改人';

CREATE TABLE e_msg_receive(
    id NUMBER(20) NOT NULL,
    msg_id NUMBER(20) NOT NULL,
    user_id NUMBER(20) NOT NULL,
    is_read NUMBER(1) DEFAULT  0,
    create_time DATE,
    created_by NUMBER(20),
    update_time DATE,
    updated_by NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE e_msg_receive IS '消息接收表';
COMMENT ON COLUMN e_msg_receive.id IS 'ID';
COMMENT ON COLUMN e_msg_receive.msg_id IS '消息ID;
#msg';
COMMENT ON COLUMN e_msg_receive.user_id IS '接收人ID;
#c_user';
COMMENT ON COLUMN e_msg_receive.is_read IS '是否已读';
COMMENT ON COLUMN e_msg_receive.create_time IS '创建时间';
COMMENT ON COLUMN e_msg_receive.created_by IS '创建人';
COMMENT ON COLUMN e_msg_receive.update_time IS '最后修改时间';
COMMENT ON COLUMN e_msg_receive.updated_by IS '最后修改人';

CREATE TABLE e_rate_limiter(
    id NUMBER(20) NOT NULL,
    count NUMBER(10) DEFAULT  0,
    request_uri VARCHAR2(255),
    request_method VARCHAR2(10),
    limit_start VARCHAR2(8),
    limit_end VARCHAR2(8),
    state NUMBER(1) DEFAULT  0,
    interval_sec NUMBER(20) DEFAULT  0,
    create_time DATE,
    created_by NUMBER(20),
    update_time DATE,
    updated_by NUMBER(20),
    PRIMARY KEY (id)
);

COMMENT ON TABLE e_rate_limiter IS '限流';
COMMENT ON COLUMN e_rate_limiter.id IS 'ID';
COMMENT ON COLUMN e_rate_limiter.count IS '次数';
COMMENT ON COLUMN e_rate_limiter.request_uri IS '请求URI';
COMMENT ON COLUMN e_rate_limiter.request_method IS '请求方法;
如果为ALL则表示对所有方法生效';
COMMENT ON COLUMN e_rate_limiter.limit_start IS '限制时间起';
COMMENT ON COLUMN e_rate_limiter.limit_end IS '限制时间止';
COMMENT ON COLUMN e_rate_limiter.state IS '状态';
COMMENT ON COLUMN e_rate_limiter.interval_sec IS '时间窗口';
COMMENT ON COLUMN e_rate_limiter.create_time IS '创建时间';
COMMENT ON COLUMN e_rate_limiter.created_by IS '创建人';
COMMENT ON COLUMN e_rate_limiter.update_time IS '修改时间';
COMMENT ON COLUMN e_rate_limiter.updated_by IS '修改人';

CREATE TABLE e_sms_send_status(
    id NUMBER(20) NOT NULL,
    task_id NUMBER(20) NOT NULL,
    send_status VARCHAR2(10) NOT NULL,
    tel_num VARCHAR2(20) NOT NULL,
    biz_id VARCHAR2(255),
    ext VARCHAR2(255),
    code VARCHAR2(255),
    message VARCHAR2(500),
    fee NUMBER(10),
    created_by NUMBER(20),
    create_time DATE,
    updated_by NUMBER(20),
    update_time DATE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE e_sms_send_status IS '短信发送状态';
COMMENT ON COLUMN e_sms_send_status.id IS 'ID';
COMMENT ON COLUMN e_sms_send_status.task_id IS '任务ID;
#e_sms_task';
COMMENT ON COLUMN e_sms_send_status.send_status IS '发送状态;
#SendStatus{WAITING:等待发送;SUCCESS:发送成功;FAIL:发送失败}';
COMMENT ON COLUMN e_sms_send_status.tel_num IS '接收者手机
单个手机号;
阿里：发送回执ID,可根据该ID查询具体的发送状态  腾讯：sid 标识本次发送id，标识一次短信下发记录  百度：requestId 短信发送请求唯一流水ID';
COMMENT ON COLUMN e_sms_send_status.biz_id IS '发送回执ID';
COMMENT ON COLUMN e_sms_send_status.ext IS '发送返回;
阿里：RequestId 请求ID  腾讯：ext：用户的session内容，腾讯server回包中会原样返回   百度：无';
COMMENT ON COLUMN e_sms_send_status.code IS '状态码;阿里：返回OK代表请求成功,其他错误码详见错误码列表  腾讯：0表示成功(计费依据)，非0表示失败  百度：1000 表示成功';
COMMENT ON COLUMN e_sms_send_status.message IS '状态码的描述';
COMMENT ON COLUMN e_sms_send_status.fee IS '短信计费的条数;腾讯专用';
COMMENT ON COLUMN e_sms_send_status.created_by IS '创建人';
COMMENT ON COLUMN e_sms_send_status.create_time IS '创建时间';
COMMENT ON COLUMN e_sms_send_status.updated_by IS '最后修改人';
COMMENT ON COLUMN e_sms_send_status.update_time IS '最后修改时间';


CREATE INDEX task_id_tel_num ON e_sms_send_status(task_id,tel_num);

CREATE TABLE e_sms_task(
    id NUMBER(20) NOT NULL,
    template_id NUMBER(20) NOT NULL,
    status VARCHAR2(10),
    source_type VARCHAR2(10),
    topic VARCHAR2(255),
    template_params VARCHAR2(500),
    send_time DATE,
    content VARCHAR2(500),
    draft NUMBER(1) DEFAULT  0,
    created_by NUMBER(20),
    create_time DATE,
    updated_by NUMBER(20),
    update_time DATE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE e_sms_task IS '发送任务';
COMMENT ON COLUMN e_sms_task.id IS '短信记录ID';
COMMENT ON COLUMN e_sms_task.template_id IS '短信模板;@Echo(api = SMS_TEMPLATE_ID_CLASS)#e_sms_template';
COMMENT ON COLUMN e_sms_task.status IS '执行状态;
(手机号具体发送状态看sms_send_status表) 
#TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}';
COMMENT ON COLUMN e_sms_task.source_type IS '发送渠道;#SourceType{APP:应用;SERVICE:服务}';
COMMENT ON COLUMN e_sms_task.topic IS '主题';
COMMENT ON COLUMN e_sms_task.template_params IS '参数;
需要封装为{‘key’:’value’, ...}格式且key必须有序';
COMMENT ON COLUMN e_sms_task.send_time IS '发送时间';
COMMENT ON COLUMN e_sms_task.content IS '发送内容;
需要封装正确格式化: 您好，张三，您有一个新的快递。';
COMMENT ON COLUMN e_sms_task.draft IS '是否草稿';
COMMENT ON COLUMN e_sms_task.created_by IS '创建人ID';
COMMENT ON COLUMN e_sms_task.create_time IS '创建时间';
COMMENT ON COLUMN e_sms_task.updated_by IS '最后修改人';
COMMENT ON COLUMN e_sms_task.update_time IS '最后修改时间';


CREATE INDEX tempate_id_topic_content ON e_sms_task(template_id,topic,content);

CREATE TABLE e_sms_template(
    id NUMBER(20) NOT NULL,
    provider_type VARCHAR2(10) NOT NULL,
    app_id VARCHAR2(255) NOT NULL,
    app_secret VARCHAR2(255) NOT NULL,
    url VARCHAR2(255),
    name VARCHAR2(255),
    content VARCHAR2(255) NOT NULL,
    template_params VARCHAR2(255) NOT NULL,
    template_code VARCHAR2(50) NOT NULL,
    sign_name VARCHAR2(100),
    template_describe VARCHAR2(255),
    created_by NUMBER(20),
    create_time DATE,
    updated_by NUMBER(20),
    update_time DATE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE e_sms_template IS '短信模板';
COMMENT ON COLUMN e_sms_template.id IS '模板ID';
COMMENT ON COLUMN e_sms_template.provider_type IS '供应商类型;
#ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}';
COMMENT ON COLUMN e_sms_template.app_id IS '应用ID';
COMMENT ON COLUMN e_sms_template.app_secret IS '应用密码';
COMMENT ON COLUMN e_sms_template.url IS 'SMS服务域名;
百度、其他厂商会用';
COMMENT ON COLUMN e_sms_template.name IS '模板名称';
COMMENT ON COLUMN e_sms_template.content IS '模板内容';
COMMENT ON COLUMN e_sms_template.template_params IS '模板参数';
COMMENT ON COLUMN e_sms_template.template_code IS '模板编码';
COMMENT ON COLUMN e_sms_template.sign_name IS '签名';
COMMENT ON COLUMN e_sms_template.template_describe IS '备注';
COMMENT ON COLUMN e_sms_template.created_by IS '创建人ID';
COMMENT ON COLUMN e_sms_template.create_time IS '创建时间';
COMMENT ON COLUMN e_sms_template.updated_by IS '最后修改人';
COMMENT ON COLUMN e_sms_template.update_time IS '最后修改时间';

