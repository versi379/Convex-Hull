import java.util.LinkedList;
import java.awt.Point;
public class Glist extends LinkedList {

    public Point top(LinkedList<Point> P){
        return P.getLast();
    }
    public Point nextToTop(LinkedList<Point> P){
        return P.get(P.size()-2);
    }
}
