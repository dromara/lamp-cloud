# 说明
## 测试代码请查看： zuihou-authortiy-controller -> test/*ValidateController

## 主要针对3种 入参类型 的请求做了测试
- 普通参数类型 （详见：ParamValidateController）
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
- 对象参数 （详见：ObjValidateController）
```
    @GetMapping("/obj/get3")
    public String objGet3(@Validated InnerDTO data) {
        return "只有参数上有@Validated 可以验证";
    }
```
- @RequestBody 格式的对象参数 （详见：BodyValidateController）
```
    @PostMapping("/post6")
    public String bodyPost6(@Validated @RequestBody HiberDTO data) {
        return "只有参数上有@Validated， 可以验证 ";
    }
```
