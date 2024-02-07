CREATE TABLE relationship.party_relationship
(
    party_relationship_id BIGINT       NOT NULL,
    relationship_type     varchar(100) NOT NULL,
    from_id               BIGINT       NOT NULL,
    to_Id                 BIGINT       NOT NULL,
    start_date            DATE,
    end_date              DATE,
    PRIMARY KEY (party_relationship_id)
);