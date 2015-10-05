drop table if exists student;
create table student(
id INT not null ,
first_name varchar(30) not null,
last_name varchar(30) not null,
birthday date not null,
groupe varchar(15) not null,
primary key (id));


