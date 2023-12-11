package abhi.learn.java.leetcode.hard;

import java.util.*;

/**
 * Created by Abhishek on 2/4/2022.
 */
public class HardMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        HardMain main = new HardMain();
        int[] input = new int[]{0,2,1};
        Object output  = main.countSmaller(input);

        System.out.println("Answer="+output);

        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }


    /// https://leetcode.com/problems/count-of-smaller-numbers-after-self/
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>(nums.length);
        int[] values = new int[nums.length];
        if (nums == null || nums.length == 0) return result;

        for (int i = nums.length-2; i >=0 ; i--) {
            int cnt = 0;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] >= nums[i]){
                    values[i] = values[j]+cnt;
                    break;
                }else cnt++;
            }
            if (values[i] < cnt) values[i] = cnt;
        }
        for (int i = 0; i < values.length; i++) {
            result.add(values[i]);
        }
        return result;
    }


    /// https://leetcode.com/problems/palindrome-pairs/
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length == 0) return result;
        boolean isPalin = false;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) continue;
                String first = words[i];
                String second = words[j];
                isPalin = true;
                int x = 0, y = second.length()-1, len = first.length();
                while (x < len+y){
                    char f = (x < len ? first.charAt(x) : second.charAt(x-len));
                    char s = (y >= 0 ? second.charAt(y) : first.charAt(len+y));
                    if ( f!=s) {isPalin = false;break;}
                    x++; y--;
                }
                if (isPalin) result.add(Arrays.asList(i,j));
            }/// j lop
        }/// i loop
        return result;
    }

}
