create or replace function create_diary( p_name text, p_user_id in bigint) returns integer
as $$
declare
  l_diary_id integer;
  l_df_id    integer;
begin
  insert into healthdiary.diaries(name) values (p_name) returning id into l_diary_id;

  l_df_id := healthdiary.create_filling(p_user_id, true);

  return l_diary_id;
end;
$$ language plpgsql;