package com.wangzhi.simplejdbcdemo;

import com.wangzhi.simplejdbcdemo.dao.BatchFooDao;
import com.wangzhi.simplejdbcdemo.dao.FooDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;


/**
 * @author wz157
 */
@SpringBootApplication
@Slf4j
public class SimpleJdbcDemoApplication implements CommandLineRunner {

    private FooDao fooDao;

    private BatchFooDao batchFooDao;

    @Autowired
    public void setFooDO(FooDao fooDao) {
        this.fooDao = fooDao;
    }

    @Autowired
    public void setBatchFooDao(BatchFooDao batchFooDao) {
        this.batchFooDao = batchFooDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleJdbcDemoApplication.class, args);
    }

    /**
     * 注入SimpleJdbcInsert
     * @param jdbcTemplate
     * @return
     */
    @Bean
    @Autowired
    public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        // 对应FOO表, 主键是ID
        return new SimpleJdbcInsert(jdbcTemplate).withTableName("FOO")
                .usingGeneratedKeyColumns("ID");

    }

    @Bean
    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void run(String... args) throws Exception {
        //fooDao.insertData();
        batchFooDao.batchInsert();
        fooDao.listData();
    }
}
