package lesson13.phonebook;

import java.util.ArrayList;
import java.util.List;

public class TelephoneBook {

    private List<Record> records;

    public TelephoneBook() {
        this.records = new ArrayList<>();
    }

    public void add(Record record){
        records.add(record);
    }
    
}
