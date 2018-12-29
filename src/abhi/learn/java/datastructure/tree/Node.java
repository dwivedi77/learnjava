package abhi.learn.java.datastructure.tree;

/**
 * Created by Abhishek on 7/14/2016.
 */
public class Node {
    public Node(int d){
        this.d = d;
    }
    public int d;
    public Node left;
    public Node right;

    public boolean isLeaf(){
        return (left == null) || (right == null);
    }
}
