package com.stock.microservices.invmarketsvc.config.client;


import com.stock.microservices.invmarketsvc.config.props.FeignClientProperties;
import feign.auth.BasicAuthRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;


@Slf4j
@Configuration
public class BasicAuthConfiguration {
    private final String AUTHORIZATION_HEADER = "Authorization";

    private final String BASIC_AUTHORIZATION = "Basic";

    public ReactiveHttpRequestInterceptor configAuthenticationInterceptor(String tenantId, FeignClientProperties.Registration properties){
        log.info("Start interceptor basic authentication for tenantId: {}", tenantId);
        return reactiveHttpRequest -> {
            String accessToken = Base64.getEncoder().encodeToString((properties.getClientId()+":"+properties.getClientSecret()).getBytes(StandardCharsets.UTF_8));
            reactiveHttpRequest.headers().put(AUTHORIZATION_HEADER, List.of(accessToken));
            return Mono.just(reactiveHttpRequest);
        };
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("CKTX1IY1UF39RBBCB43D", "unyJ8VrXMyBxNrq5fPdKKlijS4WsZnrD7wmr9KMB");
    }
}
