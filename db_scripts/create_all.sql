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
\i ./tables/diary_questions.sql
\i ./tables/answers.sql
\i ./tables/question_answers.sql

commit;