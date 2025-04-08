package com.example.lesson31;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibraryService implements LibraryAPI {
    private static final String url = "jdbc:postgresql://localhost:5432/Library_DB";
    private static final String user = "admin";
    private static final String password = "admin";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных", e);
        }
    }

    @Override
    public Book addBook(Book book) {
        String sql = "INSERT INTO books (title, author, published_year, genre) values (?, ?, ?, ?) RETURNING ID";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPublishedYear());
            statement.setString(4, book.getGenre());


            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                return new Book(id, book.getTitle(), book.getAuthor(), book.getPublishedYear(), book.getGenre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BorrowedBook updateBookStatus(int borrowId, String status, Date returnDate) {
        String sql;
        if ("borrowed".equals(status)) {
            sql = "UPDATE borrowed_books SET status = ?, return_date = NULL WHERE borrow_id = ? " +
                    "RETURNING borrow_id, book_id, reader_id, borrow_date, return_date, status";
        } else {
            sql = "UPDATE borrowed_books SET status = ?, return_date = ? WHERE borrow_id = ? " +
                    "RETURNING borrow_id, book_id, reader_id, borrow_date, return_date, status";
        }

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);

            if ("borrowed".equals(status)) {
                statement.setInt(2, borrowId);
            } else {
                statement.setDate(2, new java.sql.Date(returnDate.getTime()));
                statement.setInt(3, borrowId);
            }

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("borrow_id");
                int book_id = resultSet.getInt("book_id");
                int reader_id = resultSet.getInt("reader_id");
                Date borrow_date = resultSet.getDate("borrow_date");
                Date updatedReturnDate = resultSet.getDate("return_date");
                String updatedStatus = resultSet.getString("status");

                return new BorrowedBook(id, book_id, reader_id, borrow_date, updatedReturnDate, updatedStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reader addReader(Reader reader) {
        String sql = "INSERT INTO readers (name, email, phone) values (?, ?, ?) RETURNING ID";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             statement.setString(1, reader.getName());
             statement.setString(2, reader.getEmail());
             statement.setString(3, reader.getPhone());


            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                return new Reader(id, reader.getName(), reader.getEmail(), reader.getPhone());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OccupiedBook> getOccupiedBooks() {
        String sql = "SELECT bb.borrow_id as borrow_id, b.id as book_id, b.title, b.author, b.published_year, b.genre, " +
                "r.id as reader_id, r.name as reader_name, r.email, r.phone, " +
                "bb.borrow_date, bb.return_date, bb.status " +
                "FROM borrowed_books bb " +
                "JOIN books b ON bb.book_id = b.id " +
                "JOIN readers r ON bb.reader_id = r.id " +
                "WHERE bb.status IN ('borrowed') " +
                "ORDER BY bb.borrow_date DESC";

        List<OccupiedBook> occupiedBooks = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             ResultSet resultSet = statement.executeQuery();

             while (resultSet.next()) {
                 int borrow_id = resultSet.getInt("borrow_id");
                 int book_id = resultSet.getInt("book_id");
                 String title = resultSet.getString("title");
                 String author = resultSet.getString("author");
                 int published_year = resultSet.getInt("published_year");
                 String genre = resultSet.getString("genre");

                 int reader_id = resultSet.getInt("reader_id");
                 String readerName = resultSet.getString("reader_name");
                 String email = resultSet.getString("email");
                 String phone = resultSet.getString("phone");

                 Date borrowDate = resultSet.getDate("borrow_date");
                 Date returnDate = resultSet.getDate("return_date");
                 String status = resultSet.getString("status");

                 Book book = new Book(book_id, title, author, published_year, genre);
                 Reader reader = new Reader(reader_id, readerName, email, phone);

                 OccupiedBook occupiedBook = new OccupiedBook(borrow_id, book, reader, borrowDate, returnDate, status);
                 occupiedBooks.add(occupiedBook);
             }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return occupiedBooks;
    }

    @Override
    public Reader updateReader(Reader reader) {
        String sql = "UPDATE readers SET name = ?, email = ?, phone = ? WHERE id = ? RETURNING id, name, email, phone";

        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, reader.getName());
            statement.setString(2, reader.getEmail());
            statement.setString(3, reader.getPhone());
            statement.setInt(4, reader.getId());

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                return new Reader(id, name, email, phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> filterBooksByStatus(String status) {
        String sql = "SELECT DISTINCT b.id, b.title, b.author, b.published_year, b.genre " +
                "FROM books b " +
                "JOIN borrowed_books bb ON b.id = bb.book_id " +
                "WHERE bb.status = ?";

        List<Book> books = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int publishedYear = resultSet.getInt("published_year");
                String genre = resultSet.getString("genre");

                books.add(new Book(id, title, author, publishedYear, genre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findUnreturnedBooksAfterDate(Date date) {
        String sql = "SELECT DISTINCT b.id, b.title, b.author, b.published_year, b.genre " +
                "FROM books b " +
                "JOIN borrowed_books bb ON b.id = bb.book_id " +
                "WHERE bb.borrow_date > ? AND bb.status = 'borrowed'";

        List<Book> books = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int publishedYear = resultSet.getInt("published_year");
                String genre = resultSet.getString("genre");

                books.add(new Book(id, title, author, publishedYear, genre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}