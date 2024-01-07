package top.tangyh.lamp.msg.service.impl;

import cn.hutool.core.util.StrUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.utils.ArgumentAssert;

import top.tangyh.lamp.msg.entity.DefInterface;
import top.tangyh.lamp.msg.entity.DefInterfaceProperty;
import top.tangyh.lamp.msg.enumeration.InterfaceExecModeEnum;
import top.tangyh.lamp.msg.manager.DefInterfaceManager;
import top.tangyh.lamp.msg.manager.DefInterfacePropertyManager;
import top.tangyh.lamp.msg.service.DefInterfaceService;
import top.tangyh.lamp.msg.vo.save.DefInterfaceSaveVO;
import top.tangyh.lamp.msg.vo.update.DefInterfaceUpdateVO;

import java.util.Collection;

/**
 * <p>
 * 业务实现类
 * 接口
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 16:45:45
 * @create [2022-07-04 16:45:45] [zuihou] [代码生成器生成]
 */

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DefInterfaceServiceImpl extends SuperServiceImpl<DefInterfaceManager, Long, DefInterface> implements DefInterfaceService {
    private final DefInterfacePropertyManager defInterfacePropertyManager;

    @Override
    public Boolean check(String code, Long id) {
        ArgumentAssert.notEmpty(code, "请填写接口编码");
        return superManager.count(Wraps.<DefInterface>lbQ().eq(DefInterface::getCode, code)
                .ne(DefInterface::getId, id)) > 0;
    }

    @Override
    protected <SaveVO> DefInterface saveBefore(SaveVO saveVO) {
        DefInterfaceSaveVO interfaceSaveVO = (DefInterfaceSaveVO) saveVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(interfaceSaveVO.getCode()) &&
                check(interfaceSaveVO.getCode(), null), "接口编码{}已存在", interfaceSaveVO.getCode());
        if (InterfaceExecModeEnum.IMPL_CLASS.eq(interfaceSaveVO.getExecMode())) {
            ArgumentAssert.notEmpty(interfaceSaveVO.getImplClass(), "请填写实现类");
        } else {
            ArgumentAssert.notEmpty(interfaceSaveVO.getScript(), "请填写实现脚本");
        }
        return super.saveBefore(saveVO);
    }

    @Override
    protected <UpdateVO> DefInterface updateBefore(UpdateVO updateVO) {
        DefInterfaceUpdateVO interfaceUpdateVO = (DefInterfaceUpdateVO) updateVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(interfaceUpdateVO.getCode()) &&
                        check(interfaceUpdateVO.getCode(), interfaceUpdateVO.getId()),
                "接口编码{}已存在", interfaceUpdateVO.getCode());
        if (InterfaceExecModeEnum.IMPL_CLASS.eq(interfaceUpdateVO.getExecMode())) {
            ArgumentAssert.notEmpty(interfaceUpdateVO.getImplClass(), "请填写实现类");
        } else {
            ArgumentAssert.notEmpty(interfaceUpdateVO.getScript(), "请填写实现脚本");
        }
        return super.updateBefore(interfaceUpdateVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<Long> idList) {
        defInterfacePropertyManager.remove(Wraps.<DefInterfaceProperty>lbQ().in(DefInterfaceProperty::getInterfaceId, idList));
        return super.removeByIds(idList);
    }
}


