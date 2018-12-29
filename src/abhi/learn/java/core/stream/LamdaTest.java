package abhi.learn.java.core.stream;

/**
 * Created by Abhishek on 11/27/2018.
 */
public class LamdaTest {

    public LamdaTest(TestInterface t) {
        this.t = t;
    }

    TestInterface t;
    public void printValue(String s){
        t.test(s);
    }
}
