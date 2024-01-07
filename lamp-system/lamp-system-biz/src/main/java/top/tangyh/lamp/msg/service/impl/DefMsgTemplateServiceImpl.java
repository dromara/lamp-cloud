package top.tangyh.lamp.msg.service.impl;

import cn.hutool.core.util.StrUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.jackson.JsonUtil;
import top.tangyh.basic.model.Kv;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.StrPool;

import top.tangyh.lamp.msg.entity.DefMsgTemplate;
import top.tangyh.lamp.msg.manager.DefMsgTemplateManager;
import top.tangyh.lamp.msg.service.DefMsgTemplateService;
import top.tangyh.lamp.msg.vo.save.DefMsgTemplateSaveVO;
import top.tangyh.lamp.msg.vo.update.DefMsgTemplateUpdateVO;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 业务实现类
 * 消息模板
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [zuihou] [代码生成器生成]
 */

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DefMsgTemplateServiceImpl extends SuperServiceImpl<DefMsgTemplateManager, Long, DefMsgTemplate> implements DefMsgTemplateService {
    @Override
    public DefMsgTemplate getByCode(String code) {
        return superManager.getByCode(code);
    }

    @Override
    public Boolean check(String code, Long id) {
        ArgumentAssert.notEmpty(code, "请填写模板标识");
        return superManager.count(Wraps.<DefMsgTemplate>lbQ().eq(DefMsgTemplate::getCode, code)
                .ne(DefMsgTemplate::getId, id)) > 0;
    }

    /**
     * 解析占位符 ${xxx}
     */
    private final static Pattern REG_EX = Pattern.compile("\\$\\{([^}]+)}");

    private static String getParamByContent(String title, String content) {


        // 查找字符串中是否有匹配正则表达式的字符/字符串//有序， 目的是为了兼容 腾讯云参数
        Set<Kv> list = new LinkedHashSet<>();
        if (StrUtil.isNotEmpty(title)) {
            //编译正则表达式
            //忽略大小写的写法:
            Matcher matcher = REG_EX.matcher(title);
            while (matcher.find()) {
                String key = matcher.group(1);
                list.add(Kv.builder().key(key).value(StrPool.EMPTY).build());
            }
        }

        if (StrUtil.isNotEmpty(content)) {
            //编译正则表达式
            //忽略大小写的写法:
            Matcher matcher = REG_EX.matcher(content);
            while (matcher.find()) {
                String key = matcher.group(1);
                list.add(Kv.builder().key(key).value(StrPool.EMPTY).build());
            }
        }

        return JsonUtil.toJson(list);
    }

    @Override
    protected <SaveVO> DefMsgTemplate saveBefore(SaveVO saveVO) {
        DefMsgTemplateSaveVO extendMsgTemplateSaveVO = (DefMsgTemplateSaveVO) saveVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(extendMsgTemplateSaveVO.getCode()) &&
                check(extendMsgTemplateSaveVO.getCode(), null), "模板标识{}已存在", extendMsgTemplateSaveVO.getCode());
        extendMsgTemplateSaveVO.setParam(getParamByContent(extendMsgTemplateSaveVO.getTitle(), extendMsgTemplateSaveVO.getContent()));
        return super.saveBefore(extendMsgTemplateSaveVO);
    }

    @Override
    protected <UpdateVO> DefMsgTemplate updateBefore(UpdateVO updateVO) {
        DefMsgTemplateUpdateVO extendMsgTemplateUpdateVO = (DefMsgTemplateUpdateVO) updateVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(extendMsgTemplateUpdateVO.getCode()) &&
                        check(extendMsgTemplateUpdateVO.getCode(), extendMsgTemplateUpdateVO.getId()),
                "模板标识{}已存在", extendMsgTemplateUpdateVO.getCode());
        extendMsgTemplateUpdateVO.setParam(getParamByContent(extendMsgTemplateUpdateVO.getTitle(), extendMsgTemplateUpdateVO.getContent()));
        return super.updateBefore(extendMsgTemplateUpdateVO);
    }
}


