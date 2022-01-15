package abhi.learn.java.leetcode.tree;

import java.util.*;

/**
 * Created by Abhishek on 1/12/2022.
 */
public class TreeMain {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();
        TreeMain main = new TreeMain();

        String input = "3,5,1,6,2,0,8,null,null,7,4";
        TreeNode root = main.createTreeNode(input);
        TreeNode p = main.createTreeNode("4");
        TreeNode q = main.createTreeNode("6");

        Object output = main.lowestCommonAncestor(root, p, q);

        System.out.println("Answer=" + output);

        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");

    }

    public boolean isLeaf(TreeNode node){
        return (node.left == null && node.right == null);
    }

    /// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        List<TreeNode> pQ = new ArrayList<>();
        List<TreeNode> qQ = new ArrayList<>();

        lowestCommonAncestorHelper(root, p, pQ);
        lowestCommonAncestorHelper(root, q, qQ);

        TreeNode result = null;
        for (int i = pQ.size()-1, j=qQ.size()-1; i >= 0 && j >= 0; i--, j--) {
            TreeNode n1 = pQ.get(i);
            TreeNode n2 = qQ.get(j);

            if(n1.val == n2.val)
                result = n1;
            else {
                break;
            }
        }
        return result ;

    }

    public boolean lowestCommonAncestorHelper(TreeNode node, TreeNode p, List<TreeNode> ancestorsP) {
        if (node == null) return false;
        if (node.val == p.val) {
            ancestorsP.add(p);
            return true;
        }else{
            boolean success = lowestCommonAncestorHelper(node.left, p, ancestorsP);
            if (success){
                ancestorsP.add(node); return success;
            }
            success = lowestCommonAncestorHelper(node.right, p, ancestorsP);
            if (success){
                ancestorsP.add(node); return success;
            }
        }
        return false;

    }

    /// https://leetcode.com/problems/balanced-binary-tree/
    public boolean isBalanced(TreeNode root) { // TODO improve performance
        if (root == null || isLeaf(root)) return true;

        boolean leftB = isBalanced(root.left);
        boolean rightB = isBalanced(root.right);
        if (leftB && rightB){
            int leftH = maxDepth(root.left);
            int rightH = maxDepth(root.right);
            return  (Math.abs(leftH - rightH) <=1);
        }else return false;
    }

    /// https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public int maxDepth(TreeNode node) {
        if (node == null) return 0;
        if (isLeaf(node)) return 1;
        return 1+ Math.max( maxDepth(node.left), maxDepth(node.right));

    }

    private TreeNode createTreeNode(String input) {
        if (input == null || "".equals(input.trim())) return  null;
        input = input.trim();
//        input = input.substring(1, input.length() - 1);
//        if (input.length() == 0) {
//            return null;
//        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

}
