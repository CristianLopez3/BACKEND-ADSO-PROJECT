CREATE TABLE tb_reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    reservationDate TIMESTAMP NOT NULL,
    numberOfPeople INT NOT NULL,
    description VARCHAR(255),
    checkedIn BOOLEAN NOT NULL
);
