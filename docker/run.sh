SERVICE=${serviceName-"bms"}
LOGDIR=${LOGDIR-"/tmp/logs"}
JAVA_OPTS=${JAVA_OPTS-"-Dfile.encoding=UTF-8 -Dsun.jun.encoding=UTF-8 -Dio.netty.leakDetectionLevel=advanced -Djava.security.egd=file:/dev/./urandom"}
# 在docker容器中设置env变量
echo "====== SERVICE: $SERVICE, LOGDIR: $LOGDIR, JAVA_OPTS: $JAVA_OPTS"
nohup java -server -Dname=$SERVICE $JAVA_OPTS -jar /app.jar  > /dev/null 2>&1 &
echo $! > $LOGDIR/pid.txt