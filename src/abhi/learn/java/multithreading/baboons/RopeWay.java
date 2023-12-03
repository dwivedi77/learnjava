package abhi.learn.java.multithreading.baboons;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Abhishek on 9/6/2016.
 */
public class RopeWay {

    List<Baboon> riders = new ArrayList<>();
    Lock rideLock = new ReentrantLock();
    Condition east = rideLock.newCondition();
    Condition west = rideLock.newCondition();

    Queue<Baboon> rideEast = new LinkedList<>();
    Queue<Baboon> rideWest = new LinkedList<>();

    Direction current;

    public void run(){
        //wait to be notified
        //take the requesters for the ride
        //Ride logic : take in one direction. keep adding riders of same direction
        // till request for opposite direction is received.
        // then start picking requests for next direction.
        while (true){
            Baboon b = getNextRider();
            if (b == null){
                System.out.println("No one looking to cross the canyon.");
                spendSomeTime();
                continue;
            }else{
                System.out.println("Taking baboon #"+b.getId()+" towards "+b.getDirection().name());
                spendSomeTime();
            }

        }
    }

    private void spendSomeTime() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Baboon getNextRider() {
        if (current.equals(Direction.EAST)){
            if (rideEast.size() > 0)
            return rideEast.poll();
        }else if (current.equals(Direction.WEST)){
            if (rideWest.size() > 0)
                return rideWest.poll();
        }
        return null;
    }

}
