package com.undecided.inventory.model.prefecture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.undecided.productsale.model.prefecture.PrefectureCode;
import org.junit.jupiter.api.Test;

class PrefectureCodeTest {
    /**
     * Method under test: {@link PrefectureCode#of(String)}
     */
    @Test
    void testOf() {
        assertThrows(IllegalArgumentException.class, () -> PrefectureCode.of("Value"));
        assertEquals("42", PrefectureCode.of("42").getValue());
    }

    /**
     * Method under test: {@link PrefectureCode#reconstruct(String)}
     */
    @Test
    void testReconstruct() {
        assertEquals("42", PrefectureCode.reconstruct("42").getValue());
    }

    /**
     * Method under test: {@link PrefectureCode#toString()}
     */
    @Test
    void testToString() {
        // Arrange
        PrefectureCode prefectureCode = PrefectureCode.of("42");

        // Act
        String actualToStringResult = prefectureCode.toString();

        // Assert
        assertEquals("42", actualToStringResult);
    }
}
