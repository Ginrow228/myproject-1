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

    public Record find(String name){
        for (Record record : records){
            if(record.getName().equals(name)){
                System.out.println("Запись найдена: " + record.getName() + ", " + record.getPhoneNumber());
                return record;
            }
        }
        System.out.println("Запись по имени " + name + " не найдена");
        return null;
    }
}
