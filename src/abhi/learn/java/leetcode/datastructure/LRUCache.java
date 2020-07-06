package abhi.learn.java.leetcode.datastructure;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Abhishek on 4/24/2020.
 */
public class LRUCache {
    int capacity;
    Map<Integer, Integer> storage;
    Map<Integer, Long> lru;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        storage = new HashMap<>(capacity);
        lru = new LinkedHashMap<>(capacity);
    }

    public int get(int key) {
        if(storage.containsKey(key)){
            lru.put(key, System.nanoTime());
            return storage.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (!storage.containsKey(key) && storage.size() == capacity){
            Integer lruKey = findLRUKey();
            storage.remove(lruKey);
            lru.remove(lruKey);
        }
        storage.put(key, value);
        lru.put(key,System.nanoTime());
    }

    private Integer findLRUKey() {
        long cnt = Long.MAX_VALUE;
        Integer lruKey = -1;
        for (Integer key: lru.keySet()) {
            if (lru.get(key) < cnt){
                lruKey = key;
                cnt = lru.get(key);
            }
        }
        return lruKey;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */