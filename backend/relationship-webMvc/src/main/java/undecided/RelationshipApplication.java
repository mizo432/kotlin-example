package undecided;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import reactor.core.publisher.Hooks;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
public class RelationshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelationshipApplication.class, args);
        Hooks.enableAutomaticContextPropagation();
    }

}
