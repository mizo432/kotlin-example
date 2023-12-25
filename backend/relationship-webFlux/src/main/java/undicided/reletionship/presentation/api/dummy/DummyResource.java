package undicided.reletionship.presentation.api.dummy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/dummies")
public class DummyResource {

    @GetMapping
    Mono<String> getMessage() {
        return Mono.just("Hi I am vaifam.");
    }

}
