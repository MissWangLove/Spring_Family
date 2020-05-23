## Spring JDBC异常抽象

#### DataAccessException

Spring将所有的数据操作的异常转换为 DataAccessException, 它是所有数据异常操作类的
抽象父类.

#### SQLErrorCodeSQLExceptionTranslator

Spring 将所有数据库的异常码都进行了定义, 并且使用 SQLErrorCodeSQLExceptionTranslator 
来解析所有的错误码.

至于所有错误码的定义:
- org/springframework/jdbc/support/sql-error-codes.xml  里面包含了基本所有的数据库和对应的错误码
- Classpath 下的 sql-error-codes.xml, 自定义错误码

##### 自定义错误码

在Classpath下的 sql-error-codes.xml 中, 使用set方法注入 customTranslations,
再次使用set方法设置了 CustomSQLErrorCodesTranslation 的属性,errorCodes和ExceptionClass

注意自定义的异常类要继承指定的Spring的对应的异常类.

##### SpringBoot项目的测试类的编写

- 测试类必须是public的, 默认项目生成的不是
- 引入Junit
- 加上RunWith注解
- 编写测试类, 指定抛出的异常
- 测试就可以看到效果

由于这里指出了抛出的异常,所以测试类是可以通过的.
