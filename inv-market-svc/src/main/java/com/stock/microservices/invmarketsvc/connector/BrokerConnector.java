package com.stock.microservices.invmarketsvc.connector;

import com.stock.microservices.invmarketsvc.enums.Equity;
import com.stock.microservices.invmarketsvc.model.Asset;
import reactor.core.publisher.Flux;

public interface BrokerConnector {

    Flux<Asset> getAllAssets(String tenantId, Equity equity);
}
