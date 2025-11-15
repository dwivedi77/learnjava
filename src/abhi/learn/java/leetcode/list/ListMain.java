package abhi.learn.java.leetcode.list;

import java.util.*;

public class ListMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();



        Object input = createLinkedList(new int[]{1,9,1,2,4});
        Object input2 = createLinkedList(new int[]{3,2,4});
        ListMain main = new ListMain();

        Object output = main.getIntersectionNode((ListNode)input, (ListNode) input2);


        System.out.println("output = " + output);
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }

    ///https://leetcode.com/problems/intersection-of-two-linked-lists/description/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) { /// TODO

        ListNode l1 = headA;
        ListNode l2 = headB;

        while (l1 != l2){
            l1 = l1 == null ? headB : l1.next;
            l2 = l2 == null ? headA : l2.next;
            System.out.println("l1 = " + l1 + ", l2 = " + l2);
        }
        return l1;

    }


    /**
     * go all the way to the end of the nodes
     * start comparing the last one, if matches then return that node.
     * at any level, check that level and the return of the previous ones.
     */
    public ListNode getIntersectionNodeHelper(ListNode l1, ListNode l2){
        if (l1 == null && l2 == null) return null;
        if (l1 != null && l1.next == null && l2 != null && l2.next == null){
            if (l1.val == l2.val) return l1;
            else return null;
        }
        ListNode nextL1 = (l1 == null ? null : (l1.next == null ? l1 : l1.next));
        ListNode nextL2 = (l2 == null ? null : (l2.next == null ? l2 : l2.next));

        ListNode temp  =getIntersectionNodeHelper(nextL1, nextL2);
        if ( temp != null){
            return l1.val == l2.val ? l1 : temp;
        }

        return null;
    }

    /// https://leetcode.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode main = head;
        while (main != null){
            int skip = (main.val == head.val ? 1 : 0);
            while (skip < m){
                skip++;
                if (main == null) return head;
                main = main.next;
            }
            int del = 0;
            while (del < n){
                del++;
                if (main == null) return head;
                ListNode temp = main.next;
                main.next = (temp == null ? null : temp.next);
            }
        }
        return head;
    }

    /// https://leetcode.com/problems/odd-even-linked-list/description/
    public ListNode oddEvenList(ListNode head) { // TODO

        ListNode odd = null;
        ListNode even = null;

        return head;
    }


    /// https://leetcode.com/problems/sort-list/description/
    public ListNode sortList(ListNode head) { /// TODO

        ListNode first = head;
        ListNode current = null;

        while(first != null){
            ListNode second = first.next;
            while (second != null){
                if (second.val < first.val){
                    ListNode tempS = second;
                    first.next = tempS.next;
                    second.next = first;
                    first = second;
                    second = first.next;
                    System.out.println("");
                    // swap
                }else {

                }
            }
            //
            first =
            second = second.next;
        }


        return head;
    }

    /// https://leetcode.com/problems/partition-list/description/
    public ListNode partition(ListNode head, int x) { // TODO

        ListNode current = head;
        while (current.val != x)
            current = current.next;

        ListNode temp = null;
        while (current != null){
            temp = current.next;
        }
        return head;
    }

    /// https://leetcode.com/problems/palindrome-linked-list/description/
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null){
            stack.push(temp.val);
            temp = temp.next;
        }
        while (head != null){
            if ( head.val != stack.pop())
                return false;

            head = head.next;
        }
        return true;
    }


    /// https://leetcode.com/problems/linked-list-cycle/description/
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        Set<ListNode> visited = new HashSet<>();
        ListNode next = head;
        while (next != null){
            if(!visited.add(next))
                return true;

            next = next.next;
        }
        return false;
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

    @Override
    public String toString() {
        return "{" +
                "val=" + val +
                '}';
    }
}
