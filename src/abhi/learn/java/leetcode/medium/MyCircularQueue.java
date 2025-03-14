package abhi.learn.java.leetcode.medium;

import java.util.LinkedList;

public class MyCircularQueue {

    private LinkedList<MyNode> queue = null;
    private int totalSize;
    private int currentSize;

    private MyNode first;
    private MyNode last;

    public MyCircularQueue(int k) {
        queue = new LinkedList<>();
        totalSize = k;
        currentSize = 0;
    }

    public boolean enQueue(int value) {
        if (currentSize >= totalSize) return false;
        MyNode node = new MyNode(value);
        if (currentSize == 0){
            first = node;
            last = node;
        }else{
            MyNode temp = last;
            temp.next = node;
            last = node;
            node.next = first;
        }
        currentSize++;
        return true;
    }

    public boolean deQueue() {
        if (currentSize <= 0) return false;
        MyNode temp = first;
        last.next = first.next;
        first = temp.next;
        currentSize--;
        return true;
    }

    public int Front() {
        if (currentSize == 0) return -1;
        return first.value;
    }

    public int Rear() {
        if (currentSize == 0) return -1;
        return last.value;
    }

    public boolean isEmpty() {
        if (currentSize == 0) return true;
        else return false;
    }

    public boolean isFull() {
        if (currentSize == totalSize) return true;
        else return false;
    }


}
class MyNode {
    int value;
    MyNode next = null;
    MyNode(int val){
        value = val;
    }

    void setNext(MyNode next){
        this.next = next;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */