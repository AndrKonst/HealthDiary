-- connect to db
\c healthdiary healthdiary;

-- tables
drop table healthdiary.diary_result;

drop table healthdiary.diary_filling;
drop sequence healthdiary.diary_filling_seq;

drop table healthdiary.question_answers;

drop table healthdiary.answers;
drop sequence healthdiary.answers_seq;

drop table healthdiary.diary_question;
drop sequence healthdiary.diary_question_seq;

drop table healthdiary.questions;
drop sequence healthdiary.questions_seq;

drop table healthdiary.diaries;
drop sequence healthdiary.diaries_seq;

drop table healthdiary.users;

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