package abhi.learn.java.ss;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Abhishek on 9/24/2016.
 */
public class Randomizer {

    BlockingQueue<IntegerBox> submitQ;
    BlockingQueue<IntegerBox> resultQ;
    Random random = null;
    int maxRandomVal;

    private volatile boolean kill = false;

    public Randomizer(BlockingQueue<IntegerBox> submitQ, BlockingQueue<IntegerBox> resultQ, int maxRandomVal) {
        this.submitQ = submitQ;
        this.resultQ = resultQ;
        random = new Random();
        this.maxRandomVal = maxRandomVal;
    }

    public void stratRandomizer(){
        new Thread(() ->  {
            while (!kill){
                int next = random.nextInt(maxRandomVal);
                System.out.println("Thread RandomGen next = " + next);
                try {
                    submitQ.put(new IntegerBox(next));
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException while printing to the queue");
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread RandomGen received kill signal and will exit now.");
        }, "RandomGen").start();

        System.out.println("RandomGen thread has started sending random numbers");
        new Thread(() -> {
            while (!kill){
                try {
                    IntegerBox box = resultQ.take();
                    if (box.getNumber() == -1) {//kill signal received
                        kill = true;
                        System.out.println("Kill signal received. ResultReader will exit");
                        continue;
                    }
                    if (box.isChecked()){
                        System.out.println("Thread ResultReader:"+box.getNumber()+" is "+ (box.isPrime() ? "" : " not ")+" prime");
                    }else {
                        System.out.println("Thread ResultReader:"+box.getNumber()+" is not checked for prime"); // this should not happen
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ResultReader").start();
        System.out.println("ResultReader thread has started reading the results.");
    }

}
