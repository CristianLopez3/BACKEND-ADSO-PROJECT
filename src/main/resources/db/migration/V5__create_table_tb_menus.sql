CREATE TABLE tb_menus (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price DECIMAL(10, 2) NOT NULL,
    state BOOLEAN NOT NULL,
    image_name VARCHAR(255),
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES tb_menu_categories(id)
);
