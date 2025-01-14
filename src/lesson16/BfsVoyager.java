package lesson16;

import java.util.LinkedList;
import java.util.Queue;

public class BfsVoyager implements Voyager {

        @Override
        public int lookupIslands(int[][] map) {
            if(map == null || map.length == 0){
                return 0;
            }
            int numRows = map.length;
            int islandsCounter = 0;
            for (int i = 0; i < numRows; i++) {
                int numColumn = map[0].length;
                for (int j = 0; j < numColumn; j++) {
                    if(map[i][j] == 1){
                        islandsCounter++;
                        bfs(map, i, j);
                    }
                }
            }
            return islandsCounter;
        }

        private void bfs(int[][] map, int i, int j){
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{i, j});
            map[i][j] = 0;

            while(!queue.isEmpty()){
                int[] curr = queue.poll();
                int x = curr[0];
                int v = curr[1];
                if(x - 1 >= 0 && map[x - 1][v] == 1){
                    queue.add(new int[]{x - 1, v});
                    map[x - 1][v] = 0;
                }
                if(x + 1 < map.length && map[x + 1][v] == 1){
                    queue.add(new int[]{x + 1, v});
                    map[x + 1][v] = 0;
                }
                if(v - 1 >= 0 && map[x][v - 1] == 1){
                    queue.add(new int[]{x, v - 1});
                    map[x][v - 1] = 0;
                }
                if(v + 1 < map[0].length && map[x][v + 1] == 1){
                    queue.add(new int[]{x, v + 1});
                    map[x][v + 1] = 0;
                }
            }
        }
}

