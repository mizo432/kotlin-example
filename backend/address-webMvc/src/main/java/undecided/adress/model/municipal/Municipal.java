package undecided.adress.model.municipal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import undecided.shared.entity.id.SnowflakeId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
全国地方公共団体コード
 */
@Table(schema = "address", name = "municipals")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Municipal {
    /* 市区町村ID */
    @Id
    private Long municipalId;
    /* 都道府県コード */
    private String prefectureCode;
    /* 全国地方公共団体コード */
    private String municipalCode;
    /* 郡名 */
    private String gunName;
    /* 郡名_カナ */
    private String gunKanaName;
    /* 市区町村名 */
    private String cityName;
    /* 市区町村名_カナ */
    private String cityKanaName;
    /* 政令市区名 */
    private String ordinanceDesignatedCityName;
    /* 政令市区名_カナ */

    private String ordinanceDesignatedCityKanaName;

    /* 効力発生日付 */
    private LocalDate effectiveDate;
    /* 廃止日付 */
    private LocalDate dateOfAbolition;

    /**
     * ファクトリ＾メソッド
     *
     * @param municipalId                     市区町村ID
     * @param prefectureCode                  都道府県コード
     * @param municipalCode                   全国地方公共団体コード
     * @param gunName                         郡名
     * @param gunKanaName                     郡名_カナ
     * @param cityName                        市区町村名
     * @param cityKanaName                    市区町村名_カナ
     * @param ordinanceDesignatedCityName     政令市区名
     * @param ordinanceDesignatedCityKanaName 政令市区名_カナ
     * @param effectiveDate                   効力発生日付
     * @param dateOfAbolition                 廃止日付
     * @return 市区町村
     */
    public static Municipal create(        /* 市区町村ID */
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
        return new Municipal(municipalId, prefectureCode, municipalCode, gunName, gunKanaName, cityName, cityKanaName, ordinanceDesignatedCityName, ordinanceDesignatedCityKanaName, effectiveDate, dateOfAbolition);

    }

    /**
     * 再生成メソッド
     *
     * @param municipalId                     市区町村ID
     * @param prefectureCode                  都道府県コード
     * @param municipalCode                   全国地方公共団体コード
     * @param gunName                         郡名
     * @param gunKanaName                     郡名_カナ
     * @param cityName                        市区町村名
     * @param cityKanaName                    市区町村名_カナ
     * @param ordinanceDesignatedCityName     政令市区名
     * @param ordinanceDesignatedCityKanaName 政令市区名_カナ
     * @param effectiveDate                   効力発生日付
     * @param dateOfAbolition                 廃止日付
     * @return 市区町村
     */
    public static Municipal reconstruct(
            Long municipalId,
            String prefectureCode,
            String municipalCode,
            String gunName,
            String gunKanaName,
            String cityName,
            String cityKanaName,
            String ordinanceDesignatedCityName,
            String ordinanceDesignatedCityKanaName,
            LocalDate effectiveDate,
            LocalDate dateOfAbolition) {
        return new Municipal(municipalId, prefectureCode, municipalCode, gunName, gunKanaName, cityName, cityKanaName, ordinanceDesignatedCityName, ordinanceDesignatedCityKanaName, effectiveDate, dateOfAbolition);
    }

    /**
     * ファクトリ＾メソッド
     *
     * @param municipalCode                   全国地方公共団体コード
     * @param gunName                         郡名
     * @param gunKanaName                     郡名_カナ
     * @param cityName                        市区町村名
     * @param cityKanaName                    市区町村名_カナ
     * @param ordinanceDesignatedCityName     政令市区名
     * @param ordinanceDesignatedCityKanaName 政令市区名_カナ
     * @param effectiveDate                   効力発生日付
     * @param dateOfAbolition                 廃止日付
     * @return 市区町村
     */
    public static Municipal create(String municipalCode, String gunName, String gunKanaName, String cityName, String cityKanaName, String ordinanceDesignatedCityName, String ordinanceDesignatedCityKanaName, LocalDate effectiveDate, LocalDate dateOfAbolition) {
        Long municipalId = SnowflakeId.newInstance().getValue();
        return new Municipal(municipalId, municipalCode.substring(0, 2), municipalCode, gunName, gunKanaName, cityName, cityKanaName, ordinanceDesignatedCityName, ordinanceDesignatedCityKanaName, effectiveDate, dateOfAbolition);

    }


    @Getter
    public static class Municipals {
        private final List<Municipal> value = new ArrayList<>();

        Municipals(List<Municipal> value) {
            this.value.addAll(value);
        }

        public static Municipals of(List<Municipal> value) {
            return new Municipals(value);

        }
    }
}
