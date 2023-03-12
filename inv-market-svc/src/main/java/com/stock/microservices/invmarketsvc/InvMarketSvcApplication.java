package com.stock.microservices.invmarketsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import reactivefeign.spring.config.EnableReactiveFeignClients;

import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication
@EnableReactiveFeignClients
@EnableFeignClients
public class InvMarketSvcApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")));
        SpringApplication.run(InvMarketSvcApplication.class, args);
    }

}
