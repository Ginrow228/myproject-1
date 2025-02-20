package lesson22;

public class Main {
    public static void main(String[] args) {
        PetrolStation station = new PetrolStation(250);
        Thread c1 = new Thread(() -> station.doTank(10));
        Thread c2 = new Thread(() -> station.doTank(22));
        Thread c3 = new Thread(() -> station.doTank(5));
        Thread c4 = new Thread(() -> station.doTank(7));

        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}
