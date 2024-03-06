package undecided.projactmgmt.request.presentation.api.feature;

import undecided.projactmgmt.request.model.feature.Feature;

public record FeatureDto(
        Long id,
        Long projectId,
        String code,
        String name,
        String description,
        String notes
) {
    public static FeatureDto convertFrom(Feature feature) {
        return new FeatureDto(feature.getId(), feature.getProjectId(), feature.getCode(), feature.getName(), feature.getDescription(), feature.getNotes());
    }

    public Feature convertAtInsertToEntity(Long projectId) {
        return Feature.createAtInsert(projectId, code, name, description, notes);
    }
}
