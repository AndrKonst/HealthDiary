create or replace procedure close_diary( p_id integer )
as $$
begin
  update healthdiary.diaries set end_dt = now() where id = p_id;
end;
$$ language plpgsql;