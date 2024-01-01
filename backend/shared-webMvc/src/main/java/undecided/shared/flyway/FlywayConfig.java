package undecided.shared.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Value("${spring.flyway.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean(initMethod = "migrate")
    Flyway flyway() {
        return new Flyway(
                Flyway.configure()
                        .dataSource(url, user, password)
        );
    }
}
