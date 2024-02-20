package abhi.learn.java.leetcode.easy;

import abhi.learn.java.leetcode.tree.TreeMain;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by abhi on 2/3/2019.
 */
public class EasyMain2 {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

//        char[][] dictionary = new char[][]{{'A','B','C','A', 'R'},{'S','F','C','M','U'},{'A','D','E','Y','E'},{'A','X','E','Z','E'}};
//        int[][] matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
//        int[][] matrix = new int[][]{{1,3,5,7,9},{2,4,6,8,10},{11,13,15,17,19},{12,14,16,18,20},{21,22,23,24,25}};
//        int[][] matrix = new int[][]{{-1,3}};
//        Object output = isAnagram2("anagram","aganram");


        Object output = totalMoney(4);
        System.out.println("output = " + output);
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }

    /// https://leetcode.com/problems/calculate-money-in-leetcode-bank/
    public static int totalMoney(int n) {
        int sum = 0; int week = 0;
        for (int i = 1; i <= n; i++) {
            if ( (i-1)%7 == 0) week++;
            sum = sum + (week + (i-1)%7);

        }
        return sum;
    }


    /// https://leetcode.com/problems/path-crossing/description/
    public static boolean isPathCrossing(String path) {
        int[] coordinates = new int[]{0,0};
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        for (int i = 0; i < path.length(); i++) {
            char x = path.charAt(i);
            switch (x){
                case 'N': coordinates[1]++; break;
                case 'S': coordinates[1]--; break;
                case 'E': coordinates[0]++; break;
                case 'W': coordinates[0]--; break;
            }
            int val = coordinates[0]*10000 + coordinates[1];
            if (visited.contains(val))
                return true;
            else visited.add(val);
        }
        return false;
    }

    /// https://leetcode.com/problems/array-partition/
    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i=i+2) {
            sum += nums[i];
        }
        return sum;
    }


    ///https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount/
    public static int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int minCost = 0;
        for (int i = cost.length-1; i >=0 ; i--) {
            if ((cost.length-i)%3 != 0){
                minCost += cost[i];
            }
        }
        return minCost;
    }

    /// https://leetcode.com/problems/longest-even-odd-subarray-with-threshold/
    public static int longestAlternatingSubarray(int[] nums, int threshold) {
        int len = 0; int longest = 0; boolean nextOneEven = true;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > threshold){
                if (len > longest) longest = len;
                len = 0;
                nextOneEven = true;
                continue;
            }
            if (nextOneEven){
                if (nums[i]%2 == 0) {
                    len++;
                    nextOneEven = !nextOneEven;
                }
                else{
                    len = 0;
                    nextOneEven = true;
                }
            }else {//nextoneOdd
                if (nums[i]%2 == 1) {
                    len++;
                    nextOneEven = !nextOneEven;
                }
                else{
                    len = 1;
                    nextOneEven = false;
                }
            }
            if (len > longest) longest = len;
        }
        return longest;
    }

    /// https://leetcode.com/problems/plus-one/
    public static int[] plusOne(int[] digits) {
        if(digits == null || digits.length == 0) return new int[0];

        int carryOver = 1;
        int sum = 0;
        for (int i = digits.length-1; i >=0 ; i--) {
            sum = digits[i]+carryOver;
            if (sum > 9) {
                carryOver = 1;
                digits[i] = sum%10;
            }
            else {
                carryOver = 0;
                digits[i] = sum;
            }
        }
        if (carryOver > 0){
            int[] retVal = new int[1+digits.length];
            retVal[0] = carryOver;
            for (int i = 1; i < retVal.length; i++) {
                retVal[i] = digits[i-1];
            }
            return retVal;
        }else return digits;

    }

    /// https://leetcode.com/problems/merge-two-sorted-lists/
    public static ListNode mergeTwoListsII(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode res = l1.val <= l2.val ? l1 : l2;
        if (l1.val <= l2.val){
            res = l1;
            l1 = l1.next;
        }else {
            res = l2;
            l2 = l2.next;
        }
        ListNode temp = res;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                res.next = l1;
                l1 = l1.next;
            }else {
                res.next = l2;
                l2 = l2.next;
            }
            res = res.next;
        }
        if (l1 != null)
            res.next = l1;
        if (l2 != null)
            res.next = l2;
        return temp;
    }


    /// https://leetcode.com/problems/squares-of-a-sorted-array/
    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int[] result = new int[nums.length]; int idx = nums.length-1;
        int i = 0, j = nums.length-1;
        while (i<=j){
            int sqI = nums[i]*nums[i];
            int sqJ = nums[j]*nums[j];

            if (sqI >= sqJ){
                result[idx--] = sqI;
                i++;
            }else{
                result[idx--] = sqJ;
                j--;
            }
        }
        return result;
    }


    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int x: nums) {
            if (!set.add(x))
                return true;
        }
        return false;
    }


    /// https://leetcode.com/problems/intersection-of-two-arrays/
    public static int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> set1 = new HashSet<>();
        for (int x: nums1) {
            set1.add(x);
        }
        HashSet<Integer> results = new HashSet<>();
        for (int x: nums2) {
            if (set1.contains(x))
                results.add(x);
        }

        int[] res = new int[results.size()];
        Iterator itr = results.iterator();
        int i = 0;
        while (itr.hasNext()){
            res[i++] = (int) itr.next();
        }
        return res;
    }


    ///https://leetcode.com/problems/valid-anagram/
    private static boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length())
            return false;

        HashMap<Character, Integer> first = new HashMap<>();
        HashMap<Character, Integer> second = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            first.put(s.charAt(i), 1+first.getOrDefault(s.charAt(i),0));
            second.put(t.charAt(i), 1+second.getOrDefault(t.charAt(i),0));
        }
        if (first.size() != second.size()) return false;

        for (Character x: first.keySet()) {
            if (!first.get(x).equals(second.get(x))) return false;
        }
        return true;
    }

    ///https://leetcode.com/problems/valid-anagram/
    private static boolean isAnagram2(String s, String t) {
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

    private static int binarySearchArray2(int[] nums, int target){
        if (nums == null || nums.length == 0){
            return -1;
        }
        if (nums.length == 1 && nums[0] == target) return 0;
        int i = 0; int j = nums.length-1;
        int mid = -1;
        while (i<=j){
            mid = (i+j)/2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target){
                i = mid + 1;
            }else {
                j = mid;
            }
        }
        return -1;
    }


        /// https://leetcode.com/problems/search-a-2d-matrix-ii/
    private static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        if (target < matrix[0][0] || target > matrix[matrix.length-1][matrix[0].length-1]){
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;

        while (row < matrix.length && col >= 0){
            int num = matrix[row][col];
            if (num < target){
                row++;
            }else if(num > target){
                col--;
            }else{
                return true;
            }
        }

        return false;
    }

    /// https://leetcode.com/problems/search-a-2d-matrix-ii/
    private static boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        if (target < matrix[0][0] || target > matrix[matrix.length-1][matrix[0].length-1]){
            return false;
        }
        int col = 0;
        int row = matrix.length-1;

        while (row >= 0 && col < matrix[0].length){
            int num = matrix[row][col];
            if (num < target){
                col++;
            }else if(num > target){
                row--;
            }else{
                return true;
            }
        }
        return false;
    }

    private static boolean binarySearchArray(int[] nums, int target){
        if (nums == null || nums.length == 0) return false;

        int low=0,high=nums.length;

        while (low <= high){
            int mid = low + (high-low)/2;
            int key = nums[mid];

            if (key == target){
                return true;
            }else if(key < target){
                low = mid+1;
            }else {
                high = mid-1;
            }
        }
        return false;
    }


    private static boolean wordSearchDiag(char[][] board, String word) {

        int idx = 0;
        char x = word.charAt(idx);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char y = board[i][j];
                if (x != y){
                    idx = 0;
                    continue;
                }else{
                    int i1 = i;
                    int j1 = j;
                    int idx1 = idx;
                    while ((i1 < board.length && j1 < board[0].length) && x==y){
                        if (idx1 == word.length()-1) return true;
                        x = word.charAt(idx1++);
                        y = board[i1++][j1++];
                    }
                }
            }
        }
        return false;
    }


    private static boolean wordSearchVertical(char[][] board, String word) {
        int idx = 0;
        char x = word.charAt(idx);
        for (int i = 0; i < board[0].length; i++) {
            idx=0;
            for (int k = 0; k < board.length; k++) {
                x = word.charAt(idx);
                char y = board[k][i];
                if (x != y){
                    idx=0;
                    continue;
                }
                else {
                    if (idx== word.length()-1) return true;
                    else idx++;
                }
            }
        }
        return false;

    }

    /// word search problem
    private static boolean wordSearchHorizantal(char[][] board, String word) {
        int idx = 0;
        char x = word.charAt(idx);
        for (int k = 0; k < board.length; k++) {
            idx=0;
            for (int i = 0; i < board[0].length; i++) {
                x = word.charAt(idx);
                char y = board[k][i];
                if (x != y){
                    idx=0;
                    continue;
                }
                else {
                    if (idx== word.length()-1) return true;
                    else idx++;
                }
            }
        }
        return false;
    }


    //// https://leetcode.com/problems/3sum-smaller/
    private static int threeSumSmaller(int[] nums, int target) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            int start = i+1;
            int end = nums.length-1;
            while (start<end){
                int sum = nums[i]+nums[start]+nums[end];
                if (sum<target){
                    count++;
                    end--;
                }else{
                    start++;
                }
            }
        }
        return count;
    }


    //// https://leetcode.com/problems/3sum-closest/
    private static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        Arrays.sort(nums);
        int prevDiff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {

            int start = i+1, end=nums.length-1;
            while (start<end){
                int currSum = nums[i]+nums[start]+nums[end];
                int modDiff = (target > currSum ? target-currSum : currSum-target);
                int diff = target-currSum;
                if (modDiff == 0) return currSum;
                if (modDiff < prevDiff){
                    sum = currSum;
                    prevDiff = modDiff;
                }
                if (currSum > target){
                    end--;
                }else{
                    start++;
                }
            }
        }

        return sum;
    }

    /// https://leetcode.com/problems/3sum/
    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        if (nums == null || nums.length == 0) return output;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[i]+nums[start]+nums[end];
                if (sum == 0){
                    output.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    while (start<end && nums[start]==nums[start+1]) start++;
                    while (end>start && nums[end]==nums[end-1]) end--;
                    start++;end--;
                }else if (sum < 0){
                    start++;
                }else {
                    end--;
                }
            }
        }
        return output;
    }

    /// https://leetcode.com/problems/reverse-words-in-a-string-ii/
    private static void reverseWords(char[] s) {
        if (s == null || s.length==0) return;
        //first reverse the entire string,
        int i=0,j=s.length-1;
        while (i<j){
            char x = s[i];
            s[i]=s[j];
            s[j]=x;
            i++;j--;
        }
        int start = 0;
        for (int k = 0; k < s.length; k++) {
            if (s[k] == ' '){
                i=start;j=k-1;
                while (i<j){
                    char x = s[i];
                    s[i]=s[j];
                    s[j]=x;
                    i++;j--;
                }
                start=k+1;
            }
        }

        i=start;j=s.length-1;
        while (i<j){
            char x = s[i];
            s[i]=s[j];
            s[j]=x;
            i++;j--;
        }

    }


    /// https://leetcode.com/problems/integer-to-roman/
    private static String intToRoman(int num) {

        int copy = num;
        int pow = -1;
        while (copy > 0){
            copy = copy/10;
            pow++;
        }
        char[] numerals = new char[]{'I','V','X','L','C','D','M','A','B'};
        int[] values = new int[]{1,5,10,50,100,500,1000,5000,10000};
        LinkedHashMap<Integer, String> map = new LinkedHashMap();
        map.put(1, "I");map.put(5, "V");map.put(10, "X");map.put(50, "L");map.put(100, "C");
        map.put(500, "D");map.put(1000, "M");map.put(5000, "A");map.put(10000, "B");
        StringBuilder sb = new StringBuilder("");

        while (num > 0){
            int denom = (int)Math.pow(10,pow);
            int digit = (num/denom) * denom;
//            String



//            num = num - digit;
            pow--;
        }


        return sb.toString();
    }



    private static String intToRoman2(int num) {
        char[] numerals = new char[]{'I','V','X','L','C','D','M','A','B'};
        int[] values = new int[]{1,5,10,50,100,500,1000,5000,10000};
        LinkedHashMap<Integer, String> map = new LinkedHashMap();
        map.put(1, "I");map.put(5, "V");map.put(10, "X");map.put(50, "L");map.put(100, "C");
        map.put(500, "D");map.put(1000, "M");map.put(5000, "A");map.put(10000, "B");

        StringBuilder sb = new StringBuilder("");

        int idx = 0;
        int denom = 10;
        while (num > 0){
            int digit = num%denom;
            num = num - digit;
            if (digit == values[idx+2]-values[idx]){
                sb.append(numerals[idx+2]);sb.append(numerals[idx]);
            }else if (digit == values[idx+1]-values[idx]){
                sb.append(numerals[idx+1]);sb.append(numerals[idx]);
            }else {
                for (int i = 0; i < digit%values[idx+1]; i++) {
                    sb.append(numerals[idx]);
                }
                for (int i = 0; i < digit / values[idx + 1]; i++) {
                    sb.append(numerals[idx+1]);
                }
            }
            idx++;
            denom *= 10;
        }
        return sb.reverse().toString();
    }


    /// https://leetcode.com/problems/longest-common-prefix/
    private static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        StringBuilder sb = new StringBuilder("");
        boolean done = false;
        String first = strs[0];
        for (int i = 0; i < first.length(); i++) {
            char f = first.charAt(i);
            for (int j = 1; j < strs.length; j++) {

                if (i > strs[j].length()-1 || strs[j].charAt(i) != f){
                    done = true;
                    break;
                }
            }
            if (done) break;
            else sb.append(f);
        }
        return sb.toString();
    }


    /// https://leetcode.com/problems/palindrome-number/
    private static boolean isPalindrome2(int x) {
        if (x < 0) return false;
        if (x < 10) return true;

        int rev = 0;
        int y = x;
        while (y > 0){
            rev = rev*10 + y%10;
            y = y/10;
        }
        return x == rev;
    }



    private static boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int y = x;
        int len = 0;
        while (y>0){
            len++;
            y=(y/10);
        }
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = x%10;
            x = x/10;
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] != nums[len-1-i])
                return false;
        }
        return true;
    }

    /// https://leetcode.com/problems/sqrtx/
    private static int mySqrt(int x) {
        long previous = 1;
        if (x <= 1) return x;
        for (long i = 1; i <= (x / 2); i++) {
            if (i*i > x)
                return (int)previous;
            else
                previous = i;
        }
        return (int)previous;
    }


    ////https://leetcode.com/problems/valid-mountain-array/
    private static boolean validMountainArray(int[] A) {
        if (A.length<=2) return false;
        int prev = A[0];
        int curr = A[0];
        boolean goingUp = A[0] <= A[1];
        int hills = 0;
        for (int i = 0; i < A.length; i++) {
            curr = A[i];
            if (i !=0 && curr == prev) return false;
            if (goingUp){
                if (prev > curr){
                    goingUp = false;
                    hills++;
                }
            }else {
                if (prev < curr){
                    return false;
                }
            }
            prev = curr;
        }
        return hills == 1;

    }

    ///https://leetcode.com/problems/can-place-flowers/
    private static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int cnt = 0;
        if (flowerbed.length == 1){
            return (n==0 || (flowerbed[0]==0 && n==1));
        }
        int prev = 0;
        int curr = 0;
        int next = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            curr = flowerbed[i];
            next = (i+1==flowerbed.length ? 0 : flowerbed[i+1]);
            if (prev==0 && curr==0 && next==0){
                cnt++;
                flowerbed[i]=1;
            }
            prev = flowerbed[i];
        }

        return (cnt >= n);
    }

    ////https://leetcode.com/problems/k-diff-pairs-in-an-array/
    private static int findPairs(int[] nums, int k) {
        if(k < 0) return 0;
        int count = 0;
        LinkedHashMap<Integer, Boolean> map = new LinkedHashMap<>();
        for (int x: nums) {
            map.put(x, false);
        }
        int cnt = 0;
        Set<Integer> keys = map.keySet();
        if (k==0){
            return nums.length-keys.size();
        }
        for (Integer key: keys) {
            if (map.get(key+k) != null && !map.get(key+k)){
                count++;
                map.put(key, true);map.put(key+k, true);
            }
            if (map.get(key-k) != null && !map.get(key-k)){
                count++;
                map.put(key, true);map.put(key-k, true);
            }
        }
        return count;
    }


    //// https://leetcode.com/problems/third-maximum-number/
    private static int thirdMax(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2){
            if (nums[1] > nums[0]) return nums[1];
                else return nums[0];
        }
        Integer max1=null, max2=null, max3=null;

        for (Integer x: nums) {
            if (max1 == null || x.intValue() >= max1.intValue()){
                if (max1 != null && x.intValue() == max1.intValue()) continue;
                max3 = max2;
                max2 = max1;
                max1 = x;
            }else if(max2 == null || x.intValue() >= max2.intValue()){
                if (max2 != null && x.intValue() == max2.intValue()) continue;
                max3 = max2;
                max2 = x;
            }else if (max3 == null || x.intValue() >= max3.intValue()){
                if (max3 != null && x.intValue() == max3.intValue()) continue;
                max3 = x;
            }
        }
        if (max3 != null){
            return max3;
        }else{
            return max1;
        }
    }


    /////    https://leetcode.com/problems/product-of-array-except-self/
    private static int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return nums;

        int[] out = new int[nums.length];
        boolean has0 = false;
        int cnt0 = 0;
        int multi = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0){
                has0 = true;
                cnt0++;
            }
            else
                multi = multi*nums[i];
        }
        if (cnt0 > 1) return out;
        for (int i = 0; i < nums.length; i++) {
            if (has0){
                if (nums[i] != 0) out[i] = 0;
                else out[i] = multi;
            }else {
                out[i] = multi/nums[i];
            }
        }
        return out;
    }

    //// https://leetcode.com/problems/fixed-point/
    private static int fixedPoint(int[] A){
        for (int i = 0; i < A.length; i++) {
            if (A[i] == i) return i;
        }
        return -1;
    }

    ////https://leetcode.com/problems/delete-node-in-a-linked-list/
    private static void deleteNode(ListNode node) {
        if (node == null)
            return;
        if (node.next == null){
            node = null;return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }


    ///https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    private static ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr!= null){
            if (curr.next != null && curr.val == curr.next.val){
                curr.next=curr.next.next;continue;
            }
            curr = curr.next;
        }

        return head;
    }

    ////https://leetcode.com/problems/remove-linked-list-elements/
    private static ListNode removeElements(ListNode head, int val) {
        ListNode output = head;
        ListNode prev = head;
        while (head != null){
            if (head.val == val){
                if (output == head){
                    output = head.next;
                    prev = head.next;
                    head = head.next;
                }else {
                    prev.next = head.next;
                    head=head.next;
                }
                continue;
            }
            prev = head;
            head = head.next;
        }
        return output;
    }

    ////https://leetcode.com/problems/buddy-strings/
    private static boolean buddyStrings(String A, String B) {
        int count = 0;
        if (A == null || B == null){
            return false;
        }else if (A.length() != B.length())
            return false;
        else if(A.length() <= 1){
            return false;
        }else if (A.equals(B)){//if they r equal, we can still swap 1 char, if any char is repeated...
            for (int i = 0; i < A.length(); i++) {
                for (int j = i+1; j < A.length(); j++) {
                    if (A.charAt(i) == A.charAt(j))
                        return true;
                }
            }
            return false;
        }
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
        curr = null;
        return out;
    }
}
