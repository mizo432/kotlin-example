package undecided.projactmgmt.act.application.command.story;

import org.springframework.stereotype.Service;
import undecided.projactmgmt.act.model.story.Story;
import undecided.projactmgmt.act.model.story.StoryRepository;
import undecided.shared.exception.ResourceNotFoundException;

/**
 * ストーリー承認
 */
@Service
public class ApproveStoryCommand {
    private final StoryRepository storyRepository;

    public ApproveStoryCommand(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public Story execute(Long storyId) {
        Story oldStory = storyRepository.findById(storyId).orElseThrow(() -> new ResourceNotFoundException("対象のストーリーが見つかりません storyId:" + storyId));
        Story newStory = oldStory.approve();
        return storyRepository.save(newStory);

    }

}
