package com.stock.microservices.invmarketsvc.service.impl;

import com.stock.microservices.invmarketsvc.connector.BrokerConnector;
import com.stock.microservices.invmarketsvc.enums.Equity;
import com.stock.microservices.invmarketsvc.service.MarketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class MarketServiceImpl implements MarketService{

    private final BrokerConnector brokerConnector;

    @Override
    public Mono<Void> fetchAssetsData(String tenantId, Equity equity) {

        return brokerConnector.getAllAssets(tenantId, equity)
                .doOnNext(System.out::println)
                .then();

        // Step1: marketService.fetchData();

//        return Mono.just("hello");

        // Todo: fetchDate(Equity equity, String status)

        // Todo: build interface connector
    }
}
