package abhi.learn.java.leetcode.facebook;

import java.util.HashSet;

/**
 * Created by Abhishek on 7/9/2019.
 */
public class FBMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();
        int outx = myAtoi("-91283472332");

        System.out.println("Answer="+outx);
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");


    }
    ///https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3009/
    private static int myAtoi(String str) {
        int output = 0;
        int multiplier = 1;
        boolean negative = false;
        if (str == null || str.length() == 0) return output;

        for (int i = 0; i < str.length(); i++) {
            char x = str.charAt(i);
            if (' ' == x && output == 0) continue;

            if ('-' == x && output == 0) {
                multiplier = -1;
                continue;
            }else if('-' == x && output != 0){
                return 0;
            }

            int val = 0;
            switch (x){
                case '1':
                    val = 1;break;
                case '2':
                    val = 2;break;
                case '3':
                    val = 3;break;
                case '4':
                    val = 4;break;
                case '5':
                    val = 5;break;
                case '6':
                    val = 6;break;
                case '7':
                    val = 7;break;
                case '8':
                    val = 8;break;
                case '9':
                    val = 9;break;
                case '0':
                    val = 0;break;
                default: return output*multiplier;
            }
            if (multiplier == 1){
                if (Integer.MAX_VALUE - output*10 > val){
                    output = (output * 10) + val;
                }else {
                    return Integer.MAX_VALUE;
                }
            }else {
                if (Integer.MIN_VALUE + output*10 < multiplier*val){
                    output = (output * 10) + val;
                }else {
                    return Integer.MIN_VALUE;
                }
            }

        }
        return output*multiplier;
    }

    ///https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3011/
    private static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int count = 1;
        int i = 0;
        int j = 1;
        while (i<j && j < nums.length){
            if (nums[i] == nums[j]){
                i++;
            }else{
                nums[count] = nums[j];
                count++;
                i=j;
            }
            j++;
        }

        return count;
    }


    ///https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3008/
    private static int lengthOfLongestSubstring(String input) {
        int max = 0;
        for (int i = 0; i < input.length(); i++) {
            HashSet<String> distincts = new HashSet<>();
            char x = input.charAt(i);
            distincts.add(""+x);
            for (int j = i+1; j < input.length(); j++) {
                char y = input.charAt(j);
                if (distincts.contains(""+y)){
                    max = (distincts.size() > max ? distincts.size() : max);
                    break;
                }else {
                    distincts.add(""+y);
                }
                max = (distincts.size() > max ? distincts.size() : max);
            }
        }
        return max;
    }

}
