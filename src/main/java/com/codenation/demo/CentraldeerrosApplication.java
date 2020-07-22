package com.codenation.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;


@EnableJpaAuditing
@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class CentraldeerrosApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentraldeerrosApplication.class, args);
    }

}
