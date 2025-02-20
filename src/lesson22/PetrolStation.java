package lesson22;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class PetrolStation {
    private double fuel;
    private final Semaphore semaphore;

    public PetrolStation(double fuel) {
        this.fuel = fuel;
        this.semaphore = new Semaphore(3);
    }

    public void doTank(double fuelRequested) {
        try {
            semaphore.acquire();

            if (fuelRequested > fuel) {
                System.out.println("На заправке недостаточно топлива чтобы заполнить вашу машину.");
                return;
            }

            Random random = new Random();
            int refuelTime = 3 + random.nextInt(7);

            System.out.println(Thread.currentThread().getName() + " начал заправляться.");
            Thread.sleep(refuelTime * 1000);
            synchronized (this) {
                if(fuelRequested <= fuel){
                    fuel -= fuelRequested;
                    System.out.println(Thread.currentThread().getName() + " закончил заправку."
                            + " Оставшееся топливо: " + fuel + "л.");
                } else {
                    System.out.println("На заправке недостаточно топлива чтобы заполнить вашу машину.");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Заправка прервана.");
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }

    public double getFuel() {
        return fuel;
    }
}
