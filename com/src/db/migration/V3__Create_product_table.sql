CREATE TABLE products (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    amount INT NOT NULL,
    stock_id INT NOT NULL,
    last_update DATETIME,
    FOREIGN KEY (stock_id) REFERENCES stocks(id)
);