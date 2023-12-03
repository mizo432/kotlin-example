package com.undecided.productsale.model.municipal;

public interface MunicipalRepository {
    Municipal.Municipals selectAll();

    Municipal.Municipals selectByPre(String prefectureCode);

    Municipal findOneByMunicipalCode(String municipalCode);

}
