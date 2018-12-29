package abhi.learn.java.puzzles;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Abhishek on 7/10/2016.
 */
public class PuzzlesMain {

    public static void main(String[] args) {
        int[] ary = {1, 9, 91, 20,31,315, 7};
        System.out.println(makeBiggestNoPossible(ary));
    }
    // this method will construct max possible integer from the integers given in the array
    // [1 3 5] >> 531
    // [67 671] >> 67671
    private static String makeBiggestNoPossible(int[] ary){
        if (ary == null || ary.length == 0) return "0";
        int[] result = new int[ary.length];

        int size = 0;
        for (int i = 0; i < ary.length; i++) {
            for (int j = 0; j <= size; j++) {
                    int x = ary[i];
                    int y = result[j];
                if (x < 10 && y < 10){
                    if (x > y){//insert
                        result = insertInToArray(result, j, x);
                        size++;
                        break;
                    }else{
                        continue;
                    }
                }else{
                    int a1 = concatenateIntegers(x, y);
                    int a2 = concatenateIntegers(y, x);
                    if (a1 > a2){//insert
                        result = insertInToArray(result, j, x);
                        size++;
                        break;
                    }else{
                        continue;
                    }
                }
            }
        }


        return Arrays.toString(result);
    }

    private static int[] insertInToArray(int[] a, int pos, int num) {
        int[] result = new int[a.length];
        for(int i = 0; i < pos; i++)
            result[i] = a[i];
        result[pos] = num;
        for(int i = pos + 1; i < a.length; i++)
            result[i] = a[i - 1];
        return result;
    }

    private static int concatenateIntegers(int x, int y){
        if (x == 0) return y;
        if (y == 0) return x*10 + y;
        int temp = y;
        while(temp > 0){
            temp /= 10;
            x*=10;
        }
        return x+y;
    }

    private static String compareAndSwap(int x, int y){
        if (x < 10 && y < 10){
            return x<y ? x+""+y : y+""+x;
        }

        return "";
    }
    private static int reverse(int num){
        int reverse = 0;
        while (num != 0){
            reverse = (reverse*10) + (num%10);
            num = num/10;
        }
        return  reverse;
    }

    private static int decimalToBinary(int dec){
        int div = dec/2;
        int rem = dec%2;
        if (div == 0) return rem;
        int binary = 0;
        binary = 10*decimalToBinary(dec/2) + rem;
        return binary;
    }

    private static void pringDecimalToBinary(int dec){
        while (dec != 0){
            System.out.print(dec%2);
            dec  = dec/2;
        }
    }

    private static String reverseStr(String model){
        int len = model.length();
        if (len == 0 || len == 1) return model;
        swap(model, 0, len-1);
        return null;
    }

    private static void swap(String model, int i, int j) {
        if (j-i == 0 || j-i == 1) return;
        char c = model.charAt(i);
    }
}
