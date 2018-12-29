package abhi.learn.java.multithreading.deadlock;

import java.util.concurrent.locks.Lock;

/**
 * Created by Abhishek on 7/19/2016.
 */
public class MyRunnable implements Runnable {
    String name;
    private Lock a;
    private Lock b;

    public MyRunnable(String name,Lock a, Lock b) {
        this.name = name;
        this.a = a;
        this.b = b;
    }

    public void run(){
        System.out.println("Thread "+name+" starts");
        int i = 0;
        a.lock();
        try{
            while (i<10){
                System.out.println("name:"+name+", i = " + i);
                if (i==5)b.lock();
                i++;
            }
        }finally {
            b.unlock();
            a.unlock();
        }
        System.out.println("Thread "+name+" ends");
    }
}
