##dtmanager管理系统

###后台功能列表
* 权限管理
* 内容管理
* 任务调度

###技术选型
* 核心框架：Spring Boot 1.5.4
* 安全框架：Apache Shiro 1.2
* 任务调度：Spring + Quartz
* 持久层框架：MyBatis 3.4
* 数据库连接池：Alibaba Druid 1.0
* 全文检索库： Apache Lucene 6.4.0
* 缓存框架：Ehcache,另外提供了基于redis的缓存
* 日志管理：SLF4J、logback
* 后台前端框架： jQuery + EasyUI
* 前台前端框架：Boostrap
* 项目管理工具：Maven
* 接口文档工具：Springfox-Swagger2

###开发步骤
* 下载源码
* 创建数据库及表
* 导入到Eclipse
* 修改数据库配置文件

###部署方法
* 运行命令 mvn clean package -Dmaven.test.skip=true tomcat7:run

###后台访问
* http://localhost:8080/dtcms，admin/123456

#参考Web项目：
* jeesite:https://github.com/dlts26/jeesite.git
* shishuomcs: https://git.oschina.net/shishuo/CMS.git
* iBase4j：https://git.oschina.net/iBase4J/iBase4J.git