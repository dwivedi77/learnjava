package abhi.learn.java;

import java.util.*;

/**
 * Created by Abhishek on 5/22/2016.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(findLexagraphicLowestBiggerWord("dkhc"));

//        Scanner scan = new Scanner(System.in);
//        int c = Integer.parseInt(scan.next());
//        while (c > 0){
//            System.out.println(findLexagraphicLowestBiggerWord(scan.next()));
//            c--;
//        }
    }

    private static String findLexagraphicLowestBiggerWord(String s){
        int len = s.length();
        if (len == 0 || len == 1) return s;
        char[] result = s.toCharArray();
        while (len > 1){
            char x = s.charAt(len-1);
            char y = s.charAt(len-1-1);
            if(x > y){
                result[len-1] = y;
                result[len-1-1] = x;
                break;
            }else{
                len--;
            }
        }
        String answer = new String(result);
        return answer.equals(s) ? "no answer" : answer;
    }
    private static Node mergeLists(Node headA, Node headB) {
        if (headA == null) return headB;
        if (headB == null) return headA;

        if (headA.data <= headB.data){
            headA.next = mergeLists(headA.next, headB);
            return headA;
        }else{
            headB.next = mergeLists(headA, headB.next);
            return headB;
        }
    }

    class Node {
        int data;
        Node next;
    }


    private static boolean hasBalancedBraces1(String test) {
        if (test == null || "".equals(test)) return true;
        Stack stack = new Stack();
        if (test == null || test.length() == 0 || test.length() > 100) return false;
        int len = test.length();
        for (int i = 0; i < len; i++) {
            char x = test.charAt(i);
            if (x == '{' || x == '[' || x == '(') {
                stack.add(x);
                continue;
            }
            if (x == '}') {
                if (stack.isEmpty()) return false;
                char y = (char) stack.peek();
                if (y != '{')
                    return false;
                else stack.pop();
                continue;
            }
            if (x == ']') {
                if (stack.isEmpty()) return false;
                char y = (char) stack.peek();
                if (y != '[')
                    return false;
                else stack.pop();
                continue;
            }
            if (x == ')') {
                if (stack.isEmpty()) return false;
                char y = (char) stack.peek();
                if (y != '(')
                    return false;
                else stack.pop();
                continue;
            }
        }

        if (stack.isEmpty())
            return true;
        else return false;
    }

    static boolean isAnagram(String A, String B) {
        //Complete the function
        if (A.length() != B.length()) return false;
        char[] a1 = A.toLowerCase().toCharArray();
        char[] b1 = B.toLowerCase().toCharArray();
        Arrays.sort(a1);
        Arrays.sort(b1);
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != b1[i]) return false;
        }
        return true;
    }

    static void countDuplicates() {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        sc.nextLine();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < count; i++) {
            String name = sc.nextLine().toLowerCase();
            String a1 = name.substring(0, name.indexOf(' '));
            String a2 = name.substring(name.indexOf(' ') + 1);
            if (map.get(name) != null)
                map.put(name, map.get(name) + 1);
            else
                map.put(name, 1);
            System.out.println(map.size());
        }
    }
}

class Player {
    String name;
    int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

class Checker implements Comparator<Player> {
    public int compare(Player a, Player b) {
        if (a == null && b == null) return 0;
        if (a.score == b.score) {
            return a.name.compareTo(b.name);
        } else {
            return b.score > a.score ? 1 : -1;
        }
    }

    class Circle {
        float radius;

        Circle(float radius) {
            this.radius = radius;
        }

        long getArea() {
            double area = 3.14159265 * radius * radius;
            return (long) Math.ceil(area);
        }
    }

    class Square {
        float side;

        Square(float side) {
            this.side = side;
        }

        int getArea() {
            double area = side * side;
            return (int) Math.ceil(area);
        }
    }

    class Rectangle {
        float width;
        float height;

        Rectangle(float width, float height) {
            this.width = width;
            this.height = height;
        }

        int getArea() {
            double area = width * height;
            return (int) Math.ceil(area);
        }
    }
}