create table healthdiary.diary_question(
  diary_id integer not null references healthdiary.diaries(id),
  question_id integer not null references healthdiary.questions(question_id),
  pos integer not null,
  start_dt date not null default now(),
  end_dt date,
  constraint pk_diary_step primary key (diary_id, question_id),
  constraint uk1_diary_question unique (diary_id, pos)
);