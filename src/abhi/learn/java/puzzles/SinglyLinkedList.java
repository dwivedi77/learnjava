package abhi.learn.java.puzzles;

/**
 * Created by Abhishek on 7/9/2016.
 */
public class SinglyLinkedList {

    public static void main(String[] args) {

        Node n5 = new Node(5, null);
        Node n4 = new Node(4, n5);
        Node n3 = new Node(3, n4);
        Node n2 = new Node(2, n3);
        Node n1 = new Node(1, n2);
        reverse(n1);
        System.out.println(n1);
    }
    public Node removeDupes(Node head){

        return null;
    }

    public static Node reverse(Node currentNode){
        Node previousNode = null;
        Node nextNode;
        while (currentNode != null) {
            nextNode = currentNode.next;
            // reversing the link
            currentNode.next = previousNode;
            // moving currentNode and previousNode by 1 node
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return previousNode;
    }


    private static class Node{
        int data;
        Node next;
        Node(int data, Node next){
            this.data = data;
            this.next = next;
        }

//        @Override
//        public String toString() {
//            return "Node{" +
//                    "data=" + data +
//                    ", next=" + next +
//                    '}';
//        }
    }
}
