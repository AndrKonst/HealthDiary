create or replace function create_filling( p_user_id bigint, p_diary_creation_fl bool default false) returns integer
as $$
declare
  l_filling_id integer;
begin
  insert into healthdiary.diary_filling(user_id, creation_fl) values (p_user_id, p_diary_creation_fl) returning id into l_filling_id;

  return l_filling_id;
end;
$$ language plpgsql;