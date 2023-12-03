package abhi.learn.java.multithreading.rollercoaster;

/**
 * Created by Abhishek on 7/23/2016.
 */
public class Passanger implements Runnable {

    private String name;
    private Car car;

    public Passanger(String name, Car car) {
        this.name = name;
        this.car = car;
    }

    //request a ride
    //ride
    //walk
    //request a ride
    @Override
    public void run() {
        while(true){
            car.requestForRide(this);
            walk();
        }
    }

    private void walk(){
        System.out.println(name + " done with ride, going for a walk");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " came back from walk.");
    }

    public String getName() {
        return name;
    }

    public Car getCar() {
        return car;
    }
}
