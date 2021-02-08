import java.util.Scanner;

public class assignment1{
   public static void main(String []args){
   //declaring the scanner
   Scanner scan = new Scanner(System.in);
   //declaration of my two variables
   double birthYear;
   double age;
   //asking the user for birth year
   System.out.println("Enter your birth year:");
   birthYear = scan.nextDouble();
   //asking the user for age
   System.out.println("Enter your age:");
   age = scan.nextDouble();
   //storing the input as a new variable
   double newAge = birthYear;
   //doing the math and printing in the printf format
   newAge = (((newAge * 2 + 5) * 50) + age - 250) / 100;
   System.out.printf("value: %4.2f", newAge);
   
   }


}