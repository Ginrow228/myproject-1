package lesson17.fruits;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public void add(T fruit){
        fruits.add(fruit);
    }

    public float getWeight(){
        float sum = 0.0F;
        for (T fruit : fruits){
            sum += fruit.getWeight();
        }
        return sum;
    }

}
