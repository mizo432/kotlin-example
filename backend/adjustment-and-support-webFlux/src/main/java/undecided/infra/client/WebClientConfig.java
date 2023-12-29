package undecided.infra.client;

import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import undecided.projact.request.model.dummy.DummyClient;

@Configuration
public class WebClientConfig {

    private final LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

    public WebClientConfig(LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction) {
        this.loadBalancedExchangeFilterFunction = loadBalancedExchangeFilterFunction;
    }

    @Bean
    public WebClient.Builder projectWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient projectWebClient(WebClient.Builder projectWebClientBuilder) {
        return projectWebClientBuilder
                .baseUrl("http://relationship-service")
                .filter(loadBalancedExchangeFilterFunction)
                .build();
    }

    @Bean
    public DummyClient dummyClient(WebClient projectWebClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder().exchangeAdapter(WebClientAdapter.create(projectWebClient))
                .build();
        return httpServiceProxyFactory.createClient(DummyClient.class);
    }


}
