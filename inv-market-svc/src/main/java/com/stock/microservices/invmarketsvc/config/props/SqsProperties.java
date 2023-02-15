package com.stock.microservices.invmarketsvc.config.props;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties("cloud.aws.sqs")
public class SqsProperties {

    private Queue common;

    private String roolUrl;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Queue {
        private int waitTimeOut;

        private int maxNumberOfMessages;

        private int maxInFlightMessagesPerQueue;

        private int maxMessagesPerPoll;

        private int pollTimeOut;

        private int permitAcquireTimeout;

        private int corePoolSize;

        private int maxPoolSize;

        private int keepAliveTimeInSeconds;

        private String threadNamePrefix;
    }
}
