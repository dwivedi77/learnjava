package interviews.fb;

/**
 * Created by Abhishek on 7/6/2019.
 */
public class FBMain {
    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        Point[] answer = testGetNearestPoints();
        System.out.println("Answer="+answer);
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");

    }

    private static Point[] testGetNearestPoints(){
        Point p1 = new Point(1,3);
        Point p5 = new Point(5,9);
        Point p4 = new Point(8,4);
        Point p2 = new Point(10,2);
        Point p3 = new Point(3,3);
        Point[] input = new Point[]{p1,p2,p3,p4,p5};
        return getNearestPoints(input, 3);
    }

    ///find the num nearest points from ground zero
    private static Point[] getNearestPoints(Point[] points, int num){
        Point[] out = new Point[num];
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            double dis = Math.sqrt(p.x*p.x + p.y*p.y);
            Point temp = null;
            for (int j = 0; j < num; j++) {
                Point p2 = out[j];
                if (p2==null){
                    out[j] = p;
                    break;
                }

                double curr = Math.sqrt(p2.x*p2.x + p2.y*p2.y);
                if (curr > dis){
                    temp = p2;
                    out[j] = p;
                    p = p2;
                    dis = curr;
                }
            }
        }
        return out;
    }
}

class Point{
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x;
    int y;
}
