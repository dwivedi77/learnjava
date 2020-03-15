package abhi.learn.java.leetcode.medium;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Abhishek on 3/14/2019.
 */
public class MediumMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();


//        Object output = merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
//        Object output = merge(new int[][]{{2,3},{4,5},{6,7},{8,9},{1,10}});
        Object output = getPermutation(3,1);
        System.out.println("Answer="+output);
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
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


    /// https://leetcode.com/problems/merge-intervals/
    private static int[][] merge(int[][] intervals) { /// TODO
        if (intervals == null || intervals.length <= 1) return intervals;

        List<int[]> list = Arrays.asList(intervals).stream().sorted(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] <= o2[0] ? -1 : 1;
            }
        }).collect(Collectors.toList());
//        int[] range
        for (int i = 1; i < list.size(); i++) {
            
        }
        int[][] output = new int[list.size()][];
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


    /// https://leetcode.com/problems/group-anagrams/
    private static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> output = new ArrayList<>();
        if (strs == null || strs.length == 0) return output;

        HashMap<Integer, List<String>> grouped = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            if(grouped.get(strs[i].length()) == null){
                grouped.put(strs[i].length(), new ArrayList<>());
            }
            grouped.get(strs[i].length()).add(strs[i]);
        }

        HashMap<String, List<String>> anagrams = new HashMap<>();
        for (Integer length: grouped.keySet()) {
            String current = "";
            List<String> list = grouped.get(length);
            for (String word: list) {
                if ("".equals(current)){
                    current = word;
                    continue;
                }

            }
        }

        return output;
    }

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


    /// https://leetcode.com/problems/permutations-ii/   TODO
    private static  List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        permuteUniqueHelper(nums, used, new ArrayList<Integer>(), output);
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
                if (used[i]) continue;
                tempList.add(nums[i]);
                used[i] = true;
                permuteUniqueHelper(nums, new boolean[nums.length], tempList, output);
                used[i] = false;
                tempList.remove(tempList.size()-1);
            }
        }
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

    /// https://leetcode.com/problems/search-in-rotated-sorted-array/
    private static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int i = 0, j = nums.length - 1;
        int mid = 0;
        int pivot = 0;

        while (i<j){
            mid = (i+j)/2;
            if (mid < nums.length-1 && nums[mid] > nums[mid+1]){
                pivot = mid+1;
                break;
            }else if (nums[mid] > nums[0]){
                i=mid+1;
            }else{
                j = mid-1;
            }
        }

        if (target < nums[pivot] || (pivot > 0 && target > nums[pivot-1]))
            return -1;
        if (pivot == 0){
            i = 0; j = nums.length - 1;
        } else if (nums[0] <= target && target <= nums[pivot-1]){
            i = 0; j = pivot - 1;
        } else {
            i = pivot; j = nums.length - 1;
        }

        while (i < j){
            mid = (i+j)/2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) i = mid+1;
            else j = mid;
        }

        return -1;
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
