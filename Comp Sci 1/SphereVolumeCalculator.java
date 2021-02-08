public class SphereVolumeCalculator {
   public static void main (String [] args) {
      double piVal = 3.14159;
      double sphereVolume;
      double sphereRadius;

      sphereRadius = 5.5;

      sphereVolume = (4.0/3.0) * piVal * (sphereRadius * sphereRadius * sphereRadius);/* Your solution goes here  */

      System.out.println("Sphere volume: " + sphereVolume);
   }
}