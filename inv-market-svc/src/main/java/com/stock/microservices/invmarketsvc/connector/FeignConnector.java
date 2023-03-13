package com.stock.microservices.invmarketsvc.connector;

import com.stock.microservices.invmarketsvc.enums.Equity;
import com.stock.microservices.invmarketsvc.enums.Status;
import com.stock.microservices.invmarketsvc.model.Asset;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

public interface FeignConnector {

    @GetMapping("/v1/assets")
    Flux<Asset> getAllAssets(@RequestParam("status") Status status, @RequestParam("asset_class") Equity equity);
}
