package undecided.employee.infra.client.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration(proxyBeanMethods = false)
// @Configuration
public class WebClientConfig {


    @Bean("departmentRestClient")
    @LoadBalanced
    public RestClient departmentRestClient() {
        return RestClient.builder()
                .baseUrl("http://department-service")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean("addressRestClient")
    @LoadBalanced
    public RestClient addressRestClient() {
        return RestClient.builder()
                .baseUrl("http://address-service")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
