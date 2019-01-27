package abhi.learn.java.leetcode;

/**
 * Created by Abhishek on 12/29/2018.
 */
public class Solution {

    private static int guess;
    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        System.out.println("Time Taken="+(System.currentTimeMillis()-startTime));
        System.out.println("END");
    }

    public static int guessNumber(int n) {
        int x = 0;
        int lower = 1;
        int floor = n;
        int result = floor/2;
        int test = guess(result);
        while (test != 0){
            x++;
            if (test > 0){
                floor = result;
                result = (floor)/2;
                test = guess(result);
            }else if (test < 0){
                lower = result;
                result = lower + (floor - lower)/2;
                test = guess(result);
            }
        }
        System.out.println("Total iteration="+x);
        return result;
    }

    private static int guess(int num){
        if (num < guess)
            return -1;
        else if (num > guess)
            return 1;
        else return 0;
    }
}
