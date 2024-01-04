create table healthdiary.users (
    user_id  integer,
    is_admin integer,
    state    integer,
    step     integer,
    constraint pk_users primary key (user_id)
);

insert into healthdiary.users(user_id, is_admin) values(897021100, 1);