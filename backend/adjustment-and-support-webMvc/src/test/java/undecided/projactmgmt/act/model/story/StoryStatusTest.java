package undecided.projactmgmt.act.model.story;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StoryStatusTest {

    /**
     * Method under test: {@link StoryStatus#canEstimate()}
     */
    @Test
    void canEstimate() {
        // Arrange and Act
        assertFalse(StoryStatus.SUGGESTED.canEstimate());
        assertTrue(StoryStatus.ACCEPTED.canEstimate());
        assertTrue(StoryStatus.ESTIMATED.canEstimate());
    }
}
