package com.wangzhi.springjdbcexception;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJdbcExceptionApplicationTests {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test(expected = CustomDuplicateKeyException.class)
    public void testThrowingCustomException() {

        jdbcTemplate.execute("INSERT INTO FOO(ID, NAME) VALUES (104, 'hewiwjfeiowj')");
        jdbcTemplate.execute("INSERT INTO FOO(ID, NAME) VALUES (104, 'hewiwjfeiowj123')");
    }

    public static void main(String[] args) {
        Double a = null;
        System.out.println(a == null);
    }

}
