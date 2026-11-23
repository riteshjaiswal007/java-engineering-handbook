package leetcode.interval;

import java.util.Arrays;

public class NonOverlappingIntervals {

    public static int eraseOverlapIntervals(int[][] intervals) {

        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort by ending time
        Arrays.sort(intervals,
            (a, b) -> Integer.compare(a[1], b[1]));

        int removals = 0;

        // End time of the last selected interval
        int previousEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {

            // Overlap detected
            if (intervals[i][0] < previousEnd) {

                removals++;

            } else {

                // Non-overlapping interval: keep it
                previousEnd = intervals[i][1];
            }
        }

        return removals;
    }

    public static void main(String[] args) {

        int[][] intervals = {
            {1, 2},
            {2, 3},
            {3, 4},
            {1, 3}
        };

        System.out.println(
            "Minimum removals: " +
            eraseOverlapIntervals(intervals)
        );
    }
}