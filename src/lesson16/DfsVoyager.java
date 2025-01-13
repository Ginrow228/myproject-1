package lesson16;

public class DfsVoyager implements Voyager {

    @Override
    public int lookupIslands(int[][] map) {
        if(map == null || map.length == 0){
            return 0;
        }

        int numRows = map.length;
        int islandCounter = 0;

        for (int i = 0; i < numRows; i++) {
            int numColumn = map[i].length;
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 1){
                    islandCounter++;
                    dfs(map, i, j);
                }
            }
        }
        return islandCounter;
    }

    private void dfs(int[][] map, int i, int j){
        if(i < 0 || j < 0 || i >= map.length || j >= map[i].length || map[i][j] != 1){
            return;
        }

        map[i][j] = 2;

        dfs(map, i - 1, j);
        dfs(map, i + 1, j);
        dfs(map, i, j - 1);
        dfs(map, i, j + 1);
    }

}
