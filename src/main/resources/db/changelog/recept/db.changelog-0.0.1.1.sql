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
    nickname varchar(255) NOT NULL UNIQUE,
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
