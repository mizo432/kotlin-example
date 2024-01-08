package undecided.adress.buisiness.query.municipal;

import undecided.adress.model.municipal.Municipal;

import java.util.List;

public interface MunicipalQuery {
    List<Municipal> findAll();

    Municipal findOneByMunicipalCode(String cityCode);

    List<Municipal> findByPrefectureCode(String prefectureCode);

}
