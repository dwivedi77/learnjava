package abhi.learn.java.leetcode.medium;


import abhi.learn.java.leetcode.datastructure.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Abhishek on 3/14/2019.
 */
public class MediumMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        int[][] input = new int[][]{{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
        Object output  = merge(input);

        System.out.println("Answer="+output);

        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }

    ///


    /// https://leetcode.com/problems/merge-intervals/
    private static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        List<int[]> mergedList = new ArrayList<>(intervals.length);

        for (int i = 1; i < intervals.length; i++) {
            int[] first = intervals[i-1];
            int[] second = intervals[i];
            if ( (first[0] <= second[0] && first[1] >= second[0]) ){ // merge
                int[] merged = new int[]{(first[0] <= second[0] ? first[0] : second[0]), (first[1] >= second[1] ? first[1] : second[1])  };
                intervals[i] = merged;
            }else{
                mergedList.add(first);
            }
            if (i== intervals.length-1) mergedList.add(intervals[i]);
        }

        int[][] output = new int[mergedList.size()][];
        for (int i = 0; i < mergedList.size(); i++) {
            output[i] = mergedList.get(i);
        }
        return output;
    }

    private static int[][] mergeII(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        List<int[]> mergedList = new ArrayList<>();

        for (int i = 1; i < intervals.length; i++) {
            int[] first = intervals[i-1];
            int[] second = intervals[i];
            if ( (first[0] <= second[0] && first[1] >= second[0]) || (second[0] <= first[0] && second[1] >= first[0] ) ){ // merge
                int[] merged = new int[]{(first[0] <= second[0] ? first[0] : second[0]), (first[1] >= second[1] ? first[1] : second[1])  };
                intervals[i] = merged;
            }else{
                mergedList.add(first);
            }
            if (i== intervals.length-1) mergedList.add(intervals[i]);
        }

        int[][] output = new int[mergedList.size()][];
        for (int i = 0; i < mergedList.size(); i++) {
            output[i] = mergedList.get(i);
        }
        return output;
    }


    /// https://leetcode.com/problems/rotate-array/
    public static void rotate(int[] nums, int k) { /// TODO
        if (nums == null || nums.length == 0) return;
        int intermediate = nums[0];
        int idx = 0;
        int cnt = 0;
        int N = nums.length;
        while (cnt <= N){
            int newIdx = (idx+k+N)%N;
            int temp = nums[newIdx];
            nums[newIdx] = intermediate;
            intermediate = temp;

            idx = newIdx;
            cnt++;
        }
    }


    /// https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/
    private static int numberOfWeakCharacters(int[][] properties) {
        if (properties == null || properties.length <= 1) return 0;
        Set<Integer> count = new HashSet<>();
        int i = 0; int j = properties.length-1;
        while (i<j){
            if ( properties[i][0] < properties[j][0] && properties[i][1] < properties[j][1]){
                count.add(i);i++;
            }else if ((properties[i][0] > properties[j][0] && properties[i][1] > properties[j][1])){
                count.add(j);j--;
            }
        }

        return count.size();
    }

    /// https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/
    public static boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length <= 1) return false;

        Map<Integer, Integer> groups = new HashMap<>();
        for (int card : deck) {
            if (groups.get(card) == null){
                groups.put(card, 1);
            }else{
                groups.put(card, 1+groups.get(card));
            }
        }

        int smallest = Integer.MAX_VALUE;
        for (int value: groups.values()) {
            if (value < smallest && value > 1)
                smallest = value;
        }

        /*
        2,3,4....smallest
         */
        for (int i = 2; i <= smallest; i++) {
            boolean success = true;
            for (int value: groups.values()) {
                if (value % i != 0) success = false;
            }
            if (success) return true;
        }

        return false;
    }


    /// https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/
    private static boolean canBeIncreasing(int[] nums) { //// TODO
        if (nums == null || nums.length==0) return false;
        if (nums.length == 1) return true;

        int faults = 0;
        int secondB = Integer.MIN_VALUE;
        int biggest = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            if (current > biggest) {
                secondB = biggest;
                biggest = current;
                continue;
            }else{
                faults++;
                if (faults > 1) return false;
                if (current <= secondB){
                    continue;
                }else{
                    biggest = current;
                }

            }
        }
        return true;
    }


    /// https://leetcode.com/problems/sorting-the-sentence/
    private static String sortSentence(String s) {
        if (s == null || s.length() == 0) return s;
        StringTokenizer st = new StringTokenizer(s, " ");
        String[] results = new String[st.countTokens()];
        while (st.hasMoreTokens()){
            String token = st.nextToken();
            int i = token.length()-1;
            while (token.charAt(i) >= '0' && token.charAt(i) <= '9'){
                i--;
            }
            int idx = Integer.parseInt(token.substring(i+1)) -1;
            results[idx] = token.substring(0, i+1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < results.length; i++) {
            sb.append(results[i]);
            if (i < results.length-1){
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    /// https://leetcode.com/problems/check-if-numbers-are-ascending-in-a-sentence/
    public static boolean areNumbersAscending(String s) {
        if (s == null || s.length() == 0) return false;
        StringTokenizer toekns = new StringTokenizer(s, " ");
        int lastInt = Integer.MIN_VALUE;
        while (toekns.hasMoreTokens()){
            String token = toekns.nextToken();
            if (isInteger(token)){
                int num = Integer.parseInt(token);
                if (num > lastInt){
                    lastInt = num;
                }else return false;
            }
        }
        return true;
    }

    private static boolean isInteger(String token) {
        try {
            Integer.parseInt(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    /// https://leetcode.com/problems/number-of-valid-words-in-a-sentence/
    private static int countValidWords(String sentence) {
        if (sentence == null || sentence.length() == 0) return 0;
        StringTokenizer toekns = new StringTokenizer(sentence, " ");
        int count = 0;
        while (toekns.hasMoreTokens()){
            String token = toekns.nextToken();
                if (isValidWord(token)) count++;
        }
        return count;
    }

    private static boolean isValidWord(String token) {
        int hyphenCnt = 0;
        int tokenCnt = 0;
        for (int i = 0; i < token.length(); i++) {
            char x = token.charAt(i);

            if ( (x == '!' || x == ',' || x == '.' )){
                if ( (token.length() != 1 && i < token.length()-1) || tokenCnt > 0)return false;
                else {tokenCnt++;continue;}
            }
            if (x == '-'){
                if ( i == 0 || i == token.length()-1 || hyphenCnt > 0 ) return false;
                if( !(isLowerCaseLetter(token.charAt(i-1)) && isLowerCaseLetter(token.charAt(i+1))) ) return false;
                else {hyphenCnt++;continue;}
            }
            if ( !isLowerCaseLetter(x) )return false;
        }
        return true;
    }

    private static boolean isLowerCaseLetter(char x){
        if ( !(x >= 97 && x <=122) )return false;
        else return true;
    }

    private static int[] mergeIntervals(int[] first, int[] second) {
        int start = first[0] <= second[0] ? first[0] : second[0];
        int end = first[1] < second[1] ? second[1] : first[1];
        return new int[]{start, end};
    }

    private static boolean doIntervalsOverlap(int[] first, int[] second) {
        if (first[0] >= second[0] && first[0] <= second[1]) return true;
        if (first[1] >= second[0] && first[1] <= second[1]) return true;
        if (second[0] >= first[0] && second[0] <= first[1]) return true;
        if (second[1] >= first[0] && second[1] <= first[1]) return true;
        return false;
    }

    public static int[] buildArray(int[] nums) {
        buildArray(nums, 0);
        return nums;
    }

    public static int buildArray(int[] nums, int idx) {
        if (idx == nums.length-1) {
            int x = nums[idx];
            nums[idx] = nums[nums[idx]];
            return x;
        }
        else{
            return buildArray(nums, idx+1);
        }
    }


    ///https://leetcode.com/problems/decode-string/
    public static String decodeString(String input) {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < input.length(); i++) {
            char x = input.charAt(i);
            if(isDigit(x)){

            }else{
//                int loop = Integer.parseInt(x);
            }

        }
        return sb.toString();
    }

    private static boolean isDigit(char x) {
        return true;
    }


    ///https://leetcode.com/problems/coin-change-2/
    public static int change(int amount, int[] coins) {
        if (amount < 0) return 0;
        if (amount == 0) return 1;
        if (coins == null || coins.length == 0) return 0;

        int[][] dp = new int[coins.length+1][amount+1];
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i-1] > j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                }
            }
        }
        return dp[coins.length][amount];
    }

    public static int change2(int amount, int[] coins) {
        if (amount < 0) return 0;
        if (amount == 0) return 1;
        if (coins == null || coins.length == 0) return 0;

        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[j] = dp[j] + (j >= coins[i] ? dp[j-coins[i]] : 0);
            }
        }
        return dp[amount];
    }

    ///https://leetcode.com/problems/coin-change/
    private  static int coinChange(int[] coins, int amount) {
        if (amount==0)return 0;
        if (amount<0 || coins==null || coins.length==0) return -1;
        int[] count = new int[amount];

        int result = coinChangeHelper(coins, amount, count);
        return result;
    }

    private static int coinChangeHelper(int[] coins, int rem, int[] count) {
        if (rem == 0) return 0;
        if (rem < 0) return -1;
        if (count[rem-1] != 0) return count[rem-1];

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= coins.length-1 ; i++) {
            int res = coinChangeHelper(coins, rem-coins[i], count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem-1] = (min == Integer.MAX_VALUE ? -1 : min);
        return count[rem-1];
    }

    public static int coinChange_arun(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Map<Integer, Integer> counts = new HashMap<>();
        for (int coin : coins) {
            counts.put(coin, 1);
        }
        for (int amt = 1; amt <= amount; amt++) {
            for (int coin : coins) {
                if (amt >= coin) {
                    Integer currValue = counts.get(amt);
                    Integer newVal = null;
                    if (counts.containsKey(amt - coin)) {
                        newVal = 1 + counts.get(amt - coin);
                        if (currValue == null) {
                            counts.put(amt, newVal);
                        } else {
                            counts.put(amt, Integer.min(currValue, newVal));
                        }
                    }////if (counts.containsKey(i - coin))
                }///if (i >= coin)
            }///for
        }///for
        if (counts.containsKey(amount)) {
            return counts.get(amount);
        } else {
            return -1;
        }
    }

    /// week 2 day 13
    private static String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0 || num.length() == k) return "0";
        int output = Integer.MAX_VALUE;
        int deleted = 0;
        for (int i = 0; i < k; i++) {
            System.out.println("");
            for (int j = deleted; j < num.length(); j++) {
                int zeros = 0;
                while (j == 0 && zeros+1 < num.length() && num.charAt(zeros+1) == '0'){
                    zeros++;
                }
                if (zeros > 0){
                    num = num.substring(j+zeros+1);
                    deleted = j+zeros;
                    break;
                }else{
                    char x = num.charAt(j);
                    char next = (j+1 < num.length()) ? num.charAt(j+1) : '0'-1;
                    if (x > next){
                        num = num.substring(0,j)+num.substring(j+1);
                        deleted = j;
                        break;
                    }
                }
            }////j
        }////i
        return (num == null || num.length() == 0) ? "0" : num;
    }

    /// week 2 day 12
    /// https://leetcode.com/problems/single-element-in-a-sorted-array/
    private static int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum ^= nums[i];
        }
        return sum;
    }

    /// week 2 day 8
    private static boolean checkStraightLine(int[][] coordinates) {
        if (coordinates == null || coordinates.length == 0) return false;
        if (coordinates.length <= 2) return true;
        float slope = ((float)(coordinates[1][1]-coordinates[0][1]))/(coordinates[1][0]-coordinates[0][0]);
        for (int i = 1; i < coordinates.length-1; i++) {
            int[] curr = coordinates[i];
            int[] next = coordinates[i+1];
            float nextSlope = (next[1]-curr[1])/(next[0]-curr[0]);
            if (nextSlope != slope) return false;
        }
        return true;
    }

    /// week 2 day 11
    private static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] visited = new boolean[image.length][image[0].length];
        floodFillHelper(image, sr, sc, newColor, image[sr][sc], visited);
        return image;
    }
    private static void floodFillHelper(int[][] image, int sr, int sc, int newColor, int orig, boolean[][] visited) {
        if (sr <0 || sr >= image.length || sc < 0 || sc >= image[0].length) return;
        if (visited[sr][sc]) return;
        else if (image[sr][sc] != orig){
            visited[sr][sc] = true;
            return;
        }else {
            image[sr][sc] = newColor;
            visited[sr][sc] = true;
            floodFillHelper(image, sr+1, sc, newColor, orig, visited);
            floodFillHelper(image, sr, sc+1, newColor, orig, visited);
            floodFillHelper(image, sr, sc-1, newColor, orig, visited);
            floodFillHelper(image, sr-1, sc, newColor, orig, visited);
        }
    }

    private static int findJudge2(int N, int[][] trust) {
        if (N == 1) return 1;
        HashMap<Integer, Integer> trustMap = new HashMap<>();
        HashSet<Integer> commoners = new HashSet<>();

        for (int i = 0; i < trust.length; i++) {
            commoners.add(trust[i][0]);
            if (trustMap.containsKey(trust[i][1])){
                trustMap.put(trust[i][1], 1+trustMap.get(trust[i][1]));
            }else {
                trustMap.put(trust[i][1], 1);
            }
        }
        int judge = -1;
        for (Integer possibleJudge: trustMap.keySet()) {
            if (commoners.contains(possibleJudge)) continue;
            if (trustMap.get(possibleJudge) == N-1){
                if (judge == -1)
                    judge = possibleJudge;
                else return -1;
            }
        }
        return judge;
    }

    private static int findJudge(int N, int[][] trust) {
        HashSet<Integer> all = new HashSet<>();
        HashSet<Integer> trustees = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            all.add(i);
        }
        for (int i = 0; i < trust.length; i++) {
            all.remove(trust[i][0]);
        }
        if (all.size() != 1) return -1;
        int possibleJudge = all.iterator().next();
        for (int i = 0; i < trust.length; i++) {
            if(trust[i][1] == possibleJudge){
                trustees.add(trust[i][0]);
            }
        }
        if (trustees.size() == N-1) return possibleJudge;
        else return -1;
    }

    private static boolean isCousins_Aruns(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        if (root.val == x || root.val == y) {
            return false;
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode xNode = null;
            TreeNode yNode = null;
            TreeNode xNodeParent = null;
            TreeNode yNodeParent = null;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    if (node.left.val == x) {
                        xNode = node.left;
                        xNodeParent = node;
                    }
                    if (node.left.val == y) {
                        yNode = node.left;
                        yNodeParent = node;
                    }
                    queue.add(node.left);
                }
                if (node.right != null) {
                    if (node.right.val == x) {
                        xNode = node.right;
                        xNodeParent = node;
                    }
                    if (node.right.val == y) {
                        yNode = node.right;
                        yNodeParent = node;
                    }
                    queue.add(node.right);
                }
            }
            if (xNode != null && yNode == null) {
                return false;
            } else if (xNode == null && yNode != null) {
                return false;
            } else if (xNode != null && yNode != null) {
                return xNodeParent != yNodeParent;
            }
        }
        return false;
    }


    private static boolean isCousins(TreeNode root, int x, int y) {
        if (root == null || root.left == null || root.right == null) return false;
        int depthX = findDeapth(root, x);
        int depthY = findDeapth(root, y);
        if (depthX != depthY) return false;
        int parentX = findParent(root, x);
        int parentY = findParent(root, y);
        if (parentX != parentY) return true;
        else return false;
    }

    private static int findDeapth(TreeNode root, int x) {
        if (root == null) return 0;
        if (root.val == x) return 1;
        int left = findDeapth(root.left, x);
        int right = findDeapth(root.right, x);
        if (left > 0 || right > 0) return 1 + left + right;
        else return 0;
    }

    private static int findParent(TreeNode root, int x) {
        if (root == null || (root.left == null && root.right == null)) return -1;
        if ( (root.left != null && root.left.val == x) || (root.right != null && root.right.val == x)) return root.val;
        int leftP = findParent(root.left, x);
        if (leftP != -1) return leftP;
        int rightP = findParent(root.right, x);
        if (rightP != -1) return rightP;
        return -1;
    }

    ///May day 6
    private static int majorityElement(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (countMap.get(nums[i]) == null)
                countMap.put(nums[i], 1);
            else
                countMap.put(nums[i], 1+countMap.get(nums[i]));
        }
        for (Integer key: countMap.keySet()) {
            if (countMap.get(key) > nums.length/2) return key;
        }
        return -1;
    }

    ///May day 5
    private static int firstUniqChar(String s) {
        HashSet<Character> repeated = new LinkedHashSet<>();
        HashMap<Character, Integer> uniques = new LinkedHashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            if (repeated.contains(x)){
                uniques.remove(x);
            }else {
                repeated.add(x);
                uniques.put(x, i);
            }
        }
        for (char x: uniques.keySet()) {
            return uniques.get(x);
        }
        return -1;
    }

    /// https://leetcode.com/problems/decode-ways/
    private static int numDecodings(String s) {
//        HashSet<Integer> keys = new HashSet<>();
//        for (int i = 1; i <= 26; i++) {
//            keys.add(i);
//        }
        int[] count = new int[1];
//        for (int i = 0; i < s.length(); i++) {
//            int x = s.charAt(i) - 48;
//            if (keys.contains(x))count++;
//            if (i < s.length()-1 && keys.contains(10*x+ (s.charAt(i+1)-48)))count++;
//        }
        numDecodingsHelper(s, 0, count);
        return count[0];
    }

    ///May day 4
    private static int findComplement(int num) {
        int temp = num;
        int allOnes = 1; int raise = 0;
        while (temp != 0){
            temp /= 2;
            raise++;
        }
        for (int i = 0; i < raise; i++) {
            allOnes *= 2;
        }
        allOnes = allOnes - 1;
        int answer = num ^ allOnes;
        return answer;
    }

    public int findComplementArun(int num) {
        int digits = (int) (Math.log(num) / Math.log(2)) + 1;
        long allOnes = (long) Math.pow(2, digits) - 1;
        return (int) (num ^ allOnes);
    }
    private static void numDecodingsHelper(String s, int idx, int[] count) {
        if (idx < 0 || idx > s.length()) return;
        if (idx == s.length()) {
            count[0]++;
            return;
        }else {
            for (int i = idx; i < s.length(); i++) {
                numDecodingsHelper(s, i+1, count);
                if (i < s.length()-1){
                    int y = 10*(s.charAt(i)-48) + (s.charAt(i+1)-48);
                    if (y>=1 && y <=26){
                        numDecodingsHelper(s, i+2, count);
                        i++;
                    }
                }
            }
        }

    }

    ///May day 3
    private static boolean canConstruct(String ransomNote, String magazine) {
        int[] notes = new int[26];
        for (int i = 0; i < ransomNote.length(); i++) {
            notes[ransomNote.charAt(i)-'a']++;
        }
        for (int i = 0; i < magazine.length(); i++) {
             notes[magazine.charAt(i)-'a']--;
        }
        for (int i = 0; i < notes.length; i++) {
            if (notes[i] > 0) return false;
        }
        return true;
    }

    ///May day 2
    private static int numJewelsInStones(String J, String S) {
        HashSet<Character> jewels = new HashSet<>();
        for (int i = 0; i < J.length(); i++) {
            jewels.add(J.charAt(i));
        }
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            if (jewels.contains(S.charAt(i))) count++;
        }
        return count;
    }

    ///May Day 1
    /// https://leetcode.com/problems/first-bad-version/
    private static int firstBadVersion(int n) {
        int i=1,j=n, mid=0;
        int lastFound = 0;
        while (i<j){
            mid=i+(j-i)/2;
            if (isBadVersion(mid)){
                lastFound = mid;
                j = mid;
            }else {
                i = mid+1;
            }
        }
        return lastFound;
    }
    static boolean isBadVersion(int version){
        if (version >= 2) return true;
        else return false;
    }



    /// Week 3 day28
    private static boolean isValidSequence(TreeNode root, int[] arr) {
        if (root == null || arr == null || arr.length == 0) return false;
        boolean answer = isValidSequence(root, arr, 0);
        return answer;
    }

    private static boolean isValidSequence(TreeNode root, int[] arr, int pos) {
        if (pos > arr.length-1 || root == null || root.val != arr[pos]) return false;
        if (root.left == null && root.right == null && pos == arr.length-1) return true;
        return isValidSequence(root.left, arr, pos+1) || isValidSequence(root.right, arr, pos+1);
    }

    /// Week 3 day28
    private static int maximalSquare(char[][] matrix) {
        int maxLen = 0;
        if (matrix == null || matrix.length == 0) return maxLen;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') continue;
                else{
                    while (isSquare(matrix, i, j, i+maxLen, j+maxLen)){
                        maxLen++;
                    }
                }
            }
        }
        return maxLen*maxLen;
    }

    private static boolean isSquare(char[][] matrix, int startX, int startY, int endX, int endY) {
        if (endX > matrix.length-1 || endY > matrix[0].length-1) return false;
        for (int i = startX; i <= endX ; i++) {
            for (int j = startY; j <= endY; j++) {
                if (matrix[i][j] == '0') return false;
            }
        }
        return true;
    }

    /// Week 3 day29
    private static int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        int discard = maxPathSumHelper(root, max);
        return max[0];
    }

    private static int maxPathSumHelper(TreeNode node, int[] max) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) {
            if (max[0] < node.val) max[0] = node.val;
            return node.val;
        }
        int sum = node.val;
        int left = maxPathSumHelper(node.left, max);
        if (left > 0) sum += left;
        int right = maxPathSumHelper(node.right, max);
        if (right > 0) sum += right;
        if (max[0] < sum) max[0] = sum;
        return node.val + Math.max( (left<0? 0:left), (right<0?0:right));
    }


    private static void testLRUCache(){
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        int x = cache.get(1);
        cache.put(3,3);
        x = cache.get(2);
        cache.put(4,4);
        x = cache.get(1);
        x = cache.get(3);
        x = cache.get(4);

    }
    /// Week 3 day23
    private static int rangeBitwiseAnd(int m, int n) {
        if (m < 0 || n < m) return 0;
        int output = m;
        for (int i = m+1; i <= n; i++) {
            output = (output & i);
        }
        int x = 9;
        x >>= 1;
        return output;
    }


    /// Week 3 day22
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length-1; i++) {
            int sum = nums[i];
            if (sum == k){
                count++;
            }
            for (int j = i+1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k){
                    count++;
                }
            }
        }
        return count;
    }

    private static void subarraySumHelper(int[] nums, int sum){

    }

    /// https://leetcode.com/problems/partition-list/
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode first = head;
        ListNode prevToPivot = null;
        ListNode pivot = head.val == x ? head : null;
        ListNode prev = head;
        ListNode curr = head.next;

        if (pivot == null){
            while (curr != null && curr.val != x){
                prev = curr;
                curr = curr.next;
            }
            prevToPivot = prev;
            pivot = curr;
            if (pivot == null) return pivot;
        }

        while (curr != null){
            if (curr.val < x){

            }
            curr = curr.next;

        }
        return head;
    }


    /// Week 3 day21
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int row = binaryMatrix.dimensions().get(0);
        int col = binaryMatrix.dimensions().get(1);
        int result = -1; //assume its last column

        for (int i = row-1; i >= 0; i--) {
            int j = (result == -1 ? col-1 : result);
            for (; j >= 0 ; j--) {
                if (binaryMatrix.get(i,j) == 0) break;
                else {
                   result = j;
                }
            }
        }
        return result;
    }

    /// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
    private static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode output = new ListNode(head.val-1);
        output.next = head;
        getNextNodeAndRemoveIfDupe(output);
        return output.next;
    }

    private static boolean getNextNodeAndRemoveIfDupe(ListNode node){
        if (node.next == null) return false;
        else {
            boolean toDelete = getNextNodeAndRemoveIfDupe(node.next);
            if (node.next.val == node.val) {
                node.next = node.next.next;
                return true;
            }else if (toDelete){
                node.next = node.next.next;
                return false;
            }
        }
        return false;
    }

    // week 4 day 20
    private static TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = new TreeNode(preorder[i]);
            appendNode(root, node);
        }
        return root;
    }

    private static void appendNode(TreeNode root, TreeNode node){
        if (root.val > node.val){
            if (root.left != null) appendNode(root.left, node);
            else {
                root.left = node;
            }
        }else if(root.val < node.val){
            if (root.right != null) appendNode(root.right, node);
            else {
                root.right = node;
            }
        }
    }

    /// https://leetcode.com/problems/search-in-rotated-sorted-array/
    private static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        final int len = nums.length;
        int i = 0, j = len - 1;
        int mid = 0; int pivot = 0;
        if (nums[0] >= nums[len-1]){
            while (i<=j){
                mid = i+ (j-i)/2;
                if (mid > 0 && nums[mid-1] > nums[mid]){
                    pivot = mid;
                    break;
                }
                if (nums[0] > nums[mid]) j = mid-1;
                else i = mid+1;
            }
        }
        if (nums[pivot] == target) return pivot;
        if (pivot == 0){
            i = 0; j = len-1;
        }else if (nums[0] <= target) {
            i = 0; j = pivot-1;
        }else {
            i = pivot; j = len-1;
        }

        while (i<=j){
            mid = i+ (j-i)/2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target){
                i = mid + 1;
            }else {
                j = mid-1;
            }
        }
        return -1;
    }


    /// Week 2 day 3
    private static int diameterOfBinaryTree(TreeNode root) {
        Map<String, Integer> result = new HashMap<>();
        result.put("max", 0);
        diameterOfBinaryTreeHelper(root, result);
        return result.get("max");
    }

    private static int diameterOfBinaryTreeHelper(TreeNode node, Map<String, Integer> result) {
        if (node == null) return 0;
        if (node.left == null && node.right == null){
            return 1;
        }

        int left = diameterOfBinaryTreeHelper(node.left, result);
        int right = diameterOfBinaryTreeHelper(node.right, result);
        int distance = left + right;
        if (distance > result.get("max")){
            result.put("max", distance);
        }
        return 1+Math.max(left, right);
    }


    /// week 3 day 18
    /// https://leetcode.com/problems/minimum-path-sum/
    private static int minPathSum2(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        return minPathSum2(grid, grid.length-1, grid[0].length-1, visited);
    }
    private static int minPathSum2(int[][] grid, int row, int col, boolean[][] visited) {
        if (row < 0 || col < 0) return Integer.MAX_VALUE;
        if (row == 0 && col == 0) return grid[0][0];

        if (visited[row][col]){
            return grid[row][col];
        }else{
            visited[row][col] = true;
            grid[row][col] = grid[row][col]+ Math.min(minPathSum2(grid, row, col-1, visited), minPathSum2(grid, row-1, col, visited));
            return grid[row][col];
        }
    }

    /// week 3 day 17
    private static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 ) return 0;
        Map<String, Integer> count = new HashMap<>();
        count.put("COUNT", 0);
        boolean[][] used = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(!used[i][j])numIslands(grid, i, j, used, count, false);
            }
        }
        return count.get("COUNT");
    }

    private static boolean numIslands(char[][] grid, int i, int j, boolean[][] used, Map<String, Integer> count, boolean counted) {
        if (i<0 || j <0 || i > grid.length-1 || j > grid[0].length-1){
            return true;
        }
        if (grid[i][j] == '0' || used[i][j]) return true;
        if (grid[i][j] == '1'){
            used[i][j] = true;
            boolean left = numIslands(grid, i, j-1, used, count, true);
            boolean right = numIslands(grid, i, j+1, used, count, true);
            boolean up = numIslands(grid, i-1, j, used, count, true);
            boolean down = numIslands(grid, i+1, j, used, count, true);
            if (left && right && up && down){
                if (!counted)count.put("COUNT", 1+count.get("COUNT"));
                return true;
            }
        }

        return false;
    }


    // week 2 day 14
    private static String stringShift(String s, int[][] shift) {
        if (s == null || s.length()==0) return s;
        char[] ary = s.toCharArray();

        for (int i = 0; i < shift.length; i++) {
            int[] instr = shift[i];
            if (instr[0] == 0){
                leftShift(ary, instr[1]%ary.length);
            }else {
                rightShift(ary, instr[1]%ary.length);
            }
        }
        return new String(ary);
    }

    private static void rightShift(char[] ary, int pos){
        char[] temp = new char[pos];
        for (int i = ary.length-1; i >= 0; i--) {
            if (i+pos > ary.length-1){
                temp[pos-1 - (ary.length-1 -i)] = ary[i];
            }else{
                ary[i+pos] = ary[i];
            }
        }
        for (int i = 0; i < pos; i++) {
            ary[i] = temp[i];
        }
    }

    private static void leftShift(char[] ary, int pos){
        char[] temp = new char[pos];
        for (int i = 0; i < ary.length; i++) {
            if (i < pos){
                temp[i] = ary[i];
            }else{
                ary[i-pos] = ary[i];
            }
        }
        for (int i = 0; i < pos; i++) {
            ary[ary.length-pos+i] = temp[i];
        }
    }

    /// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
    private static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length<=2) return nums.length;
        int insertIdx = 0;
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i>0 && nums[i-1] == nums[i]){
                count++;
                if (count > 2) {
                    insertIdx--;
                }
            }else if (i > 0 && nums[i-1] != nums[i]){
                count = 1;
            }
            nums[insertIdx++] = nums[i];
        }
        return insertIdx;
    }


    private static void testMinStack(){
        MinStack stack = new MinStack();
        stack.push(-10);stack.push(14);int min  = stack.getMin();min = stack.getMin();
        stack.push(-20);min = stack.getMin();min = stack.getMin();int top = stack.top();
        min = stack.getMin();stack.pop();stack.push(10);stack.push(-7);min = stack.getMin();
        stack.push(-7);stack.pop();top = stack.top();min = stack.getMin();stack.pop();
    }

    /// 30 day, week 2 day 2
    private static boolean backspaceCompare(String S, String T) {
        Stack<Character> first = new Stack<>();
        Stack<Character> second = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if ('#' != S.charAt(i)){
                first.push(S.charAt(i));
            }else{
                if (!first.empty()) first.pop();
            }
        }
        for (int i = 0; i < T.length(); i++) {
            if ('#' != T.charAt(i)){
                second.push(T.charAt(i));
            }else{
                if (!second.empty()) second.pop();
            }
        }
        return first.equals(second);
    }

    /// 30 day, week 2 day 1
    private static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first = head;
        ListNode second = head;

        while (second != null){
            if (second.next != null){
                first = first.next;
                second = second.next.next;
            }else break;
        }
        return first;
    }

    /// https://leetcode.com/problems/word-search/
    private static boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (word.charAt(0) == board[row][col]){
                    boolean found = exist(board, used, word, row, col, 0);
                    if (found) return found;
                }
            }
        }
        return false;
    }

    private static boolean exist(char[][] board, boolean[][] used, String word, int row, int col, int index) {
        if (index == word.length()) return true;
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) return false;

        boolean match = false;
        if (!used[row][col] && word.charAt(index) == board[row][col]){
            used[row][col] = true;
            if (!match) match = exist(board, used, word, row, col+1, index+1);
            if (!match) match = exist(board, used, word, row+1, col, index+1);
            if (!match) match = exist(board, used, word, row, col-1, index+1);
            if (!match) match = exist(board, used, word, row-1, col, index+1);
            used[row][col] = false;
        }else {
            return false;
        }
        return match;
    }

    public List<List<String>> groupAnagrams_copy(String[] strs) {
        HashMap<String,Integer> map=new HashMap();
        List<List<String>> res=new ArrayList();
        int k=0;
        for(int i=0;i<strs.length;i++){
            char[] tmp=strs[i].toCharArray();
            Arrays.sort(tmp);
            String tmp1=new String(tmp);

            if(map.get(tmp1)!=null){
                int n=map.get(tmp1.toString());
                List<String> l=res.get(n);
                l.add(strs[i]);
                res.set(n,l);
            }
            else{
                map.put(tmp1,new Integer(k));
                k=k+1;
                List<String> templ=new ArrayList();
                templ.add(strs[i]);
                res.add(templ);

            }
        }
        return res;
    }

    /// https://leetcode.com/problems/group-anagrams/
    private static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> output = new ArrayList<>();
        if (strs == null || strs.length==0) return output;

        HashMap<String, List<String>> grouped = new HashMap<>();
        ArrayList<String> oneList = new ArrayList<>();
        oneList.add(strs[0]);
        grouped.put(strs[0], oneList);
        output.add(oneList);
        for (int i = 1; i < strs.length; i++) {
            String current = strs[i];
            boolean success = false;
            for (String key: grouped.keySet()) {
                if (isAnagram(key, current)){
                    grouped.get(key).add(current);
                    success = true;
                    break;
                }
            }
            if (!success){
                ArrayList<String> list = new ArrayList<>();
                list.add(current);
                grouped.put(current, list);
                output.add(list);
            }
        }
        return output;
    }

    private static boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length())
            return false;

        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i)-'a']++;
            counts[t.charAt(i)-'a']--;
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) return false;
        }
        return true;
    }


    /// 30 day challenge day 5
    private static int maxProfit(int[] prices) {
        if (prices == null || prices.length ==0) return 0;
        boolean bought = false;
        int buyIdx = -1;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if ((i+1)<prices.length && prices[i] < prices[i+1] && !bought){
                bought = true;
                buyIdx = i;
            }
            if ((i+1)<prices.length && prices[i] > prices[i+1] && bought){
                bought = false;
                profit += (prices[i]-prices[buyIdx]);
            }
        }
        if (bought){
            profit += (prices[prices.length-1]-prices[buyIdx]);
        }
        return profit;
    }

    /// count the bits to change
    private static int bitsToChange(int m, int n){
        int x = m ^ n;
        int y = 1;
        int count = 0;
        while (x > 0){
            count += (x & y);
            x = x >> 1;
        }
        return count;
    }

    /// bitwise operator tests
    private static void bitwiseOperators(){
        int x = 11;
        int y = 1;
        int count = 0;
        while (x > 0){
            count += x & y;
            x = x>>1;
        }
        System.out.println("ans = " + count);
    }

    /// 30 day challenge day 4
    private static void moveZeroes(int[] nums) {
        if (nums == null || nums.length<=1) return;
        int nonzero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                nums[nonzero++] = nums[i];
            }
        }
        for (int i = nonzero; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /// 30 day challenge day 3
    private static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            sum = nums[i];
            if (sum > max) max = sum;
            for (int j = i+1; j < nums.length; j++) {
                sum += nums[j];
                if (sum > max) max = sum;
            }
        }
        return max;
    }

    /// 30 day challenge day 1
    private static int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        HashSet<Integer> keys = new HashSet<>();
        for (int num: nums) {
            if(keys.contains(num)) keys.remove(num);
            else keys.add(num);
        }
        return keys.iterator().next();
    }

    private static List<Integer> kthPerm(int n, int k) {
        List<Integer> result = new ArrayList<>();
        List<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            digits.add(i);
        }
        kthPerm(n, k, result, digits);
        return result;
    }

    private static void kthPerm(int n, int k, List<Integer> result, List<Integer> digits) {
        if (k == 1) {
            result.addAll(digits);
            return;
        }
        int itemsPerDigit = factorial(n - 1);
        int digitLocation = (int) Math.ceil((float) k / itemsPerDigit);
        int digit = digits.remove(digitLocation - 1);
        result.add(digit);
        int remaining = k - itemsPerDigit * (digitLocation - 1);
        kthPerm(n - 1, remaining, result, digits);
    }

    private static int factorial(int n) {
        int f = 1;
        for (int i = 1; i <= n; i++) {
            f *= i;
        }
        return f;
    }

    /// https://leetcode.com/problems/permutations-ii/   TODO
    private static  List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        permuteUniqueHelper(nums, used,new ArrayList<Integer>(), output);
        return output;
    }

    private static void permuteUniqueHelper(int[] nums, boolean[] used, List<Integer> tempList, List<List<Integer>> output) {
        if (tempList.size() == nums.length){
            output.add(new ArrayList<>(tempList));
            return;
        }else if (tempList.size() > nums.length){
            return;
        }else {
            for (int i = 0; i < nums.length; i++) {
                if (!used[i]){
                    tempList.add(nums[i]);
                    used[i] = true;
                    permuteUniqueHelper(nums, used, tempList, output);
                    used[i] = false;
                    tempList.remove(tempList.size()-1);
                    while (i < nums.length-1 && nums[i] == nums[i+1]) i++;
                }
            }
        }
    }

    private static List<List<Integer>> allCombinations(int[] values) {
        List<List<Integer>> allCombs = new ArrayList<>();
        allCombs.add(new ArrayList<>());

        for (int v: values) {
            List<List<Integer>> newCombinations = new ArrayList<>();
            for (List<Integer> l: allCombs) {
                List<Integer> newComb = new ArrayList<>();
                newComb.addAll(l);
                newComb.add(v);
                newCombinations.add(newComb);
            }
            allCombs.addAll(newCombinations);
        }
        return allCombs;
    }


    /// https://leetcode.com/problems/generate-parentheses/
    private static List<String> generateParenthesis(int n) { //// (())(())
        List<String> output = new ArrayList<>();
        if (n <= 0 ) return output;
        HashSet<String> existing = new HashSet<>();
        existing.add("");
        for (int i = 1; i <= n; i++) {
            List<String> temp = new ArrayList<>();
            for (String paranths: existing) {
                temp.add("("+paranths+")");
                temp.add("()"+paranths);
                temp.add(paranths+"()");
            }
            existing.addAll(temp);
        }
        for (String paranth: existing) {
            if (paranth.length() == 2*n)
                output.add(paranth);
        }
        return output;
    }

    private static void addParenthesisToSet(int n, String valid, LinkedHashSet<String> values) {
        if (n == 0) return;
        values.add("("+valid+")");
        values.add("("+")"+valid);
        values.add(valid+"("+")");
        int len = values.size();
        for (Iterator<String> itr = values.iterator(); itr.hasNext();){
            addParenthesisToSet(n-1, itr.next(), values);
        }
    }

    private static boolean subsetWithSum(int[] nums, int k){
        return   subsetWithSum(nums, k, 0);
    }
    private static boolean subsetWithSum(int[] nums, int k, int start){/// 5, 1 > 2, 2
        if(k == 0){
            return true;
        }else if(k > 0){

            for(int i = start; i < nums.length; i++){
                boolean success = subsetWithSum(nums, k-nums[i], i+1);
                if(success) return success;
            }
        }else{
            return false;
        }

        return false;
    }



    private static boolean isMatch(String text, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        return m.matches();
    }
    /// https://leetcode.com/problems/subsets/
    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        output.add(new ArrayList<>(tempList));
        boolean[] used = new boolean[nums.length];
        HashSet<String> keys = new HashSet<>();
        subsets(nums, output, new ArrayList<Integer>(), used, 0, nums.length, keys);
        return output;
    }

    private static void subsets(int[] nums, List<List<Integer>> output, List<Integer> tempList, boolean[] used, int start, int size, HashSet<String> keys) {
        if (tempList.size() == size){
            StringBuilder sb = new StringBuilder();
            for (Integer key: tempList) {
                sb.append(key);
                sb.append("_");
            }
            if (!keys.contains(sb.toString())){
                keys.add(sb.toString());
                output.add(new ArrayList<>(tempList));
            }
            return;
        }else if(tempList.size() > size){
            return;
        }
        else{
            for (int j = 1; j <= size; j++) {
                for (int i = start; i < nums.length; i++) {
                    if (!used[i]){
                        tempList.add(nums[i]);
                        used[i] = true;
                        subsets(nums,output, tempList,used, i+1, j, keys);
                        tempList.remove(tempList.size()-1);
                        used[i] = false;
                    }
                }
            }
        }
    }

        /// https://leetcode.com/problems/combinations/
    private static List<List<Integer>> combine(int n, int k) {
        List<Integer> tempList = new ArrayList<>();
        List<List<Integer>> allList = new ArrayList<>();
        combine(n, k, 1, allList, tempList);
        return allList;
    }

    private static void combine(int n, int k, int start, List<List<Integer>> allList, List<Integer> tempList) {
        if (tempList.size() == k){
            allList.add(new ArrayList<>(tempList));
            return;
        }else {
            for (int i = start; i <= n; i++) {
                tempList.add(i);
                combine(n, k, i+1, allList, tempList);
                tempList.remove(tempList.size()-1);
            }
        }
    }

    ///https://leetcode.com/problems/set-matrix-zeroes/
    private static void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        HashSet<Integer> markedRows = new HashSet<>();
        HashSet<Integer> markedCols = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0){
                    markedRows.add(i);
                    markedCols.add(j);
                }
            }
        }
        for (Integer row: markedRows) {
            Arrays.fill(matrix[row], 0);
        }
        for (Integer col: markedCols) {
            for (int i = 0; i < rows; i++) {
                matrix[i][col] = 0;
            }
        }
    }


    /// https://leetcode.com/problems/simplify-path/
    private static String simplifyPath(String path) {
        StringTokenizer st = new StringTokenizer(path, "/");
        LinkedList<String> root = new LinkedList<>();
        while (st.hasMoreTokens()){
            String token = st.nextToken();
            if (".".equals(token)){
                continue;
            }else if("..".equals(token)){
                if (root.size() > 0)root.removeLast();
            }else{
                root.addLast(token);
            }
        }
        StringBuilder sb = new StringBuilder("");
        for (String dir: root) {
            sb.append("/").append(dir);
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }


    /// https://leetcode.com/problems/minimum-path-sum/
    private static int minPathSum(int[][] grid) {
        List<Integer> totals = new ArrayList<>();
        minPathSum(grid, 0, 0, 0, totals);
        int mininum = totals.get(0);
        for (Integer path: totals) {
            if (path < mininum)
                mininum = path;
        }
        return mininum;
    }

    private static void minPathSum(int[][] grid, int row, int col, int prevPath, List<Integer> total) {
        if (row == grid.length-1 && col == grid[0].length-1){
            total.add(grid[row][col]+prevPath);
            return;
        }
        if (row < grid.length-1){
            minPathSum(grid, row+1, col, prevPath+grid[row][col], total);
        }
        if (col < grid[0].length-1){
            minPathSum(grid, row, col+1, prevPath+grid[row][col], total);
        }
    }


    /// https://leetcode.com/problems/unique-paths-ii/
    private static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length; int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) return 0; // if robot can not start then it can reach no where. :)
        boolean obstacle = false;
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {obstacleGrid[0][i] = 0; obstacle=true;}
            else obstacleGrid[0][i] = obstacle ? 0 : 1;
        }
        obstacle = false;
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {obstacleGrid[i][0] = 0; obstacle=true;}
            else obstacleGrid[i][0] = obstacle ? 0 : 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(obstacleGrid[i][j] == 1) obstacleGrid[i][j] = 0;
                else obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
            }
        }
        return obstacleGrid[m-1][n-1];
    }

    /// https://leetcode.com/problems/unique-paths/
    private static int uniquePaths(int m, int n) {
        return uniquePaths(m, n, new HashMap<String, Integer>());

    }

    private static int uniquePaths(int m, int n, HashMap<String, Integer> visited) {
        if (m == 1 && n == 1) return 1;
        if (m == 0 || n == 0) return 0;
        String key = ""+m+"_"+n;
        if (!visited.containsKey(key)){
            int count = uniquePaths(m-1, n, visited) + uniquePaths(m, n-1, visited);
            visited.put(key, count);
        }
        return visited.get(key);
    }

    private static int uniquePaths2(int m, int n) {
        int[][] metrix = new int[m][n];
        Arrays.fill(metrix[0], 1);
        for (int i = 0; i < m; i++) {
            metrix[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                metrix[i][j] = metrix[i][j-1] + metrix[i-1][j];
            }
        }
        return metrix[m-1][n-1];
    }

    /// https://leetcode.com/problems/permutation-sequence/
    private static String getPermutation(int n, int k) {
        List<String> allPermutations = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        getPermutation(n, 0,allPermutations, sb);
        return "";
    }

    private static void getPermutation(int n, int start, List<String> allPermutations, StringBuilder sb) {
        if (sb.length() == n){
            allPermutations.add(sb.toString());
        }else{
            for (int i = start; i <= n; i++) {
                sb.append(i);
                getPermutation(n, 1, allPermutations,sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    /// https://leetcode.com/problems/spiral-matrix-ii/
    private static int[][] generateMatrix(int n) {
        if(n < 0) return null;
        int[][] output = new int[n][n];
        int x=0,y=0;
        char dir = 'R';
        for (int i = 1; i <= n*n; i++) {
            output[x][y] = i;
            switch (dir){
                case 'R' :{
                    if (y+1 < n && output[x][y+1] == 0){y++; break;}
                    else {x++;dir='D';break;}
                }
                case 'D' :{
                    if (x+1 < n && output[x+1][y] == 0){x++;; break;}
                    else{y--;dir='L';break;}
                }
                case 'L' :{
                    if (y>0 && output[x][y-1] == 0){y--;; break;}
                    else {x--;dir='U';break;}
                }
                case 'U' :{
                    if (x > 0 && output[x-1][y] == 0){x--;; break;}
                    else {y++;dir='R';break;}
                }
            }
        }
        return output;
    }



    /// https://leetcode.com/problems/powx-n/
    public double myPow(double x, int n) {
        if (x == 0.0) return x;
        if (n == 0) return 1;
        if (n == 1) return x;
        double output = 1;
        boolean neg = n < 0 ? true : false;
        n = n < 0 ? -1*n : n;
        for (int i = 1; i <= n; i++) {
            output = output*x;
        }
        if (neg) output = (1d/output);
        return output;
    }

    /// https://leetcode.com/problems/combination-sum-ii/
    private static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> output = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return output;
        Arrays.sort(candidates);
        helperCombinationSum(candidates, target, 0, new ArrayList<Integer>(), output, new HashSet<String>());
        return output;
    }

    private static void helperCombinationSum(int[] candidates, int target, int idx, List<Integer> temp,  List<List<Integer>> output, HashSet<String> keys){
        if (target == 0){
            String key = "";
            for (int k: temp) {
                key = k+key;
            }
            if (keys.add(key))
                output.add(new ArrayList<>(temp));
            return;
        }else if(target < 0){
            return;
        }else {
            for (int i = idx; i < candidates.length; i++) {
                temp.add(candidates[i]);
                helperCombinationSum(candidates, target-candidates[i], i+1, temp, output, keys);
                temp.remove(temp.size()-1);
            }
        }
    }


    private static void helperCombinationSum2(int[] candidates, int target, int idx, List<Integer> temp,  Map<Integer, List<Integer>> output){
        if (target == 0){
            int key = 1;
            for (int k: temp) {
                key *= k;
            }
            output.put(key, new ArrayList<>(temp));
        }else if (target > 0){
            for (int i = idx; i < candidates.length; i++) {
                if (i == idx) continue;
                if(temp.add(candidates[i]))
                    helperCombinationSum2(candidates, target-candidates[i], idx+1, temp, output);
                else continue;
                temp.remove(temp.size()-1);
            }
        }else{

        }
    }

    /// https://leetcode.com/problems/rotate-image/
    private static void rotate_2(int[][] matrix) {
        if (matrix == null || matrix.length==0) return;
        int height = matrix.length;
        for (int row = 0; row < (height+1)/2; row++) {
            for (int col = 0; col < height/2; col++) {
                int temp = matrix[height-1-col][row];
                matrix[height-1-col][row] = matrix[height-1-row][height-1-col];
                matrix[height-1-row][height-1-col] = matrix[col][height-1-row];
                matrix[col][height-1-row] = matrix[row][col];
                matrix[row][col] = temp;
            }
        }
    }

    /// https://leetcode.com/problems/rotate-image/
    private static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length==0) return;
        int height = matrix.length-1;
        HashSet<String> visited = new HashSet<>();
        int row, col;
        for (int i = 0; i < (height+1)/2; i++) {
            for (int j = 0; j < height/2; j++) {
                if (visited.contains(""+i+"_"+j)) continue; //this has been replaced.
                boolean done = false;
                row = i; col = j;
                int mem = matrix[row][col];
                while (!done){//calculate new position
                    int temp1 = row; int temp2 = col;
                    //new positions for the element at i,j
                    row = temp2;
                    col = height - temp1;
                    if (visited.contains(""+row+"_"+col)) {done=true;}
                    else{
                        int tempValue = matrix[row][col];
                        matrix[row][col] = mem;
                        mem = tempValue;
                        visited.add(""+row+"_"+col);
                    }
                }//while
            }
        }
    }

    /// https://leetcode.com/problems/multiply-strings/
    private static String multiply_2(String num1, String num2) {
        if (num1 == null || "".equals(num1) || num2 == null || "".equals(num2))
            return "";
        char[] result = new char[num1.length()+num2.length()];
        for (int i = 0; i < result.length; i++) {
            result[i] = '0';
        }
        int carry = 0;
        for (int i = num1.length()-1; i >=0 ; i--) {
            char c1 = num1.charAt(i);
            for (int j = num2.length()-1; j >=0 ; j--) {
                char c2 = num2.charAt(j);
                int multi = (c1-'0')*(c2-'0') + (result[i+j+1]-'0') + carry;
                carry = multi/10;
                multi = multi%10;
                result[i+j+1] = (char)(multi+'0');
            }
        }
        //find the first non-zero digit
        int start = -1;
        for (int i = 0; i < result.length; i++) {
            if (result[i] != '0'){
                start = i;
                break;
            }
        }
        return start == -1 ? "0" : new String(result,start, result.length-start);
    }

    /// https://leetcode.com/problems/multiply-strings/
    private static String multiply(String num1, String num2) {
        if (num1 == null || "".equals(num1) || num2 == null || "".equals(num2))
            return "";
        List<String> additions = new ArrayList<>();
        for (int i = 0; i < num1.length(); i++) {
            char n1 = num1.charAt(num1.length() -1 -i);
            StringBuilder oneMultiplication = new StringBuilder(num1.length()+num2.length());
            char carry = '0';
            for (int j = 0; j < num2.length(); j++) {
                char n2 = num2.charAt(num2.length()-1-j);

                char[] res = multiply( n1, n2, carry);
                carry = res[1];
                oneMultiplication.insert(0,res[0]);
            }
            if ( carry != '0')oneMultiplication.insert(0,carry);
            for (int zeros = 0; zeros < i; zeros++) { ///every next row will have to multipled by 10
                oneMultiplication.append('0');
            }
            additions.add(oneMultiplication.toString());
        }
        StringBuilder addition = new StringBuilder();
        for (String number: additions) {
            StringBuilder sbTemp = new StringBuilder(number.length()+addition.length());
            char carry = '0';
            for (int i = 0; i < number.length(); i++) {
                char c1 = number.charAt(number.length()-1-i);
                char c2 = addition.length()-i-1 >= 0 ? addition.charAt(addition.length()-1-i) : '0';
                char[] res = addition(c1,c2, carry);
                carry = res[1];
                sbTemp.insert(0, res[0]);
            }
            if (carry != '0') sbTemp.insert(0, carry);

            addition.replace(0, addition.length(), sbTemp.toString());
        }
        for (int i = 0; i < addition.length(); i++) {
            if (addition.charAt(i) == '0'){
                continue;
            }else {
                return addition.substring(i, addition.length());
            }
        }
        return "0";
    }
    private static char[] addition(char c1, char c2, char carry) {
        int x = c1-48;
        int y = c2-48;
        int add = (x+y) + ((int)carry-48);
        return  new char[]{(char)(48+ add%10), (char)(48 + add/10)};
    }
    private static char[] multiply(char c1, char c2, char carry) {
        int x = c1-48;
        int y = c2-48;
        int m = (x*y) + ((int)carry-48);
        return  new char[]{(char)(48+ m%10), (char)(48 + m/10)};
    }

    /// https://leetcode.com/problems/spiral-matrix/
    private static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> output = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return output;
        int row = 0; int col = 0;
        HashSet<String> visited = new HashSet<>();
        int total = matrix.length*matrix[0].length;
        char dir = 'R';

        visited.add(""+row+col);
        output.add(matrix[row][col]);
        boolean toAdd = false;
        while (visited.size() <  total){
            switch (dir){
                case 'R':{
                    if (col < matrix[0].length-1 && !visited.contains(""+row+(col+1)) ){
                        col++; toAdd=true;
                    }else {
                        dir = 'D';
                    }
                    break;
                }
                case 'D':{
                    if (row < matrix.length-1 && !visited.contains(""+(row+1)+col)){
                        row++; toAdd=true;
                    }else {
                        dir = 'L';
                    }
                    break;
                }
                case 'L':{
                    if (col > 0 && !visited.contains(""+row+(col-1))){
                        col--; toAdd=true;
                    }else {
                        dir = 'U';
                    }
                    break;
                }
                case 'U':{
                    if (row > 0 && !visited.contains(""+(row-1)+col)){
                        row--; toAdd=true;
                    }else {
                        dir = 'R';
                    }
                    break;
                }
            }
            if (toAdd){
                output.add(matrix[row][col]);
                visited.add(""+row+col);
            }
            toAdd=false;
        }
        return output;
    }


    /// https://leetcode.com/problems/sort-colors/
    private static void sortColors(int[] nums) {
        int zeros = 0;
        int ones = 0;
        int twos = nums.length-1;
        for (int i = 0; i < nums.length; ) {
            int x = nums[i];
            switch (x){
                case 0:{
                    if (i <= zeros){//at the right place
                        i++;
                    }else {
                        nums[i] = nums[zeros];
                        nums[zeros] = x;
                        zeros++;
                        if (zeros>=ones)ones++;
                    }
                    break;
                }
                case 1:{
                    if (ones<=i && i<=twos){
                        i++;
                    }else {
                        nums[i] = nums[ones];
                        nums[ones] = x;
                        ones++;
                    }
                    break;
                }
                case 2:{
                    if (twos<=i && i<=nums.length){
                        i++;
                    }else {
                        nums[i] = nums[twos];
                        nums[twos] = x;
                        twos--;
                    }
                    break;
                }
            }
        }
    }

    /// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    private static int[] searchRange(int[] nums, int target) {
        int[] output = new int[]{-1,-1};
        if (nums == null || nums.length == 0 || target < nums[0] || target > nums[nums.length-1]) return output;
        if (nums.length == 1){
            if (target == nums[0]) return new int[]{0,0};
            else return output;
        }
        int i = 0, j = nums.length-1;
        int mid = -1;
        ///lower bound
        while (i < j){
            mid = (i+j)/2;
            if (nums[mid] < target){
                i = mid+1;
            }else if (nums[mid] > target || nums[mid] == target){
                j = mid;
            }
        }
        if (nums[i] == target) output[0]=i;
        else return output;
        i = 0; j = nums.length-1;
        mid = -1;
        ///upper bound
        while (i < j){
            mid = (i+j)/2;
            if (nums[mid] < target || nums[mid] == target){
                i = mid+1;
            }else if (nums[mid] > target){
                j = mid;
            }
        }
        if (nums[i] == target) output[1] = i;
        else if (i > 0 && nums[i-1] == target) output[1] = i-1;
        else  return new int[]{-1,-1};
        return output;
    }

    /// https://leetcode.com/problems/combination-sum/
    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> output = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return output;
        Arrays.sort(candidates);
        helper(candidates, target, 0, new ArrayList<Integer>(), output);
        return output;
    }

    private static void helper(int[] candidates, int target, int idx, List<Integer> tempList, List<List<Integer>> output){
        if (target == 0){
            output.add(new ArrayList<>(tempList));
        }else if (target > 0){
            for (int i = idx; i < candidates.length; i++) {
                tempList.add(candidates[i]);
                helper(candidates, target-candidates[i], i, tempList, output);
                tempList.remove(tempList.size()-1); //removing last element
            }
        }
    }

    private static void testIsValidSudoku(){
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        char[][] board2 = new char[][]{
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        char[][] board3 = new char[][]{
                {'.','.','4','.','.','.','6','3','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'5','.','.','.','.','.','.','9','.'},
                {'.','.','.','5','6','.','.','.','.'},
                {'4','.','3','.','.','.','.','.','1'},
                {'.','.','.','7','.','.','.','.','.'},
                {'.','.','.','5','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}};
        boolean output = isValidSudoku(board3);
    }
    /// https://leetcode.com/problems/valid-sudoku/
    private static boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) return false;
        for (int i = 0; i < board.length; i++) {
            HashSet<Character> colSet = new HashSet();
            for (int j = 0; j < board[0].length; j++) {
                char x = board[j][i];
                if ('.' == x) continue;
                else if (colSet.contains(x)) return false;
                else colSet.add(x);
            }
        }
        for (int i = 0; i < board.length; i++) {
            HashSet<Character> rowSet = new HashSet();
            for (int j = 0; j < board[0].length; j++) {
                char x = board[i][j];
                if ('.' == x) continue;
                else if (rowSet.contains(x)) return false;
                else rowSet.add(x);
            }
        }
        for (int i=0; i < 9 ; i = i+3) {
            for (int j = 0; j < 9; j = j+3) {
                ///validating every group
                HashSet<Character> blockSet = new HashSet();
                for (int m = i; m < i+3; m++) {
                    for (int n = j; n < j+3; n++) {
                        char x = board[m][n];
                        if ('.' == x) continue;
                        else if (blockSet.contains(x)) return false;
                        else blockSet.add(x);
                    }
                }
            }
        }
        return true;
    }

    private static int solution(int[] A) {
        // write your code in Java SE 8
        if (A == null || A.length == 0) return 0;
        int output = 1;
        for(int i=0; i<A.length; i++){
            if (A[i] == 0) return 0;
            else if (A[i] < 0) output = -1*output;
        }
        return output;
    }


    /// https://leetcode.com/problems/divide-two-integers/
    private static int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        boolean isNeg = false;
        if (dividend < 0){
            isNeg = !isNeg;
            dividend = 0-dividend;
        }
        if (divisor < 0){
            isNeg = !isNeg;
            divisor = 0-divisor;
        }
        int i = 0;
        while (dividend - divisor >= 0){
            dividend = dividend - divisor;
            if (isNeg){
                if (Integer.MIN_VALUE+1 < i) i++;
                else return Integer.MIN_VALUE;
            }else{
                if (Integer.MAX_VALUE-1 >= i)i++;
                else return Integer.MAX_VALUE;
            }
        }
        return isNeg ? 0-i : i;
    }


    /// https://leetcode.com/problems/swap-nodes-in-pairs/
    private static ListNode swapPairs(ListNode head) { //TODO
        if (head == null || head.next == null) return head;
        ListNode first = head;
        ListNode prevHead = head;
        ListNode second = head.next;


        while (second != null){
            ListNode temp = second.next;
            second.next = first;
            first.next = temp;
//            prevHead = /
        }
        return head;
    }


    //// https://leetcode.com/problems/4sum/
    private static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> output = new ArrayList<>();
        if (nums == null || nums.length == 0) return output;
        Arrays.sort(nums);

        int l1 = 0;int l2=0;
        int h1 = nums.length-1, h2=h1;

        for (; l1 < h2; l1++) {
            for (; h1 > l2; h1--) {

                if (l1 < h1 - 1) l2 = l1 + 1;
                else break;
                if (h1 > l1 + 1) h2 = h1 - 1;
                else break;
                while (l2 < h2) {
                    int sum = nums[l1] + nums[h1] + nums[l2] + nums[h2];
                    if (sum < target) l2++;
                    else if (sum > target) h2--;
                    else {
                        output.add(Arrays.asList(new Integer[]{nums[l1], nums[l2], nums[h2], nums[h1]}));
                        l2++;
                    }
                }

            }
        }

        return output;
    }


    /// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    private static List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }

    private static List<String> letterCombinations2(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");

        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            int len = ans.size();
            for (int j = 0; j < len; j++) {
                for(char s : mapping[x].toCharArray()){
                    ans.add(ans.get(0)+s);
                }
                ans.remove(0);
            }

        }
        return ans;
    }

    /// https://leetcode.com/problems/string-to-integer-atoi/
    private static int myAtoi(String str) {
        int output = 0;
        int multiplier = 0;
        boolean negative = false;
        if (str == null ) return output;

        str = str.trim();
        if (str.length() == 0) return output;

        int i = 0;
        char f = str.charAt(0);
        if (f=='-'){
            multiplier = -1;
            i = 1; //skip the first char
        }else if (f == '+'){
            multiplier = 1;
            i = 1; //skip the first char
        }else {
            multiplier = 1;
            i = 0; //do not skip the first char
        }

        for (; i < str.length(); i++) {
            char x = str.charAt(i);

            int val = 0;
            switch (x){
                case '1':
                    val = 1;break;
                case '2':
                    val = 2;break;
                case '3':
                    val = 3;break;
                case '4':
                    val = 4;break;
                case '5':
                    val = 5;break;
                case '6':
                    val = 6;break;
                case '7':
                    val = 7;break;
                case '8':
                    val = 8;break;
                case '9':
                    val = 9;break;
                case '0':
                    val = 0;break;
                default: return output*multiplier;
            }
            if (multiplier == 1 || multiplier ==0){
                if (Integer.MAX_VALUE/10 - output > val){
                    output = (output * 10) + val;
                }else if (Integer.MAX_VALUE/10 - output == 0){
                    if (Integer.MAX_VALUE%10 > val){
                        output = (output * 10) + val;
                    }else {
                        return Integer.MAX_VALUE;
                    }
                }
                else {
                    return Integer.MAX_VALUE;
                }
            }else {
                if (Integer.MIN_VALUE/10 + output < multiplier*val){
                    output = (output * 10) + val;
                }else if (Integer.MIN_VALUE/10 + output == 0){
                    if (Integer.MIN_VALUE%10 < multiplier*val){
                        output = (output * 10) + val;
                    }else {
                        return Integer.MIN_VALUE;
                    }
                }else {
                    return Integer.MIN_VALUE;
                }
            }
        }
        return output*multiplier;
    }



    /// https://leetcode.com/problems/zigzag-conversion/
    private static String convertToZigZag(String s, int numRows) { // TODO
        StringBuilder sb = new StringBuilder();
        char[][] matrix = new char[numRows][s.length()];
        int idx = 0;
        boolean done = false; // to stop when all characters are written in matrix

        boolean zigzag = false;
        int zigzagIdx = numRows-2;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i < matrix.length; i++) {

                if (zigzag){
                    matrix[zigzagIdx--][j] = s.charAt(idx++);
                    if (idx == s.length()) done = true;
                    break;
                }else {
                    matrix[i][j] = s.charAt(idx++);
                }
                if (idx == s.length()) {
                    done = true;
                    break;
                }
            }
            if (done) break;
            if (zigzagIdx <= 0 || zigzagIdx >= numRows-2){
                zigzag = !zigzag;
                zigzagIdx = numRows-2;
            }

        }


        return sb.toString();
    }


    /// https://leetcode.com/problems/longest-palindromic-substring/
    private static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        String longest = s.substring(0,1);
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length()-1; j > i ; j--) {
                String sub = s.substring(i, j+1);
                if (isPalindrome(sub)){
                    if (longest.length() < sub.length())
                        longest = sub;
                }
            }
        }
        return longest;
    }

    private static String longestPalindrome2(String s) {
        if (s == null || s.length() <= 1) return s;
        String longest = s.substring(0,1);
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+longest.length(); j < s.length(); j++) {
                String sub = s.substring(i, j+1);
                if (isPalindrome(sub)){
                    longest = sub;
                }
            }
        }
        return longest;
    }

    private static boolean isPalindrome(String sub) {
        int i = 0, j= sub.length()-1;
        while (i<=j){
            if (sub.charAt(i) == sub.charAt(j)){
                i++;j--;
            }else {
                return false;
            }
        }
        return true;
    }


    /// https://leetcode.com/problems/add-two-numbers/
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carryOver = 0;
        ListNode output = new ListNode(0);
        ListNode temp = output;
        while (l1 != null || l2 != null || carryOver > 0){
            temp.next = new ListNode(0);
            temp = temp.next;
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carryOver;
            temp.val = sum%10;
            carryOver = sum/10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return output.next;
    }


    /// https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/
    private static List<Integer> pathInZigZagTree(int label) {
        List<Integer> output = new ArrayList<>();
        if (label <= 0)
            return output;
        int mark = 1;
        int row = 1;
        int[] one = new int[]{mark++};
        row++;
        List list = new ArrayList<>();
        list.add(one);

        while (mark <= label){
            int[] ary = new int[(row-1)*2];
            for (int i = 0; i < ary.length; i++) {
                ary[i] = (mark > label ? 0 : mark++);
            }
            list.add(ary);
            row++;
        }

        return null;
    }


    ///https://leetcode.com/problems/ugly-number-ii/
    private static int nthUglyNumber(int n) {
        if (n < 1) return 0;
        if (n < 7) return n;
        int cnt = 6;
        int nth = 0;
        for (int i = 8; i < Integer.MAX_VALUE; i++) {
            if (isUgly(i)){
                cnt++;
                if(cnt >= n) {nth = i; break;}
            }
        }
        return nth;
    }


    /// https://leetcode.com/problems/ugly-number/
    private static boolean isUgly(int num) {
        if (num < 1) return false;
        while(num%2 == 0){
            num = num/2;
        }
        while(num%3 == 0){
            num = num/3;
        }
        while(num%5 == 0){
            num = num/5;
        }
        if (Math.abs(num) <= 5) return true;
        else return false;

    }


    /// https://leetcode.com/problems/count-primes/
    private static int countPrimes(int n) {
        if (n<=2) return 0;
        int primes = 1;
        for (int i = 3; i < n; i++) {
            boolean isPrime = isPrime(i);
            if (isPrime) primes++;
        }
        return primes;
    }

    private static boolean isPrime(int num){
        if (num < 2) return false;
        for (int i = 2; i <= num; i++) {
            if (i*i <= num){
                if (num%i == 0) return false;
            }else break;

        }
        return true;
    }

    private static String makeBiggestNoPossible(int[] ary){
        if (ary == null || ary.length == 0) return "0";
        int[] result = new int[ary.length];

        int size = 0;
        for (int i = 0; i < ary.length; i++) {
            for (int j = 0; j <= size; j++) {
                int x = ary[i];
                int y = result[j];
                if (x < 10 && y < 10){
                    if (x > y){//insert
                        result = insertInToArray(result, j, x);
                        size++;
                        break;
                    }else{
                        continue;
                    }
                }else{
                    int a1 = concatenateIntegers(x, y);
                    int a2 = concatenateIntegers(y, x);
                    if (a1 > a2){//insert
                        result = insertInToArray(result, j, x);
                        size++;
                        break;
                    }else{
                        continue;
                    }
                }
            }
        }
        return Arrays.toString(result);
    }
    //inserts an integer into an array at a given position.
    private static int[] insertInToArray(int[] a, int pos, int num) {
        int[] result = new int[a.length];
        for(int i = 0; i < pos; i++)
            result[i] = a[i];
        result[pos] = num;
        for(int i = pos + 1; i < a.length; i++)
            result[i] = a[i - 1];
        return result;
    }

    //x=109, y=53, output = 10953
    private static int concatenateIntegers(int x, int y){
        if (x == 0) return y;
        if (y == 0) return x*10 + y;
        int temp = y;
        while(temp > 0){
            temp /= 10;
            x*=10;
        }
        return x+y;
    }

    private static String largestNumber(int[] nums) {
        String[] input = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            input[i] = ""+nums[i];
        }
        return createBiggestInteger(input);
    }

    private static String createBiggestInteger(String[] input){
        Arrays.sort(input, new Comparator<String>() {
            @Override
            public int compare(String first, String second) {
                if (first == null || first.equals(second))
                    return 0;

                int i=0, j=0;
                while(i<first.length() && j<second.length()){
                    char f = first.charAt(i);
                    char s = second.charAt(j);
                    if (f > s) return 1;
                    else if(f < s) return -1;
                    else{
                        if (i == first.length()-1 && j == second.length()-1) return 0;
                        else if (i == first.length()-1 && j < second.length()-1) {i=0;j++;continue;}
                        else if (i < first.length()-1 && j == second.length()-1) {i++;j=0;continue;}
                        else {i++;j++;}
                    }
                }
                return 0;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = input.length-1; i >= 0; i--) {
            sb.append(input[i]);
        }
        while (sb.length() >= 2 && sb.charAt(0)=='0'){
            sb.deleteCharAt(0);
        }
        return sb.toString();
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

        @Override
        public String toString() {
            return val +" > " + next ;
        }
    }

    //// this is copied form leetcode
    private static ListNode createLinkedList(int[] nodeValues){
        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private static TreeNode createTreeNode(String input) {
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


    public abstract class BinaryMatrix{
        public abstract int get(int x, int y);
        public abstract List<Integer> dimensions();
    }
}
