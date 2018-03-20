package com.fox.registeroffice;

import com.soundcloud.prometheus.hystrix.HystrixPrometheusMetricsPublisher;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAsync
@EnableTransactionManagement
@EnableSwagger2
@EnablePrometheusEndpoint
@EnableCircuitBreaker
public class RegisterOfficeConfiguration {

    @Bean
    public Docket swaggerDocketBean() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fox.registeroffice"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Конфигурация мониторинга на основе Hystrix
     *
     * @return {@link HystrixPrometheusMetricsPublisherInitializer}
     */
    @Bean
    public HystrixPrometheusMetricsPublisherInitializer prometheusMetricsPublisherInitializer() {
        HystrixPrometheusMetricsPublisher.register("register_office");
        return new HystrixPrometheusMetricsPublisherInitializer();
    }

    private class HystrixPrometheusMetricsPublisherInitializer {
    }
}
