CREATE TABLE MENU (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    description VARCHAR(250),
    price DOUBLE NOT NULL,
    state BOOLEAN NOT NULL,
    imageName VARCHAR(250),
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES tb_category(id)
);
