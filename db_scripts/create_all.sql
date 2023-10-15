-- database
\i ./other_db_objects/create_db.sql

-- user
\i ./other_db_objects/create_user.sql
commit;

\c healthdiary healthdiary
-- tables
\i ./tables/users.sql

commit;