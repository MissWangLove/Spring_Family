package com.wangzhi.mybatisdemo;

import com.wangzhi.mybatisdemo.domain.TCoffee;
import com.wangzhi.mybatisdemo.mapper.TCoffeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

/**
 * CommandLineRunner和ApplicationRunner的区别在于后者可以更好的获取命令行参数
 */
@SpringBootApplication
@Slf4j
public class MybatisDemoApplication implements CommandLineRunner {

    private TCoffeeMapper tCoffeeMapper;

    @Autowired
    public void settCoffeeMapper(TCoffeeMapper tCoffeeMapper) {
        this.tCoffeeMapper = tCoffeeMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        insert();
    }

    private void insert() {
        TCoffee tCoffee = TCoffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .createTime(new Date())
                .updateTime(new Date()).build();

        int count = tCoffeeMapper.insert(tCoffee);

        log.info("save {} count : {}", tCoffee, count);

        tCoffee = TCoffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.0))
                .createTime(new Date())
                .updateTime(new Date()).build();

        count = tCoffeeMapper.insert(tCoffee);

        log.info("save {}  count : {}", tCoffee, count);

        TCoffee tCoffee1 = tCoffeeMapper.selectByPrimaryKey(tCoffee.getId());
        log.info("select {}", tCoffee1);

    }
}
