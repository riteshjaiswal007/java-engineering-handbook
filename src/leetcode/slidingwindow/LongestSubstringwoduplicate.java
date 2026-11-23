package leetcode.slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringwoduplicate {

    public static void main(String[] args) {
        String s = "abcasoiuyt";

        Set<Character> set = new HashSet<>();
        int maxCount = 0;
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            // If the character is already in the set, shrink the window from the left
            // by removing characters until the duplicate is removed
            while (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start));
                start++;
            }

            // Add the new character and update max length
            set.add(s.charAt(end));
            maxCount = Math.max(maxCount, end - start + 1);
        }

        System.out.println("max: " + maxCount);
    }
}
