package abhi.learn.java;

import abhi.learn.java.core.lang.Outer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static abhi.learn.java.core.lang.Outer.*;

/**
 * Created by Abhishek on 5/14/2016.
 */
public class TestMain {


    static int x = 4;
    public static void main(String[] args)  {
        System.out.println("START");
        int x = 5;
        double y = -5.234236;
        System.out.println(Math.abs(y));
//        testArrayList();

        System.out.println("END");
    }


    private static void testArrayList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(new Integer(1));
        list.add(null);
        list.add(new Integer(1));
        list.add(null);
        list.add(new Integer(2));
        list.add(null);
        list.add(new Integer(4));
        list.add(null);
        list.add(new Integer(15));
        for (Integer x : list) {
            System.out.println("var="+x);
        }
        System.out.println("Size="+list.size());
    }

    private static void testInnerClasses1() {
        Outer outer = new Outer(18, "Udbhav");

        Outer.Inner inner = outer. new Inner("Abhi");
        inner.testTest();
        Outer.StatInner sinner = new Outer.StatInner("");
        sinner.testTest();
    }



    static int reductionCost(int[] num) {
        if (num == null) return -1;
        Arrays.sort(num);
        LinkedList<Integer> q = new LinkedList<>();
        int result = 0;
        for (int i = 0; i < num.length; i++) {
            result = result + num[i];
            q.add(result);
        }
        int sum = 0;
        for (int i = 1; i < q.size(); i++) {
            sum = sum+q.get(i);
        }
        return sum;
    }

    static int maxDifference(int[] a) {
        int max = -1;
        if (a == null) return max;
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                if (a[j] > a[i]){
                    max = ((a[j] - a[i]) > max) ? (a[j] - a[i]) : max;
                }
            }
        }

        return max;
    }
    private static void test(){

    }


//    static void testComparator(){
//        Scanner scan = new Scanner(System.in);
//        Comparator comp = new Comparator();
//
//        int testCases = Integer.parseInt(scan.nextLine());
//        while(testCases-- > 0){
//            int condition = Integer.parseInt(scan.nextLine());
//            switch(condition){
//                case 1:
//                    String s1=scan.nextLine().trim();
//                    String s2=scan.nextLine().trim();
//
//                    System.out.println( (comp.compare(s1,s2)) ? "Same" : "Different" );
//                    break;
//                case 2:
//                    int num1 = scan.nextInt();
//                    int num2 = scan.nextInt();
//
//                    System.out.println( (comp.compare(num1,num2)) ? "Same" : "Different");
//                    if(scan.hasNext()){ // avoid exception if this last test case
//                        scan.nextLine(); // eat space until next line
//                    }
//                    break;
//                case 3:
//                    // create and fill arrays
//                    int[] array1 = new int[scan.nextInt()];
//                    int[] array2 = new int[scan.nextInt()];
//                    for(int i = 0; i < array1.length; i++){
//                        array1[i] = scan.nextInt();
//                    }
//                    for(int i = 0; i < array2.length; i++){
//                        array2[i] = scan.nextInt();
//                    }
//
//                    System.out.println( comp.compare(array1, array2) ? "Same" : "Different");
//                    if(scan.hasNext()){ // avoid exception if this last test case
//                        scan.nextLine(); // eat space until next line
//                    }
//                    break;
//                default:
//                    System.err.println("Invalid input.");
//            }// end switch
//        }// end while
//        scan.close();
//    }
//}
//
//class Comparator {
//    boolean compare(int a, int b){
//        if (a == b) return true;
//        else return false;
//    }
//
//    boolean compare(String a, String b){
//        if (a == null) return false; //irrespective of b null or not, it will return false. as we r not considering 2 nulls as equal.
//        return a.equalsIgnoreCase(b);
//    }
//
//    boolean compare(int[] a, int[] b){
//        if (a == null || b == null) return false;
//        if (a.length != b.length) return false;
//        for (int i = 0; i < a.length; i++) {
//            if (a[i] != b[i]) return false;
//        }
//        return true;
//    }
}