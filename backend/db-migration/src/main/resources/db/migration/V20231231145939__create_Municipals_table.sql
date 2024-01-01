create table address.MUNICIPALS
(
    MUNICIPAL_ID                        BIGINT,
    PREFECTURE_CODE                     VARCHAR(2),
    MUNICIPAL_CODE                      VARCHAR(6),
    GUN_NAME                            VARCHAR(30),
    GUN_KANA_NAME                       VARCHAR(30),
    CITY_NAME                           VARCHAR(30),
    CITY_KANA_NAME                      VARCHAR(30),
    ORDINANCE_DESIGNATED_CITY_NAME      VARCHAR(30),
    ORDINANCE_DESIGNATED_CITY_KANA_NAME VARCHAR(30),
    EFFECTIVE_DATE                      DATE,
    DATE_OF_ABOLITION                   DATE,
    primary key (MUNICIPAL_ID)

)