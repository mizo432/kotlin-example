package undicided.shared.tracefilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.context.ContextSnapshot;
import io.micrometer.observation.contextpropagation.ObservationThreadLocalAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
public class TraceIdFilter implements WebFilter {
    public static final Logger log = LoggerFactory.getLogger(TraceIdFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        long startTime = System.currentTimeMillis();
        return chain.filter(exchange)
                .contextWrite(context -> {
                    ContextSnapshot.setThreadLocalsFrom(context, ObservationThreadLocalAccessor.KEY);
                    return context;

                })
                .doFinally(signalType -> {
                    long endTime = System.currentTimeMillis();
                    long executionTime = endTime - startTime;
                    List<String> traceIds = Optional.ofNullable(exchange.getResponse().getHeaders().get("traceId")).orElse(List.of());
                    MetricsLogTemplate metricsLogTemplate = new MetricsLogTemplate(
                            String.join(",", traceIds),
                            exchange.getLogPrefix().trim(),
                            executionTime
                    );
                    try {
                        log.info(new ObjectMapper().writeValueAsString(metricsLogTemplate));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });


    }

    record MetricsLogTemplate(String traceId, String logPrefix, long executionTime) {
    }
}
