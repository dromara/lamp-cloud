package top.tangyh.lamp.userinfo.feign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.R;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.model.entity.base.SysResource;
import top.tangyh.lamp.model.entity.base.SysStation;
import top.tangyh.lamp.model.entity.base.SysUser;
import top.tangyh.lamp.model.vo.query.ResourceQueryDTO;
import top.tangyh.lamp.userinfo.service.ResourceHelperService;
import top.tangyh.lamp.userinfo.service.RoleHelperService;
import top.tangyh.lamp.userinfo.service.UserHelperService;

import java.util.List;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/5/22 2:14 PM
 * @create [2022/5/22 2:14 PM ] [tangyh] [初始创建]
 */
@RequiredArgsConstructor
@Service
public class UserResolverServiceFeignImpl implements UserResolverService {
    private final UserHelperService userHelperService;
    private final ResourceHelperService resourceHelperService;
    private final RoleHelperService roleHelperService;

    @Override
    public R<SysUser> getById(UserQuery query) {
        Long userId = query.getUserId();
        SysUser sysUser = userHelperService.getUserByIdCache(userId);
        if (sysUser == null) {
            throw BizException.wrap("无法获取用户信息");
        }
        sysUser.setOrgId(sysUser.getOrgId());
        sysUser.setStationId(sysUser.getStationId());

        if (query.getFull() || query.getOrg()) {
            sysUser.setOrg(userHelperService.getOrgByIdCache(sysUser.getOrgId()));
        }

        if (query.getFull() || query.getStation()) {
            SysStation station = userHelperService.getStationByIdCache(sysUser.getStationId());
            if (station != null) {
                sysUser.setStation(station);
            }
        }

        if (query.getFull() || query.getRoles()) {
            List<String> list = roleHelperService.findRoleCodeByUserId(userId);
            sysUser.setRoles(list);
        }
        if (query.getFull() || query.getResource()) {
            List<SysResource> resourceList = resourceHelperService.findVisibleResource(ResourceQueryDTO.builder().userId(userId).build());
            sysUser.setResources(CollHelper.split(resourceList, SysResource::getCode, StrPool.SEMICOLON));
        }

        return R.success(sysUser);
    }
}
