package com.example.lesson31;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        try {
            libraryService.getConnection();
        } catch (Exception e) {
            System.out.println("Подключение не успешно: " + e.getMessage());
        }

//        Book book = new Book(0, "Война и Мир", "Лев Толстой", 1865, "Роман");
//        libraryService.addBook(book);

//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date returnDate = sdf.parse("2025-03-23");
//
//            libraryService.updateBookStatus(2, "returned", returnDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        Reader reader = new Reader("Сергей Цой", "sergeyTsoy@email.ru", "+99894121313");
//        libraryService.addReader(reader);

//        libraryService.getOccupiedBooks();
//        List<OccupiedBook> occupiedBooks = libraryService.getOccupiedBooks();
//        for (OccupiedBook book : occupiedBooks) {
//            System.out.println("Книга: " + book.getBook().getTitle() +
//                    ", Читатель: " + book.getReader().getName());
//        }

//        Reader updatedReader = new Reader(6,"Сергей Цой", "sergeyTsoy@email.ru", "+998941213134");
//        libraryService.updateReader(updatedReader);

//        List<Book> filteredBooks = libraryService.filterBooksByStatus("returned");
//        System.out.println(String.format("%-5s | %-30s | %-20s | %-10s | %-15s",
//                "ID", "Название книги", "Автор", "Год выпуска", "Жанр"));
//        System.out.println("---------------------------------------------------------------");
//
//        for (Book book : filteredBooks) {
//            System.out.println(String.format("%-5d | %-30s | %-20s | %-10d | %-15s",
//                    book.getId(), book.getTitle(), book.getAuthor(), book.getPublishedYear(), book.getGenre()));
//        }

//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date inputDate = sdf.parse("2025-03-23");
//            List<Book> unreturnedBooks = libraryService.findUnreturnedBooksAfterDate(inputDate);
//            if (unreturnedBooks.isEmpty()) {
//                System.out.println("Нет книг, которые были взяты после " + sdf.format(inputDate) + " и не возвращены.");
//            } else {
//                unreturnedBooks.forEach(book ->
//                        System.out.println(book.getId() + " | " + book.getTitle() + " | " + book.getAuthor() + " | " + book.getPublishedYear() + " | " + book.getGenre())
//                );
//            }
//        } catch (Exception e) {
//            System.out.println("Ошибка при вводе даты: " + e.getMessage());
//        }
    }
}
