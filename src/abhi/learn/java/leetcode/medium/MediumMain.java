package abhi.learn.java.leetcode.medium;

import java.util.*;

/**
 * Created by Abhishek on 3/14/2019.
 */
public class MediumMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        ListNode head = createLinkedList(new int[]{1,2,3,4,5});
        Object output = swapPairs(head);
        System.out.println("Answer="+output);


        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }

    /// https://leetcode.com/problems/swap-nodes-in-pairs/
    private static ListNode swapPairs(ListNode head) {
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


    /// https://leetcode.com/problems/generate-parentheses/
    private static List<String> generateParenthesis(int n) {
        /// TODO
        List<String> output = new ArrayList<>();
        if (n <= 0 ) return output;
        LinkedHashSet<String> values = new LinkedHashSet<>();
        addParenthesisToSet(n, "", values);


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
    private static String convertToZigZag(String s, int numRows) {
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


    /// https://leetcode.com/problems/first-bad-version/
    private static int firstBadVersion(int n) {
        if (n<=1) return n;
        int i=1,j=n, mid=0;

        while (i<j){
            mid=(i+j)/2;
            if (isBadVersion(mid)){
                    if (mid>0 && !isBadVersion(mid-1)){
                        return mid;
                    }else {
                        j=mid;
                    }
            }else {
                i = mid+1;
            }
        }
        return mid;
    }
    static boolean isBadVersion(int version){
        if (version >= 2) return true;
        else return false;
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
}
