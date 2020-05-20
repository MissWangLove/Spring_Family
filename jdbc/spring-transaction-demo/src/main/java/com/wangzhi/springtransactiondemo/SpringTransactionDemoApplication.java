package com.wangzhi.springtransactiondemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class SpringTransactionDemoApplication implements CommandLineRunner {

    private ProgrammaticTransactionService programmaticTransactionService;

    private DeclarativeTransactionService declarativeTransactionService;

    @Autowired
    public void setProgrammaticTransactionService(ProgrammaticTransactionService programmaticTransactionService) {
        this.programmaticTransactionService = programmaticTransactionService;
    }

    @Autowired
    public void setDeclarativeTransactionService(DeclarativeTransactionService declarativeTransactionService) {
        this.declarativeTransactionService = declarativeTransactionService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringTransactionDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        programmaticTransactionService.insertDataRollback();
        log.info("================================================");
        declarativeTransactionService.insertRecord();
        log.info("count : {}", declarativeTransactionService.getCount());

        try {
            declarativeTransactionService.insertThenRollback();
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        log.info("count : {}", declarativeTransactionService.getCount());

        try {
            declarativeTransactionService.invokeInsertThenRollback();
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        log.info("count : {}", declarativeTransactionService.getCount());
    }
}
