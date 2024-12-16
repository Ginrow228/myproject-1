package lesson10;

public class Dog extends Animal{

    private final String name;

    public Dog(String name) {
        super();
        this.name = name;
        dogs++;
    }

    @Override
    public void run(double distance) {
        if(distance < 500){
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать такую дистанцию");
        }
    }

    @Override
    public void swim(double distance) {
        if(distance < 10){
            System.out.println(name + " проплыл " + distance + "м.");
        }else {
            System.out.println(name + " не может проплыть столько м.");
        }
    }
}
