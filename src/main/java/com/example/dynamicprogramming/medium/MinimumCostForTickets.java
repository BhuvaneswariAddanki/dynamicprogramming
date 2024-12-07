package com.example.dynamicprogramming.medium;

import java.util.Arrays;

/**
 * You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
 * <p>
 * Train tickets are sold in three different ways:
 * <p>
 * a 1-day pass is sold for costs[0] dollars,
 * a 7-day pass is sold for costs[1] dollars, and
 * a 30-day pass is sold for costs[2] dollars.
 * <p>
 * The passes allow that many days of consecutive travel.
 * <p>
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
 * <p>
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 * Output: 11
 * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 * In total, you spent $11 and covered all the days of your travel.
 * <p>
 * Example 2:
 * <p>
 * Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * Output: 17
 * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
 * On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
 * In total, you spent $17 and covered all the days of your travel.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days is in strictly increasing order.
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 * <p>
 * <p>
 * Leetcode link : https://leetcode.com/problems/minimum-cost-for-tickets/description/
 */
public class MinimumCostForTickets {


    public int mincostTickets(int[] days, int[] costs) {
        Integer[] memo = new Integer[days.length];
        return findMinCost(days, costs, memo, 0);
    }

    private int findMinCost(int[] days, int[] costs, Integer[] memo, int index) {
        if (index >= days.length) {
            return 0;
        }

        if (memo[index] != null) {
            return memo[index];
        }

        int day1Pass = costs[0] + findMinCost(days, costs, memo, index + 1);

        int j = index + 1;
        while (j < days.length && days[j] < days[index] + 7) {
            j++;
        }
        int day7Pass = costs[1] + findMinCost(days, costs, memo, j);

        j = index + 1;
        while (j < days.length && days[j] < days[index] + 30) {
            j++;
        }
        int day30Pass = costs[2] + findMinCost(days, costs, memo, j);

        memo[index] = Math.min(day1Pass, Math.min(day30Pass, day7Pass));
        return memo[index];
    }

    public int mincostTicketswith365(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[366];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int j = 0;
        for (int i = 1; i <= 365; i++) {
            if (j < n && i == days[j]) {
                dp[i] = Math.min(dp[i], dp[i - 1] + costs[0]);
                if (i >= 7)
                    dp[i] = Math.min(dp[i], dp[i - 7] + costs[1]);
                else
                    dp[i] = Math.min(dp[i], costs[1]);
                if (i >= 30)
                    dp[i] = Math.min(dp[i], dp[i - 30] + costs[2]);
                else
                    dp[i] = Math.min(dp[i], costs[2]);
                j++;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[365];
    }


    int min = Integer.MAX_VALUE;

    public int mincostTickets2(int[] days, int[] costs) {
        findMinCost2(days, costs, 0, 0, 0);
        return min;
    }

    private void findMinCost2(int[] days, int[] costs, int passValidUpTo, int index, int totalCost) {
        if (index == days.length) {
            min = Math.min(totalCost, min);
        } else if (days[index] > passValidUpTo) {
            findMinCost2(days, costs, passValidUpTo + 1, index + 1, totalCost + costs[0]);
            findMinCost2(days, costs, passValidUpTo + 7, index + 1, totalCost + costs[1]);
            findMinCost2(days, costs, passValidUpTo + 30, index + 1, totalCost + costs[2]);
        } else {
            findMinCost2(days, costs, passValidUpTo, index + 1, totalCost);
        }

    }
}
