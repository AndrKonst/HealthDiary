-- connect to db
\c healthdiary healthdiary;

-- tables
drop table healthdiary.users;
drop table healthdiary.diaries;
drop sequence healthdiary.diaries_seq;
drop table healthdiary.questions;
drop sequence healthdiary.questions_seq;
drop table healthdiary.diary_question;
drop sequence healthdiary.question_answers_seq;
drop table healthdiary.question_answers;

commit;
-- connect to superuser
\c postgres k.andronov

-- privilages
revoke all privileges on database healthdiary from healthdiary;

-- user
drop user healthdiary;

commit;
-- database
drop database healthdiary;

commit;