package lesson11;

public class Circle implements Figure{
    private double radius;
    private final double pi = 3.14;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double findArea() {
        return pi * radius * radius;
    }
}
