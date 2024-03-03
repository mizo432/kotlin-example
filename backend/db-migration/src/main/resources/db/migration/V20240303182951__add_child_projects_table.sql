create table adjustment.child_projects
(
    id                bigInt,
    parent_project_id bigInt,
    child_project_id  bigInt,
    primary key (id)

);