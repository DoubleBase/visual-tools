package com.muskmelon.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.muskmelon")
@SpringBootApplication
public class VisualtoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisualtoolApplication.class, args);
    }

}
