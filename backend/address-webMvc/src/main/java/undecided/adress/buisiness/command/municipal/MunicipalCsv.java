package undecided.adress.buisiness.command.municipal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import undecided.adress.model.municipal.Municipal;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "全国地方公共団体コード",
        "都道府県名",
        "都道府県名_カナ",
        "都道府県名_英字",
        "郡名",
        "郡名_カナ",
        "郡名_英字",
        "市区町村名",
        "市区町村名_カナ",
        "市区町村名_英字",
        "政令市区名",
        "政令市区名_カナ",
        "政令市区名_英字",
        "効力発生日",
        "廃止日",
        "備考"
})
public class MunicipalCsv {
    /**
     * 全国地方公共団体コード
     */
    @JsonProperty("全国地方公共団体コード")
    private String municipalCode;
    /**
     * 都道府県名
     */
    @JsonProperty("都道府県名")
    String prefectureName;

    /**
     * 都道府県名_カナ
     */
    @JsonProperty("都道府県名_カナ")
    String prefectureKanaName;

    /**
     * 都道府県名_英字
     */
    @JsonProperty("都道府県名_英字")
    String prefectureEnName;

    /**
     * 郡名
     */
    @JsonProperty("郡名")
    String gunName;
    /**
     * 郡名_カナ
     */
    @JsonProperty("郡名_カナ")
    String gunKanaName;
    /**
     * 郡名_英字
     */
    @JsonProperty("郡名_英字")
    String gunEnName;

    /**
     * 市区町村名
     */
    @JsonProperty("市区町村名")
    String cityName;

    /**
     * 市区町村名_カナ
     */
    @JsonProperty("市区町村名_カナ")
    String cityKanaName;

    /**
     * 市区町村名_英字
     */
    @JsonProperty("市区町村名_英字")
    String cityEnName;
    /**
     * 政令市区名
     */
    @JsonProperty("政令市区名")
    String ordinanceDesignatedCityName;
    /**
     * 政令市区名_カナ
     */
    @JsonProperty("政令市区名_カナ")
    String ordinanceDesignatedCityKanaName;
    /**
     * 政令市区名_英字
     */
    @JsonProperty("政令市区名_英字")
    String ordinanceDesignatedCityEnName;

    /* 効力発生日付 */
    @JsonProperty("効力発生日")
    LocalDate effectiveDate;
    /* 廃止日付 */
    @JsonProperty("廃止日")
    LocalDate dateOfAbolition;

    @JsonProperty("備考")
    String note;

    public Municipal toEntity() {
        return Municipal.create(municipalCode, gunName, gunKanaName, cityName, cityKanaName, ordinanceDesignatedCityName, ordinanceDesignatedCityKanaName, effectiveDate, dateOfAbolition);
    }
}
