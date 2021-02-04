create table roles (
    role_id int unique not null generated always as identity,
    role varchar(20) unique not null,
    primary key (role_id)
);

create table users (
    user_id int unique not null generated always as identity,
    email varchar(255) not null unique,
    password varchar(255) not null,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    role_id int not null,
    primary key (user_id),
    foreign key (role_id) references roles(role_id)
);

create table material_types (
    material_type_id int unique not null generated always as identity,
    material_type varchar(30) unique not null,
    primary key (material_type_id)
);