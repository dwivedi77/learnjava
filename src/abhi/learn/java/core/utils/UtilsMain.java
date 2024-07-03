package abhi.learn.java.core.utils;

import java.util.ArrayList;
import java.util.List;

public class UtilsMain {

    public static void main(String[] args) {
        System.out.println("START");
        testArrayList();
        System.out.println("END");
    }

    private static void testArrayList(){
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);l1.add(4);

        List<Integer> l2 = new ArrayList<>();
        l2.add(2);l2.add(4);

        if (l1.hashCode() == l2.hashCode())
            System.out.println("both hashcodes r equsl");
        if (l1.equals(l2))
            System.out.println("both objects r equsl");
    }

}
