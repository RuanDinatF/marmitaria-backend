package com.ifsp.marmitaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ifsp.marmitaria")
public class MarmitariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarmitariaApplication.class, args);
    }

}
