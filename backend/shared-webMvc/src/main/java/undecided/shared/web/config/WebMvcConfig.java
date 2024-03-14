package undecided.shared.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import undecided.shared.web.logging.TraceLoggingInterceptor;
import undecided.shared.web.token.transaction.TransactionTokenInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceLoggingInterceptor());
        registry.addInterceptor(new TransactionTokenInterceptor());
    }

}
