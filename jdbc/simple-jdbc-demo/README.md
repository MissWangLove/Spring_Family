## Spring JDBC的使用

#### 基础知识

常用的Bean注解:

- @Component
- @Repository
- @Service
- @Controller
    - @RestController
    
JdbcTemplate常用方法:

- query
- queryForObject
- queryForList
- update(增删改)
- execute(通用方法)

SQL批处理:

- JdbcTemplate
    - batchUpdate
        - BatchPreparedStatementSetter
- NamedParameterJdbcTemplate
    - batchUpdate
        - SqlParameterSourceUtils.createBatch
        
案例演示:

- 依然使用Druid数据源,对数据库密码进行一个加密
- fooDO展示的是与数据库对应的实体类
- dao下的FooDao展示了JdbcTemplate的基本使用
- dao下的BatchFooDao展示了批量操作的基本使用(JdbcTemplate和NamedParameterJdbcTemplate)