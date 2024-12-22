package lesson11;

public class TotalAreaFinder {
    private Figure[] figures;

    public TotalAreaFinder(Figure[] figures) {
        this.figures = figures;
    }

    public double findTotalArea(){
        double totalArea = 0;
        for (int i = 0; i < figures.length; i++) {
            totalArea += figures[i].findArea();
        }
        return totalArea;
    }
}
