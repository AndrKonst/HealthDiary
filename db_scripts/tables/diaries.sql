CREATE SEQUENCE healthdiary.diaries_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1;

create table diaries (
    id integer default nextval('healthdiary.diaries_seq'),
    name text not null,
    start_dt date default now(),
    end_dt date,
    constraint diaries_pk primary key (id)
);