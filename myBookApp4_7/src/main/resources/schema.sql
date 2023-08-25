DROP TABLE IF EXISTS books, authors;

-- CREATE TABLE
--     alphabet
-- (
--     id  INT AUTO_INCREMENT PRIMARY KEY,
--     let VARCHAR(250) NOT NULL
-- );

CREATE TABLE authors
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50)
--     indexABC   INT NOT NULL
);

CREATE TABLE books
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    author_id INT          DEFAULT NULL,
    title     VARCHAR(250) NOT NULL,
    price_old VARCHAR(250) DEFAULT NULL,
    price     VARCHAR(250) DEFAULT NULL
);

ALTER TABLE books
    ADD FOREIGN KEY (author_id) REFERENCES authors (id);
-- ALTER TABLE authors
--     ADD FOREIGN KEY (indexABC) REFERENCES alphabet (id);





