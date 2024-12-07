package com.example.dynamicprogramming.medium;

/**
 * You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 * <p>
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
 * <p>
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * <p>
 * The testcases are generated so that the answer will be less than or equal to 2 * 109.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * <p>
 * Example 2:
 * <p>
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] is 0 or 1.
 * <p>
 * Leetcode link : https://leetcode.com/problems/unique-paths-ii/description/
 */
public class UniquePathsII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if (obstacleGrid.length == 1 && obstacleGrid[0].length ==1) {
            return obstacleGrid[0][0] == 0 ? 1 : 0;
        }
        if(obstacleGrid[0][0] == 1){
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        findPaths(0, 0, m, n, dp,obstacleGrid);
        return dp[0][0];
    }

    private int findPaths(int i, int j, int m, int n, int[][] dp,int[][] obstacleGrid) {
        if (i <= m - 1 && j <= n - 1) {
            if(obstacleGrid[i][j] == 1){
                return 0;
            }
            if (dp[i][j] != -1) {
                return dp[i][j];
            }
            if (i == m - 1 && j == n - 1) {
                return 1;
            }
            int path1 = findPaths(i + 1, j, m, n, dp,obstacleGrid);
            int path2 = findPaths(i, j + 1, m, n, dp,obstacleGrid);
            dp[i][j] = path1 + path2;
            return path1 + path2;
        }
        return 0;
    }

}
