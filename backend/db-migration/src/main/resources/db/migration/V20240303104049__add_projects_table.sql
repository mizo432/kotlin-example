create table adjustment.projects
(
    project_id         bigInt,
    project_type       varchar(10),
    name               varchar(128),
    status             varchar(20),
    priority           INTEGER,
    start_date         Date,
    end_date           date,
    description        varchar(1000),
    estimated_duration INTEGER,
    estimated_cost     INTEGER,
    budget             INTEGER,
    primary key (project_id)

);