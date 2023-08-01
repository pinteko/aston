create table if not exists students
(
    id       bigserial primary key,
    name     varchar(255),
    age      bigint default 18 not null,
    group_id bigint default 1  not null references groups
);

create table if not exists groups
(
    id    bigserial primary key,
    title varchar(255)
);

create table if not exists students_groups
    (student_id bigint not null, group_id int not null,
    primary key (student_id, group_id),
    foreign key (student_id) references students (id),
    foreign key (group_id) references groups (id));

insert into groups(title) values ('spirits'), ('young');

insert into students values ('Kirill', 31), ('Nastya', 25), ('Oleg', 36);

insert into students_groups values (1, 2), (2, 2), (3, 1), (3, 2);