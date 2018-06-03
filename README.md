## 关于灵悉
此代码为灵悉项目服务端代码

Android端查看[灵悉-Android](https://github.com/happycao/lingxi-android)

## 关于工程
- JDK 1.8、Maven 3.2、MySQL 5.7
- 使用Spring Boot作为核心框架
- 使用MyBatis
- 使用通用Mapper
- sql文件位于/resources/lingxi-server.sql
- 本工程仅包含REST API
- Spring Boot内置Web容器，无需另外配置
- application.yml 中可配置服务端口port、服务路径servlet-path
- Application类 run 'Application' 即可启动项目
- 可通过mvn package spring-boot:repackage打包，通过java -jar xxx.jar 启动服务
- 此版本对应Android APP 1.0.7 版本API，配合使用
- Android中资源服务使用阿里云oss，在此版服务中并没有，采用上传到本地的方式
- 没有其他要提醒的了，放心食用吧
- 心有灵犀一点通

## 反馈与建议
- 微博：[种下一枚种子](http://weibo.com/374845241)
- QQ：986417980
- 交流群：387355490

感谢阅读这份文档。