package undecided.adress.presentation.api.prefecture;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import undecided.adress.model.prefecture.Prefecture;

class PrefecturesDtoTest {
    /**
     * Method under test: {@link PrefecturesDto#convertFrom(Prefecture[])}
     */
    @Test
    void convertFrom() {
        // Arrange and Act
        PrefectureDto[] actualConvertFromResult = PrefecturesDto.convertFrom(new Prefecture[]{Prefecture.北海道});

        // Assert
        PrefectureDto prefectureDto = actualConvertFromResult[0];
        assertEquals("01", prefectureDto.code());
        assertEquals("北海道", prefectureDto.name());
        assertEquals(1, actualConvertFromResult.length);
    }
}
