package abhi.learn.java.leetcode.medium;

import java.util.*;

/**
 * Created by Abhishek on 3/14/2019.
 */
public class MediumMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        ListNode head = createLinkedList(new int[]{2,1,5});
        int[] out = nextLargerNodes(head);
        System.out.println("Answer="+out);
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");


    }

    ///https://leetcode.com/problems/next-greater-node-in-linked-list/
    private static int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode first = head;
        ListNode next = first.next;
        if (next == null){
            return new int[]{0};
        }
        while (first != null){
            boolean added = false;
            next = first.next;
            while (next != null){
                if (first.val < next.val){
                    list.add(next.val);
                    added = true;
                    break;
                }else{
                    next = next.next;
                }
            }
            if (!added) list.add(0);
            first = first.next;

        }
        int[] output = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            output[i] = list.get(i);
        }
        return output;
    }

    ///https://leetcode.com/problems/find-all-duplicates-in-an-array/
    private static List<Integer> findDuplicates(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null){
                map.put(nums[i], 1);
            }else{
                map.put(nums[i], map.get(nums[i])+1);
            }
        }
        List<Integer> out = new ArrayList<>();
        for (Integer key: map.keySet()) {
            if (map.get(key) > 1){
                out.add(key);
            }
        }
        return out;
    }

    ///https://leetcode.com/problems/reveal-cards-in-increasing-order/
    public static int[] deckRevealedIncreasing(int[] deck) {
        int[] out = new int[deck.length];
        for (int i = 0; i < deck.length; i++) {
            out[i]=-1;
        }

        Arrays.sort(deck);
        int idx = -1;
        for (int i = 0; i < deck.length; i++) {
            if (i==0){
                out[i]=deck[i];
                idx=0;
                continue;
            }
        int nextIdx = findNextIdx(out, idx);
            out[nextIdx]=deck[i];
            idx=nextIdx;
        }

        return out;
    }

    private static int findNextIdx(int[] out, int idx) {
        boolean skipped = false;
        while (true){
            if (idx==out.length-1){
                idx=0;
            }else {
                idx++;
            }
            if (out[idx]==-1 && !skipped){
                skipped=true;
                continue;
            }else if (out[idx]==-1){
                return idx;
            }
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
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
