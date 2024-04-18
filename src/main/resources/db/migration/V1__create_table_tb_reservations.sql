CREATE TABLE tb_reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    reservation_date TIMESTAMP NOT NULL,
    number_of_people INT NOT NULL,
    description VARCHAR(255),
    checked_in BOOLEAN NOT NULL
);
