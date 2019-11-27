package abhi.learn.java.leetcode.linkedin;

/**
 * Created by Abhishek on 7/8/2019.
 */
public class LinkedInMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        int[] input = new int[]{2,3,5,4,9,6};
        int[] out = findTwoSum(input, 10);


        System.out.println("Answer="+out);
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");


    }

    private static int[] findTwoSum(int[] nums, int target){
        int[] out = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int j = nums.length-1;
            while (i<j){
                if (nums[i]+nums[j] == target){
                    out[0] = i;
                    out[1] = j;
                    return out;
                }else {
                    j--;
                }
            }
        }
        return out;
    }
}
