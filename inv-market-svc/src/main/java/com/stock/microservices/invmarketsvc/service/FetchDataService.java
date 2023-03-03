package com.stock.microservices.invmarketsvc.service;

import com.stock.microservices.invmarketsvc.messaging.model.FetchMarketEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class FetchDataService {
    Service marketService;

    public void onFetchData(Message<FetchMarketEvent>message){
        log.info("Received fetch data event with payload: {}", message.getPayload());
        FetchMarketEvent event = message.getPayload();

        // Step1: marketService.fetchData();

        // Todo: fetchDate(Equity equity, String status)

        // Todo: build interface connector
    }


}
