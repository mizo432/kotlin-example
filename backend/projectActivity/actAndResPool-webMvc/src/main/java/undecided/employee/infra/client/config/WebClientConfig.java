package undecided.employee.infra.client.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import undecided.employee.model.department.DepartmentClient;
import undecided.employee.model.prefecture.PrefectureClient;

@Configuration(proxyBeanMethods = false)
// @Configuration
public class WebClientConfig {


    @Bean("departmentRestClient")
    @LoadBalanced
    public RestClient departmentRestClient() {
        return RestClient.builder()
                .baseUrl("http://department-service")
//                .baseUrl("http://localhost:8081")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean("addressRestClient")
    @LoadBalanced
    public RestClient addressRestClient() {
        return RestClient.builder()
                .baseUrl("http://address-service")
//                .baseUrl("http://localhost:8085")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public DepartmentClient departmentClient(@Qualifier("departmentRestClient") RestClient restClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient)
                ).build();
        return httpServiceProxyFactory.createClient(DepartmentClient.class);
    }

    @Bean
    public PrefectureClient prefectureClient(@Qualifier("addressRestClient") RestClient restClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient)
                ).build();
        return httpServiceProxyFactory.createClient(PrefectureClient.class);
    }


}
