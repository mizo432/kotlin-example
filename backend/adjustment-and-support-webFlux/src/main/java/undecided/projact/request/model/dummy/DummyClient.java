package undecided.projact.request.model.dummy;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

@HttpExchange
public interface DummyClient {
    @GetExchange("/api/v1/dummies")
    Mono<String> getMessage();

}
