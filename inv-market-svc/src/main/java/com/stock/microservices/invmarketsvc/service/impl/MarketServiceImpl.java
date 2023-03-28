package com.stock.microservices.invmarketsvc.service.impl;

import com.stock.microservices.invmarketsvc.connector.BrokerConnector;
import com.stock.microservices.invmarketsvc.enums.Equity;
import com.stock.microservices.invmarketsvc.model.Stock;
import com.stock.microservices.invmarketsvc.repository.StockRepository;
import com.stock.microservices.invmarketsvc.service.MarketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class MarketServiceImpl implements MarketService{

    private final BrokerConnector brokerConnector;

    @Autowired
    private final StockRepository iStockRepo;

    @Override
    public Mono<Void> fetchAssetsData(String tenantId, Equity equity) {

        return brokerConnector.getAllAssets(tenantId, equity)
                .map(Stock::init)
                .collectList()
                .doOnNext(stocks -> {
                    log.info("Success get all stock with size: {}", stocks.size());
                    iStockRepo.saveAll(stocks);
                })
                .then();
    }
}
