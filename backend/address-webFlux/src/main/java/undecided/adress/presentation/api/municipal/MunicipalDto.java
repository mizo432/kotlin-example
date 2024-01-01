package undecided.adress.presentation.api.municipal;

import undecided.adress.model.municipal.Municipal;
import undecided.adress.model.municipal.Municipal.Municipals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record MunicipalDto(        /* 市区町村ID */
        Long municipalId,
        /* 都道府県コード */
        String prefectureCode,
        /* 全国地方公共団体コード */
        String municipalCode,
        /* 郡名 */
        String gunName,
        /* 郡名_カナ */
        String gunKanaName,
        /* 市区町村名 */
        String cityName,
        /* 市区町村名_カナ */
        String cityKanaName,
        /* 政令市区名 */
        String ordinanceDesignatedCityName,
        /* 政令市区名_カナ */

        String ordinanceDesignatedCityKanaName,

        /* 効力発生日付 */
        LocalDate effectiveDate,
        /* 廃止日付 */
        LocalDate dateOfAbolition
) {

    ;


    public static MunicipalDto reconstruct(Municipal municipal) {
        return new MunicipalDto(
                municipal.municipalId(),
                municipal.municipalCode(),
                municipal.prefectureCode(),
                municipal.gunName(),
                municipal.gunKanaName(),
                municipal.cityName(),
                municipal.cityKanaName(),
                municipal.ordinanceDesignatedCityName(), municipal.
                ordinanceDesignatedCityKanaName(),
                municipal.effectiveDate(),
                municipal.dateOfAbolition());

    }

    public static class MunicipalsDto {

        public static List<MunicipalDto> convertFrom(Municipals municipals) {
            List<MunicipalDto> result = new ArrayList<>();
            for (Municipal municipal : municipals.getValue()) {
                result.add(MunicipalDto.reconstruct(municipal));

            }
            return result;

        }
    }

}
