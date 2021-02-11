drop table if exists labor_rates;

drop table if exists materials;

drop table if exists units;

drop table if exists material_types;

drop table if exists users;

drop table if exists roles;

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

create table units (
    unit_id int unique not null generated always as identity,
    unit varchar(20) unique not null,
    primary key (unit_id)
);

create table materials (
    material_id int unique not null generated always as identity,
    material_name varchar(255) not null,
    material_type_id int not null,
    unit_id int not null,
    unit_cost numeric not null,
    primary key (material_id),
    foreign key (material_type_id) references material_types(material_type_id),
    foreign key (unit_id) references units(unit_id)
);

create table labor_rates (
    labor_rate_id int unique not null generated always as identity,
    description varchar(255) not null,
    rate_per_hour numeric not null,
    primary key (labor_rate_id)
);

insert into roles (role) values ('Admin'), ('Planner'), ('Standard');

insert into users (email, password, first_name, last_name, role_id) values ('testemail1', 'password1', 'firstname1', 'lastname1', 1), ('testemail2', 'password2', 'firstname2', 'lastname2', 1), ('testemail3', 'password3', 'firstname3', 'lastname3', 1);

insert into material_types (material_type) values ('Raw Material'), ('Intermediate'), ('Finished Good');

insert into units (unit) values ('Each'), ('Pounds');

insert into materials (material_name, material_type_id, unit_id, unit_cost) values ('testmaterial1', 1, 1, 1.0), ('testmaterial2', 1, 1, 1.0), ('testmaterial3', 2, 1, 1.0);

insert into labor_rates (description, rate_per_hour) values ('testrate1', 1.0), ('testrate2', 1.0), ('testrate3', 1.0);