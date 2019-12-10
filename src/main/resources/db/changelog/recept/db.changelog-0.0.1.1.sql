-- liquibase formatted sql

-- changeset fkomlinov:0.0.1.1
CREATE TABLE product
(
    id     BIGINT         NOT NULL AUTO_INCREMENT,
    code   varchar(255)   NOT NULL UNIQUE,
    name   varchar(255)   NOT NULL,
    price  DECIMAL(15, 2) NOT NULL,
    active BOOLEAN        NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);

CREATE TABLE recipe
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    code         varchar(255) NOT NULL UNIQUE,
    name         varchar(255) NOT NULL,
    text         varchar(255) NOT NULL,
    created_time TIMESTAMP    NOT NULL,
    active       BOOLEAN      NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);

CREATE TABLE product_recipe
(
    id         BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    recipe_id  BIGINT NOT NULL,
    quantity   INT    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL UNIQUE,
    mail     varchar(255) NOT NULL UNIQUE,
    address  varchar(255) NOT NULL,
    active   BOOLEAN      NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);

CREATE TABLE recipe_recommendation
(
    id         BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    recipe_id  BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id                BIGINT       NOT NULL AUTO_INCREMENT,
    code              VARCHAR(255) NOT NULL UNIQUE,
    deliverer_id      BIGINT,
    user_id           BIGINT       NOT NULL,
    created_time      TIMESTAMP    NOT NULL,
    delivery_datetime DATETIME,
    address           varchar(255) NOT NULL,
    price             DECIMAL(15, 2),
    cash_on_delivery  BOOLEAN      NOT NULL,
    delivered         BOOLEAN      NOT NULL DEFAULT FALSE,
    additional_info   varchar(255),
    active            BOOLEAN      NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);

CREATE TABLE order_product
(
    id         BIGINT NOT NULL AUTO_INCREMENT,
    order_id   BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity   INT    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE deliverer
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    code       varchar(255) NOT NULL UNIQUE,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    active     BOOLEAN      NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);

ALTER TABLE product_recipe
    ADD CONSTRAINT product_recipe_fk0 FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE product_recipe
    ADD CONSTRAINT product_recipe_fk1 FOREIGN KEY (recipe_id) REFERENCES recipe (id);

ALTER TABLE recipe_recommendation
    ADD CONSTRAINT recipe_recommendation_fk0 FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE recipe_recommendation
    ADD CONSTRAINT recipe_recommendation_fk1 FOREIGN KEY (recipe_id) REFERENCES recipe (id);

ALTER TABLE orders
    ADD CONSTRAINT order_fk0 FOREIGN KEY (deliverer_id) REFERENCES deliverer (id);

ALTER TABLE orders
    ADD CONSTRAINT order_fk1 FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE order_product
    ADD CONSTRAINT order_product_fk0 FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE order_product
    ADD CONSTRAINT order_product_fk1 FOREIGN KEY (product_id) REFERENCES product (id);

-- changeset fkomlinov:0.0.1.2
CREATE TABLE card (
                      id INT NOT NULL AUTO_INCREMENT,
                      name varchar(300) NOT NULL,
                      type INT(10) NOT NULL,
                      description varchar(1500) NOT NULL,
                      race varchar(255),
                      archetype varchar(255),
                      banlist  ENUM('Banned', 'Limited', 'Semi-Limited'),
                      PRIMARY KEY (id)
);

CREATE TABLE type (
                      id INT NOT NULL AUTO_INCREMENT,
                      type varchar(255) NOT NULL,
                      PRIMARY KEY (id)
);

CREATE TABLE image (
                       id INT NOT NULL AUTO_INCREMENT,
                       card INT NOT NULL,
                       image varchar(255),
                       image_small varchar(255),
                       PRIMARY KEY (id)
);

CREATE TABLE edition (
                         id INT NOT NULL AUTO_INCREMENT,
                         name varchar(255) NOT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE card_edition (
                              edition INT NOT NULL,
                              card INT NOT NULL,
                              rarity varchar(255),
                              price DECIMAL(7,6) NOT NULL
);

ALTER TABLE card ADD CONSTRAINT card_fk0 FOREIGN KEY (type) REFERENCES type(id);

ALTER TABLE image ADD CONSTRAINT image_fk0 FOREIGN KEY (card) REFERENCES card(id);

ALTER TABLE card_edition ADD CONSTRAINT card_edition_fk0 FOREIGN KEY (edition) REFERENCES edition(id);

ALTER TABLE card_edition ADD CONSTRAINT card_edition_fk1 FOREIGN KEY (card) REFERENCES card(id);

