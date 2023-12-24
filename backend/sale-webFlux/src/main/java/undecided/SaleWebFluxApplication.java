package undecided;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SaleWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaleWebFluxApplication.class, args);
    }

}
