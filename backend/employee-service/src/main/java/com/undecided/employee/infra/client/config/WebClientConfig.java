package com.undecided.employee.infra.client.config;

import com.undecided.employee.model.depertment.DepartmentClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    private final LoadBalancedExchangeFilterFunction filterFunction;

    public WebClientConfig(LoadBalancedExchangeFilterFunction filterFunction) {
        this.filterFunction = filterFunction;
    }

    @Bean
    public WebClient departmentWebClient(){
        return WebClient.builder()
                .baseUrl("http://department-service")
                .filter(filterFunction)
                .build();

    }

    @Bean
    public DepartmentClient DepartmentClient() {
        HttpServiceProxyFactory httpServiceProxyFactory;
        httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(departmentWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(DepartmentClient.class);
    }
}
