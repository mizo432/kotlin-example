CREATE TABLE relationship.address_use
(
    party_address_id BIGINT   NOT NULL,
    party_id         BIGINT   NOT NULL,
    address_use_type SMALLINT NOT NULL,
    effective_date   date     NOT NULL,
    CONSTRAINT pk_addressUse PRIMARY KEY (party_address_id, party_id, address_use_type, effective_date)
);