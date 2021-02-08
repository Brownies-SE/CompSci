/*
  immutable 3D vector/point
*/

import java.util.Scanner;
import java.nio.FloatBuffer;

public class Triple {

   public final double x, y, z;

   public Triple( double a, double b, double c ) {
      x=a;  y=b;  z=c;
   }

   public Triple( Scanner in ) {
      x = in.nextDouble();
      y = in.nextDouble();
      z = in.nextDouble();
      in.nextLine();  // toss rest of the line
   }

   // compute dot product of this triple and
   // the given other triple
   public double dot( Triple other ) {
      return x * other.x + y * other.y + z * other.z;
   }

   // compute cross product of this triple and
   // the given other triple
   public Triple cross( Triple other ) {
      return new Triple( y*other.z - z*other.y,
                         z*other.x - x*other.z,
                         x*other.y - y*other.x );
   }

   public Triple subtract( Triple other ) {
      return new Triple(x-other.x,y-other.y,z-other.z);
   }

   public Triple add( Triple other ) {
      return new Triple(x+other.x,y+other.y,z+other.z);
   }

   public Triple normalize() {
      double norm = Math.sqrt( x*x + y*y + z*z );
      return new Triple( x/norm, y/norm, z/norm );
   }

   public double norm() {
      return Math.sqrt( x*x + y*y + z*z );
   }

   public Triple mult( double s ) {
      return new Triple( s*x, s*y, s*z );
   }

   public Triple rotate( double rotate ) {
       double radians = Math.toRadians(rotate);
       double rotX = (x * Math.cos(radians)) - (y * Math.sin(radians));
       double rotY = (x * Math.sin(radians)) + (y * Math.cos(radians));
       double rotZ = z;

       Triple rotateTriple = new Triple (rotX, rotY, rotZ);

       return rotateTriple;
   }

   public String toString() {
      return "[" + x + "," + y + "," + z + "]";
   }

   // given a, b, c forming coord system on plexiglass
   //   and eye point e, compute lambda, beta, gamma for
   // the given point p
   public static Triple project( Triple a, Triple b, Triple c,
                                 Triple e, Triple p ) {

      // parts that don't involve E
      Triple bMinusA = b.subtract(a);
      Triple cMinusA = c.subtract(a);
      Triple eMinusA = e.subtract(a);

      System.out.println("B-A:" + bMinusA );
      System.out.println("C-A:" + cMinusA );
      System.out.println("E-A:" + eMinusA );

      double ea2 = eMinusA.dot(eMinusA);
      double ba2 = bMinusA.dot(bMinusA);
      double ca2 = cMinusA.dot(cMinusA);

      System.out.println( ea2 + " " + ba2 + " " + ca2 );

      // parts that do involve P

      Triple pMinusE = p.subtract(e);

      System.out.println("P-E:" + pMinusE );

      double bape = bMinusA.dot(pMinusE);
      double cape = cMinusA.dot(pMinusE);
      double eape = eMinusA.dot(pMinusE);

      System.out.println( bape + " " + cape + " " + eape );

      double lambda = - ea2 / eape;
      double beta = lambda * ( bape / ba2 );
      double gamma = lambda * ( cape / ca2 );

      System.out.println( beta + " " + gamma + " " + lambda );

      return new Triple( beta, gamma, lambda );
   }

   public void sendData( FloatBuffer buff ) {
      buff.put( (float) x );
      buff.put( (float) y );
      buff.put( (float) z );
   }

   public final static Triple xAxis = new Triple(1,0,0),
                              yAxis = new Triple(0,1,0),
                              zAxis = new Triple(0,0,1);

   public static void main(String[] args) {
      Triple bgl = project( new Triple(7,8,9), new Triple(11,7,10),
                            new Triple(7,11,12), new Triple(8,10,7),
                            new Triple(12,4,21) );
      System.out.println( bgl );
   }

   public static void main2(String[] args) {
      Scanner keys = new Scanner(System.in);
      System.out.print("please enter a point: ");
      Triple a = new Triple( keys );
      System.out.print("please enter another point: ");
      Triple b = new Triple( keys );
      Triple v = b.subtract(a);
      System.out.print("B-A = " + v );
      System.out.print("please enter another vector: ");
      Triple w = new Triple( keys );
      Triple c = v.cross(w);
      System.out.println("dot products are " + v.dot(c) + " " + w.dot(c) );
   }

}
