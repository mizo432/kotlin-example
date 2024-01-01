package undecided.adress.model.municipal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipalRepository extends JpaRepository<Municipal, Long> {

    List<Municipal> findByPrefectureCode(String prefectureCode);

    Municipal findByMunicipalCode(String municipalCode);

}
