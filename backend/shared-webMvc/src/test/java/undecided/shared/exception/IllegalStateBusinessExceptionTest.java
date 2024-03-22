package undecided.shared.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import undecided.shared.message.ResultMessage;

class IllegalStateBusinessExceptionTest {
    /**
     * Method under test:
     * {@link IllegalStateBusinessException#IllegalStateBusinessException(String)}
     */
    @Test
    void constructor() {
        // Arrange and Act
        IllegalStateBusinessException actualIllegalStateBusinessException = new IllegalStateBusinessException(
                "An error occurred");

        // Assert
        List<ResultMessage> list = actualIllegalStateBusinessException.getResultMessages().getList();
        assertEquals(1, list.size());
        ResultMessage getResult = list.get(0);
        assertEquals("An error occurred", getResult.text());
        assertEquals("ResultMessages [type=error, list=[ResultMessage [code=null, args=[], text=An error occurred]]]",
                actualIllegalStateBusinessException.getLocalizedMessage());
        assertNull(getResult.code());
    }
}
