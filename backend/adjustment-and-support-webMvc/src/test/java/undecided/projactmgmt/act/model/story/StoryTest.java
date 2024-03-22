package undecided.projactmgmt.act.model.story;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import undecided.shared.exception.IllegalStateBusinessException;

@ContextConfiguration(classes = {Story.class})
@ExtendWith(SpringExtension.class)
class StoryTest {
    @Autowired
    private Story story;

    /**
     * Method under test: {@link Story#approve()}
     */
    @Test
    void approve() {
        // Arrange, Act and Assert
        assertThrows(IllegalStateBusinessException.class, () -> (new Story()).approve());
    }
}
