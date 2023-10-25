SERVICE=${serviceName-"bms"}
LOG_FILE=${LOG_FILE-"/tmp/logs"}
JAVA_OPTS=${JAVA_OPTS-"-Dfile.encoding=UTF-8 -Dsun.jun.encoding=UTF-8 -Dio.netty.leakDetectionLevel=advanced -Djava.security.egd=file:/dev/./urandom"}
# 在docker容器中设置env变量
echo "====== SERVICE: $SERVICE, LOG_FILE: $LOG_FILE, JAVA_OPTS: $JAVA_OPTS"
mkdir -p $LOG_FILE
echo "====== java -server -Dname=$SERVICE $JAVA_OPTS -jar /app.jar  > /dev/null 2>&1 &"
nohup java -server -Dname=$SERVICE $JAVA_OPTS -jar /app.jar  > /dev/null 2>&1 &
echo $! > $LOG_FILE/pid.txt