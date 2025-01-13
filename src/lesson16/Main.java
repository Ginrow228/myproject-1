package lesson16;

public class Main {
    public static void main(String[] args){
        int[][] map = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1},
        };

        Voyager voyager = new DfsVoyager();
        int islands = voyager.lookupIslands(map);
        System.out.println(islands);
    }
}
