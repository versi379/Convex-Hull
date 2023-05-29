import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class GrahamConvexHull {

    static Point p0 = new Point(0, 0);
    static Point p1 = new Point(1, 1);
    static Point p2 = new Point(2, 2);
    static Point p3 = new Point(-1, 4);
    static Point p4 = new Point(-2, 2);
    static Point p5 = new Point(-3, 3);
    static Point p6 = new Point(-4, 1);
    static Point p7 = new Point(1, 7);
    static Point p8= new Point(1, 2);
    static Point p9 = new Point(2, 4);

    static LinkedList<Point> points = new LinkedList<Point>(Arrays.asList(p4, p3, p1, p0, p2, p7, p6, p5, p9, p8));


    public static int crossProdcut(Point a, Point b, Point c) { // 2 consecutive segments (a to b, b to c)
        int det = (b.x - a.x)*(c.y - a.y) - (c.x - a.x)*(b.y - a.y);
        if(det == 0) { // a,b,c collinear
            return 0;
        } else if(det > 0) { // clockwise turn (a-b to a-c)
            return 1;
        } else { // counterclockwise turn (a-c to a-b)
            return 2;
        }
    }

    public static Point firstPoint(LinkedList<Point> points) {
        int min_index = 0;
        for(int i = 1; i < points.size(); i++) {
            if(points.get(i).y < points.get(min_index).y) {
                min_index = i;
            } else if(points.get(i).y == points.get(min_index).y) {
                if(points.get(i).x < points.get(min_index).x) {
                    min_index = i;
                }
            }
        }
        return points.get(min_index); // p0
    }


    public static LinkedList<Point> polarSort(LinkedList<Point> unsortedPoints, Point p0) {
        unsortedPoints.remove(p0);
        LinkedList<Point> sortedPoints = new LinkedList<>(unsortedPoints);
        Collections.sort(sortedPoints, new Comparator<Point>() {
            public int compare(Point a, Point b) { // sorting rule
                int cp = crossProdcut(p0, a, b);
                if(cp == 0) { // p0,a,b collinear
                    return (distance(p0, b) >= distance(p0, a)) ? -1 : 1;
                } else {
                    return (cp == 2) ? 1 : -1;
                }
            }
        });

         LinkedList<Point> removePoints = new LinkedList<>();
        for(int i=0; i<=sortedPoints.size()-2; i++){
            int d = crossProdcut(sortedPoints.get(i), sortedPoints.get(i+1) , p0);
            if (d==0){
                removePoints.add(sortedPoints.get(i));
            }
        }
        for(int j=0; j<removePoints.size();j++){
            sortedPoints.remove(removePoints.get(j));
        }

        return sortedPoints;

    }

    public static int distance(Point a, Point b) {
        return (b.x - a.x)*(b.x - a.x) + (b.y - a.y)*(b.y - a.y); 
    }

    public static void main(String[] args) {
        Point p0 = firstPoint(points);
        System.out.println("I punti disordinati:");
       System.out.println(points);
       System.out.println("======================================");
       System.out.println("Il punto p0 è:");
       System.out.println(p0);
       System.out.println("======================================");
       LinkedList<Point> sortedPoints = polarSort(points, p0);
       System.out.println("p1.....pm:");
       System.out.println(sortedPoints);
       System.out.println("======================================");

    }
    
}
