import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

public class GrahamConvexHull {

    private static int crossProdcut(Point a, Point b, Point c) { // 2 consecutive segments (a to b, b to c)
        int det = (c.x - a.x)*(b.y - a.y) - (b.x - a.x)*(c.y - a.y);
        if(det == 0) { // a,b,c collinear
            return 0;
        } else if(det > 0) { // clockwise turn (a-b to a-c)
            return 1;
        } else { // counterclockwise turn (a-c to a-b)
            return 2;
        }
    }

    private static Point firstPoint(LinkedList<Point> points) {
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


    private static LinkedList<Point> polarSort(LinkedList<Point> unsortedPoints, Point p0) {
        unsortedPoints.remove(p0);
        LinkedList<Point> sortedPoints = new LinkedList<>(unsortedPoints);
        Collections.sort(sortedPoints, new Comparator<Point>() {
            public int compare(Point a, Point b) { // sorting rule
                int cp = crossProdcut(p0, a, b);
                if(cp == 0) { // p0,a,b collinear
                    return (distance(p0, b) >= distance(p0, a)) ? -1 : 1;
                } else {
                    return (cp == 2) ? -1 : 1;
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

    private static int distance(Point a, Point b) {
        return (b.x - a.x)*(b.x - a.x) + (b.y - a.y)*(b.y - a.y); 
    }
    private static Point top(LinkedList<Point> P){
        return P.getLast();
    }
    private static Point nextToPeek(Stack<Point> S){
        return S.get(S.size()-2);
    }

    public static Stack<Point> grahamScan(LinkedList<Point> Q){
        Point p0 = firstPoint(Q);
        LinkedList<Point> polarSortedPoints = polarSort(Q, p0);
        Stack<Point> S = new Stack<Point>();
        S.push(p0);
        S.push(polarSortedPoints.get(0));//p1
        S.push(polarSortedPoints.get(1));//p2
        for(int i=2;i<polarSortedPoints.size();i++){
            while(crossProdcut(GrahamConvexHull.nextToPeek(S),S.peek() , polarSortedPoints.get(i)) != 2){
                S.pop();
            }
            S.push(polarSortedPoints.get(i));
        }
        return S;
    }

  /*   public static void main(String[] args) {
       System.out.println(points);
       System.out.println("===========================================================");
       Stack<Point> CH = grahamScan(points);
       System.out.println("GrahamScan eseguito");
       System.out.println(CH);
    }*/
    
}
