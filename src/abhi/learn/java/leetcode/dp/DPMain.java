package abhi.learn.java.leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abhishek on 2/22/2022.
 */
public class DPMain {

    /**
     *  MAIN Method
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();
        DPMain main = new DPMain();

        Object input = new Integer(5);
        Object output = main.robII(new int[]{1,3,1,3,100});
        System.out.println("Answer=" + output);

        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }

    /// https://leetcode.com/problems/house-robber-ii/
    public int robII(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        if (nums.length == 3) return Math.max(Math.max(nums[0], nums[1]), nums[2]);

        int[] dp1 = new int[nums.length];
        int[] dp2 = new int[nums.length];
        dp1[0] = nums[0]; dp1[1] = Math.max(dp1[0],nums[1]);
        dp2[1] = nums[1]; dp2[2] = Math.max(dp2[1],nums[2]);

        for (int i = 2; i < nums.length-1; i++) {
            dp1[i] = Math.max(dp1[i-2]+nums[i], dp1[i-1]);
        }
        for (int i = 3; i < nums.length; i++) {
            dp2[i] = Math.max(dp2[i-2]+nums[i], dp2[i-1]);
        }

        return Math.max(dp1[dp1.length-2], dp2[dp2.length-1]);
    }

    private int findIndexForMax(int[] nums, int i, int j, int k) {
        int result = 0;
        if (nums[i] >= nums[j])
            result = i;
        else result = j;
        if (nums[j] > nums[result])
                result = j;

        return result;
    }

    /// https://leetcode.com/problems/house-robber/
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0]; if (nums.length == 1) return dp[0];
        dp[1] = Math.max(nums[0], nums[1]); if (nums.length == 2) return dp[1];

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }
        return dp[dp.length-1];
    }

    /// https://leetcode.com/problems/min-cost-climbing-stairs/
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) return 0;
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < dp.length; i++) {
            dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]);
        }
        return Math.min(dp[dp.length-2], dp[dp.length-1]);
    }

    /// https://leetcode.com/problems/climbing-stairs/
    public int climbStairs(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /// https://leetcode.com/problems/n-th-tribonacci-number/
    public int tribonacci(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n+1];
        dp[0] = 0; dp[1] = 1; dp[2] = 1;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
        }
        return dp[n];
    }

    private Map<Integer, Integer> store = new HashMap<>();
    public int fibII(int n) {
        if (store.containsKey(n)) return store.get(n);
        if (n <= 1) {
            store.put(n,n);
            return n;
        }
        store.put(n, fib(n-1) +fib(n-2));
        return store.get(n);
    }

    /// https://leetcode.com/problems/fibonacci-number/
    public int fib(int n) {
        if (n ==0) return 0;
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

}
