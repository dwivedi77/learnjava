package abhi.learn.java.ib;

import java.util.*;

class A {
    public static void main(String[] args) {
        final List<Integer> list = new ArrayList<Integer>();
        Collections.addAll(list, 1, 5, 2, 3, 7, 3, 8, 9);
        final Integer pos = Integer.valueOf(3);
        list.remove(pos);
        System.out.println(list);
    }
}