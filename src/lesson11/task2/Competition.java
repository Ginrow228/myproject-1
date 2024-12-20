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
            boolean passedAllObstacles = true;

            for (int j = 0; j < obstacles.length; j++) {
                Obstacle obstacle = obstacles[j];
                if(!obstacle.canMemberOvercome(member)){
                    System.out.println(member + " не справился с испытанием");
                    passedAllObstacles = false;
                    break;
                }
                obstacle.overcome(member);
            }
            if(passedAllObstacles){
                System.out.println(member + " прошел все препятствия");
            }
            System.out.println(member + " завершает соревнование");
        }
    }
}
