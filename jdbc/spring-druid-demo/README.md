## 数据库连接池的使用

#### HikariCP

hikariCP是SpringBoot2之后默认使用的数据库连接池,特点就是快.

- 使用了自己实现的FastStatementList代替了ArrayList
- 无锁集合ConcurrentBag
- 代理类的优化等等

配置:
```
spring.datasource.hikari.maximumPoolSize=10 -- 连接池大小
spring.datasource.hikari.minimumldle=10 -- 最小的连接个数
spring.datasource.hikari.idleTimeout=600000 -- 超时
spring.datasource.hikari.connectionTimeout=30000 -- 连接超时
spring.datasource.hikari.maxLifetime=1800000 -- 存活时长
更多的配置: github.com/brettwooldridge/HikariCP
```

#### Druid

这里重点说阿里的连接池Druid,druid提供了强大的监控功能,他的特点是:
- 详细的监控
- ExceptionSorter针对主流数据库的返回码都有支持
- SQL防注入
- 内置加密配置
- 扩展点多,方便定制

使用:
 - 使用SpringBoot2以上时,首先要排除掉HikariCP的依赖,然后引入Druid的依赖:
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
        <exclusions>
            <exclusion>
                <groupId>HikariCP</groupId>
                <artifactId>com.zaxxer</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.1.22</version>
    </dependency>
    ```
 - 进行相关的配置:
    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/spring_family?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true
    spring.datasource.username=root
    spring.datasource.password=howvOyHDxYW+zooiof2ayh3TgcgQPTWVZsPos2HLyJLEoufP9NboRafFljYrULJLNpC+vHtrAj7KJ5/cydOqBg==
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    # 初始连接池大小
    spring.datasource.druid.initial-size=5
    # 最大活跃连接数
    spring.datasource.druid.max-active=5
    # 最小的连接数
    spring.datasource.druid.min-idle=5
    # filter的配置
    spring.datasource.druid.filters=conn,config,stat,slf4j
    
    spring.datasource.druid.connection-properties=config.decrypt=true;config.decrypt.key=${public-key}
    spring.datasource.druid.filter.config.enabled=true
    
    spring.datasource.druid.test-on-borrow=true
    spring.datasource.druid.test-on-return=true
    spring.datasource.druid.test-while-idle=true
    
    public-key=MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJMpKckOoZ+ImZ+4Wf+0rjpDGyhI3jGKSfzW8SaMUz36A73X5By4GBJfDeSZsonMKqEOfTrlHsd02A4j8Xs7wXUCAwEAAQ==
    ```
 - 实现密码的加密, 需要使用druid的jar包, 可以再maven的repository中复制出来, 然后执行:
    ```
    java -cp druid-1.1.22.jar com.alibaba.druid.filter.config.ConfigTools your_password
    之后会生成
        privateKey(私钥)
        publicKey(公钥)
        password(加密后的密码)
    我们需要用到的是password和publicKey,如第二步的数据库的密码就是加密后的, public-key就是公钥
    ```
 - 扩展自己的filter,在创建连接前和连接后各打印一句日志:
    ```
   // 需要继承FilterEventAdapter 
   @Slf4j
    public class ConnectionLogFilter extends FilterEventAdapter {
    
        @Override
        public void connection_connectBefore(FilterChain chain, Properties info) {
            log.info("BEFORE CONNECTION!!!");
        }
    
        @Override
        public void connection_connectAfter(ConnectionProxy connection) {
            log.info("AFTER CONNECTION!!!");
        }
    }
    ```
 - 配置自己的Filter
    ```
    在META-INF下新建druid-filter.properties,内容是为自己的过滤器类起别名
        druid.filters.conn=com.wangzhi.springdruid.ConnectionLogFilter
    conn就是别名,在第二步的时候已经在filters中进行了配置
    ```
 - 启动类进行测试,查看日志就可以看到效果
 
 更多的配置:
    ```
    SQL防注入
    spring.datasource.druid.filter.wall.enabled=true
    spring.datasource.druid.filter.wall.db-type=mysql -- 指定使用的数据库
    spring.datasource.druid.filter.wall.config.delete-allow=false -- 不允许删除
    spring.datasource.druid.filter.wall.config.drop-table-allow=false -- 不允许做drop-table操作
    等等更多的参考https://github.com/alibaba/druid/wiki
    ```
 
 
 #### 如何选择连接池
 
 选择连接池应该要考虑的方向:
 - 可靠性: 数据库连接出现问题快速回复等
 - 性能: 进程开销尽可能小
 - 功能: SQL防注入的功能
 - 可运维性: 密码加密等
 - 可扩展性: 过滤,拦截,预处理等等
 - 其他