drop table if exists orders_items;
drop table if exists items;
drop table if exists orders cascade;
drop table if exists user_role;
drop table if exists users cascade;
drop table if exists roles;
drop table if exists credit_offers;
drop table if exists products;
drop extension if exists pgcrypto;

CREATE EXTENSION pgcrypto;

create table users (
    id       bigserial primary key,
    name     varchar(100) not null,
    login    varchar(100) not null,
    password varchar(60)  not null,
    role     varchar(20)  not null,
    unique (login)
);

create unique index users_login_idx on users(login);

-- create table roles (
--     id   bigserial primary key,
--     name varchar(20) not null,
--     unique (name)
-- );
--
-- create table user_role (
--     user_id bigint,
--     role    varchar(20),
--     foreign key (user_id) references users(id),
--     unique (user_id, role)
-- );

create table products (
    id       bigserial primary key,
    uid      varchar(10) not null,
    min_sum  integer     not null,
    max_sum  integer     not null,
    min_rate integer     not null,
    max_rate integer     not null,
    period   integer     not null,
    check ((period > 0) and (period <= 120))
);

create table orders (
    uid      uuid    primary key DEFAULT gen_random_uuid(),
    id       varchar(255) not null,
    price    decimal      not null,
    discount integer,
    user_id  bigint       not null,
    check (price > 0),
    check ((discount >= 0) and (discount <= 100)),
    foreign key (user_id) references users (id) on delete no action
);

create table items (
    uid   uuid    primary key DEFAULT gen_random_uuid(),
    id    varchar(255)  not null,
    name  varchar(255)  not null,
    price decimal       not null,
    check (price > 0),
    unique (uid)
);

create table orders_items (
    order_uid  uuid  not null,
    items_uid  uuid   not null,
    unique (order_uid, items_uid),
    foreign key (order_uid) references orders(uid) on delete no action,
    foreign key (items_uid) references items(uid) on delete no action
);

create table credit_offers (
    id          bigserial primary key,
    uid         uuid         not null,
    order_uid   uuid         not null,
    date_time   timestamp default now(),
    user_login  varchar(100) not null,
    amount      decimal      not null,
    credit_rate integer      not null,
    period      integer      not null,
    check ((period > 1) and (period <= 120)),
    check ((credit_rate >= 50) and (credit_rate <= 240)),
    check (amount >= 0),
    foreign key (order_uid) references orders(uid) on delete no action
);

