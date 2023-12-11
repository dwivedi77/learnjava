package abhi.learn.java.leetcode.tree;

/**
 * Created by Abhishek on 4/5/2022.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    //        TreeNode parent;
    public TreeNode() {
    }
    public TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
