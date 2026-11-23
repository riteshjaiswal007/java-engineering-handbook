package leetcode.heappriority;

import java.util.*;

public class KClosestPoints {

    public static int[][] kClosest(int[][] points, int k) {

        // Max heap: farthest point stays at the top
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Long.compare(
                distance(b), distance(a)
            )
        );

        for (int[] point : points) {

            maxHeap.offer(point);

            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] result = new int[k][2];

        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }

        return result;
    }

    private static long distance(int[] point) {
        long x = point[0];
        long y = point[1];

        return x * x + y * y;
    }

    public static void main(String[] args) {

        int[][] points = {
            {1, 3},
            {-2, 2},
            {5, 8},
            {0, 1}
        };

        int k = 2;

        int[][] result = kClosest(points, k);

        System.out.println("K Closest Points:");

        for (int[] point : result) {
            System.out.println(Arrays.toString(point));
        }
    }
}