INSERT INTO country (id, name, code) VALUES
    (1, 'Polska', 'POL'),
    (2, 'Niemcy', 'GER');

INSERT INTO city (id, country_id, name) VALUES
    (1, 1, 'Zakopane');

INSERT INTO hill (id, name, city_id, kpoint, hill_size) VALUES
    (1, 'Wielka Krokiew', 1, 125, 140);

INSERT INTO competition (id, hill_id) VALUES
    (1, 1);

INSERT INTO competition_round (id, number, base_gate, type, competition_id) VALUES
    (1, 1, 12, 'OFFICIAL', 1);

INSERT INTO competition_competition_rounds (competition_id, competition_rounds_id) VALUES
    (1, 1);

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

INSERT INTO jumper (id, first_name, last_name, country_id, fis_code) VALUES
    (1, 'Kamil', 'Stoch', 1, '4321');

INSERT INTO jumper (id, first_name, last_name, country_id, fis_code) VALUES
    (2, 'Piotr', 'Żyła', 1, '4325');

INSERT INTO jumper (id, first_name, last_name, country_id, fis_code) VALUES
    (3, 'Dawid', 'Kubacki', 1, '5142');

INSERT INTO jumper (id, first_name, last_name, country_id, fis_code) VALUES
    (4, 'Andrzej', 'Stękała', 1, '6698');
