

# 部署 docker 组件
 
docker 是容器的运行环境，管理它的生命周期。

## 安装依赖包

```
yum install -y epel-release conntrack ipvsadm ipset jq iptables curl sysstat libseccomp && /usr/sbin/modprobe ip_vs
```


## 下载和分发 docker 二进制文件

到 [https://download.docker.com/linux/static/stable/x86_64/](https://download.docker.com/linux/static/stable/x86_64/) 页面下载最新发布包：

``` bash
mkdir -p /root/download
cd /root/download

wget https://download.docker.com/linux/static/stable/x86_64/docker-18.09.0.tgz
tar -xvf docker-18.09.0.tgz
```

分发二进制文件到所有 worker 节点：

``` bash
mkdir -p /opt/docker/bin

cp docker/*  /opt/docker/bin/
chmod +x /opt/docker/bin/*

vim /etc/profile
export PATH=/opt/docker/bin:$PATH

source /etc/profile

```

## 创建和分发 systemd unit 文件

``` bash
cd /root/download
cat > docker.service <<"EOF"
[Unit]
Description=Docker Application Container Engine
Documentation=http://docs.docker.io

[Service]
WorkingDirectory=/data/docker
Environment="PATH=/opt/docker/bin:/bin:/sbin:/usr/bin:/usr/sbin"
ExecStart=/opt/docker/bin/dockerd 
ExecReload=/bin/kill -s HUP $MAINPID
Restart=on-failure
RestartSec=5
LimitNOFILE=infinity
LimitNPROC=infinity
LimitCORE=infinity
Delegate=yes
KillMode=process

[Install]
WantedBy=multi-user.target
EOF

```
+ EOF 前后有双引号，这样 bash 不会替换文档中的变量，如 $DOCKER_NETWORK_OPTIONS；
+ dockerd 运行时会调用其它 docker 命令，如 docker-proxy，所以需要将 docker 命令所在的目录加到 PATH 环境变量中；
+ docker 需要以 root 用于运行；
+ docker 从 1.13 版本开始，可能将 **iptables FORWARD chain的默认策略设置为DROP**，从而导致 ping 其它 Node 上的 Pod IP 失败，遇到这种情况时，需要手动设置策略为 `ACCEPT`：

``` bash
$ sudo iptables -P FORWARD ACCEPT
```

并且把以下命令写入 `/etc/rc.local` 文件中，防止节点重启**iptables FORWARD chain的默认策略又还原为DROP**

``` bash
/sbin/iptables -P FORWARD ACCEPT
```

``` bash
cp docker.service  /etc/systemd/system/
```

## 配置和分发 docker 配置文件

使用国内的仓库镜像服务器以加快 pull image 的速度，同时增加下载的并发数 (需要重启 dockerd 生效)：

``` bash
cd /root/download
mkdir -p /data/docker/data
mkdir -p /data/docker/exec

cat > daemon.json <<EOF
{
    "registry-mirrors": [ "https://477njxek.mirror.aliyuncs.com", "https://registry.docker-cn.com/", "https://hub-mirror.c.163.com", "https://docker.mirrors.ustc.edu.cn"],
    "insecure-registries": [],
    "max-concurrent-downloads": 20,
    "live-restore": true,
    "max-concurrent-uploads": 10,
    "debug": true,
    "data-root": "/data/docker/data",
    "exec-root": "/data/docker/exec",
    "log-opts": {
      "max-size": "100m",
      "max-file": "5"
    }
}
EOF

```

分发 docker 配置文件到所有 work 节点：

``` bash
cd /root/download
mkdir -p  /etc/docker/

cp daemon.json /etc/docker/
```

## 启动 docker 服务

``` bash
# systemctl stop firewalld && systemctl disable firewalld
/usr/sbin/iptables -F && /usr/sbin/iptables -X && /usr/sbin/iptables -F -t nat && /usr/sbin/iptables -X -t nat
/usr/sbin/iptables -P FORWARD ACCEPT
systemctl daemon-reload && systemctl enable docker && systemctl restart docker
```
+ 关闭 firewalld(centos7)/ufw(ubuntu16.04)，否则可能会重复创建 iptables 规则；
+ 清理旧的 iptables rules 和 chains 规则；
+ 开启 docker0 网桥下虚拟网卡的 hairpin 模式;

## 检查服务运行状态

``` bash
systemctl status docker|grep Active
```

确保状态为 `active (running)`，否则查看日志，确认原因：

``` bash
$ journalctl -u docker
```
