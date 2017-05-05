
import java.util.ArrayList;
import java.util.Arrays;

/*----------------------------------------------------------------
 *  Author:        Larry Osakwe
 *  Written:       5/2/2017
 *  Last updated:  5/2/2017
 *
 *  Compilation:   javac BruteCollinearPoints.java
 *  Execution:     java BruteCollinearPoints
 *  
 *  Examines 4 points at a time and checks whether they all lie on 
 *  the same line segment
 *----------------------------------------------------------------*/
public class BruteCollinearPoints {

    private final int n;
    private final LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        n = points.length;
        cornerCases(points);

        Arrays.sort(points);
        ArrayList<LineSegment> fourPointSegments = new ArrayList<>();

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    if (points[i].slopeTo(points[j])
                            != points[i].slopeTo(points[k])) {
                        continue;
                    }
                    for (int l = k + 1; l < n; l++) {
                        if (points[i].slopeTo(points[j])
                                == points[i].slopeTo(points[l])) {
                            fourPointSegments.add(new LineSegment(points[i], points[l]));
                        }
                    }

                }
            }
        }
        lineSegments = fourPointSegments.toArray(new LineSegment[fourPointSegments.size()]);

    }

    /**
     * the number of line segments
     *
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return lineSegments.length;
    }

    /**
     * the line segments;
     *
     * @return the line segments
     */
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
            for (int j = i + 1; j < n; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("constructor contains "
                            + "duplicate point");
                }
            }
        }
    }

}
