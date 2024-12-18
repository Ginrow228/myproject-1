package lesson11.task2;

public class Cat implements Member{

    private String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void run(int distance) {
        System.out.println(name + " смог пробежать дистанцию в " + distance);
    }

    @Override
    public void jump(int height) {
        System.out.println(name + " хватило сил перепрыгнуть препятствие");
    }

    @Override
    public String toString() {
        return name;
    }
}
