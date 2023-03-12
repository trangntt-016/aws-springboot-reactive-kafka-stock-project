package com.stock.microservices.invmarketsvc.messaging.processor;

import com.stock.microservices.invmarketsvc.connector.FeignConnector;
import com.stock.microservices.invmarketsvc.enums.Equity;
import com.stock.microservices.invmarketsvc.connector.model.FetchMarketEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class FetchDataProcessor {
    //private final FeignConnector feignConnector;

    @SqsListener(value = "${cloud.aws.sqs.inv-market-data-collection-queue}")
    public void onFetchData(Message<FetchMarketEvent> message){
        log.info("Received fetch data event with payload: {}", message.getPayload());
        FetchMarketEvent event = message.getPayload();
    }


}
