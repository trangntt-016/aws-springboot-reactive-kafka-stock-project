package com.stock.microservices.invmarketsvc.service.impl;

import com.stock.microservices.invmarketsvc.connector.BrokerConnector;
import com.stock.microservices.invmarketsvc.connector.model.MarketAssetResult;
import com.stock.microservices.invmarketsvc.enums.Equity;
import com.stock.microservices.invmarketsvc.model.Stock;
import com.stock.microservices.invmarketsvc.service.MarketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class MarketServiceImpl implements MarketService{

    private final BrokerConnector brokerConnector;

    @Override
    public Mono<Void> fetchAssetsData(String tenantId, Equity equity) {

        return brokerConnector.getAllAssets(tenantId, equity)
                .filter(MarketAssetResult::getTradeable)
                .map(assetResult -> Stock.init(assetResult))
                .collectList()
                .doOnNext(stocks -> {
                    log.info("Success get all stock with size: {}", stocks.size());
//                    return repo.saveAll(stocks);
                })
                .then();

        // Step1: marketService.fetchData();

//        return Mono.just("hello");

        // Todo: fetchDate(Equity equity, String status)

        // Todo: build interface connector
    }
}
