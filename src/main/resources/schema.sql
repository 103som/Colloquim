CREATE TABLE IF NOT EXISTS book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    namee VARCHAR(255) UNIQUE NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    countt INT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book(id)
);

CREATE TABLE IF NOT EXISTS orderr (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    countt INT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book(id)
);

CREATE TABLE IF NOT EXISTS storage (
    id INT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS storage_books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    storage_id INT NOT NULL,
    book_id INT NOT NULL,
    countt INT NOT NULL,
    FOREIGN KEY (storage_id) REFERENCES storage(id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);

--CREATE UNIQUE INDEX storage_books_idx ON "storage_books" (storage_id, book_id);