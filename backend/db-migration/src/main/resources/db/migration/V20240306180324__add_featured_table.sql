create table adjustment.features
(
    feature_id   bigint,
    project_id   bigInt,
    feature_code varchar(10),
    name         varchar(128),
    description  varchar(1000),
    notes        varchar(3000),
    primary key (feature_id)

);