package abhi.learn.java.leetcode;

public class CommonFunctions {


    public static boolean isPalindrome(String sub) {
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
}
