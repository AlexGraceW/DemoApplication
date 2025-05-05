INSERT INTO CUSTOMERS (name, surname, age, phone_number) VALUES
('Alexey', 'Ivanov', 30, '123-456-7890');

INSERT INTO ORDERS (date, customer_id, product_name, amount) VALUES
(CURRENT_DATE, 1, 'Tent', 99.99),
(CURRENT_DATE, 1, 'Camping Chair', 49.99);