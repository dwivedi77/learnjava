package abhi.learn.java.leetcode.datastructure;

import java.util.LinkedHashMap;

/**
 * Created by Abhishek on 4/29/2020.
 */
public class FirstUnique {

    LinkedHashMap<Integer, Integer> cache;
    public FirstUnique(int[] nums) {
        cache  = new LinkedHashMap<>();
        if (nums == null || nums.length ==0) return;
        for (int num: nums) {
            cache.put(num, 1 + (cache.get(num) == null ? 0 : cache.get(num)));
        }
    }

    public int showFirstUnique() {
        for (Integer key: cache.keySet()) {
            if (cache.get(key) == 1) return key;
        }
        return -1;
    }

    public void add(int value) {
        cache.put(value, 1 + (cache.get(value) == null ? 0 : cache.get(value)));
    }
}


