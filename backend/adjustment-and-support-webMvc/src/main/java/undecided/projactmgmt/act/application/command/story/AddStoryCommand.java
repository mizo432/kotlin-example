package undecided.projactmgmt.act.application.command.story;

import org.springframework.stereotype.Service;
import undecided.projactmgmt.act.model.story.Story;
import undecided.projactmgmt.act.model.story.StoryRepository;

@Service
public class AddStoryCommand {

    private final StoryRepository storyRepository;

    public AddStoryCommand(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public Story execute(Story story) {
        return storyRepository.save(story);
    }
}
