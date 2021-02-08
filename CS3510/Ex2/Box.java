import java.util.ArrayList;

public class Box {

    final double Z = 0.0;
    double x, y, width, heigth, r, g, b;

    

    public Box(double x, double y, double width, double heigth, double r, double g, double b) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public double[][][] makeBox(){
        return boxPoints(x, y, width, heigth);
    }

    private double[][][] boxPoints(double x, double y, double width, double heigth) {
        double[][][] points = new double[4][3][3];
        // triangle 1 Point 1
        points[0][0][0] = x;
        points[0][0][1] = y;
        points[0][0][2] = Z;
		// triangle 1 point 2
		points[0][1][0] = x + (width / 2);
        points[0][1][1] = y + (heigth / 2);
        points[0][1][2] = Z;
		// triangle 1 point 3
		points[0][2][0] = x;
        points[0][2][1] = y + heigth;
        points[0][2][2] = Z;
		
		// triangle 2 point 1
		points[1][0][0] = x + width;
        points[1][0][1] = y;
        points[1][0][2] = Z;
		// triangle 2 point 2
		points[1][1][0] = x;
        points[1][1][1] = y + (heigth / 2);
        points[1][1][2] = Z;
		// triangle 2 point 3
		points[1][2][0] = x;
        points[1][2][1] = y;
        points[1][2][2] = Z;
        
        return points;
    }
	
	 public double[] colorBox() {
        return setColor(r, g, b);
    }

    private double[] setColor(double r, double g, double b) {
        double[] points = new double[18];

        for(int i = 0; i < points.length; i++) {
            points[i] = r;
            i++;
            points[i] = g;
            i++;
            points[i] = b;
        }
        return points;
    }
}
