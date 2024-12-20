package lesson11.task2;

public class Human implements Member {
    private String name;

    public Human(String name) {
        this.name = name;
    }

    @Override
    public void run(int distance) {
        System.out.println(name + " смог пробежать дистанцию в " + distance + "м.");
    }

    @Override
    public void jump(int height) {
        System.out.println(name + " хватило сил перепрыгнуть препятствие");
    }
}
