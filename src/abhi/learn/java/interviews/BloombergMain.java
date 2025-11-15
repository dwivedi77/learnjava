package abhi.learn.java.interviews;

import java.util.*;

public class BloombergMain {

    public static void main(String[] args) {
        System.out.println("START");

        BloombergMain main = new BloombergMain();

        System.out.println(main.maxDepthSubstrings("Hello, World!"));

        System.out.println("END");

    }

    /**
     * Problem Description
     * Given a string that may contain brackets, and no unbalanced brackets, find the substring(s) within the most deeply nested balanced bracket(s).
     * The following sets of characters should be considered as open/close brackets respectively: ( ), [ ], { }
     * If there are multiple sets of brackets with the same highest depth, your function should return all substrings. If there are no brackets in the string, then your function should return the entire input string.
     * Sample Inputs - Expected Outputs
     *
     * "ab(c(d)e)" → "d"
     * "[a{(b)c}d(e)]" → "b"
     * "((a)b(cd)ef)" → "a", "cd"
     * "(ab[]c){d(e)}" → "", "e"
     * "Hello, World!" → "Hello, World!"
     *
     * The problem asks you to find and return the content within the most deeply nested brackets in a string, handling multiple bracket types and returning all substrings at the maximum depth level.
     * @param input
     * @return
     */
    private List<String> maxDepthSubstrings(String input){
        List<String> output = new ArrayList<>();
        int maxDepth = 0; int begin = 0; int end = 0; int currDepth = 0;
        for (int i = 0; i < input.length(); i++) {
            char x = input.charAt(i);
            if (isOpeningBraces(x)){
                begin = i;
                currDepth++;
            } else if (isClosingBraces(x)){
                String sub = input.substring(begin+1, i);
                if (currDepth > maxDepth){
                    maxDepth = currDepth;
                    output = new ArrayList<>();
                    output.add(sub);
                } else if (currDepth == maxDepth)
                    output.add(sub);
                currDepth--;

            }

        }
        if (maxDepth == 0)
            output.add(input);
        return output;
    }

    private boolean isOpeningBraces(char x){
        return x == '(' || x == '{' || x == '[';
    }

    private boolean isClosingBraces(char x){
        return x == ')' || x == '}' || x == ']';
    }

    private static void doesSomething(){
    }

    private static void variousTryAttempts() throws Exception{
        boolean keepTrying = true;
        int count = 0;
        while (keepTrying){
            try {
                doesSomething();
                keepTrying = false;
            }catch (Exception ex){
                Thread.sleep(1000);
                count++;
                if (count > 3) keepTrying = false;
            }

        }
    }
    private static String subStringWithoutDupes(String input){
        StringBuilder sb = new StringBuilder();

        input.chars().distinct().reduce(
                (a,b) -> {
                    sb.append((char)a);
                    sb.append((char)b);
                    return a;
                }
        );

//        Arrays.stream(input.toCharArray())


        return sb.toString();
    }

    //recommendation system
    private List<String> recommendedItems(Map<String, Map<String, String>> alluserMap, List<String> likedSong){

        alluserMap.forEach(
                (user, songs) -> {

                }
        );

//        alluserMap.keySet().stream().stream

        return null;
    }

    private static void replaceWildCardChar(String input) {
        List<StringBuilder> list = new LinkedList<>();
        list.add(new StringBuilder(""));

        for (int i = 0; i < input.length(); i++) {
            char x = input.charAt(i);
            if (x == '?') {
                ListIterator<StringBuilder> itr = list.listIterator();
                while (itr.hasNext()){
                    StringBuilder sb = itr.next();
                    StringBuilder sb2 = new StringBuilder(sb);
                    sb.append('0');
                    sb2.append(1);
                    itr.add(sb2);
                }
            } else {
                for (StringBuilder sb : list) {
                    sb.append(x);
                }

            }
        }

        for (StringBuilder sb: list) {
            System.out.println(sb.toString());
        }
    }

    public static List<String> generatePermutations(String input) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(input);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int wildcardIndex = current.indexOf('?');

            if (wildcardIndex == -1) {
                // No more wildcards, add to result
                continue;
            }

            // Replace wildcard with '0' and '1'
            String with0 = current.substring(0, wildcardIndex) + '0' +
                    current.substring(wildcardIndex + 1);
            String with1 = current.substring(0, wildcardIndex) + '1' +
                    current.substring(wildcardIndex + 1);

            queue.offer(with0);
            queue.offer(with1);
        }

        // Collect all processed strings (those without wildcards)
        List<String> result = new ArrayList<>();
        queue.forEach(result::add);

        // Process remaining items in queue that have no wildcards
        Queue<String> tempQueue = new LinkedList<>();
        tempQueue.offer(input);

        while (!tempQueue.isEmpty()) {
            String current = tempQueue.poll();
            int wildcardIndex = current.indexOf('?');

            if (wildcardIndex == -1) {
                result.add(current);
            } else {
                String with0 = current.substring(0, wildcardIndex) + '0' +
                        current.substring(wildcardIndex + 1);
                String with1 = current.substring(0, wildcardIndex) + '1' +
                        current.substring(wildcardIndex + 1);

                tempQueue.offer(with0);
                tempQueue.offer(with1);
            }
        }

        return result;
    }

}
