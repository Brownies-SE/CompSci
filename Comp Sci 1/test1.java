import java.util.Scanner;

public class test1{
   public static void main(String []args){
   
   
      int num,den;
      int count = 0;
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter two numbers:");
      num = scan.nextInt();
      den = scan.nextInt();
      while(num > 0){
         num = num - den;
         count++;
      }
      System.out.println("The result is: " + count);
   }

}