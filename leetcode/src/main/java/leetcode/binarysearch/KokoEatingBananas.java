package leetcode.binarysearch;

public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;

        // Find the maximum pile size to set the upper bound of binary search
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        int minSpeed = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canEatAll(piles, mid, h)) {
                minSpeed = mid; // Try to find a slower valid speed
                right = mid - 1;
            } else {
                left = mid + 1; // Speed is too slow, need to eat faster
            }
        }

        return minSpeed;
    }

    // Helper method to check if Koko can eat all piles within 'h' hours at speed 'k'
    private boolean canEatAll(int[] piles, int k, int h) {
        long totalHours = 0; // Use long to prevent potential overflow

        for (int pile : piles) {
            // Equivalent to Math.ceil((double) pile / k) without floating-point inaccuracies
            totalHours += (pile + k - 1) / k;
        }

        return totalHours <= h;
    }

    public static void main(String[] args) {
        KokoEatingBananas solver = new KokoEatingBananas();

        // Test Case 1: Expected Output = 4
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        System.out.println("Test Case 1: " + solver.minEatingSpeed(piles1, h1));

        // Test Case 2: Expected Output = 30
        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        System.out.println("Test Case 2: " + solver.minEatingSpeed(piles2, h2));

        // Test Case 3: Expected Output = 23
        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 6;
        System.out.println("Test Case 3: " + solver.minEatingSpeed(piles3, h3));
    }
}