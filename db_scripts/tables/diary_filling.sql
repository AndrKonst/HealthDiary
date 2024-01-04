CREATE SEQUENCE healthdiary.diary_filling_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1;

create table healthdiary.diary_filling (
  id integer primary key default nextval('healthdiary.diary_filling_seq'),
  dt date not null default now()
);