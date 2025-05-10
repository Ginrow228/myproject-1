package lesson13.phonebook;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        TelephoneBook book = new TelephoneBook();
        book.add(new Record("Mary", "123"));
        book.add(new Record("Jerry", "12356"));
        book.add(new Record("Jerry", "1234"));
        book.add(new Record("Jerry", "12356"));
        book.add(new Record("John", "5436"));


        book.find("Jerry");
        book.findAll("Jerry");

        book.find("Terry");


    }
}
