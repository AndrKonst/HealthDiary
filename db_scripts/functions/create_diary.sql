create or replace function create_diary( p_name text ) returns integer
as $$
declare
  l_diary_id integer;
begin
  insert into healthdiary.diaries(name) values (p_name) returning id into l_diary_id;

  return l_diary_id;
end;
$$ language plpgsql;