package leetcode.interval;

import java.util.*;

public class InsertInterval {

    public static int[][] insert(
            int[][] intervals,
            int[] newInterval) {

        List<int[]> result = new ArrayList<>();

        int i = 0;
        int n = intervals.length;

        // Phase 1: Add intervals before newInterval
        while (i < n &&
               intervals[i][1] < newInterval[0]) {

            result.add(intervals[i]);
            i++;
        }

        // Phase 2: Merge overlapping intervals
        while (i < n &&
               intervals[i][0] <= newInterval[1]) {

            newInterval[0] = Math.min(
                newInterval[0],
                intervals[i][0]
            );

            newInterval[1] = Math.max(
                newInterval[1],
                intervals[i][1]
            );

            i++;
        }

        // Add merged interval
        result.add(newInterval);

        // Phase 3: Add remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {

        int[][] intervals = {
            {1, 3},
            {6, 9}
        };

        int[] newInterval = {2, 5};

        int[][] result = insert(intervals, newInterval);

        System.out.println(Arrays.deepToString(result));
    }
}