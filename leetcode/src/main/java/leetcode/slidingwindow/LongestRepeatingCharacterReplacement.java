package leetcode.slidingwindow;

public class LongestRepeatingCharacterReplacement {

    public static int characterReplacement(String s, int k) {
        if (s == null) {
            return 0;
        }

        // Frequency array for ASCII characters (size 128)
        int[] count = new int[128];
        int left = 0;
        int maxCount = 0; // Frequency of the most frequent character in the current window
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char rChar = s.charAt(right);
            count[rChar]++;
            
            // Update maxCount with the frequency of the current character
            maxCount = Math.max(maxCount, count[rChar]);

            // If the number of replacements needed exceeds k, shrink the window from the left
            // Window length = (right - left + 1)
            if ((right - left + 1) - maxCount > k) {
                char lChar = s.charAt(left);
                count[lChar]--;
                left++;
            }

            // Update the maximum length of a valid window found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        
        int result = characterReplacement(s, k);
        System.out.println("Input String: " + s);
        System.out.println("k replacements: " + k);
        System.out.println("Longest repeating substring length: " + result); 
        // Output: 4 ("AAAA" achieved by replacing the middle 'B' at index 1)
    }
}