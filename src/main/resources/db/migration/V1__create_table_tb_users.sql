CREATE TABLE tb_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) ,
    lastname VARCHAR(255) ,
    identification VARCHAR(255),
    cellphone BIGINT ,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role ENUM('ADMIN', 'SUB_ADMIN', 'WAITRESS', 'BARTENDER', 'COOK')
);
