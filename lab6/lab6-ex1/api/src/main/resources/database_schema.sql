
DROP SEQUENCE IF EXISTS books_sequence CASCADE ;
DROP SEQUENCE IF EXISTS authors_sequence CASCADE ;
DROP SEQUENCE IF EXISTS readers_sequence CASCADE ;
DROP SEQUENCE IF EXISTS rentals_sequence CASCADE ;
DROP TABLE IF EXISTS authors CASCADE ;
DROP TABLE IF EXISTS books CASCADE ;
DROP TABLE IF EXISTS readers CASCADE ;
DROP TABLE IF EXISTS rentals cascade ;

CREATE SEQUENCE books_sequence MINVALUE 100;
CREATE SEQUENCE authors_sequence MINVALUE 100;
CREATE SEQUENCE readers_sequence MINVALUE 100;
CREATE SEQUENCE rentals_sequence MINVALUE 100;

CREATE TABLE authors (
  id integer primary key default nextval('authors_sequence'),
  first_name varchar(50) not null,
  last_name varchar(50) not null
);

CREATE TABLE books (
  id integer primary key default nextval('books_sequence'),
  title varchar(200) not null,
  amount integer not null,
  author_id integer not null references authors(id)
);

CREATE TABLE readers (
  id integer primary key default nextval('readers_sequence'),
  first_name varchar(50) not null,
  last_name varchar(50) not null
);

CREATE TABLE rentals (
  id integer primary key default nextval('rentals_sequence'),
  book_id integer not null references books(id),
  reader_id integer not null references readers(id),
  start_date timestamp not null,
  end_date timestamp not null
);
