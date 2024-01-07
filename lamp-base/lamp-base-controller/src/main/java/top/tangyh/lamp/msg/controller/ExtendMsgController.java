package top.tangyh.lamp.msg.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.WebLog;
import top.tangyh.basic.annotation.user.LoginUser;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.base.entity.SuperEntity;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.database.mybatis.conditions.query.QueryWrap;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.datascope.DataScopeHelper;
import top.tangyh.lamp.model.entity.system.SysUser;
import top.tangyh.lamp.msg.biz.MsgBiz;
import top.tangyh.lamp.msg.entity.ExtendMsg;
import top.tangyh.lamp.msg.enumeration.SourceType;
import top.tangyh.lamp.msg.service.ExtendMsgService;
import top.tangyh.lamp.msg.vo.query.ExtendMsgPageQuery;
import top.tangyh.lamp.msg.vo.result.ExtendMsgResultVO;
import top.tangyh.lamp.msg.vo.save.ExtendMsgSaveVO;
import top.tangyh.lamp.msg.vo.update.ExtendMsgPublishVO;
import top.tangyh.lamp.msg.vo.update.ExtendMsgSendVO;
import top.tangyh.lamp.msg.vo.update.ExtendMsgUpdateVO;

/**
 * <p>
 * 前端控制器
 * 消息
 * </p>
 *
 * @author zuihou
 * @date 2022-07-10 11:41:17
 * @create [2022-07-10 11:41:17] [zuihou] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/extendMsg")
@Tag(name = "消息")
public class ExtendMsgController extends SuperController<ExtendMsgService, Long, ExtendMsg, ExtendMsgSaveVO,
        ExtendMsgUpdateVO, ExtendMsgPageQuery, ExtendMsgResultVO> {
    private final EchoService echoService;
    private final MsgBiz msgBiz;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Operation(summary = "根据模板发送消息", description = "根据模板发送消息")
    @PostMapping("/sendByTemplate")
    @WebLog("发送消息")
    public R<Boolean> sendByTemplate(@RequestBody @Validated(SuperEntity.Update.class) ExtendMsgSendVO data
            , @Parameter(hidden = true) @LoginUser(isEmployee = true) SysUser sysUser) {
        return R.success(msgBiz.sendByTemplate(data, sysUser));
    }

    @Operation(summary = "发布站内信", description = "发布站内信")
    @PostMapping("/publish")
    @WebLog("发布站内信")
    public R<Boolean> publish(@RequestBody @Validated(SuperEntity.Update.class) ExtendMsgPublishVO data
            , @Parameter(hidden = true) @LoginUser(isEmployee = true) SysUser sysUser) {

        return R.success(msgBiz.publish(data, sysUser));
    }

    @Override
    public QueryWrap<ExtendMsg> handlerWrapper(ExtendMsg model, PageParams<ExtendMsgPageQuery> params) {
        QueryWrap<ExtendMsg> queryWrap = super.handlerWrapper(model, params);
        queryWrap.lambda().eq(ExtendMsg::getChannel, SourceType.APP);
        DataScopeHelper.startDataScope("extend_msg");
        return queryWrap;
    }

    /**
     * 查询消息中心
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Operation(summary = "查询消息中心", description = "查询消息中心")
    @GetMapping("/{id}")
    @WebLog("查询消息中心")
    @Override
    public R<ExtendMsgResultVO> get(@PathVariable Long id) {
        return R.success(superService.getResultById(id));
    }


}


