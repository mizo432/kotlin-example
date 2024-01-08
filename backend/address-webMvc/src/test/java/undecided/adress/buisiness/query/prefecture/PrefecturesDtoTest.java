package undecided.adress.buisiness.query.prefecture;

import org.junit.jupiter.api.Test;
import undecided.adress.model.prefecture.Prefecture;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrefecturesDtoTest {
    /**
     * Method under test: {@link PrefectureDto.PrefecturesDto#convertFrom(Prefecture[])}
     */
    @Test
    void convertFrom() {
        // Arrange and Act
        PrefectureDto[] actualConvertFromResult = PrefectureDto.PrefecturesDto.convertFrom(new Prefecture[]{Prefecture.北海道});

        // Assert
        PrefectureDto prefectureDto = actualConvertFromResult[0];
        assertEquals("01", prefectureDto.code());
        assertEquals("北海道", prefectureDto.name());
        assertEquals(1, actualConvertFromResult.length);
        
    }
}
