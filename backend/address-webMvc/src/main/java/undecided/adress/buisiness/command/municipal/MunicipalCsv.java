package undecided.adress.buisiness.command.municipal;

import undecided.adress.model.municipal.Municipal;

import java.time.LocalDate;

public class MunicipalCsv {
    /* 全国地方公共団体コード */
    String municipalCode;
    /**
     * 都道府県名
     */
    String prefectureName;
    /**
     * 都道府県名_カナ
     */
    String prefectureKanaName;

    /**
     * 都道府県名_英字
     */
    String prefectureEnName;

    /**
     * 郡名
     */
    String gunName;
    /**
     * 郡名_英字
     */
    String gunEnName;

    /**
     * 郡名_カナ
     */
    String gunKanaName;
    /**
     * 市区町村名
     */
    String cityName;

    /**
     * 市区町村名_カナ
     */
    String cityKanaName;

    /**
     * 市区町村名_英字
     */
    String cityEnName;
    /**
     * 政令市区名
     */
    String ordinanceDesignatedCityName;
    /**
     * 政令市区名_カナ
     */

    String ordinanceDesignatedCityKanaName;
    /**
     * 政令市区名_英字
     */
    String ordinanceDesignatedCityEnName;

    /* 効力発生日付 */
    LocalDate effectiveDate;
    /* 廃止日付 */
    LocalDate dateOfAbolition;

    String note;

    public Municipal toEntity() {
        return Municipal.create(municipalCode, gunName, gunKanaName, cityName, cityKanaName, ordinanceDesignatedCityName, ordinanceDesignatedCityKanaName, effectiveDate, dateOfAbolition);
    }
}
