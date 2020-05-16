 ### 双数据源的配置
 
 如果项目需要双数据源,那就需要我们手动的来配置DataSource, 事务管理和JdbcTemplate了.
 
 由于H2数据库不熟悉,这次就是用MySQL数据库,数据库的准备在resources下,只需要创建两个库,跑sql就好.
 
 ##### 编码阶段
 - 创建两个数据库,分别执行foo.sql和bar.sql
 - application.yml配置双数据源的连接信息
 - 在启动类上排除SpringBoot自动帮我们配置的数据源,事务等信息,只需要排掉上次说的
 相关的AutoConfiguration就好
 - 在启动类中读取配置文件,创建DataSource,事务管理和JdbcTemplate的相关信息,并
 交给Spring去管理(放到Spring Container中)
 - 编写两个bean,去实现CommandLineRunner接口,使用JdbcTemplate去测试查询数据.
 
 