#!/bin/bash

command -v git >/dev/null 2>&1 || { echo >&2 -e "\033[31m[ERROR] required git but it's not installed. To: https://git-scm.com/ \033[0m"; exit 1; }
command -v mvn >/dev/null 2>&1 || { echo >&2 -e "\033[31m[ERROR] required git maven it's not installed. To: https://maven.apache.org/ \033[0m"; exit 1; }
command -v docker >/dev/null 2>&1 || { echo >&2 -e "\033[31m[ERROR] required docker but it's not installed. To: https://www.docker.com/ \033[0m"; exit 1; }

# 项目名
serviceName=bms

# docker仓库地址
harbor="docker.io/maship"
echo -e "\033[32m----- build $serviceName start -----\033[0m"

#gid=$(git rev-parse --short=7  HEAD)
git=latest
echo "short commit id: $gid"

echo -e "\033[32m----- start mvn package -----\033[0m"
mvn clean package -DskipTests || { echo >&2 -e "\033[31m[ERROR] mvn package failed, exit \033[0m"; exit 1; }
mkdir docker/apps
cp target/$serviceName.jar docker/apps/app.jar

# docker 构建镜像
cd docker
echo -e "\033[32m----- building image[$harbor/$serviceName:$gid] ... -----\033[0m"
docker build -t $harbor/$serviceName:$gid .

rm -rf apps

echo -e "\033[32m----- build image[$harbor/$serviceName:$gid] successful -----\033[0m"