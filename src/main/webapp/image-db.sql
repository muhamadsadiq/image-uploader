CREATE TABLE IF NOT EXISTS image
(
    id BIGSERIAL NOT NULL ,
    name varchar(100) not null ,
    path varchar(200) not null ,
    PRIMARY KEY (id)
);