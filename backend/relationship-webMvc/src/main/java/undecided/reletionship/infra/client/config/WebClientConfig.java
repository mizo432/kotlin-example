package undecided.reletionship.infra.client.config;

import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
// @Configuration
public class WebClientConfig {


/*
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
*/
}
