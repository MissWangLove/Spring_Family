## SpringBoot访问MongoDB

### 说明

- 引入依赖等就不必说了，pom文件中
- 使用template或者repository进行数据库的访问

template是可以直接直接的，请看MongoDemoApplication中注释的代码，忽略掉@EnableMongoRepositories和 
CoffeeRepository，这两个Repository的访问。

具体的操作就看 MongoDemoApplication 的api， 重点说一下使用repository的时候第二次执行save（lette），
实际做的是修改操作，而不是插入操作。