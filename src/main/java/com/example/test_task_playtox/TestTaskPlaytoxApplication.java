package com.example.test_task_playtox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TestTaskPlaytoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskPlaytoxApplication.class, args);
    }

}
