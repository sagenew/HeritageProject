create type gender_enum as enum ('male', 'female');

create table people
(
	id serial primary key,
	fullname varchar(50) not null unique,
	gender gender_enum,
	birthdate date,
	deathdate date,
	biography varchar(120)
);

select * from people;

create table parents_children
(
parent_id 	int references people(id),
child_id 	int references people(id)
);

select * from parents_children;

drop table people;
drop table perent_children;
delete table people;