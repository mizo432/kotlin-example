package undecided.sale.cash.model.municipal;


import java.util.ArrayList;
import java.util.List;

public class Municipal {
    private final Long municipalId;
    private final Long prefectureId;
    private final String code;
    private final String name;


    Municipal(Long municipalId, Long prefectureId, String code, String name) {
        this.municipalId = municipalId;
        this.prefectureId = prefectureId;
        this.code = code;
        this.name = name;
    }

    public static Municipal create(Long municipalId, Long prefectureId, String code, String name) {
        return new Municipal(municipalId, prefectureId, code, name);

    }

    Municipal reconstruct(Long municipalId, Long prefectureId, String code, String name) {
        return new Municipal(municipalId, prefectureId, code, name);
    }

    public static class Municipals {
        List<Municipal> value = new ArrayList<>();

        public List<Municipal> getValue() {
            return value;
        }

        Municipals(List<Municipal> value) {
            this.value = value;
        }

        public static Municipals of(List<Municipal> value) {
            return new Municipals(value);
        }
    }

    public Long getMunicipalId() {
        return municipalId;
    }

    public Long getPrefectureId() {
        return prefectureId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
