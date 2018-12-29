package abhi.learn.java.puzzles;

import java.util.Arrays;

/**
 * Created by Abhishek on 7/31/2016.
 */
public class AudiblePuzzle {

    public static void main(String[] args) {
        int[] ary = {0, 1, 9, 91, 20,31,315, 7};
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

    //inserts an integer into an array at a given position.
    private static int[] insertInToArray(int[] a, int pos, int num) {
        int[] result = new int[a.length];
        for(int i = 0; i < pos; i++)
            result[i] = a[i];
        result[pos] = num;
        for(int i = pos + 1; i < a.length; i++)
            result[i] = a[i - 1];
        return result;
    }

    //x=109, y=53, output = 10953
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

}
