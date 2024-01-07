package top.tangyh.lamp.datascope.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.tangyh.basic.base.entity.SuperEntity;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.lamp.datascope.model.DataFieldProperty;
import top.tangyh.lamp.datascope.service.OrgHelperService;

import java.util.Collections;
import java.util.List;

/**
 * 本部门
 *
 * @author zuihou
 * @date 2022/1/9 23:29
 */
@Slf4j
@RequiredArgsConstructor
@Component("DATA_SCOPE_05")
public class DeptDataScopeProviderImpl implements DataScopeProvider {
    @Autowired
    private OrgHelperService orgHelperService;

    @Override
    public List<DataFieldProperty> findDataFieldProperty(List<DataFieldProperty> fsp) {
        Long mainDeptId = orgHelperService.getMainDeptIdByEmployeeId(ContextUtil.getEmployeeId());
        if (mainDeptId == null) {
            return Collections.emptyList();
        }
        List<Long> orgIdList = Collections.singletonList(mainDeptId);
        fsp.forEach(item -> {
            item.setField(SuperEntity.CREATED_ORG_ID_FIELD);
            item.setValues(orgIdList);
        });
        return fsp;
    }
}
