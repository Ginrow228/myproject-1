package com.example.lesson31;

import java.util.Date;

public class BorrowedBook {
    private int borrowId;
    private int bookId;
    private int readerId;
    private Date borrowDate;
    private Date returnDate;
    private String status;

    public BorrowedBook(int borrowId, int bookId, int readerId, Date borrowDate, Date returnDate, String status) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.readerId = readerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public BorrowedBook(int bookId, int readerId, Date borrowDate, Date returnDate, String status) {
        this(0, bookId, readerId, borrowDate, returnDate, status);
    }


    public BorrowedBook(int bookId, int readerId, Date borrowDate, String status) {
        this(0, bookId, readerId, borrowDate, null, status);
    }

    public int getBorrowId() {
        return borrowId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }
}
