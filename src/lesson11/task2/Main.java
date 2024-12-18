package lesson11.task2;

public class Main {
    public static void main(String[] args){
        Member human = new Human("Human", 250, 1);
        Member cat = new Cat("Cat", 150, 2);
        Member robot = new Robot("Robot", 1000, 3);

        Obstacle treadmill = new Treadmill(230);
        Obstacle wall = new Wall(2);

        Member[] members = {human, cat, robot};
        Obstacle[] obstacles = {treadmill, wall};

        Competition competition = new Competition(members, obstacles);
        competition.start();
    }
}
