package abhi.learn.java.core.lang;

/**
 * Created by Abhishek on 9/29/2018.
 */
public class StringMain {

    public static void main(String[] args) throws Exception {
        System.out.println("START");
        float x = 101_0.000_1f;
        System.out.println(x);
        testStringLiteral();
        System.out.println("END");
    }

    private static void testStringLiteral(){
        String x1 = "Hello World";
        String x2 = "Hello"+ " "+ "World";
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" ");
        sb.append("World");
        System.out.println("Test="+ (x1==sb.toString().intern()));
    }


    private static void testSplit(){
        String pig = "asbasb,absabsab,asbabsabsa,s,s, ,";

        String[] splits = pig.split(",");
        for (String spilt: splits) {
            System.out.println("["+spilt+"]");
        }
    }

    private static void testInitializerTest() throws Exception {
        InitializerTest t1 = new InitializerTest("t1");
        InitializerTest t2 = new InitializerTest("t2");
        InitializerTest t3 = new InitializerTest("t3");
        InitializerTest.class.newInstance();
    }

}

class InitializerTest{

    private static int count = 0;

    {
        System.out.println("Count="+count);
    }
    private String name;

    public InitializerTest(String name) {
        count++;
        this.name = name;
        System.out.println(this.name);
    }

    public InitializerTest() {
        System.out.println("Default");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
