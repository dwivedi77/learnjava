package abhi.learn.java.yeildbook;

/**
 * Created by Abhishek on 9/12/2016.
 */
class A{
    public int value = 10;
    public String name = "abhi";
}

public class B{

    public static void main(String[] args){
        A a = new A();
        B b = new B();

        System.out.println(a.name); //10 abhi

        b.change(a);
        System.out.println(a.name); //15 abhi1

        b.newChange(a);
        System.out.println(a.name); //15 abhi1
    }

    public void change(A aa) {
        aa.value = 15;
        aa.name = "abhi1";
    }

    public void newChange(A aa) {
        aa = new A();
        aa.value = 20;
        aa.name = "abhi2";
    }
}
