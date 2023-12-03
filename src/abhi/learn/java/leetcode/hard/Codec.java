package abhi.learn.java.leetcode.hard;

import abhi.learn.java.leetcode.tree.TreeMain;
import abhi.learn.java.leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by Abhishek on 4/1/2022.
 */
public class Codec {

    public static void main(String[] args) {
        String test = "1,2,3,null,null,4,5";
        TreeMain tm = new TreeMain();
        Codec codec = new Codec();
        TreeNode node = codec.deserialize("");
        String output = codec.serialize(node);

        System.out.println("Test="+test);
        System.out.println("Output="+output);
        System.out.println("Passed="+test.equals(output));
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null)
            return sb.toString();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (0 < size--){
                TreeNode node = queue.poll();
                if (node == null){
                    sb.append("null");sb.append(',');
                    continue;
                }else{
                    sb.append(node.val);sb.append(',');
                }

                if (node.left != null){
                    queue.add(node.left);
                }else queue.add(null);
                if (node.right != null){
                    queue.add(node.right);
                }else queue.add(null);
            }
        }
        while ("null,".equals(sb.substring(sb.length()-6, sb.length()-1))){
            sb.delete(sb.length()-6, sb.length()-1);
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        StringTokenizer tokenizer = new StringTokenizer(data, ",");
        if (!tokenizer.hasMoreTokens()) return null;
        Integer rootVal = Integer.parseInt(tokenizer.nextToken());
        TreeNode root = new TreeNode(rootVal);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (0 < size--){
                TreeNode parent = queue.poll();

                if (tokenizer.hasMoreTokens()){
                    String left = tokenizer.nextToken();
                    if (left != null && !"null".equals(left)){
                        parent.left = createTreeNode(left);
                        queue.add(parent.left);
                    }
                }

                if (tokenizer.hasMoreTokens()){
                    String right = tokenizer.nextToken();
                    if (right != null && !"null".equals(right)){
                        parent.right = createTreeNode(right);
                        queue.add(parent.right);
                    }
                }
            }
        }
        return root;
    }

    private TreeNode createTreeNode(String val){
        Integer rootVal = Integer.parseInt(val);
        TreeNode root = new TreeNode(rootVal);
        return root;
    }

}
