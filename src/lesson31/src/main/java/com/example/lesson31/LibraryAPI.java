package com.example.lesson31;

import java.util.Date;
import java.util.List;

public interface LibraryAPI {
    Book addBook(Book book);
    BorrowedBook updateBookStatus(int borrowId, String status, Date returnDate);
    Reader addReader(Reader reader);
    List<OccupiedBook> getOccupiedBooks();
    Reader updateReader(Reader reader);
    List<Book> filterBooksByStatus(String status);
    List<Book> findUnreturnedBooksAfterDate(Date date);
}
