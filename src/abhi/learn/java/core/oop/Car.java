package abhi.learn.java.core.oop;

import java.io.IOException;

public interface Car {
    int wheels = 4;
    int maxSpeed = 120;
    String color = "white";


    public void start();
    void stop();
    void run() throws IOException;
    public String getColor();
}
