CentOS7.x 安装教程， centos6 安装可能与此不同
使用 zuihou 帐号安装。某些文件夹没有权限的， 需要sudo授权

#1,  安装机器
| 软件              |          机器          |   系统   |
| :--------------- | :------------------: | :----: |
| Tracker          | 192.168.65.129:22122 | CentOS |
| FashDHT          | 192.168.65.129:22122 | CentOS |
| Group1-Storage11 | 192.168.65.130:23000 | CentOS |

#2,所需安装包
* db-6.2.32.tar.gz
* fastdht-master.zip
* libfastcommon-master.zip
* FastDFS_v5.05.tar.gz
* fastdfs-nginx-module_v1.16.tar.gz
* ngx_cache_purge-2.1.tar.gz  (可选)
* nginx-1.10.3.tar.gz

#3，文件夹初始化: 
* 配置tracker所需的base_path:
    + 192.168.65.129机子上创建 /home/zuihou/fastdfs/tracker/
* 配置storage所需的日志目录:
    + 192.168.65.130机子上创建/home/zuihou/fastdfs/storage/logs
* 配置storage所需的存储文件目录:
    + 192.168.65.130机子上创建/home/zuihou/fastdfs/storage/data

#4, 安装libfastcommon-1.0.7.zip:
```
 unzip libfastcommon-master.zip
 sudo mv libfastcommon-master /usr/local/src/
 cd /usr/local/src/libfastcommon-master
 ./make.sh
 sudo ./make.sh install
 
 sudo ln -s /usr/lib64/libfastcommon.so /usr/local/lib/libfastcommon.so
 sudo ln -s /usr/lib64/libfastcommon.so /usr/lib/libfastcommon.so
 sudo ln -s /usr/lib64/libfdfsclient.so /usr/local/lib/libfdfsclient.so   # 未安装
 sudo ln -s /usr/lib64/libfdfsclient.so /usr/lib/libfdfsclient.so         # 未安装
 
```

#5,安装FastDFS
```
 sudo mv FastDFS /usr/local/src/
 cd /usr/local/src/FastDFS
 ./make.sh
 sudo ./make.sh install
 
 cd /etc/fdfs/
 sudo cp client.conf.sample client.conf
 sudo cp storage.conf.sample storage.conf
 sudo cp tracker.conf.sample tracker.conf
 sudo chown -R zuihou:zuihou /etc/fdfs/
```

#6,配置Tracker，配置storage:
```
vim storage.conf
disabled=false            #启用配置文件
group_name=group1
port=23000     #设置storage的端口号，默认是23000，同一个组的storage端口号必须一致
base_path=/home/zuihou/fastdfs/storage
store_path_count=1   #存储路径个数，需要和store_path个数匹配
store_path0=/home/zuihou/fastdfs/storage
tracker_server=123.249.76.119:22122 #tracker服务器的IP地址和端口号 这里若有多台，则配置多个  不能配置127.0.0.1
http.server_port=7888

vim tracker.conf
base_path=/home/zuihou/fastdfs/tracker
reserved_storage_space = 10%  # 注意这里，若虚拟机硬盘小于4g，则需要手动指定小一点的空间， 否则启动会报错说硬盘空间不足
http.server_port=6080

vim client.conf
base_path=/home/zuihou/fastdfs/tracker
tracker_server=123.249.76.119:22122  # 这里若有多台，则配置多个 不能配置127.0.0.1
http.tracker_server_port=6080

```
##启动命令：
    /usr/bin/fdfs_trackerd  /etc/fdfs/tracker.conf  restart
    /usr/bin/fdfs_storaged  /etc/fdfs/storage.conf  restart

启动完毕后，可以通过以下两个方法查看tracker是否启动成功:

* a. netstat -unltp|grep fdfs，查看`22122/23000`端口监听情况 
* b. 通过以下命令查看tracker的启动日志，看是否有错误: `tail -100f  /home/zuihou/fastdfs/tracker/logs/trackerd.log`
* c. 通过以下命令查看storage的启动日志，看是否有错误: `tail -100f  /home/zuihou/fastdfs/storage/logs/storaged.log`

如果启动没有问题，可以通过以下步骤，将tracker的启动添加到服务器的开机启动中:
* a. 打开文件 vi /etc/rc.d/rc.local
* b. 将如下命令添加到该文件中: `/usr/bin/fdfs_trackerd  /etc/fdfs/tracker.conf  restart`
* b. 将如下命令添加到该文件中: `/usr/bin/fdfs_storaged  /etc/fdfs/storage.conf  restart`

也可以创建服务：
```
ln -s /usr/bin/fdfs_trackerd /usr/local/bin
ln -s /usr/bin/stop.sh /usr/local/bin
ln -s /usr/bin/restart.sh /usr/local/bin

ln -s /usr/bin/fdfs_storaged /usr/local/bin
```
通过命令启动Tracker服务器：`service fdfs_trackerd start`
通过命令启动Storage服务器：`service fdfs_storaged start`

启动storage报错：
[2014-05-30 16:22:21] ERROR - file: fdht_client/fdht_func.c, line: 361, invalid group count: 0 <= 0!
[2014-05-30 16:22:21] CRIT - exit abnormally!

