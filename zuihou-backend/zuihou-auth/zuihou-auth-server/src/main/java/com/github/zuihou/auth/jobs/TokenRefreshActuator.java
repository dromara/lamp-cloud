package com.github.zuihou.auth.jobs;

import com.github.zuihou.admin.entity.account.po.Admin;
import com.github.zuihou.admin.repository.account.dao.AdminMapper;
import com.github.zuihou.admin.repository.account.example.AdminExample;
import com.github.zuihou.auth.cache.TokenCache;
import com.github.zuihou.auth.dto.TokenDTO;
import com.github.zuihou.auth.service.AuthService;
import com.github.zuihou.commons.constant.DeleteStatus;
import com.github.zuihou.commons.constant.EnableStatus;
import com.github.zuihou.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TokenRefreshActuator {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AuthService authService;

    /**
     * 每1个小时刷新一次token
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void batchAppTokenRefresh() {
        log.info("-------定时刷新token-----");

        AdminExample example = new AdminExample();
        example.createCriteria().andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal())
                .andIsEnableEqualTo(EnableStatus.ENABLE.getVal());
        List<Admin> adminList = adminMapper.selectByExample(example);
        adminList.forEach((admin) -> {
            TokenDTO tokenVo = authService.login(admin.getUsername());
            log.info("--tokenVo----{}", JSONUtils.toJsonString(tokenVo));

            TokenCache.of(admin.getAppId(), admin.getUsername()).set(tokenVo.getToken());
        });


    }
}
