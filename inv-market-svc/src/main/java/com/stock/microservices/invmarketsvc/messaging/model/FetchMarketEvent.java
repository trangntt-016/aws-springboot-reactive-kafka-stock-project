package com.stock.microservices.invmarketsvc.messaging.model;

import com.stock.microservices.invmarketsvc.enums.Equity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchMarketEvent {

    private Equity equity;
}
