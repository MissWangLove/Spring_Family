package com.wangzhi.many.datasource.springmanyjdbc;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author : wz157
 * @date : 2020-05-16 11:29
 * @description : Foo数据库查询
 * @path : com.wangzhi.many.datasource.springmanyjdbc.FooJdbcTemplate
 * @modifiedBy : wz157
 * @modifyDate : 2020-05-16 11:29
 */
@Component
@Slf4j
@Order(value = 2)
public class BarJdbcTemplateTest implements CommandLineRunner {
    private JdbcTemplate barJdbcTemplate;

    @Autowired
    public void setBarJdbcTemplate(JdbcTemplate barJdbcTemplate) {
        this.barJdbcTemplate = barJdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("SELECT * FROM BAR");
        barJdbcTemplate.queryForList("SELECT * FROM BAR").forEach(row -> log.info(row.toString()));
    }
}
