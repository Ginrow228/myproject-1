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

    public <T extends Fruit> boolean compare(Box<T> other){
        return Float.compare(getWeight(), other.getWeight()) == 0;
    }

    public void transfer(Box<T> other){
        if(this.getClass() == other.getClass()){
            other.fruits.addAll(this.fruits);
            this.fruits.clear();
        } else {
            System.out.println("Вы не можете пересыпать фрукты разных типов в один ящик");
        }
    }

}
