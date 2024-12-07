package com.example.dynamicprogramming.medium;

import java.util.List;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * <p>
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * The answer is guaranteed to fit into a signed 32-bit integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * <p>
 * Example 2:
 * <p>
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * <p>
 * Example 3:
 * <p>
 * Input: amount = 10, coins = [10]
 * Output: 1
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * All the values of coins are unique.
 * 0 <= amount <= 5000
 * <p>
 * Leetcode link : https://leetcode.com/problems/coin-change-ii/description/
 */
public class CoinChangeII {

    static int count(int[] coins, int n, int sum) {
        // dp[i] will be storing the number of solutions for
        // value i. We need sum+1 rows as the dp is
        // constructed in bottom up manner using the base case
        // (sum = 0)
        int dp[] = new int[sum + 1];

        // Base case (If given value is 0)
        dp[0] = 1;

        // Pick all coins one by one and update the dp[]
        // values after the index greater than or equal to the
        // value of the picked coin
        for (int i = 0; i < n; i++)
            for (int j = coins[i]; j <= sum; j++)
                dp[j] += dp[j - coins[i]];

        return dp[sum];
    }

    // Returns total distinct ways to make sum using n coins of
    // different denominations
    static int count(List<Integer> coins, int n, int sum) {
        // 2D dp array where n is the number of coin
        // denominations and sum is the target sum
        int[][] dp = new int[n + 1][sum + 1];

        // Represents the base case where the target sum is 0,
        // and there is only one way to make change: by not
        // selecting any coin
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                // Add the number of ways to make change without
                // using the current coin
                dp[i][j] += dp[i - 1][j];

                if ((j - coins.get(i - 1)) >= 0) {
                    // Add the number of ways to make change
                    // using the current coin
                    dp[i][j] += dp[i][j - coins.get(i - 1)];
                }
            }
        }
        return dp[n][sum];
    }

    public int change2(int amount, int[] coins) {
        Integer[][] memo = new Integer[coins.length][amount + 1];
        return findWays(amount, coins, memo, 0);
    }

    private int findWays(int amount, int[] coins, Integer[][] memo, int index) {

        if (amount == 0) {
            return 1;
        } else if (index >= coins.length || amount < 0) {
            return 0;
        } else if (memo[index][amount] != null) {
            return memo[index][amount];
        } else {
            memo[index][amount] = findWays(amount - coins[index], coins, memo, index) + findWays(amount, coins, memo, index + 1);
        }
        return memo[index][amount];
    }

}
