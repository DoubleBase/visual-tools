package com.muskmelon.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("com.muskmelon")
@EntityScan("com.muskmelon")
@EnableJpaRepositories("com.muskmelon")
@SpringBootApplication
@EnableWebMvc
public class VisualtoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisualtoolApplication.class, args);
    }

}
