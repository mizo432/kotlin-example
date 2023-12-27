package undecided.adress.model.municipal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MunicipalTest {
    /**
     * Method under test: {@link Municipal.Municipals#Municipals(List)}
     */
    @Test
    void MunicipalsConstructor() {
        // Arrange
        ArrayList<Municipal> value = new ArrayList<>();

        // Act and Assert
        List<Municipal> value2 = (new Municipal.Municipals(value)).getValue();
        assertTrue(value2.isEmpty());
        assertEquals(value, value2);
    }

    /**
     * Method under test: {@link Municipal.Municipals#Municipals(List)}
     */
    @Test
    void MunicipalsConstructor2() {
        // Arrange
        ArrayList<Municipal> value = new ArrayList<>();
        value.add(Municipal.create(1L, 1L, "Code", "Name"));

        // Act and Assert
        List<Municipal> value2 = (new Municipal.Municipals(value)).getValue();
        assertEquals(1, value2.size());
        assertEquals(value, value2);
    }

    /**
     * Method under test: {@link Municipal.Municipals#Municipals(List)}
     */
    @Test
    void MunicipalsConstructor3() {
        // Arrange
        ArrayList<Municipal> value = new ArrayList<>();
        value.add(Municipal.create(1L, 1L, "Code", "Name"));
        value.add(Municipal.create(1L, 1L, "Code", "Name"));

        // Act and Assert
        List<Municipal> value2 = (new Municipal.Municipals(value)).getValue();
        assertEquals(2, value2.size());
        assertEquals(value, value2);
    }

    /**
     * Method under test: {@link Municipal.Municipals#getValue()}
     */
    @Test
    void MunicipalsGetValue() {
        // Arrange
        ArrayList<Municipal> value = new ArrayList<>();

        // Act and Assert
        assertEquals(value, Municipal.Municipals.of(value).getValue());
    }

    /**
     * Method under test: {@link Municipal.Municipals#of(List)}
     */
    @Test
    void MunicipalsOf() {
        // Arrange
        ArrayList<Municipal> value = new ArrayList<>();

        // Act and Assert
        List<Municipal> value2 = Municipal.Municipals.of(value).getValue();
        assertTrue(value2.isEmpty());
        assertEquals(value, value2);
    }

    /**
     * Method under test: {@link Municipal.Municipals#of(List)}
     */
    @Test
    void MunicipalsOf2() {
        // Arrange
        ArrayList<Municipal> value = new ArrayList<>();
        value.add(Municipal.create(1L, 1L, "Code", "Name"));

        // Act and Assert
        List<Municipal> value2 = Municipal.Municipals.of(value).getValue();
        assertEquals(1, value2.size());
        assertEquals(value, value2);
    }

    /**
     * Method under test: {@link Municipal.Municipals#of(List)}
     */
    @Test
    void MunicipalsOf3() {
        // Arrange
        ArrayList<Municipal> value = new ArrayList<>();
        value.add(Municipal.create(1L, 1L, "Code", "Name"));
        value.add(Municipal.create(1L, 1L, "Code", "Name"));

        // Act and Assert
        List<Municipal> value2 = Municipal.Municipals.of(value).getValue();
        assertEquals(2, value2.size());
        assertEquals(value, value2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Municipal#Municipal(Long, Long, String, String)}
     *   <li>{@link Municipal#getCode()}
     *   <li>{@link Municipal#getMunicipalId()}
     *   <li>{@link Municipal#getName()}
     *   <li>{@link Municipal#getPrefectureId()}
     * </ul>
     */
    @Test
    void constructor() {
        // Arrange and Act
        Municipal actualMunicipal = new Municipal(1L, 1L, "Code", "Name");
        String actualCode = actualMunicipal.getCode();
        Long actualMunicipalId = actualMunicipal.getMunicipalId();
        String actualName = actualMunicipal.getName();
        Long actualPrefectureId = actualMunicipal.getPrefectureId();

        // Assert
        assertEquals("Code", actualCode);
        assertEquals("Name", actualName);
        assertEquals(1L, actualMunicipalId.longValue());
        assertEquals(1L, actualPrefectureId.longValue());
    }

    /**
     * Method under test: {@link Municipal#create(Long, Long, String, String)}
     */
    @Test
    void create() {
        // Arrange and Act
        Municipal actualCreateResult = Municipal.create(1L, 1L, "Code", "Name");

        // Assert
        assertEquals("Code", actualCreateResult.getCode());
        assertEquals("Name", actualCreateResult.getName());
        assertEquals(1L, actualCreateResult.getMunicipalId().longValue());
        assertEquals(1L, actualCreateResult.getPrefectureId().longValue());
    }

    /**
     * Method under test: {@link Municipal#reconstruct(Long, Long, String, String)}
     */
    @Test
    void reconstruct() {
        // Arrange and Act
        Municipal actualReconstructResult = Municipal.create(1L, 1L, "Code", "Name").reconstruct(1L, 1L, "Code", "Name");

        // Assert
        assertEquals("Code", actualReconstructResult.getCode());
        assertEquals("Name", actualReconstructResult.getName());
        assertEquals(1L, actualReconstructResult.getMunicipalId().longValue());
        assertEquals(1L, actualReconstructResult.getPrefectureId().longValue());
    }
}
