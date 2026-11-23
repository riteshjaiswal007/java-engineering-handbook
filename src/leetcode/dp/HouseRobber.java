package leetcode.dp;

public class HouseRobber {

    public int rob(int[] houseMoney) {

        int prev2 = 0;
        int prev1 = 0;

        for (int money : houseMoney) {

            int currentMax = Math.max(
                prev1,
                money + prev2
            );

            prev2 = prev1;
            prev1 = currentMax;
        }

        return prev1;
    }

    public static void main(String[] args) {

        HouseRobber robber = new HouseRobber();

        int[] houseMoney = {2, 7, 9, 3, 1};

        System.out.println(
            "Maximum money: " + robber.rob(houseMoney)
        );
    }
}