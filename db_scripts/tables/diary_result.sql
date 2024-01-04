create table healthdiary.diary_result(
  df_id integer not null,
  diary_question_id integer not null,
  pos integer,
  constraint fk1_diary_result foreign key (df_id) references healthdiary.diary_filling(id),
  constraint fk2_diary_result foreign key (diary_question_id) references healthdiary.diary_question(id),
  constraint pk_diary_result primary key (df_id, diary_question_id, pos)
);