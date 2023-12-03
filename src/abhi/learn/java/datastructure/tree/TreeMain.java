package abhi.learn.java.datastructure.tree;

import java.util.Arrays;

/**
 * Created by Abhishek on 7/14/2016.
 */
public class TreeMain {

    public static void main(String[] args) {
        Node n = constructBST();
        printPaths(n);
        printInOrder(n);
        System.out.println("---------------------------");
        System.out.println(printNodesInLevel(n));

    }

    private static String printNodesInLevel(Node n){
        if (n == null) return "";
        StringBuilder b = new StringBuilder(n.d);
        b.append(printNodesInLevel(n.left));
        b.append(printNodesInLevel(n.right));
        return b.toString();
    }

    private static Node treeFromSortedArray(int[] arr){
        if (arr.length == 1) return new Node(arr[0]);
        int pointer = arr.length/2;
        Node n = new Node(arr[pointer]);
        n.left = treeFromSortedArray(Arrays.copyOfRange(arr, 0, pointer));
        if (arr.length > 2)
        n.right = treeFromSortedArray(Arrays.copyOfRange(arr, pointer+1, arr.length));

        return n;
    }

    private static void printInOrder(Node n){
        if (n == null){
            return;
        }
        printInOrder(n.left);
        System.out.print("-"+n.d+"-");
        printInOrder(n.right);
    }

    private static boolean hasPathSum(Node n, int sum){
        if (n == null) return false;
        int d = n.d;
        if (n.isLeaf()) return d==sum;
        return hasPathSum(n.left, sum-d) || hasPathSum(n.right, sum-d);
    }


    private static void printPaths(Node n){
        if (n == null) return;
        printPaths(n, "-");
    }

    private static void printPaths(Node n, String prev) {
        if (n == null) return;
        if (n.isLeaf()){
            System.out.println(prev+"-"+n.d);
        }
        printPaths(n.left, prev+"-"+n.d);
        printPaths(n.right, prev+"-"+n.d);
    }

    /**
     *   Below is the tree structure it produces
     *            1
     *        3        5
     *     8    2   10   12
     *
     * @return
     */
    private static Node constructBST(){
        Node n1 = new Node(1);
        Node n2 = new Node(3);
        Node n3 = new Node(5);
        Node n4 = new Node(8);
        Node n5 = new Node(2);
        Node n6 = new Node(10);
        Node n7 = new Node(12);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        return n1;
    }
}
