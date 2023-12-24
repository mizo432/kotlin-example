package undicided.shared.flayway;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {
    @Value("${spring.flyway.url}")
    private String url;
    @Value("${spring.r2dbc.username}")
    private String user;
    @Value("${spring.r2dbc.password}")
    private String password;

    @Bean(initMethod = "migrate")
    Flyway flyway() {
        return new Flyway(
                Flyway.configure()
                        .dataSource(url, user, password)
        );
    }
}
