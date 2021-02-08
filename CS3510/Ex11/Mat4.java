import java.nio.FloatBuffer;

public class Mat4 {

   public final double[][] data;

   public Mat4() {
      data = new double[4][4];
   }

   public Mat4( double d11, double d12, double d13, double d14,
                double d21, double d22, double d23, double d24,
                double d31, double d32, double d33, double d34,
                double d41, double d42, double d43, double d44 ) {
      data = new double[4][4];
      data[0][0] = d11; data[0][1] = d12; data[0][2] = d13; data[0][3] = d14;
      data[1][0] = d21; data[1][1] = d22; data[1][2] = d23; data[1][3] = d24;
      data[2][0] = d31; data[2][1] = d32; data[2][2] = d33; data[2][3] = d34;
      data[3][0] = d41; data[3][1] = d42; data[3][2] = d43; data[3][3] = d44;
   }

   // multiply this times other
   public Mat4 mult( Mat4 other ) {

      Mat4 ans = new Mat4();

      for (int r=0; r<4; r++) {
         for (int c=0; c<4; c++) {
            for (int k=0; k<4; k++) {
               ans.data[r][c] += this.data[r][k] * other.data[k][c];
            }
         }
      }

      return ans;

   }// mult

   public static Mat4 translate( double a, double b, double c ) {
      return new Mat4( 1, 0, 0, a,
                       0, 1, 0, b,
                       0, 0, 1, c,
                       0, 0, 0, 1 );
   }

   public static Mat4 lookAt( Triple e, double azi, double alt, double d ) {
      // see page 33
      double alpha = Math.toRadians(azi);
      double beta = Math.toRadians(alt);
      double ca = Math.cos(alpha);
      double sa = Math.sin(alpha);
      double cb = Math.cos(beta);
      double sb = Math.sin(beta);

      Triple c = new Triple( e.x + cb*d*ca,
                      e.y + cb*d*sa,
                      e.z + d*sb );

      Triple n = c.subtract(e).normalize();
      Triple r = n.cross( Triple.zAxis ).normalize();
      Triple w = r.cross(n).normalize();  // probably already length 1
     
      Mat4 rot = new Mat4( r.x, r.y, r.z, 0,
                           w.x, w.y, w.z, 0,
                           -n.x, -n.y, -n.z, 0,
                           0, 0, 0, 1 );
      Mat4 tran = Mat4.translate( -e.x, -e.y, -e.z );

      return rot.mult(tran);

   }

   public static Mat4 frustum( double left, double right, 
                          double bottom, double top,
                          double near, double far ) {
      return new Mat4( 2*near/(right-left), 0, (right+left)/(right-left), 0,
                       0, 2*near/(top-bottom), (top+bottom)/(top-bottom), 0,
           0, 0, - (far+near)/(far-near), - 2*far*near/(far-near),
           0, 0, -1, 0 );
   }

   // write 16 values for this Mat4 into buffer
   // in ROW major order
   // (so when do glUniformMatrix4fv command, use 
   //  true for the transpose parameter)
   public void toBuffer( FloatBuffer buffer ) {
      buffer.rewind();
      for (int r=0; r<4; r++) {
         for (int c=0; c<4; c++) {
            buffer.put( (float) data[r][c] );
         }
      }
      buffer.rewind();
   }

   public String toString() {
      String s = "";
      for (int r=0; r<4; r++) {
         for (int c=0; c<4; c++) {
             s += String.format("%10.4f ", data[r][c] );
         }
         s += "\n";
      }
      return s;
   }// toString

   // multiply this mat4 times p (with 1 appended)
   // and after doing perspective division drop the
   // extra 1
   public Triple mult( Triple p ) {
      double[] res = new double[4];
      res[0] = data[0][0]*p.x + data[0][1]*p.y + data[0][2]*p.z + data[0][3];
      res[1] = data[1][0]*p.x + data[1][1]*p.y + data[1][2]*p.z + data[1][3];
      res[2] = data[2][0]*p.x + data[2][1]*p.y + data[2][2]*p.z + data[2][3];
      res[3] = data[3][0]*p.x + data[3][1]*p.y + data[3][2]*p.z + data[3][3];
      double w = res[3];
      return new Triple( res[0]/w, res[1]/w, res[2]/w );
   }

}
