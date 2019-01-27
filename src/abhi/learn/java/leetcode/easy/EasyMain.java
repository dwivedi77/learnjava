package abhi.learn.java.leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by abhi on 1/27/2019.
 */
public class EasyMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();
        int answer = reverse(1534236469);

        System.out.println("Answer="+answer);
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }

    //https://leetcode.com/problems/reverse-integer/
    private static int reverse(int x) {
        long reverse = 0;
        do{
            reverse = reverse*10 + (x%10);
            if (Integer.MIN_VALUE >= reverse || reverse >= Integer.MAX_VALUE)
                return 0;
        }while ((x=x/10) != 0);

        return (int)reverse;
    }

     //Definition for a binary tree node.
     class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> cache = new HashSet<>();

        boolean answer = addToCache(root, cache, k);
        return answer;
    }

    private boolean addToCache(TreeNode node, HashSet<Integer> cache, int k) {
        if (cache.contains(k - node.val)){
            return true;
        }
        cache.add(node.val);
        boolean ans = false;
        if (node.left != null){
            ans = addToCache(node.left, cache, k);
        }
        if (ans)
            return ans;

        if (node.right != null){
            ans = addToCache(node.right, cache, k);
        }
        return ans;
    }

    private static int[] twoSumSorted(int[] numbers, int target) {
        ///hashmap
//        HashMap<Integer, Integer> cache = new HashMap<>();
//        for (int i = 0; i < numbers.length; i++) {
//
//            if (cache.get(target - numbers[i]) != null)
//                return new int[]{1+cache.get(target - numbers[i]), 1+i};
//            else
//                cache.put(numbers[i], i);
//        }

        ////
        int i=0, j=numbers.length-1;
        while (i < j){
            if (numbers[i]+numbers[j] < target){
                i++;
            }else if (numbers[i]+numbers[j] > target){
                j--;
            }else {
                return new int[]{i, j};
            }
        }

        return null;
    }

    private static int[] twoSum(int[] nums, int target) {
        /////brute force
/**        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]+nums[j] == target)
                    return new int[]{i, j};
            }
        }
 **/
        ///Hashmap
        HashMap<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (cache.get(nums[i]) == null)
                cache.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (cache.get(complement) != null && cache.get(complement) != i){
                return new int[]{i, cache.get(complement)};
            }
        }

        return null;
    }


}
