create or replace procedure close_diary( p_id integer )
as $$
declare
  r record;
begin
  -- close diary_question
  -- Not closing answers because not need it now
  update healthdiary.diary_question
     set end_dt = now()
   where diary_id = p_id
     and start_dt <= now()
     and (end_dt is null or end_dt > now());

  update healthdiary.diaries set end_dt = now() where id = p_id;
end;
$$ language plpgsql;