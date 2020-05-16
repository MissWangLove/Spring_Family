### JDBC的初次使用

- 内置数据库H2的简单使用,H2是Java内置数据库,本身是一个类库,可以直接嵌入到应用程序中去
- CommandLineRunner接口: 仅仅包含一个run方法,实现这个接口的作用就是在项目启动后去执行
一些操作, 简洁的说就是项目启动后执行run方法,并且可以有多个类实现该接口,只要将对象放到容器
中, 就都会执行,如何保证执行的顺序呢?就要利用到Order注解了
- actuator的使用: 注意的是只会开放health和info接口的访问,要想访问其他的,需要额外的配置,
比如我们想看SpringBoot帮我们做的加载的所有Bean(应用上下文), 就要访问/beans,配置在yml文件中,可以查看
- 在运行项目的时候可以看到并没有配置DataSource相关的信息,但是在使用的时候确实可以使用, 这是
因为SpringBoot会检测我们使用的数据库然后默认帮我们做了配置,并加载到容器中

SpringBoot的默认配置都是按需索取,根据引入的依赖,配置等相关信息帮我配置,如果我们手动已经
配置过了,那SpringBoot就不会帮我们配置了,手动配置就会相对麻烦,写配置文件,读配置文件,生成bean,
放到容器中, SpringBoot的配置给我们提供了很大的便捷性.

SpringBoot的默认创建Bean其实就是通过一个一个内置的Config进行完成的,举三个例子:
- DataSourceAutoConfiguration 帮我们自动配置了 DataSource
- DataSourceTransactionManagerAutoConfiguration 帮我们配置了事务相关的 DataSourceTransactionManager
- JdbcTemplateAutoConfiguration 帮我们配置了 JdbcTemplate