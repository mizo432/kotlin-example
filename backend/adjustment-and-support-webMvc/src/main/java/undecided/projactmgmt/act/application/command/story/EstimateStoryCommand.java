package undecided.projactmgmt.act.application.command.story;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import undecided.projactmgmt.act.model.story.Story;
import undecided.projactmgmt.act.model.story.StoryRepository;
import undecided.shared.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class EstimateStoryCommand {
    private final StoryRepository storyRepository;

    public Story execute(Long storyId, Integer effort) {
        Story oldStory = storyRepository.findById(storyId).orElseThrow(() -> new ResourceNotFoundException("対象のストーリーが見つかりません storyId:" + storyId));
        Story newStory = oldStory.estimate(effort);
        return storyRepository.save(newStory);

    }

}
