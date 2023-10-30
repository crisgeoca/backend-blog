--Create tables
CREATE TABLE IF NOT EXISTS Users
(
    id          UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name        text,
    last_name   text,
    username    text,
    password    text
);

CREATE TABLE IF NOT EXISTS Authorities
(
    id          UUID    DEFAULT RANDOM_UUID() PRIMARY KEY,
    authority   text,
    user_id     uuid    NOT NULL REFERENCES Users on update cascade on delete cascade
);

CREATE TABLE IF NOT EXISTS Posts
(
    id          UUID    DEFAULT RANDOM_UUID() PRIMARY KEY,
    text        text,
    created_at  TIMESTAMP,
    created_by  text,
    updated_at  TIMESTAMP,
    updated_by  text,
    user_id     uuid    NOT NULL REFERENCES Users on update cascade on delete cascade
);

CREATE TABLE IF NOT EXISTS Comments
(
    id          UUID    DEFAULT RANDOM_UUID() PRIMARY KEY,
    text        text,
    created_at  TIMESTAMP,
    created_by  text,
    updated_at  TIMESTAMP,
    updated_by  text,
    post_id     uuid    NOT NULL REFERENCES Posts on update cascade on delete cascade,
    user_id     uuid    NOT NULL REFERENCES Users on update cascade on delete cascade
);