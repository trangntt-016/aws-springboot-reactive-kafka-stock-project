package com.stock.microservices.invmarketsvc.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Asset {
    private String id;
    private String exchange;
    private String symbol;
    private String name;
    private String status;
    private Boolean tradable;
    private Boolean marginable;
    private Boolean shortable;
    private Boolean easy_to_borrow;
    private Boolean fractionable;
}
