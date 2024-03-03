create table adjustment.project_activity_requests
(
    project_activity_request_id     bigInt,
    project_activity_request_type   varchar(10),
    name                            varchar(128),
    project_activity_request_status varchar(20),
    priority                        INTEGER,
    request_date                    Date,
    due_date                        date,
    approval_date                   date,
    description                     varchar(1000),
    estimated_duration              INTEGER,
    estimated_cost                  INTEGER,
    budget                          INTEGER,
    primary key (project_activity_request_id)

);