package com.undecided.adress.model.city;

public class City {
    private final String code;
    private final String name;


    City(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    City reconstruct(String code, String name){
        return new City(code,name);
    }
}
