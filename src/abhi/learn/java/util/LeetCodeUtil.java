package abhi.learn.java.util;

import java.util.*;

public class LeetCodeUtil {

    /**
     * Utility method to generate random strings
     */
    private static String generateRandomString(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * Generates a nested Map<String, Map<String, String>> with configurable size and custom prefixes
     *
     * @param outerMapSize Number of entries in the outer map
     * @param innerMapSize Number of entries in each inner map
     * @param outerKeyPrefix Prefix for outer map keys
     * @param innerKeyPrefix Prefix for inner map keys
     * @param valuePrefix Prefix for values
     * @return Map<String, Map<String, String>> populated with test data
     */
    public static Map<String, Map<String, String>> generateNestedMap(
            int outerMapSize,
            int innerMapSize,
            String outerKeyPrefix,
            String innerKeyPrefix,
            String valuePrefix) {

        Map<String, Map<String, String>> outerMap = new HashMap<>();
        for (int i = 0; i < outerMapSize; i++) {
            String outerKey = outerKeyPrefix + "_" + i;
            Map<String, String> innerMap = new HashMap<>();

            for (int j = 0; j < innerMapSize; j++) {
                String innerKey = innerKeyPrefix + "_" + j;
                String value = valuePrefix + "_" + i + "_" + j;
                innerMap.put(innerKey, value);
            }
            outerMap.put(outerKey, innerMap);
        }
        return outerMap;
    }

}
