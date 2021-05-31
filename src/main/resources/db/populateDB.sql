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
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001),
       ('USER', 100002),
       ('USER', 100003);

INSERT INTO restaurants (name, description)
VALUES ('Restaurant 1', 'sea_food'),
       ('Restaurant 2', 'sea_food'),
       ('Restaurant 3', 'sea_food'),
       ('Restaurant 4', 'italiano');

INSERT INTO dishes (restaurant_id, local_date, name, price)
VALUES (100004, '2020-03-01 00:00:00', 'Dish1', 1250),
       (100005, '2020-03-01 00:00:00', 'Dish2', 1350),
       (100006, '2020-03-01 00:00:00', 'Dish3', 20),
       (100004, '2020-03-01 00:00:00', 'Dish4', 20),
       (100005, '2020-03-01 00:00:00', 'Dish5', 20);

INSERT INTO votes (user_id, restaurant_id, local_date)
VALUES (100000, 100004, '2020-05-30 00:00:00'),
       (100001, 100004, '2020-05-30 00:00:00'),
       (100002, 100006, '2020-05-30 00:00:00'),
       (100003, 100004, now()),
       (100003, 100005, '2020-05-28 00:00:00'),
       (100003, 100005, '2020-05-29 00:00:00');