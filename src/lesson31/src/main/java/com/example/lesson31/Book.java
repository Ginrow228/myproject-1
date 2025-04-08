package com.example.lesson31;

public class Book {
    private int id;
    private String title;
    private String author;
    private int publishedYear;
    private String genre;

    public Book(int id, String title, String author, int publishedYear, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.genre = genre;
    }

    public Book(String title, String author, int publishedYear, String genre) {
        this(0, title, author, publishedYear, genre);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public String getGenre() {
        return genre;
    }
}
