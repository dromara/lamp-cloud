#!/bin/bash

ACTION=$1
MODULER=$2
PROFILES=$3

if [ "$ACTION" = "" ];
then
    echo -e "\033[0;31m 未输入操作名 \033[0m  \033[0;34m {start|stop|restart|status} \033[0m"
    exit 1
fi

if [ "$MODULER" = "" ];
then
    echo -e "\033[0;31m 未输入应用名 \033[0m"
    exit 1
fi
if [ "$PROFILES" = "" ];
then
    PROFILES="prod"
fi

JAVA_OPT="-server -Xms2G -Xmx2G -Xss512k -XX:MetaspaceSize=512M -XX:MaxMetaspaceSize=512M -XX:+UseG1GC"
JAVA_OPT=$JAVA_OPT" -Dspring.profiles.active=$PROFILES"
echo $JAVA_OPT


function start()
{
	count=`ps -ef | grep java | grep $MODULER | grep -v grep | wc -l`
	if [ $count != 0 ];then
		echo "$MODULER is running..."
	else
		echo "Start $MODULER success..."
		JENKINS_NODE_COOKIE=dontKillMe nohup java -jar $JAVA_OPT $MODULER.jar $MODULER > /dev/null 2>&1 &
	fi
}

function stop()
{
	echo "Stop $MODULER"
	pid=`ps -ef | grep java | grep $MODULER | grep -v grep | awk '{print $2}'`
	count=`ps -ef | grep java | grep $MODULER | grep -v grep | wc -l`

	if [ $count != 0 ];then
	    kill $pid
    	count=`ps -ef | grep java | grep $MODULER | grep -v grep | wc -l`

      pid=`ps -ef | grep java | grep $MODULER | grep -v grep | awk '{print $2}'`

      kill -2 $pid
      echo "Stop $MODULER Success"
	fi
}

function restart()
{
	stop
	sleep 2
	start
}

function status()
{
    count=`ps -ef | grep java | grep $MODULER | grep -v grep | wc -l`
    if [ $count != 0 ];then
        echo "$MODULER is running..."
    else
        echo "$MODULER is not running..."
    fi
}

case $ACTION in
	start)
	start;;
	stop)
	stop;;
	restart)
	restart;;
	status)
	status;;
	*)

	echo -e "\033[0;31m Usage: \033[0m  \033[0;34m sh  $0  {start|stop|restart|status}  {SpringBootJarName} \033[0m
\033[0;31m Example: \033[0m
	  \033[0;33m sh  $0  start esmart-test.jar \033[0m"
esac
