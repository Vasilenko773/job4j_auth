create table users
(
    id       serial primary key not null,
    login    varchar(2000),
    password varchar(2000),
    role_id  int                not null references role (id)
);

create table role
(
    id   serial primary key not null,
    name varchar(500)
);

create table room
(
    id   serial primary key not null,
    name varchar(500)
);

create table message
(
    id      serial primary key not null,
    text    text,
    user_id int                not null references users (id)
);

create table room_user
(
    id      serial primary key,
    room_id int references room (id),
    user_id int references users (id)
);

insert into role (name)
values ('user');

