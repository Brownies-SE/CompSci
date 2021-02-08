import java.util.Scanner;

public class SpaceReplace {
   public static void main (String [] args) {
      Scanner scnr = new Scanner(System.in);
      String userWord;
      int userNum;
      
      System.out.println("Enter a name: ");
      userWord = scnr.nextLine();
      
      System.out.println("Enter a number: ");
      userNum = scnr.nextInt();
      
   
      System.out.print(userWord + "_" + userNum);

   }
}