package com.example.burgerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BurgerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurgerApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate _restTemplate = new RestTemplate();
        return _restTemplate;
    }
}
