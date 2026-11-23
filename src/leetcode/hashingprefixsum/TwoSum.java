package leetcode.hashingprefixsum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        // Maps each number to its index
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }

            seen.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution found");
    }

    public static void main(String[] args) {
        TwoSum solver = new TwoSum();

        // Test Case 1: Expected Output = [0, 1]
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Test Case 1: " + Arrays.toString(solver.twoSum(nums1, target1)));

        // Test Case 2: Expected Output = [1, 2]
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        System.out.println("Test Case 2: " + Arrays.toString(solver.twoSum(nums2, target2)));

        // Test Case 3: Expected Output = [0, 1]
        int[] nums3 = {3, 3};
        int target3 = 6;
        System.out.println("Test Case 3: " + Arrays.toString(solver.twoSum(nums3, target3)));
    }
}