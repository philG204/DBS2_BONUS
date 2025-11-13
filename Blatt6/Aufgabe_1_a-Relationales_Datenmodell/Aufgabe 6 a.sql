DROP TABLE IF NOT EXISTS Movie, Genre, movieGenre, Person, movieCharacter;

CREATE TABLE Movie(
movieID INTEGER NOT NULL,
title VARCHAR(250) NOT NULL,
year INTEGER NOT NULL,
type VARCHAR (250) NOT NULL,
PRIMARY KEY (movieID)
);

CREATE TABLE Genre(
genreID INTEGER NOT NULL,
genre VARCHAR(250) NOT NULL,
PRIMARY KEY (genreID)
);

CREATE TABLE movieGenre(
genreID INTEGER NOT NULL,
movieID INTEGER NOT NULL,
FOREIGN KEY (genreID) REFERENCES Genre(genreID),
FOREIGN KEY (movieID) REFERENCES Movie(movieID),
PRIMARY KEY (movieID, genreID)
);

CREATE TABLE Person(
personID INTEGER NOT NULL,
name VARCHAR(250) NOT NULL,
PRIMARY KEY (personID)
);

CREATE TABLE movieCharacter(
movCharID INTEGER NOT NULL,
movieID INTEGER NOT NULL,
personID INTEGER NOT NULL,
character VARCHAR(250) NOT NULL,
position INTEGER,
alias VARCHAR(250),
FOREIGN KEY (movieID) REFERENCES Movie(movieID),
FOREIGN KEY (personID) REFERENCES Person(personID),
PRIMARY KEY (movCharID)
);

CREATE SEQUENCE seq_movie;
SELECT nextval('seq_movie');
SELECT currval('seq_movie');

CREATE SEQUENCE seq_movieCharacter;
SELECT nextval('seq_movieCharacter');
SELECT currval('seq_movieCharacter');

CREATE SEQUENCE seq_genre;
SELECT nextval('seq_genre');
SELECT currval('seq_genre');

CREATE SEQUENCE seq_pers;
SELECT nextval('seq_pers');
SELECT currval('seq_pers');

CREATE SEQUENCE seq_movieGenre;
SELECT nextval('seq_movieGenre');
SELECT currval('seq_movieGenre');