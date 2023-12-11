package undecided.sale.product.buisiness.query.municipal;

import undecided.sale.cash.model.municipal.Municipal;
import undecided.sale.cash.model.municipal.Municipal.Municipals;

import java.util.ArrayList;
import java.util.List;

public class MunicipalDto {
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static undecided.sale.cash.buisiness.query.municipal.MunicipalDto convertFrom(Municipal municipal) {
        undecided.sale.cash.buisiness.query.municipal.MunicipalDto result = new undecided.sale.cash.buisiness.query.municipal.MunicipalDto();
        result.setCode(municipal.getCode());
        result.setName(municipal.getName());
        return result;
    }


    public static class MunicipalsDto {

        public static List<undecided.sale.cash.buisiness.query.municipal.MunicipalDto> convertFrom(Municipals municipals) {
            List<undecided.sale.cash.buisiness.query.municipal.MunicipalDto> result = new ArrayList<>();
            for (Municipal municipal : municipals.getValue()) {
                result.add(undecided.sale.cash.buisiness.query.municipal.MunicipalDto.convertFrom(municipal));

            }
            return result;

        }
    }

}
