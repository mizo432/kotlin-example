package undecided.projactmgmt.act.application.command.story;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import undecided.projactmgmt.act.model.story.Story;
import undecided.projactmgmt.act.model.story.StoryRepository;

/**
 * 価値を与える
 */
@Service
@AllArgsConstructor
public class SetValueCommand {
    private final StoryRepository storyRepository;

    public Story execute(Long storyId, SetValueRequest setValueRequest) {
        Story foundStory = storyRepository.findById(storyId).orElseThrow();
        Story newStory = foundStory.setValue(setValueRequest.fullyImplementedSatisfactions(), setValueRequest.notImplementedSatisfactions());
        return storyRepository.save(newStory);

    }
}
