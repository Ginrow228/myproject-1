package lesson10;

public abstract class Animal {
    protected static int totalAnimal;
    protected static int dogs;
    protected static int cats;

    public Animal() {
        totalAnimal++;
    }

    public static int getTotalAnimal() {
        return totalAnimal;
    }

    public static int getDogs() {
        return dogs;
    }

    public static int getCats() {
        return cats;
    }

    public static void numberOfAnimals(){
        System.out.println("Общее количество животных: " + getTotalAnimal());
        System.out.println("Количество собак: " + getDogs());
        System.out.println("Количество кошек: " + getCats());
    }

    public abstract void run(double distance);
    public abstract void swim(double distance);
}
