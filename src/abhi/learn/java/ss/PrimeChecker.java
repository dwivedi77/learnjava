package abhi.learn.java.ss;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Abhishek on 9/24/2016.
 */
public class PrimeChecker{
    BlockingQueue<IntegerBox> inQueue;
    BlockingQueue<IntegerBox> outQueue;
    private boolean kill =  false;
    public PrimeChecker(BlockingQueue<IntegerBox> inQueue, BlockingQueue<IntegerBox> outQueue) {
        this.inQueue = inQueue;
        this.outQueue = outQueue;
    }

    public void startPrimeChecker(){
        new Thread(() -> {
            while (!kill){
                try {
                    IntegerBox box = inQueue.take();
                    int next = box.getNumber();
                    System.out.println("Thread PrimeChecker:"+"Received="+next);
                    if (next == -1){
                        kill = true;
                        System.out.println("Kill signal received. PrimeChecker will exit");
                    }
                    boolean isPrime = isPrime(next);
                    box.setChecked(true);
                    box.setPrime(isPrime);
                    System.out.println("Thread PrimeChecker:"+next + ":Prime="+isPrime+". Posting the result to result Q");
                    outQueue.put(box);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
            System.out.println("PrimeChecker exits now.");
        },"PrimeChecker").start();
        System.out.println("PrimeChecker has started.");
    }

    private boolean isPrime(int test){
        if (test <= 0) return false;
        for (int i = 2; i < test; i++) {
            if (test % i == 0) return false;
        }
        return true;
    }
}
