package com.stock.microservices.invmarketsvc.model;

import com.stock.microservices.invmarketsvc.connector.model.MarketAssetResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    private String id;

    private String exchange;

    private String symbol;

    private String name;

    private String status;

    private Boolean tradAble;

    private Boolean fractionAble;

    public static Stock init(MarketAssetResult assetResult){
        return Stock.builder()
                .exchange(assetResult.getExchange())
                .build();
    }
}
