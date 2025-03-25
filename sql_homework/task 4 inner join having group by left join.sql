select 
    b.title AS book_title, 
    b.author, 
    bb.reader_id, 
    bb.borrow_date
from books b
inner join borrowed_books bb on b.id = bb.book_id
where bb.status = 'borrowed'

select 
    bb.reader_id, 
    COUNT(bb.book_id) AS borrowed_count
from borrowed_books bb
group by bb.reader_id

select 
    bb.reader_id, 
    count(bb.book_id) AS borrowed_count
from borrowed_books bb
group by bb.reader_id
having count(bb.book_id) > 2

select 
    b.title AS book_title, 
    b.author, 
    b.genre, 
    bb.reader_id, 
    bb.borrow_date, 
    bb.status
from books b
left join borrowed_books bb on b.id = bb.book_id
where b.genre = 'Антиутопия'