package com.wangzhi.springtransactiondemo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author : wz157
 * @date : 2020-05-20 17:21
 * @description : 声明式事务
 * @path : com.wangzhi.springtransactiondemo.DeclarativeTransactionService
 * @modifiedBy : wz157
 * @modifyDate : 2020-05-20 17:21
 */
@Service
@Slf4j
public class DeclarativeTransactionService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(rollbackOn = RollbackException.class)
    public void insertRecord() {
        jdbcTemplate.execute("INSERT INTO FOO (NAME) VALUES ('AAA')");
    }

    @Transactional(rollbackOn = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (NAME) VALUES ('BBB')");
        throw new RollbackException();
    }

    /**
     * 通过AOP代理的方式进行声明式事务的, 此方法没有事务, 所以没有被代理<br/>
     * 如果想要被代理,应该怎么做?<br/>
     *  - 可以注入 DeclarativeTransactionService, 来在方法内调用 declarativeTransactionServiceinsertThenRollback();<br/>
     *  - 或者给当前方法加上事务的注解<br/>
     * @throws RollbackException
     */
    public void invokeInsertThenRollback() throws RollbackException {
        insertThenRollback();
    }

    public Long getCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO", Long.class);
    }

}
