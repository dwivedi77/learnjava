package abhi.learn.java.multithreading;

import abhi.learn.java.multithreading.rollercoaster.Car;
import abhi.learn.java.multithreading.rollercoaster.Passanger;

import java.util.LinkedList;
import java.util.concurrent.*;

/**
 * Created by Abhishek on 7/4/2016.
 */
public class MultiThreadingMain {
    public static void main(String[] args) {
        startRollerCoaster();
    }

    private static void testThreadPoolExceptions(){
        ExecutorService pool = Executors.newFixedThreadPool(5);
        System.out.println("Pool size = "+((ThreadPoolExecutor)pool).getCorePoolSize());

        pool.submit(new Callable<Object>() {

            @Override
            public Object call() throws Exception {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {e.printStackTrace();}
                throw new Exception("Exception from runnable 1");
            }
        });

        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(18);
                } catch (InterruptedException e) {e.printStackTrace();}
                throw new RuntimeException("Exception from runnable 2");
            }
        });

        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("printing from runnable 3");
            }
        });

        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("printing from runnable 4");
            }
        });

        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("printing from runnable 5");
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("Pool size = "+((ThreadPoolExecutor)pool).getCorePoolSize());
    }

    private static void startRollerCoaster(){
        // TODO this can be better implemented by blockingqueue
        Car c = new Car("Gazeeboo", 2);

        Passanger p1 = new Passanger("Abhi", c);
        Passanger p2 = new Passanger("Asmita", c);
        Passanger p3 = new Passanger("Archit", c);
        Passanger p4 = new Passanger("Aadu", c);
        Passanger p5 = new Passanger("Udhbav", c);

        new Thread(c).start();
        new Thread(p1).start();
        new Thread(p2).start();
        new Thread(p3).start();
        new Thread(p4).start();
        new Thread(p5).start();
        System.out.println("Main thread ends.");
    }
    private static void startProducerConsumer(){
        BlockingQueue<Integer> q = new SynchronousQueue<>();
        BlockingQueue<String> msgQ = new LinkedBlockingQueue<>();
        LinkedList list = new LinkedList();
        Producer p = new Producer(q, list);
        Consumer c = new Consumer(q, list);


        c.startConsuming();p.startProducing();
        Thread.yield();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list = " + list.get(i));
        }
        System.out.println("Completed Main");

    }
}
