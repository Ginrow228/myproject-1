package lesson11.task2;

public class Robot implements Member {
    private final String name;
    private final int maxRunDistance;
    private final int maxJumpHeight;

    public Robot(String name, int maxRunDistance, int maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public void run(int distance) {
        System.out.println(name + " смог пробежать дистанцию в " + distance + "м.");
    }

    @Override
    public void jump(int height) {
        System.out.println(name + " хватило сил перепрыгнуть препятствие");
    }

    @Override
    public boolean canRun(int distance) {
        return distance <= maxRunDistance;
    }

    @Override
    public boolean canJump(int height) {
        return height <= maxJumpHeight;
    }

    @Override
    public String toString() {
        return name;
    }
}
