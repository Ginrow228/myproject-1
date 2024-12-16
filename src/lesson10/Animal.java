package lesson10;

public abstract class Animal {

    protected static int totalAnimal = 0;
    protected static int dogs = 0;
    protected static int cats = 0;


    public Animal() {
        totalAnimal++;
    }

    public abstract void run(double distance);
    public abstract void swim(double distance);
}
