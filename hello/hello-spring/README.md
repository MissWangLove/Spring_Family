### 创建第一个Spring项目

利用Spring Boot简化项目开发, 引入了
- web
- actuator

依赖, 可以快速创建一个api, 然后访问

通过/actuator/health可以进行健康检查, 当前应用程序的状态.返回的状态是UP说明正常
, 返回的状态是down说明服务异常

Actuator提供的好用的Endpoint:
- /actuator/health   健康检查
- /actuator/beans   查看容器中的所有Bean
- /actuator/mapping   查看web的URL映射
- /actuator/env     查看环境信息