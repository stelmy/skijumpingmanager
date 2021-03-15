CREATE TABLE country (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50) NOT NULL
);

CREATE TABLE city (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    country_id  BIGINT NOT NULL,
    name        VARCHAR(50) NOT NULL
);

CREATE TABLE hill (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    city_id     BIGINT NOT NULL,
    hill_size   INTEGER NOT NULL,
    kpoint      INTEGER NOT NULL
);

CREATE TABLE competition (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    hill_id     BIGINT NOT NULL
);

CREATE TABLE competition_round (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    base_gate   INTEGER NOT NULL,
    competition_id BIGINT NOT NULL
);

CREATE TABLE judge (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    country_id  BIGINT NOT NULL
);

CREATE TABLE jump (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    competition_round_id BIGINT NOT NULL,
    distance    FLOAT NOT NULL,
    gate        INTEGER NOT NULL,
    wind_speed  FLOAT NOT NULL
);

CREATE TABLE jury_note (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    judge_id    BIGINT NOT NULL,
    note        FLOAT NOT NULL
);

CREATE TABLE jump_jury_notes (
    jump_id     BIGINT NOT NULL,
    jury_notes_id BIGINT NOT NULL
);