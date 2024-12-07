package com.example.dynamicprogramming.medium;

public class JumpGameII {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1, 1, 1};
        JumpGameII obj = new JumpGameII();
        int count = obj.jump(nums);
        System.out.print(count);
    }

    public int jump2(int[] nums) {
        int i = 0;
        int counter = 0;
        if (nums.length == 1)
            return 0;
        while (i < nums.length) {
            counter++;
            int range = i + nums[i];
            if (range >= nums.length - 1)
                break;
            int max = 0;
            int temp = 0;
            for (int k = i + 1; k <= range; k++) {
                if (nums[k] + k >= max) {
                    max = nums[k] + k;
                    temp = k;
                }
            }
            i = temp;
        }
        return counter;
    }

    int minSteps = Integer.MAX_VALUE;

    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        check(nums, 0, 0);
        return minSteps;
    }

    private void check(int[] nums, int index, int steps) {
        if (index < nums.length) {
            int toBeReached = index + nums[index];
            if (toBeReached >= nums.length - 1) {
                minSteps = Math.min(steps + 1, minSteps);
            } else {
                for (int p = index + 1; p <= toBeReached; p++) {
                    check(nums, p, steps + 1);
                }
            }
        }
    }
}
