package leetcode.monotonic.stack;

import java.util.*;

public class LargestRectangleHistogram {

    public static int largestRectangleArea(int[] heights) {

        int n = heights.length;
        int maxArea = 0;

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i <= n; i++) {

            // Treat the extra position as a zero-height bar.
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() &&
                   heights[stack.peek()] > currentHeight) {

                int height = heights[stack.pop()];

                int width = stack.isEmpty()
                        ? i
                        : i - stack.peek() - 1;

                int area = height * width;

                maxArea = Math.max(maxArea, area);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {

        int[] heights = {2, 1, 5, 6, 2, 3};

        System.out.println(
            "Maximum rectangle area: " +
            largestRectangleArea(heights)
        );
    }
}