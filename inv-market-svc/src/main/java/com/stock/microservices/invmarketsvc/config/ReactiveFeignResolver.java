package com.stock.microservices.invmarketsvc.config;

import com.stock.microservices.invmarketsvc.connector.FeignConnector;

public interface ReactiveFeignResolver {
    
    FeignConnector getDomainByClient(String tenantId, String identifier);
}
