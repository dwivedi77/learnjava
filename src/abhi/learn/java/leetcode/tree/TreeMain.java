package abhi.learn.java.leetcode.tree;

import abhi.learn.java.datastructure.tree.Node;

import java.util.*;

/**
 * Created by Abhishek on 1/12/2022.
 */
public class TreeMain {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
//        TreeNode parent;
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

    /**
     * Definition for singly-linked list.
     * */
     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }


    public boolean isLeaf(TreeNode node){
        return (node.left == null && node.right == null);
    }

    /**
     *  MAIN Method
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();
        TreeMain main = new TreeMain();

        String input1 = "1,2,3,4";
        TreeNode root = main.createTreeNode(input1);

//        main.morrisInOrderTraversal(root);
//        TreeNode node = main.createTreeNode("4");
        Object output = main.rightSideView(root);
        System.out.println("Answer=" + output);

        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");

    }

    /// https://leetcode.com/problems/binary-tree-right-side-view/
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root.val);

        return result;
    }

    /// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ///finding the mid
        ListNode mid = head;
        ListNode end = head;

        List<Integer> values = new ArrayList<>();
        while (head != null){
            values.add(head.val);
            head = head.next;
        }

        TreeNode root = sortedListToBST(values, 0, values.size()-1);
        return root;
    }

    public TreeNode sortedListToBST(List<Integer> nums, int start, int end){
        if (start >= end) return null;
        int mid = start + (end-start)/2;
        TreeNode node = new TreeNode(nums.get(mid));

        node.left = sortedListToBST(nums, start, mid);
        node.right = sortedListToBST(nums, mid+1, end);
        return node;
    }

    public TreeNode sortedArrayToBSTII(int[] nums, int start, int end){
        if (start >= end) return null;
        int mid = start + (end-start)/2;
        TreeNode node = new TreeNode(nums[mid]);

        node.left = sortedArrayToBSTII(nums, start, mid);
        node.right = sortedArrayToBSTII(nums, mid+1, end);
        return node;
    }

    /// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean reverse = false;
        while (queue.size() > 0){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();;
            while (0 < size--){
                TreeNode node = queue.poll();
                if (reverse)
                    level.add(0,node.val);
                else
                    level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            reverse = !reverse;
            result.add(level);
        }
        return result;
    }


    /// https://leetcode.com/problems/smallest-string-starting-from-leaf/
    public String smallestFromLeaf(TreeNode root) {
        List<String> allPaths = new LinkedList<>();
        smallestFromLeafHelper(root, allPaths, "");
        return allPaths.get(0);
    }

    public void smallestFromLeafHelper(TreeNode node, List<String> allPaths, String parent) {
        if (node == null) return;

        String value = (char)('a'+node.val)+parent;
        if (node.left == null && node.right == null) {
            if (allPaths.size() == 0)
                allPaths.add(value);
            else {
                int comp = allPaths.get(0).compareTo(value);
                if (comp <= 0) return;
                else {
                    allPaths.remove(0);
                    allPaths.add(value);
                }
            }
            return;
        }
        smallestFromLeafHelper(node.left, allPaths, value);
        smallestFromLeafHelper(node.right, allPaths, value);
    }

    public String smallestFromLeafHelperII(TreeNode node) {
        if (node == null) return null;

        String value = (char)('a'+node.val)+"";

        if (node.left == null && node.right == null)
            return value;

        String left = smallestFromLeafHelperII(node.left);
        String right = smallestFromLeafHelperII(node.right);

        if (left == null) return (right == null ? value : right+value);
        if (right == null) return (left == null ? value : left+value);

        left = left+value;
        right = right+value;

        int comp = left.compareTo(right);
        if (comp < 1)
            return left;
        else
            return right;
    }

    /// https://leetcode.com/problems/sum-root-to-leaf-numbers/
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        int[] bigSum = new int[]{0};
        sumNumbersHelper(root, 0, bigSum);
        return bigSum[0];
    }

    public void sumNumbersHelper(TreeNode node, int sum, int[] bigSum) {
        if (node == null) return;
        if (isLeaf(node)){
            bigSum[0] += (sum*10)+node.val;
        }
        sumNumbersHelper(node.left, sum*10+node.val, bigSum);
        sumNumbersHelper(node.right, sum*10+node.val, bigSum);
    }

    ///Morris traversal
    public void morrisInOrderTraversal(TreeNode root) { /// TODO
        if (root == null) return;
        System.out.print("[");
        TreeNode curr = root;

        while (curr != null){
            TreeNode temp = findPredecessor(curr);
            if (temp == null){
                System.out.println(curr.val+",");
                curr = curr.right;
            }
            else {
                if (temp.right == curr){
                    temp.right = null;
                    curr = curr.right;
                }else{
                    System.out.println(curr.val+",");
                    temp.right = curr;
                    curr = curr.left;
                }
            }
        }//// while
    }

    private TreeNode findPredecessor(TreeNode curr) {
        TreeNode temp = curr.left;
        if (temp == null) return null;
        while (temp.right != null && temp.right != curr){
            temp = temp.right;
        }
        return temp;
    }


    ///Morris traversal
    public void morrisInOrderTraversalII(TreeNode root){ /// TODO
        if (root == null) return;
        System.out.print("[");
        TreeNode curr = root;

//        System.out.println("");
    }

    /// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
    public TreeNode sortedListToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int mid = nums.length/2;
        TreeNode node = new TreeNode(nums[mid]);
        sortedArrayToBSTHelper(nums, node, 0, mid);
        sortedArrayToBSTHelper(nums, node, mid+1, nums.length);
        return node;
    }

    public void sortedArrayToBSTHelper(int[] nums, TreeNode parent, int start, int end) {
        if (start >= end) return;
        int mid = start + (end-start)/2;
        TreeNode node = new TreeNode(nums[mid]);
        if (parent != null){
            if (parent.val > nums[mid]) parent.left = node;
            else parent.right = node;
        }

        sortedArrayToBSTHelper(nums, node, start, mid);
        sortedArrayToBSTHelper(nums, node, mid+1, end);

    }


    /// https://leetcode.com/problems/inorder-successor-in-bst/
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) { /// TODO
        if (root == null || p == null) return null;

        if (p.right != null){

        }

        return null;
    }

    public TreeNode inorderSuccessorHelper(TreeNode node, TreeNode p, boolean matched) {
        if (node == null) return null;
        if (matched) return node;
        if (p.val < node.val)
            return inorderSuccessorHelper(node.left, p, matched);
        if (node.val == p.val) {
            matched = true;
            return node.right;
        }
        if (p.val > node.val)
            return inorderSuccessorHelper(node.right, p, matched);

        return null;
    }

//    public TreeNode inorderSuccessorHelper(TreeNode node, TreeNode p, List<TreeNode> order) {
//        if (node == null) return null;
//        order.add(node);
//        if (node.val == p.val) {
//            TreeNode result = (order.size() > 1 ? (TreeNode)order.get(order.size()-2) : null);
//            return result;
//        }
//        TreeNode result = inorderSuccessorHelper(node.left, p, order);
//        if (result != null) return result;
//        result = inorderSuccessorHelper(node.right, p, order);
//
//        return result;
//    }

    /// https://leetcode.com/problems/binary-tree-inorder-traversal/
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;

        while (curr != null){
            stack.add(curr);
            if (curr.left != null){
                curr = curr.left;
            }
        }

        return result;

    }
    public void inorderTraversalHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorderTraversalHelper(node.left, result);
        result.add(node.val);
        inorderTraversalHelper(node.right, result);
    }


    /// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
        verticalTraversalHelper(root, 0, map);
        List<List<Integer>> result = new ArrayList<>();
        for (Integer key: map.keySet()) {
            PriorityQueue q = map.get(key);
            List<Integer> list = new ArrayList<>();
            while (!q.isEmpty())
                list.add((Integer) q.poll());

            result.add(list);
        }
        return result;
    }

    public void verticalTraversalHelper(TreeNode root, int level, Map<Integer, PriorityQueue<Integer>> map) {
        if (root == null) return;
        if (map.get(level) == null)
            map.put(level, new PriorityQueue<Integer>(Integer::compareTo));
        map.get(level).offer(root.val);

        verticalTraversalHelper(root.left, level-1, map);
        verticalTraversalHelper(root.right, level+1, map);
    }



    /// https://leetcode.com/problems/binary-tree-vertical-order-traversal/
    public List<List<Integer>> verticalOrder(TreeNode root) { /// TODO
        Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
        verticalOrderHelper(root, 0, map);
        List<List<Integer>> result = new ArrayList<>(map.values());
        return result;
    }

    public void verticalOrderHelper(TreeNode root, int level, Map<Integer, ArrayList<Integer>> map) {
        if (root == null) return;
        if (map.get(level) == null)
            map.put(level, new ArrayList<Integer>());
        map.get(level).add(root.val);

        verticalOrderHelper(root.left, level-1, map);
        verticalOrderHelper(root.right, level+1, map);
    }

    /// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
    public void flatten(TreeNode root) {
        if (root == null ) return;

        Stack<TreeNode> stack = new Stack<>();
        flattenHelper(root, stack);

        TreeNode parent = stack.pop();
        TreeNode temp = null;
        while (!stack.empty()){
            temp = stack.pop();
            parent.left = null;
            parent.right = temp;
            parent = parent.right;
        }
    }

    public void flattenHelper(TreeNode node, Stack<TreeNode> list) {
        if (node == null) return;
        if (isLeaf(node)) {
            list.add(node);return;
        }
        flattenHelper(node.right, list);
        flattenHelper(node.left, list);
        list.add(node);
    }

    /// https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        Stack<List<Integer>> stack = new Stack<>();

        queue.add(root);
        while (!queue.isEmpty()){
            int level = queue.size();
            List<Integer> lvlList = new ArrayList<>(level);
            while (level-- != 0){
                TreeNode n1 = queue.poll();
                lvlList.add(n1.val);
                if (n1.left != null) queue.add(n1.left);
                if (n1.right != null) queue.add(n1.right);
            }
            stack.push(lvlList);
        }
        while (!stack.empty())
            result.add(stack.pop());

        return result;
    }


    /// https://leetcode.com/problems/binary-tree-level-order-traversal/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> subList = new ArrayList<>();

        while (!queue.isEmpty()){
            int level = queue.size();
            while (level-- != 0){
                TreeNode n1 = queue.poll();
                subList.add(n1.val);
                if (n1.left != null) {
                    queue.add(n1.left);
                }
                if (n1.right != null) {
                    queue.add(n1.right);
                }
            }
            result.add(subList);
            subList = new ArrayList<>();

        }
        return result;
    }


    /// https://leetcode.com/problems/validate-binary-search-tree/
    public boolean isValidBST(TreeNode root) {
        if (root == null || isLeaf(root)) return true;

        if (root.left != null && root.left.val >= root.val) return false;
        if (root.right != null && root.right.val <= root.val) return false;

        return isValidBSTHelper(root.left, null, root) && isValidBSTHelper(root.right, root, null);
    }

    public boolean isValidBSTHelper(TreeNode node, TreeNode min, TreeNode max) {
        if (node == null) return true;
        if (min != null && min.val >= node.val) return false;
        if (max != null && max.val <= node.val) return false;
        return isValidBSTHelper(node.left, min, node) && isValidBSTHelper(node.right, node, max);
    }

    public boolean isValidBSTHelperMid(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val >= max || node.val <= min) return false;
        return isValidBSTHelperMid(node.left, min, node.val) && isValidBSTHelperMid(node.right, node.val, max);
    }

//    public boolean isValidBSTHelperLeft(TreeNode node, int max) {
//        if (node == null) return true;
//        if (node.val >= max) return false;
//        return isValidBSTHelperLeft(node.left, node.val) && isValidBSTHelperMid(node.right, node.val, max);
//    }

//    private boolean isValidBSTHelperRight(TreeNode node, int min) {
//        if (node == null) return true;
//        if (node.val <= min) return false;
//        return isValidBSTHelperMid(node.left, min, node.val) && isValidBSTHelperRight(node.right, node.val);
//    }


    /// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
    public Node connect(Node root) {
        if (root == null) return root;

        if (root.left != null) root.left.next = root.right;
        if (root.next != null){
            if (root.right != null)
                root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);

        return root;
    }

    public Node connectII(Node root) {
        if (root == null || root.left == null || root.right == null) return root;

        List<Node> allNodes = new LinkedList<>();
        allNodes.add(root);
        int idx=0;
        while (idx < allNodes.size()){
            Node current = allNodes.get(idx++);
            if (current == null || (current.left == null & current.right == null)) continue;
            allNodes.add(current.left);
            allNodes.add(current.right);
        }

        int N = 2;idx = 1;
        while (idx < allNodes.size()){
            for (int i = 0; i < N-1; i++) {
                allNodes.get(idx++).next = allNodes.get(idx);
            }
            N *= 2;idx++;
        }
        return root;
    }

    /// https://leetcode.com/problems/merge-two-binary-trees/
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        root1.val = root1.val + root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }


    /// https://leetcode.com/problems/invert-binary-tree/
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


    /// https://leetcode.com/problems/count-good-nodes-in-binary-tree/
    public int goodNodes(TreeNode root) {
        if (root == null) return 0;
        int[] cnt = new int[]{0};
        goodNodesHelper(root, Integer.MIN_VALUE, cnt);
        return cnt[0];
    }

    public void goodNodesHelper(TreeNode node, int max, int[] cnt) {
        if (node == null) return;
        else{
            if (node.val >= max){
                cnt[0]++;
                max = node.val;
            }
            goodNodesHelper(node.left, max, cnt);
            goodNodesHelper(node.right, max, cnt);
        }
    }

    /// https://leetcode.com/problems/path-sum-iii/
    public int pathSumIII(TreeNode root, int targetSum) {
        List<Integer> current = new ArrayList<>();
        int[] count = new int[]{0};
        pathSumIIIHelper(root, targetSum, current,count);
        return count[0];
    }

    public void pathSumIIIHelper(TreeNode node, int targetSum, List<Integer> current, int[] count) {
        if (node == null) return;
        current.add(node.val);
        checkForSum(targetSum, current, count);
        pathSumIIIHelper(node.left, targetSum, current, count);
        pathSumIIIHelper(node.right, targetSum, current, count);
        current.remove(current.size()-1);
    }

    private void checkForSum(int targetSum, List<Integer> current, int[] count) {
        int sum = 0;
        for (int i = current.size()-1; i >= 0; i--) {
            sum += current.get(i);
            if (sum == targetSum) count[0]++;
        }
    }

    //// https://leetcode.com/problems/closest-binary-search-tree-value/
    public int closestValue(TreeNode root, double target) {
        int[] closest = new int[]{root.val};
        closestValue(root, target, closest);
        return closest[0];
    }

    public void closestValue(TreeNode node, double target, int[] closest) {
        if (node == null) return;
        if ( Math.abs(node.val - target) <  Math.abs(target-closest[0]))
            closest[0] = node.val;
        closestValue(node.left, target, closest);
        closestValue(node.right, target, closest);
    }

    ///https://leetcode.com/problems/minimum-depth-of-binary-tree/
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int[] len = new int[]{1};
        int[] minLen = new int[]{Integer.MAX_VALUE};
        minDepthHelper(root, len, minLen);
        return minLen[0];
    }

    public void minDepthHelper(TreeNode node, int[] len, int[] minLen) {
        if (node == null) return;
        if (isLeaf(node)) {
            if (len[0] < minLen[0]) minLen[0] = len[0];
            return;
        }
        else {
            len[0]++;
            minDepthHelper(node.left, len, minLen);
            minDepthHelper(node.right, len, minLen);
            len[0]--;
        }
    }

    /// https://leetcode.com/problems/path-sum-ii/
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> master = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        pathSumIIHelper(root, targetSum, master, current);
        return master;
    }

    public void pathSumIIHelper(TreeNode node, int targetSum, List<List<Integer>> master, List<Integer> current) {
        if (node == null) return;
        if (isLeaf(node)){
            if (node.val == targetSum){
                current.add(node.val);
                master.add(new ArrayList<>(current));
                current.remove(current.size()-1);
            }
            return;
        }else{
            current.add(node.val);
            pathSumIIHelper(node.left, targetSum-node.val, master, current);
            pathSumIIHelper(node.right, targetSum-node.val, master, current);
            current.remove(current.size()-1);
        }
    }


    /// https://leetcode.com/problems/path-sum/
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (isLeaf(root)){
            if (root.val == targetSum) return true;
            else return false;
        }else{
            boolean success = hasPathSum(root.left, targetSum-root.val);
            if (success) return success;
            success = hasPathSum(root.right, targetSum-root.val);
            if (success) return success;

        }
        return false;
    }



    public void breadthFirstSearch(TreeNode node, List<TreeNode> queue){
        if (node == null) return;
        int idx = 0;
        while (idx < queue.size()){
            TreeNode curr = queue.get(idx++);
            if (curr == null || isLeaf(curr)) continue;
            queue.add(curr.left);
            queue.add(curr.right);
        }
    }

    /// https://leetcode.com/problems/symmetric-tree/
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        if (isLeaf(root)) return true;
        TreeNode left = root.left;
        List<TreeNode> leftQ = new ArrayList<>();
        leftQ.add(left);
        int idx = 0;
        while (idx < leftQ.size()){
            TreeNode curr = leftQ.get(idx++);
            if (curr == null || isLeaf(curr)) continue;
            leftQ.add(curr.left);
            leftQ.add(curr.right);
        }

        idx = 0;
        TreeNode right = root.right;
        List<TreeNode> rightQ = new ArrayList<>();
        rightQ.add(right);
        while (idx < rightQ.size()){
            TreeNode curr = rightQ.get(idx++);
            if (curr == null || isLeaf(curr)) continue;
            rightQ.add(curr.right);
            rightQ.add(curr.left);
        }

        if (leftQ.size() != rightQ.size()) return false;
        for (int i = 0; i < leftQ.size(); i++) {
            TreeNode l1 = leftQ.get(i);
            TreeNode r1 = rightQ.get(i);
            if ( (l1 != null && r1 != null) && (l1.val == r1.val))
                continue;
            else if(l1 == null && r1 == null) continue;
            else return false;
        }
        return true;
    }

    /// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
    public TreeNode lowestCommonAncestorIII(TreeNode p, TreeNode q) {
        List<TreeNode> pQ = new ArrayList<>();
        List<TreeNode> qQ = new ArrayList<>();

        while (p != null){
            pQ.add(p);
//            p = p.parent;
        }

        while (q != null){
            qQ.add(q);
//            q = q.parent;
        }

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
//                node.left.parent = node;
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
//                node.right.parent = node;
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

}
