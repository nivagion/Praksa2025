package me.leo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "me.leo")
@EnableJpaRepositories(basePackages = "me.leo.database")
@EntityScan(basePackages = "me.leo.database")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
