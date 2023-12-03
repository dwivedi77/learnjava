package abhi.learn.java.multithreading;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Abhishek on 7/4/2016.
 */
public class Producer {
    private BlockingQueue queue;
    private BlockingQueue msgQ;
    private LinkedList list;

    public Producer(BlockingQueue queue, LinkedList list) {
        this.queue = queue;
        this.list = list;
    }

    public void startProducing(){
        final int[] cnt = {0};
        Random rnd = new Random();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (cnt[0] < 10){
                    int op = rnd.nextInt();
                    try {
                        cnt[0]++;
//                        System.out.println("Produced = " + op);
                        queue.put(op);
                        list.add(op);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
