package com.stock.microservices.invmarketsvc.model;

import com.stock.microservices.invmarketsvc.connector.model.MarketAssetResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock")
public class Stock implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column
    private String exchange;

    @Column
    private String symbol;

    @Column
    private String name;

    @Column
    private String status;

    @Column
    private boolean tradeable;

    @Column
    private boolean fractionable;



    public static Stock init(MarketAssetResult assetResult){
        return Stock.builder()
                .id(assetResult.getId())
                .exchange(assetResult.getExchange())
                .symbol(assetResult.getSymbol())
                .name(assetResult.getName())
                .status(assetResult.getStatus())
                .tradeable(assetResult.isTradeable())
                .fractionable(assetResult.isFractionable())
                .build();
    }
}
