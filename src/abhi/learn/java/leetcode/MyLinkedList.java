package abhi.learn.java.leetcode;

/**
 * Created by Abhishek on 9/8/2019.
 */
class MyLinkedList {

    Integer val;
    MyLinkedList next;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {

    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0) return -1;
        int cnt = 0;
        MyLinkedList temp = this;
        while (cnt < index){
            cnt++;
            temp = temp.next;
            if (temp == null) return -1;
        }
        return temp.val == null ? -1 : temp.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    //if this is an empty list
    public void addAtHead(int val) {
        if (this.val == null){
            this.val = val;
            return;
        }
        //else if this is not an empty list
        Integer temp = this.val;

        MyLinkedList current = new MyLinkedList();
        current.val = temp;
        current.next = this.next;

        this.val = val;
        this.next = current;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        MyLinkedList tail = new MyLinkedList();
        tail.val = val;
        if (next == null){
            next = tail;
            return;
        }
        MyLinkedList temp = next;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = tail;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (this.val == null && index > 0) return;

        MyLinkedList element = new MyLinkedList();
        element.val = val;
        int cnt = 0;
        MyLinkedList temp = this;
        while (cnt < index-1){
            cnt++;
            temp = temp.next;
            if(temp == null) return;
        }
        element.next = temp.next;
        temp.next = element;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        int cnt = 0;
        MyLinkedList prev = this;
        MyLinkedList temp = this;
        while (cnt < index){
            cnt++;
            prev = temp;
            temp = temp.next;
            if(temp == null) return;
        }
        if (temp.next != null){
            temp.val = temp.next.val;
            temp.next = temp.next.next;
        }else {
            prev.next = null;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */