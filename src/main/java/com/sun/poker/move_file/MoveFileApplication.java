package com.sun.poker.move_file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MoveFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoveFileApplication.class, args);
    }
}
