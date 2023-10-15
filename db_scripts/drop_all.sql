-- connect to db
\c healthdiary healthdiary;

-- tables
drop table users;

-- connect to superuser
\c postgres k.andronov

-- user
drop user healthdiary;

-- database
drop database healthdiary;

commit;