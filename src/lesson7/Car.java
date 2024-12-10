package lesson7;

public class Car {

    public void start(){
        startElectricity();
        startCommand();
        startFuelSystem();
    }

    private void startElectricity(){
        System.out.println("lesson7.Car turns on the electricity");
    }

    private void startCommand(){
        System.out.println("lesson7.Car starts");
    }

    private void startFuelSystem(){
        System.out.println("lesson7.Car is being filled with fuel");
    }

}
