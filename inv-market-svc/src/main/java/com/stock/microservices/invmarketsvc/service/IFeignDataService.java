package com.stock.microservices.invmarketsvc.service;

import com.stock.microservices.invmarketsvc.model.Asset;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;

import java.util.List;

@ReactiveFeignClient(value = "iFeignDemo", url="https://broker-api.sandbox.alpaca.markets/v1/assets")
public interface IFeignDataService {
    @GetMapping("/assets")
    String getAssets()  ;
}
