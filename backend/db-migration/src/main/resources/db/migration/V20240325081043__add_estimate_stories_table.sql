create table adjustment.estimate_stories
(
    estimate_story_id               bigInt,
    story_id                        bigInt    not null,
    occurred_at                     timestamp not null,
    rank                            INTEGER,
    fully_implemented_satisfactions varchar(20),
    not_implemented_satisfactions   varchar(20),
    primary key (estimate_story_id)
);