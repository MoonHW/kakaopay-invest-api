package com.kakaopay;


import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootApplication
@Configuration
@RunWith(SpringRunner.class)
public class InvestApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestApplication.class, args);

    }

}
