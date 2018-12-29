package abhi.learn.java.ss;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Abhishek on 9/25/2016.
 */
public class RandomizerMain {
    public static void main(String[] args) {
        BlockingQueue<IntegerBox> firstQ = new LinkedBlockingQueue<>();
        BlockingQueue<IntegerBox> secondQ = new LinkedBlockingQueue<>();
        int maxRandomValue = 1000; // random values will be generated between 0 and maxRandomValue.
        Randomizer random = new Randomizer(firstQ, secondQ, maxRandomValue);
        PrimeChecker prime = new PrimeChecker(firstQ, secondQ);
        System.out.println("Starting the Randomizer");
        random.stratRandomizer();
        System.out.println("Starting the Prime");
        prime.startPrimeChecker();

        Scanner scan  = new Scanner(System.in);
        int signal = scan.nextInt();
        while (signal != -1){
            System.out.println("Waiting for -1 to terminate all the threads gracefully");
            signal = scan.nextInt();
        }
        IntegerBox killIt = new IntegerBox(signal);
        try {
            firstQ.put(killIt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Exiting main thread.");
    }
}
