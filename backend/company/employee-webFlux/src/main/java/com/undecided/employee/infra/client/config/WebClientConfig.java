package com.undecided.employee.infra.client.config;

import com.undecided.employee.model.department.DepartmentClient;
import com.undecided.employee.model.prefecture.PrefectureClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration(proxyBeanMethods = false)
// @Configuration
public class WebClientConfig {

    private final LoadBalancedExchangeFilterFunction filterFunction;

    public WebClientConfig(LoadBalancedExchangeFilterFunction filterFunction) {
        this.filterFunction = filterFunction;
    }

    @Bean("departmentWebClient")
    public WebClient departmentWebClient() {
        return WebClient.builder()
                .baseUrl("http://department-service")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(filterFunction)
                .build();

    }

    @Bean("addressWebClient")
    public WebClient addressWebClient() {
        return WebClient.builder()
                .baseUrl("http://address-service")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(filterFunction)
                .build();

    }

    @Bean
    public PrefectureClient prefectureClient(@Qualifier("addressWebClient") WebClient webClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient)
                ).build();
        return httpServiceProxyFactory.createClient(PrefectureClient.class);
    }

    @Bean
    public DepartmentClient departmentClient(@Qualifier("departmentWebClient") WebClient webClient) {
        HttpServiceProxyFactory httpServiceProxyFactory;
        httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();
        return httpServiceProxyFactory.createClient(DepartmentClient.class);
    }
}
