package top.tangyh.lamp.test.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;

import top.tangyh.lamp.test.entity.DefGenTestSimple;
import top.tangyh.lamp.test.manager.DefGenTestSimpleManager;
import top.tangyh.lamp.test.service.DefGenTestSimpleService;

/**
 * <p>
 * 业务实现类
 * 测试单表
 * </p>
 *
 * @author zuihou
 * @date 2022-04-15 15:36:45
 * @create [2022-04-15 15:36:45] [zuihou] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class DefGenTestSimpleServiceImpl extends SuperServiceImpl<DefGenTestSimpleManager, Long, DefGenTestSimple> implements DefGenTestSimpleService {

}


