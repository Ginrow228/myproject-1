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

    public List<Record> findAll(String name){
        List<Record> result = new ArrayList<>();
        for (Record record : records){
            if(record.getName().equals(name)){
                result.add(record);
            }
        }
        if(!result.isEmpty()){
            System.out.println("Записи по имени " + name + " были найдены: ");
            for (Record record : result){
                System.out.print(record.getName() + ", " + record.getPhoneNumber());
                System.out.println();
            }
        } else {
            System.out.println("Записей не найдено");
        }

        return result;
    }
}
