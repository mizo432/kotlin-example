create schema "employee";

create table "employee"."employees"
(
    employee_id bigint PRIMARY KEY,
    name          varchar(40),
    department_id bigint


);