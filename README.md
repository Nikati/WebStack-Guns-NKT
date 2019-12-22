# WebStack-Guns-NKT
 ❤️ 一个开源的网址导航网站项目，后台springboot 在线DEMO https://zywhs.club/

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

