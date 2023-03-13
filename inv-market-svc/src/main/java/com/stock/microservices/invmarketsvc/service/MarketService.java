package com.stock.microservices.invmarketsvc.service;

import com.stock.microservices.invmarketsvc.enums.Equity;
import com.stock.microservices.invmarketsvc.model.Asset;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MarketService {

    Mono<Void> fetchAssetsData(String tenantId, Equity equity);
}
