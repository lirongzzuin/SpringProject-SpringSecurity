package com.sparta.blogmyself;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlogmyselfApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogmyselfApplication.class, args);
    }

}
