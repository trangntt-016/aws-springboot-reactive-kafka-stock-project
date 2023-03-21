package com.stock.microservices.invmarketsvc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    ACTIVE("active"),INACTIVE("inactive");

    private String value;
}
