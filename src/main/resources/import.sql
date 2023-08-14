create table if not exists students
(
    id       bigserial primary key,
    name     varchar(255),
    age      bigint default 18 not null,
    group_id bigint default 1  not null references groups(id)
);

create table if not exists groups
(
    id    bigserial primary key,
    title varchar(255),
    teacher_id bigint references teachers(id)
);

create table if not exists teachers
(
    id       bigserial primary key,
    name     varchar(255)
);

create table if not exists teacher_student
(teacher_id bigint not null, student_id int not null,
 primary key (teacher_id, student_id),
 foreign key (teacher_id) references teachers (id),
 foreign key (student_id) references students (id));

insert into groups(title) values ('spirits'), ('young');

insert into students values ('Kirill', 31), ('Nastya', 25), ('Oleg', 36);

-- insert into students_groups values (1, 2), (2, 2), (3, 1), (3, 2);

CREATE TABLE IF NOT EXISTS abstract_identifiable_corporation (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255),
        corporation_type VARCHAR(255) NOT NULL,
        microsoft_product VARCHAR(255), -- Поле для MicrosoftCorporation
        tesla_product VARCHAR(255),     -- Поле для TeslaCorporation
        apple_product VARCHAR(255)      -- Поле для AppleCorporation
);

create table if not exists teacher_group
(teacher_id bigint not null, group_id int not null,
 primary key (teacher_id, group_id),
 foreign key (teacher_id) references teachers (id),
 foreign key (group_id) references groups (id));
