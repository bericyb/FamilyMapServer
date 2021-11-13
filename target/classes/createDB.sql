drop table if exists AuthTokens;
drop table if exists Events;
drop table if exists Users;
drop table if exists Persons;

create table AuthTokens
(
        authtoken varchar(255) not null primary key unique,
        username varchar(255) not null,
        date varchar(255) not null
);

create table Events
(
        eventID varchar(255) not null primary key unique,
        username varchar(255) not null,
        personID varchar(255) not null,
        latitude float not null,
        longitude float not null,
        country varchar(255) not null,
        city varchar(255) not null,
        type varchar(255) not null,
        year int not null
);

create table Users
(
        username varchar(255) not null primary key unique,
        password varchar(255) not null,
        personID varchar(255) not null,
        email varchar(255) not null,
        firstname varchar(255) not null,
        lastname varchar(255) not null,
        gender varchar(10) not null
);

create table Persons
(
        personID varchar(255) not null primary key unique,
        username varchar(255) not null,
        firstname varchar(255) not null,
        lastname varchar(255) not null,
        gender varchar(10) not null,
        fatherID varchar(255),
        motherID varchar(255),
        spouseID varchar(255)
)
