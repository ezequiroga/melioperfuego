package com.ml.operfuego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OperfuegoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperfuegoApplication.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
