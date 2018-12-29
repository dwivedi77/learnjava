package abhi.learn.java.core.enums;

/**
 * Created by Abhishek on 5/13/2016.
 */
public class EnumMain {

    public static void main(String[] args) {

        Meal[] meals = Meal.values();

        for (Meal meal: meals) {
            System.out.println("meal = " + meal.ordinal());
        }
    }
}
