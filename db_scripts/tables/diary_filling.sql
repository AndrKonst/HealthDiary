CREATE SEQUENCE healthdiary.diary_filling_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1;

create table healthdiary.diary_filling (
  id          integer primary key default nextval('healthdiary.diary_filling_seq'),
  user_id     integer not null,
  dt          timestamp with time zone not null default now(),
  creation_fl integer,
  constraint fk1_diary_filling foreign key (user_id) references healthdiary.users(user_id)
);