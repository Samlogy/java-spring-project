CREATE TABLE IF NOT EXISTS order_item (
    id SERIAL PRIMARY KEY,
    order_id INTEGER,
    product_name VARCHAR(255),
    quantity INTEGER,
    price REAL,
    FOREIGN KEY (order_id) REFERENCES orderr(id) ON DELETE CASCADE
);