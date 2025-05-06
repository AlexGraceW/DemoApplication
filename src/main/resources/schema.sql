DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;

CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255),
    amount DECIMAL(10, 2),
    date DATE,
    product_id BIGINT,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id)
);