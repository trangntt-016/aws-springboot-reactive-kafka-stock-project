package com.stock.microservices.invmarketsvc.service;

import com.stock.microservices.invmarketsvc.connector.model.FetchMarketEvent;
import com.stock.microservices.invmarketsvc.model.Asset;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchDataService {
    IFeignDataService iFeignDataService;



    public String getAssets(){

        // Step1: marketService.fetchData();

            return iFeignDataService.getAssets();

        // Todo: fetchDate(Equity equity, String status)

        // Todo: build interface connector
    }




}
