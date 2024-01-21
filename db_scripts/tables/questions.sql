CREATE SEQUENCE healthdiary.questions_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1;

create table healthdiary.questions(
  question_id integer primary key default nextval('healthdiary.questions_seq'),
  text text not null
)