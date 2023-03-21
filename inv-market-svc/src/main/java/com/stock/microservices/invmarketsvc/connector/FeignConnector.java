package com.stock.microservices.invmarketsvc.connector;

import com.stock.microservices.invmarketsvc.connector.model.MarketAssetResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

public interface FeignConnector {

    @RequestMapping(method = RequestMethod.GET, value ="/v1/assets")
    Flux<MarketAssetResult> getAllAssets(@RequestParam("status") String status,
                                         @RequestParam("asset_class") String equity);
}
