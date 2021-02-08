import java.util.Scanner;

public class CoordinateGeometry {
   public static void main(String [] args) {
      double x1 = 1.0;
      double y1 = 2.0;
      double x2 = 1.0;
      double y2 = 5.0;
      double pointsDistance = 0.0;

      pointsDistance = Math.pow(2.0, Math.sqrt(x2-x1) + (y2-y1)); /* Your solution goes here  */

      System.out.print("Points distance: ");
      System.out.println(pointsDistance);
   }
}    