package com.stock.microservices.invmarketsvc.config.props;

import io.netty.util.internal.StringUtil;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.utils.StringUtils;

import java.util.Map;
import java.util.Objects;

@Configuration
@ConfigurationProperties("spring.security.client")
@Data
@Slf4j
public class FeignClientProperties implements InitializingBean {

    @Getter(AccessLevel.NONE)
    private Registration defaultConfig;

    private Map<String, Registration> registration;

    private Retryable retryable;

    private Options options;

    private Duration duration;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (Objects.isNull(this.retryable))
            this.setRetryable(Retryable.DEFAULT);
        if (Objects.isNull(this.options))
            this.setOptions(Options.DEFAULT);
        this.registration.values().removeIf(con -> StringUtils.isBlank(con.clientId) || StringUtils.isBlank(con.clientSecret));
    }

    @Data
    public static class Registration {

        private String clientId;

        private String clientSecret;

        private String url;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Retryable{

        public static final Retryable DEFAULT = new Retryable(3 ,2000L);

        private Integer maxAttempts;

        private Long period;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Options{

        public static final Options DEFAULT = new Options(2000L ,2000L, 2000L);

        private Long readTimeOutMill;

        private Long writeTimeOutMill;

        private Long responseTimeOutMill;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Duration{

        private Long minOfSeconds;

        private Long maxOfSeconds;

        private Long timeOutOfSecond;

    }

}
