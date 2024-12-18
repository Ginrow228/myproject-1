package lesson11.task2;

public class Main {
    public static void main(String[] args){
        Member human = new Human("Human");
        Member cat = new Cat("Cat");
        Member robot = new Robot("Robot");

        Obstacle treadmill = new Treadmill(50);
        Obstacle wall = new Wall(2);

        Member[] members = {human, cat, robot};
        Obstacle[] obstacles = {treadmill, wall};

        Competition competition = new Competition(members, obstacles);
        competition.start();
    }
}
