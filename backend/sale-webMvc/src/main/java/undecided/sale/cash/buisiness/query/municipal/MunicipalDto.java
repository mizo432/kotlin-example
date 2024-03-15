package undecided.sale.cash.buisiness.query.municipal;

import lombok.Data;
import undecided.sale.cash.model.municipal.Municipal;

import java.util.ArrayList;
import java.util.List;

@Data
public class MunicipalDto {

    private String code;
    private String name;

    public static MunicipalDto convertFrom(Municipal municipal) {
        MunicipalDto result = new MunicipalDto();
        result.setCode(municipal.getCode());
        result.setName(municipal.getName());
        return result;
    }

    @Data
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

