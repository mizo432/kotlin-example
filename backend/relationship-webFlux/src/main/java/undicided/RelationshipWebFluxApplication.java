package undicided;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import reactor.core.publisher.Hooks;

@SpringBootApplication
@EnableDiscoveryClient
public class RelationshipWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelationshipWebFluxApplication.class, args);
        Hooks.enableAutomaticContextPropagation();
    }

}
