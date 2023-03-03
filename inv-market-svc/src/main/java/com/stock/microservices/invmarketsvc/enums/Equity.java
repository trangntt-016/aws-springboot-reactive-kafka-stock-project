package com.stock.microservices.invmarketsvc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Equity {
    US_EQUITY("us_equity", "American/New_York");
    private String zoneId;
    private String value;
}
