package lesson11.task2;

public class Competition {
    private Member[] members;
    private Obstacle[] obstacles;

    public Competition(Member[] members, Obstacle[] obstacles) {
        this.members = members;
        this.obstacles = obstacles;
    }

    public void start(){
        for (int i = 0; i < members.length; i++) {
            Member member = members[i];
            System.out.println(member + " начинает соревнование");

            for (int j = 0; j < obstacles.length; j++) {
                Obstacle obstacle = obstacles[j];
                obstacle.overcome(member);
            }
            System.out.println(member + " закончил соревнование");
        }
    }
}
