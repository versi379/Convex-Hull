import java.util.Arrays;
import java.util.LinkedList;
import java.awt.Point;
import java.util.Random;
import java.util.Stack;

public class RandomInputPoints {
    public static void main(String[] args) {
        int dim[] = {10, 100, 1000, 10000, 50000}; // Array di numeri di punti casuali da generare
        
       /*  System.out.println("Punti casuali generati:");
        for (int i = 0; i < randomPoints.length; i++) {
            System.out.println("(" + randomPoints[i].x + ", " + randomPoints[i].y + ")");
        }*/

        for(int i=0;i<5;i++){
            Point[] randomPoints = randomPointsGenerator(dim[i]);
        
            LinkedList<Point> points = new LinkedList<Point>(Arrays.asList(randomPoints));

            long startTime = System.currentTimeMillis();
            Stack<Point> CH = GrahamConvexHull.grahamScan(points);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            System.out.println("L'inviluppo complesso dei punti inseriti è: ");
            System.out.println(CH);
            System.out.println("Il tempo impiegato da GrahamScan per " + dim[i] + " punti è: " + executionTime + " millisecondi");
            System.out.println("==========================================================================================");
        }
    }
    
    public static Point[] randomPointsGenerator(int dim) {
        Random rand = new Random();
        Point[] points = new Point[dim];
        
        for(int i = 0; i < dim; i++) {
            int x = rand.nextInt(1001); // Coordinate x casuali tra 0 e 50
            int y = rand.nextInt(1001); // Coordinate y casuali tra 0 e 50
            points[i] = new Point(x, y);
        }
        
        return points;
    }
}
