package com.stock.microservices.invmarketsvc.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Asset {

    private String id;

    private String exchange;

    private String symbol;

    private String name;

    private String status;

    private Boolean tradAble;

    private Boolean fractionAble;
}
