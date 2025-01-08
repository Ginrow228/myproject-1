package lesson11;

public class Main {
    public static void main(String[] args) {
        Figure triangle = new Triangle(10, 5);
        Figure square = new Square(5);
        Figure circle = new Circle(5);

        System.out.println(triangle.findArea());
        System.out.println(square.findArea());
        System.out.println(circle.findArea());

        Figure[] figures = {triangle, square, circle};

        TotalAreaFinder totalArea = new TotalAreaFinder(figures);

        System.out.println(totalArea.findTotalArea());


    }
}
