
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Larry Osakwe
 */
public class FastCollinearPoints {

    private final int n;
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        n = points.length;
        cornerCases(points);

        Arrays.sort(points);

        Point[] aux = points.clone();
        
        ArrayList<LineSegment> fourPointSegments = new ArrayList<>();
        
        for (Point p : points) {
            Arrays.sort(aux, p.slopeOrder());
            for (int i = 0; i < aux.length;) {
                int count = 0;
                while (i + count + 1 <= (aux.length - 1) && p.slopeTo(aux[i]) == p.slopeTo(aux[i + count + 1])) {
                    count++;
                }
                if (count >= 2) {
                    Arrays.sort(aux, i, i + count + 1);
                    if (p.compareTo(aux[i]) < 0) {
                        LineSegment newline = new LineSegment(p, aux[i + count]);
                        if (!ifDuplicate(fourPointSegments, newline)) {
                            fourPointSegments.add(newline);
                        }
                    } else {
                        if (p.compareTo(aux[i + count]) < 0) {
                            LineSegment newline = new LineSegment(aux[i], aux[i + count]);
                            if (!ifDuplicate(fourPointSegments, newline)) {
                                fourPointSegments.add(newline);
                            }
                        } else {
                            LineSegment newline = new LineSegment(aux[i], p);
                            if (!ifDuplicate(fourPointSegments, newline)) {
                                fourPointSegments.add(newline);}
                            
                        }
                    }

                }
                i += count + 1;
            }

        }
        lineSegments = fourPointSegments.toArray(new LineSegment[fourPointSegments.size()]);

    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }

    private void cornerCases(Point[] points) {
        if (points == null) {
            throw new NullPointerException("argument is null");
        }
        for (int i = 0; i < n; i++) {
            if (points[i] == null) {
                throw new NullPointerException("a point in the array is null");
            }  
        }
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
                if (points[i].compareTo(points[i+1]) == 0) {
                    throw new IllegalArgumentException("constructor contains "
                            + "duplicate point");
                }
            }
    }

    private boolean ifDuplicate(ArrayList<LineSegment> a, LineSegment line) {
        for (LineSegment l : a) {
            if (l.toString().equals(line.toString())) {
                return true;
            }
        }
        return false;
    }

}
