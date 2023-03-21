package com.stock.microservices.invmarketsvc.config;

import com.stock.microservices.invmarketsvc.config.client.BasicAuthConfiguration;
import com.stock.microservices.invmarketsvc.config.props.FeignClientProperties;
import com.stock.microservices.invmarketsvc.connector.FeignConnector;
import feign.FeignException;
import feign.Request;
import feign.Response;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import reactivefeign.ReactiveContract;
import reactivefeign.ReactiveOptions;
import reactivefeign.client.ReactiveFeignException;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactivefeign.client.ReactiveHttpResponse;
import reactivefeign.client.log.DefaultReactiveLogger;
import reactivefeign.client.statushandler.ReactiveStatusHandler;
import reactivefeign.retry.BasicReactiveRetryPolicy;
import reactivefeign.retry.ReactiveRetryPolicy;
import reactivefeign.utils.FeignUtils;
import reactivefeign.webclient.WebReactiveFeign;
import reactivefeign.webclient.WebReactiveOptions;
import reactor.core.publisher.Mono;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class BasicReactiveFeignResolver {

    @Bean
    @Scope("singleton")
    public Map<String, FeignConnector> feignClientFactor(FeignClientProperties properties,
                                                         BasicAuthConfiguration authenticationProvider){
        Map<String, FeignConnector> feignConnectorMap = new HashMap<>();

        properties.getRegistration().forEach((tenant, config)->{
            ReactiveHttpRequestInterceptor reactiveHttpRequestInterceptor = authenticationProvider.configAuthenticationInterceptor(tenant, config);

            FeignConnector connector = WebReactiveFeign.<FeignConnector>builder()
                    .addRequestInterceptor(reactiveHttpRequestInterceptor)
                    .contract(new ReactiveContract(new SpringMvcContract()))
                    .statusHandler(reactiveStatusHandler())
                    .retryWhen(reactiveRetryPolicy(properties))
                    .target(FeignConnector.class, config.getUrl());

            feignConnectorMap.put(tenant,connector);

        });
        return feignConnectorMap;
    }

    @Bean
    public DefaultReactiveLogger loggerListener() {
        return new DefaultReactiveLogger(Clock.systemUTC());
    }

    @Bean
    public ReactiveOptions reactiveOptions(FeignClientProperties properties) {
        return new WebReactiveOptions.Builder()
                .setReadTimeoutMillis(properties.getOptions().getReadTimeOutMill())
                .setWriteTimeoutMillis(properties.getOptions().getWriteTimeOutMill())
                .setResponseTimeoutMillis(properties.getOptions().getResponseTimeOutMill())
                .build();
    }

    private ReactiveRetryPolicy reactiveRetryPolicy(FeignClientProperties properties) {
        return BasicReactiveRetryPolicy.retryWithBackoff(properties.getRetryable().getMaxAttempts(),
                properties.getRetryable().getPeriod());
    }

    private ReactiveStatusHandler reactiveStatusHandler() {
        return new ReactiveStatusHandler() {
            @Override
            public boolean shouldHandle(int status) {
                return HttpStatus.valueOf(status).is5xxServerError();
            }

            @Override
            public Mono<? extends Throwable> decode(String methodKey, ReactiveHttpResponse<?> response) {
                return response.bodyData()
                        .defaultIfEmpty(new byte[0])
                        .map(bytes -> {
                            Request request = Request.create(FeignUtils.httpMethod(response.request().method()),
                                    response.request().uri().toString(),
                                    (Map) response.request().headers(),
                                    Request.Body.create(bytes), null);
                            Throwable exception = FeignException.errorStatus(methodKey, Response.builder()
                                    .body(bytes)
                                    .headers((Map) response.headers())
                                    .status(response.status())
                                    .request(request)
                                    .requestTemplate(request.requestTemplate())
                                    .build());
                            ReactiveFeignException feignException = new ReactiveFeignException(exception, response.request());
                            if (FeignUtils.httpMethod(response.request().method()).equals(Request.HttpMethod.GET)
                                    && HttpStatus.valueOf(response.status()).is5xxServerError()
                                    || HttpStatus.valueOf(response.status()).is4xxClientError()) {
                                log.info("Start to retry feign exception");
                                return new RetryableException(response.status(), exception.getMessage(), null,
                                        Date.from(Instant.EPOCH), request);
                            }
                            return feignException;
                        });
            }
        };
    }
}
