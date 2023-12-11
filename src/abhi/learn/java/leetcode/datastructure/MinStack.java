package abhi.learn.java.leetcode.datastructure;

/**
 * Created by Abhishek on 4/10/2020.
 */
public class MinStack {
    Element top;
    Element min;
    /** initialize your data structure here. */
    public MinStack() {
        // do nothing
    }

    public void push(int x) {
        Element ele = new Element(x);
        if (top == null){
            top = ele;
            min = ele;
        }else{
            ele.next = top;
            top = ele;
            if (min  != null && min.value > ele.value) min = ele;
        }
    }

    public void pop() {
        if (top == null) return;
        Element removed = top;
        top = top.next;
        if (removed == min) min = null;
    }

    public int top() {
        return top != null ? top.value : Integer.MIN_VALUE;
    }

    public int getMin() {
        if (min != null)return min.value;
        else{
            Element temp = top;
            min = temp;
            while (temp != null){
                if (min.value > temp.value)
                    min = temp;
                temp = temp.next;
            }
            return min.value;
        }
    }

    private class Element{
        int value;
        Element(int value){
            this.value = value;
        }
        Element next;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */