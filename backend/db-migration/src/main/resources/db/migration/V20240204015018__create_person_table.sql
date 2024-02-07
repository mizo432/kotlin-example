CREATE TABLE relationship.party
(
    party_id    BIGINT       NOT NULL,
    party_type  varchar(100) NOT NULL,
    legal_name  varchar(100) NOT NULL,
    search_name varchar(100) NOT NULL,
    PRIMARY KEY (party_id)
);