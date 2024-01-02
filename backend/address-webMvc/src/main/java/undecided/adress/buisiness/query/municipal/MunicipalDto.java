package undecided.adress.buisiness.query.municipal;

import undecided.adress.model.municipal.Municipal;

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
                municipal.getMunicipalId(),
                municipal.getPrefectureCode(),
                municipal.getMunicipalCode(),
                municipal.getGunName(),
                municipal.getGunKanaName(),
                municipal.getCityName(),
                municipal.getCityKanaName(),
                municipal.getOrdinanceDesignatedCityName(),
                municipal.getOrdinanceDesignatedCityKanaName(),
                municipal.getEffectiveDate(),
                municipal.getDateOfAbolition());

    }

    public static List<MunicipalDto> reconstruct(List<Municipal> municipals) {
        List<MunicipalDto> result = new ArrayList<>();
        for (Municipal municipal : municipals) {
            result.add(MunicipalDto.reconstruct(municipal));

        }
        return result;
    }

}
