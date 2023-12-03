package abhi.learn.java.leetcode.july;

import java.util.List;
import java.util.Stack;

/**
 * Created by Abhishek on 7/1/2020.
 */
public class JulyMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        JulyMain main = new JulyMain();
        Object output = main.arrangeCoins(8);

        System.out.println("Answer="+output);
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }

    /// week-1-july-1st-july-7th/3378/
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
//        Stack

        return  null;
    }


    public int arrangeCoins(int n) {
        return arrangeCoins(n, 1);
    }

    public int arrangeCoins(int n, int row) {
        if (row > n) return row-1;
        else
            return arrangeCoins(n-row, row+1);
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}