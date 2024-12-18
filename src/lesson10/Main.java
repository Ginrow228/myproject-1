package lesson10;

public class Main {
    public static void main(String[] args){
        Animal dog1 = new Dog("Корги");
        Animal dog2 = new Dog("Овчарка");

        Animal cat1 = new Cat("Сиамский");
        Animal cat2 = new Cat("Британская");

        dog1.run(250);
        dog2.swim(11);
        cat1.run(220);
        cat2.swim(2.5);

        System.out.println("Общее количество животных: " + Animal.getTotalAnimalCounter());
        System.out.println("Количество кошек: " + Cat.getCatCounter());
        System.out.println("Количество собак: " + Dog.getDogCounter());
    }
}
