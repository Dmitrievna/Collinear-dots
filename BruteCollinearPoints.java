import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.*;
import java.util.*;

public class BruteCollinearPoints {
    
    private int numberOfSegments;// number of line segments
    private LineSegment[] segments;// list of line segments 
    
    public BruteCollinearPoints(Point[] points) {
        
        this.numberOfSegments = new Integer(0);  
        double currentSlope = -1488;
        ArrayList<LineSegment> listOfSegments = new ArrayList<>();
        ArrayList<Point> startPoints = new ArrayList<Point>();
        ArrayList<Point> endPoints = new ArrayList<Point>();
        int duplicateAt;
        Point p1;
        Point p2;
        
        
        
        for (int i = 0; i < points.length - 3; i++) {
            
            for (int j = i+1; j < points.length - 2; j++) {
                
                currentSlope = points[i].slopeTo(points[j]);
       
                
                for (int k = j+1; k < points.length - 1; k++) {
                    
                    
                    if (currentSlope == points[i].slopeTo(points[k])) { // if 3 of 4 points don't have the same slope - pass
                    
                    for (int m = k+1; m < points.length; m++) {
                        
                        if (currentSlope == points[i].slopeTo(points[m])) {
                        
                        
                            Point[] bufPoints = {points[i], points[j], points[k], points[m]};
                            Arrays.sort(bufPoints);
                            
                            
                            p1 = bufPoints[0];
                            p2 = bufPoints[3];
                            duplicateAt = checkForDuplicates(startPoints, endPoints, p1, p2);
                                                        
                            if (duplicateAt  == -1) {
                                startPoints.add(bufPoints[0]);
                                endPoints.add(bufPoints[3]);
                                listOfSegments.add(new LineSegment(bufPoints[0], bufPoints[3]));
                                this.numberOfSegments++;
                                                               
                            } else {
                            
                                listOfSegments.set(duplicateAt, new LineSegment(startPoints.get(duplicateAt), endPoints.get(duplicateAt)));
                                                            
                            }
                            
                            
                                                      

                        
                       
                        }
                        
                    }
                    }
                }
            }
        }
        
        

             
        
        this.segments = listOfSegments.toArray(new LineSegment[this.numberOfSegments]);
        
        
              
           
    }
    
    
    /**
     * Checks if the list contains a duplicate of segment
     * @return true if contains
     * false otherwise
     */ 
    
    private int checkForDuplicates (ArrayList<Point> startPoints, ArrayList<Point> endPoints, Point startPoint, Point endPoint) {
        
        
        
        for (int i=0; i < startPoints.size(); i++) {
            
            if ((startPoints.get(i).slopeTo(endPoints.get(i)) == startPoint.slopeTo(endPoint)) && (startPoints.get(i).slopeTo(endPoint) == startPoint.slopeTo(endPoints.get(i)))) {
                            
                   
                if (startPoint.compareTo(startPoints.get(i)) == -1) {
                    
                    if (endPoint.compareTo(endPoints.get(i)) == -1) {
                        
                        startPoints.set(i,startPoint);
                    
                    } else {
                        
                        startPoints.set(i,startPoint);
                        endPoints.set(i, endPoint);
                        
                    }
                    
                
                } else {
                    
                    if (endPoint.compareTo(endPoints.get(i)) == 1) {
                        
                        endPoints.set(i, endPoint);
                        
                    } else {
                        ;
                    }
                
                }
                
                
                return i;
            
            }
        }
    
    
        return -1;
    }
    
    
    /**
     * @return number of line segments
     */ 
    public int numberOfSegments() {
        
        return this.numberOfSegments;
    }
    
    /**
     * @return list of segments
     */
    public LineSegment[] segments() {
        
        return this.segments;
    }
    
    public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }
    
 
    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.setPenColor(StdDraw.BLUE);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        //if (segment != null) {
           // StdOut.println(segment);
            segment.draw();
        //}
    }
    StdDraw.show();
}

}