INSERT INTO country (id, name, code) VALUES
    (1, 'Polska', 'POL'),
    (2, 'Niemcy', 'GER'),
    (3, 'Norwegia', 'NOR'),
    (4, 'Austria', 'AUT'),
    (5, 'Japonia', 'JPN'),
    (6, 'Słowenia', 'SLO'),
    (7, 'Rosja', 'RUS'),
    (8, 'Finlandia', 'FIN'),
    (9, 'Czechy', 'CZE'),
    (10, 'USA', 'USA'),
    (11, 'Kanada', 'CAN'),
    (12, 'Francja', 'FRA'),
    (13, 'Włochy', 'ITA'),
    (14, 'Bułgaria', 'BUL'),
    (15, 'Rumunia', 'ROM'),
    (16, 'Korea Południowa', 'KOR'),
    (17, 'Ukraina', 'UKR'),
    (18, 'Kazachstan', 'KAZ'),
    (19, 'Szwecja', 'SWE'),
    (20, 'Szwajcaria', 'SUI');

INSERT INTO city (id, country_id, name) VALUES
    (1, (SELECT id FROM country WHERE code = 'POL'), 'Zakopane'),
    (2, (SELECT id FROM country WHERE code = 'POL'), 'Wisła'),
    (3, (SELECT id FROM country WHERE code = 'GER'), 'Willingen'),
    (4, (SELECT id FROM country WHERE code = 'GER'), 'Oberstdorf'),
    (5, (SELECT id FROM country WHERE code = 'GER'), 'Garmisch-Partenkirchen'),
    (6, (SELECT id FROM country WHERE code = 'AUT'), 'Innsbruck'),
    (7, (SELECT id FROM country WHERE code = 'AUT'), 'Bischofshofen'),
    (8, (SELECT id FROM country WHERE code = 'NOR'), 'Oslo'),
    (9, (SELECT id FROM country WHERE code = 'SLO'), 'Planica'),
    (10, (SELECT id FROM country WHERE code = 'SUI'), 'Engelberg'),
    (11, (SELECT id FROM country WHERE code = 'FIN'), 'Lahti'),
    (12, (SELECT id FROM country WHERE code = 'ROM'), 'Rasnov');

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
