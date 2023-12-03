package abhi.learn.java.core.lang;

/**
 * Created by Abhishek on 9/29/2018.
 */
public class Outer {
    private int age;
    private String name;

    private static int test = 100;

    public Outer(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public class Inner{
        private String innerName;

        public Inner(String innerName) {
            this.innerName = innerName;
        }

        public String getInnerName() {
            return innerName;
        }

        public void setInnerName(String innerName) {
            this.innerName = innerName;
        }

        public void testTest(){
            System.out.println(test);
        }


    }

    public static class StatInner{
        private String statInnerName;

        public StatInner(String statInnerName) {
            this.statInnerName = statInnerName;
        }

        public String getStatInnerName() {
            return statInnerName;
        }

        public void setStatInnerName(String statInnerName) {
            this.statInnerName = statInnerName;
        }

        public void testTest(){
            System.out.println(test);
        }

        public void localClassMethod(){
            String innerName2 = "test name";
            class InnerLocal{
                public void print(){
                    System.out.println(test);
//                    System.out.println(innerName);
                    System.out.println(innerName2);
                }
            }

        }
    }
}
