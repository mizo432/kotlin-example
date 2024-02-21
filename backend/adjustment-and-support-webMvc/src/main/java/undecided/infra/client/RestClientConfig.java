package undecided.infra.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.Builder;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import undecided.projactmgmt.request.model.dummy.DummyClient;
import undecided.shared.client.RestClientLoggingInterceptor;

@Configuration
public class RestClientConfig {

    @Bean
    public Builder projectRestClientBuilder() {
        return RestClient
                .builder()
                .requestInterceptor(new RestClientLoggingInterceptor());
    }

    @Bean(name = "relationshipRestClient")
    public RestClient relationshipRestClient(Builder projectRestClientBuilder) {
        return projectRestClientBuilder
                .baseUrl("http://relationship-service")
                .build();
    }

    @Bean
    public DummyClient dummyClient(@Qualifier("relationshipRestClient") RestClient relationshipRestClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.
                builder().
                exchangeAdapter(RestClientAdapter.create(relationshipRestClient)).
                build();
        return httpServiceProxyFactory.createClient(DummyClient.class);

    }


}
