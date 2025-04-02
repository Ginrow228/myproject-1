package com.example.lesson31;

import java.util.Date;

public class OccupiedBook {
    private int borrowId;
    private Book book;
    private Reader reader;
    private Date borrowDate;
    private Date returnDate;
    private String status;

    public OccupiedBook(int borrowId, Book book, Reader reader, Date borrowDate, Date returnDate, String status) {
        this.borrowId = borrowId;
        this.book = book;
        this.reader = reader;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }
}
