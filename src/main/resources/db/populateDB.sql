DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM restaurants;
DELETE
FROM dishes;
DELETE
FROM votes;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('User2', 'user2@yandex.ru', 'password'),
       ('User3', 'user3@yandex.ru', 'password');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001),
       ('ROLE_USER', 100002),
       ('ROLE_USER', 100003);



INSERT INTO restaurants (name, description) VALUES
('Restaurant 1', 'Description of restaurant 1'),
('Restaurant 2', 'Description of restaurant 2'),
('Restaurant 3', 'Description of restaurant 3');

INSERT INTO dishes (restaurant_id, local_date, name, price) VALUES
(100004, '2015-05-30 00:00:00', 'Dish 1', 1250),
(100005, '2017-01-30 00:00:00', 'Dish 2', 1350),
(100006, '2017-01-30 00:00:00', 'Dish 3', 1450),
(100004, '2017-02-20 00:00:00', 'Dish 4', 12050),
(100005, '2017-02-20 00:00:00', 'Dish 5', 13050),
(100006, '2017-02-20 00:00:00', 'Dish 6', 14050),
(100005, '2017-01-21 00:00:00', 'Dish 7', 13999),
(100005, '2017-01-21 00:00:00', 'Dish 8', 14099);

INSERT INTO votes (user_id, restaurant_id, local_date) VALUES
(100000, 100004, '2017-01-30 00:00:00'),
(100001, 100005, '2017-01-30 00:00:00'),
(100002, 100006, '2017-01-30 00:00:00'),
(100003, 100006, '2017-01-30 00:00:00'),
(100000, 100004, '2017-02-20 00:00:00'),
(100001, 100004, '2017-02-20 00:00:00'),
(100002, 100005, '2017-01-21 00:00:00'),
(100003, 100004, '2017-01-21 00:00:00');