import javax.swing.JFrame;
import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class Test extends JFrame implements KeyListener {
    private boolean ex=false;
    private Stack<Point> CH;
    private Point[] randomPoints; 

    public Test() {
        super("ConvexHull");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 800));
        getContentPane().setBackground(Color.decode("#122335"));
        pack();
        this.addKeyListener(this);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(50, 50);
        if(!ex){
            randomPoints = randomPointsGenerator(50);
                    for(int i=0;i<50;i++){
                        g.setColor(Color.WHITE);
                        g.fillOval(randomPoints[i].x, randomPoints[i].y, 10, 10);
                        g.drawOval(randomPoints[i].x, randomPoints[i].y, 10, 10);
                    }
            LinkedList<Point> points = new LinkedList<Point>(Arrays.asList(randomPoints));
            CH = GrahamConvexHull.grahamScan(points);
        }
        
        
      if(ex){
        for(int i=0;i<50;i++){
            g.setColor(Color.WHITE);
            g.fillOval(randomPoints[i].x, randomPoints[i].y, 10, 10);
            g.drawOval(randomPoints[i].x, randomPoints[i].y, 10, 10);
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.decode("#a34313"));

        for(int i=0;i<CH.size();i++){
            g.setColor(Color.decode("#a34313"));
            g.fillOval(CH.get(i).x, CH.get(i).y, 10, 10);
            g.drawOval(CH.get(i).x, CH.get(i).y, 10, 10);
            
        }
        for(int i=0;i<CH.size()-1;i++){
            g.drawLine(CH.get(i).x+5, CH.get(i).y+5, CH.get(i+1).x+5, CH.get(i+1).y+5);
        }
        g.drawLine(CH.peek().x+5, CH.peek().y+5, CH.firstElement().x+5, CH.firstElement().y+5);
       }
    }

    


    public static Point[] randomPointsGenerator(int dim) {
        Random rand = new Random();
        Point[] points = new Point[dim];
        
        for(int i = 0; i < dim; i++) {
            int x = rand.nextInt(801); // Coordinate x casuali tra 0 e 1000
            int y = rand.nextInt(701); // Coordinate y casuali tra 0 e 1000
            points[i] = new Point(x, y);
        }
        
        return points;
    }
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            ex = true;
            repaint();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
       
    }
}
