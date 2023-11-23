package com.undecided.address.buisiness.query.city;

public class CityDto {
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    private  String code;
    private  String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static class CitiesDto {

    }
}
