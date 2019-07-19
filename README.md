#基于springboot的博客网站
#使用的技术和参考资料如下
##参考资料
[spring文档](https://spring.io/guides)

[springboot](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)

[spring-mybatis](http://www.mybatis.org/spring/zh/index.html)

[springboot-mybatis](http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

[mybatis generator](http://www.mybatis.org/generator/)

[springMVC](https://spring.io/guides/gs/serving-web-content/)

[bootstrap](https://v3.bootcss.com/getting-started/)

[github](https://developer.github.com/apps/building-oauth-apps/)

[okhttp](https://square.github.io/okhttp/interceptors/)

[fastjson](https://www.w3cschool.cn/fastjson/fastjson-quickstart.html)

[lomok](https://www.projectlombok.org/)

[jQuery](https://api.jquery.com/)


##工具
[git](https://www.liaoxuefeng.com/wiki/896043488029600)

[idea](https://www.cnblogs.com/anyehome/p/8982348.html)

[Visual Paradigm](https://www.visual-paradigm.com/cn/download/community.jsp)

[postman](https://www.getpostman.com/)

[markdown](https://pandao.github.io/editor.md/)

##命令和脚本
mvn方式逆向生成mybatis：
```mvn mybatis-generator:generate```



##本网站需要改进的点
1.使用mysql存储用户token，性能太低，应该使用redis
2.目前只支持github登陆，有时间，更新一个网站用户登陆注册功能（未作）
3.从数据库中查到用户，不应该直接取出来，应该先更新（需完善）
4.不应该每次都从获取cookie后都从数据库中取，应该存到session里（需完善）
5.二级评论应该把数量显示出来，由于设计改表，没有完善，（需完善）
6.热门页签功能（未开发）
7.搜索功能（开发完成） 简单通过sql regexp
8.分页没有回到首页和回到末页（完善）
9.相关问题和热门都要限制数量（已修改）
10.点赞功能（未开发）


##部署
#安装环境
-git
-jdk
-maven
-mysql
#步骤
1.从github把代码clone过来
2.复制配置文件作为正式环境的配置文件，修改正式配置文件
3.mvn clean compile package
4java -jar -Dspring.profiles.active=production target/weblog-0.0.1-SNAPSHOT.jar