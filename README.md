# WebStack-Guns-NKT
 ❤️ 一个开源的网址导航网站项目，后台springboot 在线DEMO https://zywhs.club/  （已停止运营）

一个开源的网址导航网站项目，具备完整的前后台，您可以拿来制作自己的网址导航。文件上传集成七牛云接口，前端懒加载。
QQ群：861993098

![首页](/screen/1.png)

## 运行

克隆代码：

```shell
git clone git@github.com:Nikati/WebStack-XML-Guns.git
```

编辑配置：

```
application.yml
```

```
上传文件路径，注意windows环境和linux环境：
file-upload-path
如需显示初始网站图标请把Webstack-Guns/src/main/webapp/static/tmp下的图片复制到上传文件路径
```

```
c:/tmp

数据库连接，用户名密码：
url
username
password
...
```

新建数据库(utf8mb4)，导入数据：

```shell
webstack.sql
```

maven打包或者IDE启动服务：

```shell
$ java -jar Webstack-Guns-nkt-1.0.jar
```

启动完成：http://127.0.0.1:8000

## docker 方式部署

### 下载镜像
```bash
docker pull codecly/webstack-guns-nkt-docker
```
要修改的配置主要是mysql数据库连接和图片的上传位置。
可以在 docker run -e 指定环境变量的方式，也可以使用 -v /path/to/application.yml:/root/webstack/config/application.yml 的方式映射配置文件

### 使用 docker run -e 方式设置环境变量
支持设置的环境变量有

环境变量名称|环境变量说明|默认值
--|--|--
IMAGE_UPLOAD_PATH|图片上传路径(容器中)|/root/webstack/file
DB_HOST|数据库主机|127.0.0.1
DB_PORT|数据库端口|3306
DB_DATABASE|数据库名称|webstack
DB_USERNAME|数据库用户名|root
DB_PASSWORD|数据库密码|root

示例：
```
docker run -itd \
    -e DB_DATABASE=webstack \
    -e DB_HOST=192.168.211.28 \
    --name webstack \
    -p 8000:8000 \
    codecly/webstack-guns-nkt-docker
```

### 使用 docker run -v 方式映射配置文件

首先在宿主机创建并修改配置相关文件 /path/to/config/application.yml , 示例文件[application-example.yml](./src/main/resources/application-example.yml)

主要修改：
```
guns.file-upload-path
spring.datasource.url
spring.datasource.username
spring.datasource.password
```

运行容器示例：
```
docker run -itd \
    -v /path/to/config:/root/webstack/config \
    -v /path/to/file=/root/webstack/file \
    --name webstack \
    -p 8000:8000 \
    codecly/webstack-guns-nkt-docker
```

## 使用

后台地址：http://domain/admin

默认用户：admin

默认密码：111111

在线demo: https://zywhs.club/

![主页](/screen/2.png)

![分类](/screen/3.png)

![网站](/screen/4.png)



## 感谢

前端设计：[**WebStackPage**](https://github.com/WebStackPage/WebStackPage.github.io)

后端参考：[**[jsnjfz]**](https://github.com/jsnjfz/WebStack-Guns)

