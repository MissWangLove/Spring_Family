package com.wangzhi.simplejdbcdemo.dao;


import com.wangzhi.simplejdbcdemo.FooDO;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author : wz157
 * @date : 2020-05-19 11:14
 * @description : Foo数据层的访问
 * @path : com.wangzhi.simplejdbcdemo.dao.FooDao
 * @modifiedBy : wz157
 * @modifyDate : 2020-05-19 11:14
 */
@Repository
@Slf4j
public class FooDao {

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setSimpleJdbcInsert(SimpleJdbcInsert simpleJdbcInsert) {
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    public void insertData() {
        // 使用jdbcTemplate进行插入
        Arrays.asList("c", "d").forEach(foo -> {
            jdbcTemplate.update("INSERT INTO FOO(NAME) VALUES (?)", foo);
        });
        // 使用simpleJdbcTemplate进行插入
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("NAME", "e");
        Number id = simpleJdbcInsert.executeAndReturnKey(hashMap);
        log.info("ID : {}", id.intValue());
    }

    public void listData() {
        // 查询聚合函数
        log.info("Count : {}",
                jdbcTemplate.queryForObject("SELECT COUNT(1) FROM FOO", Integer.class));

        // 查看单个字段
        List<String> strings = jdbcTemplate.queryForList("SELECT NAME FROM FOO", String.class);
        strings.forEach(name -> log.info("name : {}", name));

        // 查询全部对象
        List<FooDO> fooList = jdbcTemplate.query("SELECT * FROM FOO", new RowMapper<FooDO>() {
            @Override
            public FooDO mapRow(ResultSet resultSet, int i) throws SQLException {
                return FooDO.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
            }
        });
        fooList.forEach(foo -> log.info("foo : {}", foo));
    }


}
