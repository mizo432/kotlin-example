package undecided.projactmgmt.act.presentation.api.story;

import org.springframework.web.bind.annotation.*;
import undecided.projactmgmt.act.application.command.story.AddStoryCommand;
import undecided.projactmgmt.act.application.query.story.StoryDto;
import undecided.projactmgmt.act.model.story.Story;

@RestController
@RequestMapping(path = "/project/api/v1")
public class StoryResource {

    private final AddStoryCommand addStoryCommand;

    public StoryResource(AddStoryCommand addStoryCommand) {
        this.addStoryCommand = addStoryCommand;
    }


    @PostMapping(path = "/project/{projectId}/stories")
    public StoryDto post(@PathVariable("projectId") Long projectId, @RequestBody StoryDto storyDto) {

        Story serviceRequest = storyDto.convertToEntity(projectId);
        Story story = addStoryCommand.execute(serviceRequest);
        return StoryDto.convertFromEntity(story);

    }
}
