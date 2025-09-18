package abhi.learn.java.leetcode.hard;

import abhi.learn.java.leetcode.datastructure.ListNode;

import java.util.*;

/**
 * Created by Abhishek on 2/4/2022.
 */
public class HardMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        HardMain main = new HardMain();
        int[] input = new int[]{1,-1};
        Object output  = main.shortestPalindrome("abcd");

        System.out.println("Answer="+output);

        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }
    /// https://leetcode.com/problems/the-skyline-problem/
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> output  =new ArrayList<>();
        List<Integer> current = null;
        for (int i = 0; i < buildings.length; i++) {
            int[] bldg = buildings[i];
            if (current == null){
                current = new ArrayList<>();
                current.add(bldg[0]); //x axis
                current.add(bldg[2]); // height
                continue;
            }else
                current = output.get(output.size()-1);
                List<Integer> next = new ArrayList<>();
                if (bldg[1] <= buildings[i-1][1]){ //means overlap
                    if (bldg[2] <= buildings[i-1][2]) //2nd bldg is heigher than first
                        {next.add(bldg[0]);next.add(bldg[2]);}
                }else{

                }


        }
        return output;
    }


    /// https://leetcode.com/problems/merge-k-sorted-lists/description/
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (ListNode node : lists) {
            while (node != null) {
                queue.add(node.val);
                node = node.next;
            }
        }
        int size = queue.size();
        ListNode output = null;
        ListNode curr = null;
        ListNode prev = null;
        for (int i = 0; i < size; i++) {
            curr = new ListNode();
            curr.val = queue.poll();
            if (i == 0) {output = curr; prev = curr; continue;}
            prev.next = curr;
            prev = prev.next;

        }
        return output;
    }


    /// https://leetcode.com/problems/regular-expression-matching/description/
    public boolean isMatch(String s, String p) { /// TODO

        int sIdx = 0;
        int starIdx = -1;
        for (int i = 0; i < p.length(); i++) {
            char pChar = p.charAt(i);
            char sChar = s.charAt(sIdx);
            if (pChar == '*'){
                starIdx = i;
            }else if (pChar == '.'){
                sIdx++;
            }else {
                if (sChar != pChar){

                }
                sIdx++;

            }


        }

        return false;
    }

    public boolean isMatch2(String s, String p) {

        int pIdx = 0;
//        int starIdx = -1;
        boolean isStar = false;
        for (int i = 0; i < s.length(); i++) {
            char pChar = p.charAt(pIdx);
            char sChar = s.charAt(i);
            if (pChar == '*'){
                isStar = true;
            }

        }

        return false;
    }

    public boolean isMatch(String s, int start, int end, char p) {

        return false;
    }



    /// https://leetcode.com/problems/shortest-palindrome/description/
    public String shortestPalindrome(String s) {  /// TODO
        if (s == null || s.length() == 0) return "";
        StringBuilder sb = new StringBuilder("");
        int i = 0; int j = s.length()-1;
        while (i < j){
            if (s.charAt(j) != s.charAt(i)){
                sb.append(s.charAt(j));
            }else{
                i++;
            }
            j--;
        }
        return sb.append(s).toString();
    }


    /// https://leetcode.com/problems/sliding-window-maximum/description/

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] output = new int[nums.length-k+1];
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        int idx = 0;
        output[idx++] = queue.peek();
        for (int i = k; i < nums.length; i++) {
            queue.remove(nums[i-k]);
            queue.offer(nums[i]);
            output[idx++] = queue.peek();
        }
        return output;
    }


    /// https://leetcode.com/problems/count-of-smaller-numbers-after-self/
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>(nums.length);
        int[] values = new int[nums.length];
        if (nums == null || nums.length == 0) return result;

        for (int i = nums.length-2; i >=0 ; i--) {
            int cnt = 0;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] >= nums[i]){
                    values[i] = values[j]+cnt;
                    break;
                }else cnt++;
            }
            if (values[i] < cnt) values[i] = cnt;
        }
        for (int i = 0; i < values.length; i++) {
            result.add(values[i]);
        }
        return result;
    }


    /// https://leetcode.com/problems/palindrome-pairs/
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length == 0) return result;
        boolean isPalin = false;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) continue;
                String first = words[i];
                String second = words[j];
                isPalin = true;
                int x = 0, y = second.length()-1, len = first.length();
                while (x < len+y){
                    char f = (x < len ? first.charAt(x) : second.charAt(x-len));
                    char s = (y >= 0 ? second.charAt(y) : first.charAt(len+y));
                    if ( f!=s) {isPalin = false;break;}
                    x++; y--;
                }
                if (isPalin) result.add(Arrays.asList(i,j));
            }/// j lop
        }/// i loop
        return result;
    }

}
