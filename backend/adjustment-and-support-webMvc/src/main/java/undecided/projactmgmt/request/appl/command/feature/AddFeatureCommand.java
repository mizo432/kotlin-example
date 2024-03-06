package undecided.projactmgmt.request.appl.command.feature;

import org.springframework.stereotype.Service;
import undecided.projactmgmt.request.model.feature.Feature;
import undecided.projactmgmt.request.model.feature.FeatureRepository;

@Service
public class AddFeatureCommand {
    private final FeatureRepository featureRepository;

    public AddFeatureCommand(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    public Feature execute(Feature feature) {
        return featureRepository.save(feature);

    }
}
