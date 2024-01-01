package undecided.adress.buisiness.query.municipal;

import undecided.adress.model.municipal.Municipal;

import java.util.List;

public interface MunicipalQuery {
    List<Municipal> selectByPrefectureCode(String prefectureCode);

    List<Municipal> selectAll();

    Municipal findOneByMunicipalCode(String cityCode);

}
