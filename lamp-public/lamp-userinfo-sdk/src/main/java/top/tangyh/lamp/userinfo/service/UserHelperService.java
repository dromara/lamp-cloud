package top.tangyh.lamp.userinfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import top.tangyh.basic.context.ContextUtil;
import top.tangyh.lamp.model.entity.base.SysOrg;
import top.tangyh.lamp.model.entity.base.SysStation;
import top.tangyh.lamp.model.entity.base.SysUser;
import top.tangyh.lamp.userinfo.dao.OrgHelperMapper;
import top.tangyh.lamp.userinfo.dao.StationHelperMapper;
import top.tangyh.lamp.userinfo.dao.UserHelperMapper;

/**
 * 用户相关 帮助类
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/5/22 2:16 PM
 * @create [2022/5/22 2:16 PM ] [tangyh] [初始创建]
 */
@Component
@RequiredArgsConstructor
public class UserHelperService {

    private final UserHelperMapper userHelperMapper;
    private final OrgHelperMapper orgHelperMapper;
    private final StationHelperMapper stationHelperMapper;

    public SysUser getUserByIdCache(Long userId) {
        ContextUtil.setDatabaseBase();
        return userHelperMapper.selectById(userId);
    }

    public SysOrg getOrgByIdCache(Long orgId) {
        ContextUtil.setDatabaseBase();
        return orgHelperMapper.selectById(orgId);
    }

    public SysStation getStationByIdCache(Long positionId) {
        ContextUtil.setDatabaseBase();
        return stationHelperMapper.selectById(positionId);
    }


}
