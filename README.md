## 关于灵悉  
此代码为灵悉项目服务端代码  

**[21/05/10]**  
1、发布动态时保存话题和@用户  
2、修复一些bug  

**[19/05/18]**  
1、引入Swagger2，生成接口文档，[服务启动后访问](http://localhost:8090/lingxi/swagger-ui.html)  
2、引入dom4j解析xml，加入简单的token校验  
3、诸多配置项，希望有所收获  
4、补充，提升fastjson版本号，原因低版本存在漏洞，详见[CVE-2017-18349](https://nvd.nist.gov/vuln/detail/CVE-2017-18349)  

**[18/09/29]**  
1、rss代码更新，同步app服务  
2、动态评论提醒  

Android端查看[灵悉-Android](https://github.com/happycao/lingxi-android)  

## 关于工程  
- JDK 1.8、Maven 3.2、MySQL 5.7
- 使用Spring Boot作为核心框架
- 使用MyBatis
- 使用通用Mapper
- 使用Swagger2
- sql文件位于/resources/lingxi-server.sql
- 本工程仅包含REST API
- Spring Boot内置Web容器，无需另外配置
- application.yml 中可配置服务端口port、服务路径servlet-path
- Application类 run 'Application' 即可启动项目
- 可通过mvn clean package打包，通过java -jar xxx.jar 启动服务
- 此版本对应Android APP 1.2.0 + 版本API，配合使用
- 资源服务rss文件上传之后，是通过nginx代理的静态资源，此处请自行搜索，不多赘述
- 没有其他要提醒的了，放心食用吧
- 心有灵犀一点通

## 反馈与建议  
- QQ：986417980  
- 交流群：387355490  
  

感谢阅读这份文档。  