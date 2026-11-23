package leetcode.binarysearch;

public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // If the sub-array is already sorted, the leftmost element is the minimum
            if (nums[left] <= nums[right]) {
                return nums[left];
            }

            int mid = left + (right - left) / 2;

            // If mid element is greater than rightmost, the minimum is in the right half
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } 
            // Otherwise, the minimum is in the left half (including mid)
            else {
                right = mid;
            }
        }

        return nums[left];
    }
}