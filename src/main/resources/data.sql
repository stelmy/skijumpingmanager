INSERT INTO country (id, name) VALUES
    (1, 'Polska');

INSERT INTO city (id, country_id, name) VALUES
    (1, 1, 'Zakopane');

INSERT INTO hill (id, name, city_id, kpoint, hill_size) VALUES
    (1, 'Wielka Krokiew', 1, 125, 140);

INSERT INTO competition (id, hill_id) VALUES
    (1, 1);

INSERT INTO competition_round (id, base_gate, competition_id) VALUES
    (1, 12, 1);

INSERT INTO judge (id, first_name, last_name, country_id) VALUES
    (1, 'Jan', 'Kowalski', 1);

INSERT INTO judge (id, first_name, last_name, country_id) VALUES
    (2, 'Jan', 'Nowak', 1);

INSERT INTO judge (id, first_name, last_name, country_id) VALUES
    (3, 'Adam', 'Kwiatkowski', 1);

INSERT INTO judge (id, first_name, last_name, country_id) VALUES
    (4, 'Ryszard', 'Kot', 1);

INSERT INTO judge (id, first_name, last_name, country_id) VALUES
    (5, 'Jacek', 'Dąbrowski', 1);

