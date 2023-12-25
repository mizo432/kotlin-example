package undecided.shared.tracefilter;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

@Configuration(proxyBeanMethods = false)
public class TraceConfig {
    @Bean
    WebFilter traceIdInResponseFilter(Tracer tracer) {
        return ((exchange, chain) -> {
            Span currentSpan = tracer.currentSpan();
            if (currentSpan != null) {
                exchange.getResponse().getHeaders().add("traceId", currentSpan.context().traceId());
            }
            return chain.filter(exchange);

        });

    }
}
