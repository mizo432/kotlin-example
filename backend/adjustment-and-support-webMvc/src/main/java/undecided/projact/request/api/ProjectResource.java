package undecided.projact.request.api;

import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import undecided.projact.request.model.dummy.DummyClient;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectResource {
    private final DummyClient dummyClient;

    public ProjectResource(DummyClient dummyClient) {
        this.dummyClient = dummyClient;
    }

    @RequestMapping
    @Observed
    public Mono<String> getMessage() {
        return dummyClient.getMessage();

    }

}
