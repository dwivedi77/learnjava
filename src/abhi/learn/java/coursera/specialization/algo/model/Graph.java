package abhi.learn.java.coursera.specialization.algo.model;

import java.util.*;

/**
 * Created by Abhishek on 8/16/2020.
 */
public class Graph {


    private Map<Vertex, List<Vertex>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
    }

    public void addVertex(int value){
        adjList.putIfAbsent(new Vertex(value), new ArrayList<>());
    }

    public void removeVertex(int value){
        Vertex ver = new Vertex(value);
        adjList.remove(ver);
        Set<Vertex> keys = adjList.keySet();
        for (Vertex key: keys) {
            adjList.get(key).remove(ver);
        }
    }

    public  void addEdge(int val1, int val2){
        Vertex ver1 = new Vertex(val1);
        Vertex ver2 = new Vertex(val2);
        adjList.get(ver1).add(ver2);
        adjList.get(ver2).add(ver1);
    }

    public void removeEdge(int val1, int val2){
        Vertex ver1 = new Vertex(val1);
        Vertex ver2 = new Vertex(val2);

        adjList.get(ver1).remove(ver2);
        adjList.get(ver2).remove(ver1);
    }

    public void collapseVertices(int ver1, int ver2){

    }

    public static void main(String[] args) {

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        Graph graph = new Graph();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(4, 3);
        graph.addEdge(3, 1);
        graph.addEdge(3, 2);

        System.out.println("");
    }


}
