CREATE SEQUENCE healthdiary.diary_question_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1;

create table healthdiary.diary_question(
  id integer primary key default nextval('healthdiary.diary_question_seq'),
  diary_id integer not null references healthdiary.diaries(id),
  question_id integer not null references healthdiary.questions(question_id),
  pos integer not null,
  start_dt date not null default now(),
  end_dt date,
  constraint uk1_diary_step unique (diary_id, question_id),
  constraint uk2_diary_question unique (diary_id, pos)
);