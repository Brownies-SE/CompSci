import java.nio.FloatBuffer;

public class Camera {

   private Triple e;  // eye point of this camera
   private double azi, alt;  // angles of viewing
   private double d;  // distance from e to c

   private double left, right, bottom, top, far;

   private FloatBuffer frustumBuffer, lookAtBuffer;

   private Mat4 lookAt, frustum;

   // given eye point, azimuth and altitude (in degrees)
   // and distance from eye to center of plexiglass
   // construct a camera
   public Camera( Triple eye, double azimuth, 
                  double altitude, double dist,
                  double lft, double rgt, double bot, double tp,
                  double f ) {
      e = eye;
      azi = azimuth;  alt = altitude;
      d = dist;
      
      left = lft;  right = rgt;
      bottom = bot;  top = tp;
      far = f;
      // "near" is d

      // make permanent space for the two matrices
      frustumBuffer = Util.createFloatBuffer( 16 );
      lookAtBuffer = Util.createFloatBuffer( 16 );

      update();

      
   }// constructor

   // recompute instance fields when one of them
   // changes (azi, alt, d, e)
   private void update() {

      // stub:  make frustum identity matrix---or other columns for
      //        color checking
/*
      frustum = new Mat4( 1,   1,    1, 0,
                          0.5, 0.8,  1, 0,
                          0,   1,    0.8, 0,
                          0,   0, 0, 1 );
*/
        
      frustum = Mat4.frustum( left, right, bottom, top, d, far );
      lookAt = Mat4.lookAt( e, azi, alt, d );

      // copy data from matrices to buffers:
      frustum.toBuffer( frustumBuffer );
      lookAt.toBuffer( lookAtBuffer );

   }

   public Mat4 getFrustum() {
      return frustum;
   }

   public Mat4 getLookAt() {
      return lookAt;
   }

   public FloatBuffer getFrustumBuffer() {
      return frustumBuffer;
   }

   public FloatBuffer getLookAtBuffer() {
      return lookAtBuffer;
   }
 
   // change azi by da
   public void turnBy( double da ) {
      azi += da;
      if ( azi >= 360 ) {
         azi -= 360;
      }
      if ( azi <= -360 ) {
         azi += 360;
      }
      update();
   }

   public void tiltBy( double da ) {
      alt += da;
      if ( alt > 89 )  alt = 89;
      if ( alt < -89 )  alt = -89;
      update();
   }

   public void shiftBy( Triple dv ) {
      e = e.add( dv );
      update();
   }

   public void zoomBy( double dd ) {
      d += dd;
      update();
   }

   public String toString() {
      return "eye: " + e + " azi: " + azi + " alt: " + alt;
   }

   public static void main(String[] args) {
    
   }

}
