package top.tangyh.lamp.msg.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.SysLog;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.lamp.msg.dto.MsgPageResult;
import top.tangyh.lamp.msg.dto.MsgQuery;
import top.tangyh.lamp.msg.entity.Msg;
import top.tangyh.lamp.msg.enumeration.MsgType;
import top.tangyh.lamp.msg.service.MsgService;
import top.tangyh.lamp.msg.vo.MyMsgResult;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 消息中心
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@RestController
@RequestMapping("/anyone/myMsg")
@Api(value = "MyMsgController", tags = "我的消息")
@Validated
@RequiredArgsConstructor
public class MyMsgController {
    private final MsgService msgService;

    @ApiOperation(value = "全量查询我的消息", notes = "全量查询我的消息")
    @PostMapping
    @SysLog(value = "'全量查询我的消息:第' + #params?.current + '页, 显示' + #params?.size + '行'", response = false)
    public R<MyMsgResult> myMsg(@RequestBody @Validated PageParams<MsgQuery> params) {
        // 待办
        IPage<MsgPageResult> todoList = params.buildPage(Msg.class);
        params.setModel(MsgQuery.builder().userId(ContextUtil.getUserId())
                .msgType(MsgType.TO_DO).isRead(false).build());
        msgService.page(todoList, params);
        // 通知
        IPage<MsgPageResult> notifyList = params.buildPage(Msg.class);
        params.setModel(MsgQuery.builder().userId(ContextUtil.getUserId())
                .msgType(MsgType.NOTIFY).isRead(false).build());
        msgService.page(notifyList, params);
        // 公告
        IPage<MsgPageResult> noticeList = params.buildPage(Msg.class);
        params.setModel(MsgQuery.builder().userId(ContextUtil.getUserId())
                .msgType(MsgType.NOTICE).isRead(false).build());
        msgService.page(noticeList, params);
        // 通知
        IPage<MsgPageResult> earlyWarningList = params.buildPage(Msg.class);
        params.setModel(MsgQuery.builder().userId(ContextUtil.getUserId())
                .msgType(MsgType.EARLY_WARNING).isRead(false).build());
        msgService.page(earlyWarningList, params);

        MyMsgResult result = MyMsgResult.builder()
                .todoList(todoList).notifyList(notifyList)
                .noticeList(noticeList).earlyWarningList(earlyWarningList)
                .build();
        return R.success(result);
    }

    @ApiOperation(value = "分页查询我的消息", notes = "分页查询我的消息")
    @PostMapping("/page")
    @SysLog(value = "'分页查询我的消息:第' + #params?.current + '页, 显示' + #params?.size + '行'", response = false)
    public R<IPage<MsgPageResult>> page(@RequestBody @Validated PageParams<MsgQuery> params) {
        IPage<MsgPageResult> page = params.buildPage();
        params.getModel().setUserId(ContextUtil.getUserId());
        msgService.page(page, params);
        return R.success(page);
    }

    /**
     * 标记消息为已读
     *
     * @param msgIds 主表id
     * @return 是否成功
     */
    @ApiOperation(value = "标记消息为已读", notes = "标记消息为已读")
    @PostMapping(value = "/mark")
    public R<Boolean> mark(@RequestBody List<Long> msgIds) {
        return R.success(msgService.mark(msgIds, ContextUtil.getUserId()));
    }
}
