package undecided.adress.buisiness.query.prefecture;

import undecided.adress.model.prefecture.Prefecture;

import java.util.ArrayList;
import java.util.List;

public record PrefectureDto(String code, String name) {
    public static class PrefecturesDto {

        public static PrefectureDto[] convertFrom(Prefecture[] values) {
            List<PrefectureDto> list = new ArrayList<>();
            for (Prefecture value : values) {
                list.add(new PrefectureDto(value.getCode(), value.getName()));

            }
            return list.toArray(new PrefectureDto[0]);
        }
    }
}

