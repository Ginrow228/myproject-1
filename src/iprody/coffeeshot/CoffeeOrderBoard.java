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

    public boolean add(String name){
        Order newOrder = new Order(count, name);
        orders.add(newOrder);
        count++;

        return true;
    }

    public Order deliver(){
        if(orders.isEmpty()){
            return null;
        }
        return orders.poll();
    }

    public Order deliver(int orderNumber) throws OrderNotFoundException{
        for (Order tempOrder : orders){
            if(tempOrder.getOrderNumber() == orderNumber){
                orders.remove(tempOrder);
                return tempOrder;
            }
        }
        throw new OrderNotFoundException("Заказ под номером " + orderNumber + " не был найден");
    }




}
