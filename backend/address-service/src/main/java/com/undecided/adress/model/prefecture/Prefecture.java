package com.undecided.adress.model.prefecture;

public enum Prefecture {
    北海道("01",	"北海道"),
    青森県("02",	"青森県"),
    岩手県("03",	"岩手県"),
    宮城県("04","宮城県"),
    秋田県("05",	"秋田県"),
    山形県("06",	"山形県"),

    福島県("07",	"福島県"),
    茨城県("08",	"茨城県"),
    栃木県("09",	"栃木県"),
    群馬県("10","群馬県"),
    埼玉県("11","埼玉県"),
    千葉県("12","千葉県"),
    東京都("13",	"東京都"),
    神奈川県("14",	"神奈川県"),
    新潟県("15",	"新潟県"),
    富山県("16",	"富山県"),
    石川県("17",	"石川県"),
    福井県("18",	"福井県"),
    山梨県("19",	"山梨県"),
    長野県("20",	"長野県"),
    岐阜県("21",	"岐阜県"),
    静岡県("22",	"静岡県"),
    愛知県("23",	"愛知県"),
    三重県("24",	"三重県"),
    滋賀県("25",	"滋賀県"),
    京都府("26",	"京都府"),
    大阪府("27",	"大阪府"),
    兵庫県("28",	"兵庫県"),
    奈良県("29",	"奈良県"),
    和歌山県("30",	"和歌山県"),
    鳥取県("31",	"鳥取県"),
    島根県("32",	"島根県"),
    岡山県("33",	"岡山県"),
    広島県("34",	"広島県"),
    山口県("35",	"山口県"),
    徳島県("36",	"徳島県"),
    香川県("37",	"香川県"),
    愛媛県("38",	"愛媛県"),
    高知県("39",	"高知県"),
    福岡県("40",	"福岡県"),
    佐賀県("41",	"佐賀県"),
    長崎県("42",	"長崎県"),
    熊本県("43",	"熊本県"),
    大分県("44",	"大分県"),
    宮崎県("45",	"宮崎県"),
    鹿児島県("46",	"鹿児島県"),
    沖縄県("47",	"沖縄県");
    private final String code;
    private final String name;

    Prefecture(String code,String name){
    this.code = code;
    this.name = name;
    }

    public static Prefecture valueOfCode(String prefectureCode) {
        for (Prefecture value : values()) {
            if(value.getCode().equals(prefectureCode))
                return value;
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
