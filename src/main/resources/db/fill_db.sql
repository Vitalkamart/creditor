insert into users (name, login, password) values
    ('User Userovich', 'login1', '$2a$10$RK56TyFl9Td2WI7rXI/.0ue/KRMpe42HO/bv.DsQKDKp9EzSQxHUW'),
    ('Arnoldo Schwarzehfogel', 'login2', '$2a$10$fkeDmtfaW7x9gpZm/Nh9guNtT8TT7J95xI7hJIbYV8.24e2E3mJ8G');

insert into roles (name) values
('ROLE_ADMIN'),
('ROLE_USER');


insert into user_role (user_id, role_id) values
(1, 2),
(2, 1);

insert into items (name, price) values
    ('шкаф', 10000000),
    ('стул', 5000000);

insert into products (uid, min_sum, max_sum, min_rate, max_rate, period) values
    ('1006', 300000, 3000000, 5, 24, 6),
    ('1010', 3000100, 10000000, 5, 24, 10),
    ('1012', 10000100, 30000000, 5, 24, 12);

