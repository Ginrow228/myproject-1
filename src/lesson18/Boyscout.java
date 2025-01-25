package lesson18;

enum Team {
    RED, BLUE, GREEN
}

public class Boyscout {
    private String name;
    private int age;
    private Team team;

    public Boyscout(String name, int age, Team team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "Boyscout{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", team=" + team +
                '}';
    }
}
