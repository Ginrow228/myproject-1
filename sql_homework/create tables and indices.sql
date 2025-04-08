create table Books(
id SERIAL primary key,
title VARCHAR(255) not null,
author varchar(255) not null,
published_year int check(published_year > 0),
genre varchar(255)
)

create table Readers(
id SERIAL primary key,
name varchar(100) not null,
email varchar(255) unique not null,
phone varchar(15) unique
)

create table Borrowed_books(
borrow_id SERIAL primary key,
book_id INT not null,
reader_id INT not null,
borrow_date DATE not null,
return_date DATE,
status varchar(20) check(status in('borrowed', 'returned')),

foreign key (book_id) references books(id) on delete set null,
foreign key (reader_id) references readers(id) on delete set null
)

create index index_books_title on books(title)
create index index_borrowed_books_reader on borrowed_books(reader_id)