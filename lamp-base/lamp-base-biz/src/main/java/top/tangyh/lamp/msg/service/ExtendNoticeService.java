package top.tangyh.lamp.msg.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.base.service.SuperService;
import top.tangyh.lamp.msg.entity.ExtendNotice;
import top.tangyh.lamp.msg.vo.query.ExtendNoticePageQuery;
import top.tangyh.lamp.msg.vo.result.ExtendNoticeResultVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 通知表
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [zuihou] [代码生成器生成]
 */
public interface ExtendNoticeService extends SuperService<Long, ExtendNotice> {

    /**
     * 分页查询
     *
     * @param page
     * @param params
     * @return
     */
    IPage<ExtendNoticeResultVO> page(IPage<ExtendNoticeResultVO> page, PageParams<ExtendNoticePageQuery> params);

    /**
     * 标记 已读
     *
     * @param noticeIds
     * @param employeeId
     * @return
     */
    Boolean mark(List<Long> noticeIds, Long employeeId);

    /**
     * 删除我的通知
     *
     * @param noticeIds
     * @return
     */
    Boolean deleteMyNotice(List<Long> noticeIds);
}


