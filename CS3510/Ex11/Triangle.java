import java.util.Scanner;
import java.nio.FloatBuffer;

public class Triangle {

   private Vertex v1, v2, v3;

   public Triangle( Scanner input ) {
      v1 = new Vertex( input ); 
      v2 = new Vertex( input ); 
      v3 = new Vertex( input ); 
   }

   public Triangle( Vertex a, Vertex b, Vertex c ) {
      v1 = a;
      v2 = b;
      v3 = c;
   }

   public Triangle( ArrayList<Vertex> verts, int a, int b, int c ) {
      v1 = verts.get(a);
      v2 = verts.get(b);
      v3 = verts.get(c);
   }

   public void sendData( FloatBuffer posBuff, FloatBuffer colBuff, Mat4 transform ){
      v1.sendData( posBuff, colBuff, transform );
      v2.sendData( posBuff, colBuff, transform );
      v3.sendData( posBuff, colBuff, transform );
   }

   // display on screen the three vertices as transformed by
   // the camera's lookAt and frustum matrices
   public void showTransformedData( Camera camera ) {
       System.out.println("triangle's transformed data: ");
       v1.showTransformedData( camera );
       v2.showTransformedData( camera );
       v3.showTransformedData( camera );
   }

}
