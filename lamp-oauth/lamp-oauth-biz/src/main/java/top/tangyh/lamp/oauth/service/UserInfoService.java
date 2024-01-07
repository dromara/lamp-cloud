package top.tangyh.lamp.oauth.service;

import top.tangyh.lamp.base.entity.user.BaseOrg;
import top.tangyh.lamp.oauth.vo.param.RegisterByEmailVO;
import top.tangyh.lamp.oauth.vo.param.RegisterByMobileVO;
import top.tangyh.lamp.oauth.vo.result.OrgResultVO;

import java.util.List;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/9/16 12:21 PM
 * @create [2022/9/16 12:21 PM ] [tangyh] [初始创建]
 */
public interface UserInfoService {
    /**
     * 根据单位ID查找部门
     *
     * @param companyId 单位ID
     * @return java.util.List<top.tangyh.lamp.model.entity.base.SysOrg>
     * @author tangyh
     * @date 2022/9/29 11:18 PM
     * @create [2022/9/29 11:18 PM ] [tangyh] [初始创建]
     */
    List<BaseOrg> findDeptByCompany(Long companyId);

    /**
     * 查询单位和部门信息
     *
     * @return top.tangyh.lamp.oauth.vo.result.OrgResultVO
     * @author tangyh
     * @date 2022/9/15 2:37 PM
     * @create [2022/9/15 2:37 PM ] [tangyh] [初始创建]
     */
    OrgResultVO findCompanyAndDept();


    /**
     * 注册
     *
     * @param register 注册
     * @return
     */
    String registerByMobile(RegisterByMobileVO register);

    /**
     * 注册
     *
     * @param register 注册
     * @return
     */
    String registerByEmail(RegisterByEmailVO register);
}
