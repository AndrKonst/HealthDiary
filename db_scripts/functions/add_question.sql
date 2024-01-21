create or replace function add_question(p_question_text in text, p_diary_id in integer, p_pos in integer) returns integer
language plpgsql
as $$
declare
  l_question_id integer;
begin
  insert into healthdiary.questions(text) values (p_question_text) returning question_id into l_question_id;

  insert into healthdiary.diary_question(diary_id, question_id, pos) values (p_diary_id, l_question_id, p_pos);

  return l_question_id;
end;
$$;