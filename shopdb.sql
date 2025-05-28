CREATE TABLE shopdb.orders (
    order_id STRING,
    product_id INT,
    quantity INT,
    total_price FLOAT,
    status STRING
);

CREATE TABLE shopdb.products (
    id INT,
    name STRING,
    price FLOAT
);

CREATE TABLE shopdb.shopping_cart (
    id INT,
    name STRING,
    price DOUBLE
);


CREATE TABLE shopdb.users (
    username STRING,
    password STRING
);
