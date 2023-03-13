package com.stock.microservices.invmarketsvc.service;

import com.stock.microservices.invmarketsvc.enums.Equity;
import com.stock.microservices.invmarketsvc.model.Asset;
import reactor.core.publisher.Flux;

public interface MarketService {

    Flux<Asset> fetchAssetsData(String tenantId, Equity equity);
}
