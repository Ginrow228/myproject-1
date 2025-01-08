package lesson11.task2;

public interface Member {
    void run(int distance);
    void jump(int height);

    boolean canRun(int distance);
    boolean canJump(int height);
}
