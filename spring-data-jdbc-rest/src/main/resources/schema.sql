--------------------------------------------------
-- One Item (without relation)
--------------------------------------------------
DROP TABLE IF EXISTS item;
CREATE TABLE item (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR NOT NULL
);
--------------------------------------------------
-- One to Many (with Set)
--------------------------------------------------
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS skill;
-- One to Many Parent
CREATE TABLE person (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR NOT NULL
);
-- One to Many Child
CREATE TABLE skill (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR NOT NULL,
  -- if column name is same as parent table name,
  -- okay not to set explicit foreign key setting
  person INTEGER -- REFERENCES person(id)
);
--------------------------------------------------
-- One to Many (with List)
--------------------------------------------------
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS orders;
-- One to Many Parent
CREATE TABLE customer (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR NOT NULL
);
-- One to Many Child List
CREATE TABLE orders (
  id INTEGER IDENTITY PRIMARY KEY,
  price INTEGER NOT NULL,
  -- if column name is same as parent table name,
  -- no need to set explicit foreign key setting
  customer INTEGER,
  -- REFERENCES customer(id)
  -- List requires special column to maintain index
  customer_key INTEGER
);
-- One to Many Child Hash
CREATE TABLE address (
  id INTEGER IDENTITY PRIMARY KEY,
  city VARCHAR NOT NULL,
  customer INTEGER,
  customer_key VARCHAR
);
--------------------------------------------------
-- Many to Many
--------------------------------------------------
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book_author;
CREATE TABLE book (
  id INTEGER IDENTITY PRIMARY KEY,
  title VARCHAR NOT NULL
);
CREATE TABLE author (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR NOT NULL
);
-- junction table
CREATE TABLE book_author (
  book INTEGER NOT NULL,
  author INTEGER NOT NULL,
  PRIMARY KEY (book, author)
);
