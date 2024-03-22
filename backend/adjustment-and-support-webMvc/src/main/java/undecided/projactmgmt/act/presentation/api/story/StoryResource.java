package undecided.projactmgmt.act.presentation.api.story;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import undecided.projactmgmt.act.application.command.story.AddStoryCommand;
import undecided.projactmgmt.act.application.command.story.ApproveStoryCommand;
import undecided.projactmgmt.act.application.command.story.EstimateStoryCommand;
import undecided.projactmgmt.act.application.query.story.StoryDto;
import undecided.projactmgmt.act.model.story.Story;

@RestController
@RequestMapping(path = "/project/api/v1")
@RequiredArgsConstructor
public class StoryResource {

    private final AddStoryCommand addStoryCommand;

    private final ApproveStoryCommand approveStoryCommand;
    private final EstimateStoryCommand estimateStoryCommand;


    @PostMapping(path = "/project/{projectId}/stories")
    public StoryDto post(@PathVariable("projectId") Long projectId, @RequestBody StoryDto storyDto) {

        Story serviceRequest = storyDto.convertToEntity(projectId);
        Story story = addStoryCommand.execute(serviceRequest);
        return StoryDto.convertFromEntity(story);

    }

    @PatchMapping(path = "/project/{projectId}/stories/{storyId}/estimate")
    public StoryDto estimate(@PathVariable("projectId") Long ignoredProjectId, @PathVariable("storyId") Long storyId, @RequestBody StoryDto storyDto) {

        Story story = estimateStoryCommand.execute(storyId, storyDto.getEffort());
        return StoryDto.convertFromEntity(story);

    }

    @PatchMapping(path = "/project/{projectId}/stories/{storyId}/approve")
    public StoryDto approve(@PathVariable("projectId") Long ignoredProjectId, @PathVariable("storyId") Long storyId) {

        Story story = approveStoryCommand.execute(storyId);
        return StoryDto.convertFromEntity(story);

    }

}
