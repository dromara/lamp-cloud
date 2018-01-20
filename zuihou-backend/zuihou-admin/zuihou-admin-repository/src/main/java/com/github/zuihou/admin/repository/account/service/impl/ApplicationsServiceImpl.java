package com.github.zuihou.admin.repository.account.service.impl;

import com.github.zuihou.admin.entity.account.po.Applications;
import com.github.zuihou.admin.repository.account.dao.ApplicationsMapper;
import com.github.zuihou.admin.repository.account.example.ApplicationsExample;
import com.github.zuihou.admin.repository.account.service.ApplicationsService;
import com.github.zuihou.admin.utils.UUIDUtils;
import com.github.zuihou.base.dao.BaseDao;
import com.github.zuihou.base.service.impl.BaseServiceImpl;
import com.github.zuihou.commons.constant.DeleteStatus;
import com.github.zuihou.commons.constant.EnableStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:10
 */
@Service
public class ApplicationsServiceImpl extends BaseServiceImpl<Long, Applications, ApplicationsExample> implements ApplicationsService {
    @Autowired
    private ApplicationsMapper applicationsMapper;

    @Override
    protected BaseDao<Long, Applications, ApplicationsExample> getDao() {
        return applicationsMapper;
    }

    @Override
    public Applications getBySecret(String appId, String appSecret) {
        ApplicationsExample example = new ApplicationsExample();
        example.createCriteria().andAppIdEqualTo(appId).andAppSecretEqualTo(appSecret).andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal());
        return super.getUnique(example);
    }

    /**
     * 检测appid是否存在
     *
     * @param appId
     * @return 存在返回true 不存在返回false
     */
    public boolean check(String appId) {
        ApplicationsExample example = new ApplicationsExample();
        example.createCriteria().andAppIdEqualTo(appId);
        int count = applicationsMapper.countByExample(example);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 生成appid， 并且检测数据库中是否存在，存在则重新生成， 最多重新生成3次
     *
     * @return
     */
    private String generateAppId() {
        String appId = UUIDUtils.generateShortUuid();
        for (int i = 0; check(appId) && i < 3; i++) {
        }
        return appId;
    }

    /**
     * 保存
     * @param app
     * @return
     */
    @Override
    public Applications saveApp(Applications app) {
        app.setIsDelete(DeleteStatus.UN_DELETE.getVal());
        app.setIsEnable(EnableStatus.ENABLE.getVal());
        app.setAppId(generateAppId());
        app.setAppSecret(UUIDUtils.generateUuid());
        return super.save(app);
    }
}
