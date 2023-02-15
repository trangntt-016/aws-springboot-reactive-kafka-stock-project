package com.stock.microservices.invmarketsvc.messaging.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchMarketEvent {

    private String equity;
}
