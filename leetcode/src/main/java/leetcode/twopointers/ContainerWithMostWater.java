package leetcode.twopointers;

public class ContainerWithMostWater {

    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while (left < right) {
            // Calculate width and height
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            
            // Calculate current area and update maxWater
            int currentArea = width * h;
            maxWater = Math.max(maxWater, currentArea);

            // Move the pointer of the shorter line inward
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        
        int result = maxArea(height);
        System.out.println("Maximum water container can store: " + result); 
        // Output: 49 (formed by lines at index 1 (height 8) and index 8 (height 7): width = 8 - 1 = 7, min height = 7, area = 7 * 7 = 49)
    }
}