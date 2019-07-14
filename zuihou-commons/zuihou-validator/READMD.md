# 说明
## 测试代码请查看： zuihou-authortiy-controller -> test/*ValidateController

## 官方对一下3种 入参类型 的请求都支持校验
- 1、普通参数类型 （详见：ParamValidateController）
```
@Validated
public class ParamValidateController {
    @GetMapping("/requestParam/get1")
    public String paramGet1(@NotEmpty(message = "不能为空")
                            @RequestParam(value = "code", required = false) String code) {
        return "一定要在类上面写@Validated注解";
    }
}
```
- 2、对象参数 （详见：ObjValidateController）
```
    @GetMapping("/obj/get3")
    public String objGet3(@Validated InnerDTO data) {
        return "只有参数上有@Validated 可以验证";
    }
```
- 3、@RequestBody 格式的对象参数 （详见：BodyValidateController）
```
    @PostMapping("/post6")
    public String bodyPost6(@Validated @RequestBody HiberDTO data) {
        return "只有参数上有@Validated， 可以验证 ";
    }
```

## 但由于对hibernate-validate的一些机制不熟悉，目前只能获取第二和第三种类型的入参校验规则
