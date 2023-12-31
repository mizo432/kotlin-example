package undecided.sale.cash.buisiness.query.municipal;

import undecided.sale.cash.model.municipal.Municipal;

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

    public static MunicipalDto convertFrom(Municipal municipal) {
        MunicipalDto result = new MunicipalDto();
        result.setCode(municipal.getCode());
        result.setName(municipal.getName());
        return result;
    }


    public static class MunicipalsDto {

        public static List<MunicipalDto> convertFrom(Municipal.Municipals municipals) {
            List<MunicipalDto> result = new ArrayList<>();
            for (Municipal municipal : municipals.getValue()) {
                result.add(MunicipalDto.convertFrom(municipal));

            }
            return result;

        }
    }

}
