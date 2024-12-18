package lesson11;

public class Square implements Figure{

    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double findArea() {
        return side * side;
    }

}
