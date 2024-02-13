package undecided.projactmgmt.request.presentation.api;

import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import undecided.projactmgmt.request.model.dummy.DummyClient;

import java.util.List;

@RestController
@RequestMapping("/project/api/v1/projects")
public class ProjectResource {
    private final DummyClient dummyClient;

    public ProjectResource(DummyClient dummyClient) {
        this.dummyClient = dummyClient;
    }

    @RequestMapping
    @Observed
    public List<String> getMessage() {
        return dummyClient.getMessage();

    }

}
