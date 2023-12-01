package com.undecided.inventory.model.municipal;

import com.undecided.inventory.model.municipal.Municipal.Municipals;

public interface MunicipalRepository {
    Municipals selectAll();

    Municipals selectByPre(String prefectureCode);

    Municipal findOneByMunicipalCode(String municipalCode);

}
