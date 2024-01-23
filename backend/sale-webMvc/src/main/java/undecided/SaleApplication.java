package undecided;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import undecided.shared.entity.id.ApplicationInfoInitializer;

@SpringBootApplication
@EnableDiscoveryClient
public class SaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaleApplication.class, args);
    }

    @Value("spring.application.name")
    private String applicationNane;
    @Value("server.port")
    private String serverPort;

    @EventListener
    public void handleContextRefreshEvent(ContextStartedEvent ctxStartEvt) {
        ApplicationInfoInitializer.initialize(applicationNane, Long.valueOf(serverPort));

    }

}
