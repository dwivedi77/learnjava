package abhi.learn.java.multithreading;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Abhishek on 7/4/2016.
 */
public class Consumer<E> {
    private BlockingQueue<E> queue;
    private LinkedList list;

    public Consumer(BlockingQueue queue, LinkedList list) {
        this.queue = queue;
        this.list = list;
    }


    public void startConsuming(){
        final int[] cnt = {0};
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (cnt[0] < 10){
                    E e = null;
                    try {
                        e = queue.take();
                        list.add(e);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
//                    System.out.println("Consumed = " + e);
                    cnt[0]++;
                }
            }
        }).start();
    }

}
