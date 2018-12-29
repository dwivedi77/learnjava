package abhi.learn.java.multithreading.rollercoaster;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Abhishek on 7/23/2016.
 */
public class Car implements Runnable{

    private String name;
    private int max;
    private ArrayBlockingQueue<Passanger> queue;
    public Car(String name, int max) {
        this.name = name;
        this.max = max;
        queue = new ArrayBlockingQueue<Passanger>(max, true);
    }

    public void run(){
        int count = 0;
        Passanger[] riders = new Passanger[max];
        while (true){
            while (count < max){
                try {
                    Passanger p = queue.take();
                    riders[count] = p;
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("Taking for ride :");
            for (Passanger p: riders) {
                System.out.print(p.getName()+":");
            }
            System.out.println();
            count = 0;
            getReadyForNextRide();
        }
    }

    private void takeAPause(int mili) {
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getReadyForNextRide() {
        takeAPause(1000);
        System.out.println("Ready for ride");
    }

    public void requestForRide(Passanger p){
        try {
            System.out.println(p.getName()+" has requested a ride");
            queue.put(p);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

