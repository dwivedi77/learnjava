package abhi.learn.java.core.generics;

import java.util.Comparator;

/**
 * Created by Abhishek on 1/22/2017.
 */
public class BigComparator {

    public static <T extends Comparator<T>> int compareTo(T t1, T t2){
        return -1;
    }
}
