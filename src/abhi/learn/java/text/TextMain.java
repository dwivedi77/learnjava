package abhi.learn.java.text;

/**
 * Created by Abhishek on 8/9/2016.
 */
public class TextMain {

    public static void main(String[] args) {
        System.out.println(isInterLeavingOfTwoString("abc", "xyzz", "xyabzcz"));
//        System.out.println(checkAnagramMyVersion("O, Draconian devil!", "Leonardo Da Vinci"));
//        System.out.println(reverseStringRecursusive("Abhishek"));
//        printAllCharacters();
    }

    private static boolean isInterLeavingOfTwoString(String s1, String s2, String text){
        if (s1.length()+s2.length() != text.length()) return false;
        boolean more = true;
        for (int i = 0, j = 0, idx = 0; more; ) {
            char x = s1.charAt(i);
            char y = s2.charAt(j);
            char z = text.charAt(idx);
            if (x == z){
                if(i < s1.length()-1)i++;idx++;
                more  = more && j < s2.length() && idx < text.length();
                continue;
            }else if (y == z){
                if(j < s2.length()-1)j++; idx++;
                more  = more && i < s1.length() && idx < text.length();
                continue;
            }else{
                return false;
            }
        }

        return true;
    }

    private static String reverseStringRecursusive(String text){
        if (text ==  null || "".equals(text)) return "";
        if (text.length() == 1) return text;
        return text.charAt(text.length()-1)+reverseStringRecursusive(text.substring(0, text.length()-1));
    }
    //linked hashmap is right way
    private static void printFirstNonRepeated(String text){
        if (text ==  null || "".equals(text)) return;
        int idx = 0;
        int x = text.charAt(idx);
        while (idx < text.length()){

        }
    }
    private static void printAllCharacters(){
        for (int i = 0; i < 150; i++) {
            System.out.print("["+i+"="+ ((char)i) +"]");
            if (i%20 == 0) System.out.println();
        }
    }

    private static void printDupeChars(String text){
        if (text ==  null || "".equals(text)) return;

    }

    private static boolean checkAnagram(String s1, String s2){
        final int LETTERS_LEN = 256;

        if (s1 == null || s2 == null) return false;
        int len = s1.length();
        if (len != s2.length() || len < 2)
            return false;

        int[] letters = new int[LETTERS_LEN];

        for (int i = 0; i < len; i++) {
            letters[s1.charAt(i)]++;
            letters[s2.charAt(i)]--;
        }

        for (int i = 0; i < LETTERS_LEN; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkAnagramMyVersion(String s1, String s2){

        if (s1 == null || s2 == null) return false;
        int[] letters = new int[26];

        int len = s1.length();
        for (int i = 0; i < len; i++) {
            char x = s1.charAt(i);
            if (Character.isLetter(x))
                letters[Character.toLowerCase(s1.charAt(i))-97]++;
        }

        len = s2.length();
        for (int i = 0; i < len; i++) {
            char x = s2.charAt(i);
            if (Character.isLetter(x))
                letters[Character.toLowerCase(s2.charAt(i))-97]--;
        }

        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
