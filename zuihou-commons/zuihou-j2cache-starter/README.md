此为 spring boot2 版本！！
如下即可使用j2cache缓存方法
```
@Autowired
private CacheChannel cacheChannel;
```
在application.properties中支持指定j2cache配置文件，让你开发环境和生产环境分离
```
j2cache.config-location=/j2cache-${spring.profiles.active}.properties
```
如下两项配置在application.properties,可以开启对spring cahce的支持
```
j2cache.open-spring-cache=true  
spring.cache.type=GENERIC
```
如下两项配置在application.properties,可以设置spring cache是否缓存null值，默认是true
```
j2cache.allow-null-values=true
```
如下配置在application.properties,可以选择缓存清除的模式     
* 缓存清除模式
* active:主动清除，二级缓存过期主动通知各节点清除，优点在于所有节点可以同时收到缓存清除
* passive:被动清除，一级缓存过期进行通知各节点清除一二级缓存
* blend:两种模式一起运作，对于各个节点缓存准确性以及及时性要求高的可以使用（推荐使用前面两种模式中一种） 
```
j2cache.cache-clean-mode=passive
```
在j2cache.properties中配置,可以使用springRedis进行广播通知缓失效
```
j2cache.broadcast = net.oschina.j2cache.cache.support.redis.SpringRedisPubSubPolicy
```
在j2cache.properties中配置,使用springRedis替换二级缓存
```
j2cache.L2.provider_class = net.oschina.j2cache.cache.support.redis.SpringRedisProvider
j2cache.L2.config_section = redis (如果要使用lettuce客户端请配置为lettuce)
```
在application.properties中支持redis客户端
* jedis
* lettuce
```
j2cache.redis-client=jedis
```
在application.properties中支持关闭二级缓存
```
j2cache.l2-cache-open=false（默认开启）
```



## 场景1： 开发环境，只开启1级缓存（内存缓存）, 通知使用 jgroups, 不连redis 
### 注意： 该场景下无需连接 redis
1. 引入pom
```
    <dependency>
        <groupId>org.jgroups</groupId>
        <artifactId>jgroups</artifactId>
        <version>3.6.15.Final</version>
        <scope>provided</scope>
    </dependency>
```
2. 修改nacos中 redis.yml
```
zuihou:
  redis:
    ip: 127.0.0.1
    port: 16379
    password: SbtyMveYNfLzTks7H0apCmyStPzWJqjy

spring:
  cache:
    type: GENERIC

j2cache:
  open-spring-cache: true
  cache-clean-mode: passive
  allow-null-values: true
  redis-client: lettuce
  l2-cache-open: false   # 关闭2级缓存
  broadcast: jgroups     # 关闭2级缓存后，使用jgroups广播，就无需连接redis   
  L1:
    provider_class: caffeine
  L2:
    provider_class: net.oschina.j2cache.cache.support.redis.SpringRedisProvider
    config_section: lettuce
  sync_ttl_to_redis: true
  default_cache_null_object: false
  serialization: fst
caffeine:
  properties: /j2cache/caffeine.properties   # 这个配置文件需要放在项目中

```

## 场景2： 正式环境 开启2级缓存，1级缓存用： caffeine 2级缓存用： SpringRedisProvider  广播用：SpringRedisPubSubPolicy
```
zuihou:
  redis:
    ip: 127.0.0.1
    port: 16379
    password: SbtyMveYNfLzTks7H0apCmyStPzWJqjy

# redis 通用配置
spring:
  cache:
    type: GENERIC

j2cache:
  open-spring-cache: true
  cache-clean-mode: passive
  allow-null-values: true
  redis-client: lettuce
  l2-cache-open: true
  #以下来自j2cache.properties
  broadcast: net.oschina.j2cache.cache.support.redis.SpringRedisPubSubPolicy
  L1:
    provider_class: caffeine
  L2:
    provider_class: net.oschina.j2cache.cache.support.redis.SpringRedisProvider
    config_section: lettuce
  sync_ttl_to_redis: true
  default_cache_null_object: false
  serialization: fst
caffeine:
  properties: /j2cache/caffeine.properties   # 这个配置文件需要放在项目中
lettuce:
  mode: single
  namespace:
  storage: generic
  channel: j2cache
  scheme: redis
  hosts: ${zuihou.redis.ip}:${zuihou.redis.port}
  password: ${zuihou.redis.password}
  database: 0
  sentinelMasterId:
  maxTotal: 100
  maxIdle: 10
  minIdle: 10
  timeout: 10000
```


## 场景3： 正式环境 开启2级缓存，1级缓存用： caffeine 2级缓存用： lettuce  广播用：lettuce
```
zuihou:
  redis:
    ip: 127.0.0.1
    port: 16379
    password: SbtyMveYNfLzTks7H0apCmyStPzWJqjy

# redis 通用配置
spring:
  cache:
    type: GENERIC

j2cache:
  open-spring-cache: true
  cache-clean-mode: passive
  allow-null-values: true
  redis-client: lettuce
  l2-cache-open: true
  #以下来自j2cache.properties
  broadcast: lettuce
  L1:
    provider_class: caffeine
  L2:
    provider_class: lettuce
    config_section: lettuce
  sync_ttl_to_redis: true
  default_cache_null_object: false
  serialization: fst
caffeine:
  properties: /j2cache/caffeine.properties   # 这个配置文件需要放在项目中
lettuce:
  mode: single
  namespace:
  storage: generic
  channel: j2cache
  scheme: redis
  hosts: ${zuihou.redis.ip}:${zuihou.redis.port}
  password: ${zuihou.redis.password}
  database: 0
  sentinelMasterId:
  maxTotal: 100
  maxIdle: 10
  minIdle: 10
  timeout: 10000
```


