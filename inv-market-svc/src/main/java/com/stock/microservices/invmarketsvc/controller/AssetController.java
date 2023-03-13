package com.stock.microservices.invmarketsvc.controller;

import com.stock.microservices.invmarketsvc.model.Asset;
import com.stock.microservices.invmarketsvc.service.FetchDataService;
import com.stock.microservices.invmarketsvc.service.IFeignDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/public")
public class AssetController {
    @Autowired
    private IFeignDataService fetchDataService;

    @GetMapping("/assets")
    public Mono<String> getAllAssets(){
        return fetchDataService.getAssets();
    }
}
