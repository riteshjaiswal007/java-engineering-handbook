package leetcode.slidingwindow;

public class MinWindowSubString {
    // s= rifgteshriitjript t=rit
    public static int getMinWindow(String s, String t) {
        int[] mapt = new int[128];
        int[] maps = new int[128];

        for (char c : t.toCharArray()) {
            mapt[c]++;
        }
        int left = 0, right = 0;
        int minlength = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            maps[c]++;
            while (contains(maps, mapt)) {
                int fountLength = right - left + 1;
                minlength=Math.min(minlength,fountLength);
                char c1=s.charAt(left);
                maps[c1]--;
                left++;
            }

          right++;
        }


        return minlength==Integer.MAX_VALUE?0:minlength;
    }

    private static boolean contains(int[] maps, int[] mapt) {
        for (int i = 0; i < 128; i++) {
            if (mapt[i] > maps[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("min window string"+getMinWindow("riyytrttipt","rit"));
    }
}
