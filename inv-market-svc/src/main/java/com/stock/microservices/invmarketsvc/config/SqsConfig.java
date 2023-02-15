package com.stock.microservices.invmarketsvc.config;

import com.stock.microservices.invmarketsvc.config.props.SqsProperties;
import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.acknowledgement.AcknowledgementOrdering;
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;
import java.time.Duration;

@Slf4j
@Configuration
public class SqsConfig {

    private final SqsProperties sqsProperties;

    private final String region;

    public SqsConfig(SqsProperties sqsProperties, @Value("${cloud.aws.region}") String region) {
        this.sqsProperties = sqsProperties;
        this.region = region;
    }

    @Bean
    public SqsMessageListenerContainerFactory<Object> defaultSqsListener(){
        log.info("Config default SQS listener with corePoolSize: {} , maxMessagePerPoll: {} , waitTimeOut: {}, maxNumberOfMessage {}",
                sqsProperties.getCommon().getCorePoolSize(),
                sqsProperties.getCommon().getMaxMessagesPerPoll(),
                sqsProperties.getCommon().getWaitTimeOut(),
                sqsProperties.getCommon().getMaxInFlightMessagesPerQueue()
        );

        return SqsMessageListenerContainerFactory
                .builder()
                .sqsAsyncClient(sqsAsyncClient())
                .configure(configs -> configs
                        .acknowledgementMode(AcknowledgementMode.ON_SUCCESS)
                        .acknowledgementInterval(Duration.ofSeconds(3))
                        .acknowledgementThreshold(5)
                        .acknowledgementOrdering(AcknowledgementOrdering.ORDERED)
                        .maxMessagesPerPoll(sqsProperties.getCommon().getMaxMessagesPerPoll())
                        .maxInflightMessagesPerQueue(sqsProperties.getCommon().getMaxInFlightMessagesPerQueue())
                        .pollTimeout(Duration.ofSeconds(sqsProperties.getCommon().getPollTimeOut()))
                        .permitAcquireTimeout(Duration.ofSeconds(sqsProperties.getCommon().getPermitAcquireTimeout()))
                ).build();
    }

    @Bean
    public SqsAsyncClient sqsAsyncClient(){
        log.info("Processing set up sqs config with rootUrl: {} in region : {}", sqsProperties.getRoolUrl(), region);
        return SqsAsyncClient
                .builder()
                .endpointOverride(URI.create(sqsProperties.getRoolUrl()))
                .region(Region.of(region))
                .build();
    }
}
