package top.tangyh.lamp.system.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.base.R;
import top.tangyh.lamp.system.controller.system.domain.Server;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/6/29 2:24 PM
 * @create [2022/6/29 2:24 PM ] [tangyh] [初始创建]
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defServer")
@Tag(name = "服务监控")
public class DefServerController {
    @GetMapping()
    public R<Server> server() {
        Server server = new Server();
        server.copyTo();
        return R.success(server);
    }

}
