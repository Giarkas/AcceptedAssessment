-- Create the 'order_table'
CREATE TABLE order_table (
     order_id BIGSERIAL PRIMARY KEY,
     customer_name VARCHAR(255) NOT NULL,
     status VARCHAR(255) DEFAULT 'UNPROCESSED',
     order_date TIMESTAMP WITH TIME ZONE
);

CREATE INDEX idx_customer_name ON order_table (customer_name);

-- Create the 'order_line_table'
CREATE TABLE order_line_table (
      id BIGSERIAL PRIMARY KEY,
      product_id BIGINT NOT NULL,
      quantity INTEGER NOT NULL,
      price NUMERIC(10, 2) NOT NULL
);


CREATE INDEX idx_product_id ON order_line_table (product_id);

-- Create the 'order_line_order_table' which serves as a join table
CREATE TABLE order_line_order_table (
        order_id BIGINT NOT NULL,
        order_line_id BIGINT NOT NULL,

        PRIMARY KEY (order_id, order_line_id),

        CONSTRAINT fk_order
            FOREIGN KEY (order_id)
                REFERENCES order_table (order_id)
                ON DELETE CASCADE,

        CONSTRAINT fk_order_line
            FOREIGN KEY (order_line_id)
                REFERENCES order_line_table (id)
                ON DELETE CASCADE
);

commit;