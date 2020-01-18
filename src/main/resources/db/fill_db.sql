insert into users (name, login, password, role) values
('User Userovich', 'login1', '$2a$10$RK56TyFl9Td2WI7rXI/.0ue/KRMpe42HO/bv.DsQKDKp9EzSQxHUW', 'USER'),
('Arnoldo Schwarzehfogel', 'login2', '$2a$10$fkeDmtfaW7x9gpZm/Nh9guNtT8TT7J95xI7hJIbYV8.24e2E3mJ8G', 'ADMIN');

insert into orders (id, uid, price, discount, user_id) VALUES
('155', '9c6cd8a8-092e-4b09-a234-7eade8f24e49', 150000.00, 10, 1),
('132', 'a812cba7-af3f-4e9f-8670-4a7194af403b', 100000.00, 13, 1),
('122', '02885d24-c297-4dd8-bf60-481175fa30a6', 100000.00, 10, 2);

insert into items (id, uid,name, price) values
('12', 'a1b6f4e3-c54b-4a43-b00f-9d0b862ac2e7', 'шкаф', 100000.00),
('13', 'a15a53c2-d705-4faf-80d3-eee242bf9506', 'шкаф', 100000.00),
('14', 'f5182375-e655-4c69-ace7-d6b037b689e9', 'шкаф', 100000.00),
('15', '5b58f542-6973-4e97-a4e8-7d73e87c5160', 'стул', 50000.00);

insert into orders_items (order_uid, items_uid) VALUES
('9c6cd8a8-092e-4b09-a234-7eade8f24e49', 'a1b6f4e3-c54b-4a43-b00f-9d0b862ac2e7'),
('02885d24-c297-4dd8-bf60-481175fa30a6',  'a15a53c2-d705-4faf-80d3-eee242bf9506'),
('a812cba7-af3f-4e9f-8670-4a7194af403b', 'f5182375-e655-4c69-ace7-d6b037b689e9'),
('9c6cd8a8-092e-4b09-a234-7eade8f24e49', '5b58f542-6973-4e97-a4e8-7d73e87c5160');

insert into products (uid, min_sum, max_sum, min_rate, max_rate, period) values
('1006', 3000, 30000, 50, 240, 6),
('1010', 30001, 100000, 50, 240, 10),
('1012', 100001, 300000, 50, 240, 12);