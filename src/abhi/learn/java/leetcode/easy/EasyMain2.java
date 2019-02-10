package abhi.learn.java.leetcode.easy;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by abhi on 2/3/2019.
 */
public class EasyMain2 {

    public static void main(String[] args) {

        System.out.println("START");
        long startTime = System.currentTimeMillis();
        ListNode head = createLinkedList(new int[]{1,2,3});
        ListNode reversed = reverseListCopy(head);
        System.out.println("Answer=");
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }


    private static int removeDuplicates(int[] nums) {
        int dupes = 0;

        for (int i = 0; i < nums.length-1; ) {
            int j = i+1;
            while (j<nums.length && nums[i]==nums[j] ){
                dupes++;
                j++;
            }
            nums[i+1] = nums[j];
            i=j;
        }
        return nums.length-dupes;
    }
    //https://leetcode.com/problems/reverse-linked-list/
    private static ListNode reverseListCopy(ListNode head) {
        if (head == null)
            return head;

        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode tempHead = null;
        ListNode dummy = new ListNode(0);
        tempHead = dummy;

        while(head != null) {
            stack.push(head);
            head = head.next;
        }

        while(stack.size() != 0) {
            dummy.next = stack.pop();
            dummy = dummy.next;
        }

        dummy.next = null;
        return tempHead.next;

    }

    //https://leetcode.com/problems/reverse-linked-list/
    private static ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        Stack<ListNode> stack = new Stack<>();
        while (head!= null){
            ListNode temp = null;
            stack.push(head);
            head = head.next;
        }
        ListNode output = null;
        ListNode temp = null;
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            if (i==0){
                output = stack.pop();
                temp = output;
            }else{
                temp.next = stack.pop();
                temp = temp.next;
            }
        }
        return output;
    }

     /**Definition for singly-linked list.**/
     private static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }

         @Override
         public String toString() {
             return val + ", next=" + next ;
         }
     }
    //// https://leetcode.com/problems/merge-two-sorted-lists/
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if(l1.val <= l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
    private static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        ListNode result = new ListNode(0);
        ListNode curr = result;
        while (l1 != null || l2 != null){
            if (l1 == null ){
                curr.val = l2.val;
                curr.next = l2.next;
                break;
            }else if (l2 == null){
                curr.val = l1.val;
                curr.next = l1.next;
                break;
            }
            if (l1.val <= l2.val){
                if (curr == null)
                    curr = new ListNode(l1.val);
                else
                    curr.val = l1.val;
                l1 = l1.next;
            }else{
                if (curr == null)
                    curr = new ListNode(l2.val);
                else
                    curr.val = l2.val;
                l2 = l2.next;
            }
            curr.next = new ListNode(0);
            curr = curr.next;
        }

        return result;
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
        return out;
    }
}
