CREATE TABLE book(
	book_id serial PRIMARY KEY,
	title VARCHAR (200) UNIQUE NOT NULL,
	author VARCHAR (200) NOT NULL,
	publisher VARCHAR (200) NOT NULL,
	summary TEXT NOT NULL,
	release_date DATE NOT NULL,
	status BOOLEAN NOT NULL DEFAULT TRUE,
	isAvailable VARCHAR (50) NOT NULL DEFAULT 'Available',
	category_id INTEGER NOT NULL,
	rent_date DATE
	);
	
CREATE TABLE category(
	category_id serial PRIMARY KEY,
	category_txt VARCHAR (200) UNIQUE NOT NULL
	);

CREATE TABLE bookloan(
	bookloan_id serial PRIMARY KEY,
	user_id INTEGER NOT NULL,
	book_id INTEGER NOT NULL,
	rent_date DATE NOT NULL,
	due_date DATE NOT NULL,
	is_loan BOOLEAN NOT NULL DEFAULT TRUE
	);

CREATE TABLE userInfo(
    login_id serial PRIMARY KEY,
    username VARCHAR (200) NOT NULL,
    password VARCHAR (200) NOT NULL,
    gender BOOLEAN NOT NULL,
    email VARCHAR (200) NOT NULL,
    role_id BIGINT NOT NULL,
    address_id BIGINT NOT NULL
	);

CREATE TABLE userRole(
	role_id serial PRIMARY KEY,
	name VARCHAR (50) NOT NULL,
	description VARCHAR (200) NOT NULL
	);
	
CREATE TABLE userAddress(
	address_id serial PRIMARY KEY,
	address TEXT NOT NULL,
	city VARCHAR (50) NOT NULL,
	country VARCHAR (50) NOT NULL,
	postal_code VARCHAR (50) NOT NULL
	);