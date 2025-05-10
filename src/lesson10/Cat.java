package lesson10;

public class Cat extends Animal{

    private final String name;
    private static int catCounter;

    public Cat(String name) {
        this.name = name;
        catCounter++;
    }

    @Override
    public void run(double distance) {
        if(distance < 200){
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println("Слишком много для кота");
        }
    }

    @Override
    public void swim(double distance) {
        System.out.println(name + " не умеет плавать");
    }

    public static int getCatCounter(){
        return catCounter;
    }
}
