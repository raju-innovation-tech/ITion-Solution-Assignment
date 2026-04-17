// -------------------- PROBLEM 3 --------------------
public class Problem3 {

    // 8 directions (horizontal, vertical, diagonal)
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    /*
     * DFS function to calculate size of an orchard
     * Approach:
     * - Mark current cell visited
     * - Explore all 8 directions
     * - Count connected 'T'
     */
    public static int dfs(char[][] grid, boolean[][] visited, int i, int j) {

        visited[i][j] = true;
        int count = 1;

        for (int d = 0; d < 8; d++) {
            int ni = i + dx[d];
            int nj = j + dy[d];

            // Check boundaries and unvisited tree
            if (ni >= 0 && nj >= 0 && ni < grid.length && nj < grid[0].length) {
                if (grid[ni][nj] == 'T' && !visited[ni][nj]) {
                    count += dfs(grid, visited, ni, nj);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {

        char[][] grid = {
                {'O','T','O','O'},
                {'O','T','O','T'},
                {'T','T','O','T'},
                {'O','T','O','T'}
        };

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        System.out.print("Orchard Sizes: ");

        // Traverse entire grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                // If tree and not visited, start DFS
                if (grid[i][j] == 'T' && !visited[i][j]) {
                    int size = dfs(grid, visited, i, j);
                    System.out.print(size + " ");
                }
            }
        }
    }
}