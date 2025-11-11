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

DROP TABLE Movie, Genre, movieGenre, Person, movieCharacter;