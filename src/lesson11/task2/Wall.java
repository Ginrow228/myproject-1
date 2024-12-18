package lesson11.task2;

public class Wall implements Obstacle{

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void overcome(Member member) {
        member.jump(height);
    }

    public int getParameter() {
        return height;
    }
}
