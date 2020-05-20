package com.wangzhi.springtransactiondemo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author : wz157
 * @date : 2020-05-20 17:19
 * @description : 编程式事务
 * @path : com.wangzhi.springtransactiondemo.ProgrammaticTransactionService
 * @modifiedBy : wz157
 * @modifyDate : 2020-05-20 17:19
 */
@Service
@Slf4j
public class ProgrammaticTransactionService {

    private JdbcTemplate jdbcTemplate;

    private TransactionTemplate transactionTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void insertDataRollback() {
        log.info("count : {}", getCount());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                jdbcTemplate.update("INSERT INTO FOO(NAME) VALUES ('transaction')");
                log.info("COUNT : {}", getCount());
                status.setRollbackOnly();
            }
        });
        log.info("count : {}", getCount());
    }

    private Long getCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO", Long.class);
    }
}
