CREATE TABLE tb_events(
    id        INT AUTO_INCREMENT,
    title       VARCHAR(255),
    description VARCHAR(1000),
    discount    INT,
    url         VARCHAR(255),
    PRIMARY KEY (id)
);