package undecided.adress.model.prefecture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class PrefectureTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Prefecture#getCode()}
     *   <li>{@link Prefecture#getName()}
     * </ul>
     */
    @Test
    void getCode() {
        // Arrange
        Prefecture valueOfResult = Prefecture.valueOf("北海道");

        // Act
        String actualCode = valueOfResult.getCode();

        // Assert
        assertEquals("01", actualCode);
        assertEquals("北海道", valueOfResult.getName());
    }

    /**
     * Method under test: {@link Prefecture#valueOfCode(String)}
     */
    @Test
    void valueOfCode() {
        // Arrange, Act and Assert
        assertNull(Prefecture.valueOfCode("Prefecture Code"));
        assertEquals(Prefecture.北海道, Prefecture.valueOfCode("01"));
        assertEquals(Prefecture.青森県, Prefecture.valueOfCode("02"));
        assertEquals(Prefecture.岩手県, Prefecture.valueOfCode("03"));
        assertEquals(Prefecture.宮城県, Prefecture.valueOfCode("04"));
        assertEquals(Prefecture.秋田県, Prefecture.valueOfCode("05"));
        assertEquals(Prefecture.山形県, Prefecture.valueOfCode("06"));
        assertEquals(Prefecture.長崎県, Prefecture.valueOfCode("42"));
        assertEquals(Prefecture.福島県, Prefecture.valueOfCode("07"));
        assertEquals(Prefecture.茨城県, Prefecture.valueOfCode("08"));
        assertEquals(Prefecture.栃木県, Prefecture.valueOfCode("09"));
        assertEquals(Prefecture.群馬県, Prefecture.valueOfCode("10"));
        assertEquals(Prefecture.埼玉県, Prefecture.valueOfCode("11"));
        assertEquals(Prefecture.千葉県, Prefecture.valueOfCode("12"));
        assertEquals(Prefecture.東京都, Prefecture.valueOfCode("13"));
        assertEquals(Prefecture.神奈川県, Prefecture.valueOfCode("14"));
        assertEquals(Prefecture.新潟県, Prefecture.valueOfCode("15"));
        assertEquals(Prefecture.富山県, Prefecture.valueOfCode("16"));
    }
}
