package abhi.learn.java.core.lang;

/**
 * Created by Abhishek on 9/29/2018.
 */
public class LangMain {

    public static void main(String[] args) {
        System.out.println("START");
        testInnerClass();
        System.out.println("END");

    }

    private static void testInnerClass() {
//        Outer outer = new Outer("outer");
//        Outer.Inner inner = outer.new Inner("inner");

    }

    private static void testSwitchCase() {
        int amount = 5;
        switch (amount) {
            case 0:
                System.out.println("amount is  0");
            case 5:
                System.out.println("amount is  5");
            case 10:
                System.out.println("amount is 10");
            case 12:
                System.out.println("amount is 12");
            default:
                System.out.println("amount is something else");
        }
    }

    private static void testSwitchCaseString() {
        String name = "abhi";
        switch (name) {
            case "abhi":
                System.out.println("name is abhi");
            case "abhi1":
                System.out.println("name is abhi1");
            case "abhi2":
                System.out.println("name is abhi2");
            default:
                System.out.println("name is something else");
        }
    }

}
