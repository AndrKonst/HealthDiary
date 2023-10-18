CREATE SEQUENCE healthdiary.question_answers_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1;

create table healthdiary.question_answers(
  answer_id integer constraint pk_question_answers primary key default nextval('healthdiary.question_answers_seq'),
  question_id integer not null references healthdiary.questions(question_id),
  pos integer not null,
  start_dt date not null default now(),
  end_dt date,
  constraint uk1_question_answers unique (question_id, pos)
);