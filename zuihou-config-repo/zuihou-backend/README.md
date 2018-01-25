注意这里文件夹的命名！一定要和服务的spring.application.name 一致！！！

- application.yml：
    - 缺省的配置文件
- application-dev.yml：
    - 开发环境的配置文件
- application-prod*.yml：
    - 生产环境的配置文件

原则上，生产环境和开发环境有区别的属性（如，mysql/redis等的连接信息），
从`application.yml`文件分别复制到`application-dev.yml`、 
`application-prod*.yml` ，然后修改各自的值即可。
