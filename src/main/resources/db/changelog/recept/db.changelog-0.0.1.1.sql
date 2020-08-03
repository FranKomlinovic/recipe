-- liquibase formatted sql

-- changeset fkomlinov:0.0.1.1
CREATE TABLE user
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    email     varchar(255) NOT NULL UNIQUE,
    birthday     DATE,
    password varchar (255) NOT NULL,
    phone_number varchar (255),
    first_name varchar (255) NOT NULL,
    last_name varchar (255) NOT NULL,
    address  varchar(255),
    created_time TIMESTAMP    NOT NULL,
    active   BOOLEAN      NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);