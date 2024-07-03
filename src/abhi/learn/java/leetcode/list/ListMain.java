package abhi.learn.java.leetcode.list;

import java.util.List;
import java.util.Stack;

public class ListMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();



        Object input = createLinkedList(new int[]{1,2,3,4,5});
        ListMain main = new ListMain();

        Object output = main.reverseList((ListNode)input);


        System.out.println("output = " + output);
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }

    /// https://leetcode.com/problems/reverse-linked-list-ii/description/
    public ListNode reverseBetween(ListNode head, int left, int right) { /// TODO
            if (head == null || head.next == null) return head;
            ListNode prev = null;
            ListNode start = null;
            ListNode end = null;
            ListNode temp = head;
            while (temp != null || temp.val != left){
                prev = temp;
                temp = temp.next;
            }
            start = temp;

            ListNode current = start;
            while (current.val != right){

            }

        return current;
    }

    /// https://leetcode.com/problems/reverse-linked-list/description/
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode temp = null;
        ListNode prev = null;
        ListNode current = head;

        while (current != null){

            temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null) return head;
        Stack<ListNode> stack = new Stack<>();

        ListNode node = head;
        while (node != null){
            ListNode temp = node.next;
            node.next = null;
            stack.push(node);
            node = temp;
        }
        ListNode output = stack.pop();
        node = output;
        while (!stack.empty()){
            node.next = stack.pop();
            node = node.next;
        }
        return output;
    }
    private static ListNode createLinkedList(int[] ary){
        ListNode out = null;
        ListNode curr = null;
        for (int i = 0; i < ary.length; i++) {
            if (out == null){
                out = new ListNode(ary[i]);
                curr = out;
            }else{
                curr.next = new ListNode(ary[i]);
                curr = curr.next;
            }
        }
        curr = null;
        return out;
    }

}


/*
  Definition for singly-linked list.
*/
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
