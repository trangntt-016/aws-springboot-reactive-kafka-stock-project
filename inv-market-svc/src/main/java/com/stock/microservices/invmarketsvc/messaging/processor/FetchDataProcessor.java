package com.stock.microservices.invmarketsvc.messaging.processor;

import com.stock.microservices.invmarketsvc.messaging.model.FetchMarketEvent;
import com.stock.microservices.invmarketsvc.service.MarketService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class FetchDataProcessor {

    private final MarketService marketService;

    @SqsListener(value = "${cloud.aws.sqs.inv-market-data-collection-queue}")
    public void onFetchData(Message<FetchMarketEvent> message){
        log.info("Received fetch data event with payload: {}", message.getPayload());
        FetchMarketEvent event = message.getPayload();
        marketService.fetchAssetsData("vcb", event.getEquity()).subscribe();
    }


}
