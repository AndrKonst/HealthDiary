create table healthdiary.users (
    user_id  integer,
    is_admin integer,
    state    text,
    constraint pk_users primary key (user_id)
);