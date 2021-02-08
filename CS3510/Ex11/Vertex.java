import java.util.Scanner;
import java.nio.FloatBuffer;

public class Vertex {

   private Triple v;
   private Triple color;

   public Vertex( Scanner input ) {
      v = new Triple( input );
      color = new Triple( input );
   }

   public Vertex( Triple loc, Triple col ) {
      v = loc;
      color = col;
   }

   public Triple add ( Triple amount ) {
       Triple shift = v.add(amount);
       return shift;
   }

   public Triple rotate ( double rotate ) {
       Triple rotateTriple = v.rotate( rotate );
       return rotateTriple;
   }

   // public Triple mult ( double amount ) {
   //     Triple turnBy = v.mult(amount);
   //     return turnBy;
   // }

   public void sendData( FloatBuffer posBuff, FloatBuffer colBuff ) {
      v.sendData( posBuff );
      color.sendData( colBuff );
   }

   public String toString() {
      return String.format("%12.5f %12.5f %12.5f", v.x, v.y, v.z );
   }

   public void showTransformedData( Camera camera ) {
       Triple tranV = camera.getFrustum().mult( camera.getLookAt().mult( v ));
       System.out.println("orig: " + v + " transformed: " + tranV );
   }

}
