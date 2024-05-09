CREATE TABLE Bank (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE Account (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(255),
    balance DECIMAL(10, 2),
    bank_id INT,
    FOREIGN KEY (bank_id) REFERENCES Bank(id)
);

CREATE TABLE Transaction (
    id SERIAL PRIMARY KEY,
    amount DECIMAL(10, 2),
    originating_account_id INT,
    resulting_account_id INT,
    transaction_reason VARCHAR(255),
    FOREIGN KEY (originating_account_id) REFERENCES Account(id),
    FOREIGN KEY (resulting_account_id) REFERENCES Account(id)
);
