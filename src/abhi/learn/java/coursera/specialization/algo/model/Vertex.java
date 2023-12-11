package abhi.learn.java.coursera.specialization.algo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Node of a graph
 * Created by Abhishek on 8/2/2020.
 */
public class Vertex {

    public Vertex(int value){
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return value == vertex.value;

    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "Vertex{"+ value +'}';
    }
}
