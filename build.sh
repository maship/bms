#!/bin/bash

command -v git >/dev/null 2>&1 || { echo >&2 "required git but it's not installed. To: https://git-scm.com/"; exit 1; }
command -v docker >/dev/null 2>&1 || { echo >&2 "required docker but it's not installed. To: https://www.docker.com/"; exit 1; }

# 项目名
serviceName=bms

# docker仓库地址
harbor="docker.io/maship"
echo "\033[32m----- build $serviceName start -----\033[0m"

gid=$(git rev-parse --short=7  HEAD)
echo "short commit id :[$gid]"

echo "\033[32m----- start mvn package -----\033[0m"
mvn clean package -DskipTests
mkdir docker/apps
cp target/$serviceName.jar docker/apps/app.jar

# docker 构建镜像
cd docker
echo "\033[32m----- building image[$harbor/$serviceName:$gid] ... -----\033[0m"
docker build -t $harbor/$serviceName:$gid .

rm -rf apps

echo "\033[32m----- build image[$harbor/$serviceName:$gid] successful -----\033[0m"