package abhi.learn.java.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Abhishek on 12/29/2018.
 */
public class Solution {

    private static int guess;
    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        MyLinkedList my = new MyLinkedList();
        int zero = my.get(0);
        my.addAtIndex(1,2);
        zero = my.get(0);
        int one = my.get(1);
        my.addAtIndex(0,1);
        zero = my.get(0);
        one = my.get(1);

        System.out.println("Time Taken="+(System.currentTimeMillis()-startTime));
        System.out.println("END");
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

    private static int guessNumber(int n) {
        int x = 0;
        int lower = 1;
        int floor = n;
        int result = floor/2;
        int test = guess(result);
        while (test != 0){
            x++;
            if (test > 0){
                floor = result;
                result = (floor)/2;
                test = guess(result);
            }else if (test < 0){
                lower = result;
                result = lower + (floor - lower)/2;
                test = guess(result);
            }
        }
        System.out.println("Total iteration="+x);
        return result;
    }

    private static int guess(int num){
        if (num < guess)
            return -1;
        else if (num > guess)
            return 1;
        else return 0;
    }
}