check_file_duplicate=0  # 先将这个设置成0， 在安装了FastDHT后，才将它设置为1，否则启动会报错


#7,初步测试
测试时需要设置客户端的配置文件，编辑/etc/fdfs目录下的client.conf 文件，打开文件后依次做以下修改：
```
base_path=/home/zuihou/fastdfs/tracker #tracker服务器文件路径
tracker_server=123.249.76.119:22122 #tracker服务器IP地址和端口号
http.tracker_server_port=6080 # tracker 服务器的 http 端口号，必须和tracker的设置对应起来
```
执行：`/usr/bin/fdfs_upload_file  /etc/fdfs/client.conf  /home/zuihou/tools/fastdfs/logo.jpg`
一切成功的话，会返回一个url，但你访问：
http://123.249.76.119:6080/group1/M00/00/00/e_lMd1kgST-AQAodAAATIDVcVxc531.jpg
却无法访问，因为FastDFS目前已不支持http协议，我们在FastDFS 5.0.5的版本更新日志中可以看到这样一条信息： 
​    
    * remove embed HTTP support
所以余大提供了nginx上使用FastDFS的模块fastdfs-nginx-module

#8,安装nginx和fastdfs-nginx-module模块 
查看nginx安装了那些模块： nginx/sbin/nginx -V
```
cd /usr/local/src/fastdfs-nginx-module
vim config # 修改如下2行代码
CORE_INCS="$CORE_INCS /usr/include/fastdfs /usr/include/fastcommon/"
CORE_LIBS="$CORE_LIBS -L/usr/lib64 -lfastcommon -lfdfsclient"

cd /usr/local/src/nginx-1.10.3
./configure --user=zuihou --group=zuihou --prefix=/usr/local/nginx --add-module=/usr/local/src/fastdfs-nginx-module/src --add-module=/usr/local/src/ngx_cache_purge-2.1
./configure --user=zuihou --group=zuihou --prefix=/usr/local/nginx --add-module=/usr/local/src/ngx_cache_purge-2.1
make
sudo make install
vim /usr/local/nginx/conf/nginx.conf 
include ./fastdfs.conf; # 加入

vim /usr/local/nginx/conf/fastdfs.conf  # 加入如下代码,自行修改ip
tcp_nopush     on;

server_names_hash_bucket_size 128;
client_header_buffer_size 32k;
large_client_header_buffers 4 32k;

client_max_body_size 300m;

proxy_redirect off;
proxy_set_header Host $http_host;
proxy_set_header X-Real-IP $remote_addr;
proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

proxy_connect_timeout 90;
proxy_send_timeout 90;
proxy_read_timeout 90;

proxy_buffer_size 16k;
proxy_buffers 4 64k;
proxy_busy_buffers_size 128k;
proxy_temp_file_write_size 128k;

proxy_cache_path /opt/cache/nginx/proxy_cache levels=1:2
keys_zone=http-cache:500m max_size=10g inactive=30d;
proxy_temp_path /opt/cache/nginx/proxy_cache/tmp;

upstream fdfs_group1 {
        server 123.249.76.119:7888 weight=1 max_fails=2 fail_timeout=30s;
        #server 192.168.65.128:18080 weight=1 max_fails=2 fail_timeout=30s;
}
upstream fdfs_group2 {
        server 123.249.76.119:7888 weight=1 max_fails=2 fail_timeout=30s;
        #server 192.168.65.128:18080 weight=1 max_fails=2 fail_timeout=30s;
}


server {
        listen 6080;
        #server_name www.zuihou.com;  # Here to modify the domain name by 
        server_name 123.249.76.119;
        server_tokens off;

        location /group1/M00 {
                access_log /usr/local/nginx/logs/fastdfsstoragegroup1.access.log main;
            proxy_next_upstream http_502 http_504 error timeout invalid_header;
            proxy_cache http-cache;
            proxy_cache_valid  200 304 12h;
            proxy_cache_key $uri$is_args$args;
            proxy_pass http://fdfs_group1;
            expires 30d;
        }

        location /group2/M00 {
                access_log /usr/local/nginx/logs/fastdfsstoragegroup2.access.log main;
            proxy_next_upstream http_502 http_504 error timeout invalid_header;
            proxy_cache http-cache;
            proxy_cache_valid  200 304 12h;
            proxy_cache_key $uri$is_args$args;
            proxy_pass http://fdfs_group2;
            expires 30d;
        }

        #location ~/purge(/.*) {
        #    allow 127.0.0.1;
        #    allow 192.168.224.0/24;
        #    deny all;
        #    proxy_cache_purge http-cache $1$is_args$args;
        #}

        location ~/group1/M00{
                access_log /usr/local/nginx/logs/fastdfs.access.log main;
                root    /home/zuihou/fastdfs/storage/data;
                ngx_fastdfs_module;

        }

}

# 复制文件过来
cp -r /usr/local/src/fastdfs-nginx-module/src/mod_fastdfs.conf /etc/fdfs/
cp -r /usr/local/src/FastDFS/conf/http.conf /etc/fdfs/
cp -r /usr/local/src/FastDFS/conf/mime.types /etc/fdfs/

```
vim /etc/fdfs/mod_fastdfs.conf
```
base_path=/home/zuihou/fastdfs/storage
tracker_server=123.249.91.119:22122 #tracker服务器的IP地址以及端口号
storage_server_port=23000 #storage服务器的端口号
url_have_group_name = true #文件 url 中是否有 group 名
store_path0=/home/zuihou/fastdfs/storage # 存储路径
group_count = 1 #设置组的个数，事实上这次只使用了group1

```
建立软连接:
`ln  -s  /home/zuihou/fastdfs/storage/data  /home/zuihou/fastdfs/storage/M00`

