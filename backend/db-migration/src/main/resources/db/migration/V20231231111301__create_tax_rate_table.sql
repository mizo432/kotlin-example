create table adjustment.TAX_RATE
(
    tax_rate_id  bigInt,
    percentage   INTEGER,
    tax_kind     varchar(10),
    display_name varchar(50),
    start_date   date,
    end_date     date,
    primary key (tax_rate_id)
);