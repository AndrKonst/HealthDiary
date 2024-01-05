create table healthdiary.question_answers(
  answer_id integer not null references healthdiary.answers(answer_id),
  question_id integer not null references healthdiary.questions(question_id),
  pos integer not null,
  start_dt timestamp with time zone not null default now(),
  end_dt timestamp with time zone,
  constraint pk_question_answers primary key (question_id, answer_id),
  constraint uk1_question_answers unique (question_id, pos)
);