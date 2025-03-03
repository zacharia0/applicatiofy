package com.zacharia.applicatiofybe;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:env.properties")
@SpringBootApplication
public class ApplicatiofyBeApplication {

    @Value("${JWT_EXPIRATION}")
    private String JWT_EXPIRATION;

    @Value("${JWT_SECRET}")
    private String JWT_SECRET;

    public static void main(String[] args) {
        SpringApplication.run(ApplicatiofyBeApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("JWT_EXPIRATION:" + JWT_EXPIRATION);
        System.out.println("JWT SECRET:" + JWT_SECRET);
    }

}
