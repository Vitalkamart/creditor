drop table if exists order_items;
drop table if exists items;
drop table if exists users cascade ;
drop table if exists orders cascade ;
drop table if exists credit_offers;
drop table if exists products;


create table users (
    id       bigserial primary key,
    name     varchar(100) not null,
    login    varchar(100) not null,
    password varchar(60)  not null,
    unique (login)
);
create unique index users_login_idx on users(login);

create table products (
    id       bigserial primary key,
    uid      varchar(10) not null,
    min_sum  integer     not null,
    max_sum  integer     not null,
    min_rate integer     not null,
    max_rate integer     not null,
    period   integer     not null,
    check ((period > 0) and (period <= 120)),
    unique (uid)
);

create table items (
    id    bigserial primary key,
    name  varchar(255) not null,
    price integer      not null,
    check (price > 0)
);

create table orders (
    id       bigserial primary key,
    uid      varchar(10) not null,
    price    integer     not null,
    discount integer,
    user_id  bigint      not null,
    check (price > 0),
    check ((discount > 0) and (discount <= 100)),
    unique (uid),
    foreign key (user_id) references users (id) on delete no action
);

create table order_items (
    id       bigserial primary key,
    order_id bigint  not null,
    item_id  bigint  not null,
    count    integer not null,
    unique (order_id, item_id),
    foreign key (order_id) references orders(id) on delete no action,
    foreign key (item_id) references items(id) on delete no action
);

create table credit_offers (
    id          bigserial primary key,
    uid         varchar(10)  not null,
    order_id    bigint       not null,
    date_time   timestamp default now(),
    user_login  varchar(100) not null,
    amount      integer      not null,
    credit_rate integer      not null,
    check ((credit_rate >= 5) and (credit_rate <= 24)),
    check (amount > 0),
    unique (uid),
    foreign key (order_id) references orders(id) on delete no action
);

