package undecided.projactmgmt.act.application.query.story;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import undecided.projactmgmt.act.model.story.Satisfactions;
import undecided.projactmgmt.act.model.story.Story;
import undecided.projactmgmt.act.model.story.StoryStatus;
import undecided.projactmgmt.act.model.story.StoryType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryDto {
    private Long id;
    private Long projectId;
    private String name;
    private String description;
    private String notes;
    private StoryStatus state;
    private StoryType type;
    private Integer rank;
    private Satisfactions fullyImplementedSatisfactions;
    private Satisfactions notImplementedSatisfactions;
    private Long featureId;
    private Long dependsOnStoryId;
    private Long creatorId;
    private Long parentSprintId;
    private Integer effort;
    private String affectVersion;

    public static StoryDto convertFromEntity(Story story) {
        return new StoryDto(story.getId(),
                story.getProjectId(),
                story.getName(),
                story.getDescription(),
                story.getNotes(),
                story.getState(),
                story.getType(),
                story.getRank(),
                story.getFullyImplementedSatisfactions(),
                story.getNotImplementedSatisfactions(),
                story.getFeatureId(),
                story.getDependsOnStoryId(),
                story.getCreatorId(),
                story.getParentSprintId(),
                story.getEffort(),
                story.getAffectVersion());
    }

    public Story convertToEntity(Long projectId) {
        return Story.create(projectId, name, description, notes, type, fullyImplementedSatisfactions, notImplementedSatisfactions, featureId, dependsOnStoryId, creatorId, parentSprintId, affectVersion);

    }
}
