DROP TABLE IF EXISTS Movie, Genre, movieGenre, Person, movieCharacter;
DROP SEQUENCE seq_movie;
DROP SEQUENCE seq_genre;
DROP SEQUENCE seq_person;
DROP SEQUENCE seq_movieCharacter;


CREATE TABLE Movie(
movieID INTEGER NOT NULL,
title VARCHAR(250) NOT NULL,
year INTEGER NOT NULL,
type VARCHAR (250) NOT NULL,
CONSTRAINT movie_title UNIQUE (title),
PRIMARY KEY (movieID)
);

CREATE TABLE Genre(
genreID INTEGER NOT NULL,
genre VARCHAR(250) NOT NULL,
CONSTRAINT genre_name UNIQUE (genre),
PRIMARY KEY (genreID)
);

CREATE TABLE movieGenre(
genreID INTEGER NOT NULL,
movieID INTEGER NOT NULL,
FOREIGN KEY (genreID) REFERENCES Genre(genreID),
FOREIGN KEY (movieID) REFERENCES Movie(movieID) ON DELETE CASCADE,
CONSTRAINT genre_movie UNIQUE (genreID, movieID),
PRIMARY KEY (movieID, genreID)
);

CREATE TABLE Person(
personID INTEGER NOT NULL,
name VARCHAR(250) NOT NULL,
PRIMARY KEY (personID),
CONSTRAINT p_name UNIQUE (name)
);

CREATE TABLE movieCharacter(
movCharID INTEGER NOT NULL,
movieID INTEGER NOT NULL,
personID INTEGER NOT NULL,
character VARCHAR(250) NOT NULL,
position INTEGER,
alias VARCHAR(250),
FOREIGN KEY (movieID) REFERENCES Movie(movieID) ON DELETE CASCADE,
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

CREATE SEQUENCE seq_person;
SELECT nextval('seq_person');
SELECT currval('seq_person');
