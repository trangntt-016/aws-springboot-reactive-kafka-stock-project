package com.stock.microservices.invmarketsvc.connector.impl;

import com.stock.microservices.invmarketsvc.config.ReactiveFeignResolver;
import com.stock.microservices.invmarketsvc.connector.BrokerConnector;
import com.stock.microservices.invmarketsvc.connector.model.MarketAssetResult;
import com.stock.microservices.invmarketsvc.enums.Equity;
import com.stock.microservices.invmarketsvc.enums.Status;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
@AllArgsConstructor
public class BrokerConnectorImpl implements BrokerConnector {

    private final ReactiveFeignResolver reactiveFeignResolver;

    private static final String COMPONENT_IDENTIFIER = "broker";

    @Override
    public Flux<MarketAssetResult> getAllAssets(String tenantId, Equity equity) {
        return reactiveFeignResolver.getDomainByClient(tenantId, COMPONENT_IDENTIFIER)
                .getAllAssets(Status.ACTIVE.getValue(), equity.getValue());
    }
}
