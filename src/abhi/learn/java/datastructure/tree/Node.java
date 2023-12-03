package abhi.learn.java.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

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
    public Node next;

    public boolean isLeaf(){
        return (left == null) || (right == null);
    }

    public static Node createNode(String input) {
        if (input == null || "".equals(input.trim())) return  null;
        input = input.trim();

        String[] parts = input.split(",");
        String item = parts[0];
        Node root = new Node(Integer.parseInt(item));
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            Node node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new Node(leftNumber);
//                node.left.parent = node;
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new Node(rightNumber);
//                node.right.parent = node;
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

}
