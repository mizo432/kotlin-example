create table adjustment.stories
(
    story_id                        bigInt,
    project_id                      bigInt,
    story_type                      varchar(10),
    name                            varchar(128),
    story_status                    varchar(20),
    description                     varchar(1000),
    notes                           varchar(3000),
    rank                            INTEGER,
    fully_implemented_satisfactions varchar(20),
    not_implemented_satisfactions   varchar(20),
    feature_id                      bigint,
    depends_on_story_id             bigint,
    creator_id                      bigint,
    parent_sprint_id                bigint,
    value                           INTEGER,
    effort                          INTEGER,
    affect_version                  varchar(20),
    primary key (story_id)
);