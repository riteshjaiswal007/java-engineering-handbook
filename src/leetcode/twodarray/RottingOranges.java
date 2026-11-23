package leetcode.twodarray;

import java.util.*;

public class RottingOranges {

    public static int orangesRotting(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        int freshCount = 0;
        int minutes = 0;

        // Four possible directions: up, down, left, right
        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        // Step 1: Add all rotten oranges to queue
        // Count all fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        // No fresh oranges
        if (freshCount == 0) {
            return 0;
        }

        // Step 2: Multi-source BFS
        while (!queue.isEmpty()) {

            int size = queue.size();
            boolean rottedThisMinute = false;

            // Process one complete BFS level
            for (int i = 0; i < size; i++) {

                int[] current = queue.poll();

                int row = current[0];
                int col = current[1];

                // Check all four directions
                for (int[] dir : directions) {

                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    // Check boundaries and fresh orange
                    if (newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols &&
                        grid[newRow][newCol] == 1) {

                        // Make orange rotten
                        grid[newRow][newCol] = 2;

                        freshCount--;
                        rottedThisMinute = true;

                        queue.offer(new int[]{
                            newRow, newCol
                        });
                    }
                }
            }

            // Increment time only if at least one orange rotted
            if (rottedThisMinute) {
                minutes++;
            }
        }

        // Step 3: Check if any fresh orange remains
        return freshCount == 0 ? minutes : -1;
    }

    public static void main(String[] args) {

        int[][] grid = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };

        System.out.println(
            "Minutes required: " + orangesRotting(grid)
        );
    }
}