CREATE SEQUENCE seq_movie;
SELECT nextval('seq_movie');
SELECT currval('seq_movie');

CREATE SEQUENCE seq_movieCharacter;
SELECT nextval('seq_movieCharacter');
SELECT currval('seq_movieCharacter');

CREATE SEQUENCE seq_genre;
SELECT nextval('seq_genre');
SELECT currval('seq_genre');

CREATE SEQUENCE seq_person;
SELECT nextval('seq_person');
SELECT currval('seq_person');


SELECT * FROM Movie;
SELECT * FROM MovieCharacter;
SELECT * FROM Genre;
SELECT * FROM Person;