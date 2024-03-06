package undecided.projactmgmt.request.presentation.api.feature;

import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import undecided.projactmgmt.request.appl.command.feature.AddFeatureCommand;

@RestController
@RequestMapping("/project/api/v1/projects")
public class FeatureResource {

    private final AddFeatureCommand addFeatureCommand;

    public FeatureResource(AddFeatureCommand addFeatureCommand) {
        this.addFeatureCommand = addFeatureCommand;
    }

    @RequestMapping(path = "/{projectId}/features", method = RequestMethod.POST)
    @Observed
    public FeatureDto post(Long projectId, @RequestBody FeatureDto featureDto) {
        return FeatureDto.convertFrom(addFeatureCommand.execute(featureDto.convertAtInsertToEntity(projectId)));

    }
}
