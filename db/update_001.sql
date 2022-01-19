create table person
(
    id       serial primary key not null,
    login    varchar(2000),
    password varchar(2000)
);

create table employee
(
    id serial primary key not null,
    name varchar(500),
    surname varchar(500),
    inn int,
    dateOfEmployment timestamp
);



 insert into person (login, password) values ('parsentev', '123');
insert into person (login, password)
values ('ban', '123');
insert into person (login, password)
values ('ivan', '123');