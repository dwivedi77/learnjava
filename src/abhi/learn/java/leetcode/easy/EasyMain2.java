package abhi.learn.java.leetcode.easy;

import java.util.*;

/**
 * Created by abhi on 2/3/2019.
 */
public class EasyMain2 {

    public static void main(String[] args) {

        System.out.println("START");
        long startTime = System.currentTimeMillis();
//        int output = printRemainder(-99, -99);
        boolean output = buddyStrings("aa", "aa");
        System.out.println("Answer="+output);
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }

    ////https://leetcode.com/problems/buddy-strings/
    private static boolean buddyStrings(String A, String B) {
        int count = 0;
        if (A == null || B == null){
            return false;
        }else if (A.length() != B.length())
            return false;
        int first = -1;

        for (int i = 0; i < A.length(); i++) {
            char a = A.charAt(i);
            char b = B.charAt(i);
            if (a != b){
                if (first != -1 && i != first && a == B.charAt(first) && A.charAt(first) == b){
                    count++;
                }else {
                    first = i;
                }
            }
        }
        if (count==1)
            return true;
        else
            return false;

    }

    private static int printRemainder(int x, int y) {
        return (x%y);
    }
    ///3,4,2,3   1,5,4,6,7,10,8,9    -1,4,2,3
    ///https://leetcode.com/problems/non-decreasing-array/
    private static boolean checkPossibility(int[] nums) {
        if (nums ==null || nums.length <=1)
            return true;

        int failCount = 0;
        for (int i = 0; i < nums.length-1; i++) {
            int j = i+1;
            if (nums[i]>nums[j]){
//                if (i==0)
//                    nums[i]=nums[j];
//                else
                    nums[j]=nums[i];
                failCount++;
            }
            if (failCount>1)
                return false;
        }

        return true;
    }

    //https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    private static int removeDuplicates3(int[] nums) {
        if (nums == null) return 0;
        int prev = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[prev] != nums[i]){
                nums[++prev] = nums[i];
            }
        }
        return prev+1;
    }

    //https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    private static int removeDuplicates2(int[] nums) {
        if (nums == null)
            return 0;

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null){
                map.put(nums[i], i);
            }
        }
        int idx = 0;

        for (Integer key: map.keySet()) {
            nums[idx++]= key;
        }
        return map.size();
    }

    //https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    private static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length==1) return nums.length;

        int dupes = 0;
        int[] output = new int[nums.length];
        int idx = 0;
        output[idx] = nums[idx];///copy the 1st element to start with
        idx++;
        for (int i = 0, j=1; j < nums.length; ) {
            if (nums[i] == nums[j]){
                j++;
                dupes++;
                continue;
            }else {
                output[idx++] = nums[j];
                i = j++;
            }
        }
        if (dupes==0) return nums.length;
        for (int i = 0; i <= nums.length-dupes; i++) {
            nums[i] = output[i];
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
    private static ListNode next = null;
    private static ListNode prev = null;
    private static ListNode reverseList(ListNode head) {
        if (head == null)
            return prev;

        next = head.next;
        prev = head;
        head.next = prev;
        head = next;
        return reverseList(next);
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
