CREATE TABLE relationship.address
(
    party_address_id                    BIGINT      NOT NULL,
    address_id                          BIGINT,
    prefecture_code                     VARCHAR(2)  NOT NULL,
    prefecture_name                     VARCHAR(50) NOT NULL,
    prefecture_kana_name                VARCHAR(50) NOT NULL,
    municipal_code                      VARCHAR(6),
    gun_name                            VARCHAR(50),
    gun_kana_name                       VARCHAR(50),
    city_name                           VARCHAR(50),
    city_kana_name                      VARCHAR(50),
    ordinance_designated_city_name      VARCHAR(50),
    ordinance_designated_city_kana_name VARCHAR(50),
    CONSTRAINT pk_address PRIMARY KEY (party_address_id)
);
