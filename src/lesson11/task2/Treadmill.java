package lesson11.task2;

public class Treadmill implements Obstacle{

    private int distance;

    public Treadmill(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean canMemberOvercome(Member member) {
        return member.canRun(distance);
    }

    @Override
    public void overcome(Member member) {
        member.run(distance);
    }
}
