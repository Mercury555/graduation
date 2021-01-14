CREATE SEQUENCE GLOBAL_SEQ START WITH 100000;

CREATE TABLE users
(
    id         INTEGER default GLOBAL_SEQ.nextval primary key,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    registered TIMESTAMP,
    enabled    BOOLEAN   DEFAULT TRUE,
    CONSTRAINT users_unique_email_idx UNIQUE (email)
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
    id          INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY ,
    name        TEXT NOT NULL,
    description TEXT not null);

CREATE TABLE dishes
(
    id            INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY ,
    name          TEXT      NOT NULL,
    restaurant_id INT       NOT NULL,
    local_date     TIMESTAMP NOT NULL,
    price         INT       NOT NULL,

    CONSTRAINT dishes_unique_idx UNIQUE (restaurant_id, local_date, price),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
    id            INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY ,
    user_id       INTEGER   NOT NULL,
    restaurant_id INTEGER   NOT NULL,
    local_date     TIMESTAMP NOT NULL,
    CONSTRAINT user_date_unique_idx UNIQUE (user_id, local_date),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE INDEX fki_dishes_rest ON dishes (restaurant_id);
CREATE INDEX fki_votes_rest ON votes (restaurant_id);
CREATE INDEX fki_votes_user ON votes (user_id);