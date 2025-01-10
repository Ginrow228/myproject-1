package iprody.coffeeshot;

import java.util.LinkedList;
import java.util.Queue;

public class CoffeeOrderBoard {
    private final Queue<Order> orders;
    private int count;


    public CoffeeOrderBoard() {
        this.orders = new LinkedList<>();
        count = 1;
    }

}
