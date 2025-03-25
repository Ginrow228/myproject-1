begin

insert into books (title, author, published_year, genre) 
values('Приступление и наказание', 'Федор Достоевский', 1866, 'Роман')

insert into borrowed_books (book_id, reader_id, borrow_date, return_date, status)
values(7, 2, '2025-03-23', null, 'borrowed')

// проверил в текущей сессии изменения (есть), так же проверил в другой сессии есть ли изменения до коммита (изменений нет)

rollback

begin

insert into books (title, author, published_year, genre) 
values('Приступление и наказание', 'Федор Достоевский', 1866, 'Роман')

insert into borrowed_books (book_id, reader_id, borrow_date, return_date, status)
values(7, 2, '2025-03-23', null, 'borrowed')

commit