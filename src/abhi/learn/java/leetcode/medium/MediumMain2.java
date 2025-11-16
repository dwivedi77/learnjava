package abhi.learn.java.leetcode.medium;

import java.util.*;

public class MediumMain2 {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        MediumMain2 main = new MediumMain2();
        Object output = main.maxProduct(new int[]{2,3,-2,4});
//
        System.out.println("Answer="+output);

        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }

    ///  https://leetcode.com/problems/maximum-product-subarray/description/
    public int maxProduct(int[] nums) {
        if (nums.length == 1) return nums[0];
        int maxProduct = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int product = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                int y = nums[j];
                product *= y;
                if (product > maxProduct){
                    maxProduct = product;
                }
            }
        }
        return maxProduct;
    }



    /// https://leetcode.com/problems/top-k-frequent-elements/description/
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], 1 + countMap.getOrDefault(nums[i], 0));
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return ((Integer)o2[1]).compareTo(o1[1]);
            }
        });

        for (Integer key: countMap.keySet()) {
            queue.add(new int[]{key, countMap.get(key)});
        }

        int[] out = new int[k];
        for (int i = 0; i < k; i++) {
            out[i] = queue.poll()[0];

        }
        return out;
    }


    /// https://leetcode.com/problems/find-pattern-in-infinite-stream-i/description/
    public int findPatternTest(InfiniteStream data, int[] pattern) {
        int matchStartIdx = -1;
        int nextVal = -1;
        boolean getNext = true;
        int streamCnt = 0;
        for (int i = 0; i < pattern.length; ) {
            if (getNext) {
                nextVal = data.next();
                streamCnt++;
            }

            if(nextVal == pattern[i]){
                matchStartIdx = (matchStartIdx == -1 ? streamCnt-1 : matchStartIdx);
                i++;
                getNext = true;
            }else {
                matchStartIdx = -1;
                i = 0;
                getNext = !getNext;
            }
        }
        return matchStartIdx;
    }
    public int findPattern2(InfiniteStream infiniteStream, int[] pattern) {
        int matchStartIdx = -1;
        int nextVal = -1;
        boolean getNext = true;
        int streamCnt = 1;
        for (int i = 0; i < pattern.length; ) {
            if (getNext) {
                nextVal = infiniteStream.next();
                streamCnt++;
            }

            if(nextVal == pattern[i]){
                matchStartIdx = (matchStartIdx == -1 ? streamCnt-1 : matchStartIdx);
                i++;
                getNext = true;
            }else {
                matchStartIdx = -1;
                i = 0;
                getNext = false;
            }
        }
        return matchStartIdx;
    }

    /// https://leetcode.com/problems/additive-number/description/
    public boolean isAdditiveNumber(String num) { /// TODO
        return false;
    }

    public void isAdditiveNumber(String num, int sum, int first, int second, int idx) {
        if (first + second == sum){
            return;
//            if (idx == 0) return;
        }
        if (first + second < sum){

        }
        if (first + second > sum){
            
        }
    }


    /// https://leetcode.com/problems/factor-combinations/description/
    public List<List<Integer>> getFactors(int n) { // TODO
        List<List<Integer>> output = new ArrayList<>();
        if (n<2) return output;
        List<Integer> temp = new ArrayList<>();
        getFactorsHelper(n, n, temp,output, new HashSet<>());
        return output;
    }

    public void getFactorsHelper(int n, int dividend, List<Integer> temp, List<List<Integer>> output, Set<Integer> keys){
        if (dividend <= 1){
            int key = 0;
            for (int x: temp) {
                key += x*x;
            }
            if (!keys.contains(key)){
                output.add(new ArrayList<>(temp));
                keys.add(key);
            }
            return;
        }
        for (int i = 2; i <= n/2; i++) {
            if (dividend%i == 0){
                temp.add(i);
                getFactorsHelper(n, dividend/i, temp, output, keys);
                temp.remove(temp.size()-1);
            }
        }
    }

    /// https://leetcode.com/problems/combination-sum-iii/description/
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        combinationSum3Helper(k, n, 1, temp, output);
        return output;
    }

    public void combinationSum3Helper(int k, int n, int curr, List<Integer> temp, List<List<Integer>> output) {
        if (n == 0 && temp.size() == k){
            output.add(new ArrayList<>(temp));
            return;
        } else if (n < 0 || k < temp.size()) {
            return;
        }

        for (int i = curr; i <= 9; i++) {
            temp.add(i);
            combinationSum3Helper(k, n-i, i+1, temp, output);
            temp.remove(temp.size()-1);
        }
    }

    /// https://leetcode.com/problems/palindrome-partitioning/
    public List<List<String>> partition(String s) {
        List<List<String>> output = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        partitionHelper(s, temp, output);
        return output;
    }

    public void partitionHelper(String s, List<String> temp, List<List<String>> output) {
        if (s.length() == 0){
            output.add(new ArrayList<>(temp));
        }

        for (int i = 1; i <= s.length(); i++) {
            String subStr = s.substring(0, i);
            if (isPalindrome(subStr)){
                temp.add(subStr);
                partitionHelper(s.substring(i, s.length()), temp, output);
                temp.remove(temp.size()-1);
            }
        }
    }
    
    private boolean isPalindrome(String sub) {
        int i = 0, j = sub.length() - 1;
        while (i <= j) {
            if (sub.charAt(i) == sub.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    /// https://leetcode.com/problems/permutations-ii/   TODO
    private static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        permuteUniqueHelper(nums, used, new ArrayList<Integer>(), output, new StringBuilder(), new HashSet<>());
        return output;
    }

    private static void permuteUniqueHelper(int[] nums, boolean[] used, List<Integer> tempList, List<List<Integer>> output, StringBuilder sb, Set<String> keys) {
        if (tempList.size() == nums.length) {
//            String str = "";
//            for (int x: tempList) {
//                str += "_"+x;
//            }

            if (!keys.contains(sb.toString())){
                keys.add(sb.toString());
                output.add(new ArrayList<>(tempList));
            }
            return;
        } else if (tempList.size() > nums.length) {
            return;
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) continue;
                used[i] = true;
                sb.append("_"+nums[i]);
                tempList.add(nums[i]);
                permuteUniqueHelper(nums, used, tempList, output, sb, keys);
                tempList.remove(tempList.size() - 1);
                used[i] = false;
                sb.delete(sb.lastIndexOf("_"), sb.length());
            }
        }
    }

    /// https://leetcode.com/problems/restore-ip-addresses/description/
    public List<String> restoreIpAddresses(String s) {
        Set<String> out = new HashSet<>();
        List<StringBuilder> oneIP = new ArrayList<>();
        restoreIpAddressesHelper(s, 0, 0, oneIP, out);
        List<String> result = new ArrayList<>(out);
        return result;
    }

    public void restoreIpAddressesHelper(String s, int length, int idx, List<StringBuilder> oneIP, Set<String> result) {
        if (length > s.length()) return;
        if (oneIP.size() == 4){
            if (length == s.length()){
                StringBuilder sb = new StringBuilder();
                for (StringBuilder part: oneIP) {
                    sb.append(part.toString());
                    sb.append(".");
                }
                sb.deleteCharAt(sb.length()-1);
                result.add(sb.toString());
            }else return;
        }
        if (oneIP.size() > 4) return;

        for (int i = idx; i < s.length(); i++) {
            for (int k = 1; k <= 3; k++) {
                if (i+k > s.length()) continue;
                String part = s.substring(i, i+k);
                if (!validIPPart(part) ) continue;
                oneIP.add(new StringBuilder(part));
                length += part.length();
                restoreIpAddressesHelper(s, length, i+k, oneIP, result);
                length = length- (oneIP.remove(oneIP.size()-1)).length();
            }
        }
    }
    public boolean validIPPart(String part){
        if (part.length() > 1 && part.charAt(0) == '0' )
            return false;
        int val = Integer.valueOf(part);
        if (val < 0 || val > 255) return false;

        return true;
    }

    /// https://leetcode.com/problems/combinations/
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        combineHelper(n,k, 1, temp, result);
        return result;
    }

    public void combineHelper(int n, int k, int curr, List<Integer> temp, List<List<Integer>> result) {
        if (temp.size() == k){
            List<Integer> out = new ArrayList<>(temp);
            result.add(out);
            return;
        }
        for (int i = curr; i <= n; i++) {
            temp.add(i);
            combineHelper(n, k, i+1, temp, result);
            temp.remove(temp.size()-1);
        }
    }


    /// https://leetcode.com/problems/combination-sum-ii/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        combinationSum2Helper(candidates, target, 0, temp, result, new HashSet<>());
        return result;
    }

    public void combinationSum2Helper(int[] candidates, int target, int curr, List<Integer> temp, List<List<Integer>> result, Set<String> check) {
        if (target == 0){
            StringBuilder key = new StringBuilder();
            for (int x: temp) {
                key.append(x+"_");
            }
            if (check.contains(key.toString())) return;
            else check.add(key.toString());
            List<Integer> answer = new ArrayList<>(temp);
            result.add(answer);
            return;
        } else if (target < 0) {
            return ;
        }
        for (int i = curr; i < candidates.length; i++) {
            temp.add(candidates[i]);
            combinationSum2Helper(candidates, target - candidates[i], i+1, temp, result, check);
            temp.remove(temp.size()-1);
        }

    }

    /// https://leetcode.com/problems/combination-sum/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        combinationSumHelper(candidates, target, 0, temp, result);
        return result;
    }

    public void combinationSumHelper(int[] candidates, int target, int curr, List<Integer> temp, List<List<Integer>> result) {
        if (target == 0){
            List<Integer> answer = new ArrayList<>(temp);
            result.add(answer);
            return;
        } else if (target < 0) {
            return ;
        }
        for (int i = curr; i < candidates.length; i++) {
            temp.add(candidates[i]);
            combinationSumHelper(candidates, target - candidates[i], i, temp, result);
            temp.remove(temp.size()-1);
        }
    }
}
    class InfiniteStream {
    int[] data = null;
    int index = -1;
    public InfiniteStream(int[] bits){
        data = bits;
        index = 0;
    }
    public int next(){
        return data[index++];
    }
}

