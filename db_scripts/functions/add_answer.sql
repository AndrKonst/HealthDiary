create or replace function add_answer(p_answer_text in text, p_question_id in integer, p_pos in integer) returns integer
language plpgsql
as $$
declare
  l_answer_id integer;
begin
  insert into healthdiary.answers(name) values (p_answer_text) returning answer_id into l_answer_id;

  insert into healthdiary.question_answers(answer_id, question_id, pos) values (l_answer_id, p_question_id, p_pos);

  return l_answer_id;
end;
$$;