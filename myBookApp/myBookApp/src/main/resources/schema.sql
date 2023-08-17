DROP TABLE IF EXISTS alphabetRUS, books, authors;



CREATE TABLE books
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    author_id INT          DEFAULT NULL,
    title     VARCHAR(250) NOT NULL,
    price_old VARCHAR(250) DEFAULT NULL,
    price     VARCHAR(250) DEFAULT NULL
);

CREATE TABLE authors
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    author   VARCHAR(250) NOT NULL,
    indexABC INT          NOT NULL
);

CREATE TABLE
    alphabetRUS
(
    id  INT AUTO_INCREMENT PRIMARY KEY,
    let VARCHAR(250) NOT NULL
);

ALTER TABLE books ADD FOREIGN KEY (author_id) REFERENCES authors(id);
ALTER TABLE authors ADD FOREIGN KEY (indexABC) REFERENCES alphabetRUS(id);





