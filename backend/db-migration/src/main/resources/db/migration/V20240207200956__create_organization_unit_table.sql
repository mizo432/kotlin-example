CREATE TABLE relationship.organization_unit
(
    organization_unit_id BIGINT       NOT NULL,
    organization_id      BIGINT       NOT NULL,
    name                 varchar(100) NOT NULL,
    start_date           date         NOT NULL,
    end_date             date         NOT NULL,
    PRIMARY KEY (organization_unit_id)
);
