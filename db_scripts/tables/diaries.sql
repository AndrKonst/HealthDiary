CREATE SEQUENCE healthdiary.diaries_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1;

create table diaries (
  id integer default nextval('healthdiary.diaries_seq'),
  name text not null,
  start_dt timestamp with time zone default now(),
  end_dt timestamp with time zone,
  constraint pk_diaries primary key (id),
  constraint uk_diaries unique (name)
);