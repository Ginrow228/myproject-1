package lesson22;

import java.util.ArrayList;
import java.util.List;

public class ThreadSafeList<T> {

    private final List<T> list;

    public ThreadSafeList() {
        this.list = new ArrayList<>();
    }

    public synchronized void add(T item){
        list.add(item);
    }

    public synchronized boolean remove(T item){
        return list.remove(item);
    }

    public synchronized T get(int index){
        if(index >= 0 && index < list.size()){
            return list.get(index);
        }
        return null;
    }

}
