package abhi.learn.java.core.lang;

/**
 * Created by Abhishek on 9/29/2018.
 */
public class ArraysMain {

    public static void main(String[] args) {
        System.out.println("START");
        testArraysEquality();
        System.out.println("END");
    }


    private static void testArraysEquality() {
        int[] ary1 = {1,2,3};
        int[] ary2 = {1,2,3};
        System.out.println(ary1.equals(ary2));

    }

    private static void testArraysConstructions() {
        int[][] ary = new int[4][];
        ary[0] = new int[1];
        ary[1] = new int[10];
        ary[2] = new int[5];
        ary[3] = new int[6];

    }

    private static void print2DArray(){
        int arr[][] = new int[4][];
        arr[0] = new int[1];
        arr[1] = new int[2];
        arr[2] = new int[3];
        arr[3] = new int[4];

        int i, j, k = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < i + 1; j++) {
                arr[i][j] = k;
                k++;
            }
        }
        for (i = 0; i < 4; i++) {
            for (j = 0; j < i + 1; j++) {
                System.out.print(" " + arr[i][j]);
                k++;
            }
            System.out.println();
        }
    }
}
