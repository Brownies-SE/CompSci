import java.util.Scanner;
public class problem6{
public static void main(String []args){

   Scanner scan = new Scanner(System.in);
   int value;
   System.out.println(" Enter a number: ");
   value = scan.nextInt();
      if (value <= 10){
   System.out.println("Great");
  }   else  if (value <= 22){
   System.out.println("Regular");
  }   else  if (value <= 41){
   System.out.println("Inadequate");
  }   else{
   System.out.println("Give up");
  }

      }

}