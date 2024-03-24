create table adjustment.approve_stories
(
    approve_story_id bigInt,
    story_id         bigInt,
    occurred_at      timestamp,
    primary key (approve_story_id)
);