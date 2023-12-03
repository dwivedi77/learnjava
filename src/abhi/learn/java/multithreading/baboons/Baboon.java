package abhi.learn.java.multithreading.baboons;

/**
 * Created by Abhishek on 9/6/2016.
 */
public class Baboon {
    private int id;
    private Direction direction;


    public void run(){
        //request
        //ride
        //goaway
        //request

    }

    private void request(){

    }
    private void goaway(){
        //spend some random time
        int i = (int) (10000*Math.random());
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
