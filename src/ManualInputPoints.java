import java.util.Arrays;
import java.util.LinkedList;
import java.awt.Point;
import java.util.Scanner;
import java.util.Stack;

public class ManualInputPoints {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Inserisci il numero di punti da inserire: ");
            int numCoppie = scanner.nextInt();
            
            Point[] arrayPunti = new Point[numCoppie];
            
            for(int i = 0; i < numCoppie; i++) {
                System.out.print("Inserisci il primo intero della coppia " + (i + 1) + ": ");
                int xCordinate = scanner.nextInt();
        
                System.out.print("Inserisci il secondo intero della coppia " + (i + 1) + ": ");
                int yCordinate = scanner.nextInt();
                
                Point punto = new Point(xCordinate, yCordinate);
                arrayPunti[i] = punto;
            }
            
            System.out.println("Coppie di interi inserite:");
            for(int i = 0; i < numCoppie; i++) {
                Point punto = arrayPunti[i];
                System.out.println("(" + punto.x + ", " + punto.y + ")");
            }

            LinkedList<Point> points = new LinkedList<Point>(Arrays.asList(arrayPunti));
            Stack<Point> CH = GrahamConvexHull.grahamScan(points);
            System.out.println("L'inviluppo complesso dei punti inseriti è: ");
            System.out.println(CH);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
