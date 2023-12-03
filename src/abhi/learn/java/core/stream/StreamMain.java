package abhi.learn.java.core.stream;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * Created by Abhishek on 11/26/2018.
 */
public class StreamMain {

    public static void main(String[] args) throws Exception {
        System.out.println("START");
        testLambda();
        System.out.println("END");
    }

    private static void testLambda() {
        LamdaTest test = new LamdaTest(s -> System.out.print("lamnda prints::"+s));
        test.printValue("Hello Lambda Baby....");
    }

    private static void testIntStream() {
        IntStream.range(1,100).filter(value -> (value % 2 == 0)).forEach(value1 -> {System.out.print(value1); System.out.print(" ");});
        System.out.println();
        IntStream.range(1,100).filter(value -> (value % 3 == 0)).forEach(System.out::print);

    }

}