启动nginx：
/usr/local/nginx/sbin/nginx
或者 重启
/usr/local/nginx/sbin/nginx -s reload


#测试上传
1. 打开 /etc/fdfs 文件夹，编辑 client.conf 文件，编辑内容如下:
  a. base_path=/home/zuihou/fastdfs/tracker      #存放路径
  b. tracker_server=123.249.76.119:22122          #tracker服务器IP地址和端口号
  c. http.tracker_server_port=6080              #tracker服务器的http端口号，注意，这个配置在fastdfs5.0.5中已经没有用了

2. 模拟上传文件，执行如下命令: /usr/bin/fdfs_upload_file  /etc/fdfs/client.conf  /home/zuihou/tools/fastdfs/logo.jpg

使用浏览器访问返回的url: http://123.249.76.119:6080/group1/M00/00/00/wKjgGlVYgi6AAv3tAAAADv4ZzcQ572.txt

也可以直接访问文件所在的storage: http://123.249.76.119:6080/group1/M00/00/00/wKjgGlVYgi6AAv3tAAAADv4ZzcQ572.txt

上述如果访问成功，会在 tracker 123.249.76.119 的 /opt/cache中产生缓存

我们还可以通过在url中添加purge清除缓存，例如: http://123.249.76.119:6080/purge/group1/M00/00/00/wKjgGlVYgi6AAv3tAAAADv4ZzcQ572.txt

#9, 安装db-6.2.32
```
 mv db-6.2.32 /usr/local/src
 cd /usr/local/src/db-6.2.32/build_unix
 ../dist/configure --prefix=/usr/local/db-6.2.32
 make
 make install
```

#10, 安装FastDHT
```
 mv fastdht-master /usr/local/src
 cd /usr/local/src/fastdht-master
 vim make.sh
 CFLAGS='-Wall -D_FILE_OFFSET_BITS=64 -D_GNU_SOURCE -I/usr/local/db-6.2.32/include/ -L/usr/local/db-6.2.32/lib/'
 ./make.sh
 ./make.sh install
```
 配置FastDHT:
```
vim /etc/fdht/fdht_servers.conf
group_count = 1
group0 = 123.249.76.119:11411
```
配置fdhtd.conf文件
```
vim /etc/fdht/fdhtd.conf
port=11411
base_path=/home/zuihou/fastdht （该目录必须是已经存在的）
cache_size = 32MB
#include /etc/fdhtd/fdht_servers.conf -> (本行前有#表示打开，如果想关闭此选项，则应该为##开头)
```
配置storaged.conf文件
```
vim /etc/fdfs/storage.conf
#是否检测上传文件已经存在。如果已经存在，则建立一个索引链接以节省磁盘空间 
check_file_duplicate=1 
#当上个参数设定为1时 ， 在FastDHT中的命名空间
key_namespace=FastDFS 
#长连接配置选项，如果为0则为短连接 1为长连接 
keep_alive=1 
#此处特别需要注意配置
#include /etc/fdht/fdht_servers.conf
```

启动
```
fdhtd /etc/fdht/fdhtd.conf 
fdhtd /etc/fdht/fdhtd.conf restart
```
###出错解决方案:
fdhtd: error while loading shared libraries: libdb-6.2.so: cannot open shared object file: No such file or directory
* sudo cp /usr/local/db-6.2.32/lib/libdb-6.2.so /usr/lib/
* 编辑/etc/ld.so.conf文件，在新的一行中加入库文件所在目录；
* 运行ldconfig，以更新/etc/ld.so.cache文件；


#常用命令：

| 命令                                       | 解释                                       |
| :--------------------------------------- | ---------------------------------------- |
| service fdfs_trackerd start/stop/restart | tracker启动/停止/重启                          |
| service fdfs_storaged start/stop/restart | storage启动/停止/重启                          |
| /usr/bin/fdfs_trackerd  /etc/fdfs/tracker.conf  start/stop/restart | tracker启动/停止/重启                          |
| /usr/bin/fdfs_storaged  /etc/fdfs/storage.conf  start/stop/restart | storage启动/停止/重启                          |
| /usr/bin/fdfs_monitor /etc/fdfs/storage.conf | 看storage服务器是否已经登记到 tracker服务器(123.249.76.119  ACTIVE代表已登记成功) |
| /usr/bin/fdfs_upload_file  /etc/fdfs/client.conf  /home/zuihou/tools/fastdfs/logo.jpg | 测试上传                                     |


​    