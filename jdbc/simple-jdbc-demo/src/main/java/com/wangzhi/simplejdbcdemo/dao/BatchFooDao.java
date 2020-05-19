package com.wangzhi.simplejdbcdemo.dao;


import com.wangzhi.simplejdbcdemo.FooDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : wz157
 * @date : 2020-05-19 11:46
 * @description : 批量操作FooDO
 * @path : com.wangzhi.simplejdbcdemo.dao.BatchFooDao
 * @modifiedBy : wz157
 * @modifyDate : 2020-05-19 11:46
 */
@Repository
public class BatchFooDao {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void batchInsert() {
        // 循环三次
        jdbcTemplate.batchUpdate("INSERT INTO FOO(NAME) VALUES (?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, "e-" + i);
            }

            @Override
            public int getBatchSize() {
                return 3;
            }
        });

        // 使用namedParameterJdbcTemplate进行批量插入
        List<FooDO> list = new ArrayList<>();
        list.add(FooDO.builder().id(100).name("f-100").build());
        list.add(FooDO.builder().id(101).name("f-101").build());
        namedParameterJdbcTemplate.batchUpdate("INSERT INTO FOO(ID, NAME) VALUES (:id, :name)",
                SqlParameterSourceUtils.createBatch(list));
    }
}
