package com.stock.microservices.invmarketsvc.connector;

import com.stock.microservices.invmarketsvc.connector.model.MarketAssetResult;
import com.stock.microservices.invmarketsvc.enums.Equity;
import reactor.core.publisher.Flux;

public interface BrokerConnector {

    Flux<MarketAssetResult> getAllAssets(String tenantId, Equity equity);
}
