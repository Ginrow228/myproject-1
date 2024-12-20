package lesson11.task2;

public class Treadmill implements Obstacle {
    private int distance;

    public Treadmill(int distance) {
        this.distance = distance;
    }

    @Override
    public void overcome(Member member) {
        member.run(distance);
    }

    @Override
    public boolean canMemberOvercome(Member member) {
        return member.canJump(distance);
    }
}

