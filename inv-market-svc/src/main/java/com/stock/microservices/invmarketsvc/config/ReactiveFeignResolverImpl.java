package com.stock.microservices.invmarketsvc.config;

import com.stock.microservices.invmarketsvc.connector.FeignConnector;
import com.stock.microservices.invmarketsvc.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class ReactiveFeignResolverImpl implements ReactiveFeignResolver {

    private final Map<String, FeignConnector> feignConnectorMap;

    @Override
    public FeignConnector getDomainByClient(String tenantId, String identifier) {
        log.info("Start get broker instance by tenantId: {}", tenantId);
        return feignConnectorMap.get(StringUtils.join(tenantId, Constants.SpecialCharacter.DASH, identifier));
    }
}
