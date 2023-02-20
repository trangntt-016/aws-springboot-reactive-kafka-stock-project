package com.stock.microservices.invmarketsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients
public class InvMarketSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvMarketSvcApplication.class, args);
    }

}
