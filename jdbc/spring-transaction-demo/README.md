## 事务抽象

#### 事务抽象的核心接口

- PlatformTransactionManager
- TransactionDefinition

在实际使用中可以使用 TransactionTemplate 进行编程式事务或者通过AOP进行声明式事务, 这个在
PlatformTransactionManager中有说明.

#### 事务的传播特性

传播特性是在 TransactionDefinition 接口中声明的

|  传播性   | 值  |  描述  |
|  ----  | ----  | --- |
| PROPAGATION_REQUIRED  | 0 | 当前事务有就用当前的,没有就用新的 |
| PROPAGATION_SUPPORTS  | 1 | 事务可有可无,不是必须的 |
| PROPAGATION_MANDATORY | 2 | 当前一定要有事务,不然就抛异常 |
| PROPAGATION_REQUIRES_NEW | 3 | 无论是否有事务,都起个新的事务 |
| PROPAGATION_NOT_SUPPORTED | 4 | 不支持事务,按非事务方式进行 |
| PROPAGATION_NEVER | 5 | 不支持事务,如果有事务就抛异常 |
| PROPAGATION_NESTED | 6 | 当前有事务就在当前事务中再起一个事务 |

PROPAGATION_REQUIRES_NEW: 两个事务之间没有任何关系

PROPAGATION_NESTED: 两个事务是内嵌的, 外部事务回滚,内部事务也会回滚


默认是 PROPAGATION_REQUIRED.

事务的隔离特性:

|  隔离性   | 值  |  sql隔离性  |
|  ----  | ----  | --- |
| ISOLATION_READ_UNCOMMITTED  | 1 | READ_UNCOMMITTED |
| ISOLATION_READ_COMMITTED  | 2 | READ_COMMITTED |
| ISOLATION_REPEATABLE_READ | 4 | REPEATABLE_READ |
| ISOLATION_SERIALIZABLE | 8 | SERIALIZABLE |
  
 ##### 编程式事务
 
 ProgrammaticTransactionService演示编程式事务
 
 ##### 声明式事务
 
 DeclarativeTransactionService演示
 
 Spring声明式事务的本质上是通过AOP来增强类的功能
 
 Spring的AOP的本质就是为类做了一个代理(看似在调用自己写的类,实际上用的是增强后的代理类)
 
 
 