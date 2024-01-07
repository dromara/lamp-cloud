package top.tangyh.lamp.msg.manager;

import top.tangyh.basic.base.manager.SuperManager;
import top.tangyh.lamp.msg.entity.DefMsgTemplate;

/**
 * <p>
 * 通用业务接口
 * 消息模板
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [zuihou] [代码生成器生成]
 */
public interface DefMsgTemplateManager extends SuperManager<DefMsgTemplate> {
    /**
     * 根据消息模板编码查询消息模板
     *
     * @param code
     * @return
     */
    DefMsgTemplate getByCode(String code);
}


