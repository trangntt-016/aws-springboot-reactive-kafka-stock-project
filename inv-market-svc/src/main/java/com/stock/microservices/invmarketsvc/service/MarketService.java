package com.stock.microservices.invmarketsvc.service;

import com.stock.microservices.invmarketsvc.enums.Equity;
import reactor.core.publisher.Mono;

public interface MarketService {

    Mono<Void> fetchAssetsData(String tenantId, Equity equity);
}
