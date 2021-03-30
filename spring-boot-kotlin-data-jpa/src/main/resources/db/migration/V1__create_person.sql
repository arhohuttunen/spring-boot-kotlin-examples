CREATE SEQUENCE hibernate_sequence
    START 1 INCREMENT 1;

CREATE TABLE person(
    id INT NOT NULL PRIMARY KEY,
    name TEXT NOT NULL,
    date_of_birth TIMESTAMP NOT NULL
);
