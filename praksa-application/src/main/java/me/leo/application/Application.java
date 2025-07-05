package me.leo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // scans everything in me.leo.*
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
