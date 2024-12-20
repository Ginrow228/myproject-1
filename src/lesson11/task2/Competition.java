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
                int parameter = obstacle.getParameter();
                if(obstacle instanceof Treadmill){
                    if(!member.canRun(parameter)){
                        System.out.println(member + " не справляется с препятствием длиной в " + parameter + "м.");
                        passedAllObstacles = false;
                        break;
                    }
                } else if (obstacle instanceof Wall) {
                    if(!member.canJump(parameter)){
                        System.out.println(member + " не в состоянии перепрыгнуть препятствие длиной в " + parameter + "м.");
                        passedAllObstacles = false;
                        break;
                    }
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