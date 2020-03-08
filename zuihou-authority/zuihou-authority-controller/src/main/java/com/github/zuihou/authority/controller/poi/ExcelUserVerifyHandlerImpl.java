package com.github.zuihou.authority.controller.poi;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import cn.hutool.core.util.StrUtil;
import com.github.zuihou.authority.dto.auth.UserExcelVO;
import com.github.zuihou.authority.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExcelUserVerifyHandlerImpl implements IExcelVerifyHandler<UserExcelVO> {

    @Autowired
    private UserService userService;

    @Override
    public ExcelVerifyHandlerResult verifyHandler(UserExcelVO obj) {
        StringBuilder builder = new StringBuilder();
        boolean bool = true;
        if (StrUtil.isEmpty(obj.getAccount())) {
            builder.append("账号不能为空");
            bool = false;
        } else {
            boolean check = userService.check(obj.getAccount());
            if (check) {
                builder.append(String.format("账号%s重复", obj.getAccount()));
                bool = false;
            }
        }
        return new ExcelVerifyHandlerResult(bool, builder.toString());
    }

}
