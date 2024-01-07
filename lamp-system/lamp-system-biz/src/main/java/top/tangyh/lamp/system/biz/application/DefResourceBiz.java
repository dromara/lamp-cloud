package top.tangyh.lamp.system.biz.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.lamp.system.service.application.DefResourceService;

import java.util.List;

/**
 * @author zuihou
 * @date 2021/11/17 15:23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DefResourceBiz {
    private final DefResourceService defResourceService;

    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIdWithCache(List<Long> ids) {
        boolean result = defResourceService.removeByIdWithCache(ids);

        // 删除 角色资源关系表 员工资源关系表
        defResourceService.deleteRoleResourceRelByResourceId(ids);
        return result;
    }


}
