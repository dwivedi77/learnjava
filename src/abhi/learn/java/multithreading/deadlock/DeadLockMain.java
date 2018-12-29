package abhi.learn.java.multithreading.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Abhishek on 7/19/2016.
 */
public class DeadLockMain {

    public static void main(String[] args) {
        Lock a = new ReentrantLock();
        Lock b = new ReentrantLock();

        Thread first = new Thread(new MyRunnable("First Thread", a, b), "First");
        Thread second = new Thread(new MyRunnable("Second Thread", b, a), "Second");

        first.start();second.start();
        System.out.println("Main thread done.");

    }
}
