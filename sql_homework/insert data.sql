insert into books (title, author, published_year, genre)
VALUES('Скотный двор', 'Джордж Оруэлл', 1945, 'Аллегория')

insert into books (title, author, published_year, genre)
values
	('1984', 'Джордж Оруэлл', 1949, 'Антиутопия'),
	('451 градус по Фаренгейту', 'Рэй Брэдбери', 1953, 'Антиутопия'),
	('Дневник памяти', 'Николас Спаркс', 1996, 'Роман')

insert into books(title, author, published_year, genre)
values
	('Тьма', 'Иван Бунин', 1930, 'Рассказ'),
	('Тьма и Свет', 'Нацуо Кирино', 1998, 'Психологический триллеер')

INSERT INTO readers (name, email, phone) VALUES
('Иван Иванов', 'ivan.ivanov@example.com', '+998901234567'),
('Мария Петрова', 'maria.petrova@example.com', null),
('Алексей Смирнов', 'alexey.smirnov@example.com', '+998909876543'),
('Елена Козлова', 'elena.kozlova@example.com', null),
('Дмитрий Сидоров', 'dmitry.sidorov@example.com', '+998935551122')

insert into borrowed_books (book_id, reader_id , borrow_date, return_date, status) values
(4, 3, CURRENT_DATE, null, 'borrowed')

insert into borrowed_books (book_id, reader_id, borrow_date, return_date, status)
	values
	(3, 5, current_date, null, 'borrowed')

insert into borrowed_books (book_id, reader_id, borrow_date, return_date, status)
	values
	(1, 1, '2025-02-15', current_date, 'returned')

insert into borrowed_books (book_id, reader_id, borrow_date, return_date, status)
	values
	(3, 2, current_date, null, 'borrowed') // тут проверил будет ли арендована книга если она уже арендована (арендовалась)