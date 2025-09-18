package abhi.learn.java.interviews;

import java.util.*;

public class BloombergPrep {

    public static void main(String[] args) {
        System.out.println("START");

        List<String> output = generatePermutations("0??1");
        System.out.println(output);
        System.out.println("END");

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
