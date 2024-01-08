-- database
\i ./other_db_objects/create_db.sql

-- user
\i ./other_db_objects/create_user.sql

commit;

\c healthdiary healthdiary

-- schema
\i ./other_db_objects/create_schema.sql

-- tables
\i ./tables/users.sql
\i ./tables/diaries.sql
\i ./tables/questions.sql
\i ./tables/diary_question.sql
\i ./tables/answers.sql
\i ./tables/question_answers.sql
\i ./tables/diary_filling.sql
\i ./tables/diary_result.sql

-- functions
\i ./functions/create_diary.sql
\i ./functions/create_filling.sql
\i ./functions/add_question.sql

-- procedures
\i ./procedures/close_diary.sql

commit;