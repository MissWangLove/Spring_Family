## Mybatis的使用

### 引入依赖

```
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.2</version>
</dependency>
```

由于使用到金钱，所以要配置类型转化器，用于处理字段与属性类型不匹配的问题。

handler的编写：

```
public class MoneyTypeHandler extends BaseTypeHandler<Money> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Money parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter.getAmountMinorLong());
    }

    @Override
    public Money getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parseMoney(rs.getLong(columnName));
    }

    @Override
    public Money getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parseMoney(rs.getLong(columnIndex));
    }

    @Override
    public Money getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parseMoney(cs.getLong(columnIndex));
    }

    private Money parseMoney(Long value) {
        return Money.of(CurrencyUnit.of("CNY"), value / 100.0);
    }
}
```

相关的配置：

```
# mybatis
# handler是Mybatis的类型转换器，将数据库中的类型与实体类的字段属性进行转化
mybatis.type-handlers-package=com.wangzhi.mybatisdemo.handler
# 指定mybatis的xml存放路径
mybatis.mapper-locations=classpath:mapper/*.xml
# 指明实体类存放路径
mybatis.type-aliases-package=com.wangzhi.mybatisdemo.domain
# 是否启用下划线转驼峰式命名
mybatis.configuration.map-underscore-to-camel-case=true
```

插入返回主键：

```
seGeneratedKeys="true"
```

--