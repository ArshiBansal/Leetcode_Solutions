class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];

        // Min-heap: stores {time, x, y}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{grid[0][0], 0, 0});

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0], x = curr[1], y = curr[2];

            if (visited[x][y]) continue;
            visited[x][y] = true;

            // If we reach the bottom-right cell
            if (x == n - 1 && y == n - 1) {
                return time;
            }

            // Explore 4 directions
            for (int[] d : directions) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                    pq.offer(new int[]{Math.max(time, grid[nx][ny]), nx, ny});
                }
            }
        }

        return -1; // Should never reach here
    }
}