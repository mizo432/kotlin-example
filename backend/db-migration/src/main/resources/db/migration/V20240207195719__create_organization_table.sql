CREATE TABLE relationship.organization
(
    organization_id BIGINT       NOT NULL,
    name            varchar(100) NOT NULL,
    start_date      date         NOT NULL,
    end_date        date         NOT NULL,
    is_tax_exempt   bit          NOT NULL,
    PRIMARY KEY (organization_id)
);
