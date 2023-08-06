create table if not exists role (
    id bigserial primary key,
    role varchar(30) check (role in ('USER', 'ADMIN'))
);

create table if not exists "user" (
    id bigserial primary key,
    username varchar(50) unique,
    password varchar(250) unique,
    role_id bigint not null,
    foreign key (role_id) references role(id),
    check (length(username) >= 5 and length(username) <= 50)
);

create table if not exists note (
    id bigserial primary key,
    title varchar(200),
    content text,
    access varchar(30) check (access in ('PUBLIC', 'PRIVATE')),
    user_id bigint not null,
    foreign key (user_id) references "user"(id)
);

create table if not exists user_role (
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references "user"(id),
    foreign key (role_id) references role(id)
);

create unique index person_index on "user"(id, username);
create unique index note_index on note(id);