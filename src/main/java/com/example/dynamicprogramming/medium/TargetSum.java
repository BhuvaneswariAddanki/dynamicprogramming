package com.example.dynamicprogramming.medium;

/**
 * You are given an integer array nums and an integer target.
 * <p>
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
 * <p>
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 * <p>
 * Return the number of different expressions that you can build, which evaluates to target.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1], target = 1
 * Output: 1
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 * <p>
 * <p>
 * Leetcode link : https://leetcode.com/problems/target-sum/description/
 */
public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {

        return backTrack(nums, 0,0,target);
    }

    private int backTrack(int[] nums, int index, int sum, int target) {
        if (index == nums.length) {
            if (sum == target) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return backTrack(nums, index + 1, sum + nums[index], target)
                    + backTrack(nums, index + 1, sum - nums[index], target);
        }
    }

}
