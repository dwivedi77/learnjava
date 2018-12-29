package abhi.learn.java.certification;

import static java.lang.Integer.*;
//import static java.lang.Integer.MIN_VALUE;
//import static java.lang.Long.MAX_VALUE;
/**
 * Created by Abhishek on 1/21/2017.
 */
public class CertMain {

    public static void main(String[] args) {
        System.out.println("START");

        learnString();

        System.out.println("END");
    }

    private static void learnString(){

        String hello = "Hello", lo = "lo";
        System.out.println("Hel"+lo);
        System.out.print((hello == "Hello") + " ");
        System.out.print((hello == ("Hel"+"lo")) + " ");
        System.out.print((hello == ("Hel"+lo)) + " ");
        System.out.println(hello == ("Hel"+lo).intern());
    }

    private static void lerarnbyte(){
        int x = 14354;
        byte y  = (byte) x;
        x=y;
        System.out.println("x = " + x);

        byte z = 1;
        z = (byte) (z<<1);
        System.out.println("z = " + z);
        z = (byte) (z<<1);
        System.out.println("z = " + z);
        z = (byte) (z<<1);
        System.out.println("z = " + z);
    }

    private static void learnFloating(){
        float x = Float.NaN;
        float y = 0f/0f;
        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }
}
