package lesson17.fruits;

public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        appleBox2.add(new Apple());
        appleBox2.add(new Apple());
        appleBox2.add(new Apple());
        System.out.println("Ящик с яблоками: " + appleBox2.getWeight());
        //appleBox2.transfer(orangeBox);
        System.out.println("Ящик с яблоками: " + appleBox.getWeight());
        System.out.println(appleBox2.compare(orangeBox));
    }
}
