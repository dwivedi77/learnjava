package abhi.learn.java.ikm;

/**
 * Created by Abhishek on 10/5/2016.
 */
public class Super {

    static private int x = getValue();

    protected int getLocalCode(String value, boolean isValidated){
        return -1;
    }

    private static int getValue(){
        return x;
    }
    public static void main(String[] args) {
        String str1 = "My String";
        String str2 = new String("My String");
        System.out.println(str1.hashCode() == str2.hashCode());
        System.out.println(str1.matches(str2));

        int x = 0;
        x = ~x ;
        boolean b = false;
        System.out.println((b=false) ? "yes" : "no");

        Long i =new Long(10);
        long j = 10;
        i++;
        System.out.println(i);
        System.out.println("d"+ (i == j));

    }
}
