package lesson11;

public class Triangle implements Figure {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double findArea() {
        return 0.5 * base * height;
    }
}
