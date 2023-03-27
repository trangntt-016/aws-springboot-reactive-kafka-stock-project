package com.stock.microservices.invmarketsvc.connector.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class MarketAssetResult {

    private String id;

    @JsonProperty("class")
    private String equity;

    private String exchange;

    private String symbol;

    private String name;

    private String status;

    private boolean isTradeable;

    private boolean fractionable;
}
