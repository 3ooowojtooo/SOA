INSERT INTO authors(first_name, last_name)
VALUES ('Fiodor', 'Dostojewski'),
       ('Albert', 'Camus'),
       ('Lew', 'Tolstoj'),
       ('Erich', 'Gamma'),
       ('Robert', 'Martin'),
       ('Joshua', 'Bloch'),
       ('Philip', 'Pullman'),
       ('Juliusz', 'Verne');

INSERT INTO books(title, amount, author_id)
VALUES ('Effective Java', 5,(SELECT id FROM authors WHERE last_name = 'Bloch')),
       ('Clean Code', 6,(SELECT id FROM authors WHERE last_name = 'Martin')),
       ('Design Patterns', 7,(SELECT id FROM authors WHERE last_name = 'Gamma')),
       ('Anna Karenina', 10,(SELECT id FROM authors WHERE last_name = 'Tolstoj')),
       ('Dzuma', 3,(SELECT id FROM authors WHERE last_name = 'Camus')),
       ('Zbrodnia i Kara', 20,(SELECT id FROM authors WHERE last_name = 'Dostojewski')),
       ('Zorza Polnocna', 10,(SELECT id FROM authors WHERE last_name = 'Pullman')),
       ('Magiczny Noz', 7,(SELECT id FROM authors WHERE last_name = 'Pullman')),
       ('Bursztynowa Luneta', 8,(SELECT id FROM authors WHERE last_name = 'Pullman')),
       ('Kapitan Nemo', 9,(SELECT id FROM authors WHERE last_name = 'Verne'));

INSERT INTO readers(first_name, last_name)
VALUES ('Jan', 'Kowalski'),
       ('John', 'Smith'),
       ('Jane', 'Doe');

INSERT INTO rentals(reader_id, book_id, start_date, end_date)
VALUES ((SELECT id FROM readers WHERE last_name = 'Kowalski'),
        (SELECT id FROM books WHERE title = 'Effective Java'), current_date - 21, current_date - 7),
       ((SELECT id FROM readers WHERE last_name = 'Kowalski'),
        (SELECT id FROM books WHERE title = 'Clean Code'), current_date - 21, NULL),
       ((SELECT id FROM readers WHERE last_name = 'Kowalski'),
        (SELECT id FROM books WHERE title = 'Design Patterns'), current_date - 6, NULL),
       ((SELECT id FROM readers WHERE last_name = 'Smith'),
        (SELECT id FROM books WHERE title = 'Anna Karenina'), current_date - 180, current_date-120),
       ((SELECT id FROM readers WHERE last_name = 'Smith'),
        (SELECT id FROM books WHERE title = 'Dzuma'), current_date - 100, NULL),
       ((SELECT id FROM readers WHERE last_name = 'Doe'),
        (SELECT id FROM books WHERE title = 'Anna Karenina'), current_date - 45, current_date),
       ((SELECT id FROM readers WHERE last_name = 'Doe'),
        (SELECT id FROM books WHERE title = 'Kapitan Nemo'), current_date - 360, current_date-180);

INSERT INTO rentals(book_id, reader_id, start_date, end_date)
VALUES ((SELECT id FROM books where title = 'Effective Java'),
        (SELECT id FROM readers where last_name = 'Kowalski'), current_date - 21, current_date - 7),
       ((SELECT id FROM books where title = 'Clean Code'),
        (SELECT id FROM readers where last_name = 'Kowalski'), current_date - 21, current_date - 6);