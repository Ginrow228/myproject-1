package lesson10;

public abstract class Animal {

    private static int totalAnimalCounter;

    public Animal() {
        totalAnimalCounter++;
    }

    public abstract void run(double distance);
    public abstract void swim(double distance);

    public static int getTotalAnimalCounter(){
        return totalAnimalCounter;
    }
}
