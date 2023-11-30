package com.undecided.address.presentation.api.prefecture;

import com.undecided.address.model.prefecture.Prefecture;

import java.util.ArrayList;
import java.util.List;

public record PrefectureDto(String code, String name) {
}
class PrefecturesDto{

    public static PrefectureDto[] convertFrom(Prefecture[] values) {
        List<PrefectureDto> list = new ArrayList<>();
        for (Prefecture value : values) {
            list.add(new PrefectureDto(value.getCode(),value.getName()));

        }
        return list.toArray(new PrefectureDto[0]);
    }
}
