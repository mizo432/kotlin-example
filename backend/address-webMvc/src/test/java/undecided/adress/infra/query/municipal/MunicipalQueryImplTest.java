package undecided.adress.infra.query.municipal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import undecided.adress.model.municipal.Municipal;
import undecided.adress.model.municipal.MunicipalRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MunicipalQueryImplTest {
    @Autowired
    private MunicipalQueryImpl municipalQueryImpl;

    @MockBean
    private MunicipalRepository municipalRepository;

    /**
     * Method under test: {@link MunicipalQueryImpl#findAll()}
     */
    @Test
    void selectAll() {
        // Arrange
        ArrayList<Municipal> municipalList = new ArrayList<>();
        when(municipalRepository.findAll()).thenReturn(municipalList);

        // Act
        List<Municipal> actualSelectAllResult = municipalQueryImpl.findAll();

        // Assert
        verify(municipalRepository).findAll();
        assertTrue(actualSelectAllResult.isEmpty());
        assertSame(municipalList, actualSelectAllResult);
    }

    /**
     * Method under test: {@link MunicipalQueryImpl#findOneByMunicipalCode(String)}
     */
    @Test
    void findOneByMunicipalCode() {
        // Arrange, Act and Assert
        assertNull(municipalQueryImpl.findOneByMunicipalCode("Municipal Code"));
    }

    /**
     * Method under test: {@link MunicipalQueryImpl#findByPrefectureCode(String)}
     */
    @Test
    void findByPrefectureCode() {
        // Arrange
        ArrayList<Municipal> municipalList = new ArrayList<>();
        when(municipalRepository.findByPrefectureCode(Mockito.<String>any())).thenReturn(municipalList);

        // Act
        List<Municipal> actualFindByPrefectureCodeResult = municipalQueryImpl.findByPrefectureCode("Prefecture Code");

        // Assert
        verify(municipalRepository).findByPrefectureCode(Mockito.<String>any());
        assertTrue(actualFindByPrefectureCodeResult.isEmpty());
        assertSame(municipalList, actualFindByPrefectureCodeResult);
    }
}
