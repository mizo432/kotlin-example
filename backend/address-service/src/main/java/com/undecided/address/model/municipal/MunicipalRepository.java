package com.undecided.address.model.municipal;

import com.undecided.address.model.municipal.Municipal.Municipals;

public interface MunicipalRepository {
    Municipals selectAll();

    Municipals selectByPre(String prefectureCode);

    Municipal findOneByMunicipalCode(String municipalCode);

}
