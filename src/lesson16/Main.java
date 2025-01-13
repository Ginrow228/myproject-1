package src.lesson16;

public class Main {
    public static void main(String[] args){
        int[][] map = {
                {1, 1, 0, 1, 1},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1},
        };

//        DfsVoyager voyager = new DfsVoyager();
//        int islandsByDfs = voyager.lookupIslands(map);
//        System.out.println(islandsByDfs);
        BfsVoyager voyager2 = new BfsVoyager();
        int islandsByBfs = voyager2.lookupIslands(map);
        System.out.println(islandsByBfs);
    }
}
