package lesson11.task2;

public class Treadmill implements Obstacle{

    private int distance;

    public Treadmill(int distance) {
        this.distance = distance;
    }

    @Override
    public void overcome(Member member) {
        member.run(distance);
    }

    public int getParameter() {
        return distance;
    }
}
