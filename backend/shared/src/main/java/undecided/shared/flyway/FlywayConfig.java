package undecided.shared.flyway;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    private static final Logger log = LoggerFactory.getLogger(FlywayConfig.class);
    @Value("${spring.flyway.url}")
    private String url;
    @Value("${spring.r2dbc.username}")
    private String user;
    @Value("${spring.r2dbc.password}")
    private String password;

    @Bean(initMethod = "migrate")
    Flyway flyway() {
        log.info("url:" + url);
        log.info("user:" + user);
        log.info("password:" + password);
        return new Flyway(
                Flyway.configure()
                        .dataSource(url, user, password)
        );
    }
}
