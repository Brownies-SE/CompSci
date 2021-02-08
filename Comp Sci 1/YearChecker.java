import java.util.Scanner;

public class YearChecker {
   public static void main (String [] args) {
      int givenYear = 0;

      givenYear = 2000;

      if (givenYear >= 2100){
         System.out.print("Distant future");
      }
      else if (givenYear >= 2000){
         System.out.print("21st century");
      }
      else if (givenYear >= 1900){
         System.out.print("20th century");
      }
      else if (givenYear <= 1899){
         System.out.print("Long ago");
      }

      return;
   }
}