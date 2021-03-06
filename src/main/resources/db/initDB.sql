DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;


CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name       VARCHAR                           NOT NULL,
    email      VARCHAR                           NOT NULL,
    password   VARCHAR                           NOT NULL,
    registered TIMESTAMP           DEFAULT now() NOT NULL,
    enabled    BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        TEXT NOT NULL,
    description TEXT not null
);

CREATE TABLE dishes
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name          TEXT    NOT NULL,
    local_date    DATE    NOT NULL,
    price         INT     NOT NULL,
    restaurant_id INTEGER NOT NULL,
    CONSTRAINT dishes_unique_idx UNIQUE (restaurant_id, local_date, price),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id       INTEGER NOT NULL,
    restaurant_id INTEGER NOT NULL,
    local_date    DATE    NOT NULL,
    CONSTRAINT user_date_unique_idx UNIQUE (user_id, local_date),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE INDEX fki_dishes_rest ON dishes (restaurant_id);
CREATE INDEX fki_votes_rest ON votes (restaurant_id);
CREATE INDEX fki_votes_user ON votes (user_id);