package com.stock.microservices.invmarketsvc.controller;

import com.stock.microservices.invmarketsvc.model.Stock;
import com.stock.microservices.invmarketsvc.service.MarketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class MarketController {

    private final MarketService marketService;

    @GetMapping("/assets")
    public Mono<Stock> getAllAssets(){
        return null;
//        return marketService.fetchAssetsData();
    }
}
