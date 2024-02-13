package undecided.infra.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import undecided.projactmgmt.request.model.dummy.DummyClient;
import undecided.shared.client.RestClientLoggingInterceptor;

@Configuration
public class WebClientConfig {

    @Bean
    public RestClient.Builder projectWebClientBuilder() {
        return RestClient
                .builder()
                .requestInterceptor(new RestClientLoggingInterceptor());
    }

    @Bean
    public RestClient projectWebClient(RestClient.Builder projectWebClientBuilder) {
        return projectWebClientBuilder
                .baseUrl("http://relationship-service")
                .build();
    }

    @Bean
    public DummyClient dummyClient(RestClient projectWebClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder().exchangeAdapter(RestClientAdapter.create(projectWebClient))
                .build();
        return httpServiceProxyFactory.createClient(DummyClient.class);
        
    }


}
