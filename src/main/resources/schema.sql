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
    number      INTEGER NOT NULL,
    base_gate   INTEGER NOT NULL,
    type        VARCHAR(10) NOT NULL,
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

CREATE TABLE jump_score (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    jump_id     BIGINT NOT NULL,
    distance    DECIMAL NOT NULL,
    jury        DECIMAL NOT NULL,
    gate        DECIMAL NOT NULL,
    wind        DECIMAL NOT NULL,
    total       DECIMAL NOT NULL
);

CREATE TABLE competition_round_result
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    round_id    BIGINT NOT NULL,
    score_id    BIGINT NOT NULL
);

CREATE TABLE competition_competition_rounds
(
    competition_id  BIGINT NOT NULL,
    competition_rounds_id BIGINT NOT NULL
);
