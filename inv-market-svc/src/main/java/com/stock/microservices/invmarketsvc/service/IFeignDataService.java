package com.stock.microservices.invmarketsvc.service;

import com.stock.microservices.invmarketsvc.config.client.BasicAuthConfiguration;
import com.stock.microservices.invmarketsvc.model.Asset;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;


@ReactiveFeignClient(value = "iFeignDemo", url="https://broker-api.sandbox.alpaca.markets/v1", configuration = BasicAuthConfiguration.class)
public interface IFeignDataService {
    @GetMapping("/assets")
    Mono<String> getAssets()  ;
}
