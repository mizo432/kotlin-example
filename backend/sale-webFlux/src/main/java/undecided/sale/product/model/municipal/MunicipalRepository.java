package undecided.sale.product.model.municipal;

import undecided.sale.cash.model.municipal.Municipal;
import undecided.sale.cash.model.municipal.Municipal.Municipals;

public interface MunicipalRepository {
    Municipals selectAll();

    Municipals selectByPre(String prefectureCode);

    Municipal findOneByMunicipalCode(String municipalCode);

}
