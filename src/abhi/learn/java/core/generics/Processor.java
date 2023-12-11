package abhi.learn.java.core.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek on 8/25/2018.
 */
public interface Processor <INPUT, OUTPUT>{
//    public final static List<Integer> list = new ArrayList<Number>();
    public OUTPUT process(INPUT input);

}
