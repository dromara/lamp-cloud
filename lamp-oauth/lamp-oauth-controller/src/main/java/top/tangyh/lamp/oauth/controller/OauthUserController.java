package top.tangyh.lamp.oauth.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Hidden;
import top.tangyh.basic.annotation.base.IgnoreResponseBodyAdvice;
import top.tangyh.lamp.authority.service.auth.UserService;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * 用户
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
@Tag(name = "用户")
@AllArgsConstructor
@Hidden
public class OauthUserController {
    private final UserService userService;

    /**
     * 根据用户id，查询用户权限范围
     *
     * @param id 用户id
     */
    @Operation(summary = "查询用户权限范围", description = "根据用户id，查询用户权限范围")
    @GetMapping(value = "/ds/{id}")
    @IgnoreResponseBodyAdvice
    public Map<String, Object> getDataScopeById(@PathVariable("id") Long id) {
        return userService.getDataScopeById(id);
    }

}
