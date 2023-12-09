package com.undecided.inventory.model.municipal;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
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

    @Getter
    public static class Municipals {
        List<Municipal> value = new ArrayList<>();

        Municipals(List<Municipal> value) {
            this.value = value;
        }

        public static Municipals of(List<Municipal> value) {
            return new Municipals(value);
        }
    }
}
