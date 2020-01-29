# 远程数据自动注入器

# 解决问题
跨库、库表、库服务 关联数据自动注入。

# 场景举例
1. 业务表中存放了用户表的 用户ID， 但在 页面回显（分页，查看页面）时，需要把用户姓名、用户头像等显示出来。
2. 业务表中存放了 数据字典表的 编码或者ID， 显示时需要回显出来

# 常见的解决方案探讨：
1. 冗余字段： 在业务表冗余想要回显字段
    - 优点：回显简单， 查询快
    - 缺点：更新用户信息后，出现数据不一致。
    
2. 关联查询，对于在同一个数据库的表可以关联查询后，实时回显
    - 优点：数据绝对一致，
    - 缺点：关联查询慢、业务复杂时会关联N张表、sql或代码变复杂且会出现大量重复性代码
    
3. 前端缓存部分数据，回显时，前端根据业务接口返回的id自己去控制回显。
    比如：用户在登陆时，将系统会常用的所有枚举类型，组织，用户，菜单等信息查询后，缓存在前端，需要回显的业务，自己根据 字典id、组织id、用户id等去查缓存
    - 优点：业务方逻辑简单，只需要读取id即可，无需考虑别的
    - 缺点：大量数据缓存在前端不安全、前端缓存的数据在后端修改后无法同步更新、前端工作量重
    
4. 前端不缓存数据，回显时，前端先去查需要回显的数据列表，在查业务数据。
    - 优点：业务方逻辑简单，只需要读取id即可，无需考虑别的
    - 缺点：前端工作量重、前端请求较多
    
5. 其他没想到的方案

# 本系统采用的解决方案
1. 对于安全性要求不高的数据、且能够忍受数据不同步的数据采用 `常见的解决方案3`。 如，本系统中用到的 枚举类型，会在用户登录时，一次性拉取到前端缓存。
2. 对于数据字典的回显方式，采用 `常见的解决方案4`。 如，地区表中的 行政区划 字段，就是读取的数据字典， 在地区表中，只会存一个字典code，前端回显时，实时去拉取数据字典的列表来遍历回显。
3. 对于用户信息、组织信息、其他业务表信息的回显，采用 `常见的解决方案2`。 

但上述解决方案都有缺点，本系统如何解决呢？
- 方案1：本系统中不使用这种方案。 解决方案：可以做一个监听器，监听到用户修改name后，异步的去修改业务表存存放的name字段
- 方案2：采用 zuihou-injection-starer 解决
- 方案3：枚举类型 数据无关紧要，泄露了也无价值，不存在安全性问题。且不存在数据不同步的情况。 只是前端稍微增加了工作量，可以忍受
- 方案4：只是前端稍微增加了工作量，可以忍受

# 实现方式   
1. 添加依赖 
```xml
<dependency>
    <groupId>com.github.zuihou</groupId>
    <artifactId>zuihou-injection-starer</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>        
```
2. application.yml配置
```yaml
zuihou:
  injection:
    # 是否启用 远程数据 手动注入
    enabled: true
    # 是否启用 远程数据 注解注入 
    aop-enabled: true
```
3. 在需要注入的对象上添加注解： @InjectionField
```java
public class Area {
    private String name;

    /**
     * 需要注入 用户名
     */
    @InjectionField(api = "userServiceImpl", method = "findUser")
    private RemoteData<Long, User> createUser;
    
     /**
      * 需要注入 用户名
      */
    @InjectionField(api = "dictionaryServiceImpl", method = "findDictionaryItem")
    private RemoteData<String, String> city;

}
```
4. 手动注入：
```java
public class Controller{
    
    @Autowired
    private InjectionCore injectionCore;
    
    @Test
    public void test(){
        Area area = Area.builder().createUser(3L).city("GUANGZHOU").build(); // 从数据库连查询出来    
        injectionCore.injection(area);
        log.info(area);

        Area area1 = Area.builder().createUser(3L).city("GUANGZHOU").build();     
        Area area2 = Area.builder().createUser(3L).city("GUANGZHOU").build();     
        List<Area> list = Arrays.asList(area1, area2); // 从数据库连查询出来
        injectionCore.injection(list);
        log.info(list);
    }

}
```
5. 自动注入：
```java
public class Controller{
    
    @Autowired
    private Service service;
    
    @Test
    public void test(){
        injectionCore.injection(service.get());
        log.info(area);
    
        injectionCore.injection(service.list());
        log.info(list);
    }

}
@org.springframework.stereotype.Service
public class Service{
    
    // 自动拦截这个注解
    @InjectionResult
    public Area get(){
        Area area = Area.builder().createUser(3L).city("GUANGZHOU").build(); // 从数据库连查询出来
        return area;
    }
    
    @InjectionResult
    public List<Area> list(){
        Area area1 = Area.builder().createUser(3L).city("GUANGZHOU").build();     
        Area area2 = Area.builder().createUser(3L).city("GUANGZHOU").build();     
        List<Area> list = Arrays.asList(area1, area2); // 从数据库连查询出来
        return list;
    }

}
```


