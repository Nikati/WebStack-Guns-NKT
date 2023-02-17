# Docker image for WebStack-Guns-NKT file run
# VERSION 0.0.1
# Author: maxinchun
# 基础镜像使用java
FROM java:8u72
# 作者
MAINTAINER maxinchun <maxinchun0215@qq.com>

# 设置字符编码
ENV LANG=C.UTF-8
# 设置时区
ENV TZ=Asia/Shanghai
# 项目的环境变量
ENV IMAGE_UPLOAD_PATH=/root/webstack/file
ENV DB_HOST=127.0.0.1
ENV DB_PORT=3306
ENV DB_DATABASE=webstack
ENV DB_USERNAME=root
ENV DB_PASSWORD=root

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /root/webstack/file

# 将jar包额配置文件添加到容器中
ADD target/Webstack-Guns-nkt-1.0.jar /root/webstack/webstack-guns-nkt.jar
ADD src/main/resources/application-example.yml /root/webstack/application.yml
ADD src/main/webapp/static/tmp/* /root/webstack/file/

# 工作目录
WORKDIR /root/webstack

# 暴露端口, 容器内部端口
EXPOSE 8000

# 运行jar包
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","webstack-guns-nkt.jar"]