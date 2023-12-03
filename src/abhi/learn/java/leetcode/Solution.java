package abhi.learn.java.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

/**
 * Created by Abhishek on 12/29/2018.
 */
public class Solution {

    private static int pick;
    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        pick = 15;
        int result = guessNumber(10);
        System.out.println("result = " + result);
        System.out.println("Time Taken="+(System.currentTimeMillis()-startTime));
        System.out.println("END");
    }

    /// https://leetcode.com/problems/guess-number-higher-or-lower/
    private static int guessNumber(int n) {
        int start = 1, end = n;
        int result = guessNumber(n, 1, n);
        return result;
    }

    private static int guessNumber(int n, int start, int end) {
        int mid = start + (end-start)/2;
        int guess = guess(mid);
        if (guess == 0){
            return mid;
        }else if (guess > 0){
            return guessNumber(n, mid+1, end);
        }else {
            return guessNumber(n, start, mid);
        }
    }

    private static int guess(int num){
        if (num > pick)
            return -1;
        else if (num < pick)
            return 1;
        else return 0;
    }

    ///https://leetcode.com/problems/distant-barcodes/
    private static int[] rearrangeBarcodes(int[] barcodes) {
        int[] out = new int[barcodes.length];
        HashMap<Integer, Integer> master = new HashMap<>();
        for (int barcode: barcodes) {
            if (master.containsKey(barcode)){
                master.put(barcode, master.get(barcode)+1);
            }else {
                master.put(barcode, 1);
            }
        }
        HashMap<Integer, Integer> sorted = master.entrySet().stream().sorted((e1, e2) -> (e2.getValue().compareTo(e1.getValue()))).collect(Collectors.toMap((e -> e.getKey()), e -> e.getValue(), (e2, e1) -> e2, LinkedHashMap::new));

//        Integer curr = null;
//        Integer next = null;
        int idx = 0;
        int keyIdx = 0;
        Object[] keys = sorted.keySet().toArray();
        for (int curr = (int) keys[keyIdx++], next = (int) keys[keyIdx++]; sorted.get(curr) > 0;){
            out[idx++] = curr; sorted.put(curr, sorted.get(curr)-1);
            do{
                next = (int) keys[keyIdx++];
            }while (sorted.get(next) <= 0 || keyIdx > keys.length);
            out[idx++] = next; sorted.put(next, sorted.get(next)-1);
            if (sorted.get(curr) <= 0){
                curr = next;
                next = (keyIdx > keys.length) ? -1 : (int)keys[keyIdx++];
            }
        }

        return out;

    }

}
