INSERT INTO book (title,author,publisher,summary,release_date,status,isavailable,category_id,rent_date)
VALUES('JAVA','HERBIT','ORACLE','GOOD BOOK',now(),true,'Available',1, '2019-10-24');

INSERT INTO book(title,author,publisher,summary,release_date,status,isavailable,category_id,rent_date)
VALUES('Science Magazine','AB','ORACLE','GOOD BOOK', now(), true , 'Available', 3 ,  '2019-10-24');


INSERT INTO category(category_txt) VALUES('Develop');
INSERT INTO category(category_txt) VALUES('Romance');
INSERT INTO category(category_txt) VALUES('Science');

INSERT INTO bookloan(user_id, book_id, rent_date, due_date, is_loan) VALUES(1, 2,  '2019-10-24',  '2019-10-27', true);

INSERT INTO userInfo(username, password, gender, email, role_id, address_id) 
VALUES('admin','$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', true, 'test@example.com', 1, 1);

INSERT INTO userInfo(username, password, gender, email, role_id, address_id)
VALUES('user', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', true, 'testing@example.com', 2, 2);

INSERT INTO userRole(name, description)
VALUES('ADMIN', 'This is admin role');

INSERT INTO userRole(name, description)
VALUES('USER', 'This is user role');

INSERT INTO userAddress(address, city, country, postal_code)
VALUES('No.49 , Test street', 'Testa City', 'Testing Country', '00000');

INSERT INTO userAddress(address, city, country, postal_code)
VALUES('No.56 , AB street', 'Testing City', 'Tested Country', '00000');
