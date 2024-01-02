CREATE SEQUENCE healthdiary.answers_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1;

create table answers (
    answer_id integer default nextval('healthdiary.answers_seq'),
    name text not null,
    constraint pk_answers primary key (answer_id)
);
