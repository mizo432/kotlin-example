package undecided.adress.buisiness.query.municipal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import undecided.adress.model.municipal.Municipal;

class MunicipalDtoTest {
    /**
     * Method under test:
     * {@link MunicipalDto.MunicipalsDto#convertFrom(Municipal.Municipals)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void MunicipalsDtoConvertFrom() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "undecided.adress.model.municipal.Municipal$Municipals.getValue()" because "municipals" is null
        //       at undecided.adress.buisiness.query.municipal.MunicipalDto$MunicipalsDto.convertFrom(MunicipalDto.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        MunicipalDto.MunicipalsDto.convertFrom(null);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link MunicipalDto#setCode(String)}
     *   <li>{@link MunicipalDto#setName(String)}
     *   <li>{@link MunicipalDto#getCode()}
     *   <li>{@link MunicipalDto#getName()}
     * </ul>
     */
    @Test
    void setCode() {
        // Arrange
        MunicipalDto municipalDto = new MunicipalDto();

        // Act
        municipalDto.setCode("Code");
        municipalDto.setName("Name");
        String actualCode = municipalDto.getCode();

        // Assert that nothing has changed
        assertEquals("Code", actualCode);
        assertEquals("Name", municipalDto.getName());
    }

    /**
     * Method under test: {@link MunicipalDto#convertFrom(Municipal)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void convertFrom() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "undecided.adress.model.municipal.Municipal.getCode()" because "municipal" is null
        //       at undecided.adress.buisiness.query.municipal.MunicipalDto.convertFrom(MunicipalDto.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        MunicipalDto.convertFrom(null);
    }
}
