package undecided.adress.model.municipal;

import undecided.adress.model.municipal.Municipal.Municipals;

public interface MunicipalRepository {
    Municipals selectAll();

    Municipals selectByPre(String prefectureCode);

    Municipal findOneByMunicipalCode(String municipalCode);

}
