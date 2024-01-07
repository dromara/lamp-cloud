package top.tangyh.lamp.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.base.R;
import top.tangyh.lamp.base.entity.user.BaseEmployee;
import top.tangyh.lamp.base.service.BaseEmployeeTestService;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/9/20 11:33 AM
 * @create [2022/9/20 11:33 AM ] [tangyh] [初始创建]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/baseEmployeeController")
public class BaseEmployeeTestController {

    @Autowired
    private BaseEmployeeTestService baseEmployeeTestService;


    @PostMapping("/save")
    public R<Boolean> save(@RequestBody BaseEmployee baseEmployee) {
        // 拼接
        return R.success(baseEmployeeTestService.save(baseEmployee));
    }

    @GetMapping("/getById")
    public R<BaseEmployee> getById(@RequestParam Long id) {
        // 拼接
        return R.success(baseEmployeeTestService.getById(id));
    }

    @GetMapping("/get")
    public R<BaseEmployee> get(@RequestParam Long id) {
        // 不会拼接
        return R.success(baseEmployeeTestService.get(id));
    }
}
