create table adjustment.estimate_stories
(
    estimate_story_id bigInt,
    story_id          bigInt    not null,
    occurred_at       timestamp not null,
    effort            INTEGER   not null,
    primary key (estimate_story_id)
);