package abhi.learn.java.multithreading.deadlock;

import java.util.concurrent.locks.Lock;

/**
 * Created by Abhishek on 7/19/2016.
 */
public class Resource {
    private String name;

    public Resource(String name) {
        this.name = name;
    }

}
