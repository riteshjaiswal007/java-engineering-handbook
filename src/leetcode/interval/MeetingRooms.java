package leetcode.interval;

import java.util.Arrays;

public class MeetingRooms {

    public static boolean canAttendMeetings(int[][] intervals) {

        if (intervals == null || intervals.length <= 1) {
            return true;
        }

        // Sort by meeting start time
        Arrays.sort(intervals,
            (a, b) -> Integer.compare(a[0], b[0]));

        // Check consecutive meetings
        for (int i = 1; i < intervals.length; i++) {

            // Current meeting starts before previous ends
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int[][] meetings1 = {
            {0, 30},
            {5, 10},
            {15, 20}
        };

        int[][] meetings2 = {
            {7, 10},
            {2, 4}
        };

        System.out.println(canAttendMeetings(meetings1));
        System.out.println(canAttendMeetings(meetings2));
    }
}