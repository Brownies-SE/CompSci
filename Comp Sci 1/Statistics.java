import java.util.Scanner;

public class Statistics{
   public static void main(String []args){
   
   Scanner scan = new Scanner(System.in);
   int i = 0;
   int sum = 0;
   int average = 0;
   //int min = 0;
   //int max = 0;
   
   System.out.println("Enter numbers or -1 to end the program");
   int num = scan.nextInt();
   
   for ( i = 0; num >= 0; i++){
      sum += num;
   
   
   
  /* if (i == 0){
      min = num;
      max = num;
   } else if (num > max){
      max = num;
   } else if (num < min){
      min = num;  
   }
   */
   System.out.println("Enter numbers or -1 to end the program");
   num = scan.nextInt();
   
   }
   
   average = sum / i;
   System.out.println("Total entries: " + i);
   System.out.println("Sum of entries: " + sum );
   System.out.println("Average number: " + average);
   //System.out.println("Max entry: " + max);
   //System.out.println("Min enry: " + min);

}
}