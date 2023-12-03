package abhi.learn.java.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Abhishek on 10/7/2016.
 */
public class Solution {

    public static void main(String[] args)  {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */



    }

    private static void someMethod()throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null) {
//            System.out.println(s);
            char[] input = s.toCharArray();
            char prev = ' ';
            int prevRem = -1;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < input.length; i++) {
                if (prev == ' ') {// this is for first iteration
                    prev = input[i];
                    prevRem = prev % 2;
                    sb.append(prev);
                    continue;
                }
                char next = input[i];
                int nextRem = next % 2;

                if (prev == 48 || next == 48) { //if either of the char equal to 0
                    sb.append(next);
                    prev = next;
                    continue;
                }
                switch (prevRem + nextRem) {
                    case 0: //when both are even
                        sb.append('*');
                        sb.append(next);
                        prev = next;
                        prevRem = nextRem; //remembering the previous values for next itr
                        break;
                    case 1:
                        sb.append(next);
                        prev = next;
                        prevRem = nextRem; //remembering the previous values for next itr
                        break;
                    case 2://when both are odd
                        sb.append('-');
                        sb.append(next);
                        prev = next;
                        prevRem = nextRem; //remembering the previous values for next itr
                        break;
                }

            }
            System.out.println(sb.toString());
        }
    }



    private static void findLongestSuffix(){
        Scanner in = new Scanner(System.in);
        String nextLine = in.nextLine();
        int commaIdx = nextLine.indexOf(',');
        String first = nextLine.substring(0, commaIdx);
        String second = nextLine.substring(commaIdx+1, nextLine.length());
        // It is not given if we have to be case insensitive. So this step might be unnecessary, but since information is missing I still added it.
        //trimming has to be done as the test input contains extra space
        first = first.trim().toLowerCase();
        second = second.trim().toLowerCase();

        int fLength = first.length();
        int sLength = second.length();
        StringBuilder sb = new StringBuilder("");
        for (int i = fLength-1, j=sLength-1; i >=0 && j >=0; i--, j--) {
            if (first.charAt(i) == second.charAt(j)){
                // add it to a buffer
                sb.append(first.charAt(i));
            }else{
                break;
            }
        }
        //reversing it as we have appended the char from end to begining.
        String suffix = sb.reverse().toString();
        if ("".equals(suffix)) System.out.println("NULL");
        else System.out.println(suffix);
        return ;
    }

    private static void kangaroos(){
        Scanner in = new Scanner(System.in);
        int x1 = in.nextInt();
        int v1 = in.nextInt();
        int x2 = in.nextInt();
        int v2 = in.nextInt();

        int loc1 = 0;
        int loc2 = 0;
        int diff = 0;

        boolean isX1StartsAhead = (x1 > x2);
        if (isX1StartsAhead){
            if (v1 >= v2) {System.out.println("YES"); return;}
        }else{
            if (v2 > v1) {System.out.println("NO"); return;}
        }

        for (int i = 0; i < 10000; i++) {
            loc1 = x1 + i*v1;
            loc2 = x2 + i*v2;
            if (isX1StartsAhead){
                diff = loc1 - loc2;
            }else{
                diff = loc2 - loc1;
            }

            if (diff == 0){
                System.out.println("YES");return;
            }else if (diff > 0){
//                System.out.println("Continuing");
                // do nothing
            } else if (diff < 0){
                System.out.println("NO");return;
            }
        }
        System.out.println("NO");return;

    }

    private static void biggerIsGreater(){
        Scanner scan = new Scanner(System.in);
        int total = scan.nextInt();
        for (int i = 0; i < total; i++) {
            String next = scan.next();
            String answer = getSmallestBiggerString(next);
            System.out.println(answer);
        }
    }

    private static String getSmallestBiggerString(String input) {
        if (input == null || input.length() == 0) return "no answer";
        char[] next = input.toCharArray();
        int len = next.length;
        int i=len-1,j=len-1;
        boolean swapped = false;
        for (; i >= 0; i--) {
            char first = next[i];
            for (j=i; j >= 0; j--) {
                char second = next[j];
                if (first > second) {
                    char temp = next[i];
                    next[i] = next[j];
                    next[j] = temp;
                    swapped = true;
                    break;
                }
            }
            if (swapped) break;
        }
        if (!swapped) return "no answer";
        Arrays.sort(next, j+1, len);
        return new String(next);
    }
}
