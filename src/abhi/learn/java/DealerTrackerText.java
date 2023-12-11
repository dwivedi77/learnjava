package abhi.learn.java;

/**
 * Created by Abhishek on 6/19/2016.
 */
public class DealerTrackerText {
    private final String text;

    public String getText() {
        return text;
    }

    public DealerTrackerText(String text){
        this.text = text;
    }

    public boolean isPalindrome(String text){
        if (text == null || "".equals(text)) return false;
        int length = text.length();
        for (int i = 0, j=length-1; i<j ; ) {
            char x = text.charAt(i);
            if (isNotAlphaNumeric(x)){
                i++;continue;
            }
            char y = text.charAt(j);
            if (isNotAlphaNumeric(y)){
                j--;continue;
            }
            if (Character.toUpperCase(x) != Character.toUpperCase(y)){
                return false;
            }else{
                i++;j--;
            }
        }
        return true;
    }

    private static boolean isNotAlphaNumeric(char c) {
        return !Character.isLetterOrDigit(c);
    }


}
